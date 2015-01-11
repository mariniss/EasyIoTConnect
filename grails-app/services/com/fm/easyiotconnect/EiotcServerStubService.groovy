package com.fm.easyiotconnect

import com.fm.easyiotconnect.mq.AuthenticationServer
import com.fm.easyiotconnect.mq.Jack
import grails.plugins.rest.client.RestBuilder
import grails.transaction.Transactional

/**
 * @Author Fabio Marini
 */
@Transactional
class EiotcServerStubService {

    protected static final String USER_PATH = '/security/user'

    RestBuilder rest = new RestBuilder()

    /**
     *
     * @param mqServer
     * @param user
     * @param jacks
     * @return
     */
    boolean registerUser(AuthenticationServer server, User user, List<Jack> jacks) {
        if(server == null || user == null || jacks == null) {
            throw new ServiceError(ServiceCodes.Errors.NULL_ARGUMENT)
        }

        for(def jack : jacks) {
            def resp = rest.post("${server.url}${USER_PATH}") {
                //auth System.getProperty("artifactory.user"), System.getProperty("artifactory.pass")
                contentType "application/vnd.org.jfrog.artifactory.security.Group+json"
                json {
                    username  = user.username
                    password  = user.password
                    queueName = jack.queueName
                }
            }

            if(resp.status != 200) {
                return false
            }
        }

        return true
    }
}
