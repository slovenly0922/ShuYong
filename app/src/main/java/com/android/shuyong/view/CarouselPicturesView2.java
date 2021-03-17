package com.android.shuyong.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Shader;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.android.shuyong.R;

public class CarouselPicturesView2 extends View {
    private int image_src_int;
    private static String TAG = "CarouselPicturesView2";
    private ShapeDrawable shapeDrawable = null;
    private Bitmap srcBitmap = null;
    private int srcBitmapWidth;
    private int srcBitmapHeight;
    private BitmapShader bitmapShader = null;

    public CarouselPicturesView2(Context context) {
        super(context);
    }

    public CarouselPicturesView2(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        TypedArray typedArray = context.obtainStyledAttributes(attributeSet, R.styleable.CarouselPicturesView2);
        image_src_int = typedArray.getResourceId(R.styleable.CarouselPicturesView2_image_src2, 0);

        srcBitmap = BitmapFactory.decodeResource(context.getResources(), image_src_int);
        srcBitmapWidth = srcBitmap.getWidth() / 2;
        srcBitmapHeight = srcBitmap.getHeight() / 2;
        bitmapShader = new BitmapShader(srcBitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);

        typedArray.recycle();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //构建ShapeDrawable对象并定义形状为椭圆
        shapeDrawable = new ShapeDrawable(new OvalShape());
        //得到画笔并设置渲染器
        Paint paint = shapeDrawable.getPaint();
        paint.setShader(bitmapShader);

        //设置显示区域
        shapeDrawable.setBounds(-getWidth()/4, -getHeight()/4, getWidth() / 2, getHeight() / 2);
        //绘制shapeDrawable
        shapeDrawable.draw(canvas);

    }


}
