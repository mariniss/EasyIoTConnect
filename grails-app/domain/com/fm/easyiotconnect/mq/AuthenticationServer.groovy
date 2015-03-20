package com.fm.easyiotconnect.mq

/**
 * @author Fabio Marini
 */
class AuthenticationServer {

    static String TYPE_EIOTC_SERVER = "EIOTC_SERVER"
    static String TYPE_EIOTC_APP    = "EIOTC_APP"

    String type
    String url
    String boUrl

    static constraints = {

        type    (nullable: false,
                 blank   : false,
                 inList  : [TYPE_EIOTC_APP, TYPE_EIOTC_SERVER])

        url     (nullable: false,
                 blank   : false)

        boUrl   (nullable: true, //only to backward compatibility compatibility
                 blank   : false)
    }


    def beforeValidate() {
        if(boUrl == null || boUrl.isEmpty()) boUrl = url
    }
}
