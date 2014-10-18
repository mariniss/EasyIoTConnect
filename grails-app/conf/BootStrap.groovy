import com.fm.easyiotconnect.Role
import com.fm.easyiotconnect.User
import com.fm.easyiotconnect.UserRole
import com.fm.easyiotconnect.DateUtils

class BootStrap {

    def init = { servletContext ->

		createBaseRoles()
		createDevelUser()
    }
    def destroy = {
    }
	
	
	/**************************************************************************
	 * 			Utility methods
	 **************************************************************************/
	
	def createDevelUser(){
		//Creating admin and devel roles to work
		if(User.findByUsername('fabio') == null)
		{			
			User fabioUser = createMyUser()
			fabioUser.save(flush: true)
	  
			UserRole fabioDevelRole = new UserRole(user: fabioUser, role : Role.findByAuthority("ROLE_DEVEL"))
			UserRole fabioAdminRole = new UserRole(user: fabioUser, role : Role.findByAuthority("ROLE_ADMIN"))
			
			fabioDevelRole.save(flush: true)
			fabioAdminRole.save(flush: true)
			
			assert User.count() == 1
			assert UserRole.count() == 2
		}
	}

	def createMyUser = {

		def fabio = new User()

		fabio.name 		= "Fabio"
		fabio.lastname 	= "Marini"
		fabio.bornDate 	= DateUtils.createDate(18, 10, 1986)
		fabio.email		= "fabio.mariniSS@gmail.com"
		fabio.adress	= "Via delle vie, 7, Bel Paese"
		fabio.gender 	= "M"
		fabio.phone		= "0011223344"

		fabio.username	= 'fabio'
		fabio.password 	= 'fabio'

		fabio.passwordExpired	= false
		fabio.enabled			= true
		
		return fabio
	}

	def createBaseRoles(){
		if(Role.findByAuthority("ROLE_DEVEL") == null){
			def develRole = new Role(authority: 'ROLE_DEVEL', description : "Role for the developments")
			develRole.save(flush: true)
		}
		
		if(Role.findByAuthority("ROLE_ADMIN") == null){
			def adminRole = new Role(authority: 'ROLE_ADMIN', description : "Administration role")
			adminRole.save(flush: true)
		}
		
		if(Role.findByAuthority("ROLE_BASE") == null){
			def baseRole = new Role(authority: 'ROLE_BASE', description : "Basic user role")
			baseRole.save(flush: true)
		}
	}
}
