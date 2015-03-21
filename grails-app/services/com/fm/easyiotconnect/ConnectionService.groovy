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
		Jack commandProducer = new Jack(queueName 		: names.commandProducer,
										type			: Jack.TYPE_COMMAND,
										uses  			: Jack.USES_PRODUCE,
										serverContainer : server)

		Jack commandConsumer = new Jack(queueName 		: names.commandConsumer,
										type			: Jack.TYPE_COMMAND,
										uses  			: Jack.USES_CONSUMER,
										serverContainer : server)

		Jack statusProducer = new Jack(queueName 		: names.statusProducer,
									   type				: Jack.TYPE_STATUS,
				                       uses  			: Jack.USES_PRODUCE,
				  					   serverContainer 	: server)

		Jack statusConsumer = new Jack(queueName 	    : names.statusConsumer,
									   type			    : Jack.TYPE_STATUS,
									   uses  		    : Jack.USES_CONSUMER,
									   serverContainer  : server)

		DeviceInfos infos = new DeviceInfos(name : deviceName)

		Device device = new Device(type 	   : deviceType,
								   jackCommandProducer: commandProducer,
								   jackCommandConsumer: commandConsumer,
								   jackStatusProducer : statusProducer,
								   jackStatusConsumer : statusConsumer,
								   infos	   : infos,
								   user		   : user)

		device.validate()
		if(device.hasErrors()){
			return false
		}

		Device.withTransaction { st ->
			Device savedDevice = device.save()
			
			if (savedDevice) {
				boolean okRegister = registerToAuthenticationServer(server, user, device.jacks)

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


	private boolean registerToAuthenticationServer(MQServer mqServer, User user, List<Jack> jacks) {
		AuthenticationServer authenticationServer = mqServer.authenticationServer

		if(authenticationServer.type != AuthenticationServer.TYPE_EIOTC_APP) {
			return eiotcServerStubService.registerJacks(authenticationServer, user, jacks)
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
	boolean deleteDevice(User user, Device device) {
		boolean result = false

		if(user && device) {
			device.delete()

			boolean deleted = deleteFromAuthenticationServer(device.serverContainer, user, device.jacks)
			if (deleted) {
				result = true
			}
			else {
				st.setRollbackOnly()
			}
		}

		return result
	}


	private boolean deleteFromAuthenticationServer(MQServer mqServer, User user, List<Jack> jacks) {
		AuthenticationServer authenticationServer = mqServer.authenticationServer

		if(authenticationServer.type != AuthenticationServer.TYPE_EIOTC_APP) {
			return eiotcServerStubService.deleteJacks(authenticationServer, user, jacks)
		}
		else {
			return  true
		}
	}
}
