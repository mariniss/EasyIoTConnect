class UrlMappings {

	static mappings = {

		//Devel
        "/devel"(view:"/devel")
		"/$controller/$action?/$id?(.$format)?"{
			constraints {
				// apply constraints here
			}
		}

		//Landing
		"/"(controller: 'landing', action: 'index')
		"/askQuestion"(controller: 'landing', action: 'askQuestion')
		"/singUp"(controller: 'landing', action: 'singUp')


		//Dashboard
		"/dashboard"(controller:"dashboard", action:"index")
		"/saveDevice"(controller:"dashboard", action:"saveDevice")
		"/updateDevice"(controller: "dashboard", action: "updateDevice")
		"/deleteDevice"(controller: "dashboard", action: "deleteDevice")

		"/sendCommand"(controller: "dashboard", action: "sendCommand")

		"/downloadConfiguration"(controller: "dashboard", action: "downloadConfiguration")
		"/client.zip"(controller: "dashboard", action: "downloadClient")
		"/install.sh"(controller: "dashboard", action: "downloadInstallationScript")


		//General
		"500"(view:'/error')


		//REST Api
		"/security/connection" (controller:"security", action:"connectionSecurityCheck")
		"/security/session" (controller:"security", action:"sessionSecurityCheck")


		//Support
		"/support/keepmeawake" (controller:"support", action:"keepMeAwake")
	}
}
