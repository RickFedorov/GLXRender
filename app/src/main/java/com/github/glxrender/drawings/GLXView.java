package com.github.glxrender.drawings;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.ScaleGestureDetector;
import android.view.View;

import com.github.glxrender.glx.Galaxy;
import com.github.glxrender.glx.SpaceObject;

public class GLXView extends View implements ScaleGestureDetector.OnScaleGestureListener, GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener {

    private Galaxy galaxy;

    private int width;
    private int height;

    private BasePaint backgroundPaint;
    private BasePaint dotPaint;
    private BasePaint textPaint;


    //ZOOM
    private enum Mode {
        NONE,
        DRAG,
        ZOOM
    }

    private static final float MIN_ZOOM = 1.0f;
    private static final float MAX_ZOOM = 4.0f;

    private Mode mode = Mode.NONE;
    private float scale = 1.0f;
    private float lastScaleFactor = 0f;

    private float startX = 0f;
    private float startY = 0f;

    private float dx = 0f;
    private float dy = 0f;
    private float prevDx = 0f;
    private float prevDy = 0f;


    public GLXView(Context context) {
        super(context);
        init(context);
    }

    public GLXView (Context context, AttributeSet attrs){
        super(context, attrs);
        init(context);
    }

    private void init(Context context){
        final ScaleGestureDetector scaleDetector = new ScaleGestureDetector(context, this);
        final GestureDetector gestureDetector = new GestureDetector(context,this);
        gestureDetector.setOnDoubleTapListener(this);

        this.setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction() & MotionEvent.ACTION_MASK) {
                    case MotionEvent.ACTION_DOWN:
                        if (scale >= MIN_ZOOM) {
                            mode = Mode.DRAG;
                            startX = motionEvent.getX() - prevDx;
                            startY = motionEvent.getY() - prevDy;
                        }
                        break;
                    case MotionEvent.ACTION_MOVE:
                        if (mode == Mode.DRAG) {
                            dx = motionEvent.getX() - startX;
                            dy = motionEvent.getY() - startY;
                        }
                        break;
                    case MotionEvent.ACTION_POINTER_DOWN:
                        mode = Mode.ZOOM;
                        break;
                    case MotionEvent.ACTION_POINTER_UP:
                        mode = Mode.DRAG;
                        break;
                    case MotionEvent.ACTION_UP:
                        mode = Mode.NONE;
                        prevDx = dx;
                        prevDy = dy;
                        break;
                }
                scaleDetector.onTouchEvent(motionEvent);
                gestureDetector.onTouchEvent(motionEvent);


                if ((mode == Mode.DRAG && scale >= MIN_ZOOM) || mode == Mode.ZOOM) {
                    getParent().requestDisallowInterceptTouchEvent(true);
                }

                view.invalidate();

                return true;
            }
        });


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
        //Log.e("GLXrender", "onDraw");

        int xMult =  (int) (width / galaxy.getSize() * scale);
        int yMult =  (int) (height / galaxy.getSize() * scale);
        int xCorr = (int) dx;
        int yCorr = (int) dy;


        //canvas.drawPaint(backgroundPaint);

        for (SpaceObject ob:
             galaxy.getSpaceObjects()) {
            canvas.drawCircle(xCorr + ob.getX()*xMult, yCorr + ob.getY()*yMult, 5*scale, dotPaint);
            canvas.drawText(ob.toString(),xCorr + ob.getX()*xMult + 5, yCorr +  ob.getY()*yMult + 5, dotPaint);
        }

    }

    @Override
    public boolean onScale(ScaleGestureDetector scaleGestureDetector) {
        float scaleFactor = scaleGestureDetector.getScaleFactor();
        //Math.signum returns +- to indicate zoom direction
        if (lastScaleFactor == 0 || (Math.signum(scaleFactor) == Math.signum(lastScaleFactor))) {
            scale *= scaleFactor;
            scale = Math.max(MIN_ZOOM, Math.min(scale, MAX_ZOOM));
            lastScaleFactor = scaleFactor;
        } else {
            lastScaleFactor = 0;
        }
        Log.e("GLXrender", "scale:"+scale);
        return true;
    }

    @Override
    public boolean onScaleBegin(ScaleGestureDetector scaleGestureDetector) {  //NOT USED
        return true;
    } //NOT USED

    @Override
    public void onScaleEnd(ScaleGestureDetector scaleGestureDetector) {}//NOT USED

    @Override
    public boolean onDown(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent motionEvent) {

    }

    @Override
    public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent1, float v, float v1) {
        return false;
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
        return false;
    }

    @Override
    public boolean onDoubleTap(MotionEvent motionEvent) {
        mode = Mode.NONE;
        scale = 1.0f;
        lastScaleFactor = 0f;

        startX = 0f;
        startY = 0f;

        dx = 0f;
        dy = 0f;
        prevDx = 0f;
        prevDy = 0f;
        
        return true;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent motionEvent) {
        return false;
    }
}
