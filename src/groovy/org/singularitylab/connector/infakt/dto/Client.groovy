package org.singularitylab.connector.infakt.dto

import groovy.transform.ToString

/**
 * Representation of Infakt Client.
 *
 * @author Jakub Dzon
 *
 */
@ToString
class Client {
    int id
    String company_name
    String street
    String city
    String country
    String postal_code
    String nip
    String phone_number
    boolean same_forward_address
    String web_site
    String email
    String note
    String receiver
    String mailing_company_name
    String mailing_street
    String mailing_city
    String mailing_postal_code
    int days_to_payment
    String invoice_note
    PaymentMethod payment_method

    void setPayment_method(String payment_method) {
        if (payment_method) {
            this.payment_method = PaymentMethod.valueOf(payment_method)
        }
    }

    void setId(String id) {
        this.id = Integer.valueOf(id)
    }

    void setSame_forward_address(String same_forward_address) {
        if (same_forward_address) {
            this.same_forward_address = Boolean.valueOf(same_forward_address)
        }
    }

    void setDays_to_payment(String days_to_payment) {
        if (days_to_payment) {
            this.days_to_payment = Integer.valueOf(days_to_payment)
        }
    }
}
