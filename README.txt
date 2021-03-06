A reworking of the Schemarama 2 code originally developed by Alistair Miles.

Original version is available from:

http://isegserv.itd.rl.ac.uk/schemarama/

This version provides a simple command-line interface for running tests against a dataset.

http://github.com/ldodds/sparql-check

BUILDING

After checking out the code:

ant jar

Then:

java -jar sparql-check.jar

Parameters:

* -test -- indicate file containing Schemarama test case description
* -id -- the URI of the test case resource to be found in above data
* -data -- a file to parse and test, OR
* -location -- location of Jena TDB indexes containing data to test against, OR
* -remote -- URI of sparql endpoint to test against

E.g (should be all on one line):

java -jar sparql-check.jar
  -test etc/testCase.ttl
  -id http://example.org/testcase
  -data etc/data.ttl
  
Will dump a rough text report to the command-line.
  
LIMITATIONS

* Doesn't currently implement the :infer predicate
* Doesn't currently support Info messages