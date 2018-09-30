package com.github.glxrender.glx;

public class SpaceObject {

    private int x;
    private int y;

    SpaceObject(int x, int y){
        this.x = x;
        this.y = y;
    }


    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return "SpaceObject["+ x +", "+ y + "]";
    }
}
