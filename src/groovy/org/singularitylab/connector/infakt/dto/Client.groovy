package org.singularitylab.connector.infakt.dto

/**
 *
 *
 * @author Jakub Dzon
 *
 */
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

}
