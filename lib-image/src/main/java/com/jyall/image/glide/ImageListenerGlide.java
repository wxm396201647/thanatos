package com.jyall.image.glide;

import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.jyall.image.ImageListener;

/**
 * Created by wang.wenlong5 on 2017/11/8.
 */

public class ImageListenerGlide implements RequestListener {

    private ImageListener mImageListener;

    ImageListenerGlide(ImageListener imageListener) {
        mImageListener = imageListener;
    }

    @Override
    public boolean onException(Exception e, Object model, Target target, boolean isFirstResource) {
        if (null != mImageListener) {
            mImageListener.onLoadFail();
        }
        return false;
    }

    @Override
    public boolean onResourceReady(Object resource, Object model, Target target, boolean isFromMemoryCache, boolean isFirstResource) {
        if (null != mImageListener) {
            mImageListener.onLoadSuc();
        }
        return false;
    }
}
