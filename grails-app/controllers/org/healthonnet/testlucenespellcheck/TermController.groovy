package org.healthonnet.testlucenespellcheck

import org.healthonnet.testlucenespellcheck.TermFrequencyFileLoaderService;

class TermController {

	static final TEMP_FILENAME = "uploadedTsv"
	static final TEMP_FILENAME_SUFFIX = ".txt"
	
	def termFrequencyFileLoaderService
	
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
		
		def loadResult = termFrequencyFileLoaderService.analyzeFile(tempFile);
		return [numInserted : loadResult.getNumInserted(), timeElapsed: loadResult.getTimeElapsed()];
	}
}
