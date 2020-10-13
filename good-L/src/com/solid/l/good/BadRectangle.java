package com.solid.l.good;

public class BadRectangle extends Shape {



    BadRectangle(int height, int width) {
        this.height = height;
        this.width = width;
    }





    public int getArea() {
        return this.height * this.width;
    }

}