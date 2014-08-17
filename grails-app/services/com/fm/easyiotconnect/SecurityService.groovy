package com.fm.easyiotconnect

import com.fm.easyiotconnect.User
import com.fm.easyiotconnect.Role
import com.fm.easyiotconnect.UserRole

import grails.transaction.Transactional

/**
 * 
 * @author fabiomarini
 *
 */
@Transactional
class SecurityService {

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
}
