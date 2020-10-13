package com.solid.l.good;

public class BadSquare extends BadRectangle {

    public BadSquare(int height, int width) {
        super(height, width);
    }



    @Override
    public String toString() {
        return "I am a square";
    }
}
