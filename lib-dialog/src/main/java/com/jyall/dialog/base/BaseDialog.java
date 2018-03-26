package com.jyall.dialog.base;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.jyall.dialog.R;

import java.util.HashMap;
import java.util.Map;

public class BaseDialog extends DialogFragment {
    /**
     * 调试标志
     */
    private static final String TAG = BaseDialog.class.getSimpleName();
    /**
     * Dialog的参数
     */
    protected DialogParams mParams;

    /**
     * onSaveInstanceState保存的key
     */
    private static final String KEY = "dialog";
    /**
     * Text的key
     */
    private static final String TEXT = "text";
    /**
     * Drawable的key
     */
    private static final String DRAWABLE = "drawable";
    /**
     * Listener的Key
     */
    private static final String LISTENER = "listener";
    /**
     * 是否是恢复的数据
     */
    private boolean isSavedInstanceState = false;

    /**
     * 构造方法
     */
    public BaseDialog(){
        super();
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, R.style.StyleDialog);
        //保存数据，防止重建Dialog时出现数据丢失的情况
        if (savedInstanceState != null){
            mParams = (DialogParams) savedInstanceState.getSerializable(KEY);
            Log.i(TAG,"savedInstanceState mParams:" + mParams.toString());
            isSavedInstanceState = true;
        }
        setCancelable(mParams.isCancelable);
        Log.i(TAG,"onCreate()");
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable(KEY,mParams);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.i(TAG,"onCreateView()");
        //去除标题
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        //必须返回的View是Builder中已经设置的那个对象，使用LayoutInflater加载的是另外一个对象
        View contentView = mParams.mContentView;

        if (savedInstanceState != null){
            contentView = LayoutInflater.from(getContext()).inflate(mParams.mLayoutId,null);
            mParams.mContentView = contentView;
        }
        Log.i(TAG,"contentView.getParent():" + contentView.getParent());
        return contentView;
    }

    @Override
    public void onStart() {
        super.onStart();
        //设置布局属性
        Window window = getDialog().getWindow();
        window.setLayout(mParams.mWidth,mParams.mHeight);
        window.setGravity(mParams.mGravity);

        Log.i(TAG,"onStart()");
        if (!isSavedInstanceState){
            return;
        }
        HashMap<Integer,Object> hashMap = mParams.mEventMap.get(TEXT);
        for (Map.Entry<Integer,Object> entry : hashMap.entrySet()){
            TextView view = (TextView) mParams.mContentView.findViewById(entry.getKey());
            view.setText((CharSequence) entry.getValue());
        }

        hashMap = mParams.mEventMap.get(DRAWABLE);
        for (Map.Entry<Integer,Object> entry : hashMap.entrySet()){
            ImageView view = (ImageView) mParams.mContentView.findViewById(entry.getKey());
            view.setVisibility(View.VISIBLE);
            if (entry.getValue() instanceof Integer){
                view.setImageResource((Integer) entry.getValue());
            }else if (entry.getValue() instanceof Bitmap){
                view.setImageBitmap((Bitmap) entry.getValue());
            }
        }

        hashMap = mParams.mEventMap.get(LISTENER);
        for (final Map.Entry<Integer,Object> entry : hashMap.entrySet()){
            View view = mParams.mContentView.findViewById(entry.getKey());
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ((DialogListener) entry.getValue()).onClick(v);
                    dismiss();
                }
            });
        }
    }

    /**
     * 使用builder模式
     */
    public static class Builder{

        private Context mBuilderContext;
        /**
         * 参数
         */
        private DialogParams mBuilderParams ;
        /**
         * helper
         */
        private DialogViewHolder mHolder;
        /**
         * 所引用的dialog
         */
        BaseDialog dialog;

        /**
         * 构造方法
         * @param context
         */
        public Builder(Context context){
            mBuilderContext = context;
            dialog = new BaseDialog();

            mBuilderParams = new DialogParams();
            mHolder = new DialogViewHolder(dialog);
            mBuilderParams.mHolder = mHolder;

            mBuilderParams.mEventMap.put(TEXT,new HashMap<Integer, Object>());
            mBuilderParams.mEventMap.put(DRAWABLE,new HashMap<Integer, Object>());
            mBuilderParams.mEventMap.put(LISTENER,new HashMap<Integer, Object>());
        }

        /**
         * 设置setContentView
         * @param layoutId
         * @return
         */
        public Builder setContentView(int layoutId){
            mBuilderParams.mLayoutId = layoutId;
            mBuilderParams.mContentView = LayoutInflater.from(mBuilderContext).inflate(mBuilderParams.mLayoutId,null);
            Log.i(TAG,"mBuilderParams.mContentView.getParent():" + mBuilderParams.mContentView.getParent());
            mBuilderParams.mHolder.setContentView(mBuilderParams.mContentView);
            return this;
        }

        /**
         * 设置ContentView
         * @param view
         * @return
         */
        public Builder setContentView(View view){
            mBuilderParams.mContentView = view;
            mBuilderParams.mLayoutId = 0;
            mBuilderParams.mHolder.setContentView(mBuilderParams.mContentView);
            Log.i(TAG,"mBuilderParams.mContentView.getParent():" + mBuilderParams.mContentView.getParent());
            return this;
        }

        /**
         * 设置点击对话框外是否可以取消
         * @param cancelable
         * @return
         */
        public Builder setCancelable(boolean cancelable){
            mBuilderParams.isCancelable = cancelable;
            return this;
        }

        /**
         * 设置要显示需要的fragment
         * @param manager
         * @return
         */
        public Builder setFragmentManager(FragmentManager manager){
            mBuilderParams.mFragmentManager = manager;
            return this;
        }

        /**
         * 全部宽度显示
         * @return
         */
        public Builder fullWidth(){
            mBuilderParams.mWidth = ViewGroup.LayoutParams.MATCH_PARENT;
            return this;
        }

        /**
         * 设置位置 居中 底部 顶部
         * @param gravity
         * @return
         */
        public Builder setGravity(int gravity){
            mBuilderParams.mGravity = gravity;
            return this;
        }

        /**
         * 设置内容
         * @param viewId
         * @param text
         */
        public Builder setText(int viewId, CharSequence text) {
            Log.i(TAG,"setText viewId : " + viewId + ",text :" + text);
            mBuilderParams.mEventMap.get(TEXT).put(viewId,text);
            mBuilderParams.mHolder.setText(viewId,text);

            return this;
        }

        /**
         * 设置ImageView
         * @param viewId
         * @param bitmap
         */
        public Builder setImage(int viewId, Bitmap bitmap) {
            Log.i(TAG,"setImage viewId : " + viewId + ",bitmap :" + bitmap);
            mBuilderParams.mEventMap.get(DRAWABLE).put(viewId,bitmap);
            mBuilderParams.mHolder.setImage(viewId,bitmap);
            return this;
        }

        /**
         * 设置ImageView的Drawable
         * @param viewId
         * @param resId
         */
        public Builder setDrawable(int viewId, int resId) {
            Log.i(TAG,"setDrawable viewId : " + viewId + ",resId :" + resId);
            mBuilderParams.mEventMap.get(DRAWABLE).put(viewId,resId);
            Drawable drawable ;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
                drawable = mBuilderContext.getDrawable(resId);
            }else {
                drawable = mBuilderContext.getResources().getDrawable(resId);
            }
            mBuilderParams.mHolder.setDrawable(viewId,drawable);
            return this;
        }

        /**
         * 设置ImageView的Drawable
         * @param viewId
         * @param drawable
         */
        @Deprecated
        public Builder setDrawable(int viewId, Drawable drawable) {
            Log.i(TAG,"setDrawable viewId : " + viewId + ",drawable :" + drawable);
            mBuilderParams.mHolder.setDrawable(viewId,drawable);
            return this;
        }

        /**
         * 设置点击事件
         *
         * @param viewId
         * @param listener
         */
        public Builder setDialogListener(int viewId, DialogListener listener) {
            Log.i(TAG,"setOnItemClickListener viewId : " + viewId + ",listener :" + listener);
            mBuilderParams.mEventMap.get(LISTENER).put(viewId,listener);
            mBuilderParams.mHolder.setOnClickListener(viewId,listener);
            return this;
        }

        /**
         * 创建对话框
         * @return
         */
        public BaseDialog build(){
            dialog.mParams = mBuilderParams;
            return dialog;
        }
    }

    /**
     * 根据id获取对应的view
     * @param viewId
     * @param <T>
     * @return
     */
    public <T extends View> T getView(int viewId){
        return mParams.mHolder.getView(viewId);
    }

    /**
     * 显示对话框
     */
    public void show(){
        //如果没有被添加
        if (!isAdded()){
            FragmentTransaction transaction = mParams.mFragmentManager.beginTransaction();
            transaction.add(this, TAG);
            transaction.commitAllowingStateLoss();
        }
        Log.i(TAG,"show()");
    }

    /**
     * 取消显示对话框
     */
    @Override
    public void dismiss(){
        super.dismiss();
    }


}
