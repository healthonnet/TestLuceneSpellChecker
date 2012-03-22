package org.healthonnet.testlucenespellcheck

/**
 * Base class for a term and its frequencies.  It is assumed that this data comes from some analysis of a corpus.
 * @author nolan
 *
 */
class Term {

	String value
	int frequency
	
    static constraints = {
		value blank : false
		frequency min : 0
    }
}
