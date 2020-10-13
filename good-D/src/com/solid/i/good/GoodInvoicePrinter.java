package com.solid.i.good;

import com.solid.i.ComplexInvoice;
import com.solid.i.Invoice;

public class GoodInvoicePrinter implements GoodInvoicePrinter_I {

    @Override
    public void print(Invoice invoice) {
        System.out.println("Printing invoice from Good " + invoice.getNumber());
    }





}