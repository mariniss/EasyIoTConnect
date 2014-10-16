package com.fm.easyiotconnect

import com.fm.easyiotconnect.User
import com.fm.easyiotconnect.Role
import com.fm.easyiotconnect.UserRole
import com.fm.easyiotconnect.mq.Jack

import grails.plugin.springsecurity.SpringSecurityService;
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
	 * @param user
	 * @return
	 */
	def createBaseUser(User user) {

		//FIXME:it is a simple implementation

		user.validate()
		user.save(flush:true)

		UserRole userRole = new UserRole(user : user, role : Role.findByAuthority("ROLE_BASE"))
		userRole.save(flush:true)

		return "PTSCOO"
	}

	/**
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	def authenticateConnection (String username, String password) {
		User user = User.findByUsernameAndPassword(username, password)
		if(user != null){
			return true
		}

		return false
	}

	/**
	 *
	 * @param username
	 * @param password
	 * @param queueName
	 * @return
	 */
	def authenticateSession (String username, String queueName) {
		User user = User.findByUsername(username)
		if(user != null){

			def jack = Jack.findByUserAndQueueName(user, queueName)
			if(jack != null){
				return true
			}
		}

		return false
	}
}
