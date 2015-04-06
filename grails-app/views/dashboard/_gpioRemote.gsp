<g:if test="${visible}">
    <div class="row">
        <div class="col-xs-4 text-right">
            <button class="btn btn-success btn-lg"
                    onclick="$.ajax({
                        url: '${createLink(action: 'sendCommand', params: [id: deviceId, pin: pinId, status: 1])}',
                        type: 'POST', dataType:'JSON', data: {},
                            success: function(r){
                                if(!r.error) {
                                    swal('Done', 'Command sent successfully!', 'success');
                                }
                                else {
                                    swal('Sorry', 'We have got a problem sending the command!', 'warning');
                                }
                            },
                            error: function(e){
                                if(e.status == 401) {
                                    window.location = '${createLink(uri: '/#loginModal')}'
                                }
                                else {
                                    swal('Sorry', 'We have got a problem sending the command!', 'warning');
                                }
                            }
                    });">
                Send On
            </button>
        </div>

        <div class="form-group col-xs-4 text-center text-primary" style="margin-top: 10px">
            ${name}
        </div>

        <div class="col-xs-4 text-left">
            <button class="btn btn-danger btn-lg"
                    onclick="$.ajax({
                        url: '${createLink(action: 'sendCommand', params: [id: deviceId, pin: pinId, status: 0])}',
                        type: 'POST', dataType:'JSON', data: {},
                        success: function(r){
                            if(!r.error) {
                                swal('Done', 'Command sent successfully!', 'success');
                            }
                            else {
                                swal('Sorry', 'We have got a problem sending the command!', 'warning');
                            }
                        },
                        error: function(e){
                            if(e.status == 401) {
                                window.location = '${createLink(uri: '/#loginModal')}'
                            }
                            else {
                                swal('Sorry', 'We have got a problem sending the command!', 'warning');
                            }
                        }
                        }
                    });">
                Send Off
            </button>
        </div>
    </div>
    <br />
</g:if>
