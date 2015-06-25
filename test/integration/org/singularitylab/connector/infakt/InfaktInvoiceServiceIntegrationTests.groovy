package org.singularitylab.connector.infakt

import org.junit.Test
import org.singularitylab.connector.infakt.dto.Client
import org.singularitylab.connector.infakt.dto.Invoice
import org.singularitylab.connector.infakt.dto.Service

import static org.junit.Assert.*
import static org.singularitylab.connector.infakt.dto.Invoice.Kind.PROFORMA
import static org.singularitylab.connector.infakt.dto.Invoice.SaleType.SERVICE
import static org.singularitylab.connector.infakt.dto.PaymentMethod.TRANSFER

/**
 *
 *
 * @author Jakub Dzon
 *
 */
class InfaktInvoiceServiceIntegrationTests extends GroovyTestCase {

    def infaktInvoiceService
    def invoiceExporterService
    def infaktExporterService

    @Test
    void shouldGetNextInvoiceNumber() {
        //When
        String nextNumber = infaktInvoiceService.nextNumber(PROFORMA)

        println(nextNumber)

        //Then
        assertNotNull nextNumber
        assertTrue nextNumber.contains("PROF")
    }

    @Test
    void shouldCreateInvoice() {
        //Given
        def client = new Client(company_name: "Test company", country: "PL")
        client = infaktClientService.create(client)

        def services = [
                new Service(name: "Test service", tax_symbol: "23", quantity: 1, unit_net_price: 10)
        ]

        def invoice = new Invoice(services: services, client_id: client.id, payment_method: TRANSFER, payment_date: new Date(), kind: PROFORMA, bank_account: "23 1111 1111 1111 1111")

        //When
        def createdInvoice = infaktInvoiceService.create invoice

        //Then
        assertNotNull createdInvoice
        assertNotNull createdInvoice.id
        assertTrue createdInvoice.id > 0
    }

    @Test
    void shouldListInvoices() {

        //When
        def invoices = infaktInvoiceService.list('kind', 'vat')
        def invoicesAdvance = infaktInvoiceService.list('kind', 'advance')
        def invoicesFinal = infaktInvoiceService.list('kind', 'final')

        //Then
        println invoices
        println invoicesAdvance
        println invoicesFinal

        def csv = invoiceExporterService.exportToCsv(invoices + invoicesAdvance + invoicesFinal)

    }


}
