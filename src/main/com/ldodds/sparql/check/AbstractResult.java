package com.ldodds.sparql.check;

public abstract class AbstractResult {
	private String title;
	private String description;
	
	public AbstractResult(String title, String description) {
		this.title = title;
		this.description = description;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getDescription() {
		return description;
	}
	
}
