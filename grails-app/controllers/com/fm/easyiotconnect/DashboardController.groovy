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

      boolean okCreate = connectionService.createDevice(currentUser, deviceType, deviceName)
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
         def okUpdate = connectionService.updateDevice(device, params)
         if(okUpdate) {
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

      String okPassword = (password == repeatPassword)
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
