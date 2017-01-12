package me.anany.weikandian;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.widget.Toast;
import me.anany.weikandian.db.dao.DaoMaster;
import me.anany.weikandian.db.dao.DaoSession;
import me.anany.weikandian.retrofit.ApiService;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * modify by anany on 17/1/6.
 */
public class App extends Application {

    private static App mApp;
    private static ApiService mApiService;

    private static DaoMaster mDaoMaster;
    private static DaoSession mDaoSession;

    @Override
    public void onCreate() {
        super.onCreate();

        mApp = this;

//        OkHttpClient okHttpClient = new OkHttpClient();
//        okHttpClient.interceptors().add(chain -> {
//            Request request = chain.request();
//            String url = request.urlString();
//            Request build = request.newBuilder().url(url + "&").build();
//            LogUtil.d(build.urlString());
//            return chain.proceed(build);
//        });

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ApiService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        mApiService = retrofit.create(ApiService.class);

    }

    public static ApiService getApi() {
        return mApiService;
    }

    public static DaoMaster getDaoMaster(Context context) {
        if (mDaoSession == null) {
            DaoMaster.OpenHelper helper = new DaoMaster.DevOpenHelper(context,Constants.DB_NAME, null);
            mDaoMaster = new DaoMaster(helper.getWritableDatabase());
        }
        return mDaoMaster;
    }

    public static DaoSession getDaoSession(Context context) {

        if (mDaoSession == null) {
            if (mDaoMaster == null) {
                mDaoMaster = getDaoMaster(context);
            }
            mDaoSession = mDaoMaster.newSession();
        }
        return mDaoSession;
    }

    public static void toast(@NonNull CharSequence text) {
        Toast.makeText(mApp, text, Toast.LENGTH_SHORT).show();
    }

    public static void toast(@StringRes int stringRes) {
        Toast.makeText(mApp, stringRes, Toast.LENGTH_SHORT).show();
    }
}
