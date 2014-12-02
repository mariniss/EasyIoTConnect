<!DOCTYPE html>
<html>
<head>
<meta name="layout" content="dashboard" />
<title>Manage Devices</title>
</head>

<body>
	<div class="container">
		<div class="row-fluid">
			<g:if test="${devices.size() != 0}">
				<div class="panel-group" id="accordion">
					<g:each in="${devices}" var="device">
						<div class="panel panel-default">
							<div class="panel-heading">
								<h4 class="panel-title">
									<a data-toggle="collapse" data-parent="#accordion"
										href="#section${device.id}"> ${device.infos.name}
									</a>
								</h4>
							</div>
							<div id="section${device.id}" class="panel-collapse collapse in">
								<div class="panel-body">
									<g:form action="updateDeviceInfo">
										<fieldset>
											<legend>Informations device:</legend>
											<input type="hidden" name="id" value="${device.id}">
											<g:select name="type" from="${['RASPBERRY']}"
												value="${device.type}" readonly="readonly" />
											<br> <br> GPIO 0:
											<g:textField name="gpio0Name"
												value="${device.infos.gpio0Name?:''}" />
											Visible
											<g:checkBox name="gpio0Visible"
												value="${device.infos.gpio0Visible?:false}" />
											<br /> GPIO 1:
											<g:textField name="gpio1Name"
												value="${device.infos.gpio1Name?:''}" />
											Visible
											<g:checkBox name="gpio1Visible"
												value="${device.infos.gpio1Visible?:false}" />
											<br /> GPIO 2:
											<g:textField name="gpio2Name"
												value="${device.infos.gpio2Name?:''}" />
											Visible
											<g:checkBox name="gpio2Visible"
												value="${device.infos.gpio2Visible?:false}" />
											<br /> GPIO 3:
											<g:textField name="gpio3Name"
												value="${device.infos.gpio3Name?:''}" />
											Visible
											<g:checkBox name="gpio3Visible"
												value="${device.infos.gpio3Visible?:false}" />
											<br /> GPIO 4:
											<g:textField name="gpio4Name"
												value="${device.infos.gpio4Name?:''}" />
											Visible
											<g:checkBox name="gpio4Visible"
												value="${device.infos.gpio4Visible?:false}" />
											<br /> GPIO 5:
											<g:textField name="gpio5Name"
												value="${device.infos.gpio5Name?:''}" />
											Visible
											<g:checkBox name="gpio5Visible"
												value="${device.infos.gpio5Visible?:false}" />
											<br /> GPIO 6:
											<g:textField name="gpio6Name"
												value="${device.infos.gpio6Name?:''}" />
											Visible
											<g:checkBox name="gpio6Visible"
												value="${device.infos.gpio6Visible?:false}" />
											<br /> GPIO 7:
											<g:textField name="gpio7Name"
												value="${device.infos.gpio7Name?:''}" />
											Visible
											<g:checkBox name="gpio7Visible"
												value="${device.infos.gpio7Visible?:false}" />
											<br /> GPIO 8:
											<g:textField name="gpio8Name"
												value="${device.infos.gpio8Name?:''}" />
											Visible
											<g:checkBox name="gpio8Visible"
												value="${device.infos.gpio8Visible?:false}" />
											<br /> GPIO 9:
											<g:textField name="gpio9Name"
												value="${device.infos.gpio9Name?:''}" />
											Visible
											<g:checkBox name="gpio9Visible"
												value="${device.infos.gpio9Visible?:false}" />
											<br /> GPIO 10:
											<g:textField name="gpio10Name"
												value="${device.infos.gpio10Name?:''}" />
											Visible
											<g:checkBox name="gpio10Visible"
												value="${device.infos.gpio10Visible?:false}" />
											<br /> GPIO 11:
											<g:textField name="gpio11Name"
												value="${device.infos.gpio11Name?:''}" />
											Visible
											<g:checkBox name="gpio11Visible"
												value="${device.infos.gpio11Visible?:false}" />
											<br /> GPIO 12:
											<g:textField name="gpio12Name"
												value="${device.infos.gpio12Name?:''}" />
											Visible
											<g:checkBox name="gpio12Visible"
												value="${device.infos.gpio12Visible?:false}" />
											<br /> GPIO 13:
											<g:textField name="gpio13Name"
												value="${device.infos.gpio13Name?:''}" />
											Visible
											<g:checkBox name="gpio13Visible"
												value="${device.infos.gpio13Visible?:false}" />
											<br /> GPIO 14:
											<g:textField name="gpio14Name"
												value="${device.infos.gpio14Name?:''}" />
											Visible
											<g:checkBox name="gpio14Visible"
												value="${device.infos.gpio14Visible?:false}" />
											<br /> GPIO 15:
											<g:textField name="gpio15Name"
												value="${device.infos.gpio15Name?:''}" />
											Visible
											<g:checkBox name="gpio15Visible"
												value="${device.infos.gpio15Visible?:false}" />
											<br /> GPIO 16:
											<g:textField name="gpio16Name"
												value="${device.infos.gpio16Name?:''}" />
											Visible
											<g:checkBox name="gpio16Visible"
												value="${device.infos.gpio16Visible?:false}" />
											<br /> GPIO 17:
											<g:textField name="gpio17Name"
												value="${device.infos.gpio17Name?:''}" />
											Visible
											<g:checkBox name="gpio17Visible"
												value="${device.infos.gpio17Visible?:false}" />
											<br />
										</fieldset>
										<input type="submit" value="Update">
									</g:form>
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
	</div>
</body>
</html>
