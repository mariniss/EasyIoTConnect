package com.fm.easyiotconnect.mq



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class AuthenticationServerController {
    static scaffold = true
}
