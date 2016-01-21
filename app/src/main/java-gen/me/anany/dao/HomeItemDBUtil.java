package me.anany.dao;

import android.content.Context;
import android.database.Cursor;

import me.anany.weikandian.App;
import me.anany.weikandian.utils.LogUtil;

/**
 * Created by anany on 16/1/21.
 * <p>
 * Email:zhujun2730@gmail.com
 */
public class HomeItemDBUtil {

    public static String getInput_time(Context mContext,int position){

        String input_time = "";

        DaoSession daoSession = App.getDaoSession(mContext);

        String sql = "select * from HomeItemDB where position = " + position + " order by id desc LIMIT 0,1";

        Cursor cursor = daoSession.getDatabase().rawQuery(sql, null);

        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToNext();

            int index = cursor.getColumnIndex("input_time");
            input_time = cursor.getString(index);
            LogUtil.e("数据库input_time：" + input_time);
            cursor.close();
        }

        return input_time;
    }
}
