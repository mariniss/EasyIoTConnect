package easyiotconnect

import com.fm.easyiotconnect.ServiceCodes
import com.fm.easyiotconnect.User
import com.fm.easyiotconnect.UserQuestions


class LandingController {

    def springSecurityService
    def securityService
    def messageCodeResolverService


    def index() {
    }


    def askQuestion = {
        if(params.sender && params.message) {
            UserQuestions question = new UserQuestions()
            question.email = params.sender
            question.name  = params.name
            question.text  = params.message

            //FIXME: please move me on a service!!!
            if(question.save(flush: true)) {
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

        def newUser = new User()
        newUser.name 	 = name
        newUser.state	 = country
        newUser.email 	 = email
        newUser.password = password

        newUser.validate()
        if(newUser.hasErrors()){
            flash.alert = [type:"waring", title: "Sorry", message: "There was problem on inserted data!"]

            redirect view: "index"
        }
        else {
            def resultCode = securityService.createBaseUser(newUser)

            def type = resultCode == ServiceCodes.Infos.USER_CREATED ? "success" : "waring"
            def title = resultCode == ServiceCodes.Infos.USER_CREATED ? "Done" : "Sorry"
            def message = messageCodeResolverService.getMessageByCode(resultCode)

            flash.alert = [type:type, title: title, message: message]

            if(resultCode == ServiceCodes.Infos.USER_CREATED) {
                springSecurityService.reauthenticate(newUser.email)

                redirect controller: "dashboard"
            }
            else {
                redirect view: "index"
            }
        }
    }
}
