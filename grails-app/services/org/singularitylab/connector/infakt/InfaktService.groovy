package org.singularitylab.connector.infakt

import grails.plugins.rest.client.RestBuilder
import grails.util.Holders

/**
 * Base Infakt Service class providing common configuration and functionality.
 *
 * @author Jakub Dzon
 *
 */
abstract class InfaktService {
    public static final String HEADER_API_KEY = 'X-inFakt-ApiKey'

    def infaktUrl = Holders.config.infakt.api.url

    def infaktApiKey = Holders.config.infakt.api.key

    def restBuilder = new RestBuilder()
}
