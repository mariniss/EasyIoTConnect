package easyiotconnect

import com.fm.easyiotconnect.User


class LandingController {

    def mailService


    def index() {
    }


    def askQuestion = {
        Map result = [error : true]

        if(params.email && params.message) {
            def message = mailService.sendMail {
                to "easyiotconnect@gmail.com"
                from "${params.email}"
                cc "fabio.mariniss@gmail.com"
                subject "Question form ${params.name}"
                body "${params.message}"
            }

            result.error = (message == null)
        }

        redirect view: "index"
    }


    def singUp() {
        def name 	 = params.name
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
            render view:"singup", model:[userData:newUser]
        }
        else {
            def resultCode = securityService.createBaseUser(newUser)

            flash.message = messageCodeResolverService.getMessageByCode(resultCode)
            render view:"index"
        }

        redirect view: "index"
    }
}
