package com.fm.easyiotconnect.mq

import com.fm.easyiotconnect.User

/**
 * 
 * @author fabiomarini
 *
 */
class Device {
	
	static String TYPE_RASPBERRY = "Raspberry"
	
	String type
	
	Jack jackProducer
	Jack jackConsumer
	Jack jackStatus
	
	static belongsTo = [user: User]
	
    static constraints = {
		type (nullable: false, 
			  inList: [TYPE_RASPBERRY])
    }
}
