<html>
<head>
<title>Test POST method</title>
</head>
<body>
 	<form action="/favourite_fruit" method="POST">
 		<p>Whats your favourite fruit?</p>
 		<#list fruits as fruit>
 			<p>
 				<input type="radio" name="fruit" value="${fruit}">${fruit}</input>
 			</p>
 		</#list>
 		<input type="submit" value="submit"/>
 	</form>
</body>
</html>