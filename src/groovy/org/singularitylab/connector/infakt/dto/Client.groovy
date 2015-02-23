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
    Boolean same_forward_address
    String web_site
    String email
    String note
    String receiver
    String mailing_company_name
    String mailing_street
    String mailing_city
    String mailing_postal_code
    Integer days_to_payment
    String invoice_note
    PaymentMethod payment_method

    void setPayment_method(String payment_method) {
        if (payment_method) {
            this.payment_method = PaymentMethod.valueOf(payment_method)
        }
    }

    void setId(String id) {
        this.id = Long.valueOf(id)
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

    boolean equals(o) {
        if (this.is(o)) return true
        if (getClass() != o.class) return false

        Client client = (Client) o

        if (city != client.city) return false
        if (company_name != client.company_name) return false
        if (country != client.country) return false
        if (email != client.email) return false
        if (mailing_city != client.mailing_city) return false
        if (mailing_company_name != client.mailing_company_name) return false
        if (mailing_postal_code != client.mailing_postal_code) return false
        if (mailing_street != client.mailing_street) return false
        if (nip != client.nip) return false
        if (phone_number != client.phone_number) return false
        if (postal_code != client.postal_code) return false
        if (receiver != client.receiver) return false
        if (same_forward_address != client.same_forward_address) return false
        if (street != client.street) return false
        if (web_site != client.web_site) return false

        return true
    }

    int hashCode() {
        int result
        result = (company_name != null ? company_name.hashCode() : 0)
        result = 31 * result + (street != null ? street.hashCode() : 0)
        result = 31 * result + (city != null ? city.hashCode() : 0)
        result = 31 * result + (country != null ? country.hashCode() : 0)
        result = 31 * result + (postal_code != null ? postal_code.hashCode() : 0)
        result = 31 * result + (nip != null ? nip.hashCode() : 0)
        result = 31 * result + (phone_number != null ? phone_number.hashCode() : 0)
        result = 31 * result + (same_forward_address != null ? same_forward_address.hashCode() : 0)
        result = 31 * result + (web_site != null ? web_site.hashCode() : 0)
        result = 31 * result + (email != null ? email.hashCode() : 0)
        result = 31 * result + (receiver != null ? receiver.hashCode() : 0)
        result = 31 * result + (mailing_company_name != null ? mailing_company_name.hashCode() : 0)
        result = 31 * result + (mailing_street != null ? mailing_street.hashCode() : 0)
        result = 31 * result + (mailing_city != null ? mailing_city.hashCode() : 0)
        result = 31 * result + (mailing_postal_code != null ? mailing_postal_code.hashCode() : 0)
        return result
    }
}
