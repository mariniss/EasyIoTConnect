package com.fm.easyiotconnect.mq

/**
 * 
 * @author fabiomarini
 *
 */
class MQServer {

	static String TYPE_AMQ 		  	 = "A-MQ"
	static String TYPE_ACTIVE_MQ  	 = "Active MQ"
	
	static String PROVIDER_AWS 	 	 = "AWS"
	static String PROVIDER_RACKSPACE = "RackSpace"
	static String PROVIDER_HEROKU    = "Heroku"
	static String PROVIDER_OPENSHIFT = "OpenShift"
	static String PROVIDER_LOCALHOST = "Localhost"
	
	String name
	String type
	String url
	String provider
	
	long activeUsers = 0
	
    static constraints = {
		
		name		(nullable 	: false,
				 	 blank		: false,
					 unique		: true)
		
		type		(nullable 	: false,
				 	 blank		: false,
					 inList		: [TYPE_AMQ, TYPE_ACTIVE_MQ])
		
		url			(nullable 	: false,
					 unique		: true,
					 url		: true)
		
		provider	(nullable 	: false,
					 blank		: false,
					 inList		: [PROVIDER_AWS, PROVIDER_RACKSPACE, PROVIDER_HEROKU, PROVIDER_OPENSHIFT, PROVIDER_LOCALHOST])
    }
	
	String toString(){
		return "${name} (${type} on ${provider})"
	}
}
