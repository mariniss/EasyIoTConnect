package com.fm.easyiotconnect

import java.util.Date;
import java.util.Set;
import com.fm.easyiotconnect.mq.Jack;

class User {

	transient springSecurityService

	String username
	String password

	boolean enabled = true

	String 	email
	String 	name
	String 	lastname
	Date   	bornDate
	String	gender
	String 	adress
	String 	phone

	boolean accountExpired
	boolean accountLocked
	boolean passwordExpired
	
	static hasMany = [jacks: Jack]

	static transients = ['springSecurityService']

	static constraints = {
		username 		( nullable 	: false,
						  blank		: false,
						  unique	: true  )

		password 		( nullable 	: false,
						  blank		: false )

		name			( nullable  : false,
						  blank	    : false,
						  size 	    : 1..45 )

		lastname		( nullable  : false,
						  blank	    : false,
						  size 	    : 1..45 )

		bornDate		( nullable 	: false)

		gender	 		( nullable 	: false,
						  blank	   	: false,
						  inList  	: ["M", "F"])

		adress			( nullable 	: false,
						  blank	   	: false,
						  size 	   	: 1..100)

		phone			( nullable 	: false,
						  blank	   	: false,
						  size 	   	: 1..45)

		email 			( nullable 	: false,
						  email	   	: true,
						  blank	   	: false)
		
		jacks			( nullable  : true)
	}

	static mapping = { table 'ss_user';
						password column: '`password`' }
	
	Set<Role> getAuthorities() {
		UserRole.findAllByUser(this).collect { it.role } as Set
	}

	def beforeInsert() {
		encodePassword()
	}

	def beforeUpdate() {
		if (isDirty('password')) {
			encodePassword()
		}
	}

	protected void encodePassword() {
		password = springSecurityService?.passwordEncoder ? springSecurityService.encodePassword(password) : password
	}

	String toString() {
		return "${this.name} ${this.lastname} (${this.username})"
	}
}
