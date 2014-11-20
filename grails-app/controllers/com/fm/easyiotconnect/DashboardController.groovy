package com.fm.easyiotconnect

import com.fm.easyiotconnect.mq.Jack
import com.fm.easyiotconnect.mq.Device
import org.fm.pimq.IPinMessage
import org.fm.pimq.PinMQ
import org.fm.pimq.PinStateMQ
import org.fm.pimq.impl.PinMessageImpl

/**
 * 
 * @author fabiomarini
 *
 */
class DashboardController {

	def springSecurityService
	def connectionService
	def deviceService
	def grailsApplication
	
    def index() {
		def currentUser = springSecurityService.currentUser

		def devices = Device.findAllByUser(currentUser)
		def toConfigure = devices.any { it.toConfigure == true } != null
		
		render view:"index", 
			   model:[ devices 	  : devices, 
				   	   toConfigure: devices.size() == 0? false : toConfigure]
	}
	
	def createDevice() {
		render view:"createDevice"
	}
	
	def saveDevice() {
		String deviceName = params.name
		String deviceType = params.type
		
		def currentUser = springSecurityService.currentUser
		
		boolean okCreate = connectionService.create(currentUser, deviceType, deviceName)
		
		if(okCreate) {
			flash.message = "Great! Device created successful!"
		}
		else {
			flash.message = "Sorry! There was a problem creating device"
		}
		
		redirect action:"index"
	}
	
	def configure() {
		def deviceId = params.id
		
		def currentUser = springSecurityService.currentUser
		
		Device device = Device.findByIdAndUser(deviceId, currentUser)
		if(device == null) {
			flash.message = "Error, device not found!"
		}
		
		[ user: currentUser,
		  device: device,
		  pimqUrl: grailsApplication.config.eiotc.device.configure.pimqUrl ]
	}
	
	def manage() {
		def deviceId = params.id
		
		def currentUser = springSecurityService.currentUser
		
		Device device = null
		List<Device> devices = []
	
		if(deviceId != null) {
			device = Device.findByIdAndUser(deviceId, currentUser)	
		}
		
		if(device == null) {
			devices = Device.findAllByUser(currentUser)
		}
		
		[ device: device, devices: devices ]
	}
	
	def updateDeviceInfo() {
		def deviceId = params.id
		
		def currentUser = springSecurityService.currentUser
		
		Device device = Device.findByIdAndUser(deviceId, currentUser)
		if(device == null) {
			flash.message = "Error, device not found!"
		}
		else {
			device.infos.name = params.name
			
			device.infos.gpio0Name  = params.gpio0Name
			device.infos.gpio1Name  = params.gpio1Name
			device.infos.gpio2Name  = params.gpio2Name
			device.infos.gpio3Name  = params.gpio3Name
			device.infos.gpio4Name  = params.gpio4Name
			device.infos.gpio5Name  = params.gpio5Name
			device.infos.gpio6Name  = params.gpio6Name
			device.infos.gpio7Name  = params.gpio7Name
			device.infos.gpio8Name  = params.gpio8Name
			device.infos.gpio9Name  = params.gpio9Name
			device.infos.gpio10Name = params.gpio10Name
			device.infos.gpio11Name = params.gpio11Name
			device.infos.gpio12Name = params.gpio12Name
			device.infos.gpio13Name = params.gpio13Name
			device.infos.gpio14Name = params.gpio14Name
			device.infos.gpio15Name = params.gpio15Name
			device.infos.gpio16Name = params.gpio16Name
			device.infos.gpio17Name = params.gpio17Name

			device.infos.gpio0Visible  = params.gpio0Visible
			device.infos.gpio1Visible  = params.gpio1Visible
			device.infos.gpio2Visible  = params.gpio2Visible
			device.infos.gpio3Visible  = params.gpio3Visible
			device.infos.gpio4Visible  = params.gpio4Visible
			device.infos.gpio5Visible  = params.gpio5Visible
			device.infos.gpio6Visible  = params.gpio6Visible
			device.infos.gpio7Visible  = params.gpio7Visible
			device.infos.gpio8Visible  = params.gpio8Visible
			device.infos.gpio9Visible  = params.gpio9Visible
			device.infos.gpio10Visible = params.gpio10Visible
			device.infos.gpio11Visible = params.gpio11Visible
			device.infos.gpio12Visible = params.gpio12Visible
			device.infos.gpio13Visible = params.gpio13Visible
			device.infos.gpio14Visible = params.gpio14Visible
			device.infos.gpio15Visible = params.gpio15Visible
			device.infos.gpio16Visible = params.gpio16Visible
			device.infos.gpio17Visible = params.gpio17Visible
			
			if(device.save(flush:true)) {
				flash.message = "Great! Device update successfully"
			}
			else {
				flash.message = "Sorry! There were problems updating device"
			}
		}
		
		redirect action:"index"
	}
	
	def remote() {
		def deviceId = params.id
		
		def currentUser = springSecurityService.currentUser
		
		Device device = null
		List<Device> devices = []
	
		if(deviceId != null) {
			device = Device.findByIdAndUser(deviceId, currentUser)	
		}
		
		if(device == null) {
			devices = Device.findAllByUser(currentUser)
		}
		
		[ device: device, devices: devices ]
	}
	
	def sendCommand() {
		def deviceId = params.id
		
		def currentUser = springSecurityService.currentUser
		
		Device device = Device.findByIdAndUser(deviceId, currentUser)
		if(device == null) {
			flash.message = "Error, device not found!"
		}
		else {
			int pin    = params.int('pin')
			int status = params.int('status')
			
			PinMQ mqPin = new PinMQ(pin)
			PinStateMQ mqState = status == 0 ? PinStateMQ.LOW : PinStateMQ.HIGH
			IPinMessage command = new PinMessageImpl(mqPin, mqState)

			if(deviceService.sendCommand(device, command)) {
				flash.message = "Great! Command sent successful!"
			}
			else {
				flash.message = "Sorry! Command not sent"
			}
		}
		
		redirect action:"remote", params: [id: deviceId]
	}
}
