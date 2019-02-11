package com.b3ds.ifarm.installation;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.b3ds.ifarm.installation.configs.db.DBUtils;
import com.b3ds.ifarm.installation.models.Credentials;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class DBTest {
	
//	@Test
	public void testInsertAmbari()
	{
		DBUtils util = new DBUtils();
		int i = util.setCredentials("Ambari", "sec2-18-188-39-103.us-east-2.compute.amazonaws.com", new Integer(8080).toString(), "admin", "Intelcore@i5", "Sandbox");
//		System.out.println(i);
//		util.getCredentials("Ambari");
	}

//	@Test
	public void testMysql()
	{
		DBUtils util = new DBUtils();
		int i = util.setCredentials("Mysql", "192.168.1.16", new Integer(3306).toString(), "vivek", "2611798", null);
		
	}
	
	//	@Test
	public void testCreateTable() throws SQLException
	{
		DBUtils util = new DBUtils();
		util.getConnection();
		Connection con = util.connection;
		
		String sql = "create table credentials( type varchar(10), hostname varchar(100), port_no varchar(5),"
				+ "username varchar(100), password varchar(100), cluster varchar(10))";
//		String sql = "drop table credentials";
		
		Statement stm = con.createStatement();
		stm.execute(sql);
		con.close();
	}
	
	@Test
	public void uestgetCredentials() throws SQLException
	{
		String type = "Ambari";
		DBUtils util = new DBUtils();
		Credentials cred = util.getCredentials(type);
		System.out.println(cred);
	}
	
//	@Test
	public void testGetColumnNames()
	{
		String query = "Select * from credentials";
		DBUtils utils = new DBUtils();
		utils.getColumnNames(query);
	}
	
//	@Test
	public void testDBsDetails()
	{
		DBUtils utils = new DBUtils();
		List<String> list = utils.getDBsDetails("Mysql");
		System.out.println(list);
	}
	
//	@Test
	public void testConfigsDetail() throws SQLException
	{
		DBUtils utils = new DBUtils();
		//utils.emptyTable("configs");
		System.out.println(utils.getIfarmConfig());
	}
}
