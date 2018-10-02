package com.github.glxrender.glx;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;



public class Galaxy {

    private List<SpaceObject> spaceObjects = new ArrayList<>();
    private final int size = 1000;
    private final int arms = 7;
    private final int objects = 500;
    private final int middleX = 500;
    private final int middleY = 500;

    public Galaxy(){
        generateSpaceObjects();

        /*
        int min = 1;
        int max = size;
        for (int i = 0; i < 100; i++){
            spaceObjects.add(new SpaceObject(
                    (int) (Math.random() * ((max - min) + 1)) + min,
                    (int) (Math.random() * ((max - min) + 1)) + min));
        }*/
    }

    private void generateSpaceObjects(){
        double scale = size / (4.0*Math.PI);
        //compute the angle between arms
        double angle = Math.toRadians(360/arms);

        for (int i = 1; i <=objects; i ++)
        {
            if((i % 500) != 0){
                if(i + 499 > objects) {
                    int end = objects;
                }    
                else {
                    int end = 499;
                }    
                    
            }
                //The Spiral Galaxy Code was provided by "Kelly Shane Harrelson" <shane@mo-ware.com>
				// need to randomly assign this point to an arm.
            int arm = new Random().nextInt( arms + 1);
            double arm_offset = arm * angle;
				// generate the logical position on the spiral (0 being closer to the center).
				// the double rand puts more towards the center.
            //int r = new Random().nextInt(360 + 1);

            double u = Math.toRadians(new Random().nextInt( 360 + 1));
				// generate the base x,y,z location in cartesian form
            double bx = u*Math.cos(u+arm_offset);
            double by = u*Math.sin(u+arm_offset);
				// generate a max delta from the base x, y, z.
				// this will be larger closer to the center,
				// tapering off the further out you are.
				// this will create the bulge like effect in
				// the center.	this is just a rough function
				// and there are probably better ones out there.
            double d = (u<0.3) ? 1.5 : ((Math.log(u)/ Math.log(10))*-1.0)+1.0;	// log base 10
				// generate random angles and distance for offsets from base x,y,z
            double dt = Math.toRadians(new Random().nextInt(360 +1)); // angle theta 0-360
            double dp =  Math.toRadians(new Random().nextInt(360 +1)); // angle phi	0-360
            double dd = d* new Random().nextInt(100)/100;	 // distance	 0-d
				// based on random angles and distance, generate cartesian offsets for base x,y,z
            double dx = dd*Math.sin(dt)*Math.cos(dp);
            double dy = dd*Math.sin(dt)*Math.sin(dp);
				// calcuate final cartesian coordinate
            double x = bx + dx;
            double y = by + dy;
				// now scale them to fit $universe_size
            x *= scale;
            y *= scale;
            x += middleX;
            y += middleY;

            spaceObjects.add(new SpaceObject(
                    (int) x,
                    (int) y));

        }
    }

    public List<SpaceObject> getSpaceObjects() {
        return spaceObjects;
    }

    public int getSize() {
        return size;
    }
}
