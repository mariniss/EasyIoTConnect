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
    <g:set var="installScriptUrl" value="${createLink(uri: '/install.sh')}" />

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
                                    Download the installation script by <a href="${installScriptUrl}">this link</a> on your raspberry,
                                    <span>You can use the wget command from raspberry and download it on the home</span>
<pre>
cd /home/pi
mkdir eiotc
cd eiotc
wget http://www.easyiotconnect.com/install.sh
</pre>
                                </dd>
                                <br/>

                                <dt>Install EIoT client</dt>
                                <dd class="text-left">
                                    Execute the installation script just downloaded
<pre class="text-left vertical-grabber">
sh ./install.sh
</pre>
                                </dd>
                                <br/>

                                <dt>Configure EIoT client</dt>
                                <dd class="text-left">
                                    Put
                                    <a href="${createLink(action: 'downloadConfiguration', params: [id: device.id])}">this file</a>
                                    on client folder. Copy the file by scp or usb key; if you are using Windows OS you can use WinSCP <br />
                                    With scp you can do:
<pre class="text-left vertical-grabber">
#open a terminal
#goto into the directory where is the download file
scp configurations.properties pi@[raspberry ip]:/home/pi/eiotc/client
</pre>
                                </dd>
                                <br/>

                                <dt>Finish</dt>
                                <dd class="text-left">
                                    Start the client using the generated file runClient.sh
<pre class="text-left vertical-grabber">
sudo sh ./runClient.sh
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

                            <g:form method='POST' name='updateDeviceInfo' action='updateDevice'
                                    class="form-horizontal" role="form" autocomplete='off'>

                                <input type="hidden" name="id" value="${device.id}">

                                <g:render template="gpioInput"
                                          model="[gpioNumber: 0, name: device.infos.gpio0Name, type: device.infos.gpio0Type]" />
                                <g:render template="gpioInput"
                                          model="[gpioNumber: 1, name: device.infos.gpio1Name, type: device.infos.gpio1Type]" />
                                <g:render template="gpioInput"
                                          model="[gpioNumber: 2, name: device.infos.gpio2Name, type: device.infos.gpio2Type]" />
                                <g:render template="gpioInput"
                                          model="[gpioNumber: 3, name: device.infos.gpio3Name, type: device.infos.gpio3Type]" />
                                <g:render template="gpioInput"
                                          model="[gpioNumber: 4, name: device.infos.gpio4Name, type: device.infos.gpio4Type]" />
                                <g:render template="gpioInput"
                                          model="[gpioNumber: 5, name: device.infos.gpio5Name, type: device.infos.gpio5Type]" />
                                <g:render template="gpioInput"
                                          model="[gpioNumber: 6, name: device.infos.gpio6Name, type: device.infos.gpio6Type]" />
                                <g:render template="gpioInput"
                                          model="[gpioNumber: 7, name: device.infos.gpio7Name, type: device.infos.gpio7Type]" />
                                <g:render template="gpioInput"
                                          model="[gpioNumber: 8, name: device.infos.gpio8Name, type: device.infos.gpio8Type]" />
                                <g:render template="gpioInput"
                                          model="[gpioNumber: 9, name: device.infos.gpio9Name, type: device.infos.gpio9Type]" />
                                <g:render template="gpioInput"
                                          model="[gpioNumber: 10, name: device.infos.gpio10Name, type: device.infos.gpio10Type]" />
                                <g:render template="gpioInput"
                                          model="[gpioNumber: 11, name: device.infos.gpio11Name, type: device.infos.gpio11Type]" />
                                <g:render template="gpioInput"
                                          model="[gpioNumber: 12, name: device.infos.gpio12Name, type: device.infos.gpio12Type]" />
                                <g:render template="gpioInput"
                                          model="[gpioNumber: 13, name: device.infos.gpio13Name, type: device.infos.gpio13Type]" />
                                <g:render template="gpioInput"
                                          model="[gpioNumber: 14, name: device.infos.gpio14Name, type: device.infos.gpio14Type]" />
                                <g:render template="gpioInput"
                                          model="[gpioNumber: 15, name: device.infos.gpio15Name, type: device.infos.gpio15Type]" />
                                <g:render template="gpioInput"
                                          model="[gpioNumber: 16, name: device.infos.gpio16Name, type: device.infos.gpio16Type]" />
                                <g:render template="gpioInput"
                                          model="[gpioNumber: 17, name: device.infos.gpio17Name, type: device.infos.gpio17Type]" />
                                <g:render template="gpioInput"
                                          model="[gpioNumber: 18, name: device.infos.gpio18Name, type: device.infos.gpio18Type]" />
                                <g:render template="gpioInput"
                                          model="[gpioNumber: 19, name: device.infos.gpio19Name, type: device.infos.gpio19Type]" />
                                <g:render template="gpioInput"
                                          model="[gpioNumber: 20, name: device.infos.gpio20Name, type: device.infos.gpio20Type]" />

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
                                      model="['visible': device.infos.gpio0Visible, 'name': device.infos.gpio0Name, 'deviceId': device.id, 'pinId': 0]" />
                            <g:render template="gpioRemote"
                                      model="['visible': device.infos.gpio1Visible, 'name': device.infos.gpio1Name, 'deviceId': device.id, 'pinId': 1]" />
                            <g:render template="gpioRemote"
                                      model="['visible': device.infos.gpio2Visible, 'name': device.infos.gpio2Name, 'deviceId': device.id, 'pinId': 2]" />
                            <g:render template="gpioRemote"
                                      model="['visible': device.infos.gpio3Visible, 'name': device.infos.gpio3Name, 'deviceId': device.id, 'pinId': 3]" />
                            <g:render template="gpioRemote"
                                      model="['visible': device.infos.gpio4Visible, 'name': device.infos.gpio4Name, 'deviceId': device.id, 'pinId': 4]" />
                            <g:render template="gpioRemote"
                                      model="['visible': device.infos.gpio5Visible, 'name': device.infos.gpio5Name, 'deviceId': device.id, 'pinId': 5]" />
                            <g:render template="gpioRemote"
                                      model="['visible': device.infos.gpio6Visible, 'name': device.infos.gpio6Name, 'deviceId': device.id, 'pinId': 6]" />
                            <g:render template="gpioRemote"
                                      model="['visible': device.infos.gpio7Visible, 'name': device.infos.gpio7Name, 'deviceId': device.id, 'pinId': 7]" />
                            <g:render template="gpioRemote"
                                      model="['visible': device.infos.gpio8Visible, 'name': device.infos.gpio8Name, 'deviceId': device.id, 'pinId': 8]" />
                            <g:render template="gpioRemote"
                                      model="['visible': device.infos.gpio9Visible, 'name': device.infos.gpio9Name, 'deviceId': device.id, 'pinId': 9]" />
                            <g:render template="gpioRemote"
                                      model="['visible': device.infos.gpio10Visible, 'name': device.infos.gpio10Name, 'deviceId': device.id, 'pinId': 10]" />
                            <g:render template="gpioRemote"
                                      model="['visible': device.infos.gpio11Visible, 'name': device.infos.gpio11Name, 'deviceId': device.id, 'pinId': 11]" />
                            <g:render template="gpioRemote"
                                      model="['visible': device.infos.gpio12Visible, 'name': device.infos.gpio12Name, 'deviceId': device.id, 'pinId': 12]" />
                            <g:render template="gpioRemote"
                                      model="['visible': device.infos.gpio13Visible, 'name': device.infos.gpio13Name, 'deviceId': device.id, 'pinId': 13]" />
                            <g:render template="gpioRemote"
                                      model="['visible': device.infos.gpio14Visible, 'name': device.infos.gpio14Name, 'deviceId': device.id, 'pinId': 14]" />
                            <g:render template="gpioRemote"
                                      model="['visible': device.infos.gpio15Visible, 'name': device.infos.gpio15Name, 'deviceId': device.id, 'pinId': 15]" />
                            <g:render template="gpioRemote"
                                      model="['visible': device.infos.gpio16Visible, 'name': device.infos.gpio16Name, 'deviceId': device.id, 'pinId': 16]" />
                            <g:render template="gpioRemote"
                                      model="['visible': device.infos.gpio17Visible, 'name': device.infos.gpio17Name, 'deviceId': device.id, 'pinId': 17]" />
                            <g:render template="gpioRemote"
                                      model="['visible': device.infos.gpio18Visible, 'name': device.infos.gpio18Name, 'deviceId': device.id, 'pinId': 18]" />
                            <g:render template="gpioRemote"
                                      model="['visible': device.infos.gpio19Visible, 'name': device.infos.gpio19Name, 'deviceId': device.id, 'pinId': 19]" />
                            <g:render template="gpioRemote"
                                      model="['visible': device.infos.gpio20Visible, 'name': device.infos.gpio20Name, 'deviceId': device.id, 'pinId': 20]" />

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

<script>
    <g:if test="${devices.any{it.w1ThermEnabled()}}">
        $(function() {
            var w1thermOuts = $('.w1therm-out');
            var timeout = null, lastXhr = false;

            function refresh_w1therm_value(w1therm, w1thermDiv) {
                if(w1therm.error) {
                    $('.w1therm-out-one', w1thermDiv).html('- °C');
                    $('.w1therm-out-two', w1thermDiv).html('- °F');
                }
                else {
                    $('.w1therm-out-one', w1thermDiv).html(w1therm.data.celsius + ' °C' || '- °C');
                    $('.w1therm-out-two', w1thermDiv).html(w1therm.data.fahrenheit + ' °F'  || '- °F');
                }
            }

            function update_status() {
                if(lastXhr) { lastXhr.abort(); }

                w1thermOuts.each(function() {
                    var self = this;
                    var deviceId = self.dataset.deviceId;

                    lastXhr = $.ajax({
                        url: "${g.createLink(action:'w1ThermData')}",
                        type: "POST", dataType: "json", data: { id: deviceId },
                        success: function(data) { refresh_w1therm_value(data, self) },
                        error: function() {
                            var data = {error: 'Data transmision'};
                            refresh_w1therm_value(data, self)
                        }
                    });
                });
            }

            function start_monitoring() {
                if(timeout) { clearInterval(timeout); }
                timeout = setInterval(update_status, 30000);
            }

            if($(w1thermOuts).length) {
                update_status();
                start_monitoring();
            }
        });
    </g:if>

</script>

</body>
</html>
