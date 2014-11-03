package com.fm.easyiotconnect

import com.fm.easyiotconnect.mq.Jack
import com.fm.easyiotconnect.mq.Device

class DashboardController {

	def springSecurityService
	
    def index() {
		def currentUser = springSecurityService.currentUser

		def userJaks = Jack.findAllByUser(currentUser)
		def device = Device.findByUser(currentUser)
		
		render view:"index", 
			   model:[jaks : userJaks, device : device]
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
