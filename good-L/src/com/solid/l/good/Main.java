package com.solid.l.good;

public class Main {
    public static void main(String[] args) {
        Shape badRectangle=new BadRectangle(12,13);
        Shape badSquare=new BadSquare(4,4);
        BadGraphicsService badService= new BadGraphicsService();
        badService.checkForArea(badRectangle);
        badService.verify(badRectangle,4,4,"square");
    }
    }