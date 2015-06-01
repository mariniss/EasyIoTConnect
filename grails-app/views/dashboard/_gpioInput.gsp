<%@ page import="com.fm.easyiotconnect.mq.TimedCommand" %>

<g:set var="timerSendOn"  value="${gpioTimedCommands[(TimedCommand.TYPE_SEND_ON)]}" />
<g:set var="timerSendOff" value="${gpioTimedCommands[(TimedCommand.TYPE_SEND_OFF)]}" />
<g:if test="${timerSendOn != null}" >
    <g:set var="recurringType"          value="${timerSendOn.recurringType}" />
    <g:set var="recurringOnMonday"      value="${timerSendOn.recurringOnMonday}" />
    <g:set var="recurringOnTuesday"     value="${timerSendOn.recurringOnTuesday}" />
    <g:set var="recurringOnWednesday"   value="${timerSendOn.recurringOnWednesday}" />
    <g:set var="recurringOnThursday"    value="${timerSendOn.recurringOnThursday}" />
    <g:set var="recurringOnFriday"      value="${timerSendOn.recurringOnFriday}" />
    <g:set var="recurringOnSaturday"    value="${timerSendOn.recurringOnSaturday}" />
    <g:set var="recurringOnSunday"      value="${timerSendOn.recurringOnSunday}" />
</g:if>
<g:else>
    <g:set var="recurringType"          value="${null}" />
    <g:set var="recurringOnMonday"      value="${false}" />
    <g:set var="recurringOnTuesday"     value="${false}" />
    <g:set var="recurringOnWednesday"   value="${false}" />
    <g:set var="recurringOnThursday"    value="${false}" />
    <g:set var="recurringOnFriday"      value="${false}" />
    <g:set var="recurringOnSaturday"    value="${false}" />
    <g:set var="recurringOnSunday"      value="${false}" />
</g:else>

<div id="input_${deviceId}_${gpioNumber}" class="form-group row">
    <div class="col-xs-6 floating-label-form-group controls" >
        <label><g:message code="gpio.input.identifier.label" args="[gpioNumber]"/></label>
        <input type="text" class="form-control" placeholder="${message(code:'gpio.input.name.label', args: [gpioNumber])}"
               id="gpio${gpioNumber}name" name="gpio${gpioNumber}name"
               value="${name}">
        <p class="help-block text-danger"></p>

        <a id ="show_timer_${deviceId}_${gpioNumber}" href="#timer_input_${deviceId}_${gpioNumber}"
           class="${(timerSendOn == null)?'hidden':''}" onclick="collapseTimerSection('timer_input_${deviceId}_${gpioNumber}', false);"><i class="fa fa-chevron-down"></i> Show or change timer</a>
    </div>
    <div class="col-xs-6 controls">
        <label></label>
        <select class="form-control" id="gpio${gpioNumber}type" name="gpio${gpioNumber}type"
                onchange="if(this.value == '${com.fm.easyiotconnect.mq.DeviceInfos.GPIO_TYPE_TIMER}'){ collapseTimerSection('timer_input_${deviceId}_${gpioNumber}', false); setTimeZoneOn('timer_command_${deviceId}_${gpioNumber}_timezone');} else { collapseTimerSection('timer_input_${deviceId}_${gpioNumber}', true); $('#show_timer_${deviceId}_${gpioNumber}').addClass('hidden');}">
            <option value="${com.fm.easyiotconnect.mq.DeviceInfos.GPIO_TYPE_ON_OFF}"
                    ${type == com.fm.easyiotconnect.mq.DeviceInfos.GPIO_TYPE_ON_OFF?'selected=selected':''}">
                On - Off
            </option>

            <option value="${com.fm.easyiotconnect.mq.DeviceInfos.GPIO_TYPE_TIMER}"
                ${type == com.fm.easyiotconnect.mq.DeviceInfos.GPIO_TYPE_TIMER?'selected=selected':''}>
                    Timer
            </option>

            <g:if test="${gpioNumber == com.fm.easyiotconnect.mq.DeviceInfos.GPIO_W1_THERM}">
                <option value="${com.fm.easyiotconnect.mq.DeviceInfos.GPIO_TYPE_W1_THERM}"
                    ${type == com.fm.easyiotconnect.mq.DeviceInfos.GPIO_TYPE_W1_THERM?'selected=selected':''}>
                    W1-Therm
                </option>
            </g:if>
        </select>
        <p class="help-block text-danger"></p>
    </div>
</div>

<!-- Accordion Time configuration -->
<div class="col-xs-12">
    <div class="panel-group">
        <div class="panel panel-default" style="border: 0px;">
            <div id="timer_input_${deviceId}_${gpioNumber}" class="panel-collapse collapse">
                <div class="panel-body">

                    <div class="row">
                        <div class="col-xs-8 form-group text-center">
                            <label>Send On at</label>
                            <div class="input-group date form_datetime" data-date="" data-date-format="dd/MM/yyyy HH:ii p"
                                 id='dp_${deviceId}_${gpioNumber}_send_on_date' name='dp_${deviceId}_${gpioNumber}_send_on_date'>
                                <input class="form-control" size="16" type="text" value="" readonly id="dp_${deviceId}_${gpioNumber}_send_on_at" name="dp_${deviceId}_${gpioNumber}_send_on_at">
                                <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                                <span class="input-group-addon"><span class="glyphicon glyphicon-th"></span></span>
                            </div>
                            <g:if test="${timerSendOn?.executionTime != null}">
                                <script>
                                    $("#dp_${deviceId}_${gpioNumber}_send_on_date").datetimepicker('update', new Date(${timerSendOn.executionTimeWithTZ.time}));
                                </script>
                            </g:if>
                        </div>

                        <div class="col-xs-4 controls">
                            <label>Send off after</label>
                            <select class="form-control" id="sl_${deviceId}_${gpioNumber}_send_off_at" name="sl_${deviceId}_${gpioNumber}_send_off_at">
                                <g:each in="${com.fm.easyiotconnect.mq.SendOffValues.values()}" var="sendOffType">
                                    <option value="${sendOffType}" ${(timerSendOn?.sendOffAfter == sendOffType)? 'selected="selected"':''}>
                                        ${sendOffType.name}
                                    </option>
                                </g:each>
                            </select>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-xs-4 controls repeat-controls">
                            <label>Repeat every</label>
                            <select class="form-control" id="sl_${deviceId}_${gpioNumber}_timer_repeat" name="sl_${deviceId}_${gpioNumber}_timer_repeat">
                                <g:each in="${com.fm.easyiotconnect.mq.TimedRecurType.values()}" var="recurType">
                                    <option value="${recurType}" ${(recurringType == recurType)? 'selected="selected"':''}>
                                        ${recurType.name}
                                    </option>
                                </g:each>
                            </select>
                        </div>

                        <div class="col-xs-8 controls">
                            <label>Repeat on</label><br/>
                            <div class="gpio-input repeat-on-option-first">
                                <input type="checkbox" id="cb_${deviceId}_${gpioNumber}_timer_repeat_mo" name="cb_${deviceId}_${gpioNumber}_timer_repeat_mo"
                                    ${recurringOnMonday? 'checked="checked"':''}/> Mo
                            </div>
                            <div class="gpio-input repeat-on-option">
                                <input type="checkbox" id="cb_${deviceId}_${gpioNumber}_timer_repeat_tu" name="cb_${deviceId}_${gpioNumber}_timer_repeat_tu"
                                    ${recurringOnTuesday? 'checked="checked"':''}/> Tu
                            </div>
                            <div class="gpio-input repeat-on-option">
                                <input type="checkbox" id="cb_${deviceId}_${gpioNumber}_timer_repeat_we" name="cb_${deviceId}_${gpioNumber}_timer_repeat_we"
                                    ${recurringOnWednesday? 'checked="checked"':''}> We
                            </div>
                            <div class="gpio-input repeat-on-option">
                                <input type="checkbox" id="cb_${deviceId}_${gpioNumber}_timer_repeat_th" name="cb_${deviceId}_${gpioNumber}_timer_repeat_th"
                                    ${recurringOnThursday? 'checked="checked"':''}> Th
                            </div>
                            <div class="gpio-input repeat-on-option">
                                <input type="checkbox" id="cb_${deviceId}_${gpioNumber}_timer_repeat_fr" name="cb_${deviceId}_${gpioNumber}_timer_repeat_fr"
                                    ${recurringOnFriday? 'checked="checked"':''}> Fr
                            </div>
                            <div class="gpio-input repeat-on-option">
                                <input type="checkbox" id="cb_${deviceId}_${gpioNumber}_timer_repeat_sa" name="cb_${deviceId}_${gpioNumber}_timer_repeat_sa"
                                    ${recurringOnSaturday? 'checked="checked"':''}> Sa
                            </div>
                            <div class="gpio-input repeat-on-option">
                                <input type="checkbox" id="cb_${deviceId}_${gpioNumber}_timer_repeat_su" name="cb_${deviceId}_${gpioNumber}_timer_repeat_su"
                                    ${recurringOnSunday? 'checked="checked"':''}> Su
                            </div>
                        </div>
                    </div>

                    <input type="hidden" id="timer_command_${deviceId}_${gpioNumber}_timezone" name="timer_command_${deviceId}_${gpioNumber}_timezone" value="${timerSendOn?.timeZoneName?:''}">

                    <a type="button" class="btn btn-success" href="#timer_input_${deviceId}_${gpioNumber}"
                        onclick="collapseTimerSection('timer_input_${deviceId}_${gpioNumber}'); if($('#gpio${gpioNumber}type').val() == '${com.fm.easyiotconnect.mq.DeviceInfos.GPIO_TYPE_TIMER}') {$('#show_timer_${deviceId}_${gpioNumber}').removeClass('hidden');} else {$('#show_timer_${deviceId}_${gpioNumber}').addClass('hidden');}">
                        <i class="fa fa-check"></i>Close
                    </a>

                    <a type="button" class="btn btn-warning" href="#timer_input_${deviceId}_${gpioNumber}"
                       onclick="collapseTimerSection('timer_input_${deviceId}_${gpioNumber}'); $('#gpio${gpioNumber}type').val('${com.fm.easyiotconnect.mq.DeviceInfos.GPIO_TYPE_ON_OFF}'); $('#show_timer_${deviceId}_${gpioNumber}').addClass('hidden');">
                        <i class="fa fa-times"></i>Cancel
                    </a>
                </div>
            </div>
        </div>
    </div>
</div>
