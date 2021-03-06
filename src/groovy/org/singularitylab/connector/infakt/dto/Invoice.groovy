package org.singularitylab.connector.infakt.dto

import groovy.transform.ToString

/**
 *
 *
 * @author Jakub Dzon
 *
 */
@ToString
class Invoice {
    int id
    String number
    Currency currency
    int paid_price
    String notes
    String kind
    String payment_method
    String recipient_signature
    String seller_signature
    String invoice_date
    String sale_date
    String payment_date
    int net_price
    int tax_price
    int gross_price
    int client_id
    String client_company_name
    String client_street
    String client_city
    String client_post_code
    String client_tax_code
    String client_country
    boolean check_duplicate_number
    String bank_name
    String bank_account
    List<Service> services
    String paid_date
    String sale_type
    String invoice_date_kind
    String status
    String vat_exemption_reason
    String swift
    String vat_exemption_reason_id


    static enum Currency {
        THB, USD, AUD, HKD, CAD, NZD, SGD,
        EUR, HUF, CHF, GBP, UAH, JPY, CZK,
        DKK, ISK, NOK, SEK, HRK, RON, BGN,
        TRY, LVL, PHP, MXN, ZAR, BRL, MYR,
        RUB, IDR, KRW, CNY, INR, LTL, PLN
    }

    static enum Kind {
        FINAL,
        ADVANCE,
        MARGIN,
        PROFORMA,
        VAT

        @Override
        public String toString() {
            return name().toLowerCase()
        }
    }

    static enum Status {
        DRAFT,
        SENT,
        PRINTED,
        PAID

        @Override
        public String toString() {
            return name().toLowerCase()
        }
    }

    static enum SaleType {
        MERCHANDISE,
        SERVICE

        @Override
        public String toString() {
            return name().toLowerCase()
        }

    }
}
