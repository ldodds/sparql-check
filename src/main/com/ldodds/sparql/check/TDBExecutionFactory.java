package com.ldodds.sparql.check;

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.tdb.TDBFactory;

/**
 * Generates a QueryExecutionFactory based on a directory containing TDB indexes
 * 
 * @author ldodds
 */
public class TDBExecutionFactory implements ExecutionFactory {

	private String dir;
	
	public TDBExecutionFactory(String dir) {
		this.dir = dir;
	}
	
	public QueryExecution getQueryExecution(Query query) {
		return QueryExecutionFactory.create(query, TDBFactory.createDataset(dir) );
	}

}
