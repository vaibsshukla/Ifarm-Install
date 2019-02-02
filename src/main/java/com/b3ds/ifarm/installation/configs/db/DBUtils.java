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
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
				Service service = new Service(rs.getString(1), rs.getString(2), rs.getString(3));
				serviceList.add(service);
			}
		}catch(SQLException ex)
		{
			ex.printStackTrace();
			logger.error("Could not connectect to database");
		}
		
		return serviceList;
	}
	
	public int setCredentials(String type, String hostname, String port, String username, String password, String cluster) //This will set the credential details of ambari and other services like mysql, mongo
	{
		getConnection();
		final String sql = "insert into credentials (type, hostname, port_no, username, password)values(?,?,?,?,?,?)";
		int status = 0;
		try(Connection conn = this.connection;
				PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setString(1, type);
			pstmt.setString(2, hostname);
			pstmt.setString(3, port);
			pstmt.setString(4, username);
			pstmt.setString(5, password);
			pstmt.setString(5, cluster);
			status = pstmt.executeUpdate();
			return status;
		}catch(SQLException ex){
			ex.printStackTrace();
			logger.error("Internal error");
			logger.error("Could not connect to database");
		}
		return status;
	}
	
	public void getCredentials(String type) //This will set the credential details of ambari and other services like mysql, mongo
	{
		getConnection();
		final String sql = "select * from credentials where type = ?";
		try(Connection conn = this.connection;
				PreparedStatement pstmt = conn.prepareStatement(sql)){
			pstmt.setString(1, type);
			ResultSet rs = pstmt.executeQuery();
			while(rs.next())
			{
				System.out.println(rs.getString(2));
			}
		}catch(SQLException ex){
			ex.printStackTrace();
			logger.error("Internal error");
			logger.error("Could not connect to database");
		}
	}

	public void getAmbariServiceDetails()
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
		
	}
	
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
/*	public static void main(String[] args) {
		DBUtils utils = new DBUtils();
	}*/
}
