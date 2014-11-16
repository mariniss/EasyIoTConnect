<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="dashboard" />
		<title>Create Device</title>
	</head>
	
	<body>
		<div class="container">
         <h2> Create a new Device </h2>
         <hr>		   
			
			<g:form action="saveDevice">
			   Name: <input type="text" name="name"><br>
			   Type: <input type="text" name="type"><br>
			  <input type="submit" value="Create" />
			</g:form>		
		</div>
	</body>
</html>
