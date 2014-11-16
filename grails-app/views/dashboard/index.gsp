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
				<h2>Welcome on Easy IoT Connect</h2>
				<br />
			</g:if>
			<g:else>
			   <div class="main-content">
					<g:each in="${devices}" var="device">
						<div class='row'>
							<div class='span12'>
								<section data-id="${device.id}" class="device-section">
								   <div class="device-name">
								       <p>${device.infos.name}</p>
								   </div>
								   <g:link action="manage" params="[id: device.id]">
								       <asset:image src="device-raspb.png" class="device"/>
								   </g:link>
								   
								   <g:link action="configure" params="[id: device.id]">
									    <asset:image src="jack.png" class="jack"/>
									 </g:link>
									 
									<g:link action="remote" params="[id: device.id]">
                              <asset:image src="world.png" class="world"/>
                           </g:link>
								</section>
							</div>
						</div>
                  <hr>
					</g:each>
				</div>
			</g:else>
         
         <div id="main-actions">
				<p class="text-right">
					<g:if test="${devices == null || devices.size == 0}">To start you have to do a new Device! -> </g:if>
					<a href="${createLink(action: 'createDevice')}"
						class="btn btn-lg btn-primary main-action">New Device</a>
				</p>
				<p class="text-right">
					<g:if test="${toConfigure == true}">And now configure your Device! -> </g:if>
					<a href="${createLink(action: 'configureDevice')}"
						class="btn btn-lg btn-primary main-action">Configure Device</a>
				</p>
			</div>
		</div>
	</div>
</body>
</html>
