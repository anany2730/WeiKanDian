package me.anany.weikandian.model;

import java.util.List;

/**
 * Created by anany on 16/1/6.
 * <p/>
 *
 *  发现 【实时热点】
 *
 * Email:zhujun2730@gmail.com
 */
public class DiscoverTopNews {

    /**
     * 请求参数：
     *
     * channel_code : c1005
     * client_version : 2.0.4
     * device_id : phone IMEI
     * device_type : 2 【 meybe means Android】
     * phone_code : 186xxxxxx
     * phone_newwork : WIFI/3G/4G
     * Phone_sim : 1 / 2 / 3  【1 meybe means China MOBILE】
     * uid : 9279697
     * uuid : 13cb971cfd30403dbc80314d4216e6ad
     * sign : 31a8f2e3b1c71932245178cf33367042
     */


    /**
     * success : true
     * error_code : 0
     * message : 执行成功
     * items : [{"id":"808","name":"2016年新规","word":"2016年新规","ishot":"1","sort":"29","status":"1"},{"id":"802","name":"网络支付新规","word":"网络支付新规","ishot":"0","sort":"25","status":"1"},{"id":"807","name":"章子怡产女","word":"章子怡产女","ishot":"0","sort":"24","status":"1"},{"id":"782","name":"长江沉船报告公布","word":"长江沉船报告公布","ishot":"1","sort":"24","status":"1"},{"id":"796","name":"男女签同居协议","word":"男女签同居协议","ishot":"0","sort":"23","status":"1"},{"id":"783","name":"考研被曝泄题","word":"考研被曝泄题","ishot":"0","sort":"23","status":"1"},{"id":"800","name":"中国版奥巴马走红","word":"中国版奥巴马走红","ishot":"0","sort":"23","status":"1"},{"id":"799","name":"NANA夺最美面孔","word":"NANA夺最美面孔","ishot":"0","sort":"23","status":"1"}]
     */

    private boolean success;
    private String error_code;
    private String message;
    /**
     * id : 808
     * name : 2016年新规
     * word : 2016年新规
     * ishot : 1
     * sort : 29
     * status : 1
     */

    private List<DiscoverTopNewsItem> items;

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setError_code(String error_code) {
        this.error_code = error_code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setItems(List<DiscoverTopNewsItem> items) {
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

    public List<DiscoverTopNewsItem> getItems() {
        return items;
    }

    public static class DiscoverTopNewsItem {
        private String id;
        private String name;
        private String word;
        private String ishot;
        private String sort;
        private String status;

        public void setId(String id) {
            this.id = id;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setWord(String word) {
            this.word = word;
        }

        public void setIshot(String ishot) {
            this.ishot = ishot;
        }

        public void setSort(String sort) {
            this.sort = sort;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getWord() {
            return word;
        }

        public String getIshot() {
            return ishot;
        }

        public String getSort() {
            return sort;
        }

        public String getStatus() {
            return status;
        }
    }
}
