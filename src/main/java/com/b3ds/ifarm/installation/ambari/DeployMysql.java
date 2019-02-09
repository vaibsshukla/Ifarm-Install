/*package com.b3ds.ifarm.installation.ambari;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

public class DeployMysql {
	
	private BasicDataSource dataSource(String url,String userName,String password)
	{
		BasicDataSource dataSource=new BasicDataSource();
		dataSource.setDriverClassName("com.mysql.cj.jdbc.driver");   // Provide options for sql driver 5.1
		dataSource.setUrl(url);
		dataSource.setUsername(userName);
		dataSource.setPassword(password);
		JdbcTemplate template=new JdbcTemplate();
		template.setDataSource(dataSource);
		return dataSource;
	}
	
	public JdbcTemplate jdbcTemplate(String host, String port, String username, String password)
	{
		JdbcTemplate template = new JdbcTemplate();
		final String url = "jdbc:mysql://"+host+":"+port;
		template.setDataSource(dataSource(url,username, password));
		return template;
	}
	
	public void createDb(String url,String userName,String password)
	{
		JdbcTemplate template = new JdbcTemplate();
		template.setDataSource(dataSource(url,userName,password));
		Resource resource=new ClassPathResource("my.sql");
		ResourceDatabasePopulator databasepopulator = new ResourceDatabasePopulator(resource);
		databasepopulator.execute(dataSource(url,userName,password));
	}
	
}
*/