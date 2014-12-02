package com.fm.easyiotconnect

class Role {

	String authority

	static mapping = {
		cache true
	}

	static constraints = {
		authority blank: false, unique: true
	}
	
	public static Role getBaseRole() {
		return Role.findByAuthority("ROLE_BASE")
	}
}
