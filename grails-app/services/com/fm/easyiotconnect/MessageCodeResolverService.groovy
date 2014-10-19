package com.fm.easyiotconnect

import grails.transaction.Transactional


/**
 * 
 * @author fabiomarini
 *
 */
@Transactional
class MessageCodeResolverService {

	/**
	 * 
	 * @param code
	 * @return
	 */
    def String getMessageByCode(String code) {
		
		def message = "Unknow"
		
		switch(code){
			//PT : PORTAL, SC: SECURITY
			case "PTSCOO":
				message = "Sing-Up done!"
				break;
		}
		
		return message
    }
}
