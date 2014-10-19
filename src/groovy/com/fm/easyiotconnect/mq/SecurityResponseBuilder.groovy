/**
 * 
 */
package com.fm.easyiotconnect.mq

/**
 * @author fabiomarini
 *
 */
class SecurityResponseBuilder {
	
	/**
	 * 
	 * @return
	 */
	def static buildPositiveResponse()
	{
		BaseSecurityResponse baseResponse = new BaseSecurityResponse()
		baseResponse.response = "OK"
		
		return baseResponse
	}
	
	/**
	 *
	 * @return
	 */
	def static buildNegativeResponse()
	{
		BaseSecurityResponse baseResponse = new BaseSecurityResponse()
		baseResponse.response = "NOOK"
		
		return baseResponse
	}
}
