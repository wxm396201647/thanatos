package com.jyall.image;

import android.widget.ImageView;

/**
 * Created by wang.wenlong5 on 2017/11/8.
 */

public interface ImageParent {
    /**
     * 加载本项目图片
     * @param imageView
     * @param resId
     * @param imageListener
     */
    void displayByRes(ImageView imageView, int resId, ImageListener imageListener);

    /**
     * 加载网络或本地图片，无默认
     *
     * @param imageView
     * @param url
     * @param imageListener
     */
    void displayByUrl(ImageView imageView, String url, ImageListener imageListener);
    /**
     * 加载网络或本地图片，有默认
     *
     * @param imageView
     * @param url
     * @param defaultResId
     * @param imageListener
     */

    void displayByUrl(ImageView imageView, String url, int defaultResId, ImageListener imageListener);
    /**
     * 加载圆形图片，无默认
     *
     * @param imageView
     * @param url
     * @param imageListener
     */
    void displayCircle(ImageView imageView, String url, ImageListener imageListener);

    /**
     * 加载圆形图片，有默认
     *
     * @param imageView
     * @param url
     * @param defaultResId
     * @param imageListener
     */
    void displayCircle(ImageView imageView, String url, int defaultResId, ImageListener imageListener);
    /**
     * 加载圆角图片，无默认
     *
     * @param imageView
     * @param url
     * @param round
     * @param imageListener
     */
    void displayRound(ImageView imageView, String url, int round, ImageListener imageListener);

    /**
     * 加载圆角图片，有默认
     *
     * @param imageView
     * @param url
     * @param round
     * @param defaultResId
     * @param imageListener
     */
    void displayCircle(ImageView imageView, String url, int round, int defaultResId, ImageListener imageListener);
}
