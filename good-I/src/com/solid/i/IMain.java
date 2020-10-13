package com.solid.i;

import com.solid.i.good.GoodInvoicePrinter;
import com.solid.i.good.GoodInvoicePrinter_I;
import com.solid.i.good.SomeOtherGoodInvoicePrinter;
import com.solid.i.good.SomeOtherGoodInvoicePrinter_I;

import java.util.Date;



public class IMain {

    public static void main(String[] args) {
        testGoodI();
    }

    private static void testGoodI() {
        Invoice invoice = new Invoice(423);
        ComplexInvoice complexInvoice = new ComplexInvoice(242, new Date());

        GoodInvoicePrinter_I goodInvoicePrinter = new GoodInvoicePrinter();
        goodInvoicePrinter.print(invoice);
        goodInvoicePrinter.printComplexInvoice(complexInvoice);
        // goodInvoicePrinter.someOtherPrintMethod(invoice); // unavailable! great.


        SomeOtherGoodInvoicePrinter_I someOtherGoodInvoicePrinter =  new SomeOtherGoodInvoicePrinter();
        // other 2 methods are unavailable
        someOtherGoodInvoicePrinter.someOtherPrintMethod(invoice);
    }
}