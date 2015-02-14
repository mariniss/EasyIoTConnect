package easyiotconnect

import com.fm.easyiotconnect.User
import com.fm.easyiotconnect.UserQuestions


class LandingController {

    def securityService
    def messageCodeResolverService


    def index() {
    }


    def askQuestion = {
        Map result = [error : true]

        if(params.sender && params.message) {
            UserQuestions question = new UserQuestions()
            question.email = params.sender
            question.name  = params.name
            question.text  = params.message

            //FIXME: please move me on a service!!!
            result.error = (question.save())
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
            flash.message = "Validation error: ${newUser.errors}"

            redirect view: "index"
        }
        else {
            def resultCode = securityService.createBaseUser(newUser)

            flash.message = messageCodeResolverService.getMessageByCode(resultCode)

            redirect controller: "dashboard"
        }
    }
}
