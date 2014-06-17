package org.singularitylab.connector.infakt.dto

/**
 *
 *
 * @author Jakub Dzon
 *
 */
enum PaymentMethod {
    TRANSFER,
    CASH,
    CARD,
    BARTER,
    CHECK,
    BILL_OF_SALE,
    DELIVERY,
    COMPENSATION,
    ACCREDITED,
    PAYPAL,
    PAYU,
    OTHER

    @Override
    public String toString() {
        return name().toLowerCase()
    }
}