package me.anany.weikandian.retrofit;

import me.anany.weikandian.NetworkConfig;
import me.anany.weikandian.model.HomeChannel;
import me.anany.weikandian.model.HomeNewsData;
import me.anany.weikandian.model.HomeTitleData;
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
     * 首页 【新闻Title数据】
     */
    @GET(NetworkConfig.HOME_TITLE)
    Observable<HomeTitleData> getHomeNewsTitle(
            @Query("access") String access,
            @Query("app_version") String app_version,
            @Query("channel") String channel,
            @Query("device_model") String device_model,
            @Query("iid") String iid,
            @Query("openuidi") String openuidi,
            @Query("os_api") String os_api,
            @Query("os_version") String os_version,
            @Query("phone_sim") String phone_sim,
            @Query("request_time") String request_time,
            @Query("uid") String uid,
            @Query("uuid") String uuid,
            @Query("version_code") String version_code,
            @Query("sign") String sign
    );

    /**
     * 首页 【新闻列表数据】
     */
    @GET("v3/article/lists2.json")
    Observable<HomeNewsData> getHomeNewsData(
            @Query("access") String access,
            @Query("app_version") String app_version,
            @Query("catid") String catid,
            @Query("channel") String channel,
            @Query("device_model") String device_model,
            @Query("device_platform") String device_platform,
            @Query("iid") String iid,
            @Query("min_time") String min_time,
            @Query("openudid") String openuidi,
            @Query("os_api") String os_api,
            @Query("os_version") String os_version,
            @Query("phone_sim") String phone_sim,
            @Query("request_time") String request_time,
            @Query("step") String step,
            @Query("uid") String uid,
            @Query("uuid") String uuid,
            @Query("version_code") String version_code,
            @Query("sign") String sign
    );

    /**
     * 首页 【新闻列表数据 加载更多】
     */
    @GET("v3/article/lists2.json")
    Observable<HomeNewsData> getHomeNewsDataMore(
            @Query("access") String access,
            @Query("app_version") String app_version,
            @Query("catid") String catid,
            @Query("channel") String channel,
            @Query("device_model") String device_model,
            @Query("device_platform") String device_platform,
            @Query("iid") String iid,
            @Query("max_time") String min_time,
            @Query("openuidi") String openuidi,
            @Query("os_api") String os_api,
            @Query("os_version") String os_version,
            @Query("phone_sim") String phone_sim,
            @Query("request_time") String request_time,
            @Query("uid") String uid,
            @Query("uuid") String uuid,
            @Query("version_code") String version_code,
            @Query("sign") String sign
    );

    /**
     * 首页 【新闻列表数据 加载更多】
     */
    @GET(NetworkConfig.HOME_CHANNLE)
    Observable<HomeChannel> getHomeChannel(
    );

}
