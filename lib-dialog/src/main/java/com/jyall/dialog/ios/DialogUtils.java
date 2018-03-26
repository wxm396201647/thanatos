package com.jyall.dialog.ios;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;

/**
 * 弹窗简单封装
 */

public class DialogUtils {

    public interface OnDialogOneListener {
        void onClick();
    }

    public interface OnDialogTwoListener {
        void onOk();

        void onCancel();
    }

    /**
     * 弹出只确认的提示框
     *
     * @param context
     * @param title    标题
     * @param content  内容
     * @param btnText  按钮文案
     * @param listener 点击回调
     */
    public static Dialog showOneChoiceDialog(Context context, String title, String content, String btnText, final OnDialogOneListener listener) {
        if (null == context || (context instanceof Activity && ((Activity) context).isFinishing())) {
            return null;
        }
        Dialog dialog = new IosDialog(context, title, content, btnText, new IosDialog.OnIosDialogConfirmClickListener() {
            @Override
            public void onConfirm() {
                if (null != listener) {
                    listener.onClick();
                }
            }
        });
        dialog.show();
        return dialog;
    }

    /**
     * 弹出确定/取消的弹框
     *
     * @param context
     * @param title    标题
     * @param content  内容
     * @param listener 回调
     */
    public static Dialog showTwoChoicesDialog(Context context, String title, String content, final OnDialogTwoListener listener) {
        if (null == context || (context instanceof Activity && ((Activity) context).isFinishing())) {
            return null;
        }
        Dialog dialog = new IosDialog(context, title, content, "", "", new IosDialog.OnIosDialogClickListener() {
            @Override
            public void onOk() {
                if (null != listener) {
                    listener.onOk();
                }
            }

            @Override
            public void onCancel() {
                if (null != listener) {
                    listener.onCancel();
                }
            }
        });
        dialog.show();
        return dialog;
    }

    /**
     * 弹出自定义按钮文案的弹框
     *
     * @param context
     * @param title      标题
     * @param content    内容
     * @param btnPosText 确认文案
     * @param btnNegText 取消文案
     * @param listener   回调
     */
    public static Dialog showTwoChoicesDialog(Context context, String title, String content, String btnPosText, String btnNegText, final OnDialogTwoListener listener) {
        if (null == context || (context instanceof Activity && ((Activity) context).isFinishing())) {
            return null;
        }
        Dialog dialog = new IosDialog(context, title, content, btnPosText, btnNegText, new IosDialog.OnIosDialogClickListener() {
            @Override
            public void onOk() {
                if (null != listener) {
                    listener.onOk();
                }
            }

            @Override
            public void onCancel() {
                if (null != listener) {
                    listener.onCancel();
                }
            }
        });
        dialog.show();
        return dialog;
    }
}
