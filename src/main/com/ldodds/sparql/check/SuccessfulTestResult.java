package com.ldodds.sparql.check;

import java.util.ArrayList;
import java.util.List;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ResIterator;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.vocabulary.RDF;

/**
 * Result of applying a SPARQL query to a given dataset.
 * 
 * @author ldodds
 */
public class SuccessfulTestResult extends AbstractTestResult {

	private List<Result> errors;
	private List<Result> warnings;

	public class Result {
		private String message;
		private String implicated;
		
		public Result(String message, String implicated) {
			this.message = message;
			this.implicated = implicated;
		}
		public String getMessage() {
			return message;
		}
		public String getImplicated() {
			return implicated;
		}
	}
	
	public boolean isSuccessfullyRun() { return true; }
	
	private Model results;
	
	public SuccessfulTestResult(String title, String description, Model results) {
		super(title, description);
		this.results = results;
	}
	
	/**
	 * Retrieve the raw results of the SPARQL query
	 * @return
	 */
	public Model getResults() {
		return results;
	}

	public List<Result> getErrors() {
		if (errors != null) {
			return errors;
		}
		errors = new ArrayList<Result>();
		ResIterator iter = results.listResourcesWithProperty(RDF.type, Schemarama.Error);
		populateList(errors, iter);
		return errors;
	}

	public List<Result> getWarnings() {
		if (warnings != null) {
			return warnings;
		}
		warnings = new ArrayList<Result>();
		ResIterator iter = results.listResourcesWithProperty(RDF.type, Schemarama.Warning);
		populateList(warnings, iter);
		return warnings;
	}

	
	private void populateList(List<Result> results, ResIterator iter) {
		while ( iter.hasNext() ) {
			Resource r = iter.next();
			String msg = null;
			if ( r.hasProperty(Schemarama.message) ) {
				msg = r.getProperty(Schemarama.message).getString();
			}
			String implicated = null;
			if ( r.hasProperty(Schemarama.implicated) ) {
				Resource i = r.getProperty(Schemarama.implicated).getResource();
				if ( i.isAnon() ) {
					implicated = i.getId().toString();
				} else {
					implicated = i.getURI();
				}
			}
			results.add( new Result(msg, implicated) );
		}
	}
}
