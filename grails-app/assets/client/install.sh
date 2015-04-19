#!/bin/bash

_java=`type java`
echo "searcing java ..."

if [ ! -z "$_java" ]; then
    echo "Using ${_java}"

    echo "Downloading client"
    wget http://www.easyiotconnect.com/client.zip

    unzip client.zip

    echo "#!/bin/bash
sudo modprobe w1-gpio
sudo modprobe w1-therm
sudo java -cp \"./client/:./client/dependency-jars/*\" -jar ./client/org.fm.pimq.client-1.0.jar ./client/ configurations.properties &
echo \"Client started\"
" > runClient.sh

    if ! grep -q "dtoverlay=w1-gpio" "/boot/config.txt"; then
        echo "dtoverlay=w1-gpio" >> /boot/config.txt
    fi

    echo "Client installed. "
    echo "Now you have to download the configuration from your dashboard and put it in this folder"
else
    echo "Plese install java, it is necessary to run the EIoTClient"
fi
