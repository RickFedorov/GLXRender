package com.github.glxrender.glx;

import java.util.ArrayList;
import java.util.List;

public class Galaxy {

    private List<SpaceObject> spaceObjects = new ArrayList<>();
    private int size = 250;


    public Galaxy(){
        int min = 1;
        int max = size;
        for (int i = 0; i < 100; i++){
            spaceObjects.add(new SpaceObject(
                    (int) (Math.random() * ((max - min) + 1)) + min,
                    (int) (Math.random() * ((max - min) + 1)) + min));
        }
    }

    public List<SpaceObject> getSpaceObjects() {
        return spaceObjects;
    }

    public int getSize() {
        return size;
    }
}
