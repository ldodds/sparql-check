package com.ldodds.sparql.check;

import com.hp.hpl.jena.rdf.model.Model;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.util.FileManager;

public class Main {

	public static void main(String[] args) {
		
		Checker checker = new Checker();
		
        if (args.length < 3) {
            printHelp();  
            return;
        }
        Model testCases = null;
        Model toTest = null;
        String location = null;
        String remote = null;
        String id = null;
        try {
	        for (int i=0; i<args.length; i++)
	        {
	            String arg = args[i];
	            if (arg.equals("-test"))
	            {
	        		testCases = ModelFactory.createDefaultModel();		
	        		FileManager.get().readModel(testCases, args[++i]);
	            }
	            else if ( arg.equals("-id") )
	            {	            	
	                id = args[++i];             
	            }
	            else if (arg.equals("-data"))
	            {
	        		toTest = ModelFactory.createDefaultModel();
	        		FileManager.get().readModel(toTest, args[++i]);
	            }
	            else if (arg.equals("-location"))
	            {
	              location = args[++i];
	            }
	            else if (arg.equals("-remote"))
	            {
	              remote = args[++i];
	            }	            
	            else
	            {
	                System.err.println("Unknown argument '" + arg + "' ignored");
	            }
	        }
        } catch (Exception e) {
        	e.printStackTrace();
        }
					
        if (testCases == null || id == null) {
        	System.out.println("Must specify both a test case and test case uri");
        	System.exit(0);
        }
        checker.setTestCases(testCases);
        
        ExecutionFactory factory = null;
        
        if (remote != null) {
        	factory = new ServiceExecutionFactory(remote);
        } else if (location != null) {
        	factory = new TDBExecutionFactory(location);
        } else if (toTest != null) {
        	factory = new ModelExecutionFactory(toTest);
        } else {
        	System.out.println("Must specify source of test data");
        	System.exit(0);        	
        }
        	
		Report report = checker.run(id, factory);
		System.out.println( ReportFormatter.asText( report) );
	}
	
	public static void printHelp() {
	      System.out.println("Expected parameters");
	      System.out.println("  -test <filename-or-uri>");
	      System.out.println("  -id <testcase-uri>");
	      System.out.println("  -data <filename-or-uri> (optional)");
	      System.out.println("  -location <dir> (optional)");
	      System.out.println("  -remote <uri> (optional)");	      
	      System.exit(0);
		
	}
}
