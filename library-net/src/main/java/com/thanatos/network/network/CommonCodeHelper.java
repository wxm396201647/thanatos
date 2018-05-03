package com.thanatos.network.network;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * 处理通用code
 */

class CommonCodeHelper {
    static <T> boolean processCode(@Nullable Context context, @NonNull ErrorResponseBean<T> errorResponseBean) {
        switch (errorResponseBean.code) {
            case ResponseCode.ACCESS_TOKEN_INVALID:

                return true;
            case ResponseCode.UPDATE_FORCE:

                return true;
            case  ResponseCode.TICKET_UNAVALIBLE:

                break;
            case  ResponseCode.TICKET_UNAVALIBLE2:

                break;
            case  ResponseCode.TICKET_UNAVALIBLE3:

                break;
            case ResponseCode.NO_LOGIN:

                break;
        }
        return false;
    }
}
