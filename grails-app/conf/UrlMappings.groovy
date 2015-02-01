class UrlMappings {

	static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

		//Devel
        "/devel"(view:"/devel")
		
		//Portal
		//"/"(view:"/portal/index")

		//Landing
		"/"(controller: 'landing', action: 'index')
		"/askQuestion"(controller: 'landing', action: 'askQuestion')

		//Dashboard
		"/dashboard"(controller:"dashboard", action:"index")
		
		//REST Api
		"/security/connection" (controller:"security", action:"connectionSecurityCheck")
		"/security/session" (controller:"security", action:"sessionSecurityCheck")
		
		//General
		"500"(view:'/error')
		
		//Support
		"/support/keepmeawake" (controller:"support", action:"keepMeAwake")
	}
}
