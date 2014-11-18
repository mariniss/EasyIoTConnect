import com.fm.easyiotconnect.Role
import com.fm.easyiotconnect.User
import com.fm.easyiotconnect.UserRole
import com.fm.easyiotconnect.DateUtils
import com.fm.easyiotconnect.mq.MQServer

class BootStrap {

    def init = { servletContext ->

		createBaseRoles()
		createDevelUser()
		addMQServers()
    }
    def destroy = {
    }
	
	
	/**************************************************************************
	 * 			Utility methods
	 **************************************************************************/
	
	def createDevelUser(){
		//Creating admin and devel roles to work
		if(User.findByUsername('fabio.mariniss@gmail.com') == null)
		{			
			User fabioUser = createMyUser()
			fabioUser.save(flush: true, failOnError: true)
	  
			UserRole fabioDevelRole = new UserRole(user: fabioUser, role : Role.findByAuthority("ROLE_DEVEL"))
			UserRole fabioAdminRole = new UserRole(user: fabioUser, role : Role.findByAuthority("ROLE_ADMIN"))
			
			fabioDevelRole.save(flush: true, failOnError: true)
			fabioAdminRole.save(flush: true, failOnError: true)
			
			assert User.count() == 1
			assert UserRole.count() == 2
		}
	}

	def createMyUser = {

		def fabio = new User()

		fabio.name 		= "Fabio Marini"
		fabio.state		= "Italy"
		fabio.email		= 'fabio.mariniss@gmail.com'

		fabio.username	= 'fabio.mariniss@gmail.com'
		fabio.password 	= 'eiotc636x'
		
		return fabio
	}

	def createBaseRoles(){
		if(Role.findByAuthority("ROLE_DEVEL") == null){
			def develRole = new Role(authority: 'ROLE_DEVEL', description : "Role for the developments")
			develRole.save(flush: true, failOnError: true)
		}
		
		if(Role.findByAuthority("ROLE_ADMIN") == null){
			def adminRole = new Role(authority: 'ROLE_ADMIN', description : "Administration role")
			adminRole.save(flush: true, failOnError: true)
		}
		
		if(Role.findByAuthority("ROLE_BASE") == null){
			def baseRole = new Role(authority: 'ROLE_BASE', description : "Basic user role")
			baseRole.save(flush: true, failOnError: true)
		}
	}
	
	def addMQServers() {
		if(MQServer.count() == 0) {
			if(Environment.current == Environment.DEVELOPMENT) {
				MQServer local =
				new MQServer(name: "Local test server",
							 type: MQServer.TYPE_ACTIVE_MQ,
							 url: "http://localhost:61616",
							 provider: MQServer.PROVIDER_LOCALHOST)

				local.save(flush: true, failOnError: true)
			}
			else {
				MQServer awsServerOne = 
					new MQServer(name: "AWS Srv One",
								 type: MQServer.TYPE_ACTIVE_MQ,
								 url: "http://ec2-54-77-129-207.eu-west-1.compute.amazonaws.com:61516",
								 provider: MQServer.PROVIDER_AWS)
	
				awsServerOne.save(flush: true, failOnError: true)
			}
		}
	}
}
