package com.b3ds.ifarm.installation;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.b3ds.ifarm.installation.configs.db.DBUtils;

public class NifiSqlite {
	
	public void db() throws SQLException
	{
		DBUtils utils = new DBUtils();
		utils.getConnection();
		Connection conn = utils.connection;
		
		final String sql = "create table if not exists NifiJobs(ProcessGroupId varchar(20), ProcessGroupName varchar(30))";
		Statement stm = conn.createStatement();
		stm.execute(sql);
	}

	public void insertdb() throws SQLException
	{
		DBUtils utils = new DBUtils();
		utils.getConnection();
		Connection conn = utils.connection;
		List<String> list = new ArrayList<String>();
		list.add("insert into NifiJobs(ProcessGroupId , ProcessGroupName) values ('c7838b86-0168-1000-6205-738dfd75ccca', 'DataRecievers')");
		list.add("insert into NifiJobs(ProcessGroupId , ProcessGroupName) values ('3b834954-fc22-3cc1-9b54-e3e696a070ae', 'Packs')");
		list.add("insert into NifiJobs(ProcessGroupId , ProcessGroupName) values ('c640add0-be42-3da7-80c6-6892643f819e', 'AirPollution')");
		list.add("insert into NifiJobs(ProcessGroupId , ProcessGroupName) values ('63a8df39-b899-3873-d954-c91d712b5634', 'FHIR Standard')");
		list.add("insert into NifiJobs(ProcessGroupId , ProcessGroupName) values ('a81d3438-9715-3232-ea81-5f44a0d5b32b', 'Metadata')");
		list.add("insert into NifiJobs(ProcessGroupId , ProcessGroupName) values ('9cde9387-0168-1000-854b-8762ce34af4c', 'FHIR-Stage-1')");
		list.add("insert into NifiJobs(ProcessGroupId , ProcessGroupName) values ('0cfd6b21-d5ba-30f9-c638-a64222032fa2', 'FHIR After Staging')");
		list.add("insert into NifiJobs(ProcessGroupId , ProcessGroupName) values ('22083987-5a97-1d17-edf3-beeba5ba8268', 'FailedDataGroup')");
		list.add("insert into NifiJobs(ProcessGroupId , ProcessGroupName) values ('eb8a4e65-f5df-343a-83e3-eb21b72d9253', 'FEISH_APP')");
		Statement stm = conn.createStatement();
		
		for(String sql : list)
		{
			stm.execute(sql);
		}
	}
	
	@Test
	public void checkdb() throws SQLException
	{
		DBUtils utils = new DBUtils();
		utils.getConnection();
		Connection conn = utils.connection;
		
		final String sql = "select * from NifiJobs";
		Statement stm =  conn.createStatement();
		ResultSet rs = stm.executeQuery(sql);
		
		while(rs.next())
		{
			System.out.println(rs.getString(1)+ "  :  "+rs.getString(2));
		}
		
	}
}
