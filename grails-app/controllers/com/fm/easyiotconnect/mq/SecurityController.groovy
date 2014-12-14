package com.fm.easyiotconnect.mq

import grails.converters.JSON
import grails.rest.Resource;
import grails.rest.RestfulController

/**
 * REST Constroller to manage access to MQ Servers
 * @author fabiomarini
 *
 */
@Resource(uri='/security', formats=['json'])
class SecurityController {
	
	def securityService
	
	static allowedMethods = [connectionSecurityCheck: ["PUT", "POST"],
									 sessionSecurityCheck: ["PUT", "POST"]]

	static responseFormats = ['json']
	
	/**
	 * Security check to create mq connection
	 */
    def connectionSecurityCheck = { 
		def username  = params.username
		def password  = params.password
		
		def securityResponse
		
		if(securityService.authenticateConnection(username, password))
		{	
			securityResponse =  SecurityResponseBuilder.buildPositiveResponse()
		}
		else
		{
			securityResponse = SecurityResponseBuilder.buildNegativeResponse()
		}
		
		render securityResponse as JSON
	}
	
	/**
	 * Security check to create mq session
	 */
	def sessionSecurityCheck = {
		def username  = params.username
		def queueName = params.queueName
		
		def securityResponse
		
		if(securityService.authenticateSession(username, queueName))
		{
			securityResponse =  SecurityResponseBuilder.buildPositiveResponse()
		}
		else
		{
			securityResponse = SecurityResponseBuilder.buildNegativeResponse()
		}
		
		render securityResponse as JSON
	}
}
