package com.fm.easyiotconnect

class DashboardController {

    def index() {
		flash.message = "test Message"
		
		render view:"index"
	}
	
	def createJack() {
		
		//Identify the correct A-MQ
		
		//Create the user
		
		//Get the certificate
		
		//Create the queues (names)
		
		//Give the rule to the new A-MQ user for the new pair queues
		
		render view:"createJack"
	}
}
