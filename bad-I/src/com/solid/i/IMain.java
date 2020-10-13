package com.solid.i;

import java.util.Date;

import com.solid.i.bad.BadInvoicePrinter;
import com.solid.i.bad.BadInvoicePrinter_I;
import com.solid.i.bad.SomeOtherBadInvoicePrinter;

public class IMain {

    public static void main(String[] args) {
        testBadI();
    }

    private static void testBadI() {
        Invoice invoice = new Invoice(-132523);
        ComplexInvoice complexInvoice = new ComplexInvoice(21439, new Date());

        BadInvoicePrinter_I badInvoicePrinter = new BadInvoicePrinter();
        badInvoicePrinter.print(invoice);
        badInvoicePrinter.printComplexInvoice(complexInvoice);
        badInvoicePrinter.someOtherPrintMethod(invoice); //why should I be able to do this?

        BadInvoicePrinter_I someOtherBadInvoicePrinter = new SomeOtherBadInvoicePrinter();
        someOtherBadInvoicePrinter.print(invoice); //why should I be able to do this?
        someOtherBadInvoicePrinter.printComplexInvoice(complexInvoice); //why should I be able to do this?
        someOtherBadInvoicePrinter.someOtherPrintMethod(invoice);
    }
}