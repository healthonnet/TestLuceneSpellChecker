package org.healthonnet.testlucenespellcheck

import groovy.sql.Sql
import org.apache.commons.logging.LogFactory
import org.healthonnet.testlucenespellcheck.Term

class TermFrequencyFileLoaderService {

	private static final LOG = LogFactory.getLog(this)
	private static final BATCH_SIZE = 100;
	
	def sessionFactory
	def dataSource
	
    def analyzeFile(File file) {
		LOG.info("found file: " + file.getCanonicalPath())
		def inputStream = new BufferedInputStream(new FileInputStream(file));
		
		def statement = sessionFactory.getCurrentSession().connection().createStatement();
		def filename = file.getAbsolutePath();
		
		def numInserted = Term.count();
		def timeElapsed = System.currentTimeMillis()
		statement.execute("load data local infile '$filename' " + 
			"into table `term` fields terminated by '\t' lines terminated by '\n'  (value, frequency)")
		
		numInserted = Term.count() - numInserted;
		timeElapsed = System.currentTimeMillis() - timeElapsed;
		LOG.info("number of terms inserted: $numInserted in $timeElapsed ms")
		
		return new LoadTermFrequencyFileLoadResult(numInserted : numInserted, timeElapsed: timeElapsed);
	}
}
