package com.github.glxrender.drawings;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.github.glxrender.glx.Galaxy;
import com.github.glxrender.glx.SpaceObject;

public class GLXView extends View {

    private Galaxy galaxy;

    private int width;
    private int height;

    private BasePaint backgroundPaint;
    private BasePaint dotPaint;
    private BasePaint textPaint;

    public GLXView(Context context) {
        super(context);
        init(context);
    }

    public GLXView (Context context, AttributeSet attrs){
        super(context, attrs);
        init(context);
    }

    private void init(Context context){
        galaxy = new Galaxy();

        backgroundPaint = new BasePaint.Builder(BasePaint.black)
                .setAntiAlias(true)
                .setStrokeCap(Paint.Cap.ROUND)
                .setStrokeWidth(4)
                .build();
        dotPaint = new BasePaint.Builder(BasePaint.red)
                .setAntiAlias(true)
                .setStrokeCap(Paint.Cap.ROUND)
                .setStrokeWidth(4)
                .build();
        textPaint = new BasePaint.Builder(BasePaint.white)
                .setAntiAlias(true)
                .setStrokeCap(Paint.Cap.ROUND)
                .setStrokeWidth(4)
                .build();
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.e("GLXrender", "onMeasure");

        height = getMeasuredHeight();
        width = getMeasuredWidth();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.e("GLXrender", "onDraw");

        int xMult = (int) width / galaxy.getSize();
        int yMult = (int) height / galaxy.getSize();


        //canvas.drawPaint(backgroundPaint);

        for (SpaceObject ob:
             galaxy.getSpaceObjects()) {
            canvas.drawCircle(ob.getX()*xMult, ob.getY()*yMult, 5, dotPaint);
            canvas.drawText(ob.toString(),ob.getX()*xMult + 5, ob.getY()*yMult + 5, dotPaint);
        }

    }

}
