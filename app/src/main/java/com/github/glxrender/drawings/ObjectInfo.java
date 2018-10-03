package com.github.glxrender.drawings;

import android.content.Context;
import android.support.v7.widget.LinearLayoutCompat;
import android.widget.RelativeLayout;

import com.github.glxrender.R;
import com.github.glxrender.glx.SpaceObject;

public class ObjectInfo extends LinearLayoutCompat {

    private final SpaceObject spaceObject;
    private final RelativeLayout.LayoutParams params;


    public ObjectInfo(Context context, SpaceObject SpaceObject) {
        super(context);
        this.spaceObject = SpaceObject;
        this.setBackgroundColor(getResources().getColor(R.color.red,null));

        params = new RelativeLayout.LayoutParams(500, 500);
        params.leftMargin = spaceObject.getXView();
        params.topMargin = spaceObject.getYView();
    }

    public RelativeLayout.LayoutParams getParams() {
        return params;
    }
}
