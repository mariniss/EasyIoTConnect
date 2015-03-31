import com.fm.easyiotconnect.Role
import com.fm.easyiotconnect.User
import com.fm.easyiotconnect.UserRole
import com.fm.easyiotconnect.mq.AuthenticationServer
import com.fm.easyiotconnect.mq.MQServer
import grails.util.Environment

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
				AuthenticationServer authenticationServer =
						new AuthenticationServer(type: AuthenticationServer.TYPE_EIOTC_SERVER,
								url: "http://localhost:8888",
								boUrl: "http://localhost:9999",)
				authenticationServer.save(flush: true, failOnError: true)

				MQServer local =
					new MQServer(name: "Local test server",
								 type: MQServer.TYPE_ACTIVE_MQ,
								 url: "http://localhost:61616",
								 provider: MQServer.PROVIDER_LOCALHOST,
								 authenticationServer: authenticationServer)

				local.save(flush: true, failOnError: true)
			}
			else {
				AuthenticationServer authenticationServer =
					new AuthenticationServer(type: AuthenticationServer.TYPE_EIOTC_SERVER,
											 url: "http://ec2-52-17-125-181.eu-west-1.compute.amazonaws.com:8888",
											 boUrl: "http://ec2-52-17-125-181.eu-west-1.compute.amazonaws.com:9999",)
				authenticationServer.save(flush: true, failOnError: true)

				MQServer awsProOne =
						new MQServer(name: "AWS PRO 1",
									 type: MQServer.TYPE_ACTIVE_MQ,
									 url: "http://ec2-52-17-125-181.eu-west-1.compute.amazonaws.com:61516",
									 provider: MQServer.PROVIDER_AWS,
								     authenticationServer: authenticationServer)

				awsProOne.save(flush: true, failOnError: true)

			}
		}
	}
}
