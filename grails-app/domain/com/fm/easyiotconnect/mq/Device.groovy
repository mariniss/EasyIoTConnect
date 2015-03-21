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
	
	Jack jackCommandProducer //webapp
	Jack jackCommandConsumer //device
	Jack jackStatusProducer  //device
	Jack jackStatusConsumer  //webapp

	DeviceInfos infos
	
	Boolean toConfigure = false // FIXME: not used with the new interface
	
    static constraints = {
		type (nullable: false, 
			  inList: [TYPE_RASPBERRY])
    }

	static mapping = {
		jackCommandProducer cascade: 'all-delete-orphan'
		jackCommandConsumer cascade: 'all-delete-orphan'
		jackStatusProducer  cascade: 'all-delete-orphan'
		jackStatusConsumer  cascade: 'all-delete-orphan'
		infos		 		cascade: 'all-delete-orphan'
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

	List<Jack> getJacks() {
		List<Jack> jacks = []

		if(jackCommandProducer) jacks += jackCommandProducer
		if(jackCommandConsumer) jacks += jackCommandConsumer
		if(jackStatusProducer)   jacks += jackStatusProducer
		if(jackStatusConsumer)   jacks += jackStatusConsumer

		return jacks
	}


	MQServer getServerContainer() {
		if(!jacks.isEmpty()) {
			return jacks.first().serverContainer
		}

		return null
	}
}
