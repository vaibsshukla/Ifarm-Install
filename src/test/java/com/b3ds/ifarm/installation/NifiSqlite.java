package com.b3ds.ifarm.installation;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.b3ds.ifarm.installation.configs.db.DBUtils;
import com.b3ds.ifarm.installation.models.IfarmConfig;

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
	
	public void createNifiVariableTable() throws SQLException
	{
		DBUtils utils = new DBUtils();
		utils.getConnection();
		Connection conn = utils.connection;
		
		final String sql1 = "Drop table NifiVariableList";
		final String sql = "create table if not exists NifiVariableList(VariableName varchar(20), VariableValue varchar(100))";
		Statement stm = conn.createStatement();
		stm.execute(sql1);
		stm.execute(sql);
	}
	
//	@Test
	public void insertNifiVariableTable() throws SQLException
	{
		createNifiVariableTable();
		DBUtils utils = new DBUtils();
		IfarmConfig confi = utils.getIfarmConfig();
		String mongo = "mongodb://"+confi.getMongoHostName()+":"+confi.getMongoPort();
		String ifarmmysql = "jdbc:mysql://"+confi.getMysqlHost()+":"+confi.getMysqlPort();
		String mysqluser = confi.getMysqlUsername();
		String mysqlpass = confi.getMysqlPassword();
		String solr = confi.getSolrHost()+":"+confi.getSolrPort();
		String kafka = confi.getKafkaBrokerHost()+":"+confi.getKafkaBrokerPort();
		
		String livy = confi.getLivyHost()+":"+confi.getSolrPort()+"/batches";
		utils.getConnection();
		Connection conn = utils.connection;
		List<String> list = new ArrayList<String>();
		list.add("insert into NifiVariableList(VariableName, VariableValue) values ('IFARM_MONGO','"+mongo+"')");
		list.add("insert into NifiVariableList(VariableName, VariableValue) values ('IFARM_MYSQL_HOST','"+ifarmmysql+"')");
		list.add("insert into NifiVariableList(VariableName, VariableValue) values ('IFARM_MYSQL_USER','"+mysqluser+"')");
		list.add("insert into NifiVariableList(VariableName, VariableValue) values ('IFARM_MYSQL_PASS','"+mysqlpass+"')");
		list.add("insert into NifiVariableList(VariableName, VariableValue) values ('IFARM_MYSQL_DRIVER_LOC','"+confi.getMysqlDriverLocation()+"')");
		list.add("insert into NifiVariableList(VariableName, VariableValue) values ('HADOOP_CONFIG_LOC','"+confi.getHadoopConfigLoc()+"')");
		list.add("insert into NifiVariableList(VariableName, VariableValue) values ('SOLR_LOC','"+solr+"')");
		list.add("insert into NifiVariableList(VariableName, VariableValue) values ('SOLR_USER','"+confi.getSolrUsername()+"')");
		list.add("insert into NifiVariableList(VariableName, VariableValue) values ('SOLR_PASS','"+confi.getSolrPassword()+"')");
/*		list.add("insert into NifiVariableList(VariableName, VariableValue) values ('SPARK_STAGE_1')");
		list.add("insert into NifiVariableList(VariableName, VariableValue) values ('SPARK_STAGE_2')");*/
		list.add("insert into NifiVariableList(VariableName, VariableValue) values ('LIVY_URL','"+livy+"')");
		list.add("insert into NifiVariableList(VariableName, VariableValue) values ('KAFKA_BROKER','"+kafka+"')");
		list.add("insert into NifiVariableList(VariableName, VariableValue) values ('MYSQL_HOST','"+confi.getMysqlHost()+"')");
		list.add("insert into NifiVariableList(VariableName, VariableValue) values ('MYSQL_PORT','"+confi.getMysqlPort()+"')");
		Statement stm = conn.createStatement();
		
		for(String sql : list)
		{
			stm.execute(sql);
		}
	}
	
	@Test
	public void checkNifiVariableList() throws SQLException
	{
		DBUtils utils = new DBUtils();
		utils.getConnection();
		Connection conn = utils.connection;
		
		final String sql = "select * from NifiVariableList";
		Statement stm =  conn.createStatement();
		ResultSet rs = stm.executeQuery(sql);
		
		while(rs.next())
		{
			System.out.println(rs.getString(1)+"  :  "+rs.getString(2));
		}
		
	}

}
