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

	def grailsApplication
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

		int maxDevices = grailsApplication.config.eiotc.device.max
		if(Device.countByUser(user) >= maxDevices) {
			return false
		}

		def query = Device.where { user == user && infos.name == deviceName }
		if(query.count() != 0) {
			return false
		}

		//Identify the correct A-MQ
		MQServer server = mqServerService.identifyMQserver(user)

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

		DeviceInfos infos = new DeviceInfos(name : deviceName)

		Device device = new Device(type 	   : deviceType,
								   jackProducer: producer,
								   jackConsumer: consumer,
								   jackStatus  : status,
								   infos	   : infos,
								   user		   : user)

		device.validate()
		if(device.hasErrors()){
			return false
		}

		Device.withTransaction { st ->
			Device savedDevice = device.save()
			
			if (savedDevice) {
				boolean okRegister = registerToAuthenticationServer(server, user, [producer, consumer, status])

				okCreate = okRegister
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

	/**
	 *
	 * @param device
	 * @return
	 */
	boolean deleteDevice(Device device) {

		//TODO: remove all linked data
		device.delete()

		return true
	}
}
