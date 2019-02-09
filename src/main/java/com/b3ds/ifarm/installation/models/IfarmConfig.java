package com.b3ds.ifarm.installation.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class IfarmConfig {

@SerializedName("hdfsNameNode")
@Expose
private String hdfsNameNode;
@SerializedName("hadoopConfigLoc")
@Expose
private String hadoopConfigLoc;
@SerializedName("mysqlHost")
@Expose
private String mysqlHost;
@SerializedName("mysqlPort")
@Expose
private String mysqlPort;
@SerializedName("mysqlUsername")
@Expose
private String mysqlUsername;
@SerializedName("mysqlPassword")
@Expose
private String mysqlPassword;
@SerializedName("solrHost")
@Expose
private String solrHost;
@SerializedName("solrPort")
@Expose
private String solrPort;
@SerializedName("solrUsername")
@Expose
private String solrUsername;
@SerializedName("solrPassword")
@Expose
private String solrPassword;
@SerializedName("mongoHostName")
@Expose
private String mongoHostName;
@SerializedName("mongoPort")
@Expose
private String mongoPort;
@SerializedName("mongoUsername")
@Expose
private String mongoUsername;
@SerializedName("mongoPassword")
@Expose
private String mongoPassword;
@SerializedName("livyHost")
@Expose
private String livyHost;
@SerializedName("livyPort")
@Expose
private String livyPort;
@SerializedName("livyUsername")
@Expose
private String livyUsername;
@SerializedName("livyPassword")
@Expose
private String livyPassword;
@SerializedName("kafkaBrokerHost")
@Expose
private String kafkaBrokerHost;
@SerializedName("kafkaBrokerPort")
@Expose
private String kafkaBrokerPort;
@SerializedName("ifarmDataHost")
@Expose
private String ifarmDataHost;
@SerializedName("ifarmDataPort")
@Expose
private String ifarmDataPort;
@SerializedName("ifarmPacksHost")
@Expose
private String ifarmPacksHost;
@SerializedName("ifarmPacksPort")
@Expose
private String ifarmPacksPort;

public String getHdfsNameNode() {
return hdfsNameNode;
}

public void setHdfsNameNode(String hdfsNameNode) {
this.hdfsNameNode = hdfsNameNode;
}

public String getHadoopConfigLoc() {
return hadoopConfigLoc;
}

public void setHadoopConfigLoc(String hadoopConfigLoc) {
this.hadoopConfigLoc = hadoopConfigLoc;
}

public String getMysqlHost() {
return mysqlHost;
}

public void setMysqlHost(String mysqlHost) {
this.mysqlHost = mysqlHost;
}

public String getMysqlPort() {
return mysqlPort;
}

public void setMysqlPort(String mysqlPort) {
this.mysqlPort = mysqlPort;
}

public String getMysqlUsername() {
return mysqlUsername;
}

public void setMysqlUsername(String mysqlUsername) {
this.mysqlUsername = mysqlUsername;
}

public String getMysqlPassword() {
return mysqlPassword;
}

public void setMysqlPassword(String mysqlPassword) {
this.mysqlPassword = mysqlPassword;
}

public String getSolrHost() {
return solrHost;
}

public void setSolrHost(String solrHost) {
this.solrHost = solrHost;
}

public String getSolrPort() {
return solrPort;
}

public void setSolrPort(String solrPort) {
this.solrPort = solrPort;
}

public String getSolrUsername() {
return solrUsername;
}

public void setSolrUsername(String solrUsername) {
this.solrUsername = solrUsername;
}

public String getSolrPassword() {
return solrPassword;
}

public void setSolrPassword(String solrPassword) {
this.solrPassword = solrPassword;
}

public String getMongoHostName() {
return mongoHostName;
}

public void setMongoHostName(String mongoHostName) {
this.mongoHostName = mongoHostName;
}

public String getMongoPort() {
return mongoPort;
}

public void setMongoPort(String mongoPort) {
this.mongoPort = mongoPort;
}

public String getMongoUsername() {
return mongoUsername;
}

public void setMongoUsername(String mongoUsername) {
this.mongoUsername = mongoUsername;
}

public String getMongoPassword() {
return mongoPassword;
}

public void setMongoPassword(String mongoPassword) {
this.mongoPassword = mongoPassword;
}

public String getLivyHost() {
return livyHost;
}

public void setLivyHost(String livyHost) {
this.livyHost = livyHost;
}

public String getLivyPort() {
return livyPort;
}

public void setLivyPort(String livyPort) {
this.livyPort = livyPort;
}

public String getLivyUsername() {
return livyUsername;
}

public void setLivyUsername(String livyUsername) {
this.livyUsername = livyUsername;
}

public String getLivyPassword() {
return livyPassword;
}

public void setLivyPassword(String livyPassword) {
this.livyPassword = livyPassword;
}

public String getKafkaBrokerHost() {
return kafkaBrokerHost;
}

public void setKafkaBrokerHost(String kafkaBrokerHost) {
this.kafkaBrokerHost = kafkaBrokerHost;
}

public String getKafkaBrokerPort() {
return kafkaBrokerPort;
}

public void setKafkaBrokerPort(String kafkaBrokerPort) {
this.kafkaBrokerPort = kafkaBrokerPort;
}

public String getIfarmDataHost() {
return ifarmDataHost;
}

public void setIfarmDataHost(String ifarmDataHost) {
this.ifarmDataHost = ifarmDataHost;
}

public String getIfarmDataPort() {
return ifarmDataPort;
}

public void setIfarmDataPort(String ifarmDataPort) {
this.ifarmDataPort = ifarmDataPort;
}

public String getIfarmPacksHost() {
return ifarmPacksHost;
}

public void setIfarmPacksHost(String ifarmPacksHost) {
this.ifarmPacksHost = ifarmPacksHost;
}

public String getIfarmPacksPort() {
return ifarmPacksPort;
}

public void setIfarmPacksPort(String ifarmPacksPort) {
this.ifarmPacksPort = ifarmPacksPort;
}

@Override
public String toString() {
	return "IfarmConfig [hdfsNameNode=" + hdfsNameNode + ", hadoopConfigLoc=" + hadoopConfigLoc + ", mysqlHost="
			+ mysqlHost + ", mysqlPort=" + mysqlPort + ", mysqlUsername=" + mysqlUsername + ", mysqlPassword="
			+ mysqlPassword + ", solrHost=" + solrHost + ", solrPort=" + solrPort + ", solrUsername=" + solrUsername
			+ ", solrPassword=" + solrPassword + ", mongoHostName=" + mongoHostName + ", mongoPort=" + mongoPort
			+ ", mongoUsername=" + mongoUsername + ", mongoPassword=" + mongoPassword + ", livyHost=" + livyHost
			+ ", livyPort=" + livyPort + ", livyUsername=" + livyUsername + ", livyPassword=" + livyPassword
			+ ", kafkaBrokerHost=" + kafkaBrokerHost + ", kafkaBrokerPort=" + kafkaBrokerPort + ", ifarmDataHost="
			+ ifarmDataHost + ", ifarmDataPort=" + ifarmDataPort + ", ifarmPacksHost=" + ifarmPacksHost
			+ ", ifarmPacksPort=" + ifarmPacksPort + "]";
}

}