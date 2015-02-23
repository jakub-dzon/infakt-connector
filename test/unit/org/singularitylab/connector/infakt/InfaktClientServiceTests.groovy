package org.singularitylab.connector.infakt

import grails.plugins.rest.client.RestBuilder
import grails.test.mixin.TestFor
import org.codehaus.groovy.grails.web.json.JSONObject
import org.junit.Before
import org.junit.Test
import org.singularitylab.connector.infakt.dto.Client

/**
 * See the API for {@link grails.test.mixin.services.ServiceUnitTestMixin} for usage instructions
 */
@TestFor(InfaktClientService)
class InfaktClientServiceTests {

    @Test
    void shouldGetClientByNip() {
        //Given
        def vatId = "5260304484"
        def restBuilder = mockFor(RestBuilder)
        restBuilder.demand.get(1) { String arg, Closure x -> [json: new JSONObject("{\"metainfo\":{\"count\":1,\"total_count\":1,\"next\":\"https://api.infakt.pl/api/v3/clients.json?q%5Bnip_eq%5D=5260304484&offset=10\",\"previous\":\"https://api.infakt.pl/api/v3/clients.json?q%5Bnip_eq%5D=5260304484&offset=0\"},\"entities\":[{\"id\":1568545,\"company_name\":\"ABB sp. z o.o.\",\"street\":\"Å»egaÅ\u0084ska 1\",\"city\":\"Warszawa\",\"country\":\"PL\",\"postal_code\":\"04-713\",\"nip\":\"5260304484\",\"phone_number\":\"222238610\",\"web_site\":\"\",\"email\":\"agnieszka.michalska@pl.abb.com\",\"note\":\"\",\"receiver\":\"\",\"mailing_company_name\":\"\",\"mailing_street\":\"\",\"mailing_city\":\"\",\"mailing_postal_code\":\"\",\"days_to_payment\":\"\",\"payment_method\":\"\",\"invoice_note\":\"\",\"same_forward_address\":true}]}")] }
        service.restBuilder = restBuilder.createMock()

        //When
        def clients = service.find "nip", vatId

        //Then
        restBuilder.verify()
        assertNotNull clients
        assertEquals clients.size(), 1
        assertEquals vatId, clients.get(0).nip
        assertEquals "ABB sp. z o.o.", clients.get(0).company_name
    }

    @Test
    void shouldNotFindClientByNip() {
        //Given
        def vatId = "111111"
        def restBuilder = mockFor(RestBuilder)
        restBuilder.demand.get(1) { String arg, Closure x -> [json: new JSONObject("{\"metainfo\":{\"count\":0,\"total_count\":0,\"next\":\"https://api.infakt.pl/api/v3/clients.json?q%5Bnip_eq%5D=111111&offset=10\",\"previous\":\"https://api.infakt.pl/api/v3/clients.json?q%5Bnip_eq%5D=111111&offset=0\"},\"entities\":[]}")] }
        service.restBuilder = restBuilder.createMock()

        //When
        def clients = service.find "nip", vatId

        //Then
        restBuilder.verify()
        assertNotNull clients
        assertEquals clients.size(), 0
    }

    @Test
    void shouldCreateClient() {
        //Given
        def client = new Client(company_name: "Test Client", country: "PL")

        def restBuilder = mockFor(RestBuilder)
        restBuilder.demand.post(1) { String url, Closure c -> [json: new JSONObject("{\"id\":2432262,\"company_name\":\"Test client\",\"street\":\"\",\"city\":\"\",\"country\":\"PL\",\"postal_code\":\"\",\"nip\":\"\",\"phone_number\":\"\",\"web_site\":\"\",\"email\":\"\",\"note\":\"\",\"receiver\":\"\",\"mailing_company_name\":\"\",\"mailing_street\":\"\",\"mailing_city\":\"\",\"mailing_postal_code\":\"\",\"days_to_payment\":\"\",\"payment_method\":\"\",\"invoice_note\":\"\",\"same_forward_address\":true}")]

        }
        service.restBuilder = restBuilder.createMock()

        //When
        def createdClient = service.create client

        //Then
        assertNotNull createdClient
        assertEquals 2432262, createdClient.id
    }

    @Test
    void shouldInvokeMetodWorkForNip() {
        //Given
        def vatId = "5260304484"
        def url = ""

        def restBuilder = mockFor(RestBuilder)
        restBuilder.demand.get(1) { String arg, Closure x ->
            url = arg
            [json: new JSONObject("{\"metainfo\":{\"count\":1,\"total_count\":1,\"next\":\"https://api.infakt.pl/api/v3/clients.json?q%5Bnip_eq%5D=5260304484&offset=10\",\"previous\":\"https://api.infakt.pl/api/v3/clients.json?q%5Bnip_eq%5D=5260304484&offset=0\"},\"entities\":[{\"id\":1568545,\"company_name\":\"ABB sp. z o.o.\",\"street\":\"Å»egaÅ\u0084ska 1\",\"city\":\"Warszawa\",\"country\":\"PL\",\"postal_code\":\"04-713\",\"nip\":\"5260304484\",\"phone_number\":\"222238610\",\"web_site\":\"\",\"email\":\"agnieszka.michalska@pl.abb.com\",\"note\":\"\",\"receiver\":\"\",\"mailing_company_name\":\"\",\"mailing_street\":\"\",\"mailing_city\":\"\",\"mailing_postal_code\":\"\",\"days_to_payment\":\"\",\"payment_method\":\"\",\"invoice_note\":\"\",\"same_forward_address\":true}]}")]
        }

        service.restBuilder = restBuilder.createMock()

        //When
        service.findByNip vatId

        //Then
        restBuilder.verify()
        assertTrue url.contains("nip_eq")
        assertTrue url.contains(vatId)


    }

    @Test
    void shouldInvokeMetodWorkForCustomParameter() {
        //Given
        def value = "someValue"
        def url = ""

        def restBuilder = mockFor(RestBuilder)
        restBuilder.demand.get(1) { String arg, Closure x ->
            url = arg
            [json: new JSONObject("{\"metainfo\":{\"count\":1,\"total_count\":1,\"next\":\"https://api.infakt.pl/api/v3/clients.json?q%5Bnip_eq%5D=5260304484&offset=10\",\"previous\":\"https://api.infakt.pl/api/v3/clients.json?q%5Bnip_eq%5D=5260304484&offset=0\"},\"entities\":[{\"id\":1568545,\"company_name\":\"ABB sp. z o.o.\",\"street\":\"Å»egaÅ\u0084ska 1\",\"city\":\"Warszawa\",\"country\":\"PL\",\"postal_code\":\"04-713\",\"nip\":\"5260304484\",\"phone_number\":\"222238610\",\"web_site\":\"\",\"email\":\"agnieszka.michalska@pl.abb.com\",\"note\":\"\",\"receiver\":\"\",\"mailing_company_name\":\"\",\"mailing_street\":\"\",\"mailing_city\":\"\",\"mailing_postal_code\":\"\",\"days_to_payment\":\"\",\"payment_method\":\"\",\"invoice_note\":\"\",\"same_forward_address\":true}]}")]
        }

        service.restBuilder = restBuilder.createMock()

        //When
        service.findByCustom value

        //Then
        restBuilder.verify()
        assertTrue url.contains("custom_eq")
        assertTrue url.contains(value)


    }
}
