package me.anany.weikandian.db.dao;

import android.content.Context;
import android.database.Cursor;
import me.anany.weikandian.App;
import me.anany.weikandian.db.entity.HomeTitleDB;
import me.anany.weikandian.utils.LogUtil;

import java.util.List;

/**
 * Created by anany on 16/1/21.
 * <p>
 * Email:zhujun2730@gmail.com
 */
public class DaoUtil {

    public static String getHomeItemInput_time(Context mContext, int position) {

        String input_time = "";

        DaoSession daoSession = App.getDaoSession(mContext);

        String sql = "SELECT * FROM HOMEITEMDB WHERE POSITION = " + position + " ORDER BY ID DESC LIMIT 0,1";

        Cursor cursor = daoSession.getDatabase().rawQuery(sql, null);

        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToNext();

            int index = cursor.getColumnIndex("INPUT_TIME");
            input_time = cursor.getString(index);
            LogUtil.e("数据库input_time：" + input_time);
            cursor.close();
        }

        return input_time;
    }

    public static List<HomeTitleDB> getHomeTitleList(Context context) {
        DaoSession daoSession = App.getDaoSession(context);
        HomeTitleDBDao titleDao = daoSession.getHomeTitleDBDao();
        return titleDao.loadAll();
    }

    public static void saveHomeTitleList(List<HomeTitleDB> channelList,
                                         Context context) {

        DaoSession daoSession = App.getDaoSession(context);
        HomeTitleDBDao homeTitleDBDao = daoSession.getHomeTitleDBDao();

        for (HomeTitleDB titleDB : channelList) {
            homeTitleDBDao.insert(titleDB);
        }
    }
}
