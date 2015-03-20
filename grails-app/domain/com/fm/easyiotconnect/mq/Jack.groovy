package com.fm.easyiotconnect.mq


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

	Device device

	String containerStoreId //the identifier of this jack on eiotcServer and eiotcBoServer

    static constraints = {
		queueName		( nullable  : false,
						  blank		: false,
						  unique	: true )

		
		type			( nullable: false,
					  	  inList: [TYPE_PRODUCER, TYPE_CONSUMER, TYPE_STATUS] )
		
		serverContainer	( nullable  : false )

		containerStoreId ( nullable : true )
    }

	String toString() {
		return "${device} (${type} : ${queueName})"
	}
}
