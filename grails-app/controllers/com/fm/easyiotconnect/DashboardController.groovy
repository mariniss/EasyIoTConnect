package com.fm.easyiotconnect

import com.fm.easyiotconnect.mq.Device
import org.apache.commons.lang.StringUtils
import org.fm.pimq.IPinMessage
import org.fm.pimq.PinMQ
import org.fm.pimq.PinStateMQ
import org.fm.pimq.impl.PinMessageImpl

/**
 * 
 * @author fabiomarini
 *
 */
class DashboardController {

   
   def springSecurityService
   def connectionService
   def deviceService
   def grailsApplication
   def groovyPageRenderer

   
    def index() {
      def currentUser = springSecurityService.currentUser

      def devices = Device.findAllByUser(currentUser)
      def toConfigure = devices.any { it.toConfigure == true }
      
      render view:"index", 
            model:[ devices     : devices, 
                    toConfigure: devices.size() == 0? false : toConfigure,
                    pimqUrl: grailsApplication.config.eiotc.device.configure.pimqUrl ]
   }

   
   def saveDevice() {
      String deviceName = params.name
      String deviceType = Device.TYPE_RASPBERRY //params.type
      
      def currentUser = springSecurityService.currentUser

      boolean okCreate = connectionService.create(currentUser, deviceType, deviceName)
      if(okCreate) {
         flash.message = "Great! Device created successful!"
      }
      else {
         flash.message = "Sorry! There was a problem creating device"
      }
      
      redirect action:"index"
   }
   
   
   def configure() {
      def deviceId = params.id
      def currentUser = springSecurityService.currentUser

      List<Device> devices = []
   
      if(deviceId != null) {
         devices << Device.findByIdAndUser(deviceId, currentUser) 
      }
      else {
         devices = Device.findAllByUser(currentUser)
      }
      
      [ user: currentUser,
        devices: devices,
        pimqUrl: grailsApplication.config.eiotc.device.configure.pimqUrl ]
   }
   
   
   def downloadConfiguration() {
      def currentUser = springSecurityService.currentUser
      Device device = Device.findByIdAndUser(params?.id, currentUser) 

      if(device != null) {
         String deviceConfig = 
                  groovyPageRenderer.render(view: '/fileTemplates/pimqConfig', 
                                            model: [jack: device.jackProducer,
                                            user: currentUser])

         response.setContentType("text/plain")
         response.setHeader("Content-disposition", "attachment;filename=configurations.properties")
         response.outputStream << deviceConfig.bytes
      }
      else {
         redirect action: "index"
      }
   }
   

   def configured() {
      def deviceId = params.id
      
      def currentUser = springSecurityService.currentUser
      
      Device device = Device.findByIdAndUser(deviceId, currentUser)
      if(device == null) {
         flash.message = "Error, device not found!"
      } else {
         device.toConfigure = false
         device.save(flush:true)
      }
      
      redirect view:"index"
   }
   
   
   def manage() {
      def deviceId = params.id
      def currentUser = springSecurityService.currentUser
      
      List<Device> devices = []
   
      if(deviceId != null) {
         devices << Device.findByIdAndUser(deviceId, currentUser) 
      }
      else {
         devices = Device.findAllByUser(currentUser)
      }
      
      [ devices: devices ]
   }
   
   
   def updateDeviceInfo() {
      def deviceId = params.id
      
      def currentUser = springSecurityService.currentUser
      
      Device device = Device.findByIdAndUser(deviceId, currentUser)
      if(device == null) {
         flash.message = "Error, device not found!"
      }
      else {
         //Actually the name is unmodifiable
         //device.infos.name = params.name

         device.infos.gpio0Name  = params.gpio0name
         device.infos.gpio1Name  = params.gpio1name
         device.infos.gpio2Name  = params.gpio2name
         device.infos.gpio3Name  = params.gpio3name
         device.infos.gpio4Name  = params.gpio4name
         device.infos.gpio5Name  = params.gpio5name
         device.infos.gpio6Name  = params.gpio6name
         device.infos.gpio7Name  = params.gpio7name
         device.infos.gpio8Name  = params.gpio8name
         device.infos.gpio9Name  = params.gpio9name
         device.infos.gpio10Name = params.gpio10name
         device.infos.gpio11Name = params.gpio11name
         device.infos.gpio12Name = params.gpio12name
         device.infos.gpio13Name = params.gpio13name
         device.infos.gpio14Name = params.gpio14name
         device.infos.gpio15Name = params.gpio15name
         device.infos.gpio16Name = params.gpio16name
         device.infos.gpio17Name = params.gpio17name

         device.infos.gpio0Visible  = StringUtils.isNotBlank(device.infos.gpio0Name)
         device.infos.gpio1Visible  = StringUtils.isNotBlank(device.infos.gpio1Name)
         device.infos.gpio2Visible  = StringUtils.isNotBlank(device.infos.gpio2Name)
         device.infos.gpio3Visible  = StringUtils.isNotBlank(device.infos.gpio3Name)
         device.infos.gpio4Visible  = StringUtils.isNotBlank(device.infos.gpio4Name)
         device.infos.gpio5Visible  = StringUtils.isNotBlank(device.infos.gpio5Name)
         device.infos.gpio6Visible  = StringUtils.isNotBlank(device.infos.gpio6Name)
         device.infos.gpio7Visible  = StringUtils.isNotBlank(device.infos.gpio7Name)
         device.infos.gpio8Visible  = StringUtils.isNotBlank(device.infos.gpio8Name)
         device.infos.gpio9Visible  = StringUtils.isNotBlank(device.infos.gpio9Name)
         device.infos.gpio10Visible = StringUtils.isNotBlank(device.infos.gpio10Name)
         device.infos.gpio11Visible = StringUtils.isNotBlank(device.infos.gpio11Name)
         device.infos.gpio12Visible = StringUtils.isNotBlank(device.infos.gpio12Name)
         device.infos.gpio13Visible = StringUtils.isNotBlank(device.infos.gpio13Name)
         device.infos.gpio14Visible = StringUtils.isNotBlank(device.infos.gpio14Name)
         device.infos.gpio15Visible = StringUtils.isNotBlank(device.infos.gpio15Name)
         device.infos.gpio16Visible = StringUtils.isNotBlank(device.infos.gpio16Name)
         device.infos.gpio17Visible = StringUtils.isNotBlank(device.infos.gpio17Name)
         
         if(device.save(flush:true)) {
            flash.message = "Great! Device update successfully"
         }
         else {
            flash.message = "Sorry! There were problems updating device"
         }
      }
      
      redirect action:"index"
   }

   def deleteDevice() {
      def currentUser = springSecurityService.currentUser

      Device deviceToDelete = Device.get(params.id)
      if(deviceToDelete == null || currentUser.id != deviceToDelete.user.id) {
         flash.message = "Sorry! You cannot delete these data"
      }
      else {
         boolean deleted = connectionService.deleteDevice(deviceToDelete)

         if(deleted) {
            flash.message = "Device deleted successfully"
         }
         else {
            flash.message = "Sorry! There were problems during the delete"
         }
      }

      redirect action:"index"
   }
   
   def remote() {
      def deviceId = params.id
      
      def currentUser = springSecurityService.currentUser

      List<Device> devices = []
   
      if(deviceId != null) {
         devices << Device.findByIdAndUser(deviceId, currentUser) 
      }
      else {
         devices = Device.findAllByUser(currentUser)
      }
      
      [ devices: devices ]
   }
   
   
   def sendCommand() {
      def deviceId = params.id
      
      def currentUser = springSecurityService.currentUser
      
      Device device = Device.findByIdAndUser(deviceId, currentUser)
      if(device == null) {
         flash.message = "Error, device not found!"
      }
      else {
         int pin    = params.int('pin')
         int status = params.int('status')
         
         PinMQ mqPin = new PinMQ(pin)
         PinStateMQ mqState = status == 0 ? PinStateMQ.LOW : PinStateMQ.HIGH
         IPinMessage command = new PinMessageImpl(mqPin, mqState)

         if(deviceService.sendCommand(device, command)) {
            flash.message = "Great! Command sent successful!"
         }
         else {
            flash.message = "Sorry! Command not sent"
         }
      }
      
      redirect action:"remote", params: [id: deviceId]
   }
   
   
   def personal() {
      def currentUser = springSecurityService.currentUser

      render view:"personal", model:[user : currentUser]
   }
   
   
   def updatePersonal() {
      def currentUser = springSecurityService.currentUser
      
      User userToUpdate = User.get(params.id)
      if(userToUpdate == null || currentUser.id != userToUpdate.id) {
         flash.message = "Sorry! You cannot change these data"
      }
      else {
         String name = params.name
         userToUpdate.name = name
 
         String password = params.password
         String repeatPassword = params.repeatPassword
         String okPassword = password == params.repeatPassword
         if(password.size() > 0) {
            userToUpdate.password = password
         }

         boolean saved = userToUpdate.save(flush: true)

         if(saved) {
            flash.message = "Data updated successfully"
         }
         else {
            flash.message = "Sorry! There were problems during the update"
         }
      }
      
      redirect action:"index"
   }

}
