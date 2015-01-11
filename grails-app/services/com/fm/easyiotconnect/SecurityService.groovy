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
	ServiceCodes.Infos createBaseUser(User user) {
		if(user == null) {
			throw ServiceError.build(ServiceCodes.Errors.NULL_ARGUMENT)
		}

		if(!user.save()) {
			throw ServiceError.build(ServiceCodes.Errors.USER_NOT_SAVED)
		}

		UserRole userRole = new UserRole(user : user, role : Role.getBaseRole())
		if(!userRole.save()) {
			throw ServiceError.build(ServiceCodes.Errors.USER_ROLE_NOT_SAVED)
		}

		return ServiceCodes.Infos.USER_CREATED
	}

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
