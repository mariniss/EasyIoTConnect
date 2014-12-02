package com.fm.easyiotconnect

import grails.transaction.Transactional


/**
 * 
 * @author fabiomarini
 *
 */
@Transactional
class MessageCodeResolverService {


    def String getMessageByCode(ServiceCodes.Infos code) {
		
		def message = "Sorry!, something was wrong"
		
		switch(code){
			case ServiceCodes.Infos.USER_CREATED:
				message = "Sing-Up done!"
				break
		}
		
		return message
    }
	
	def String getMessageByCode(ServiceCodes.Errors code) {
		
		def message = "Sorry!, we have got a proble"
		
		switch(code){
			case ServiceCodes.Errors.NULL_ARGUMENT:
				message = "The sing-up has got a problem"
				break
			
			case ServiceCodes.Errors.USER_NOT_SAVED:
			case ServiceCodes.Errors.ROLE_NOT_SAVED:
				message = "The request has got a problem"
				break;
		}
		
		return message
	}
}
