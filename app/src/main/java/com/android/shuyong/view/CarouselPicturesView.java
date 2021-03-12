package com.android.shuyong.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.android.shuyong.R;

public class CarouselPicturesView extends View {
    private int defaultSize;

    public CarouselPicturesView(Context context) {
        super(context);
    }

    public CarouselPicturesView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        TypedArray a = context.obtainStyledAttributes(attributeSet, R.styleable.CarouselPicturesView);
        defaultSize = a.getDimensionPixelSize(R.styleable.CarouselPicturesView_default_size, 100);
        Log.d("lqq", "defaultSize=" + defaultSize);
        a.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int width = getMySize(defaultSize, widthMeasureSpec);
        int height = getMySize(defaultSize, heightMeasureSpec);

        if (width < height)
            height = width;
        else width = height;
        Log.d("lqq", "height=" + height);
        Log.d("lqq", "width=" + width);

        setMeasuredDimension(width, height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //得到半径
        int r = getMeasuredWidth() / 2;
        Log.d("lqq", "getMeasuredWidth=" + getMeasuredWidth());
        //圆心的横坐标
        int centerX = getLeft() + r;
        //圆心的纵坐标
        int centerY =  r;

        Paint paint = new Paint();
        paint.setColor(Color.GREEN);
        canvas.drawCircle(centerX, centerY, r, paint);
    }

    private int getMySize(int defaultSize, int measureSpec) {
        int mySize = defaultSize;
        int mode = MeasureSpec.getMode(measureSpec);
        int size = MeasureSpec.getSize(measureSpec);

        switch (mode) {
            case MeasureSpec.UNSPECIFIED:
                mySize = defaultSize;
                Log.d("lqq", "UNSPECIFIED");
                break;
            case MeasureSpec.AT_MOST:
                mySize = size;
                Log.d("lqq", "AT_MOST");
                break;
            case MeasureSpec.EXACTLY:
                mySize = size;
                Log.d("lqq", "EXACTLY");
                break;
        }
        return mySize;

    }
}
