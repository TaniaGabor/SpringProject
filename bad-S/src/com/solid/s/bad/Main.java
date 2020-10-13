package com.solid.s.bad;

public class Main {
    public static void main(String[] args) {
        BadEmployee employee= new BadEmployee("A","Tania",6);
        System.out.println( employee.reportHours());
     System.out.println(employee.calculatePay());
     employee.save();

    }
}