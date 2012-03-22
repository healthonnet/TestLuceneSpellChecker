package testlucenespellcheck

import org.apache.commons.logging.LogFactory
import org.healthonnet.testlucenespellcheck.Term;

class TermFrequencyFileAnalyzerService {

	private static final log = LogFactory.getLog(this)
	
    def analyzeFile(File file) {
		log.info("found file: " + file.getCanonicalPath())
		file.readLines().each { line -> 
			log.info("found line: " + line)
			def tokens = line.split("\t")
			if (tokens.size() == 2) {
				try {
					def value = tokens[0];
					def frequency = Integer.parseInt(tokens[1]);
					Term.findOrCreateWhere(value : value , frequency : frequency).save();
					log.info("found or created term: $value $frequency");
				} catch (NumberFormatException ignore) { }
			}
		};
		
	}
}
