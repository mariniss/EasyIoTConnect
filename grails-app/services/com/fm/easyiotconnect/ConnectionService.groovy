package com.fm.easyiotconnect

import com.fm.easyiotconnect.mq.Device
import com.fm.easyiotconnect.mq.DeviceInfos
import com.fm.easyiotconnect.mq.Jack

import grails.transaction.Transactional
import com.fm.easyiotconnect.MqServerService
import com.fm.easyiotconnect.mq.MQServer

/**
 * 
 * @author fabiomarini
 *
 */
@Transactional
class ConnectionService {

	def mqServerService
	
	/**
	 * 
	 * @param user
	 * @param deviceType
	 * @param deviceName
	 * @return
	 */
    public boolean create(User user, String deviceType, String deviceName) {
		boolean okCreate = false
		
		//Identify the correct A-MQ
		MQServer server = mqServerService.identifyMQserver(user)
		
		//FIXME: check it the device name is unique on users devices
		DeviceInfos infos = new DeviceInfos(name : deviceName)
	
		//Create the queues (names)
		Map names = mqServerService.generateQueuesName(user, server, deviceName)
		
		//Make Jacks, Device and DeviceInfos

		Jack producer = new Jack(queueName 		: names.producer,
								 type			: Jack.TYPE_PRODUCER,
								 serverContainer: server)
		Jack consumer = new Jack(queueName 		: names.consumer,
								 type			: Jack.TYPE_CONSUMER,
								 serverContainer: server)
		Jack status	  = new Jack(queueName 		: names.status,
								 type			: Jack.TYPE_STATUS,
								 serverContainer: server)
		
		Device device = new Device(type 	   : deviceType,
								   jackProducer: producer,
								   jackConsumer: consumer,
								   jackStatus  : status,
								   infos	   : infos,
								   user		   : user)
		
		Device.withTransaction { st ->
			if (device.save()) {
				okCreate = true
			}
			else {
				st.setRollbackOnly()
			}
		}
		
		if(okCreate){
			server.activeUsers ++
			server.save()
		}
		
		return okCreate
	}
}
