package com.zhuanworld.dev.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by Administrator on 2017/3/17 0017.
 */
public class CircleRectImageView extends ImageView {

    Paint mPaint;

    RectF rectF;

    public CircleRectImageView(Context context) {
        this(context, null);
    }

    public CircleRectImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleRectImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        Drawable drawable = getDrawable();
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Bitmap bitmap1 = getBitmap();
        canvas.drawBitmap(bitmap1, 0, 0, null);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    Bitmap getBitmap() {
//        Bitmap bitmap = getBitmpByDrawable();
        Bitmap bitmap = Bitmap.createScaledBitmap(getBitmpByDrawable(), getWidth(), getHeight(), true);
//        Bitmap bitmap = mBitmap;
        if (bitmap != null) {
            Bitmap bit = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getWidth(), Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bit);
            mPaint.setXfermode(null);

            if (rectF == null)
                rectF = new RectF(0, 0, bit.getWidth(), bit.getHeight());
            canvas.drawRoundRect(rectF, 10, 10, mPaint);
            mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
            canvas.drawBitmap(bitmap, 0, 0, mPaint);
            return Bitmap.createScaledBitmap(bit, getWidth(), getHeight(), true);
        }
        return null;
    }


    Bitmap getBitmpByDrawable() {
        Drawable drawable = getDrawable();

        if (drawable != null) {
            if (drawable instanceof BitmapDrawable) {
                return ((BitmapDrawable) drawable).getBitmap();
            }
        } else if (drawable instanceof ColorDrawable) {
            Rect rect = drawable.getBounds();
            int width = rect.right - rect.left;
            int height = rect.bottom - rect.top;
            int color = ((ColorDrawable) drawable).getColor();
            Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmap);
            canvas.drawARGB(Color.alpha(color), Color.red(color), Color.green(color), Color.blue(color));
            return bitmap;
        }
        return null;

    }
}
