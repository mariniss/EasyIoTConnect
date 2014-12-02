/**
 * 
 */
package com.fm.easyiotconnect

/**
 * @author fabiomarini
 *
 */
class ServiceError extends Error {

	/**************************************************************************
	 * 	Builder
	 *************************************************************************/
	
	public static ServiceError build(ServiceCodes.Errors code) {
		return new ServiceError(code)
	}
	
	
	public static ServiceError build(ServiceCodes.Errors code, String message) {
		return new ServiceError(code, message)
	}
	
	
	public static ServiceError build(ServiceCodes.Errors code, Throwable cause) {
		return new ServiceError(code, cause)
	}
	
	/**************************************************************************
	 * 	ServiceError definition
	 *************************************************************************/
	
	ServiceCodes.Errors code
	

	public ServiceError(ServiceCodes.Errors code, String message) {
		super(message)
		
		this.code = code
	}
	

	public ServiceError(ServiceCodes.Errors code) {
		this(code, "Code: ${code}")
	}


	public ServiceError(ServiceCodes.Errors code, Throwable cause) {
		this(code)

		initCause(cause)
	}


	public ServiceError(ServiceCodes.Errors code, String message, Throwable cause) {
		this(code, message)

		initCause(cause)
	}
}
