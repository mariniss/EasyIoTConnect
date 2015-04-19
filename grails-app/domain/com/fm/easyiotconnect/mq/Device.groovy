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

	Jack jackW1DataRequestProducer  //webapp
	Jack jackW1DataRequestConsumer  //device

	Jack jackW1DataProducer  		//device
	Jack jackW1DataConsumer  		//webapp

	DeviceInfos infos
	
	Boolean toConfigure = false // FIXME: not used with the new interface
	
    static constraints = {
		type (nullable: false, 
			  inList: [TYPE_RASPBERRY])
    }

	static mapping = {
		jackCommandProducer 		cascade: 'all-delete-orphan'
		jackCommandConsumer 		cascade: 'all-delete-orphan'
		jackStatusProducer  		cascade: 'all-delete-orphan'
		jackStatusConsumer  		cascade: 'all-delete-orphan'
		jackW1DataRequestProducer	cascade: 'all-delete-orphan'
		jackW1DataRequestConsumer 	cascade: 'all-delete-orphan'
		jackW1DataProducer			cascade: 'all-delete-orphan'
		jackW1DataConsumer			cascade: 'all-delete-orphan'
		infos		 				cascade: 'all-delete-orphan'
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

		if(jackCommandProducer) 		jacks += jackCommandProducer
		if(jackCommandConsumer) 		jacks += jackCommandConsumer
		if(jackStatusProducer)  		jacks += jackStatusProducer
		if(jackStatusConsumer)  		jacks += jackStatusConsumer
		if(jackW1DataRequestProducer) 	jacks += jackW1DataRequestProducer
		if(jackW1DataRequestConsumer) 	jacks += jackW1DataRequestConsumer
		if(jackW1DataProducer) 		  	jacks += jackW1DataProducer
		if(jackW1DataConsumer) 		  	jacks += jackW1DataConsumer

		return jacks
	}


	MQServer getServerContainer() {
		if(!jacks.isEmpty()) {
			return jacks.first().serverContainer
		}

		return null
	}


	boolean w1ThermEnabled() {
		boolean enabled = false
		if(infos && infos.gpio4Visible
				 && infos.gpio4Type == DeviceInfos.GPIO_TYPE_W1_THERM) {
			enabled = true
		}

		return enabled
	}
}
