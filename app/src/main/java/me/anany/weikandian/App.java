package me.anany.weikandian;

import android.app.Application;
import android.support.annotation.NonNull;
import android.support.annotation.StringRes;
import android.widget.Toast;

import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

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

    @Override
    public void onCreate() {
        super.onCreate();

        _instance = this;

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

    public RefWatcher getWatcher(){
        return _watcher;
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
