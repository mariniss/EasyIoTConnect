package com.fm.easyiotconnect
import com.fm.easyiotconnect.mq.*
import grails.transaction.Transactional

/**
 * 
 * @author fabiomarini
 *
 */
@Transactional
class ConnectionService {

	def mqServerService
	def eiotcServerStubService

	/**
	 * 
	 * @param user
	 * @param deviceType
	 * @param deviceName
	 * @return
	 */
    boolean create(User user, String deviceType, String deviceName) {
		boolean okCreate = false

		log.error(">>> Creations....")

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
			boolean  childrenSaved = infos.save()    &&
						    producer.save() &&  
							consumer.save() &&
							status.save()
			
			if (childrenSaved && device.save()) {
				okCreate = true
			}
			else {
				st.setRollbackOnly()
			}
		}

		boolean okRegister = registerToAuthenticationServer(server, user, [producer, consumer, status])
		
		if(okCreate && okRegister){
			server.activeUsers ++
			server.save()
		}
		
		return okCreate
	}

	/**
	 *
	 * @param mqServer
	 * @param user
	 * @param jacks
	 * @return
	 */
	boolean registerToAuthenticationServer(MQServer mqServer, User user, List<Jack> jacks) {
		AuthenticationServer authenticationServer = mqServer.authenticationServer

		if(authenticationServer.type != AuthenticationServer.TYPE_EIOTC_APP) {
			return eiotcServerStubService.registerUser(authenticationServer, user, jacks)
		}
		else {
			return  true
		}
	}

	boolean deleteDevice(Device device) {

		//TODO: remove all linked data

		return device.delete()
	}
}
