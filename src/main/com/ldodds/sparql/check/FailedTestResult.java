package com.ldodds.sparql.check;

public class FailedTestResult extends AbstractTestResult {

	private String msg;
	
	public FailedTestResult(String title, String description, String msg) {
		super(title, description);
		this.msg = msg;
	}
	
	public boolean isSuccessfullyRun() { return false; }
	
	public String getFailureMessage() {
		return msg;
	}
	
}
