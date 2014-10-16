package com.fm.easyiotconnect.mq

import com.fm.easyiotconnect.User

class Jack {

	String queueName
	
	static belongsTo = [user: User]
	
    static constraints = {
		queueName	( nullable  : false,
					  blank		: false,
					  unique	: true)
		
		user		( nullable	: false)
    }
	
	@Override
	String toString()
	{
		return "${queueName}"
	}
}
