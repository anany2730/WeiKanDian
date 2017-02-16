package me.anany.weikandian.retrofit;

import me.anany.weikandian.NetworkConfig;
import me.anany.weikandian.entry.HomeChannel;
import me.anany.weikandian.entry.HomeNewsData;
import me.anany.weikandian.entry.HomeTitleData;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * 数据接口
 * Created by Berial on 16/1/6.
 */
public interface ApiService {

    String BASE_URL = "http://www.weixinkd.com/";

    /**
     * 首页 【新闻Title数据】
     */
    @GET(NetworkConfig.HOME_TITLE)
    Observable<HomeTitleData> getHomeNewsTitle(
            @Query("access") String access,
            @Query("channel") String channel,
            @Query("app_version") String app_version,
            @Query("version_code") String version_code,
            @Query("device_platform") String device_platform
    );

    /**
     * 首页 【新闻列表数据】
     */
    @GET("v3/article/lists.json")
    Observable<HomeNewsData> getHomeNewsData(
            @Query("catid") String catid,
            @Query("step") int step,
            @Query("op") int op,
            @Query("uid") int uid,
            @Query("request_time") long request_time
    );

    /**
     * 首页 【新闻列表数据 加载更多】
     */
    @GET("v3/article/lists2.json")
    Observable<HomeNewsData> getHomeNewsDataMore(
            @Query("catid") String catid,
            @Query("step") int step,
            @Query("op") int op,
            @Query("uid") int uid,
            @Query("request_time") long request_time
    );

    /**
     * 获取频道
     */
    @GET(NetworkConfig.HOME_CHANNLE)
    Observable<HomeChannel> getHomeChannel(
    );

}
