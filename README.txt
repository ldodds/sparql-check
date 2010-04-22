A reworking of the Schemarama 2 code originally developed by Alistair Miles.

This version provides a simple command-line interface for running tests against a dataset.

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

LIMITATIONS

* Doesn't currently implement the :infer predicate
* Doesn't currently support Info messages