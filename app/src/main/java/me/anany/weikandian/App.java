package me.anany.weikandian;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.widget.Toast;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;
import me.anany.weikandian.db.dao.DaoMaster;
import me.anany.weikandian.db.dao.DaoSession;
import me.anany.weikandian.retrofit.ApiService;
import retrofit.GsonConverterFactory;
import retrofit.Retrofit;
import retrofit.RxJavaCallAdapterFactory;

/**
 * App
 * Created by Berial on 16/1/6.
 */
public class App extends Application {

    private static ApiService _api;
    private static App _instance;

    private RefWatcher _watcher;

    private static DaoMaster daoMaster;
    private static DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();

        _instance = this;

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

        _api = retrofit.create(ApiService.class);

        _watcher = LeakCanary.install(this);
    }

    public static ApiService getApi() {
        return _api;
    }

    public RefWatcher getWatcher() {
        return _watcher;
    }

    /**
     * 取得DaoMaster
     *
     * @param context
     * @return
     */
    public static DaoMaster getDaoMaster(Context context) {
        if (daoMaster == null) {
            DaoMaster.OpenHelper helper = new DaoMaster.DevOpenHelper(context,Constants.DB_NAME, null);
            daoMaster = new DaoMaster(helper.getWritableDatabase());
        }
        return daoMaster;
    }

    /**
     * 取得DaoSession
     *
     * @param context
     * @return
     */
    public static DaoSession getDaoSession(Context context) {

        if (daoSession == null) {
            if (daoMaster == null) {
                daoMaster = getDaoMaster(context);
            }
            daoSession = daoMaster.newSession();
        }
        return daoSession;
    }

    /**
     * toast
     */
    public static void toast(@NonNull CharSequence text) {
        Toast.makeText(_instance, text, Toast.LENGTH_SHORT).show();
    }

    /**
     * toast
     */
    public static void toast(@StringRes int stringRes) {
        Toast.makeText(_instance, stringRes, Toast.LENGTH_SHORT).show();
    }
}
