package com.b3ds.ifarm.installation.dbdeploy;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import com.b3ds.ifarm.installation.configs.db.DBUtils;
import com.b3ds.ifarm.installation.models.Credentials;
import com.b3ds.ifarm.installation.models.IfarmConfig;

public class MysqlDeploy implements IFaceDbDeploy{
	
	private final Logger logger = LogManager.getLogger(MysqlDeploy.class);

	private BasicDataSource dataSource(String hostname, String port, String username, String password)
	{
		BasicDataSource basicDataSource = new BasicDataSource();
		basicDataSource.setUrl("jdbc:mysql://"+hostname+":"+port+"/");
		basicDataSource.setUsername(username);
		basicDataSource.setPassword(password);
		basicDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
		
		return basicDataSource;
	}
		
	@Override
	public Map<String, String> deployDatabase() {
		DBUtils utils = new DBUtils();
		IfarmConfig credentials = null;
		String hostname = null;
		String port = null;
		String username = null;
		String password = null;
		
		try{
			credentials = utils.getIfarmConfig();
			hostname = credentials.getMysqlHost();
			port = credentials.getMysqlPort();
			username = credentials.getMysqlUsername();
			password = credentials.getMysqlPassword();
			
		}catch(SQLException ex)
		{
			return null;
		}		

		JdbcTemplate template = new JdbcTemplate();
		template.setDataSource(dataSource(hostname, port, username, password));
		
		Resource resource = new ClassPathResource("msyqlscript/mysql.sql");
		ResourceDatabasePopulator databasepopulator = new ResourceDatabasePopulator(resource);
		databasepopulator.execute(dataSource(hostname, port, username, password));		
		
		List<String> listDbs = utils.getDBsDetails("Mysql");
		Map<String, String> map = checkDb(listDbs, template);
		return map;
	}
	
	private Map<String, String> checkDb(List<String> listDbs, JdbcTemplate template)
	{
		Map<String, String> map = new HashMap<>();
		
		Iterator<String> itr = listDbs.iterator();
		
		while(itr.hasNext())
		{
			String db = itr.next();
			getDbDetails(db, template, map);
		}
		return map;
	}
	
	private void getDbDetails(String dbname, JdbcTemplate template, Map<String, String> map)
	{
		Integer i = template.queryForObject("select count(*) from information_Schema.`TABLES` where table_schema = ?", new String[]{dbname}, Integer.class);
		map.put(dbname, i.toString());
	}
	
	public static void main(String[] args) {
		IFaceDbDeploy db = new MysqlDeploy();
		db.deployDatabase();
	}
	
}
