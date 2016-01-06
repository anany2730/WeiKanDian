package me.anany.weikandian.retrofit;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 从工作线程返回主线程
 * Created by Berial on 15/12/10.
 */
public final class RxApiThread {

    static Observable.Transformer schedulersTransformer = new Observable.Transformer() {

        @Override
        public Object call(Object o) {
            return ((Observable) o).subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread());
        }
    };

    static Observable.Transformer main = new Observable.Transformer() {

        @Override
        public Object call(Object o) {
            return ((Observable) o).subscribeOn(AndroidSchedulers.mainThread())
                    .observeOn(AndroidSchedulers.mainThread());
        }
    };

    @SuppressWarnings("unchecked")
    public static <T> Observable.Transformer<T, T> convert() {
        return schedulersTransformer;
    }

    @SuppressWarnings("unchecked")
    public static <T> Observable.Transformer<T, T> main() {
        return main;
    }
}
