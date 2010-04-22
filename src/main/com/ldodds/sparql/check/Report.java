package com.ldodds.sparql.check;

import java.util.ArrayList;
import java.util.List;

/**
 * A test report generated by applying a test case to a dataset.
 * 
 * @author ldodds
 */
public class Report extends AbstractResult {

	private List<GroupResult> results;
	private String id;
	
	public Report(String title, String description, String id) {
		super(title, description);
		this.id = id;
		results = new ArrayList<GroupResult>();
	}
	
	/**
	 * @return URI of the Test case that was executed
	 */
	public String getId() {
		return id;		
	}
	
	public void addGroupResult(GroupResult result) {
		results.add(result);
	}
	
	/**
	 * @return the grouped test results
	 */
	public List<GroupResult> getResults() {
		return results;
	}
}
