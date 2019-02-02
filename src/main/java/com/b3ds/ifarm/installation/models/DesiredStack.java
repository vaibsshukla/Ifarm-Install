package com.b3ds.ifarm.installation.models;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;

public class DesiredStack implements Serializable{
	
	@SerializedName("stackName")
	private String stackName;

	@SerializedName("stackVersion")
	private String stackVersion;
	
	@SerializedName("stackId")
	private String stackId;

	public DesiredStack(String stackName, String stackVersion, String stackId) {
		this.stackName = stackName;
		this.stackVersion = stackVersion;
		this.stackId = stackId;
	}

	public String getStackName() {
		return stackName;
	}

	public void setStackName(String stackName) {
		this.stackName = stackName;
	}

	public String getStackVersion() {
		return stackVersion;
	}

	public void setStackVersion(String stackVersion) {
		this.stackVersion = stackVersion;
	}

	public String getStackId() {
		return stackId;
	}

	public void setStackId(String stackId) {
		this.stackId = stackId;
	}

	@Override
	public String toString() {
		return "DesiredStack [stackName=" + stackName + ", stackVersion=" + stackVersion + ", stackId=" + stackId + "]";
	}
	
}
