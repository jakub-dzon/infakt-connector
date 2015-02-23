package org.singularitylab.connector.infakt

import org.junit.Test
import org.singularitylab.connector.infakt.dto.Client

import static org.junit.Assert.*

/**
 *
 *
 * @author Jakub Dzon
 *
 */
class InfaktClientServiceIntegrationTests extends GroovyTestCase {

    private static final String TEST_CLIENT_NAME = "Zażółć gęślą jaźń"

    def infaktClientService

    @Test
    void shouldGetDataFromInfaktByNip() {
        //Given
        def nip = "5260304484"

        //When
        def client = infaktClientService.findByNip(nip)

        //Then
        assertNotNull client
        assertEquals nip, client.nip
    }

    @Test
    void shouldGetDataFromInfaktByEmail() {
        //Given
        def email = "jakub@dzon.pl"

        //When
        def client = infaktClientService.findByEmail(email)

        //Then
        assertNotNull client
        assertEquals email, client.email
    }

    @Test
    void shouldNotGetDataFromInfaktByNipForNotFoundClient() {
        //Given
        def nip = "111111"

        //When
        def client = infaktClientService.findByNip(nip)

        //Then
        assertNull client
    }

    @Test
    void shouldCreateClient() {
        //Given
        def client = new Client(company_name: TEST_CLIENT_NAME, country: "PL")

        //When
        def createdClient = infaktClientService.create client

        //Then
        assertNotNull createdClient
        assertNotNull createdClient.id
        assertTrue createdClient.id > 0
    }

    @Test
    void shouldUpdateClient() {
        //Given
        def client = new Client(company_name: TEST_CLIENT_NAME, country: "PL")
        def createdClient = infaktClientService.create client
        createdClient.country = "DE"

        //When
        def updatedClient = infaktClientService.update createdClient

        //Then
        assertNotNull updatedClient
        assertNotNull updatedClient.id
        assertTrue updatedClient.id > 0
        assertEquals updatedClient.id, createdClient.id
        assertEquals updatedClient.country, "DE"
    }
}
