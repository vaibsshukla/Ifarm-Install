package com.b3ds.ifarm.installation.models;

public class VariableHolder {
	
	private Variable variable;

	private Boolean canWrite;

	public VariableHolder(Variable variable, Boolean canWrite) {
		this.variable = variable;
		this.canWrite = canWrite;
	}

	public Variable getVariable() {
		return variable;
	}

	public void setVariable(Variable variable) {
		this.variable = variable;
	}

	public Boolean getCanWrite() {
		return canWrite;
	}

	public void setCanWrite(Boolean canWrite) {
		this.canWrite = canWrite;
	}

	@Override
	public String toString() {
		return "VariableHolder [variable=" + variable + ", canWrite=" + canWrite + "]";
	}

	
	
}
