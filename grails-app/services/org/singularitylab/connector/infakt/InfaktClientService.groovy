package org.singularitylab.connector.infakt

import org.singularitylab.connector.infakt.dto.Client

/**
 * Service providing CRUD operations for Infakt Clients.
 *
 * @author Jakub Dzon
 *
 */
class InfaktClientService extends InfaktService {

    def serviceUrl = infaktUrl + "/clients.json"

    /**
     * Retrieve client data for one's VAT ID number
     *
     * @param vatId VAT ID number of a client
     * @return {@link Client} object representing a client stored in Infakt
     */
    def getClient(String vatId) {
        def resp = restBuilder.get(serviceUrl + "?q[nip_eq]=${vatId}") {
            header HEADER_API_KEY, infaktApiKey
        }
        resp.json.entities.collect {
            new Client(it)
        }
    }
}
