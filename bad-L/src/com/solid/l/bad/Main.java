package com.solid.l.bad;

public class Main {
    public static void main(String[] args) {
        BadRectangle badRectangle=new BadRectangle(12,13);
        BadSquare badSquare=new BadSquare(4,4);
        BadGraphicsService badService= new BadGraphicsService();
        badService.checkForArea(badRectangle);
        badService.verify(badRectangle,4,4,"square");

    }
    }
