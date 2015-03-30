#!/bin/bash

# checking java

if type -p java; then
    echo found java executable in PATH
    _java=java
elif [[ -n "$JAVA_HOME" ]] && [[ -x "$JAVA_HOME/bin/java" ]];  then
    echo "found java executable in JAVA_HOME"

    _java="$JAVA_HOME/bin/java"
else
    echo "Plese install java, it is necessary to run the EIoTClient"
fi

# checking java version
if [[ "$_java" ]]; then
    version=$("$_java" -version 2>&1 | awk -F '"' '/version/ {print $2}')
    echo "java version $version"

    if [[ "$version" > "1.5" ]]; then
        _java_version="$version"
    else
        echo "Plese upgrade java, it is necessary a version up to 1.5 to run the EIoTClient"
    fi
fi

# downloading client and unziping it
if [[ "$_java_version" ]]; then
    echo "Using ${_java} : ${_java_version}"

    echo "Downloading client"
    wget http://www.easyiotconnect.com/downloadClient

    unzip client.zip

    #TODO: create runClient.sh file
    echo "#!/bin/bash
          sudo java -cp \"./dependency-jars\" -jar org.fm.pimq.client-0.1-SNAPSHOT.jar conf.properties &" > runClient.sh

    echo "Client installed. "
    echo "Now you have to download the configuration from your dashboard and put it in this folder"
fi
