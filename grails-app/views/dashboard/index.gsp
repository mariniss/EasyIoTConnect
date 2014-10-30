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
			<p>And then link to a device...</p>
			
			<!--  DEBUG -->
			<p>Add the link to the device and a link to switch the pin status. We need to think abount the connection
			of jeck to a specific device (app domamin), actually only raspberry</p>
			<br /><br />
			
			<a href="${createLink(controller: 'device', action: 'sendCommand', params: [pin: 1, status: 1])}" 
			   class="btn btn-lg btn-primary"> Send On </a>
			
			<a href="${createLink(controller: 'device', action: 'sendCommand', params: [pin: 1, status: 0])}" 
			   class="btn btn-lg btn-primary"> Send Off </a>
		</div>
	</body>
</html>
