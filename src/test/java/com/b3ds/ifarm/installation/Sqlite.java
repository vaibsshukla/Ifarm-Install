package com.b3ds.ifarm.installation;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.junit.Test;

import com.b3ds.ifarm.installation.configs.db.DBUtils;

public class Sqlite {
	
	public void db() throws SQLException
	{
		DBUtils utils = new DBUtils();
		utils.getConnection();
		Connection conn = utils.connection;
		
		final String sql = "create table if not exists dbTables (dbtype varchar(25), dbname varchar(25))";
		Statement stm = conn.createStatement();
		stm.execute(sql);
	}

	public void insertdb() throws SQLException
	{
		DBUtils utils = new DBUtils();
		utils.getConnection();
		Connection conn = utils.connection;
		
//		final String sql1 = "insert into dbTables (dbtype, dbname) values ('Mysql','HL7')";
		final String sql2 = "insert into dbTables (dbtype, dbname) values ('Mysql','IfarmStage1')";
		final String sql3 = "insert into dbTables (dbtype, dbname) values ('Mysql','IfarmMetadata')";
		final String sql4 = "insert into dbTables (dbtype, dbname) values ('Mysql','FHIRV3')";
		final String sql5 = "insert into dbTables (dbtype, dbname) values ('Mysql','GenomicsReference')";
		Statement stm = conn.createStatement();
//		stm.execute(sql1);
		stm.execute(sql2);
		stm.execute(sql3);
		stm.execute(sql4);
		stm.execute(sql5);
	}
	
	@Test
	public void checkdb() throws SQLException
	{
		DBUtils utils = new DBUtils();
		utils.getConnection();
		Connection conn = utils.connection;
		
		final String sql = "select * from dbTables";
		Statement stm =  conn.createStatement();
		ResultSet rs = stm.executeQuery(sql);
		
		while(rs.next())
		{
			System.out.println(rs.getString(1)+ "  :  "+rs.getString(2));
		}
		
	}
}
