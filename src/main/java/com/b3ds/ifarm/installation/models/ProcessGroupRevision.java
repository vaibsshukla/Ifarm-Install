package com.b3ds.ifarm.installation.models;

public class ProcessGroupRevision {
	
	private Long version;

	public ProcessGroupRevision()
	{
		
	}
	public ProcessGroupRevision(Long version) {
		this.version = version;
	}

	public Long getVersion() {
		return version;
	}

	public void setVersion(Long version) {
		this.version = version;
	}

	@Override
	public String toString() {
		return "ProcessGroupRevision [version=" + version + "]";
	}
	
	
}
