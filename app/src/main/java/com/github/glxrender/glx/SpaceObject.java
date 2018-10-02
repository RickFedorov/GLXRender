package com.github.glxrender.glx;

import android.support.annotation.NonNull;

public class SpaceObject {

    private int x;
    private int y;

    private int xView;
    private int yView;

    SpaceObject(int x, int y) {
        this.x = x;
        this.y = y;
        xView = x;
        yView = y;
    }

    /**
     *
     * @param xMulti screen / galaxy size
     * @param yMulti screen / galaxy size
     * @param dx    drag movement on x
     * @param dy    drag movement on y
     * @param scale scale factor
     */
    public void setXYView(float xMulti, float yMulti, float dx, float dy, float scale) {
        xView = (int) (dx + x * xMulti * scale);
        yView = (int) (dy + y * yMulti * scale);
    }


    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getXView() {
        return xView;
    }

    public int getYView() {
        return yView;
    }


    @NonNull
    @Override
    public String toString() {
        return "SpaceObject[" + x + ", " + y + "]";
    }
}
