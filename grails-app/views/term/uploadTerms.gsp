<g:applyLayout name="main">
<html>
	<head>
		</head>
	<body>
	<g:if test="${showResults}">
		<div style="text-align: center; padding: 10px;">
			<span>
				<g:message code="tsvFileLoadResult" args="${ [numInserted, timeElapsed] }"/>
			</span>
		</div>
	</g:if>
	<g:render template="blocks/uploadFile" model="[prompt:'uploadTsvFile',action:'uploadTerms']"></g:render>
	</body>
</html>
</g:applyLayout>