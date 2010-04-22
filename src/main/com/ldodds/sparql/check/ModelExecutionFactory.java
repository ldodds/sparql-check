package com.ldodds.sparql.check;

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.rdf.model.Model;

/**
 * Generates a QueryExecutionFactory from a Jena Model
 * 
 * @author ldodds
 */
public class ModelExecutionFactory implements ExecutionFactory {

	private Model model;
	
	public ModelExecutionFactory(Model model) {
		this.model = model;
	}
	public QueryExecution getQueryExecution(Query query) {
		return QueryExecutionFactory.create(query, model);
	}
	
}
