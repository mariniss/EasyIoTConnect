<!DOCTYPE html>
<html>
<head>
<meta name="layout" content="dashboard" />
<title>Dashboard</title>
</head>
<body>
	<div class="container">
		<div class="row-fluid">
			<g:if test="${devices.size() == 0}">
				<h2 class="text-center">Welcome on your personal dashboard</h2>
				<br /><br />
			</g:if>
			<g:else>
			   <div class="main-content">
					<g:each in="${devices}" var="device">
					   <div class="row text-center">
		               <div class="col-md-12">
		                  <section data-id="${device.id}" class="device-section">
			                  <div class="panel panel-success">
			                     <div class="panel-heading">
			                        <h3 class="panel-title">${device.infos.name}</h3>
			                     </div>
			                     <div class="panel-body text-left">
			                        <g:link action="manage" params="[id: device.id]">
		                               <asset:image src="device-raspb.png" class="device"/>
		                           </g:link>
		                           
		                           <g:link action="configure" params="[id: device.id]">
		                               <asset:image src="jack.png" class="jack"/>
		                            </g:link>
		                            
		                           <g:link action="remote" params="[id: device.id]">
		                              <asset:image src="world.png" class="world"/>
		                           </g:link>
			                     </div>
			                  </div>
		                  </section>
		               </div>
	               </div>
						<br />
					</g:each>
				</div>
			</g:else>
         
         <div id="main-actions">
				<p class="text-right">
					<g:if test="${devices == null || devices.size == 0}">To start you have to add a new Device! -> </g:if>
					<a href="${createLink(action: 'createDevice')}"
						class="btn btn-lg btn-primary main-action">New Device</a>
				</p>
				<p class="text-right">
					<g:if test="${toConfigure == true}">And now configure your it! -> </g:if>
					<a href="${createLink(action: 'configureDevice')}"
						class="btn btn-lg btn-primary main-action">Configure Device</a>
				</p>
			</div>
		</div>
	</div>
</body>
</html>
