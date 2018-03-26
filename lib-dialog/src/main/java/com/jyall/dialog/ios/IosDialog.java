package com.jyall.dialog.ios;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jyall.dialog.R;

/**
 * 自定义的仿 iOS Dialog
 * Created by wang.wenlong5 on 2017/11/2
 */
public class IosDialog extends Dialog implements View.OnClickListener {
    private Activity mContext;
    private TextView mTvTitle;
    private TextView mTvText;
    private TextView mTvOk;
    private TextView mTvCancel;
    private TextView mTvConfirm;
    private LinearLayout mContainerOne;
    private LinearLayout mContainerTwo;
    private LinearLayout mContainerContent;
    private RelativeLayout mContainerClickOk;
    private RelativeLayout mContainerClickCancel;
    private RelativeLayout mContainerClickConfirm;
    private OnIosDialogClickListener mOnIosDialogClickListener;
    private OnIosDialogConfirmClickListener mOnIosDialogConfirmClickListener;

    private String mStringTitle;
    private CharSequence mStringText;
    private String mStringClickOk;
    private String mStringClickCancel;
    private String mStringClickConfirm;
    private boolean mClickOkDismiss = true;

    public IosDialog(@NonNull Context context) {
        super(context, R.style.StyleIosDialog);
        this.mContext = (Activity) context;
        init();
    }

    public IosDialog(@NonNull Context context, String title, CharSequence text, String clickConfirm, OnIosDialogConfirmClickListener onIosDialogConfirmClickListener) {
        super(context, R.style.StyleIosDialog);
        this.mContext = (Activity) context;
        this.mStringTitle = title;
        this.mStringText = text;
        this.mStringClickConfirm = clickConfirm;
        this.mOnIosDialogConfirmClickListener = onIosDialogConfirmClickListener;
        init();
    }

    public IosDialog(@NonNull Context context, String title, CharSequence text, String clickOk, String clickCancel, @NonNull OnIosDialogClickListener onIosDialogClickListener) {
        super(context, R.style.StyleIosDialog);
        this.mContext = (Activity) context;
        this.mStringTitle = title;
        this.mStringText = text;
        this.mStringClickOk = clickOk;
        this.mStringClickCancel = clickCancel;
        this.mOnIosDialogClickListener = onIosDialogClickListener;
        init();
    }

    private void init() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.dialog_ios, null);
        mTvTitle = view.findViewById(R.id.tv_ios_dialog_title);
        mTvText = view.findViewById(R.id.tv_ios_dialog_text);
        mTvOk = view.findViewById(R.id.tv_ios_dialog_ok);
        mTvCancel = view.findViewById(R.id.tv_ios_dialog_cancel);
        mTvConfirm = view.findViewById(R.id.tv_ios_dialog_confirm);
        mContainerOne = view.findViewById(R.id.container_one);
        mContainerTwo = view.findViewById(R.id.container_two);
        mContainerContent = view.findViewById(R.id.container_content);
        mContainerClickOk = view.findViewById(R.id.container_ios_dialog_ok);
        mContainerClickCancel = view.findViewById(R.id.container_ios_dialog_cancel);
        mContainerClickConfirm = view.findViewById(R.id.container_ios_dialog_confirm);

        mContainerClickOk.setOnClickListener(this);
        mContainerClickCancel.setOnClickListener(this);
        mContainerClickConfirm.setOnClickListener(this);

        if (null == mOnIosDialogClickListener) {
            mContainerOne.setVisibility(View.VISIBLE);
            mContainerTwo.setVisibility(View.GONE);
        } else {
            mContainerOne.setVisibility(View.GONE);
            mContainerTwo.setVisibility(View.VISIBLE);
        }

        if (TextUtils.isEmpty(mStringTitle)) {
            mTvTitle.setVisibility(View.GONE);
        } else {
            mTvTitle.setText(mStringTitle);
        }
        if (!TextUtils.isEmpty(mStringText)) {
            mTvText.setText(mStringText);
        }
        if (!TextUtils.isEmpty(mStringClickOk)) {
            mTvOk.setText(mStringClickOk);
        }
        if (!TextUtils.isEmpty(mStringClickCancel)) {
            mTvCancel.setText(mStringClickCancel);
        }
        if (!TextUtils.isEmpty(mStringClickConfirm)) {
            mTvConfirm.setText(mStringClickConfirm);
        }

        setContentView(view);
        setCanceledOnTouchOutside(false);
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.container_ios_dialog_ok) {
            if (mClickOkDismiss) {
                this.dismiss();
            }
            if (null != mOnIosDialogClickListener) {
                mOnIosDialogClickListener.onOk();
            }
        } else if (i == R.id.container_ios_dialog_cancel) {
            if (null != mOnIosDialogClickListener) {
                mOnIosDialogClickListener.onCancel();
            }
            this.dismiss();
        } else if (i == R.id.container_ios_dialog_confirm) {
            this.dismiss();
            if (null != mOnIosDialogConfirmClickListener) {
                mOnIosDialogConfirmClickListener.onConfirm();
            }
        }
    }

    public interface OnIosDialogConfirmClickListener {
        void onConfirm();
    }

    public interface OnIosDialogClickListener {
        void onOk();

        void onCancel();
    }

    public void setOnIosDialogConfirmClickListener(OnIosDialogConfirmClickListener onIosDialogConfirmClickListener) {
        this.mOnIosDialogConfirmClickListener = onIosDialogConfirmClickListener;
    }

    public void setOnIosDialogClickListener(OnIosDialogClickListener onIosDialogClickListener) {
        this.mOnIosDialogClickListener = onIosDialogClickListener;
        if (null == mOnIosDialogClickListener) {
            mContainerOne.setVisibility(View.VISIBLE);
            mContainerTwo.setVisibility(View.GONE);
        } else {
            mContainerOne.setVisibility(View.GONE);
            mContainerTwo.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 设置标题
     *
     * @param title
     */
    public void setTitle(String title) {
        mTvTitle.setText(title);
        mTvTitle.invalidate();
    }

    /**
     * 设置内容
     *
     * @param text
     */
    public void setText(CharSequence text) {
        mTvText.setText(text);
        mTvText.invalidate();
    }

    /**
     * 设置点击区域上方的 ContentView
     *
     * @param view
     */
    public void setBodyView(View view) {
        mContainerContent.removeAllViews();
        mContainerContent.addView(view);
        mContainerContent.invalidate();
    }

    /**
     * 禁止点击确定 dialog　消失
     */
    public void disableClickOkDismiss() {
        this.mClickOkDismiss = false;
    }
}
