package com.ldodds.sparql.check;

import java.util.Iterator;
import java.util.List;

import com.ldodds.sparql.check.SuccessfulTestResult.Result;

public class ReportFormatter {

	private static final String LINE = "=====================================================================\n";
	
	/**
	 * Produces a simple text based report for dumping to console or file
	 * 
	 * @param report the test result
	 * @return a string containing the formatted report
	 */
	public static String asText(Report report) {
		StringBuffer buffer = new StringBuffer();
		buffer.append( LINE );
		buffer.append( "TEST REPORT\n" );
		buffer.append( "Test Case: " + report.getId() + "\n");
		if (report.getTitle() != null) buffer.append( "Title: " + report.getTitle() + "\n" );		
		if (report.getDescription() != null) buffer.append( "Description: " + report.getDescription() + "\n" );
		
		buffer.append( LINE );
		Iterator<GroupResult> i = report.getResults().iterator();		
		while ( i.hasNext() ) {
			
			GroupResult groupResult = i.next();
			buffer.append( "GROUP\n" );
			if (groupResult.getTitle() != null) buffer.append( "Title: " + groupResult.getTitle() + "\n" );			
			if (groupResult.getDescription() != null) buffer.append( "Description: " + groupResult.getDescription() + "\n" );
			buffer.append( "\n");
			
			Iterator<AbstractTestResult> t = groupResult.getResults().iterator();			
			while ( t.hasNext() ) {
				AbstractTestResult testResult = t.next();
				buffer.append("TEST\n");
				
				if (testResult.getTitle() != null) buffer.append("Title: " + testResult.getTitle() + "\n" );
				if (testResult.getDescription() != null) buffer.append("Description: " + testResult.getDescription() + "\n\n" );
				
				if ( testResult.isSuccessfullyRun() ) {
					SuccessfulTestResult success = (SuccessfulTestResult)testResult;
					if ( success.getResults().isEmpty() ) {
					    buffer.append("Test PASSED\n");	
					} else {
						List<Result> results = success.getErrors();
						for (Result r : results) {
							buffer.append( "ERROR <" + r.getImplicated() + "> " + r.getMessage() + "\n" );
						}
						results = success.getWarnings();
						for (Result r : results) {
							buffer.append( "WARN <" + r.getImplicated() + "> " + r.getMessage() + "\n" );
						}					
					}
				} else {
					buffer.append("Test FAILED TO EXECUTE\n");
					buffer.append( ((FailedTestResult)testResult).getFailureMessage() + "\n" );
				}
				buffer.append(LINE);
			}
			buffer.append( "\n\n" );			
		}
		return buffer.toString();
	}
}
