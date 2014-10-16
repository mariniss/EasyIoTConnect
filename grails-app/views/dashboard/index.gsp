<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="dashboard" />
		<title>Dashboard</title>
	</head>
	
	<body>
		<div class="container">
	        <h2>Your Dashboard</h2>
	        <br />
	        <g:if test="${jaks == null || jaks.size == 0}">
	        	<p>To start is necessary... <a href="${createLink(controller: 'dashboard', action: 'createJack')}" class="btn btn-lg btn-primary">Create your Jack</a></p>
			</g:if>
			<g:else>
				<g:each in="${jaks}" var="jack">
				 <p>${jack}</p><br/>
				</g:each>
			</g:else>
		</div>
	</body>
</html>
