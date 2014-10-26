package com.fm.easyiotconnect.beckend

import grails.converters.JSON

class SupportController {

	/**
	 * Support method to avoid the sleeping on Heroku server 
	 * @return
	 */
    def keepMeAwake() {
		render "I'm awake, thanks"
	}
}
