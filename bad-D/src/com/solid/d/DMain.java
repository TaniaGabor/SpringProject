package com.solid.d;

import com.solid.d.bad.BadPrintingService;
import com.solid.i.Invoice;

public class DMain {

    public static void main(String[] args) {
        testBadD();
    }

    private static void testBadD() {
        Invoice invoice = new Invoice(665);

        BadPrintingService badPrintingService = new BadPrintingService();
        badPrintingService.print(invoice);

        // now what?
        // badPrintingService = new BadPrintingService(new HtmlInvoicePrinter()); // but why!?
    }
}