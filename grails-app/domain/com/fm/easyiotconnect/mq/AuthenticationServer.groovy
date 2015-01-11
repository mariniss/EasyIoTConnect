package com.fm.easyiotconnect.mq

/**
 * @author Fabio Marini
 */
class AuthenticationServer {

    static String TYPE_EIOTC_SERVER = "EIOTC_SERVER"
    static String TYPE_EIOTC_APP    = "EIOTC_APP"

    String type
    String url

    static constraints = {

        type (nullable: false,
              blank   : false,
              inList  : [TYPE_EIOTC_APP, TYPE_EIOTC_SERVER])

        url  (nullable: false,
              blank   : false)
    }
}
