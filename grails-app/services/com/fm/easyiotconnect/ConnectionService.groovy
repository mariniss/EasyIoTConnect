package com.fm.easyiotconnect
import com.fm.easyiotconnect.mq.*
import grails.transaction.Transactional
import grails.util.Environment
import org.apache.commons.lang.StringUtils

import java.sql.Time

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


    boolean createDevice(User user, String deviceType, String deviceName) {
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

		Jack w1DataRequestProducer = new Jack(queueName 		: names.w1DataRequestProducer,
										  	  type				: Jack.TYPE_W1_REQUEST,
											  uses  			: Jack.USES_PRODUCE,
											  serverContainer	: server)

		Jack w1DataRequestConsumer = new Jack(queueName 		: names.w1DataRequestConsumer,
											  type				: Jack.TYPE_W1_REQUEST,
											  uses  			: Jack.USES_CONSUMER,
											  serverContainer 	: server)

		Jack w1DataProducer = new Jack(queueName 		: names.w1DataProducer,
									   type				: Jack.TYPE_W1,
									   uses  			: Jack.USES_PRODUCE,
				 					   serverContainer 	: server)

		Jack w1DataConsumer = new Jack(queueName 	    : names.w1DataConsumer,
									   type			    : Jack.TYPE_W1,
									   uses  		    : Jack.USES_CONSUMER,
									   serverContainer  : server)

		DeviceInfos infos = new DeviceInfos(name : deviceName)

		Device device = new Device(type 	   : deviceType,
								   jackCommandProducer: commandProducer,
								   jackCommandConsumer: commandConsumer,
								   jackStatusProducer : statusProducer,
								   jackStatusConsumer : statusConsumer,
								   jackW1DataRequestConsumer: w1DataRequestConsumer,
								   jackW1DataRequestProducer: w1DataRequestProducer,
								   jackW1DataConsumer: w1DataConsumer,
								   jackW1DataProducer: w1DataProducer,
								   infos	   : infos,
								   user		   : user)

		device.validate()
		if(device.hasErrors()){
			return false
		}

		Device.withTransaction { st ->
			Device savedDevice = device.save()
			
			if (savedDevice) {
				//DEBUG: development environment, add a configuration to skip the connection instead of this check
				if(Environment.current != Environment.DEVELOPMENT) {
					okCreate = registerToAuthenticationServer(server, user, device.jacks)
				}
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


	boolean deleteDevice(User user, Device device) {
		boolean result = false

		if(user && device) {
			Device.withTransaction {
				device.infos.allTimedCommands?.each { it.delete() }
				device.infos.delete()
				device.delete()
			}

			//DEBUG: development environment, add a configuration to skip the connection instead of this check
			if(Environment.current == Environment.DEVELOPMENT) {
				result = true
			}
			else {
				boolean deleted = deleteFromAuthenticationServer(device.serverContainer, user, device.jacks)
				if (deleted) {
					result = true
				}
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


	boolean updateDevice(Device device, Map deviceParams) {
		boolean okUpdate = false

		if(device && deviceParams) {

			//Actually the name is unmodifiable
			//device.infos.name = deviceParams.name

			device.infos.gpio0Name = deviceParams.gpio0name
			device.infos.gpio1Name = deviceParams.gpio1name
			device.infos.gpio2Name = deviceParams.gpio2name
			device.infos.gpio3Name = deviceParams.gpio3name
			device.infos.gpio4Name = deviceParams.gpio4name
			device.infos.gpio5Name = deviceParams.gpio5name
			device.infos.gpio6Name = deviceParams.gpio6name
			device.infos.gpio7Name = deviceParams.gpio7name
			device.infos.gpio8Name = deviceParams.gpio8name
			device.infos.gpio9Name = deviceParams.gpio9name
			device.infos.gpio10Name = deviceParams.gpio10name
			device.infos.gpio11Name = deviceParams.gpio11name
			device.infos.gpio12Name = deviceParams.gpio12name
			device.infos.gpio13Name = deviceParams.gpio13name
			device.infos.gpio14Name = deviceParams.gpio14name
			device.infos.gpio15Name = deviceParams.gpio15name
			device.infos.gpio16Name = deviceParams.gpio16name
			device.infos.gpio17Name = deviceParams.gpio17name
			device.infos.gpio18Name = deviceParams.gpio18name
			device.infos.gpio19Name = deviceParams.gpio19name
			device.infos.gpio20Name = deviceParams.gpio20name


			device.infos.gpio0Visible = StringUtils.isNotBlank(device.infos.gpio0Name)
			device.infos.gpio1Visible = StringUtils.isNotBlank(device.infos.gpio1Name)
			device.infos.gpio2Visible = StringUtils.isNotBlank(device.infos.gpio2Name)
			device.infos.gpio3Visible = StringUtils.isNotBlank(device.infos.gpio3Name)
			device.infos.gpio4Visible = StringUtils.isNotBlank(device.infos.gpio4Name)
			device.infos.gpio5Visible = StringUtils.isNotBlank(device.infos.gpio5Name)
			device.infos.gpio6Visible = StringUtils.isNotBlank(device.infos.gpio6Name)
			device.infos.gpio7Visible = StringUtils.isNotBlank(device.infos.gpio7Name)
			device.infos.gpio8Visible = StringUtils.isNotBlank(device.infos.gpio8Name)
			device.infos.gpio9Visible = StringUtils.isNotBlank(device.infos.gpio9Name)
			device.infos.gpio10Visible = StringUtils.isNotBlank(device.infos.gpio10Name)
			device.infos.gpio11Visible = StringUtils.isNotBlank(device.infos.gpio11Name)
			device.infos.gpio12Visible = StringUtils.isNotBlank(device.infos.gpio12Name)
			device.infos.gpio13Visible = StringUtils.isNotBlank(device.infos.gpio13Name)
			device.infos.gpio14Visible = StringUtils.isNotBlank(device.infos.gpio14Name)
			device.infos.gpio15Visible = StringUtils.isNotBlank(device.infos.gpio15Name)
			device.infos.gpio16Visible = StringUtils.isNotBlank(device.infos.gpio16Name)
			device.infos.gpio17Visible = StringUtils.isNotBlank(device.infos.gpio17Name)
			device.infos.gpio18Visible = StringUtils.isNotBlank(device.infos.gpio18Name)
			device.infos.gpio19Visible = StringUtils.isNotBlank(device.infos.gpio19Name)
			device.infos.gpio20Visible = StringUtils.isNotBlank(device.infos.gpio20Name)


			device.infos.gpio0Type = deviceParams.gpio0type
			device.infos.gpio1Type = deviceParams.gpio1type
			device.infos.gpio2Type = deviceParams.gpio2type
			device.infos.gpio3Type = deviceParams.gpio3type
			device.infos.gpio4Type = deviceParams.gpio4type
			device.infos.gpio5Type = deviceParams.gpio5type
			device.infos.gpio6Type = deviceParams.gpio6type
			device.infos.gpio7Type = deviceParams.gpio7type
			device.infos.gpio8Type = deviceParams.gpio8type
			device.infos.gpio9Type = deviceParams.gpio9type
			device.infos.gpio10Type = deviceParams.gpio10type
			device.infos.gpio11Type = deviceParams.gpio11type
			device.infos.gpio12Type = deviceParams.gpio12type
			device.infos.gpio13Type = deviceParams.gpio13type
			device.infos.gpio14Type = deviceParams.gpio14type
			device.infos.gpio15Type = deviceParams.gpio15type
			device.infos.gpio16Type = deviceParams.gpio16type
			device.infos.gpio17Type = deviceParams.gpio17type
			device.infos.gpio18Type = deviceParams.gpio18type
			device.infos.gpio19Type = deviceParams.gpio19type
			device.infos.gpio20Type = deviceParams.gpio20type

			Device.withTransaction {
				if(device.validate() && device.infos.validate()) {
					updateTimedGpios(device, deviceParams)

					device.infos.save()
					device.save()

					okUpdate = true
				}
				else {
					okUpdate = false
				}
			}
		}

		return okUpdate
	}


	private void updateTimedGpios(Device device, Map deviceParams) {
		if(device && deviceParams) {
			int gpioId = 0

			if(device.infos.gpio0Type == DeviceInfos.GPIO_TYPE_TIMER) {
				updateTimedGpio(device, deviceParams, gpioId++)
			} else {
				deleteTimedGpio(device, gpioId++)
			}
			if(device.infos.gpio1Type == DeviceInfos.GPIO_TYPE_TIMER) {
				updateTimedGpio(device, deviceParams, gpioId++)
			} else {
				deleteTimedGpio(device, gpioId++)
			}
			if(device.infos.gpio2Type == DeviceInfos.GPIO_TYPE_TIMER) {
				updateTimedGpio(device, deviceParams, gpioId++)
			} else {
				deleteTimedGpio(device, gpioId++)
			}
			if(device.infos.gpio3Type == DeviceInfos.GPIO_TYPE_TIMER) {
				updateTimedGpio(device, deviceParams, gpioId++)
			} else {
				deleteTimedGpio(device, gpioId++)
			}
			if(device.infos.gpio4Type == DeviceInfos.GPIO_TYPE_TIMER) {
				updateTimedGpio(device, deviceParams, gpioId++)
			} else {
				deleteTimedGpio(device, gpioId++)
			}
			if(device.infos.gpio5Type == DeviceInfos.GPIO_TYPE_TIMER) {
				updateTimedGpio(device, deviceParams, gpioId++)
			} else {
				deleteTimedGpio(device, gpioId++)
			}
			if(device.infos.gpio6Type == DeviceInfos.GPIO_TYPE_TIMER) {
				updateTimedGpio(device, deviceParams, gpioId++)
			} else {
				deleteTimedGpio(device, gpioId++)
			}
			if(device.infos.gpio7Type == DeviceInfos.GPIO_TYPE_TIMER) {
				updateTimedGpio(device, deviceParams, gpioId++)
			} else {
				deleteTimedGpio(device, gpioId++)
			}
			if(device.infos.gpio8Type == DeviceInfos.GPIO_TYPE_TIMER) {
				updateTimedGpio(device, deviceParams, gpioId++)
			} else {
				deleteTimedGpio(device, gpioId++)
			}
			if(device.infos.gpio9Type == DeviceInfos.GPIO_TYPE_TIMER) {
				updateTimedGpio(device, deviceParams, gpioId++)
			} else {
				deleteTimedGpio(device, gpioId++)
			}
			if(device.infos.gpio10Type == DeviceInfos.GPIO_TYPE_TIMER){
				updateTimedGpio(device, deviceParams, gpioId++)
			}
			else  {
				deleteTimedGpio(device, gpioId++)
			}
			if(device.infos.gpio11Type == DeviceInfos.GPIO_TYPE_TIMER){
				updateTimedGpio(device, deviceParams, gpioId++)
			}
			else  {
				deleteTimedGpio(device, gpioId++)
			}
			if(device.infos.gpio12Type == DeviceInfos.GPIO_TYPE_TIMER){
				updateTimedGpio(device, deviceParams, gpioId++)
			}
			else  {
				deleteTimedGpio(device, gpioId++)
			}
			if(device.infos.gpio13Type == DeviceInfos.GPIO_TYPE_TIMER){
				updateTimedGpio(device, deviceParams, gpioId++)
			}
			else  {
				deleteTimedGpio(device, gpioId++)
			}
			if(device.infos.gpio14Type == DeviceInfos.GPIO_TYPE_TIMER){
				updateTimedGpio(device, deviceParams, gpioId++)
			}
			else  {
				deleteTimedGpio(device, gpioId++)
			}
			if(device.infos.gpio15Type == DeviceInfos.GPIO_TYPE_TIMER){
				updateTimedGpio(device, deviceParams, gpioId++)
			}
			else  {
				deleteTimedGpio(device, gpioId++)
			}
			if(device.infos.gpio16Type == DeviceInfos.GPIO_TYPE_TIMER){
				updateTimedGpio(device, deviceParams, gpioId++)
			}
			else  {
				deleteTimedGpio(device, gpioId++)
			}
			if(device.infos.gpio17Type == DeviceInfos.GPIO_TYPE_TIMER){
				updateTimedGpio(device, deviceParams, gpioId++)
			}
			else  {
				deleteTimedGpio(device, gpioId++)
			}
			if(device.infos.gpio18Type == DeviceInfos.GPIO_TYPE_TIMER){
				updateTimedGpio(device, deviceParams, gpioId++)
			}
			else  {
				deleteTimedGpio(device, gpioId++)
			}
			if(device.infos.gpio19Type == DeviceInfos.GPIO_TYPE_TIMER){
				updateTimedGpio(device, deviceParams, gpioId++)
			}
			else  {
				deleteTimedGpio(device, gpioId++)
			}
			if(device.infos.gpio20Type == DeviceInfos.GPIO_TYPE_TIMER){
				updateTimedGpio(device, deviceParams, gpioId)
			}
			else  {
				deleteTimedGpio(device, gpioId)
			}
		}
	}


	private void updateTimedGpio(Device device, Map deviceParams, int gprioId) {
		def sendOnAt  = deviceParams["dp_${device.id}_${gprioId}_send_on_at"]

		def sendOffAt  = deviceParams["sl_${device.id}_${gprioId}_send_off_at"]
		def repeatType = deviceParams["sl_${device.id}_${gprioId}_timer_repeat"]

		def repeatOnMo = deviceParams["cb_${device.id}_${gprioId}_timer_repeat_mo"]
        def repeatOnTu = deviceParams["cb_${device.id}_${gprioId}_timer_repeat_tu"]
		def repeatOnWe = deviceParams["cb_${device.id}_${gprioId}_timer_repeat_we"]
        def repeatOnTh = deviceParams["cb_${device.id}_${gprioId}_timer_repeat_th"]
		def repeatOnFr = deviceParams["cb_${device.id}_${gprioId}_timer_repeat_fr"]
		def repeatOnSa = deviceParams["cb_${device.id}_${gprioId}_timer_repeat_sa"]
		def repeatOnSu = deviceParams["cb_${device.id}_${gprioId}_timer_repeat_su"]

		def timeZoneName = deviceParams["timer_command_${device.id}_${gprioId}_timezone"]

		TimedCommand timedCommandOn  = device.infos.getTimedCommand(gprioId, TimedCommand.TYPE_SEND_ON)
		TimedCommand timedCommandOff = device.infos.getTimedCommand(gprioId, TimedCommand.TYPE_SEND_OFF)
		if(sendOnAt && sendOnAt.size() > 0) {
			if (timedCommandOn == null) {
				timedCommandOn = new TimedCommand(gpioId: gprioId, deviceInfos: device.infos, type: TimedCommand.TYPE_SEND_ON)
			}

			Date date = (new Date()).parse('dd/MMM/yyyy hh:mm a', sendOnAt)

			//Calendar dateCalTZ = Calendar.getInstance(TimeZone.getTimeZone(timeZoneName))
			//dateCalTZ.setTime(date)

			timedCommandOn.executionTime		 = date

			timedCommandOn.recurringType		 = repeatType
			timedCommandOn.recurringOnMonday	 = (repeatOnMo == 'on')
			timedCommandOn.recurringOnTuesday	 = (repeatOnTu == 'on')
			timedCommandOn.recurringOnWednesday  = (repeatOnWe == 'on')
			timedCommandOn.recurringOnThursday	 = (repeatOnTh == 'on')
			timedCommandOn.recurringOnFriday	 = (repeatOnFr == 'on')
			timedCommandOn.recurringOnSaturday	 = (repeatOnSa == 'on')
			timedCommandOn.recurringOnSunday	 = (repeatOnSu == 'on')

			timedCommandOn.sendOffAfter 		 = sendOffAt

			timedCommandOn.timeZoneName			 = timeZoneName

			if(timedCommandOn.sendOffAfter != SendOffValues.NONE) {
				if (timedCommandOff == null) {
					timedCommandOff = new TimedCommand(gpioId: gprioId, deviceInfos: device.infos, type: TimedCommand.TYPE_SEND_OFF)
				}

				Calendar executionTime = timedCommandOn.executionTime.toCalendar()
				SendOffValues sendOffValue = sendOffAt

				executionTime.add(sendOffValue.type, sendOffValue.value)

				timedCommandOff.executionTime		 = executionTime.time
				timedCommandOff.recurringType		 = timedCommandOn.recurringType
				timedCommandOff.recurringOnMonday	 = timedCommandOn.recurringOnMonday
				timedCommandOff.recurringOnTuesday	 = timedCommandOn.recurringOnTuesday
				timedCommandOff.recurringOnWednesday = timedCommandOn.recurringOnWednesday
				timedCommandOff.recurringOnThursday	 = timedCommandOn.recurringOnThursday
				timedCommandOff.recurringOnFriday	 = timedCommandOn.recurringOnFriday
				timedCommandOff.recurringOnSaturday	 = timedCommandOn.recurringOnSaturday
				timedCommandOff.recurringOnSunday	 = timedCommandOn.recurringOnSunday

				timedCommandOff.sendOffAfter 		 = SendOffValues.NONE

				timedCommandOff.timeZoneName		 = timedCommandOn.timeZoneName

				TimedCommand.withTransaction {
					timedCommandOff.save()
					timedCommandOn.save()
				}
			}
			else {
				TimedCommand.withTransaction {
					timedCommandOff?.delete()
					timedCommandOn.save()
				}
			}
		}
		else {
			timedCommandOn?.delete()
			timedCommandOff?.delete()
		}
	}


	private void deleteTimedGpio(Device device, int gprioId) {
		TimedCommand timedCommandOn = device.infos.getTimedCommand(gprioId, TimedCommand.TYPE_SEND_ON)
		if(timedCommandOn){
			timedCommandOn.delete()
		}

		TimedCommand timedCommandOff = device.infos.getTimedCommand(gprioId, TimedCommand.TYPE_SEND_OFF)
		if(timedCommandOff){
			timedCommandOff.delete()
		}
	}
}
