package com.thoughtworks.example;

public class Mars implements IMars {
    private int height;
    private int width;

    @Override
    public void setRange(int width, int height) {
        this.width = width;
        this.height = height;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }
}
