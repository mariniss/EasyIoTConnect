<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="dashboard" />
		<title>Create Device</title>
	</head>
	
	<body>
		<div class="container">
      <div class="row-fluid">
         <g:if test="${device != null}">
            <h3>${device.infos.name} (${device.type})</h3>
	         <br />
	         
	         <g:if test="${device.infos.gpio0Visible}">
		         <p>${device.infos.gpio0Name}:
			         <a href="${createLink(action: 'sendCommand', params: [id: device?.id, pin: 1, status: 1])}" 
		               class="btn btn-success"> Send On </a>
		            <a href="${createLink(action: 'sendCommand', params: [id: device?.id, pin: 1, status: 0])}" 
		               class="btn btn btn-danger"> Send Off </a> 
		          </p><br />
            </g:if>
            
            <g:if test="${device.infos.gpio1Visible}">
               <p>${device.infos.gpio1Name}:
                  <a href="${createLink(action: 'sendCommand', params: [id: device?.id, pin: 2, status: 1])}" 
                     class="btn btn-success"> Send On </a>
                  <a href="${createLink(action: 'sendCommand', params: [id: device?.id, pin: 2, status: 0])}" 
                     class="btn btn btn-danger"> Send Off </a> 
                </p><br />
            </g:if>
            
            <g:if test="${device.infos.gpio2Visible}">
               <p>${device.infos.gpio2Name}:
                  <a href="${createLink(action: 'sendCommand', params: [id: device?.id, pin: 3, status: 1])}" 
                     class="btn btn-success"> Send On </a>
                  <a href="${createLink(action: 'sendCommand', params: [id: device?.id, pin: 3, status: 0])}" 
                     class="btn btn btn-danger"> Send Off </a> 
                </p><br />
            </g:if>
            
            <g:if test="${device.infos.gpio3Visible}">
               <p>${device.infos.gpio3Name}:
                  <a href="${createLink(action: 'sendCommand', params: [id: device?.id, pin: 4, status: 1])}" 
                     class="btn btn-success"> Send On </a>
                  <a href="${createLink(action: 'sendCommand', params: [id: device?.id, pin: 4, status: 0])}" 
                     class="btn btn btn-danger"> Send Off </a> 
                </p><br />
            </g:if>
            
            <g:if test="${device.infos.gpio4Visible}">
               <p>${device.infos.gpio4Name}:
                  <a href="${createLink(action: 'sendCommand', params: [id: device?.id, pin: 5, status: 1])}" 
                     class="btn btn-success"> Send On </a>
                  <a href="${createLink(action: 'sendCommand', params: [id: device?.id, pin: 5, status: 0])}" 
                     class="btn btn btn-danger"> Send Off </a> 
                </p><br />
            </g:if>
            
            <g:if test="${device.infos.gpio5Visible}">
               <p>${device.infos.gpio5Name}:
                  <a href="${createLink(action: 'sendCommand', params: [id: device?.id, pin: 6, status: 1])}" 
                     class="btn btn-success"> Send On </a>
                  <a href="${createLink(action: 'sendCommand', params: [id: device?.id, pin: 6, status: 0])}" 
                     class="btn btn btn-danger"> Send Off </a> 
                </p><br />
            </g:if>
            
            <g:if test="${device.infos.gpio6Visible}">
               <p>${device.infos.gpio6Name}:
                  <a href="${createLink(action: 'sendCommand', params: [id: device?.id, pin: 7, status: 1])}" 
                     class="btn btn-success"> Send On </a>
                  <a href="${createLink(action: 'sendCommand', params: [id: device?.id, pin: 7, status: 0])}" 
                     class="btn btn btn-danger"> Send Off </a> 
                </p><br />
            </g:if>
            
            <g:if test="${device.infos.gpio7Visible}">
               <p>${device.infos.gpio7Name}:
                  <a href="${createLink(action: 'sendCommand', params: [id: device?.id, pin: 8, status: 1])}" 
                     class="btn btn-success"> Send On </a>
                  <a href="${createLink(action: 'sendCommand', params: [id: device?.id, pin: 8, status: 0])}" 
                     class="btn btn btn-danger"> Send Off </a> 
                </p><br />
            </g:if>
            
            <g:if test="${device.infos.gpio8Visible}">
               <p>${device.infos.gpio8Name}:
                  <a href="${createLink(action: 'sendCommand', params: [id: device?.id, pin: 9, status: 1])}" 
                     class="btn btn-success"> Send On </a>
                  <a href="${createLink(action: 'sendCommand', params: [id: device?.id, pin: 9, status: 0])}" 
                     class="btn btn btn-danger"> Send Off </a> 
                </p><br />
            </g:if>
            
            <g:if test="${device.infos.gpio9Visible}">
               <p>${device.infos.gpio9Name}:
                  <a href="${createLink(action: 'sendCommand', params: [id: device?.id, pin: 10, status: 1])}" 
                     class="btn btn-success"> Send On </a>
                  <a href="${createLink(action: 'sendCommand', params: [id: device?.id, pin: 10, status: 0])}" 
                     class="btn btn btn-danger"> Send Off </a> 
                </p><br />
            </g:if>
            
            <g:if test="${device.infos.gpio10Visible}">
               <p>${device.infos.gpio10Name}:
                  <a href="${createLink(action: 'sendCommand', params: [id: device?.id, pin: 11, status: 1])}" 
                     class="btn btn-success"> Send On </a>
                  <a href="${createLink(action: 'sendCommand', params: [id: device?.id, pin: 11, status: 0])}" 
                     class="btn btn btn-danger"> Send Off </a> 
                </p><br />
            </g:if>
            
            <g:if test="${device.infos.gpio11Visible}">
               <p>${device.infos.gpio11Name}:
                  <a href="${createLink(action: 'sendCommand', params: [id: device?.id, pin: 12, status: 1])}" 
                     class="btn btn-success"> Send On </a>
                  <a href="${createLink(action: 'sendCommand', params: [id: device?.id, pin: 12, status: 0])}" 
                     class="btn btn btn-danger"> Send Off </a> 
                </p><br />
            </g:if>
            
            <g:if test="${device.infos.gpio12Visible}">
               <p>${device.infos.gpio12Name}:
                  <a href="${createLink(action: 'sendCommand', params: [id: device?.id, pin: 13, status: 1])}" 
                     class="btn btn-success"> Send On </a>
                  <a href="${createLink(action: 'sendCommand', params: [id: device?.id, pin: 13, status: 0])}" 
                     class="btn btn btn-danger"> Send Off </a> 
                </p><br />
            </g:if>
            
            <g:if test="${device.infos.gpio13Visible}">
               <p>${device.infos.gpio13Name}:
                  <a href="${createLink(action: 'sendCommand', params: [id: device?.id, pin: 14, status: 1])}" 
                     class="btn btn-success"> Send On </a>
                  <a href="${createLink(action: 'sendCommand', params: [id: device?.id, pin: 14, status: 0])}" 
                     class="btn btn btn-danger"> Send Off </a> 
                </p><br />
            </g:if>
            
            <g:if test="${device.infos.gpio14Visible}">
               <p>${device.infos.gpio14Name}:
                  <a href="${createLink(action: 'sendCommand', params: [id: device?.id, pin: 15, status: 1])}" 
                     class="btn btn-success"> Send On </a>
                  <a href="${createLink(action: 'sendCommand', params: [id: device?.id, pin: 15, status: 0])}" 
                     class="btn btn btn-danger"> Send Off </a> 
                </p><br />
            </g:if>
            
            <g:if test="${device.infos.gpio15Visible}">
               <p>${device.infos.gpio15Name}:
                  <a href="${createLink(action: 'sendCommand', params: [id: device?.id, pin: 16, status: 1])}" 
                     class="btn btn-success"> Send On </a>
                  <a href="${createLink(action: 'sendCommand', params: [id: device?.id, pin: 16, status: 0])}" 
                     class="btn btn btn-danger"> Send Off </a> 
                </p><br />
            </g:if>
            
            <g:if test="${device.infos.gpio16Visible}">
               <p>${device.infos.gpio16Name}:
                  <a href="${createLink(action: 'sendCommand', params: [id: device?.id, pin: 17, status: 1])}" 
                     class="btn btn-success"> Send On </a>
                  <a href="${createLink(action: 'sendCommand', params: [id: device?.id, pin: 17, status: 0])}" 
                     class="btn btn btn-danger"> Send Off </a> 
                </p><br />
            </g:if>
            
            <g:if test="${device.infos.gpio17Visible}">
               <p>${device.infos.gpio17Name}:
                  <a href="${createLink(action: 'sendCommand', params: [id: device?.id, pin: 18, status: 1])}" 
                     class="btn btn-success"> Send On </a>
                  <a href="${createLink(action: 'sendCommand', params: [id: device?.id, pin: 18, status: 0])}" 
                     class="btn btn btn-danger"> Send Off </a> 
                </p><br />
            </g:if>
 
         </g:if>
         <g:else>
            <g:if test="${devices.size() != 0}">
            </g:if>
            <g:else>
               <h2>You have no devices to configure</h2>
            </g:else>
         </g:else>
      </div>
   </div>
	</body>
</html>
