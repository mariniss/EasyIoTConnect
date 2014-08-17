package com.fm.easyiotconnect

/**
 * 
 * @author fabiomarini
 *
 */
class PortalController {

	/*
	 * Security service
	 */
	def securityService
	
	/*
	 * Message code resolver service
	 */
	def messageCodeResolverService

	def index() {
		render view:"index"
	}

    def singUp() { 
		render view:"singup"
	}
	
	def singUpCheck() {
		
		//reading form data
		def name 		= params.name
		def lastName 	= params.lastName
		def borndate 	= params.borndate
		def gender		= params.gender
		def adress		= params.adress
		def phone 		= params.phone
		def email		= params.email
		def username	= params.username
		def password	= params.password
		
		//Checking data
		def newUser = new User()
		newUser.name 		= name
		newUser.lastname 	= lastName
		newUser.bornDate	= DateUtils.createDate(18, 10, 1986) //FIXME:
		newUser.gender		= gender
		newUser.adress		= adress
		newUser.phone		= phone
		newUser.email		= email
		newUser.username	= username
		newUser.password	= password

		if(!newUser.validate()){
			//TODO: add parameters

			flash.message = "Validation error"
			render view:"singup", model:[userData:newUser]
		}
		else {
			def resultCode = securityService.createBaseUser(newUser)
			
			flash.message = messageCodeResolverService.getMessageByCode(resultCode)
			render view:"index"
		}
	}
}
