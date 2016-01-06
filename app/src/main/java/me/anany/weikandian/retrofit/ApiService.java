package me.anany.weikandian.retrofit;

import me.anany.weikandian.model.HomeNewsData;
import retrofit.http.GET;
import retrofit.http.Query;
import rx.Observable;

/**
 * 数据接口
 * Created by Berial on 16/1/6.
 */
public interface ApiService {

    String BASE_URL = "http://www.weixinkd.com/";

    /**
     * 首页 【新闻列表数据】
     */
    @GET("v3/article/lists2.json")
    Observable<HomeNewsData> getHomeNewsData(
            @Query("catid") int catId,
            @Query("iid") int iid,
            @Query("uid") int uid
    );
}
