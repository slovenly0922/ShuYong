package com.android.shuyong.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;

import com.android.shuyong.R;

public class MaskImage extends androidx.appcompat.widget.AppCompatImageView {
    private int mImageSource = 0;
    private int mMaskSource = 0;
    int width=getWidth();
    int height=getHeight();

    public MaskImage(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        //获取资源
        TypedArray a = context.obtainStyledAttributes(attributeSet, R.styleable.MaskImage, 0, 0);
        mImageSource = a.getResourceId(R.styleable.MaskImage_image, 0);
        mMaskSource = a.getResourceId(R.styleable.MaskImage_mask, 0);

        //获取图片的bitmap
        Bitmap original = BitmapFactory.decodeResource(getResources(), mImageSource);
        //获取遮罩的bitmap
        Bitmap mask = BitmapFactory.decodeResource(getResources(), mMaskSource);
        //根据透明度创建一个新的Bitmap，Bitmap.Config.ARGB_8888表示支持32位图片，也就是支持透明通道
        Bitmap result = Bitmap.createBitmap(original.getWidth(), original.getHeight(), Bitmap.Config.ARGB_8888);

        Log.d("lqq","getWidth="+width+"getHeight="+height);
        //将遮罩层的图片放到画布中
        Canvas mCanvas = new Canvas(result);
        //消除锯齿
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        //设置图层混合模式
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
        //绘制图层
        mCanvas.drawBitmap(original, 0, 0, null);
        //计算mask的绘制比例
        Matrix mMatrix=new Matrix();
        mMatrix.setScale(1f,1.2f);
        mCanvas.drawBitmap(mask,mMatrix,paint);
        paint.setXfermode(null);
        setImageBitmap(result);
        setScaleType(ScaleType.CENTER_CROP);
        //释放掉图片资源
        a.recycle();
    }
}
