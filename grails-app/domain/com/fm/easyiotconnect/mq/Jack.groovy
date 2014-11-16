package com.fm.easyiotconnect.mq

import com.fm.easyiotconnect.User
import com.fm.easyiotconnect.mq.MQServer

/**
 * 
 * @author fabiomarini
 *
 */
class Jack {
	
	static String TYPE_PRODUCER = "producer"
	static String TYPE_CONSUMER = "consumer"
	static String TYPE_STATUS	= "status"
	
	String queueName
	String type
	MQServer serverContainer
	
	static belongsTo = [device: Device]
	
    static constraints = {
		queueName		( nullable  : false,
						  blank		: false,
						  unique	: true)

		
		type			( nullable: false,
					  	  inList: [TYPE_PRODUCER, TYPE_CONSUMER, TYPE_STATUS])
		
		serverContainer	( nullable  : false)
    }

	String toString() {
		return "${device} (${type} : ${queueName})"
	}
}
