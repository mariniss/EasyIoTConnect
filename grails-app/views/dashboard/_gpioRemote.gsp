<g:if test="${visible}">
    <div class="row">
        <div class="col-xs-4 text-right">
            <a href="${createLink(action: 'sendCommand', params: [id: deviceId, pin: pinId, status: 1])}"
               class="btn btn-success btn-lg"> Send On </a>
        </div>

        <div class="form-group col-xs-4 text-center text-primary" style="margin-top: 10px">
            ${name}
        </div>

        <div class="col-xs-4 text-left">
            <a href="${createLink(action: 'sendCommand', params: [id: deviceId, pin: pinId, status: 0])}"
               class="btn btn-danger btn-lg"> Send Off </a>
        </div>
    </div>
    <br />
</g:if>
