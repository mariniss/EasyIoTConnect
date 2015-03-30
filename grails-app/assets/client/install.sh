#!/bin/bash

_java=`type java`
echo "searcing java ..."

if [ ! -z "$_java" ]; then
    echo "Using ${_java}"

    echo "Downloading client"
    wget http://www.easyiotconnect.com/client.zip

    unzip client.zip

    echo "#!/bin/bash
sudo java -cp \"./client/dependency-jars\" -jar ./client/org.fm.pimq.client-0.1-SNAPSHOT.jar ./client/configurations.properties &
" > runClient.sh

    echo "Client installed. "
    echo "Now you have to download the configuration from your dashboard and put it in this folder"
else
    echo "Plese install java, it is necessary to run the EIoTClient"
fi
