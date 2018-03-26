package com.jyall.image;

import android.content.Context;

import com.jyall.image.glide.ImageGlide;

/**
 * Created by wang.wenlong5 on 2017/11/7.
 */

public class ImageLoader {
    private static volatile ImageParent mImageLoader;

    private ImageLoader() {
    }

    public static ImageParent getInstance(Context context) {
        if (null == mImageLoader) {
            synchronized (ImageLoader.class) {
                if (null == mImageLoader) {
                    mImageLoader = new ImageGlide(context);
                }
            }
        }
        return mImageLoader;
    }
}
