package me.anany.weikandian.utils;

import android.content.Context;

/**
 * Created by anany on 16/1/22.
 * <p>
 * Email:zhujun2730@gmail.com
 */
public class UiUtil {

    /**
     * dip转为 px
     */
    public static int dip2px(Context context, float dipValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5f);
    }

    /**
     *  px 转为 dip
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }
}
