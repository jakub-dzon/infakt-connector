package org.singularitylab.connector.infakt

import org.singularitylab.connector.infakt.dto.Invoice

/**
 * Service providing CRUD operations for Infakt Clients.
 *
 * @author Jakub Dzon
 *
 */
class InfaktInvoiceService extends InfaktService {

    InfaktInvoiceService() {
        super("invoices")
    }

    /**
     * Create invoice with data provided in {@code invoiceDto} in Infakt.
     * @param clientDto Client data
     * @return client data stored in Infakt after the create operation succeeds
     */
    def create(Invoice invoiceDto) {
        def resp = restBuilder.post(serviceUrl) {
            header HEADER_API_KEY, infaktApiKey
            json { [invoice: invoiceDto] }
        }

        if (log.isDebugEnabled()) {
            log.debug("Received response from Infakt: ${resp.text}")
        }

        new Invoice(resp.json)
    }

    def nextNumber(Invoice.Kind kind) {
        def resp = restBuilder.get(contextServiceUrl + "/next_number.json?kind=" + kind) {
            header HEADER_API_KEY, infaktApiKey
        }

        if (log.isDebugEnabled()) {
            log.debug("Received response from Infakt: ${resp.text}")
        }

        resp.json.next_number
    }

    void send(int id, def printType = "regular") {
        def resp = restBuilder.post("${contextServiceUrl}/${id}/deliver_via_email.json?print_type=${printType}") {
            header HEADER_API_KEY, infaktApiKey
        }

        if (log.isDebugEnabled()) {
            log.debug("Received response from Infakt: ${resp.text}")
        }

    }

}
