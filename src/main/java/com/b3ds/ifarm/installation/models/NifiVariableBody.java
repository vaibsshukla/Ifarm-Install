package com.b3ds.ifarm.installation.models;

public class NifiVariableBody {
	
	private ProcessGroupRevision processGroupRevision;
	private VariableRegistry variableRegistry;
//	private String processGroupId;
	
/*	public NifiVariableBody(ProcessGroupRevision processGroupRevision, VariableRegistry variableRegistry,
			String processGroupId) {*/
	public NifiVariableBody(ProcessGroupRevision processGroupRevision, VariableRegistry variableRegistry) {
		this.processGroupRevision = processGroupRevision;
		this.variableRegistry = variableRegistry;
//		this.processGroupId = processGroupId;
	}

	public ProcessGroupRevision getProcessGroupRevision() {
		return processGroupRevision;
	}

	public void setProcessGroupRevision(ProcessGroupRevision processGroupRevision) {
		this.processGroupRevision = processGroupRevision;
	}

	public VariableRegistry getVariableRegistry() {
		return variableRegistry;
	}

	public void setVariableRegistry(VariableRegistry variableRegistry) {
		this.variableRegistry = variableRegistry;
	}

	@Override
	public String toString() {
		return "NifiVariableBody [processGroupRevision=" + processGroupRevision + ", variableRegistry="
				+ variableRegistry + "]";
	}

/*	public String getProcessGroupId() {
		return processGroupId;
	}

	public void setProcessGroupId(String processGroupId) {
		this.processGroupId = processGroupId;
	}

	@Override
	public String toString() {
		return "NifiVariableBody [processGroupRevision=" + processGroupRevision + ", variableRegistry="
				+ variableRegistry + ", processGroupId=" + processGroupId + "]";
	}*/
	
	
	
}
