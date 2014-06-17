package org.singularitylab.connector.infakt

import grails.plugins.rest.client.RestBuilder
import grails.util.Holders

class InfaktClientService {

    def infaktUrl = Holders.config.infakt.api.url

    def infaktApiKey = Holders.config.infakt.api.key

    def restBuilder = new RestBuilder()

    def getClient(String vatId) {
        def resp = restBuilder.get(infaktUrl) {
            header 'X-inFakt-ApiKey', infaktApiKey
            json {
                q {
                    nip_eq = vatId
                }
            }
        }
        resp.json
    }
}
