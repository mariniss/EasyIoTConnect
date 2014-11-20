<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="dashboard" />
		<title>Create Device</title>
	</head>
	
	<body>
		<div class="container">
         <h2> How configure your Device ${device?.infos.name}</h2>
         <hr>		   
			
			<ul>
			   <li>
			      Give the Internet access to your Device ... 
			      <span>See the <a href="http://www.raspberrypi.org/documentation/configuration/wireless/README.md"> official guide </a></span>
			   </li>
			   
			   <li>
               Download the client application from <a href="${pimqUrl}">here</a>...
               <span>Use wget</span>
            </li>
            
            <li>
               Extract the client content and copy on Raspberry
               <span>Example...</span>
            </li>
            
            <li>
               Copy and paste this configuration: <br />
               <span> ${device.jackConsumer.queueName} </span><br />
               <span>${user.username} - ${user.password} </span>
            </li>
            
             <li>
               Start the client...
            </li>
			</ul>		
		</div>
	</body>
</html>
