package com.fm.easyiotconnect.mq

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.RedeliveryPolicy;
import org.fm.pimq.IPinMessage;
import org.fm.pimq.PinMQ;
import org.fm.pimq.PinStateMQ;
import org.fm.pimq.impl.PinMessageImpl
import javax.jms.*;

@Transactional(readOnly = true)
class DeviceController {

    static scaffold = true
	
	def springSecurityService
	def deviceService
	
	def sendCommand() {
		def currentUser = springSecurityService.currentUser
		
		def device = Device.get(params.int('idDevice'))
		int pin    = params.int('pin')
		int status = params.int('status')

		assert device.user == currentUser
		
		PinMQ mqPin = new PinMQ(pin)
		PinStateMQ mqState = status == 0 ? PinStateMQ.LOW : PinStateMQ.HIGH

		IPinMessage command = new PinMessageImpl(mqPin, mqState)
		deviceService.sendCommand(device, command)

		redirect(controller: "dashboard")
	}
}
