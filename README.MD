Easy IoT Connect
======

EIoTC is a web application on top of a cloud platform that I have created to simplify the creation of any IoT device build with a Rapsberry Pi.

The idea is to provide a service that, with a few and simple steps, connects your Raspberry with the cloud in order to have the remote control from any location in the world.

With EIoTC it is possible to send the low and higt command (on/off) to each GPIO, one shot or via timer, and fetch data from a temperature sensor, all through a nice responsive web interface.

The whole platform is composed by:
- EasyIoTConnect
- PiMQ
- EIoTCBoServer
- EIoTCServer
- Apache ActiveMQ (extended with a my plugin for the security: MQSercurityPlugin)

1.EasyIoTConnect
It is is a Grails web application that implements the front-end and the business logic.
The front-end layer is base on Bootstrap and JQuery, the back-office site is the standard grails implementation (not so cool, ma works really well) and there is also Quarz to as scheduler to handle the timed commands
It also defines the reletional database schema (via grails GORM).

2. PiMQ
PiMQ is the client to install on the Raspberry Pi: it sends and recieves the commands through an ActiveMQ queue. You can get it [here](https://github.com/mariniss/PiMQ)

3. EIoTCBoServer
EIoTCBoServer implements the authentication logic for the backoffice component: ActiveMQ. It provides a REST interface to perform the security checks for each command from/to the raspberry

4. EIoTCServer
EIoTCBoServer implements the authentication logic for all clients, the users' Rapsberries, throught PiMQ. It provides a REST interface to perform the security checks for each command from/to the raspberry

5. Apache ActiveMQ with MQSercurityPlugin
The communication with the Raspberry is based on an asynch queue; I have choosen ActiveMQ because it is a quite good system, easy to use and really easy to extend.
Indeed, I have create a plugin in order to allow each Raspberry to connect to its personal queue.

The main point of this architecture is the security, it is implemented by a double check and two different services, EIoTCBoServer and EIoTCServer and then there is the scalability, the architecture implements a vertical scalability allowing more instance of EIoTCBoServer, EIoTCServer and ActiveMQ
