package com.b3ds.ifarm.installation.utils;

import java.sql.SQLException;
import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

public class MySqlOperations {

	

	public BasicDataSource dataSource(String url,String userName,String password) throws SQLException
	{
		BasicDataSource dataSource=new BasicDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");   // Provide options for sql driver 5.1
		dataSource.setUrl(url);
		dataSource.setUsername(userName);
		dataSource.setPassword(password);
		JdbcTemplate template=new JdbcTemplate();
		template.setDataSource(dataSource);
		return dataSource;
	}
	
	public void createDb(String url,String userName,String password) throws SQLException
	{
		JdbcTemplate template = new JdbcTemplate();
		template.setDataSource(dataSource(url,userName,password));
		Resource resource=new ClassPathResource("my.sql");
		ResourceDatabasePopulator databasepopulator = new ResourceDatabasePopulator(resource);
		databasepopulator.execute(dataSource(url,userName,password));
		
	
	}

	
}