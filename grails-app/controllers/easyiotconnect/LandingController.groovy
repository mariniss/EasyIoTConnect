package easyiotconnect

import com.fm.easyiotconnect.User
import com.fm.easyiotconnect.UserQuestions


class LandingController {

    //def mailService
    def securityService
    def messageCodeResolverService


    def index() {
    }


    def askQuestion = {
        Map result = [error : true]

        if(params.sender && params.message) {
            /*
            def message = mailService.sendMail {
                to "easyiotconnect@gmail.com"
                from "easyiotconnect@gmail.com"
                subject "[ASK] - ${params.sender} : ${params.name?:'-'}"
                body "${params.message}"
            }

            result.error = (message == null)
            */

            UserQuestions question = new UserQuestions()
            question.email = params.
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
