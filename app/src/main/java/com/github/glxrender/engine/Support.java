package com.github.glxrender.engine;

import java.util.Collection;

public class Support {

    public static <T> boolean isEmpty(T var){
        if (var == null){
            return true;
        }

        if (var == ""){
            return true;
        }

        return false;
    }

    public static <T> boolean isEmpty(Collection<T> var){
        if (var == null){
            return true;
        }

        if (var.size() == 0){
            return true;
        }

        return false;
    }

    public static <T> boolean isEmpty(T[] var){
        if (var == null){
            return true;
        }

        if (var.length == 0){
            return true;
        }

        return false;
    }


}
