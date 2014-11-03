package com.fm.easyiotconnect.mq

import com.fm.easyiotconnect.User
import com.fm.easyiotconnect.mq.MQServer

/**
 * 
 * @author fabiomarini
 *
 */
class Jack {

	String producerQueueName
	String consumerQueueName
	String statusQueueName
	
	static belongsTo = [user: User]

	MQServer serverContainer
	
    static constraints = {
		producerQueueName	( nullable  : false,
							  blank		: false,
							  unique	: true)
		
		consumerQueueName	( nullable  : false,
							  blank		: false,
							  unique	: true)
		
		statusQueueName		( nullable  : false,
							  blank		: false,
							  unique	: true)
		
		user				( nullable	: false)

		serverContainer		( nullable  : false)
    }

	String toString() {
		return "${user} (${consumerQueueName})"
	}
}
