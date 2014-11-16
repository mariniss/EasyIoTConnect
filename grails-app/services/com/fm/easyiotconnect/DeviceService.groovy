package com.fm.easyiotconnect

import com.fm.easyiotconnect.mq.Device
import com.fm.easyiotconnect.mq.Jack
import com.fm.easyiotconnect.mq.MQServer
import grails.transaction.Transactional


import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

import org.apache.activemq.ActiveMQConnectionFactory
import org.apache.activemq.RedeliveryPolicy
import org.fm.pimq.IPinMessage
import org.fm.pimq.PinMQ
import org.fm.pimq.PinStateMQ
import org.fm.pimq.IPinCommand
import org.fm.pimq.impl.PinMessageImpl

import javax.jms.*

/**
 * 
 * @author fabiomarini
 *
 */
@Transactional
class DeviceService {

	/**
	 * Sends the given command to the specified device
	 * @param device the device to send the command
	 * @param command the command to send
	 * @return true if the command has been sent without problems, or false otherwise
	 * @throws AssertError if the given arguments are not valid
	 */
    public Boolean sendCommand(Device device, IPinCommand command) {
		log.debug "Sending command ${command} for Device ${device}"
		
		assert device != null
		assert device.jackProducer != null
		assert device.user != null
		assert command != null
		
		User user = device.user
		Jack jack = device.jackProducer
		MQServer server = jack.serverContainer
		
		Boolean result = false
		try {
			ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(server.url)
					
			RedeliveryPolicy policy = new RedeliveryPolicy()
			policy.initialRedeliveryDelay = 1000L
			policy.maximumRedeliveries = RedeliveryPolicy.NO_MAXIMUM_REDELIVERIES
			connectionFactory.redeliveryPolicy = policy
			
			Connection connection = connectionFactory.createConnection(user.username, 
																	   user.password)
			connection.start()
	
			Session amqSession = connection.createSession(false, Session.AUTO_ACKNOWLEDGE)
			Destination destination = amqSession.createQueue(jack.queueName)
			MessageProducer producer = amqSession.createProducer(destination)
	
			ObjectMessage message = amqSession.createObjectMessage(command)
			producer.send(message)

			log.debug "Command ${command} for Device ${device} has been sent"
			
			amqSession.close()
			connection.close()
			
			result = true
		} catch (Exception e) {
			log.error"Caught: ${e} when sending command ${command} for Device ${device}"
			e.printStackTrace()
			
			result = false
		}
		
		return result
	}
}
