package com.ldodds.sparql.check;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;

/**
 * Schema definitions for the Schemarama 2 vocabulary.
 * 
 * @author ldodds
 */
public class Schemarama {

	private static Model model = ModelFactory.createDefaultModel();
	
	public static final String NS = "http://purl.org/net/schemarama#";
	
	public static final String getURI() { return NS; }
	
	public static final Resource NAMESPACE = model.createResource( NS );
	
	public static final Resource Error = model.createResource( NS + "Error" );
	public static final Resource Report = model.createResource( NS + "Report" );
	public static final Resource Info = model.createResource( NS + "Info" );
	public static final Resource Test = model.createResource( NS + "Test" );
	public static final Resource TestCase = model.createResource( NS + "TestCase" );
	public static final Resource TestGroup = model.createResource( NS + "TestGroup" );
	public static final Resource Warning = model.createResource( NS + "Warning" );
	
	public static final Property groups = model.createProperty( NS + "groups" );
	public static final Property implicated = model.createProperty( NS + "implicated" );
	public static final Property infer = model.createProperty( NS + "infer" );
	public static final Property tests = model.createProperty( NS + "tests" );
	public static final Property message = model.createProperty( NS + "message" );
	
	
}
