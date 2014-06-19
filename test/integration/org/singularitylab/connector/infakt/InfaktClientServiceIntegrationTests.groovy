package org.singularitylab.connector.infakt

import grails.test.mixin.TestFor
import org.junit.Test
import org.singularitylab.connector.infakt.dto.Client

import static org.junit.Assert.*

/**
 *
 *
 * @author Jakub Dzon
 *
 */
@TestFor(InfaktClientService)
class InfaktClientServiceIntegrationTests {

    private static final String TEST_CLIENT_NAME = "Test client"

    @Test
    void shouldGetDataFromInfaktByNip() {
        //Given
        def nip = "5260304484"

        //When
        def clients = service.findByNip(nip)

        //Then
        assertNotNull clients
        assertEquals clients.size(), 1
        assertEquals nip, clients.get(0).nip
    }

    @Test
      void shouldGetDataFromInfaktByEmail() {
          //Given
          def email = "jakub@dzon.pl"

          //When
          def clients = service.findByEmail(email)

          //Then
          assertNotNull clients
          assertEquals clients.size(), 1
          assertEquals email, clients.get(0).email
      }

    @Test
    void shouldNotGetDataFromInfaktByNipForNotFoundClient() {
        //Given
        def nip = "111111"

        //When
        def clients = service.findByNip(nip)

        //Then
        assertNotNull clients
        assertEquals clients.size(), 0
    }

    @Test
    void shouldCreateClient() {
        //Given
        def client = new Client(company_name: TEST_CLIENT_NAME, country: "PL")

        //When
        def createdClient = service.create client

        //Then
        assertNotNull createdClient
        assertNotNull createdClient.id
        assertTrue createdClient.id > 0
    }
}
