package me.anany.weikandian.utils;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by anany on 16/1/19.
 * <p>
 * Email:zhujun2730@gmail.com
 */
public class ToastUtil {

    /**
     * 显示Toast
     * @param context
     * @param text
     */
    public static void showToast(Context context,String text){
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
    }

    /**
     * 显示Toast
     * @param context
     * @param text
     */
    public static void showToast(Context context,int text){
        Toast.makeText(context, text, Toast.LENGTH_SHORT).show();
    }
}
