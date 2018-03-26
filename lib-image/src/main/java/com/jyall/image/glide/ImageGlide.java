package com.jyall.image.glide;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;
import com.jyall.image.ImageListener;
import com.jyall.image.ImageParent;

/**
 * Created by wang.wenlong5 on 2017/11/8.
 */

public class ImageGlide implements ImageParent {

    private Context mContext;
    private RequestManager mGlideRequestManager;

    public ImageGlide(Context context) {
        this.mContext = context;
        mGlideRequestManager = Glide.with(context.getApplicationContext());
    }

    @Override
    public void displayByRes(ImageView imageView, int resId, final ImageListener imageListener) {
        mGlideRequestManager.load(resId)
                .listener(new ImageListenerGlide(imageListener))
                .crossFade()
                .centerCrop()
                .into(imageView);
    }

    @Override
    public void displayByUrl(ImageView imageView, String url, final ImageListener imageListener) {
        mGlideRequestManager.load(url)
                .listener(new ImageListenerGlide(imageListener))
                .crossFade()
                .centerCrop()
                .into(imageView);
    }

    @Override
    public void displayByUrl(ImageView imageView, String url, int defaultResId, final ImageListener imageListener) {
        mGlideRequestManager.load(url)
                .listener(new ImageListenerGlide(imageListener))
                .placeholder(defaultResId)
                .error(defaultResId)
                .crossFade()
                .centerCrop()
                .into(imageView);
    }

    @Override
    public void displayCircle(ImageView imageView, String url, final ImageListener imageListener) {
        mGlideRequestManager.load(url)
                .listener(new ImageListenerGlide(imageListener))
                .transform(new GlideCircleTransform(mContext))
                .crossFade()
                .centerCrop()
                .into(imageView);
    }

    @Override
    public void displayCircle(ImageView imageView, String url, int defaultResId, final ImageListener imageListener) {
        mGlideRequestManager.load(url)
                .listener(new ImageListenerGlide(imageListener))
                .placeholder(defaultResId)
                .error(defaultResId)
                .transform(new GlideCircleTransform(mContext))
                .crossFade()
                .centerCrop()
                .into(imageView);
    }

    @Override
    public void displayRound(ImageView imageView, String url, int round, final ImageListener imageListener) {
        mGlideRequestManager.load(url)
                .listener(new ImageListenerGlide(imageListener))
                .transform(new GlideRoundTransform(mContext))
                .crossFade()
                .centerCrop()
                .into(imageView);
    }

    @Override
    public void displayCircle(ImageView imageView, String url, int round, int defaultResId, final ImageListener imageListener) {
        mGlideRequestManager.load(url)
                .listener(new ImageListenerGlide(imageListener))
                .placeholder(defaultResId)
                .error(defaultResId)
                .transform(new GlideRoundTransform(mContext))
                .crossFade()
                .centerCrop()
                .into(imageView);
    }

    /**
     * Glide-显示圆角图片的转换类
     */
    private static class GlideRoundTransform extends BitmapTransformation {
        private float radius = 0f;

        public GlideRoundTransform(Context context) {
            this(context, 4);
        }

        public GlideRoundTransform(Context context, int radiusDp) {
            super(context);
            this.radius = Resources.getSystem().getDisplayMetrics().density * radiusDp;
        }

        @Override
        protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
            return roundCrop(pool, toTransform);
        }

        private Bitmap roundCrop(BitmapPool pool, Bitmap source) {
            if (source == null) return null;
            Bitmap result = pool.get(source.getWidth(), source.getHeight(), Bitmap.Config.ARGB_8888);
            if (result == null) {
                result = Bitmap.createBitmap(source.getWidth(), source.getHeight(), Bitmap.Config.ARGB_8888);
            }
            Canvas canvas = new Canvas(result);
            Paint paint = new Paint();
            paint.setShader(new BitmapShader(source, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP));
            paint.setAntiAlias(true);
            RectF rectF = new RectF(0f, 0f, source.getWidth(), source.getHeight());
            canvas.drawRoundRect(rectF, radius, radius, paint);
            return result;
        }

        @Override
        public String getId() {
            return getClass().getName() + Math.round(radius);
        }
    }

    /**
     * Glide-所使用显示圆形图片的转换类
     */
    private static class GlideCircleTransform extends BitmapTransformation {
        GlideCircleTransform(Context context) {
            super(context);
        }

        @Override
        protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
            return circleCrop(pool, toTransform);
        }

        private Bitmap circleCrop(BitmapPool pool, Bitmap source) {
            if (source == null) return null;
            int size = Math.min(source.getWidth(), source.getHeight());
            int x = (source.getWidth() - size) / 2;
            int y = (source.getHeight() - size) / 2;
            Bitmap squared = Bitmap.createBitmap(source, x, y, size, size);
            Bitmap result = pool.get(size, size, Bitmap.Config.ARGB_8888);
            if (result == null) {
                result = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888);
            }
            Canvas canvas = new Canvas(result);
            Paint paint = new Paint();
            paint.setShader(new BitmapShader(squared, BitmapShader.TileMode.CLAMP, BitmapShader.TileMode.CLAMP));
            paint.setAntiAlias(true);
            float r = size / 2f;
            canvas.drawCircle(r, r, r, paint);
            return result;
        }

        @Override
        public String getId() {
            return getClass().getName();
        }
    }
}
