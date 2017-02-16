package me.anany.weikandian;

import android.app.Application;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.widget.Toast;

import org.greenrobot.greendao.database.Database;

import me.anany.weikandian.db.DaoMaster;
import me.anany.weikandian.db.DaoSession;
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
    public boolean ENCRYPTED = false;
    private static DaoSession daoSession;

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

        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, ENCRYPTED ?
                Constants.DB_NAME_ENCRYPTED : Constants.DB_NAME);
        Database db = ENCRYPTED ? helper.getEncryptedWritableDb("super-secret") : helper.getWritableDb();
        daoSession = new DaoMaster(db).newSession();
    }

    public static ApiService getApi() {
        return mApiService;
    }

    public static void toast(@NonNull CharSequence text) {
        Toast.makeText(mApp, text, Toast.LENGTH_SHORT).show();
    }

    public static void toast(@StringRes int stringRes) {
        Toast.makeText(mApp, stringRes, Toast.LENGTH_SHORT).show();
    }

    public static DaoSession getDaoSession() {
        return daoSession;
    }
}
