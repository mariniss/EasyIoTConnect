package com.fm.easyiotconnect.mq

/**
 * 
 * @author Fabio Marini
 *
 */
class DeviceInfos {

	public static final String GPIO_TYPE_ON_OFF 	= 'on-off'
	public static final String GPIO_TYPE_TIMER 		= 'timer'
	public static final String GPIO_TYPE_W1_THERM 	= 'w1-therm'

	public static final List<String> GPIO_TYPES = [GPIO_TYPE_ON_OFF, GPIO_TYPE_TIMER]
	public static final List<String> GPIO_W1_THERM_TYPES = [GPIO_TYPE_ON_OFF, GPIO_TYPE_TIMER, GPIO_TYPE_W1_THERM]

	public static final Integer GPIO_W1_THERM = 4

	String name 	  = "Raspberry"
	
	String gpio0Name  = ""
	String gpio1Name  = ""
	String gpio2Name  = ""
	String gpio3Name  = ""
	String gpio4Name  = ""
	String gpio5Name  = ""
	String gpio6Name  = ""
	String gpio7Name  = ""
	String gpio8Name  = ""
	String gpio9Name  = ""
	String gpio10Name = ""
	String gpio11Name = ""
	String gpio12Name = ""
	String gpio13Name = ""
	String gpio14Name = ""
	String gpio15Name = ""
	String gpio16Name = ""
	String gpio17Name = ""
	String gpio18Name = ""
	String gpio19Name = ""
	String gpio20Name = ""
	
	Boolean gpio0Visible  = false
	Boolean gpio1Visible  = false
	Boolean gpio2Visible  = false
	Boolean gpio3Visible  = false
	Boolean gpio4Visible  = false
	Boolean gpio5Visible  = false
	Boolean gpio6Visible  = false
	Boolean gpio7Visible  = false
	Boolean gpio8Visible  = false
	Boolean gpio9Visible  = false
	Boolean gpio10Visible = false
	Boolean gpio11Visible = false
	Boolean gpio12Visible = false
	Boolean gpio13Visible = false
	Boolean gpio14Visible = false
	Boolean gpio15Visible = false
	Boolean gpio16Visible = false
	Boolean gpio17Visible = false
	Boolean gpio18Visible = false
	Boolean gpio19Visible = false
	Boolean gpio20Visible = false

	String gpio0Type  = GPIO_TYPE_ON_OFF
	String gpio1Type  = GPIO_TYPE_ON_OFF
	String gpio2Type  = GPIO_TYPE_ON_OFF
	String gpio3Type  = GPIO_TYPE_ON_OFF
	String gpio4Type  = GPIO_TYPE_ON_OFF
	String gpio5Type  = GPIO_TYPE_ON_OFF
	String gpio6Type  = GPIO_TYPE_ON_OFF
	String gpio7Type  = GPIO_TYPE_ON_OFF
	String gpio8Type  = GPIO_TYPE_ON_OFF
	String gpio9Type  = GPIO_TYPE_ON_OFF
	String gpio10Type = GPIO_TYPE_ON_OFF
	String gpio11Type = GPIO_TYPE_ON_OFF
	String gpio12Type = GPIO_TYPE_ON_OFF
	String gpio13Type = GPIO_TYPE_ON_OFF
	String gpio14Type = GPIO_TYPE_ON_OFF
	String gpio15Type = GPIO_TYPE_ON_OFF
	String gpio16Type = GPIO_TYPE_ON_OFF
	String gpio17Type = GPIO_TYPE_ON_OFF
	String gpio18Type = GPIO_TYPE_ON_OFF
	String gpio19Type = GPIO_TYPE_ON_OFF
	String gpio20Type = GPIO_TYPE_ON_OFF
	
	static belongsTo = [device: Device]
	
    static constraints = {
		device (nullable: false)
		
		name (blank : false)
		
		gpio0Name   (nullable: true)
		gpio1Name   (nullable: true)
		gpio2Name   (nullable: true)
		gpio3Name   (nullable: true)
		gpio4Name   (nullable: true)
		gpio5Name   (nullable: true)
		gpio6Name   (nullable: true)
		gpio7Name   (nullable: true)
		gpio8Name   (nullable: true)
		gpio9Name   (nullable: true)
		gpio10Name  (nullable: true)
		gpio11Name  (nullable: true)
		gpio12Name  (nullable: true)
		gpio13Name  (nullable: true)
		gpio14Name  (nullable: true)
		gpio15Name  (nullable: true)
		gpio16Name  (nullable: true)
		gpio17Name  (nullable: true)
		gpio18Name  (nullable: true)
		gpio19Name  (nullable: true)
		gpio20Name  (nullable: true)

		gpio0Visible  ( nullable: true )
		gpio1Visible  ( nullable: true )
		gpio2Visible  ( nullable: true )
		gpio3Visible  ( nullable: true )
		gpio4Visible  ( nullable: true )
		gpio5Visible  ( nullable: true )
		gpio6Visible  ( nullable: true )
		gpio7Visible  ( nullable: true )
		gpio8Visible  ( nullable: true )
		gpio9Visible  ( nullable: true )
		gpio10Visible ( nullable: true )
		gpio11Visible ( nullable: true )
		gpio12Visible ( nullable: true )
		gpio13Visible ( nullable: true )
		gpio14Visible ( nullable: true )
		gpio15Visible ( nullable: true )
		gpio16Visible ( nullable: true )
		gpio17Visible ( nullable: true )
		gpio18Visible ( nullable: true )
		gpio19Visible ( nullable: true )
		gpio20Visible ( nullable: true )

		gpio0Type  ( nullable: true, inList: GPIO_TYPES )
		gpio1Type  ( nullable: true, inList: GPIO_TYPES )
		gpio2Type  ( nullable: true, inList: GPIO_TYPES )
		gpio3Type  ( nullable: true, inList: GPIO_TYPES )
		gpio4Type  ( nullable: true, inList: GPIO_W1_THERM_TYPES )
		gpio5Type  ( nullable: true, inList: GPIO_TYPES )
		gpio6Type  ( nullable: true, inList: GPIO_TYPES )
		gpio7Type  ( nullable: true, inList: GPIO_TYPES )
		gpio8Type  ( nullable: true, inList: GPIO_TYPES )
		gpio9Type  ( nullable: true, inList: GPIO_TYPES )
		gpio10Type ( nullable: true, inList: GPIO_TYPES )
		gpio11Type ( nullable: true, inList: GPIO_TYPES )
		gpio12Type ( nullable: true, inList: GPIO_TYPES )
		gpio13Type ( nullable: true, inList: GPIO_TYPES )
		gpio14Type ( nullable: true, inList: GPIO_TYPES )
		gpio15Type ( nullable: true, inList: GPIO_TYPES )
		gpio16Type ( nullable: true, inList: GPIO_TYPES )
		gpio17Type ( nullable: true, inList: GPIO_TYPES )
		gpio18Type ( nullable: true, inList: GPIO_TYPES )
		gpio19Type ( nullable: true, inList: GPIO_TYPES )
		gpio20Type ( nullable: true, inList: GPIO_TYPES )
    }

	def beforeInsert() {
		if(gpio0Type  == null) gpio0Type  = GPIO_TYPE_ON_OFF
		if(gpio1Type  == null) gpio1Type  = GPIO_TYPE_ON_OFF
		if(gpio2Type  == null) gpio2Type  = GPIO_TYPE_ON_OFF
		if(gpio3Type  == null) gpio3Type  = GPIO_TYPE_ON_OFF
		if(gpio4Type  == null) gpio4Type  = GPIO_TYPE_ON_OFF
		if(gpio5Type  == null) gpio5Type  = GPIO_TYPE_ON_OFF
		if(gpio6Type  == null) gpio6Type  = GPIO_TYPE_ON_OFF
		if(gpio7Type  == null) gpio7Type  = GPIO_TYPE_ON_OFF
		if(gpio8Type  == null) gpio8Type  = GPIO_TYPE_ON_OFF
		if(gpio9Type  == null) gpio9Type  = GPIO_TYPE_ON_OFF
		if(gpio10Type == null) gpio10Type = GPIO_TYPE_ON_OFF
		if(gpio11Type == null) gpio11Type = GPIO_TYPE_ON_OFF
		if(gpio12Type == null) gpio12Type = GPIO_TYPE_ON_OFF
		if(gpio13Type == null) gpio13Type = GPIO_TYPE_ON_OFF
		if(gpio14Type == null) gpio14Type = GPIO_TYPE_ON_OFF
		if(gpio15Type == null) gpio15Type = GPIO_TYPE_ON_OFF
		if(gpio16Type == null) gpio16Type = GPIO_TYPE_ON_OFF
		if(gpio17Type == null) gpio17Type = GPIO_TYPE_ON_OFF
		if(gpio18Type == null) gpio18Type = GPIO_TYPE_ON_OFF
		if(gpio19Type == null) gpio19Type = GPIO_TYPE_ON_OFF
		if(gpio20Type == null) gpio20Type = GPIO_TYPE_ON_OFF
	}

	String toString() {
		return "${this.name}: ${this.device}"
	}
}
