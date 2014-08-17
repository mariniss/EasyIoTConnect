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
		"/"(view:"/portal/index")
		
		//Dashboard
		"/dashboard"(view:"/dashboard/index")
		
		//General
		"500"(view:'/error')
	}
}
