package com.fm.easyiotconnect.mq

/**
 * 
 * @author fabiomarini
 *
 */
class MQServer {

	String name
	String type
	String url
	byte[] certificate
	
	long activeUsers = 0
	
    static constraints = {
		
		name		(nullable 	: false,
				 	 blank		: false,
					 unique		: true)
		
		type		(nullable 	: false,
				 	 blank		: false,
					 inList		: ["A-MQ", "Active MQ"])
		
		url			(nullable 	: false,
					 unique		: true,
					 url		: true)
		
		certificate (type		: 'blob',
					 maxSize	: 102400 ) //100kb
    }
}
