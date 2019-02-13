package com.b3ds.ifarm.installation.utils;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class InitializeDb {
	
	public Connection createNewDatabase(String fileName) throws SQLException, IOException 
	{	 
		String projectLoc=System.getProperty("user.dir");
		System.out.println(projectLoc);
		String url = "jdbc:sqlite:"+projectLoc+"\\src\\main\\resources\\sqlite.db";
	
		
		Connection con = DriverManager.getConnection(url);
        try(Connection conn = DriverManager.getConnection(url))
        {
            if (conn != null) 
            {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("A new database has been created.");
            }
        } catch (SQLException e)
        	{
            	System.out.println(e.getMessage());
        	}
        
    		return con;   
	}
	
	
	public void createSchema() throws SQLException, IOException
	{
		Connection conn=createNewDatabase("Initialize.db");
	    Statement stmt;
        ResultSet res = null ;
        try
        {
        	String sql1 = "create table if not exists Services (`type` varchar(8), service_Name varchar(24) ,service_version varchar(8))";
        	String sql2 = "Insert into Services values('HDP','HDFS','2.6.0')";
        	String sql3 = "Insert into Services values('HDP','NIFI','1.5.0')";
          	String sql4=  "Insert into Services values('HDP','Zookeeper','3.4.0')";
          	String sql5 = "Insert into Services values('HDP','Kafka','1.0.0')";
          	String sql6 = "Insert into Services values('HDP','Spark2','2.3.0')";
          	String sql7 = "Insert into Services values('HDP', 'solr','5.5')";
          	String sql8 = "Insert into Services values('NON-HDP','Mysql','8.0')";
          	String sql9 = "Insert into Services values('NON-HDP','Mongo','3.5')";
          	String sql10 = "Insert into Services values('NON-HDP','Neo4J','3.5')";
          	String sql11 = "Insert into Services values('NON-HDP','PackServer','1.0')";
        	String sql12 = "create table ClusterDetails(host_name varchar(16),port_no varchar(32),userName varchar(16),"
        			+ "`password` varchar(32),clustername varchar(32),HDPVersion varchar(8))";
        	String sql13 = "create table Credentials(type varchar(8),hostname varchar(32),port_No varchar(16),userName varchar(16),"
        			+ "`password` varchar(32))";
            String sql14="select * from Services ";
        	stmt = conn.createStatement();
            stmt.execute(sql1);
            stmt.execute(sql2);
            stmt.execute(sql3);
            stmt.execute(sql4);
            stmt.execute(sql5);
            stmt.execute(sql6);
            stmt.execute(sql7);
            stmt.execute(sql8);
            stmt.execute(sql9);
            stmt.execute(sql10);
            stmt.execute(sql11);
            stmt.execute(sql12);
            stmt.execute(sql13);
            res = stmt.executeQuery(sql14);
            while(res.next())
            {
	            String service_name=res.getString("service_name");
	            String service_version=res.getString("service_version");
	            String type=res.getString("type");
	            System.out.println(type+" : "+service_name+ " : " +service_version);
	            
            }         
        }
        catch(SQLException e)
        {
            System.out.println("Error creating or running statement: " + e.toString());
            try{ conn.close();
            }
            catch(Exception e1)
            {
            System.out.println("Error while closing connection: " +e1.toString());
            }
        }
	}
	
	public static void main(String[] args) throws SQLException, IOException {
		
		InitializeDb db=new InitializeDb();
		db.createSchema();
	}
}