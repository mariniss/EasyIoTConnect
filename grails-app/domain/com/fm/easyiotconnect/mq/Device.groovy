package com.fm.easyiotconnect.mq

import com.fm.easyiotconnect.User

/**
 * 
 * @author fabiomarini
 *
 */
class Device {
	
	static String TYPE_RASPBERRY = "Raspberry"
	
	User user
	String type
	
	Jack jackProducer
	Jack jackConsumer
	Jack jackStatus
	
	DeviceInfos infos
	
	Boolean toConfigure = false // FIXME: not used with the new interface
	
    static constraints = {
		type (nullable: false, 
			  inList: [TYPE_RASPBERRY])
    }

	static mapping = {
		jackProducer cascade: 'all-delete-orphan'
		jackConsumer cascade: 'all-delete-orphan'
		jackStatus   cascade: 'all-delete-orphan'
		infos		 cascade: 'all-delete-orphan'
	}
	
	def beforeValidate() {
		if(infos == null) {
			infos = new DeviceInfos()
		}
	}
	
	def beforeInsert() {
		if(infos == null) {
			infos = new DeviceInfos()
		}
	}

	String toString() {
		return "${infos.name}: ${type} - ${user}"
	}
}
