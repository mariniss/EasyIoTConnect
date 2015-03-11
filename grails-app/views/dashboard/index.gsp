<!DOCTYPE html>
<html>
<head>
    <meta name="layout" content="dashboard"/>
    <title>Dashboard</title>
</head>

<body>

<!-- Header -->
<g:if test="${devices.size() == 0}">
    <header>
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <h2 class="text-center">Welcome on your personal dashboard</h2>
                    <br />
                    <p>This is the section where you can register, configure and control the devices</p>
                    <br />
                </div>
            </div>
        </div>
    </header>

    <section class="not-first">
        <div class="container">
            <div class="row">
                <div class="col-lg-12 text-center">
                    <p>To start you have to add a new device, give a good name and configure it by three simple steps</p>
                    <p>You will found these images for each device, they are the link to performs the three steps</p>
                </div>
            </div>

            <div class="row">
                <div class="col-lg-4 text-center">
                    <asset:image src="sweet-home.png"/>
                </div>
                <div class="col-lg-8 link-description text-left">
                    This is the link for the section to give labels to the device's pins
                </div>
            </div>

            <div class="row">
                <div class="col-lg-4  text-center">
                    <asset:image src="jack-l.png"/>
                </div>
                <div class="col-lg-8 link-description text-left">
                    This is the link for the section that contains the instructions to configure the EIoTClient
                </div>
            </div>

            <div class="row">
                <div class="col-lg-4  text-center">
                    <asset:image src="Internet.png"/>
                </div>
                <div class="col-lg-8 link-description text-left">
                    This is the link for the section to send command to the pins (that have a label)
                </div>
            </div>
        </div>
    </section>
</g:if>
<g:if test="${devices.size() > 0}">
    <g:each in="${devices}" var="device" status="i">
        <section data-id="${device.id}" class="device ${i % 2 == 0 ? 'success' : ''} ${i == 0 ? 'first' : 'not-first'}">
            <div class="container">
                <div class="row">
                    <div class="col-lg-12 text-center">
                        <h3>${device.infos.name}</h3>
                    </div>
                </div>

                <div class="col-lg-4 text-center">
                    <a href="#manageDevice${device.id}" data-toggle="modal">
                        <asset:image src="sweet-home.png"/>
                    </a>
                </div>

                <div class="col-lg-4  text-center">
                    <a href="#configureDevice${device.id}" data-toggle="modal">
                        <asset:image src="jack-l.png"/>
                    </a>
                </div>

                <div class="col-lg-4  text-center">
                    <a href="#remoteDevice${device.id}" id="btnRemoteDevice${device.id}" data-toggle="modal">
                        <asset:image src="Internet.png"/>
                    </a>
                </div>
            </div>
        </section>
    </g:each>
    </div>
</div>
</div>
</g:if>

<!-- *** Modals *** -->

<!-- Create Device -->
<g:if test="${devices.size() < grailsApplication.config.eiotc.device.max}">
    <div class="portfolio-modal modal fade" id="createDevice" tabindex="-1" role="dialog" aria-hidden="true">
        <div class="modal-content">
            <div class="close-modal" data-dismiss="modal">
                <div class="lr">
                    <div class="rl">
                    </div>
                </div>
            </div>

            <div class="container">
                <div class="row">
                    <div class="col-lg-8 col-lg-offset-2">
                        <h3>Create a new Device</h3>

                        <g:form method='POST' name='saveDevice' action='saveDevice'
                                class="form-horizontal" role="form" autocomplete='off'>

                            <div class="row control-group">
                                <div class="form-group col-xs-12 floating-label-form-group controls">
                                    <label>Name</label>
                                    <input type="text" class="form-control" placeholder="An identifier for the raspberry"
                                           id="name" name="name" required
                                           data-validation-required-message="Please enter the device name.">

                                    <p class="help-block text-danger"></p>
                                </div>
                            </div>
                            <br/>

                            <div class="row">
                                <div class="form-group col-xs-12 text-right">
                                    <g:submitButton name="saveDevice" value="Create" class="btn btn-success btn-lg"/>
                                </div>
                            </div>
                        </g:form>
                    </div>
                </div>
            </div>
        </div>
    </div>
</g:if>

<!-- Configure Device -->
<g:if test="${devices.size() > 0}">
    <g:each in="${devices}" var="device" status="i">
        <div class="portfolio-modal modal fade" id="configureDevice${device.id}" tabindex="-1" role="dialog"
             aria-hidden="true">
            <div class="modal-content">
                <div class="close-modal" data-dismiss="modal">
                    <div class="lr">
                        <div class="rl">
                        </div>
                    </div>
                </div>

                <div class="container">
                    <div class="row">
                        <div class="col-lg-8 col-lg-offset-2">
                            <h3>How configure your device ${device.infos.name}</h3>
                            <hr />

                            <dl class="dl-horizontal" style="align-content: center">
                                <dt>Internet access</dt>
                                <dd class="text-left">
                                    Give the Internet access to your Device, you can connect by a cable to the router/modem or
                                    connect by wifi,
                                    <span>
                                        see the <a href="http://www.raspberrypi.org/documentation/configuration/wireless/README.md" target="_blank">
                                        official guide</a>
                                    </span>
                                </dd>
                                <br/>

                                <dt>Get EIoT client</dt>
                                <dd class="text-left">
                                    Download the client application from <a href="${pimqUrl}">here</a>,
                                    <span>You can use the wget command from raspberry</span>
<pre>
wget ${pimqUrl}
</pre>
                                </dd>
                                <br/>

                                <dt>Install EIoT client</dt>
                                <dd class="text-left">
                                    Extract the client content and copy on Raspberry
<pre class="text-left vertical-grabber">
unzip ${pimqUrl} <br/>
cp -r client /opt/eiotc
</pre>
                                </dd>
                                <br/>

                                <dt>Configure EIoT client</dt>
                                <dd class="text-left">
                                    Put
                                    <a href="${createLink(action: 'downloadConfiguration', params: [id: device.id])}">this file</a>
                                    on client folder
<pre class="text-left vertical-grabber">
cp configuration.properties /opt/eiotc/
</pre>
                                </dd>
                                <br/>

                                <dt>Finish</dt>
                                <dd class="text-left">
                                    Start the client
<pre class="text-left vertical-grabber">
cd /opt/eiotc/
sudo java -cp "/home/pi/development/pimq/client/.:/home/pi/development/pimq/client/dependency-jars/*" -jar /home/pi/development/pimq/client/org.fm.pimq.client-0.1-SNAPSHOT.jar /home/pi/development/pimq/client/ conf.properties &
</pre>
                                </dd>
                                <br/>
                            </dl>

                            <a href="#" type="button" class="btn btn-success btn-mg" data-dismiss="modal">
                                <i class="fa fa-check"></i>Done
                            </a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </g:each>
</g:if>


<!-- Manage Device -->
<g:if test="${devices.size() > 0}">
    <g:each in="${devices}" var="device" status="i">
        <div class="portfolio-modal modal fade" id="manageDevice${device.id}" tabindex="-1" role="dialog"
             aria-hidden="true">
            <div class="modal-content">
                <div class="close-modal" data-dismiss="modal">
                    <div class="lr">
                        <div class="rl">
                        </div>
                    </div>
                </div>

                <div class="container">
                    <div class="row">
                        <div class="col-lg-8 col-lg-offset-2">
                            <h3>${device.infos.name}</h3>
                            <hr />

                            <g:form method='POST' name='updateDeviceInfo' action='updateDeviceInfo'
                                    class="form-horizontal" role="form" autocomplete='off'>

                                <input type="hidden" name="id" value="${device.id}">

                                <g:render template="gpioInput"
                                          model="['label': 'GPIO 0', id: 'gpio0name', name: 'gpio0name', value: device.infos.gpio0Name]" />
                                <g:render template="gpioInput"
                                          model="['label': 'GPIO 1', id: 'gpio1name', name: 'gpio1name', value: device.infos.gpio1Name]" />
                                <g:render template="gpioInput"
                                          model="['label': 'GPIO 2', id: 'gpio2name', name: 'gpio2name', value: device.infos.gpio2Name]" />
                                <g:render template="gpioInput"
                                          model="['label': 'GPIO 3', id: 'gpio3name', name: 'gpio3name', value: device.infos.gpio3Name]" />
                                <g:render template="gpioInput"
                                          model="['label': 'GPIO 4', id: 'gpio4name', name: 'gpio4name', value: device.infos.gpio4Name]" />
                                <g:render template="gpioInput"
                                          model="['label': 'GPIO 5', id: 'gpio5name', name: 'gpio5name', value: device.infos.gpio5Name]" />
                                <g:render template="gpioInput"
                                          model="['label': 'GPIO 6', id: 'gpio6name', name: 'gpio6name', value: device.infos.gpio6Name]" />
                                <g:render template="gpioInput"
                                          model="['label': 'GPIO 7', id: 'gpio7name', name: 'gpio7name', value: device.infos.gpio7Name]" />
                                <g:render template="gpioInput"
                                          model="['label': 'GPIO 8', id: 'gpio8name', name: 'gpio8name', value: device.infos.gpio8Name]" />
                                <g:render template="gpioInput"
                                          model="['label': 'GPIO 9', id: 'gpio9name', name: 'gpio9name', value: device.infos.gpio9Name]" />
                                <g:render template="gpioInput"
                                          model="['label': 'GPIO 10', id: 'gpio10name', name: 'gpio10name', value: device.infos.gpio10Name]" />
                                <g:render template="gpioInput"
                                          model="['label': 'GPIO 11', id: 'gpio11name', name: 'gpio11name', value: device.infos.gpio11Name]" />
                                <g:render template="gpioInput"
                                          model="['label': 'GPIO 12', id: 'gpio12name', name: 'gpio12name', value: device.infos.gpio12Name]" />
                                <g:render template="gpioInput"
                                          model="['label': 'GPIO 13', id: 'gpio13name', name: 'gpio13name', value: device.infos.gpio13Name]" />
                                <g:render template="gpioInput"
                                          model="['label': 'GPIO 14', id: 'gpio14name', name: 'gpio14name', value: device.infos.gpio14Name]" />
                                <g:render template="gpioInput"
                                          model="['label': 'GPIO 15', id: 'gpio15name', name: 'gpio15name', value: device.infos.gpio15Name]" />
                                <g:render template="gpioInput"
                                          model="['label': 'GPIO 16', id: 'gpio16name', name: 'gpio16name', value: device.infos.gpio16Name]" />
                                <g:render template="gpioInput"
                                          model="['label': 'GPIO 17', id: 'gpio17name', name: 'gpio17name', value: device.infos.gpio17Name]" />

                                <div class="row">
                                    <div class="form-group col-xs-6 text-left">
                                        <br/><br/>
                                        <a href="#"
                                           id="delete" name="delete" class="btn btn-danger btn-lg"
                                           onclick='swal({title: "Are you sure?",
                                                          text:  "You will not be able to recover this device!",
                                                          type: "warning",
                                                          showCancelButton: true,
                                                          confirmButtonColor: "#DD6B55",
                                                          confirmButtonText: "Yes, delete it!",
                                                          cancelButtonText: "No, cancel plx!",
                                                          closeOnConfirm: false,   closeOnCancel: true },
                                                         function(isConfirm) {
                                                             if(isConfirm) {
                                                                 window.location = "${createLink(controller: 'dashboard', action: 'deleteDevice', id: device.id)}";
                                                             }
                                                         });'>
                                            Delete device</a>
                                    </div>

                                    <div class="form-group col-xs-6 text-right">
                                        <br/><br/>
                                        <g:submitButton name="updateDevice" value="Update" class="btn btn-success btn-lg"/>
                                    </div>
                                </div>
                            </g:form>

                        </div>
                    </div>
                </div>
            </div>
        </div>
    </g:each>
</g:if>


<!-- Remote Device -->
<g:if test="${devices.size() > 0}">
    <g:each in="${devices}" var="device" status="i">
        <div class="portfolio-modal modal fade" id="remoteDevice${device.id}" tabindex="-1" role="dialog"
             aria-hidden="true">
            <div class="modal-content">
                <div class="close-modal" data-dismiss="modal">
                    <div class="lr">
                        <div class="rl">
                        </div>
                    </div>
                </div>

                <div class="container">
                    <div class="row">
                        <div class="col-lg-8 col-lg-offset-2">
                            <h3>${device.infos.name}</h3>
                            <hr />

                            <g:render template="gpioRemote"
                                      model="['visible': device.infos.gpio0Visible, 'name': device.infos.gpio0Name, 'deviceId': device.id, 'pinId': 1]" />
                            <g:render template="gpioRemote"
                                      model="['visible': device.infos.gpio1Visible, 'name': device.infos.gpio1Name, 'deviceId': device.id, 'pinId': 2]" />
                            <g:render template="gpioRemote"
                                      model="['visible': device.infos.gpio2Visible, 'name': device.infos.gpio2Name, 'deviceId': device.id, 'pinId': 3]" />
                            <g:render template="gpioRemote"
                                      model="['visible': device.infos.gpio3Visible, 'name': device.infos.gpio3Name, 'deviceId': device.id, 'pinId': 4]" />
                            <g:render template="gpioRemote"
                                      model="['visible': device.infos.gpio4Visible, 'name': device.infos.gpio4Name, 'deviceId': device.id, 'pinId': 5]" />
                            <g:render template="gpioRemote"
                                      model="['visible': device.infos.gpio5Visible, 'name': device.infos.gpio5Name, 'deviceId': device.id, 'pinId': 6]" />
                            <g:render template="gpioRemote"
                                      model="['visible': device.infos.gpio6Visible, 'name': device.infos.gpio6Name, 'deviceId': device.id, 'pinId': 7]" />
                            <g:render template="gpioRemote"
                                      model="['visible': device.infos.gpio7Visible, 'name': device.infos.gpio7Name, 'deviceId': device.id, 'pinId': 8]" />
                            <g:render template="gpioRemote"
                                      model="['visible': device.infos.gpio8Visible, 'name': device.infos.gpio8Name, 'deviceId': device.id, 'pinId': 9]" />
                            <g:render template="gpioRemote"
                                      model="['visible': device.infos.gpio9Visible, 'name': device.infos.gpio9Name, 'deviceId': device.id, 'pinId': 10]" />
                            <g:render template="gpioRemote"
                                      model="['visible': device.infos.gpio10Visible, 'name': device.infos.gpio10Name, 'deviceId': device.id, 'pinId': 11]" />
                            <g:render template="gpioRemote"
                                      model="['visible': device.infos.gpio11Visible, 'name': device.infos.gpio11Name, 'deviceId': device.id, 'pinId': 12]" />
                            <g:render template="gpioRemote"
                                      model="['visible': device.infos.gpio12Visible, 'name': device.infos.gpio12Name, 'deviceId': device.id, 'pinId': 13]" />
                            <g:render template="gpioRemote"
                                      model="['visible': device.infos.gpio13Visible, 'name': device.infos.gpio13Name, 'deviceId': device.id, 'pinId': 14]" />
                            <g:render template="gpioRemote"
                                      model="['visible': device.infos.gpio14Visible, 'name': device.infos.gpio14Name, 'deviceId': device.id, 'pinId': 15]" />
                            <g:render template="gpioRemote"
                                      model="['visible': device.infos.gpio15Visible, 'name': device.infos.gpio15Name, 'deviceId': device.id, 'pinId': 16]" />
                            <g:render template="gpioRemote"
                                      model="['visible': device.infos.gpio16Visible, 'name': device.infos.gpio16Name, 'deviceId': device.id, 'pinId': 17]" />
                            <g:render template="gpioRemote"
                                      model="['visible': device.infos.gpio17Visible, 'name': device.infos.gpio17Name, 'deviceId': device.id, 'pinId': 18]" />

                        </div>
                    </div>
                </div>
            </div>
        </div>
    </g:each>
</g:if>


<!-- Personal -->
<div class="portfolio-modal modal fade" id="personal" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-content">
        <div class="close-modal" data-dismiss="modal">
            <div class="lr">
                <div class="rl">
                </div>
            </div>
        </div>

        <div class="container">
            <div class="row">
                <div class="col-lg-8 col-lg-offset-2">
                    <h3>Change your personal information</h3>

                    <g:form method='POST' name='updatePersonal' action='updatePersonal'
                            class="form-horizontal" role="form" autocomplete='off'>

                        <div class="row control-group">
                            <div class="form-group col-xs-12 floating-label-form-group controls">
                                <input type="text" class="form-control" placeholder="Your complete name"
                                       id="completeName" name="completeName" value="${currentUser.name}"
                                       data-validation-required-message="Please enter your complete name" />
                                <p class="help-block text-danger"></p>
                            </div>
                        </div>

                        <div class="row control-group">
                            <div class="form-group col-xs-12 floating-label-form-group controls">
                                <label>Email</label>
                                <input type="text" class="form-control" placeholder="Your country"
                                       id="country" name="country" value="${currentUser.state}"
                                       data-validation-required-message="Please enter your country" />
                                <p class="help-block text-danger"></p>
                            </div>
                        </div>

                        <div class="row control-group">
                            <div class="form-group col-xs-6 floating-label-form-group controls">
                                <label>Password</label>
                                <input type="password" class="form-control" placeholder="Your password"
                                       id="password" name="password"
                                       data-validation-required-message="Please enter the new password" />
                                <p class="help-block text-danger"></p>
                            </div>
                            <div class="form-group col-xs-6 floating-label-form-group controls">
                                <label>Repeat password</label>
                                <input type="password" class="form-control" placeholder="Repeat password"
                                       id="repeatPassword" name="repeatPassword"
                                       data-validation-required-message="Please repeat the new password" />
                                <p class="help-block text-danger"></p>
                            </div>
                        </div>

                        <br/>

                        <div class="row">
                            <div class="form-group col-xs-12 text-right">
                                <g:submitButton name="updatePersonal" value="Update" class="btn btn-success btn-lg"/>
                            </div>
                        </div>
                    </g:form>
                </div>
            </div>
        </div>
    </div>
</div>


<script>
    <g:each in="${devices}" var="device" status="i">
        if (location.hash == "#remoteDevice${device.id}") {
            $("#btnRemoteDevice${device.id}").click();
        }
    </g:each>
</script>

</body>
</html>
