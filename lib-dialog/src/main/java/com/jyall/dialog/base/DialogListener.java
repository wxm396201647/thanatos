package com.jyall.dialog.base;

import android.view.View;

import java.io.Serializable;

public interface DialogListener extends Serializable {
    void onClick(View v);
}
