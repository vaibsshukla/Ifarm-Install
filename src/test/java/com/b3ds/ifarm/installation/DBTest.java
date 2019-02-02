package com.b3ds.ifarm.installation;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;

import com.b3ds.ifarm.installation.configs.db.DBUtils;

public class DBTest {
	
	@Test
	public void testInsertAmbari()
	{
		DBUtils util = new DBUtils();
//		int i = util.setCredentials("Ambari", "localhost", new Integer(8080).toString(), "Vivek", "Singh", "Sandbox");
//		System.out.println(i);
		util.getCredentials("Ambari");
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
}
