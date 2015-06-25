package org.singularitylab.connector.infakt

import com.opencsv.CSVWriter
import groovy.transform.ToString
import org.singularitylab.connector.infakt.dto.Invoice
import org.singularitylab.connector.infakt.dto.Service

class InvoiceExporterService {

    def exportToCsv(List<Invoice> invoices) {
        def sw = new StringWriter()
        CSVWriter w = new CSVWriter(sw, ',' as char, '"' as char, '\\' as char)
        w.writeNext([
                "nr faktury", "typ faktury", "data wystawienia", "data sprzedaży/dostawy", "termin płatności",
                "NumerDokKorygowanego", "Data dok. Korygowanego", "skrót kontrahtenta",
                "Nazwa kontrahenta", "Nip kontrahenta", "kodpoczt", "miasto ", "ulica", "nr domu", "nr lokalu",
                "Opis wykonannej usługi",
                "netto", "vat", "brutto",
                "netto23", "vat23", "brutto23",
                "netto8", "vat8", "bruto8",
                "netto0", "vat0", "bruto0",
                "nettoNp", "vatNp", "brutoNp"
        ] as String[])
        invoices.collect {
            def taxSymbols = it.services.inject([:]) { result, next ->
                if (result[next.tax_symbol]) {
                    result[next.tax_symbol].add(next.net_price, next.gross_price, next.tax_price)
                } else {
                    result[next.tax_symbol] = new Prices(netPrice: next.net_price, grossPrice: next.gross_price, taxPrice: next.tax_price)
                }
                result
            }
            def currency = it.currency
            def companyName = it.client_company_name.replace('"', '')
            [
                    it.number,
                    it.kind,
                    it.invoice_date,
                    it.sale_date,
                    it.payment_date,
                    "", "", shorten(companyName),
                    companyName, it.client_tax_code, it.client_post_code, it.client_city, it.client_street, "", "",
                    getServiceDescription(it.services),
                    toDecimalCurrency(it.net_price, currency), toDecimalCurrency(it.tax_price, currency), toDecimalCurrency(it.gross_price, currency),
                    toDecimalCurrency(taxSymbols["23"]?.netPrice, currency), toDecimalCurrency(taxSymbols["23"]?.taxPrice, currency), toDecimalCurrency(taxSymbols["23"]?.grossPrice, currency),
                    toDecimalCurrency(taxSymbols["8"]?.netPrice, currency), toDecimalCurrency(taxSymbols["8"]?.taxPrice, currency), toDecimalCurrency(taxSymbols["8"]?.grossPrice, currency),
                    toDecimalCurrency(taxSymbols["0"]?.netPrice, currency), toDecimalCurrency(taxSymbols["0"]?.taxPrice, currency), toDecimalCurrency(taxSymbols["0"]?.grossPrice, currency),
                    toDecimalCurrency(taxSymbols["np"]?.netPrice, currency), toDecimalCurrency(taxSymbols["np"]?.taxPrice, currency), toDecimalCurrency(taxSymbols["np"]?.grossPrice, currency),

            ] as String[]
        }.each {
            w.writeNext(it)
        }

        def string = sw.toString()
        def fw = new FileWriter("/tmp/csv.csv")
        fw.write(string)
        fw.flush()

        string
    }

    def toDecimalCurrency(Integer price, Invoice.Currency currency) {

        "${(new BigDecimal(price ?: 0) / 100).toString()} ${currency}"
    }

    def getServiceDescription(List<Service> services) {
        def description = services.inject(null) { result, next ->
            result ? "${result} ${next.name}" : next.name
        }
        shorten(description)
    }

    private def shorten(def string) {
        def newString = string
        if (string.length() > 40) {
            newString = string.subSequence(0, 39)
        }
        newString
    }

    @ToString
    private static class Prices {
        int netPrice
        int grossPrice
        int taxPrice

        def add(int netPrice,
                int grossPrice,
                int taxPrice) {
            this.netPrice += netPrice
            this.grossPrice += grossPrice
            this.taxPrice += taxPrice
        }
    }
}
