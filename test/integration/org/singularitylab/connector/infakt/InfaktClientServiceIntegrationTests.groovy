package org.singularitylab.connector.infakt

import grails.test.mixin.TestFor
import org.junit.Test

/**
 *
 *
 * @author Jakub Dzon
 *
 */
@TestFor(InfaktClientService)
class InfaktClientServiceIntegrationTests {

    @Test
    void shouldGetDataFromInfakt() {
        println service.getClient("5260304484")
    }


}
