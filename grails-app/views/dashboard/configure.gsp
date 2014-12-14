<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="dashboard" />
		<title>Configure Devices</title>
	</head>
	
	<body>
		<div class="container">
		  <g:if test="${devices.size() != 0}">
            <div class="panel-group" id="accordion">
               <g:each in="${devices}" var="device">
                  <div class="panel panel-default">
                     <div class="panel-heading">
                        <h4 class="panel-title">
                           <a data-toggle="collapse" data-parent="#accordion"
                              href="#section${device.id}">How configure your Device ${device.infos.name}
                           </a>
                        </h4>
                     </div>
                     <div id="section${device.id}" class="panel-collapse collapse in">
                        <div class="panel-body">
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
                                 <p>Insert 
                                    <a href="${createLink(action: 'downloadConfiguration', 
                                                          params: [id: device.id])}">this file</a>  
                                    on client folder</p>
						            </li>
						            
						             <li>
						               Start the client...
						            </li>
						         </ul>
						         
						         <a href="${createLink(action: 'configured', params: [id: device.id])}"
						            class="btn btn-success btn-lg" role="button">Done</a>                            
                        </div>
                     </div>
                  </div>
               </g:each>
            </div>
         </g:if>
         <g:else>
            <h2>You have no devices to configure</h2>
         </g:else>
		</div>
	</body>
</html>
