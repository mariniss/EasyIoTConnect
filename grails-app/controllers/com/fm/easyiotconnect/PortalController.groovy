package com.fm.easyiotconnect

/**
 * 
 * @author fabiomarini
 *
 */
class PortalController {

	def securityService
	def messageCodeResolverService
	def mailService
	
	def index() {
		render view:"index"
	}
	
	
    def howItWorks() { 
		render view:"howItWorks"
	}
	
	
	def contact() {
		render view:"contact"
	}

	
	def login() {
		render view:"login"
	}
	
	
	def singUp() {
		render view:"singup"
	}
	

	def singUpCheck() {
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
	}
	
	
	def askQuestion() {
		//TODO: add params validation

		mailService.sendMail {
		   to "easyiotconnect@gmail.com"
		   from "${params.email}"
		   cc "fabio.mariniss@gmail.com"
		   subject "Question form ${params.name}"
		   body "${params.question}"
		 }
		
		render view:"index"
	}
}
