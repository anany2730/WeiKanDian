package me.anany.weikandian.model;

import java.util.List;

/**
 * Created by anany on 16/1/6.
 * <p/>
 *
 * 发现 【贴心生活】
 *
 * Email:zhujun2730@gmail.com
 */
public class DiscoverLife {

    /**
     * 请求参数：
     * access : WIFI
     * app_version : 2.0.4
     * channel : c1005
     * device_model : Nexus 4
     * device_platform : android
     * iid : 6416405
     * openuidi : 7f08bcd287cc5096
     * os_api : 22
     * os_version : 5.1.1
     * phone_sim : 1 / 2 / 3  【1 meybe means China MOBILE】
     * request_time : current request time
     * uid : 9279697
     * uuid : phone IMIE
     * version_code : 204
     * sign : 31a8f2e3b1c71932245178cf33367042
     */


    /**
     * success : true
     * error_code : 0
     * message : 返回成功
     * items : [{"id":"67","title":"查快递","thumb":"http://file.weixinkd.com/201509_02_02r_55e6ac407d9c1.png","url":"http://m.kuaidi100.com/ucdaohang/","is_new":0},{"id":"68","title":"查公交","thumb":"http://file.weixinkd.com/201509_02_026_55e6ac4d8bb50.png","url":"http://zuoche.com/touch/","is_new":0},{"id":"69","title":"查违章","thumb":"http://file.weixinkd.com/201509_02_02r_55e6ac56e1ac8.png","url":"http://a.xcar.com.cn/tool/weizhang//?zoneclick=102091","is_new":0},{"id":"70","title":"查火车","thumb":"http://file.weixinkd.com/201509_02_02a_55e6ac624d461.png","url":"https://qpb.uc.cn/?uc_param_str=nidnvessbifrpfcpcheiwi&utm_source=navi_local_shwg_zf&utm_medium=site&utm_campaign=chunyun#!/home","is_new":0},{"id":"72","title":"查天气","thumb":"http://file.weixinkd.com/201509_02_026_55e6ac71efc43.png","url":"http://m.sm.cn/s?q=%E5%A4%A9%E6%B0%94&from=sm&by=submit&snum=6","is_new":0},{"id":"71","title":"特卖场","thumb":"http://file.weixinkd.com/201509_02_02n_55e6ac836204b.png","url":"http://temai.m.taobao.com/cheap.htm?pt=1&pid=mm_31288322_9992877_38050256","is_new":0},{"id":"73","title":"小游戏","thumb":"http://file.weixinkd.com/201509_02_02j_55e6ac9342c40.png","url":"http://www.liebao.cn/game/","is_new":1}]
     */

    private boolean success;
    private String error_code;
    private String message;
    /**
     * id : 67
     * title : 查快递
     * thumb : http://file.weixinkd.com/201509_02_02r_55e6ac407d9c1.png
     * url : http://m.kuaidi100.com/ucdaohang/
     * is_new : 0
     */

    private List<DiscoverLifeItem> items;

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setError_code(String error_code) {
        this.error_code = error_code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setItems(List<DiscoverLifeItem> items) {
        this.items = items;
    }

    public boolean isSuccess() {
        return success;
    }

    public String getError_code() {
        return error_code;
    }

    public String getMessage() {
        return message;
    }

    public List<DiscoverLifeItem> getItems() {
        return items;
    }

    public static class DiscoverLifeItem {
        private String id;
        private String title;
        private String thumb;
        private String url;
        private int is_new;

        public void setId(String id) {
            this.id = id;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public void setThumb(String thumb) {
            this.thumb = thumb;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public void setIs_new(int is_new) {
            this.is_new = is_new;
        }

        public String getId() {
            return id;
        }

        public String getTitle() {
            return title;
        }

        public String getThumb() {
            return thumb;
        }

        public String getUrl() {
            return url;
        }

        public int getIs_new() {
            return is_new;
        }
    }
}
