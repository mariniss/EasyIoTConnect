package com.fm.easyiotconnect

import java.util.Date;
import java.util.Set;
import com.fm.easyiotconnect.mq.Device;

class User {

	transient springSecurityService

	String username
	String password

	boolean enabled = true

	String 	email
	String 	name
	String 	state

	boolean accountExpired  = false
	boolean accountLocked   = false
	boolean passwordExpired = false
	
	static hasMany = [devices: Device]

	static transients = ['springSecurityService']

	static constraints = {

		username 		( nullable 	: false,
						  blank		: false,
						  unique	: true  )

		password 		( nullable 	: false,
						  blank		: false )
	
		email 			( nullable 	: false,
						  email	   	: true,
						  blank	   	: false,
						  unique	: true )

		name			( nullable  : false,
						  blank	    : false)
				
		state			( nullable 	: false,
						  blank	   	: false)
	}

	static mapping = { 
		table 'ss_user';
	    password column: '`password`' 
	}
	
	Set<Role> getAuthorities() {
		UserRole.findAllByUser(this).collect { it.role } as Set
	}

	def beforeValidate() {
		if(username == null || username.size() == 0) {
			username  = email
		}	
	}
	
	def beforeInsert() {
		if(username == null || username.size() == 0) {
			username  = email
		}
		
		encodePassword()
	}

	def beforeUpdate() {
		if(username == null || username.size() == 0) {
			username  = email
		}
		
		if (isDirty('password')) {
			encodePassword()
		}
	}

	protected void encodePassword() {
		password = springSecurityService?.passwordEncoder ? springSecurityService.encodePassword(password) : password
	}

	String toString() {
		return "${this.name} (${this.username})"
	}
}
