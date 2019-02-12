package com.b3ds.ifarm.installation.models;

public class Variable {
	
	private String name;
	private String value;
		
	public Variable(String name, String value) {
		this.name = name;
		this.value = value;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	@Override
	public String toString() {
		return "Variable [name=" + name + ", value=" + value + "]";
	}
	
	
	
}
