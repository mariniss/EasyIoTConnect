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
                </div>
            </div>
        </div>
    </header>
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
                    <g:link action="manage" params="[id: device.id]">
                        <asset:image src="sweet-home.png"/>
                    </g:link>
                </div>

                <div class="col-lg-4  text-center">
                    <a href="#configureDevice${device.id}" data-toggle="modal">
                        <asset:image src="jack-l.png"/>
                    </a>
                </div>

                <div class="col-lg-4  text-center">
                    <g:link action="remote" params="[id: device.id]">
                        <asset:image src="Internet.png"/>
                    </g:link>
                </div>
            </div>
        </section>
    </g:each>
    </div>
</div>
</div>
</g:if>

<!-- Buttons section -->
<section id="actions">
    <div class="container">
        <div class="row">
            <p class="text-right">
                <g:if test="${devices == null || devices.size == 0}">To start you have to add a new Device <i
                        class="fa fa-arrow-right"></i></g:if>
                <a href="#createDevice" class="btn btn-lg btn-primary main-action  btn-action"
                   data-toggle="modal">New</a>
            </p>

            <p class="text-right">
                <g:if test="${toConfigure == true}">And configure your it <i class="fa fa-arrow-right"></i></g:if>
                <a href="${createLink(action: 'configure')}"
                   class="btn btn-lg btn-primary main-action  btn-action">Configure</a>
            </p>
        </div>
    </div>
</section>

<!-- *** Modals *** -->

<!-- Create Device -->
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

                        <!-- <div id="success"></div> -->
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
                                <dd>Give the Internet access to your Device,
                                    <span>see the <a
                                            href="http://www.raspberrypi.org/documentation/configuration/wireless/README.md">official guide</a>
                                    </span>
                                </dd>
                                <br/>

                                <dt>Get EIoT client</dt>
                                <dd>
                                    Download the client application from <a href="${pimqUrl}">here</a>,
                                    <span>You can use the wget command from raspberry</span>
                                </dd>
                                <br/>

                                <dt>Install EIoT client</dt>
                                <dd>
                                    Extract the client content and copy on Raspberry, <span>Example...</span>
                                </dd>
                                <br/>

                                <dt>Configure EIoT client</dt>
                                <dd>
                                    Put
                                    <a href="${createLink(action: 'downloadConfiguration', params: [id: device.id])}">this file</a>
                                    on client folder
                                </dd>
                                <br/>

                                <dt>Finish</dt>
                                <dd>
                                    Start the client, <span>Example...</span>
                                </dd>
                                <br/>
                            </dl>

                            <a href="${createLink(action: 'configured', params: [id: device.id])}"
                               class="btn btn-success btn-lg" role="button">Done</a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </g:each>
</g:if>
</body>
</html>
