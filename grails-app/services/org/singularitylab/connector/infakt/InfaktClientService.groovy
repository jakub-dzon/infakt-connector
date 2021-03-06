package org.singularitylab.connector.infakt

import org.singularitylab.connector.infakt.dto.Client

import static java.lang.String.format

/**
 * Service providing CRUD operations for Infakt Clients.
 *
 * @author Jakub Dzon
 *
 */
class InfaktClientService extends InfaktService {


    InfaktClientService() {
        super("clients")
    }

    /**
     * Create client with data provided in {@code clientDto} in Infakt.
     * @param clientDto Client data
     * @return client data stored in Infakt after the create operation succeeds
     */
    def create(Client clientDto) {
        def resp = restBuilder.post(serviceUrl) {
            header HEADER_API_KEY, infaktApiKey
            json { [client: clientDto] }
        }

        if (log.isDebugEnabled()) {
            log.debug("Received response from Infakt: ${resp.text}")
        }

        new Client(resp.json)
    }

    /**
     * Update client with data provided in {@code clientDto} in Infakt.
     * @param clientDto Client data
     * @return client data stored in Infakt after the create operation succeeds
     */
    def update(Client clientDto) {
        def resp = restBuilder.put(format(updateServiceUrl, clientDto.id)) {
            contentType "application/json; charset=UTF-8"
            header HEADER_API_KEY, infaktApiKey
            json { [client: clientDto] }
        }

        if (log.isDebugEnabled()) {
            log.debug("Received response from Infakt: ${resp.text}")
        }

        new Client(resp.json)
    }

    def findOne(String property, def value) {
        def list = find(property, value)
        if (list.size() > 0) {
            return list[0]
        }

        return null
    }

    /**
     * Retrieve client data for one's {@code property} equal to {@code value}
     *
     * @param property the name of the client's property to search by
     * @param value value of the {@code property} the clients will be retrieved for
     * @return {@link Client} object representing a client stored in Infakt
     */
    def find(String property, def value) {
        def resp = restBuilder.get(serviceUrl + "?q[${property}_eq]=${value}") {
            header HEADER_API_KEY, infaktApiKey
        }

        if (log.isDebugEnabled()) {
            log.debug("Received response from Infakt: ${resp.text}")
        }

        resp.json.entities.collect {
            new Client(it)
        }
    }

    /**
     * If the {@code name} starts with {@code findBy} then {@link #find(java.lang.String, java.lang.Object)}
     * method is called with {@code property} equal to string following "findBy" in lower case and with {@code value}
     * equal to the original argument.<br/>For example:<br/>
     * {@code findByNip ("1234")} will delegate the call to {@code find ("nip", "1234")}
     */
    def methodMissing(String name, args) {
        if (name.startsWith("findBy")) {
            def parameter = name.substring(6).toLowerCase()
            return findOne(parameter, args[0])
        } else {
            throw new MissingMethodException(name, delegate, args)
        }

    }
}
