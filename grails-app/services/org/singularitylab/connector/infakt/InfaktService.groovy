package org.singularitylab.connector.infakt

import grails.plugins.rest.client.RestBuilder
import grails.util.Holders
import org.springframework.beans.factory.InitializingBean
import org.springframework.http.converter.StringHttpMessageConverter

import java.nio.charset.Charset

/**
 * Base Infakt Service class providing common configuration and functionality.
 *
 * @author Jakub Dzon
 *
 */
abstract class InfaktService implements InitializingBean {
    public static final String HEADER_API_KEY = 'X-inFakt-ApiKey'

    def infaktUrl = Holders.config.infakt.api.url

    def infaktApiKey = Holders.config.infakt.api.key

    RestBuilder restBuilder

    protected def serviceUrl

    protected def updateServiceUrl

    protected def contextServiceUrl

    InfaktService(def context) {
        this.contextServiceUrl = infaktUrl + "/${context}"
        this.serviceUrl = contextServiceUrl + ".json"
        this.updateServiceUrl =contextServiceUrl+"/%s.json"
    }

    void afterPropertiesSet() {
        if (restBuilder != null) {
            restBuilder.restTemplate.setMessageConverters([new StringHttpMessageConverter(Charset.forName("UTF-8"))])
        }
    }
}
