package com.fm.easyiotconnect

import com.fm.easyiotconnect.mq.Device
import com.fm.easyiotconnect.mq.Jack
import com.fm.easyiotconnect.mq.MQServer
import grails.transaction.Transactional

import org.apache.activemq.ActiveMQConnectionFactory
import org.apache.activemq.RedeliveryPolicy
import org.apache.activemq.command.ActiveMQObjectMessage
import org.fm.pimq.IPinCommand
import org.fm.pimq.IPinMessage
import org.fm.pimq.bus.w1.therm.Temperature

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
		assert device.jackCommandProducer != null
		assert device.user != null
		assert command != null
		
		User user = device.user
		Jack jack = device.jackCommandProducer
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
			Destination destination = amqSession.createQueue(jack.queueName)
			MessageProducer producer = amqSession.createProducer(destination)
	
			ObjectMessage message = amqSession.createObjectMessage(command)
			producer.send(message)

			log.debug "Command ${command} for Device ${device} has been sent"
			
			amqSession.close()
			connection.close()
			
			return true
		} catch (Exception e) {
			log.error"Caught: ${e} when sending command ${command} for Device ${device}"
			e.printStackTrace()
			
			return false
		}
	}


	public Boolean sendW1DataRequest(Device device, IPinCommand command) {
		log.debug "Sending w1 data request ${command} for Device ${device}"

		assert device != null
		assert device.jackW1DataRequestProducer != null
		assert device.user != null
		assert command != null

		User user = device.user
		Jack jack = device.jackW1DataRequestProducer
		MQServer server = jack.serverContainer

		try {
			ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(server.url)

			RedeliveryPolicy policy = new RedeliveryPolicy()
			policy.initialRedeliveryDelay = 1000L
			policy.maximumRedeliveries = RedeliveryPolicy.NO_MAXIMUM_REDELIVERIES
			connectionFactory.redeliveryPolicy = policy

			Connection connection = connectionFactory.createConnection(user.username, user.password)
			connection.start()

			Session amqSession = connection.createSession(false, Session.AUTO_ACKNOWLEDGE)
			Destination destination = amqSession.createQueue(jack.queueName)
			MessageProducer producer = amqSession.createProducer(destination)

			ObjectMessage message = amqSession.createObjectMessage(command)
			producer.send(message)

			log.debug "w1 data request ${command} for Device ${device} has been sent"

			amqSession.close()
			connection.close()

			return true
		} catch (Exception exc) {
			log.error ("Caught: exception when sending command ${command} for Device ${device}", exc)

			return false
		}
	}


	public Temperature getW1Data(Device device) {
		log.debug "Consuming w1 data for Device ${device}"

		Temperature temperature = null

		assert device != null
		assert device.jackW1DataProducer != null
		assert device.user != null

		User user = device.user
		Jack jack = device.jackW1DataProducer
		MQServer server = jack.serverContainer

		try {
			ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory(server.url)

			RedeliveryPolicy policy = new RedeliveryPolicy()
			policy.initialRedeliveryDelay = 1000L
			policy.maximumRedeliveries = RedeliveryPolicy.NO_MAXIMUM_REDELIVERIES
			connectionFactory.redeliveryPolicy = policy

			Connection connection = connectionFactory.createConnection(user.username, user.password)
			connection.start()

			Session amqSession = connection.createSession(false, Session.AUTO_ACKNOWLEDGE)
			Destination destination = amqSession.createQueue(jack.queueName)
			MessageConsumer consumer = amqSession.createConsumer(destination)

			Message message = consumer.receive()
			if (((ActiveMQObjectMessage) message).getObject() instanceof IPinMessage) {
				log.debug "w1 data for Device ${device} has been received, message ${message}"

				IPinMessage w1DataMessage = (IPinMessage) ((ActiveMQObjectMessage) message).getObject()
				temperature = new Temperature(w1DataMessage.w1Data.therm)
			}
			else {
				log.error "Received unknown message from Device ${device}, message ${message}"
			}

			amqSession.close()
			connection.close()
		} catch (Exception exc) {
			log.error ("Caught: exception when receiving w1 data for Device ${device}", exc)
		}


		return temperature
	}
}
