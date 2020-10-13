package com.solid.d.bad;

import com.solid.i.Invoice;
import com.solid.i.good.GoodInvoicePrinter;
import com.solid.i.good.GoodInvoicePrinter_I;
import com.solid.i.good.HtmlInvoicePrinter;

public class BadPrintingService {

    private GoodInvoicePrinter_I invoicePrinter; // notice the lack of an interface!
    public BadPrintingService(Invoice invoice) {
        this.invoicePrinter = new GoodInvoicePrinter();
    }
    public BadPrintingService(HtmlInvoicePrinter html) {
        this.invoicePrinter = html;
    }
    public void print(Invoice invoice) {
        invoicePrinter.print(invoice);
    }

}