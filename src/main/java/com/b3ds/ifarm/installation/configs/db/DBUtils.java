/*
 * 	This is a util class which is responsible to provide all components required for Ifarm.
 *  It also maintains the state of all services of Ifarm
 */

package com.b3ds.ifarm.installation.configs.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.b3ds.ifarm.installation.models.Credentials;
import com.b3ds.ifarm.installation.models.IfarmConfig;
import com.b3ds.ifarm.installation.models.Service;


public class DBUtils {

	public Connection connection = null;
	private final static Logger logger = LogManager.getLogger(DBUtils.class);

	public void getConnection() //Connect to sqlite database
	{
		try{
			final String url = "jdbc:sqlite:../sqlite.db";
			connection = DriverManager.getConnection(url);
			
		}catch(SQLException ex)
		{
			ex.printStackTrace();
			logger.error("could not connect to database");
		}
	}
		
	public List<Service> getAllServices() //Get list of required services from sqlite database
	{
		getConnection();
		final String sql = "select * from Services";
		List<Service> serviceList = new ArrayList<>();
		
		try(Connection conn = this.connection;
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql);
				){
			while(rs.next())
			{
				Service service = new Service(rs.getString(1), rs.getString(2),"Unknown", "Unknown",rs.getString(3), "Unknown");
				serviceList.add(service);
			}
		}catch(SQLException ex)
		{
			ex.printStackTrace();
			logger.error("Could not connectect to database");
		}
		
		return serviceList;
	}
	
	public List<String> getDBsDetails(String type)
	{
		getConnection();
		final String sql = "select * from dbTables where dbtype = ?";
		List<String> dbLists = new ArrayList<>();
		
		try(Connection conn = this.connection;
				PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setString(1, type);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next())
			{
				dbLists.add(rs.getString(2));
			}
		}catch(SQLException ex)
		{
			ex.printStackTrace();
			logger.error("Could not connectect to database");
		}
		
		return dbLists;
	}
	
	public void setIfarmConfigTable()
	{
		getConnection();
		final String sql = "create table if not exists configs("+
				"hdfsNameNode varchar(100), hadoopConfigLoc varchar(100),"
				+ "mysqlHost varchar(100),	mysqlPort varchar(100),	mysqlUsername varchar(100),	"
				+ "mysqlPassword varchar(100),	solrHost varchar(100),	solrPort varchar(100),	"
				+ "solrUsername varchar(100),	solrPassword varchar(100),	mongoHostName varchar(100),	"
				+ "mongoPort varchar(100),	mongoUsername varchar(100),	mongoPassword varchar(100), "
				+ "livyHost varchar(100),	livyPort varchar(100),	livyUsername varchar(100),	"
				+ "livyPassword varchar(100),"
				+ "kafkaBrokerHost varchar(100),kafkaBrokerPort varchar(100),ifarmDataHost varchar(100),"
				+ "ifarmDataPort varchar(100),	"
				+ "ifarmPacksHost varchar(100), ifarmPacksPort varchar(100), mysqlDriverLocation varchar(100),"
				+ "nifiHost varchar(100), nifiPort varchar(100), nifiUsername varchar(100),"
				+ "nifiPassword varchar(100))";
		try(Connection conn = this.connection;
				Statement pstmt = conn.createStatement()){
				pstmt.execute(sql);
				
			}catch(SQLException ex){
				ex.printStackTrace();
				logger.error("Internal error");
				logger.error("Could not connect to database");
			}
	}
	
	public IfarmConfig getIfarmConfig() throws SQLException
	{
		getConnection();
		final String sql = "select * from configs";
		try(Connection conn = this.connection;
				PreparedStatement pstmt = conn.prepareStatement(sql)){
			ResultSet rs = pstmt.executeQuery();
			while(rs.next())
			{
				IfarmConfig config = new IfarmConfig();
				config.setHdfsNameNode(rs.getString(1));
				config.setHadoopConfigLoc(rs.getString(2));
				config.setMysqlHost(rs.getString(3));
				config.setMysqlPort(rs.getString(4));
				config.setMysqlUsername(rs.getString(5));
				config.setMysqlPassword(rs.getString(6));
				config.setSolrHost(rs.getString(7));
				config.setSolrPort(rs.getString(8));
				config.setSolrUsername(rs.getString(9));
				config.setSolrPassword(rs.getString(10));
				config.setMongoHostName(rs.getString(11));
				config.setMongoPort(rs.getString(12));
				config.setMongoUsername(rs.getString(13));
				config.setMongoPassword(rs.getString(14));
				config.setLivyHost(rs.getString(15));
				config.setLivyPort(rs.getString(16));
				config.setLivyUsername(rs.getString(17));
				config.setLivyPassword(rs.getString(18));
				config.setKafkaBrokerHost(rs.getString(19));
				config.setKafkaBrokerPort(rs.getString(20));
				config.setIfarmDataHost(rs.getString(21));
				config.setIfarmDataPort(rs.getString(22));
				config.setIfarmPacksHost(rs.getString(23));
				config.setIfarmPacksPort(rs.getString(24));
				
				config.setMysqlDriverLocation(rs.getString(25));
				config.setNifiHost(rs.getString(26));
				config.setNifiPort(rs.getString(27));
				config.setNifiUsername(rs.getString(28));
				config.setNifiPassword(rs.getString(29));
				return config;
			}
		}catch(SQLException ex){
			ex.printStackTrace();
			logger.error("Internal error");
			logger.error("Could not connect to database");
		}
		return new IfarmConfig();
		
	}
	
	public void emptyTable(String tablename)
	{
		getConnection();
		final String sql = "Drop table if exists "+tablename+"";
		try(Connection conn = this.connection;
				PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.execute();
		}catch(SQLException ex){
			ex.printStackTrace();
			logger.error("Internal error");
			logger.error("Could not connect to database");
		}
	}


	public int setIfarmConfig(IfarmConfig config) //This will set the credential details of ambari and other services like mysql, mongo
	{
		emptyTable("configs");
		setIfarmConfigTable();
		getConnection();
		final String sql = "insert into configs(hdfsNameNode, hadoopConfigLoc, mysqlHost ,mysqlPort ,	mysqlUsername ,	mysqlPassword ,	solrHost ,	solrPort ,	solrUsername ,	solrPassword ,	mongoHostName ,	mongoPort ,	mongoUsername ,	mongoPassword , livyHost ,	livyPort ,	livyUsername ,	livyPassword ,	kafkaBrokerHost ,	kafkaBrokerPort ,	ifarmDataHost ,	ifarmDataPort ,	ifarmPacksHost , ifarmPacksPort,  mysqlDriverLocation, nifiHost, nifiPort, nifiUsername, nifiPassword)"
				+ "values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		int status = 0;
		try(Connection conn = this.connection;
			PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setString(1, config.getHdfsNameNode());
			pstmt.setString(2, config.getHadoopConfigLoc());
			pstmt.setString(3, config.getMysqlHost());
			pstmt.setString(4, config.getMysqlPort());
			pstmt.setString(5, config.getMysqlUsername());
			pstmt.setString(6, config.getMysqlPassword());
			pstmt.setString(7, config.getSolrHost());
			pstmt.setString(8, config.getSolrPort());
			pstmt.setString(9, config.getSolrUsername());
			pstmt.setString(10, config.getSolrPassword());
			pstmt.setString(11, config.getMongoHostName());
			pstmt.setString(12, config.getMongoPort());
			pstmt.setString(13, config.getMongoUsername());
			pstmt.setString(14, config.getMongoPassword());
			pstmt.setString(15, config.getLivyHost());
			pstmt.setString(16, config.getLivyPort());
			pstmt.setString(17, config.getLivyUsername());
			pstmt.setString(18, config.getLivyPassword());
			pstmt.setString(19, config.getKafkaBrokerHost());
			pstmt.setString(20, config.getKafkaBrokerPort());
			pstmt.setString(21, config.getIfarmDataHost());
			pstmt.setString(22, config.getIfarmDataPort());
			pstmt.setString(23, config.getIfarmPacksHost());
			pstmt.setString(24, config.getIfarmPacksPort());

			pstmt.setString(25, config.getMysqlDriverLocation());
			pstmt.setString(26, config.getNifiHost());
			pstmt.setString(27, config.getNifiPort());
			pstmt.setString(28, config.getNifiUsername());
			pstmt.setString(29, config.getNifiPassword());
			status = pstmt.executeUpdate();
			return status;
		}catch(SQLException ex){
			ex.printStackTrace();
			logger.error("Internal error");
			logger.error("Could not connect to database");
		}
		return status;
	}

	public int setCredentials(String type, String hostname, String port, String username, String password, String cluster) //This will set the credential details of ambari and other services like mysql, mongo
	{
		deleteRow("credentials", type);
		getConnection();
		final String sql = "insert into credentials (type, hostname, port_no, username, password, cluster)"
				+ "values(?,?,?,?,?,?)";
		int status = 0;
		try(Connection conn = this.connection;
			PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setString(1, type);
			pstmt.setString(2, hostname);
			pstmt.setString(3, port);
			pstmt.setString(4, username);
			pstmt.setString(5, password);
			pstmt.setString(6, cluster);
			status = pstmt.executeUpdate();
			return status;
		}catch(SQLException ex){
			ex.printStackTrace();
			logger.error("Internal error");
			logger.error("Could not connect to database");
		}
		return status;
	}
	
	public Credentials getCredentials(String type) throws SQLException //This will set the credential details of ambari and other services like mysql, mongo
	{
		getConnection();
		final String sql = "select * from credentials where type = ?";
		try(Connection conn = this.connection;
				PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setString(1, type);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next())
			{
				Credentials credentials = new Credentials(rs.getString(2),
						rs.getString(3), rs.getString(6), rs.getString(4), rs.getString(5));
				return credentials;
			}
		}catch(SQLException ex){
			logger.error("Internal error");
			logger.error("Could not connect to database");
			throw new SQLException();
		}
		return new Credentials(null, null, null, null, null);
	}
	
	public void deleteRow(String tablename, String param)
	{
		getConnection();
		final String sql = "delete from "+tablename+" where type = ?";
		try(Connection conn = this.connection;
				PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setString(1, param);
			pstmt.execute();
		}catch(SQLException ex){
			logger.error("Internal error");
			logger.error("Could not connect to database");
		}
	}
	
	public void getColumnNames(final String query)
	{
		getConnection();
		try(Connection conn = this.connection;
				Statement pstmt = conn.createStatement()){
			ResultSet rs = pstmt.executeQuery(query);
			int columncount = rs.getMetaData().getColumnCount();
			logger.info("Total Column count = "+columncount);
			for(int i = 1; i <= columncount; i++)
			{
				logger.info(rs.getMetaData().getColumnLabel(i));
			}
		}catch(SQLException ex){
			ex.printStackTrace();
			logger.error("Internal error");
			logger.error("Could not connect to database");
		}
		
	}
/*	public void getAmbariServiceDetails()
	{
		getConnection();
		final String sql = "select * from Credentials";
		List<Service> serviceList = new ArrayList<>();
		
		try(Connection conn = this.connection;
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql);				
				){
			while(rs.next())
			{
				Service service = new Service(rs.getString(1), rs.getString(2), rs.getString(3));
				serviceList.add(service);
			}
		}catch(SQLException ex)
		{
			ex.printStackTrace();
			logger.error("Could not connectect to database");
		}
		
	}*/
	
	
	public void showMetadata()
	{
		final String sql = "select * from Credentials";
		List<Service> serviceList = new ArrayList<>();
		
		try(Connection conn = this.connection;
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql);				
				){
		}catch(SQLException ex)
		{
			ex.printStackTrace();
			logger.error("Could not connectect to database");
		}
		
		
	}
	
	
	/*
	 * Set the list of registry variables used in ifarm 
	 */
	public void createNifiVariableTable() throws SQLException
	{
		getConnection();
		final String sql1 = "Drop table NifiVariableList";
		final String sql = "create table if not exists NifiVariableList(VariableName varchar(20), VariableValue varchar(100))";
		
		try(Connection conn = this.connection;
				Statement stmt = conn.createStatement();
				){
			stmt.addBatch(sql1);
			stmt.addBatch(sql);
			stmt.executeBatch();
		}catch(SQLException ex)
		{
			ex.printStackTrace();
			logger.error("Could not connectect to database");
		}
	}

	public void insertNifiVariableTable() throws SQLException
	{
		createNifiVariableTable();

		IfarmConfig confi = getIfarmConfig();
		
		String mongo = "mongodb://"+confi.getMongoHostName()+":"+confi.getMongoPort();
		String ifarmmysql = "jdbc:mysql://"+confi.getMysqlHost()+":"+confi.getMysqlPort();
		String mysqluser = confi.getMysqlUsername()+"klp";
		String mysqlpass = confi.getMysqlPassword();
		String solr = confi.getSolrHost()+":"+confi.getSolrPort();
		String kafka = confi.getKafkaBrokerHost()+":"+confi.getKafkaBrokerPort();
		
		String livy = confi.getLivyHost()+":"+confi.getSolrPort()+"/batches";
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
		
		getConnection();

		try(Connection conn = this.connection;
				Statement stmt = conn.createStatement();
				){
			for(String sql : list)
			{
				stmt.addBatch(sql);
			}
			stmt.executeBatch();
		}catch(SQLException ex)
		{
			ex.printStackTrace();
			logger.error("Could not connectect to database");
		}
		
	}

	/*
	 * Get the list of registry variables used in ifarm 
	 */
	public Map<String, String> getNifiVariableList() throws SQLException
	{
		DBUtils utils = new DBUtils();
		utils.getConnection();
		Connection conn = utils.connection;
		
		final String sql = "select * from NifiVariableList";
		Statement stm =  conn.createStatement();
		ResultSet rs = stm.executeQuery(sql);
		Map<String, String> map = new HashMap<>();
		while(rs.next())
		{
			map.put(rs.getString(1), rs.getString(2));
		}
		
		return map;
	}


/*	public static void main(String[] args) {
		DBUtils utils = new DBUtils();
	}*/
}
