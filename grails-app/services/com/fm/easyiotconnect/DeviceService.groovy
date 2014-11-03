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
	 * 
	 * @param device
	 * @param pin
	 * @param status
	 * @param user
	 * @return
	 */
    def sendCommand(Device device, IPinCommand command) {
		assert device != null
		assert device.jack != null
		assert device.user != null
		assert command != null
		
		User user = device.user
		Jack jack = device.jackProducer
		MQServer server = jack.serverContainer
		
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
			Destination destination = amqSession.createQueue(jack.producerQueueName)
			MessageProducer producer = amqSession.createProducer(destination)
	
			ObjectMessage message = amqSession.createObjectMessage(command)
			producer.send(message)

			log.debug "Sent command ${command} for jack ${jack}"
			
			amqSession.close()
			connection.close()
		} catch (Exception e) {
			log.error"Caught: ${e}"
			e.printStackTrace();
		}
	}
}
