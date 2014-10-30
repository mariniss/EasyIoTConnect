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

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Device.list(params), model:[deviceInstanceCount: Device.count()]
    }

    def show(Device deviceInstance) {
        respond deviceInstance
    }

    def create() {
        respond new Device(params)
    }
	
	def sendCommand() {
		try {
			ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("http://ec2-54-77-129-207.eu-west-1.compute.amazonaws.com:61516")
			RedeliveryPolicy policy = new RedeliveryPolicy()
			policy.setInitialRedeliveryDelay(1000L)
			policy.setMaximumRedeliveries(RedeliveryPolicy.NO_MAXIMUM_REDELIVERIES)

			connectionFactory.setRedeliveryPolicy(policy);
			Connection connection = connectionFactory.createConnection('fabio',
				'$2a$10$XEvV/Obo6PXHcEP3YgL1RuicDvyFUJ/m7FRFg4ovu5eOe1K0U39US');

			connection.start()

			// Create a Session
			Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE)

			// Create the destination (Topic or Queue)
			Destination destination = session.createQueue("PiMQ.GPIO.Commands")

			// Create a MessageProducer from the Session to the Topic or Queue
			MessageProducer producer = session.createProducer(destination)

			//IPinMessage command = new PinMessageImpl(RaspiPin.GPIO_01, PinState.HIGH);
			IPinMessage command = new PinMessageImpl(new PinMQ(Integer.valueOf(params.pin)), 
															   (Integer.valueOf(params.status) == 0) ? 
															   			PinStateMQ.LOW : 
																		PinStateMQ.HIGH)

			ObjectMessage message = session.createObjectMessage(command)

			// Tell the producer to send the message
			System.out.println("Sent message: " + message.hashCode() + " : " + Thread.currentThread().getName())
			producer.send(message)

			// Clean up
			session.close()
			connection.close()
		} catch (Exception e) {
			System.out.println("Caught: " + e);
			e.printStackTrace();
		}
		
		redirect(controller: "dashboard")
	}

    @Transactional
    def save(Device deviceInstance) {
        if (deviceInstance == null) {
            notFound()
            return
        }

        if (deviceInstance.hasErrors()) {
            respond deviceInstance.errors, view:'create'
            return
        }

        deviceInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'device.label', default: 'Device'), deviceInstance.id])
                redirect deviceInstance
            }
            '*' { respond deviceInstance, [status: CREATED] }
        }
    }

    def edit(Device deviceInstance) {
        respond deviceInstance
    }

    @Transactional
    def update(Device deviceInstance) {
        if (deviceInstance == null) {
            notFound()
            return
        }

        if (deviceInstance.hasErrors()) {
            respond deviceInstance.errors, view:'edit'
            return
        }

        deviceInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Device.label', default: 'Device'), deviceInstance.id])
                redirect deviceInstance
            }
            '*'{ respond deviceInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Device deviceInstance) {

        if (deviceInstance == null) {
            notFound()
            return
        }

        deviceInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Device.label', default: 'Device'), deviceInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'device.label', default: 'Device'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
