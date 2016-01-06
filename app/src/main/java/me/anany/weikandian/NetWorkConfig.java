package me.anany.weikandian;

/**
 * Created by anany on 16/1/6.
 * <p/>
 * Email:zhujun2730@gmail.com
 */
public class NetWorkConfig {

    public static final String BASE_URL = "http://www.weixinkd.com/";


    /**
     * 首页 【新闻列表数据】
     */
    public static final String HOME_NEWS_DATA = "/v3/article/lists2.json";

    /**
     * 首页 【title数据】
     */
    public static final String HOME_TITLE = "v3/User/getinfo.json";

    /**
     * 订阅 【我的订阅数据】
     */
    public static final String SUBSCRIBE_FRAGMENT_ME = "v1/focus/info/get.json";


    /**
     * 排行榜 【列表数据】
     */
    public static final String BILLBOARD_DATA = "v3/article/top/get.json";


    /**
     * 发现 【实时热点】
     */
    public static final String DISCOVER_TOP_NEWS = "v1/Search/suggest.json";
}
