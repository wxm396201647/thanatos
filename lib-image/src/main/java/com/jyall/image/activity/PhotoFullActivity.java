/*******************************************************************************
 * Copyright 2011, 2012 Chris Banes.
 * <p/>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p/>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p/>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *******************************************************************************/
package com.jyall.image.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jyall.image.ImageLoader;
import com.jyall.image.R;

import java.util.ArrayList;
import java.util.List;

import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;

/**
 * 大图展示<br><br>
 * <b>若有特殊需求，继承此类重写 convert 内部逻辑即可</b>
 */
public class PhotoFullActivity extends Activity {

    // 传递过来的图片集合与将要展示的张数
    public static final String PHOTO_FULL_INTENT_POSITION = "photo_full_intent_position";
    public static final String PHOTO_FULL_INTENT_LIST = "photo_full_intent_list";

    private List<String> mListUrls;
    private HackyViewPager mViewPager;

    public static void open(Activity activity, int position, ArrayList<String> urls){
        Intent intent = new Intent();
        intent.putExtra(PHOTO_FULL_INTENT_POSITION, position);
        intent.putStringArrayListExtra(PHOTO_FULL_INTENT_LIST, urls);
        activity.startActivity(intent);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_full);
        mViewPager = (HackyViewPager) findViewById(R.id.view_pager);

        int position = getIntent().getIntExtra(PHOTO_FULL_INTENT_POSITION, 0);
        mListUrls = getIntent().getStringArrayListExtra(PHOTO_FULL_INTENT_LIST);
        mViewPager.setAdapter(new SamplePagerAdapter());
        mViewPager.setCurrentItem(position);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus && Build.VERSION.SDK_INT >= 19) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(
                    View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                            | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                            | View.SYSTEM_UI_FLAG_FULLSCREEN
                            | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        }
    }

    /**
     * 默认适配器
     */
    private class SamplePagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return mListUrls.size();
        }

        @Override
        public View instantiateItem(ViewGroup container, int position) {
            View view = convert(container, position);

            // 以下为基本的大图展示逻辑，暂时写死
            container.addView(view);
            PhotoView photoView = (PhotoView) view.findViewById(R.id.photoview);
            photoView.setOnPhotoTapListener(new PhotoViewAttacher.OnPhotoTapListener() {
                @Override
                public void onPhotoTap(View view, float x, float y) {
                    finish();
                    overridePendingTransition(0, R.anim.zoom_in);
                }

                @Override
                public void onOutsidePhotoTap() {
                    finish();
                    overridePendingTransition(0, R.anim.zoom_in);
                }
            });
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view == object;
        }
    }

    /**
     * 若有特殊需求，直接重写此方法
     *
     * @param container
     * @param position
     * @return
     */
    protected View convert(ViewGroup container, int position) {
        View view = LayoutInflater.from(PhotoFullActivity.this).inflate(R.layout.item_photo_full, null);
        PhotoView photoView = (PhotoView) view.findViewById(R.id.photoview);
        ImageLoader.getInstance(PhotoFullActivity.this).displayByUrl(photoView, mListUrls.get(position), null);
        return view;
    }
}
