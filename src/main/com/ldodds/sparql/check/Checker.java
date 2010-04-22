package com.ldodds.sparql.check;

import java.io.InputStream;
import java.util.Iterator;
import java.util.List;

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryFactory;
import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.RDFList;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.util.FileManager;
import com.hp.hpl.jena.vocabulary.DC;

/**
 * Applies a test case described in terms of SPARQL assertions to a data source defined by an 
 * ExecutionFactory.
 *  
 * @author ldodds
 */
public class Checker {

	private Model testCases;
	
	public Checker() {
		this.testCases = null;
	}
	
	public Checker(Model testCases) {
	  	this.testCases = testCases;
	}	
	
	public void setTestCases(Model testCases) {
		this.testCases = testCases;
	}
	
	public Report run(String testCase, ExecutionFactory factory) {
		
		Resource testCaseResource = testCases.getResource(testCase);
		Report report = new Report( getLiteral(testCaseResource, DC.title), 
				getLiteral(testCaseResource, DC.description ), testCase );		
		if ( testCaseResource.hasProperty(Schemarama.groups) ) {
			RDFList groupList = (RDFList)testCaseResource.getProperty(Schemarama.groups).getResource().as(RDFList.class);
			List groups = groupList.asJavaList();
			Iterator i = groups.iterator();
			while( i.hasNext() ) {
				Resource group = (Resource)i.next();
				runGroup(factory, report, group);
			}
		} else {
			System.err.println("Test case has no groups");
		}
		return report;
		
	}

	/**
	 * Run a test group
	 * @param factory
	 * @param report
	 * @param i
	 */
	private void runGroup(ExecutionFactory factory, Report report, Resource group) {		
		GroupResult groupResult = new GroupResult( getLiteral(group, DC.title), getLiteral(group, DC.description));
		report.addGroupResult(groupResult);
		
		if ( group.hasProperty(Schemarama.tests) ) {
			RDFList testList = (RDFList)group.getProperty(Schemarama.tests).getResource().as(RDFList.class);
			List tests = testList.asJavaList();
			Iterator t = tests.iterator();
			while( t.hasNext() ) {				
				Resource test = (Resource)t.next();				
				runTest(factory, groupResult, test);
			}
		} else {
			System.err.println("Group does not have any tests");
		}
	}

	/**
	 * Run a test
	 * @param factory
	 * @param groupResult
	 * @param test
	 */
	private void runTest(ExecutionFactory factory, GroupResult groupResult,
			Resource test) {
		AbstractTestResult testResult = null;
		String title = getLiteral(test, DC.title);
		String description = getLiteral(test, DC.description);
		try {
			InputStream in = FileManager.get().open( test.getURI() );
			if (in == null) {
				throw new RuntimeException("Unable to load test " + test.getURI());
			}
			String queryText = FileManager.get().readWholeFileAsUTF8( in );
			Query query = QueryFactory.create(queryText);
			QueryExecution qe = factory.getQueryExecution(query);
			Model reportModel = qe.execConstruct();
			testResult = new SuccessfulTestResult(title, description, reportModel);
		} catch (Exception e) {
			testResult = new FailedTestResult(title, description, e.getMessage() );
		}		
		groupResult.addTestResult(testResult);
	}
	
	/**
	 * Run test case against the provided Model
	 * @param testCase
	 * @param model
	 * @return
	 */
	public Report run(String testCase, Model model) {
		return run(testCase, new ModelExecutionFactory(model) );
	}

	/**
	 * Run test case against a remote SPARQL endpoint
	 * @param testCase
	 * @param service
	 * @return
	 */
	public Report run(String testCase, String service) {
		return run(testCase, new ServiceExecutionFactory(service) );
	}	
	
	private String getLiteral(Resource resource, Property p) {		
		if (resource.hasProperty(p)) {
			return resource.getProperty(p).getString();
		}
		return "";
	}
}
