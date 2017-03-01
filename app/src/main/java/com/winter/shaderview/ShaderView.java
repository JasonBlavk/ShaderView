package com.winter.shaderview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.Xfermode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;


public class ShaderView extends View {

    private Paint mBitmapPaint;
    /**
     * 着色图像
     */
    private RectF mRectF;
    /**
     * 模板图形
     */
    private Bitmap mRectBitmap;
    private Xfermode mXfermode;

    public ShaderView(Context context) {
        this(context, null);
    }

    public ShaderView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mRectF = new RectF();
        mBitmapPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mXfermode = new PorterDuffXfermode(PorterDuff.Mode.SRC_IN);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        mRectF.set(getPaddingLeft(), getPaddingTop(), getWidth(), getHeight() - getPaddingBottom());
        if (getMeasuredWidth() > 0 && getMeasuredHeight() > 0) {

            Drawable drawable = getResources().getDrawable(R.drawable.corner_blue);
            //资源drawable必须转化成bitmap，mXfermode才会生效!
            mRectBitmap = drawableToBitamp(drawable, getMeasuredWidth(), getMeasuredHeight());
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (null != mRectBitmap) {
            int saveLayer = canvas.saveLayer(getPaddingLeft(),
                    getPaddingTop(),
                    getWidth(),
                    getHeight(),
                    null,
                    Canvas.ALL_SAVE_FLAG);
            canvas.drawBitmap(mRectBitmap, 0, 0, mBitmapPaint);
            mBitmapPaint.setXfermode(mXfermode);
            setShaderIfNeeded();
            canvas.drawRect(mRectF, mBitmapPaint);

            mBitmapPaint.setXfermode(null);
            canvas.restoreToCount(saveLayer);
        }
    }

    /**
     * 设置Shader
     */
    private void setShaderIfNeeded() {
        if (null == mBitmapPaint.getShader()) {
            // 装载资源
            Bitmap shaderBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.template);
            // 创建BitmapShader对象
            BitmapShader mBitmapShader = new BitmapShader(shaderBitmap, Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);
            mBitmapPaint.setShader(mBitmapShader);
        }
    }

    /**
     * drawable转bitmap
     *
     * @param drawable
     * @return
     */
    private Bitmap drawableToBitamp(Drawable drawable, int targetWidth, int targetHeight) {
        if (drawable instanceof BitmapDrawable) {
            BitmapDrawable bd = (BitmapDrawable) drawable;
            return bd.getBitmap();
        }
        Bitmap bitmap = Bitmap.createBitmap(targetWidth, targetHeight, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, targetWidth, targetHeight);
        drawable.draw(canvas);
        return bitmap;
    }
}
