package com.ldodds.sparql.check;

import java.util.ArrayList;
import java.util.List;

/**
 * Result of applying a group of tests to a given dataset
 * 
 * @author ldodds
 */
public class GroupResult extends AbstractResult {
	private List<AbstractTestResult> testResults;
	
	public GroupResult(String title, String description) {
		super(title, description);
		testResults = new ArrayList<AbstractTestResult>();
	}
	
	public void addTestResult(AbstractTestResult result) {
		testResults.add(result);
	}
	
	/**
	 * @return the list of test results
	 */
	public List<AbstractTestResult> getResults() {
		return testResults;
	}
	
}
