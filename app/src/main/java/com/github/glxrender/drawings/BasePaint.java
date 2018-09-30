package com.github.glxrender.drawings;

import android.graphics.Paint;

import com.github.glxrender.R;

public class BasePaint extends Paint {

    public static int black = R.color.black;
    public static int white = R.color.white;
    public static int red = R.color.red;

    public BasePaint(Builder builder){
        super(builder.paint);
        
    }

    public static class Builder{
        Paint paint;

        public Builder(int color) {
            paint = new Paint();
            paint.setColor(color);
        }

        public Builder setStrokeCap(Paint.Cap strokeCap) {
            paint.setStrokeCap(strokeCap);
            return this;
        }

        public Builder setStyle(Paint.Style style) {
            paint.setStyle(style);
            return this;
        }

        public Builder setStrokeWidth(int strokeWidth) {
            if (strokeWidth == 0){
                strokeWidth = 8;
            }
            paint.setStrokeWidth(strokeWidth);
            return this;
        }

        public Builder setAntiAlias(boolean antiAlias) {
            paint.setAntiAlias(antiAlias);
            return this;
        }        
        
        
        public BasePaint build(){ return new BasePaint(this);}
    }
}
