package com.solid.s.good;


public class Main {
    public static void main(String[] args) {
        BadEmployee employee= new BadEmployee("A","Tania",6);
        Report report =new Report(employee);
       System.out.println( report.reportHours());
        Status status= new Status(employee);
        System.out.println( status.calculatePay());
        Save save=new Save(employee);
        save.save();

    }
}