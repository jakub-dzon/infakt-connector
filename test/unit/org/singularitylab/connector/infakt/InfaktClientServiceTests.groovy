package org.singularitylab.connector.infakt

import grails.plugins.rest.client.RestBuilder
import grails.test.mixin.TestFor
import org.junit.Test

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(InfaktClientService)
class InfaktClientServiceTests {

    @Test
    void shouldGetClientByNip() {
        //Given
        def restBuilder = mockFor(RestBuilder)
        restBuilder.demand.get(1) { String arg -> println arg }
        service.restBuilder = restBuilder.createMock()
        service
        def vatId = "123-123-12-12"

        //When
        def client = service.getClient vatId

        //Then
        restBuilder.verify()
    }
}
