package com.b3ds.ifarm.installation.models;

import java.util.List;

public class VariableRegistry {
	
	private String processGroupId;
	private List<VariableHolder> variables;
	
	public VariableRegistry(){}

	public VariableRegistry(String processGroupId, List<VariableHolder> variables) {
		super();
		this.processGroupId = processGroupId;
		this.variables = variables;
	}

	public String getProcessGroupId() {
		return processGroupId;
	}

	public void setProcessGroupId(String processGroupId) {
		this.processGroupId = processGroupId;
	}

	public List<VariableHolder> getVariables() {
		return variables;
	}

	public void setVariables(List<VariableHolder> variables) {
		this.variables = variables;
	}

	@Override
	public String toString() {
		return "VariableRegistry [processGroupId=" + processGroupId + ", variables=" + variables + "]";
	}
		
	
}
