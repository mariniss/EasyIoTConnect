package com.fm.easyiotconnect.mq

/**
 * 
 * @author Fabio Marini
 *
 */
class DeviceInfos {

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
    }
}
