package com.fm.easyiotconnect.mq

import grails.rest.Resource;
import grails.rest.RestfulController

@Resource(uri='/security', formats=['json'])
class SecurityController { //extends RestfulController {
	
	static allowedMethods = [baseSecurityCheck: ["POST"]]
	static responseFormats = ['json']
	
    def baseSecurityCheck() { 
		def username = params.username
		def password = params.password
		
		if("fabio" == username && "marini" == password)
		{
			response.status = 200;
		}
		else
		{
			response.status = 503;
		}
	}
}
