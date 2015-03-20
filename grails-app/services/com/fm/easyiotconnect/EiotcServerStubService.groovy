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
    boolean registerJacks(AuthenticationServer server, User user, List<Jack> jacks) {
        if(server == null || user == null || jacks == null) {
            throw new ServiceError(ServiceCodes.Errors.NULL_ARGUMENT)
        }

        for(def jack : jacks) {

            def resp = rest.post("${server.boUrl}${USER_PATH}") {
                //auth System.getProperty("artifactory.user"), System.getProperty("artifactory.pass")
                contentType "application/vnd.org.jfrog.artifactory.security.Group+json"
                json {
                    username  = user.username
                    password  = user.password
                    queueName = jack.queueName
                }
            }

            if(resp.status == 200) {
                if(resp.json.status == "ok") {
                    jack.containerStoreId = resp.json._id
                    jack.save()
                }
                else {
                    log.error "Received an error response from BO server: ${resp.status} ${resp.text}"

                    return false
                }
            }
            else {
                return false
            }
        }

        return true
    }


    boolean deleteJacks(AuthenticationServer server, User user, List<Jack> jacks) {
        if(server == null || user == null || jacks == null) {
            throw new ServiceError(ServiceCodes.Errors.NULL_ARGUMENT)
        }

        for(def jack : jacks) {

            if(jack.containerStoreId) {
                def resp = rest.delete("${server.boUrl}${USER_PATH}/${jack.containerStoreId}") {
                    //auth System.getProperty("artifactory.user"), System.getProperty("artifactory.pass")
                    contentType "application/vnd.org.jfrog.artifactory.security.Group+json"
                    json {}
                }

                if (resp.status == 200) {
                    if (resp.json.status == "ok") {
                        jack.delete()
                    } else {
                        log.error "Received an error response from BO server: ${resp.text}"

                        return false
                    }
                } else {
                    return false
                }
            }
        }

        return true
    }


    boolean updateJacks(AuthenticationServer server, User user, List<Jack> jacks) {

        //TODO:
    }
}
