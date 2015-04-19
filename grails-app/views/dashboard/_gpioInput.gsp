<g:if test="${gpioNumber == com.fm.easyiotconnect.mq.DeviceInfos.GPIO_W1_THERM}">
    <div class="row form-group">
        <div class="col-xs-6 floating-label-form-group controls" >
            <label><g:message code="gpio.input.name.label" args="${gpioNumber}"/></label>
            <input type="text" class="form-control" placeholder="${message(code:'gpio.input.name.label', args: gpioNumber)}"
                   id="gpio${gpioNumber}name" name="gpio${gpioNumber}name"
                   value="${name}">
            <p class="help-block text-danger"></p>
        </div>
        <div class="col-xs-6 controls">
            <label></label>
            <select class="form-control" id="gpio${gpioNumber}type" name="gpio${gpioNumber}type">
                <option value="${com.fm.easyiotconnect.mq.DeviceInfos.GPIO_TYPE_ON_OFF}"
                        ${type == com.fm.easyiotconnect.mq.DeviceInfos.GPIO_TYPE_ON_OFF?'selected=selected':''}">
                    On - Off
                </option>

                <option value="${com.fm.easyiotconnect.mq.DeviceInfos.GPIO_TYPE_W1_THERM}"
                        ${type == com.fm.easyiotconnect.mq.DeviceInfos.GPIO_TYPE_W1_THERM?'selected=selected':''}>
                    W1-Therm
                </option>
            </select>
            <p class="help-block text-danger"></p>
        </div>
    </div>
</g:if>
<g:else>
    <div class="form-group col-xs-12 floating-label-form-group controls">
        <label><g:message code="gpio.input.name.label" args="${gpioNumber}"/></label>
        <input type="text" class="form-control" placeholder="${message(code:'gpio.input.name.label', args: gpioNumber)}"
               id="gpio${gpioNumber}name" name="gpio${gpioNumber}name"
               value="${name}">
        <p class="help-block text-danger"></p>
    </div>
</g:else>