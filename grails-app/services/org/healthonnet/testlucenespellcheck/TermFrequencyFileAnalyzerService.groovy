package org.healthonnet.testlucenespellcheck

import org.apache.commons.logging.LogFactory
import org.healthonnet.testlucenespellcheck.Term;

class TermFrequencyFileAnalyzerService {

	private static final LOG = LogFactory.getLog(this)
	private static final BATCH_SIZE = 100;
	
	def sessionFactory
	def propertyInstanceMap = org.codehaus.groovy.grails.plugins.DomainClassGrailsPlugin.PROPERTY_INSTANCE_MAP;
	
    def analyzeFile(File file) {
		LOG.info("found file: " + file.getCanonicalPath())
		file.readLines().eachWithIndex {line, i -> 
			def tokens = line.split("\t")
			if (tokens.size() == 2) {
				try {
					def value = tokens[0];
					def frequency = Integer.parseInt(tokens[1]);
					
					Term.findOrCreateWhere(value : value, frequency : frequency).save()
					
				} catch (NumberFormatException ignore) { }
			}
			
			if (i % BATCH_SIZE == (BATCH_SIZE - 1)) {
				LOG.info("processed " + (i + 1) + "th line");
				cleanUpGorm();
			}
		};
		
	}
	
	/**
	 * Optimization for batch processing.
	 * @return
	 */
	def cleanUpGorm() {
		def session = sessionFactory.currentSession;
		session.flush();
		session.clear();
		propertyInstanceMap.get().clear();
	}
}
