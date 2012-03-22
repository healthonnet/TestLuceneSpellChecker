<div style="text-align: center; padding: 10px;">
	<span>
		<g:message code="${prompt}"/>
	</span>
</div>
<div>
	<g:uploadForm name="myUpload" controller="term" action="handleFile"
			align="center" padding="20px">
		<input type="file" name="payload"/>
		<input type="submit"/>
		
	</g:uploadForm>
</div>