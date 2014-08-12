import com.fm.easyiotconnect.Role
import com.fm.easyiotconnect.User
import com.fm.easyiotconnect.UserRole
import com.fm.easyiotconnect.DateUtils

class BootStrap {

    def init = { servletContext ->

		//Creating admin and devel roles to work
		if(User.findByUsername('fabio') == null)
		{
			def develRole = new Role(authority: 'ROLE_DEVEL', description : "Role for the developments")
			def adminRole = new Role(authority: 'ROLE_ADMIN', description : "Administration role")

			develRole.save(flush: true)
			adminRole.save(flush: true)
			
			User fabioUser = createMyUser()
			fabioUser.save(flush: true)
	  
			UserRole fabioDevelRole = new UserRole(user: fabioUser, role : develRole)
			UserRole fabioAdminRole = new UserRole(user: fabioUser, role : adminRole)
			
			fabioDevelRole.save(flush: true)
			fabioAdminRole.save(flush: true)
			
			assert User.count() == 1
			assert Role.count() == 2
			assert UserRole.count() == 2
		}
    }
    def destroy = {
    }
	
	/**
	 * Create the "Fabio" user
	 */
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
}
