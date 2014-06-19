package org.singularitylab.connector.infakt

import grails.test.mixin.TestFor
import org.junit.Test

import static org.junit.Assert.*

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
        //Given
        def nip = "5260304484"

        //When
        def clients = service.getClient(nip)

        //Then
        assertNotNull clients
        assertEquals clients.size(), 1
        assertEquals nip, clients.get(0).nip
    }

    @Test
    void shouldNotGetDataFromInfaktForNotFoundClient() {
        //Given
        def nip = "111111"

        //When
        def clients = service.getClient(nip)

        //Then
        assertNotNull clients
        assertEquals clients.size(), 0
    }
}
