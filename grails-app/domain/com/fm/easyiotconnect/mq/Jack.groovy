package com.fm.easyiotconnect.mq


/**
 * 
 * @author fabiomarini
 *
 */
class Jack {
	
	static String TYPE_COMMAND = "command"
	static String TYPE_STATUS  = "status"

	static String USES_PRODUCE = "producer"
	static String USES_CONSUMER = "consumer"

	String queueName
	String type
	String uses
	MQServer serverContainer

	Device device

	String containerStoreId //the identifier of this jack on eiotcServer and eiotcBoServer

    static constraints = {
		queueName		( nullable  : false,
						  blank		: false,
						  unique	: true )
		
		type			( nullable: false,
					  	  inList: [TYPE_COMMAND, TYPE_STATUS] )

		uses			( nullable: false,
						  inList: [USES_PRODUCE, USES_CONSUMER] )

		serverContainer	( nullable  : false )

		containerStoreId ( nullable : true )
    }

	String toString() {
		return "${device} (${type} - ${uses} : ${queueName})"
	}
}
