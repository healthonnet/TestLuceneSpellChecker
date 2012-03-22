package org.healthonnet.testlucenespellcheck

import testlucenespellcheck.TermFrequencyFileAnalyzerService;

class TermController {

	static final TEMP_FILENAME = "uploadedTsv"
	static final TEMP_FILENAME_SUFFIX = ".txt"
	
	TermFrequencyFileAnalyzerService termFrequencyFileAnalyzerService
	
    def index() { 
		render "hello world"
	}
	
	/**
	 * Asks the user to upload a TSV file with term + frequency information
	 * @return
	 */
	def uploadTerms() {
		
	}
	
	/**
	 * Processes the uploaded file, adding it to the database.
	 * @return
	 */
	def handleFile() {
		
		//handle uploaded file
		def downloadedFile = request.getFile('payload')
		
		def tempFile = File.createTempFile(TEMP_FILENAME, TEMP_FILENAME_SUFFIX)
		tempFile.deleteOnExit();
		downloadedFile.transferTo(tempFile);
		
		termFrequencyFileAnalyzerService.analyzeFile(tempFile);
		
	}
}
