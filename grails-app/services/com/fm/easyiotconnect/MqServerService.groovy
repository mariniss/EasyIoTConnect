package com.fm.easyiotconnect

import com.fm.easyiotconnect.mq.Jack
import com.fm.easyiotconnect.mq.MQServer

import grails.transaction.Transactional


@Transactional
class MqServerService {
	
	/**
	 * 
	 * @param user
	 * @param jack
	 * @return
	 */
    public MQServer identifyMQserver(User user) {
		assert user != null
		//TODO: I can use the user location to identify the closes server
		
		return MQServer.first()
    }
	
	/**
	 * 
	 * @param user
	 * @param server
	 * @return
	 */
	public Map generateQueuesName(User user, MQServer server, String deviceName){
		assert user != null
		assert server != null
		assert deviceName != null
		
		String name = deviceName.replaceAll("[^a-zA-Z0-9]+","")
		
		Map names = [:]

		names.commandProducer = "${Jack.TYPE_COMMAND}_${user.id}_${name}"
		names.commandConsumer = "queue://${names.commandProducer}"

		names.statusProducer = "${Jack.TYPE_STATUS}_${user.id}_${name}"
		names.statusConsumer = "queue://${names.statusProducer}"

		return names
	}
}
