package com.fm.easyiotconnect

import com.fm.easyiotconnect.mq.Device
import com.fm.easyiotconnect.mq.DeviceInfos
import grails.converters.JSON
import grails.util.Environment
import org.apache.commons.lang.StringUtils
import org.fm.pimq.IPinMessage
import org.fm.pimq.PinMQ
import org.fm.pimq.PinStateMQ
import org.fm.pimq.bus.w1.therm.Temperature
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
   def userService

   
    def index() {
       def currentUser = springSecurityService.currentUser
       def devices = Device.findAllByUser(currentUser)

       render view:"index",
              model:[devices : devices,
                     currentUser: currentUser]
   }

   
   def saveDevice() {
      String deviceName = params.name
      String deviceType = Device.TYPE_RASPBERRY //params.type
      
      def currentUser = springSecurityService.currentUser

      boolean okCreate = connectionService.create(currentUser, deviceType, deviceName)
      if(okCreate) {
         flash.alert = [type:"success", title: "Done", message: "Device created successfully!"]
      }
      else {
         flash.alert = [type:"warning", title: "Sorry", message: "There was a problem creating device!"]
      }
      
      redirect action:"index"
   }


   def updateDevice() {
      def deviceId = params.id
      
      def currentUser = springSecurityService.currentUser


      Device device = Device.findByIdAndUser(deviceId, currentUser)
      if(device == null) {
         flash.alert = [type:"waring", title: "Sorry", message: "Device not found!"]
      }
      else {
         //Actually the name is unmodifiable
         //device.infos.name = params.name

         //TODO: move into a service

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
         device.infos.gpio18Name = params.gpio18name
         device.infos.gpio19Name = params.gpio19name
         device.infos.gpio20Name = params.gpio20name


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
         device.infos.gpio18Visible = StringUtils.isNotBlank(device.infos.gpio18Name)
         device.infos.gpio19Visible = StringUtils.isNotBlank(device.infos.gpio19Name)
         device.infos.gpio20Visible = StringUtils.isNotBlank(device.infos.gpio20Name)


         device.infos.gpio0Type  = params.gpio0type
         device.infos.gpio1Type  = params.gpio1type
         device.infos.gpio2Type  = params.gpio2type
         device.infos.gpio3Type  = params.gpio3type
         device.infos.gpio4Type  = params.gpio4type
         device.infos.gpio5Type  = params.gpio5type
         device.infos.gpio6Type  = params.gpio6type
         device.infos.gpio7Type  = params.gpio7type
         device.infos.gpio8Type  = params.gpio8type
         device.infos.gpio9Type  = params.gpio9type
         device.infos.gpio10Type = params.gpio10type
         device.infos.gpio11Type = params.gpio11type
         device.infos.gpio12Type = params.gpio12type
         device.infos.gpio13Type = params.gpio13type
         device.infos.gpio14Type = params.gpio14type
         device.infos.gpio15Type = params.gpio15type
         device.infos.gpio16Type = params.gpio16type
         device.infos.gpio17Type = params.gpio17type
         device.infos.gpio18Type = params.gpio18type
         device.infos.gpio19Type = params.gpio19type
         device.infos.gpio20Type = params.gpio20type
         
         if(device.save(flush:true)) {
            flash.alert = [type:"success", title: "Done", message: "Device updated successfully!"]
         }
         else {
            flash.alert = [type:"waring", title: "Sorry", message: "There was problems updating device!"]
         }
      }
      
      redirect action:"index"
   }


   def deleteDevice() {
      def currentUser = springSecurityService.currentUser

      Device deviceToDelete = Device.get(params.id)
      if(deviceToDelete == null || currentUser.id != deviceToDelete.user.id) {
         flash.alert = [type:"error", title: "Sorry", message: "You cannot delete this device!"]
      }
      else {
         boolean deleted = connectionService.deleteDevice(currentUser, deviceToDelete)

         if(deleted) {
            flash.alert = [type:"success", title: "Done", message: "Device deleted successfully!"]
         }
         else {
            flash.alert = [type:"warning", title: "Sorry", message: "There was a problem deleting device!"]
         }
      }

      redirect action:"index"
   }


   def sendCommand() {
      def deviceId = params.id
      def currentUser = springSecurityService.currentUser

      Map result = [error:true]

      Device device = Device.findByIdAndUser(deviceId, currentUser)
      if(device == null) {
         result.message = "Device not found!"
      }
      else {
         int pin    = params.int('pin')
         int status = params.int('status')

         PinMQ mqPin = new PinMQ(pin)
         PinStateMQ mqState = status == 0 ? PinStateMQ.LOW : PinStateMQ.HIGH
         IPinMessage command = new PinMessageImpl(mqPin, mqState)

         result.error = !deviceService.sendCommand(device, command)
      }

      render result as JSON
   }


   def w1ThermData() {
      def deviceId = params.id
      def currentUser = springSecurityService.currentUser

      Map result = [error:true]

      Device device = Device.findByIdAndUser(deviceId, currentUser)
      if(device == null) {
         result.message = "Device not found!"
      }
      else {
         int pin = DeviceInfos.GPIO_W1_THERM

         PinMQ mqPin = new PinMQ(pin)
         IPinMessage command = new PinMessageImpl(mqPin)

         if(deviceService.sendW1DataRequest(device, command)) {
            Temperature temperature = deviceService.getW1Data(device)
            if(temperature != null) {
               result.error = false
               result.data = [celsius: temperature.celsius, fahrenheit: temperature.fahrenheit]
            }
         }
      }

      render result as JSON

   }

   
   def updatePersonal() {
      boolean updated = false

      User currentUser = springSecurityService.currentUser

      Integer userId = currentUser.id
      String name = params.completeName
      String country = params.country

      String password = params.password
      String repeatPassword = params.repeatPassword
      String okPassword = password == repeatPassword
      if(okPassword) {
         updated = userService.updateUser(userId, name, country, password)
      }

      if(updated) {
         flash.alert = [type:"success", title: "Done", message: "Information updated successfully!"]
      }
      else {
         flash.alert = [type:"warning", title: "Sorry", message: "There was problems during the update!"]
      }

      redirect action:"index"
   }


   def downloadConfiguration() {
      def currentUser = springSecurityService.currentUser
      Device device = Device.findByIdAndUser(params?.id, currentUser)

      if(device != null) {
         String deviceConfig =
                 groovyPageRenderer.render(view : '/fileTemplates/pimqConfig',
                         model: [device: device,
                                 user: currentUser])

         response.setContentType("text/plain")
         response.setHeader("Content-disposition", "attachment;filename=configurations.properties")
         response.outputStream << deviceConfig.bytes
      }
      else {
         redirect action: "index"
      }
   }


   def downloadClient() {
      redirect url: "/assets/client.zip"
   }


   def downloadInstallationScript() {
      redirect url: "/assets/install.sh"
   }
}
