package com.b3ds.ifarm.installation.models;

public class Credentials {
	
	private String hostname;
	private String port;
	private String clusterName;
	private String userName;
	private String password;
	
	
	public Credentials(String hostname, String port, String clusterName, String userName, String password) {
		this.hostname = hostname;
		this.port = port;
		this.clusterName = clusterName;
		this.userName = userName;
		this.password = password;
	}
	public String getHostname() {
		return hostname;
	}
	public void setHostname(String hostname) {
		this.hostname = hostname;
	}
	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
	}
	public String getClusterName() {
		return clusterName;
	}
	public void setClusterName(String clusterName) {
		this.clusterName = clusterName;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	@Override
	public String toString() {
		return "Credentials [hostname=" + hostname + ", port=" + port + ", clusterName=" + clusterName + ", userName="
				+ userName + ", password=" + password + "]";
	}
	
	
}
