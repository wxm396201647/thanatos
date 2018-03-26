package com.jyall.titleview;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.annotation.ColorInt;
import android.support.annotation.DrawableRes;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import modularity.shouba.jyall.com.lib_titleview.R;

/**
 * 统一标题
 * Created by wang.wenlong5 on 2017/11/23
 */
public class TitleView extends RelativeLayout {
    private TextView mTvBack;
    private TextView mTvTitle;
    private TextView mTvRight;
    private ImageView mImageRight;
    private View mViewLine;
    private RelativeLayout mContainerBack;
    private RelativeLayout mContainerClose;
    private LinearLayout mContainerRight;
    private RelativeLayout mContainerTitle;
    private OnBaseLeftClickListener mOnBaseLeftClickListener;
    private OnBaseTitleClickListener mOnBaseTitleClickListener;
    private OnBaseRightClickListener mOnBaseRightClickListener;

    private boolean hasInflate = false;
    private AttributeSet attrs = null;

    private int mRightTextColor = Color.parseColor("#333333");

    private OnClickListener mOnClickListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            int i = v.getId();
            if (i == R.id.base_container_back) {
                if (mOnBaseLeftClickListener != null) {
                    mOnBaseLeftClickListener.onClick();
                } else {
                    if (getContext() instanceof Activity) {
                        ((Activity) getContext()).onBackPressed();
                    }
                }
            } else if (i == R.id.base_container_close) {
                if (getContext() instanceof Activity) {
                    ((Activity) getContext()).finish();
                    ((Activity) getContext()).overridePendingTransition(0, R.anim.push_right_out);
                }
            } else if (i == R.id.base_container_right || i == R.id.right_image || i == R.id.base_right) {
                if (mOnBaseRightClickListener != null) {
                    mOnBaseRightClickListener.onClick();
                }
            } else if (i == R.id.base_container_title) {
                if (mOnBaseTitleClickListener != null) {
                    mOnBaseTitleClickListener.onClick();
                }
            }
        }
    };

    public TitleView(Context context) {
        super(context);
        initView(null);
    }

    public TitleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView(attrs);
    }

    public TitleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(attrs);
    }

    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        if (hasWindowFocus && !hasInflate) {
            hasInflate = true;
            initView(attrs);
        }
        super.onWindowFocusChanged(hasWindowFocus);
    }

    private void initView(AttributeSet attrs) {
        if (hasInflate || attrs == null) {
            return;
        }
        this.attrs = attrs;
        hasInflate = true;

        View titleView = LayoutInflater.from(getContext()).inflate(R.layout.title_view, null);
        mTvBack = (TextView) titleView.findViewById(R.id.base_back);
        mTvTitle = (TextView) titleView.findViewById(R.id.base_title);
        mTvRight = (TextView) titleView.findViewById(R.id.base_right);
        mImageRight = titleView.findViewById(R.id.right_image);
        mViewLine = titleView.findViewById(R.id.base_line);
        mContainerBack = (RelativeLayout) titleView.findViewById(R.id.base_container_back);
        mContainerClose = (RelativeLayout) titleView.findViewById(R.id.base_container_close);
        mContainerRight = (LinearLayout) titleView.findViewById(R.id.base_container_right);
        mContainerTitle = (RelativeLayout) titleView.findViewById(R.id.base_container_title);
        mContainerBack.setOnClickListener(mOnClickListener);
        mContainerClose.setOnClickListener(mOnClickListener);
        mContainerRight.setOnClickListener(mOnClickListener);
        mContainerTitle.setOnClickListener(mOnClickListener);
        mImageRight.setOnClickListener(mOnClickListener);
        mTvRight.setOnClickListener(mOnClickListener);

        TypedArray attribute = getContext().obtainStyledAttributes(attrs,
                R.styleable.TitleView);
        String titleText = attribute.getString(R.styleable.TitleView_titleViewTitleText);
        setTitleText(titleText);
        String backText = attribute.getString(R.styleable.TitleView_titleViewBackText);
        setBackText(backText);
        String rightText = attribute.getString(R.styleable.TitleView_titleViewRightText);
        setRightText(rightText);
        int rightImageRes = attribute.getResourceId(R.styleable.TitleView_titleViewRightImage, -1);
        if (rightImageRes != -1) {
            setRightResource(rightImageRes);
        }
        boolean backVisible = attribute.getBoolean(R.styleable.TitleView_titleViewBackShow, true);
        setBackVisible(backVisible);
        boolean rightTextVisible = attribute.getBoolean(R.styleable.TitleView_titelViewRightTextShow, false);
        setRightTextVisible(rightTextVisible);
        boolean rightImageVisible = attribute.getBoolean(R.styleable.TitleView_titleViewRightImageShow, false);
        setRightImageVisible(rightImageVisible);
        addView(titleView);
    }


    // 设置标题文字
    public void setTitleText(String title) {
        if (TextUtils.isEmpty(title)) {
            return;
        }
        mTvTitle.setText(title);
    }

    // 设置title文字颜色
    public void setTitleTextColor(@ColorInt int textColor) {
        mTvTitle.setTextColor(textColor);
    }

    // 设置标题右侧图标
    public void setTitleResource(@DrawableRes int titleResource) {
        Drawable drawable = ContextCompat.getDrawable(getContext(), titleResource);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        mTvTitle.setCompoundDrawables(null, null, drawable, null);
    }

    // 设置后退文字
    public void setBackText(String backString) {
        if (TextUtils.isEmpty(backString)) {
            return;
        }
        mTvBack.setText(backString);
    }

    // 设置后退图标
    public void setBackResource(@DrawableRes int backResource) {
        Drawable drawable = ContextCompat.getDrawable(getContext(), backResource);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        mTvBack.setCompoundDrawables(drawable, null, null, null);
    }

    // 清除后退内容
    public void clearBack() {
        mTvBack.setCompoundDrawables(null, null, null, null);
        mTvBack.setText("");
    }

    // 设置右部文字
    public void setRightText(String rightString) {
        if (TextUtils.isEmpty(rightString)) {
            return;
        }
        mTvRight.setText(rightString);
        mTvRight.setVisibility(VISIBLE);
    }

    // 设置右部文字及点击
    public void setRightText(String rightString, OnBaseRightClickListener onBaseRightClickListener) {
        if (TextUtils.isEmpty(rightString)) {
            return;
        }
        mTvRight.setVisibility(VISIBLE);
        mTvRight.setText(rightString);
        this.mOnBaseRightClickListener = onBaseRightClickListener;
    }

    // 设置右部图标
    public void setRightResource(@DrawableRes int resource) {
        mImageRight.setVisibility(VISIBLE);
        mImageRight.setImageResource(resource);
    }

    // 设置右部图标及点击
    public void setRightResource(@DrawableRes int resource, OnBaseRightClickListener onBaseRightClickListener) {
        setRightResource(resource);
        this.mOnBaseRightClickListener = onBaseRightClickListener;
    }

    // 设置右部文字及图标
    public void setRightTextResource(String text, @DrawableRes int resource, OnBaseRightClickListener onBaseRightClickListener) {
        mTvRight.setVisibility(VISIBLE);
        mImageRight.setVisibility(VISIBLE);
        mTvRight.setText(text);
        mImageRight.setImageResource(resource);
        this.mOnBaseRightClickListener = onBaseRightClickListener;
    }

    // 设置右部View
    public void setRightView(View view, OnBaseRightClickListener onBaseRightClickListener) {
        mContainerRight.addView(view);
        this.mOnBaseRightClickListener = onBaseRightClickListener;
    }

    // 设置右部文字颜色
    public void setRightTextColor(@ColorInt int textColor) {
        mTvRight.setTextColor(textColor);
        this.mRightTextColor = textColor;
    }

    // 隐藏后退
    public void hideBack() {
        setBackVisible(false);
    }

    // 设置后退显示
    public void setBackVisible(boolean visible) {
        mContainerBack.setVisibility(visible ? VISIBLE : GONE);
    }

    // 设置右部文字显示
    public void setRightTextVisible(boolean visible) {
        mTvRight.setVisibility(visible ? VISIBLE : GONE);
    }

    // 设置右部图片显示
    public void setRightImageVisible(boolean visible) {
        mImageRight.setVisibility(visible ? VISIBLE : GONE);
    }

    // 设置关闭显示
    public void setCloseVisible(boolean visible) {
        mContainerClose.setVisibility(visible ? VISIBLE : GONE);
    }

    // 设置右部显示
    public void setRightVisible(boolean visible) {
        mContainerRight.setVisibility(visible ? VISIBLE : GONE);
    }

    // 设置下标线
    public void showLine() {
        mViewLine.setVisibility(VISIBLE);
    }

    // 设置左侧点击
    public void addOnBaseLeftClickListener(OnBaseLeftClickListener onBaseLeftClickListener) {
        this.mOnBaseLeftClickListener = onBaseLeftClickListener;
    }

    // 设置右侧点击
    public void addOnBaseRightClickListener(OnBaseRightClickListener onBaseLeftClickListener) {
        this.mOnBaseRightClickListener = onBaseLeftClickListener;
    }

    // 设置标题点击
    public void addOnBaseTitleClickListener(OnBaseTitleClickListener onBaseLeftClickListener) {
        this.mOnBaseTitleClickListener = onBaseLeftClickListener;
    }

    public interface OnBaseLeftClickListener {
        void onClick();
    }

    public interface OnBaseTitleClickListener {
        void onClick();
    }

    public interface OnBaseRightClickListener {
        void onClick();
    }

    public View getRightView() {
        return mContainerRight;
    }
}
