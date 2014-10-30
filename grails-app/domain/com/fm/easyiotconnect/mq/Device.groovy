package com.fm.easyiotconnect.mq

class Device {
	
	String type
	Jack jack
	
    static constraints = {
		type (nullable: false, 
			  inList: ["Raspberry"])
    }
}
