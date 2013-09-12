<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="span-10 append-2 last">
	<form name="f" action="<c:url value="/console" />" method="post">
		<fieldset>
			<legend>Stocks DSL</legend>
			<div>
				<textarea id="scriptContent" name="scriptContent"></textarea>
			</div>
			<div>
				<button id="submit" type="submit">Submit</button>
			</div>
		</fieldset>
	</form>
	<h2>Output</h2>
	<div id="output">${output}</div>
</div>