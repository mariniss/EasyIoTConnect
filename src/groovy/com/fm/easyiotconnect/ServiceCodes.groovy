/**
 * 
 */
package com.fm.easyiotconnect

/**
 * @author fabiomarini
 *
 */
class ServiceCodes {

	/**************************************************************************
	 * 	Error codes
	 *************************************************************************/

	public static enum Errors {
		NULL_ARGUMENT,
		
		USER_NOT_SAVED,
		ROLE_NOT_SAVED,
		USER_ROLE_NOT_SAVED
	}
	
	/**************************************************************************
	 * 	Info codes
	 *************************************************************************/

	public static enum Infos {		
		USER_CREATED
	}
}
