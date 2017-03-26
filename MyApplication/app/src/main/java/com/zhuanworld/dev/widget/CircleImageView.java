package com.zhuanworld.dev.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.Xfermode;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.bumptech.glide.load.resource.bitmap.BitmapDrawableResource;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.zhuanworld.dev.R;

import java.util.logging.XMLFormatter;

/**
 * Created by Administrator on 2017/3/17 0017.
 */
public class CircleImageView extends ImageView {

    Paint mPaint;
    boolean is_circle;

    public CircleImageView(Context context) {
        this(context, null);
    }

    public CircleImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CircleImageViewStyle);
        is_circle = a.getBoolean(R.styleable.CircleImageViewStyle_is_circle, true);
        a.recycle();
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setAntiAlias(true);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (is_circle) {
            Bitmap bitmap1 = getBitmap();
            canvas.drawBitmap(bitmap1, 0, 0, null);
        } else {
            super.onDraw(canvas);
        }
    }

    Bitmap getBitmap() {
//        Bitmap bitmap = getBitmpByDrawable();
        Bitmap bitmap = Bitmap.createScaledBitmap(getBitmpByDrawable(), getWidth(), getHeight(), true);
//        Bitmap bitmap = mBitmap;
        if (bitmap != null) {
            Bitmap bit = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getWidth(), Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bit);
            mPaint.setXfermode(null);
            canvas.drawCircle(bit.getWidth() / 2, bit.getHeight() / 2, bit.getHeight() / 2, mPaint);
            mPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
            canvas.drawBitmap(bitmap, 0, 0, mPaint);
            return bit;
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
