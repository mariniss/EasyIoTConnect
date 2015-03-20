package com.fm.easyiotconnect

import com.fm.easyiotconnect.mq.Jack
import grails.transaction.Transactional


/**
 * 
 * @author fabiomarini
 *
 */
@Transactional
class SecurityService {

	def springSecurityService


	/**
	 *
	 * @param username
	 * @param password
	 * @return
	 */
	boolean authenticateConnection (String username, String password) {
		User user = User.findByUsernameAndPassword(username, password)
		if(user != null){
			return true
		}

		return false
	}


	/**
	 *
	 * @param username
	 * @param queueName
	 * @return
	 */
	boolean authenticateSession (String username, String queueName) {
		User user = User.findByUsername(username)
		if(user != null){
			def jack = Jack.findByQueueName(queueName)
			if(jack != null) {
				return jack.device?.user == user
			}
		}

		return false
	}
}
