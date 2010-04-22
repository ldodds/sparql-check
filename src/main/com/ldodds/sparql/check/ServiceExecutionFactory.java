package com.ldodds.sparql.check;

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;

/**
 * Generates a QueryExecutionFactory for a remote SPARQL service
 * 
 * @author ldodds
 */
public class ServiceExecutionFactory implements ExecutionFactory {

	private String service;
	
	public ServiceExecutionFactory(String service) {
		this.service = service;
	}
	
	public QueryExecution getQueryExecution(Query query) {
		return QueryExecutionFactory.sparqlService(service, query);
	}

}
