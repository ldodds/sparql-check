package com.ldodds.sparql.check;

public abstract class AbstractTestResult extends AbstractResult {

	public AbstractTestResult(String title, String description) {
		super(title, description);
	}	
	
	/**
	 * Was the test successfully executed? A successfully executed test may still generate 
	 * warnings or errors. This just indicates if the test itself failed to execute or not
	 * 
	 * @return true if successful
	 */
	public abstract boolean isSuccessfullyRun(); 
}
