package com.solid.i.good;

import com.solid.i.ComplexInvoice;
import com.solid.i.Invoice;

import java.sql.SQLOutput;

public class HtmlInvoicePrinter implements GoodInvoicePrinter_I {
    @Override
    public void print(Invoice invoice) {
        System.out.println(invoice.getNumber()+" HTML");

    }


}
