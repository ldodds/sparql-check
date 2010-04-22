package com.ldodds.sparql.check;

import com.hp.hpl.jena.query.Query;
import com.hp.hpl.jena.query.QueryExecution;

public interface ExecutionFactory {

	QueryExecution getQueryExecution(Query query);
	
}
