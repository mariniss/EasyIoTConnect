package easyiotconnect

import com.fm.easyiotconnect.ServiceCodes
import com.fm.easyiotconnect.ServiceError
import com.fm.easyiotconnect.User
import com.fm.easyiotconnect.UserQuestions


class LandingController {

    def springSecurityService
    def messageCodeResolverService
    def userService


    def index() {
    }


    def askQuestion = {
        if(params.sender && params.message) {
            boolean asked = userService.askQuestion(params.sender, params.name, params.message)

            if(asked) {
                flash.alert = [type:"success", title: "Done", message: "Question sent successfully!"]
            }
            else {
                flash.alert = [type:"waring", title: "Sorry", message: "There was problem asking your question!"]
            }
        }

        redirect view: "index"
    }


    def singUp() {
        def name 	 = params.fullname
        def country  = params.country
        def email	 = params.email
        def password = params.password

        def resultCode
        try{
            resultCode = userService.createBaseUser(name, country, email, password)
        }
        catch (ServiceError exc) {
            log.error("Exception creating new base user ${email}", exc)

            resultCode = exc.code
        }

        def type = (resultCode == ServiceCodes.Infos.USER_CREATED) ? "success" : "waring"
        def title = (resultCode == ServiceCodes.Infos.USER_CREATED) ? "Done" : "Sorry"
        def message = messageCodeResolverService.getMessageByCode(resultCode)

        flash.alert = [type:type, title: title, message: message]

        if(resultCode == ServiceCodes.Infos.USER_CREATED) {
            springSecurityService.reauthenticate(email)

            redirect controller: "dashboard"
        }
        else {
            redirect view: "index"
        }

    }
}
