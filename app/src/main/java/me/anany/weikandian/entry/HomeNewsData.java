package me.anany.weikandian.entry;

import java.io.Serializable;
import java.util.List;

/**
 * Created by anany on 16/1/6.
 * <p>
 * <p>
 * 首页新闻列表 【推荐/笑话/美文/健康】
 * <p>
 * Email:zhujun2730@gmail.com
 */
public class HomeNewsData implements Serializable{

    /**
     * success : true
     * error_code : 0
     * message : 获取成功
     * count : 5
     * step : 2
     * items : [{"video_id":"","cmt_num":"18","input_time":1487211398,"extra":["http://file.weixinkd.com/article_201606_01_012_574f00fd94b63.jpg/180x135","http://file.weixinkd.com/article_201606_01_01o_574f00fe418d4.jpg/180x135","http://file.weixinkd.com/201702_16_164_58a4ec400af9c.png/180x135"],"idx":"0","wurl":"","tagid":"1264","id":"15524309","video":"0","thumb":"http://file.weixinkd.com/article_201606_01_012_574f00fd94b63.jpg/180x135","catid":"2","title":"【荐读】这样说话的人，不要深交！","op_mark":"","like_num":"1","op_mark_iurl":"","isext":"1","share_num":"14","account_id":"44532","read_sum":"2879","image_type":"2","op_mark_icolor":"","article_id":"15524309","read_num":"2879","behot_time":1487211398558,"step":2,"catname":"美文","account_name":"资讯最前沿","avatar":"http://file.weixinkd.com/2015-10-16_5620cd61764a7.jpg","url":"http://www.weixinkd.com/n/15524309","is_cache":"1","article_type":"0","oid":"15524309"},{"video_id":"","cmt_num":"24","input_time":1487211398,"extra":["http://file.weixinkd.com/article_201702_16_16u_58a4ea3d25e29.jpg/180x135","http://file.weixinkd.com/article_201702_16_16a_58a4ea3d7ccdd.jpg/180x135","http://file.weixinkd.com/article_201702_16_16y_58a4ea3e29b9a.jpg/180x135"],"idx":"0","wurl":"","tagid":"48,49,50","id":"15523994","video":"0","thumb":"http://file.weixinkd.com/article_201702_16_16u_58a4ea3d25e29.jpg/180x135","catid":"3","title":"熬夜最伤肝！白开水加点它，让肝脏越来越健康~！","op_mark":"","like_num":"1","op_mark_iurl":"","isext":"1","share_num":"23","account_id":"44532","read_sum":"3182","image_type":"2","op_mark_icolor":"","article_id":"15523994","read_num":"3182","behot_time":1487211398458,"step":2,"catname":"健康","account_name":"资讯最前沿","avatar":"http://file.weixinkd.com/2015-10-16_5620cd61764a7.jpg","url":"http://www.weixinkd.com/n/15523994","is_cache":"1","article_type":"0","oid":"15523994"},{"video_id":"","cmt_num":"29","input_time":1487211398,"extra":["http://file.weixinkd.com/article_201702_16_169_58a4e9c2c1e51.jpg/180x135","http://file.weixinkd.com/article_201702_16_166_58a4e9c3285dd.jpg/180x135","http://file.weixinkd.com/article_201702_16_16b_58a4e9c3aade4.jpg/180x135"],"idx":"0","wurl":"","tagid":"1288","id":"15523932","video":"0","thumb":"http://file.weixinkd.com/article_201702_16_169_58a4e9c2c1e51.jpg/180x135","catid":"18","title":"原来，这就是嫁给爱情的样子。","op_mark":"","like_num":"1","op_mark_iurl":"","isext":"1","share_num":"13","account_id":"44532","read_sum":"3025","image_type":"2","op_mark_icolor":"","article_id":"15523932","read_num":"3025","behot_time":1487211398358,"step":2,"catname":"情感","account_name":"资讯最前沿","avatar":"http://file.weixinkd.com/2015-10-16_5620cd61764a7.jpg","url":"http://www.weixinkd.com/n/15523932","is_cache":"1","article_type":"0","oid":"15523932"},{"video_id":"","cmt_num":"18","input_time":1487211398,"extra":["http://file.weixinkd.com/article_201701_07_07b_5870891f82b29.jpg/180x135","http://file.weixinkd.com/article_201701_07_07p_5870891fc4933.jpg/180x135","http://file.weixinkd.com/article_201701_07_07x_587089201f7ce.jpg/180x135"],"idx":"0","wurl":"","tagid":"907,908","id":"15523838","video":"0","thumb":"http://file.weixinkd.com/article_201701_07_07b_5870891f82b29.jpg/180x135","catid":"4","title":"好多人都说想学这几道食谱，今天就手把手的教给你！","op_mark":"","like_num":"1","op_mark_iurl":"","isext":"1","share_num":"12","account_id":"44532","read_sum":"2915","image_type":"2","op_mark_icolor":"","article_id":"15523838","read_num":"2915","behot_time":1487211398258,"step":2,"catname":"美食","account_name":"资讯最前沿","avatar":"http://file.weixinkd.com/2015-10-16_5620cd61764a7.jpg","url":"http://www.weixinkd.com/n/15523838","is_cache":"1","article_type":"0","oid":"15523838"},{"video_id":"","cmt_num":"12","input_time":1487211398,"extra":["http://file.weixinkd.com/201702_16_16m_58a4eaf8b9fe2.png/180x135","http://file.weixinkd.com/201702_16_16g_58a4eae014c61.png/180x135","http://file.weixinkd.com/201702_16_16d_58a4eb1165856.png/180x135"],"idx":"0","wurl":"","tagid":"49,50","id":"15524104","video":"0","thumb":"http://file.weixinkd.com/201702_16_16m_58a4eaf8b9fe2.png/180x135","catid":"3","title":"【健康】牢记春季状元菜！一整年的健康不用愁                     ","op_mark":"","like_num":"1","op_mark_iurl":"","isext":"1","share_num":"11","account_id":"44532","read_sum":"2343","image_type":"2","op_mark_icolor":"","article_id":"15524104","read_num":"2343","behot_time":1487211398158,"step":2,"catname":"健康","account_name":"资讯最前沿","avatar":"http://file.weixinkd.com/2015-10-16_5620cd61764a7.jpg","url":"http://www.weixinkd.com/n/15524104","is_cache":"1","article_type":"0","oid":"15524104"}]
     * steps : {"down":1,"up":0}
     */

    private boolean success;
    private String error_code;
    private String message;
    private String count;
    private int step;
    private StepsBean steps;
    private List<ItemsBean> items;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getError_code() {
        return error_code;
    }

    public void setError_code(String error_code) {
        this.error_code = error_code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }

    public StepsBean getSteps() {
        return steps;
    }

    public void setSteps(StepsBean steps) {
        this.steps = steps;
    }

    public List<ItemsBean> getItems() {
        return items;
    }

    public void setItems(List<ItemsBean> items) {
        this.items = items;
    }

    public static class StepsBean {
        /**
         * down : 1
         * up : 0
         */

        private int down;
        private int up;

        public int getDown() {
            return down;
        }

        public void setDown(int down) {
            this.down = down;
        }

        public int getUp() {
            return up;
        }

        public void setUp(int up) {
            this.up = up;
        }
    }

    public static class ItemsBean implements Serializable{
        /**
         * video_id :
         * cmt_num : 18
         * input_time : 1487211398
         * extra : ["http://file.weixinkd.com/article_201606_01_012_574f00fd94b63.jpg/180x135","http://file.weixinkd.com/article_201606_01_01o_574f00fe418d4.jpg/180x135","http://file.weixinkd.com/201702_16_164_58a4ec400af9c.png/180x135"]
         * idx : 0
         * wurl :
         * tagid : 1264
         * id : 15524309
         * video : 0
         * thumb : http://file.weixinkd.com/article_201606_01_012_574f00fd94b63.jpg/180x135
         * catid : 2
         * title : 【荐读】这样说话的人，不要深交！
         * op_mark :
         * like_num : 1
         * op_mark_iurl :
         * isext : 1
         * share_num : 14
         * account_id : 44532
         * read_sum : 2879
         * image_type : 2
         * op_mark_icolor :
         * article_id : 15524309
         * read_num : 2879
         * behot_time : 1487211398558
         * step : 2
         * catname : 美文
         * account_name : 资讯最前沿
         * avatar : http://file.weixinkd.com/2015-10-16_5620cd61764a7.jpg
         * url : http://www.weixinkd.com/n/15524309
         * is_cache : 1
         * article_type : 0
         * oid : 15524309
         */

        private String video_id;
        private String cmt_num;
        private int input_time;
        private String idx;
        private String wurl;
        private String tagid;
        private String id;
        private String video;
        private String thumb;
        private String catid;
        private String title;
        private String op_mark;
        private String like_num;
        private String op_mark_iurl;
        private String isext;
        private String share_num;
        private String account_id;
        private String read_sum;
        private String image_type;
        private String op_mark_icolor;
        private String article_id;
        private String read_num;
        private long behot_time;
        private int step;
        private String catname;
        private String account_name;
        private String avatar;
        private String url;
        private String is_cache;
        private String article_type;
        private String oid;
        private List<String> extra;

        public String getVideo_id() {
            return video_id;
        }

        public void setVideo_id(String video_id) {
            this.video_id = video_id;
        }

        public String getCmt_num() {
            return cmt_num;
        }

        public void setCmt_num(String cmt_num) {
            this.cmt_num = cmt_num;
        }

        public int getInput_time() {
            return input_time;
        }

        public void setInput_time(int input_time) {
            this.input_time = input_time;
        }

        public String getIdx() {
            return idx;
        }

        public void setIdx(String idx) {
            this.idx = idx;
        }

        public String getWurl() {
            return wurl;
        }

        public void setWurl(String wurl) {
            this.wurl = wurl;
        }

        public String getTagid() {
            return tagid;
        }

        public void setTagid(String tagid) {
            this.tagid = tagid;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getVideo() {
            return video;
        }

        public void setVideo(String video) {
            this.video = video;
        }

        public String getThumb() {
            return thumb;
        }

        public void setThumb(String thumb) {
            this.thumb = thumb;
        }

        public String getCatid() {
            return catid;
        }

        public void setCatid(String catid) {
            this.catid = catid;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getOp_mark() {
            return op_mark;
        }

        public void setOp_mark(String op_mark) {
            this.op_mark = op_mark;
        }

        public String getLike_num() {
            return like_num;
        }

        public void setLike_num(String like_num) {
            this.like_num = like_num;
        }

        public String getOp_mark_iurl() {
            return op_mark_iurl;
        }

        public void setOp_mark_iurl(String op_mark_iurl) {
            this.op_mark_iurl = op_mark_iurl;
        }

        public String getIsext() {
            return isext;
        }

        public void setIsext(String isext) {
            this.isext = isext;
        }

        public String getShare_num() {
            return share_num;
        }

        public void setShare_num(String share_num) {
            this.share_num = share_num;
        }

        public String getAccount_id() {
            return account_id;
        }

        public void setAccount_id(String account_id) {
            this.account_id = account_id;
        }

        public String getRead_sum() {
            return read_sum;
        }

        public void setRead_sum(String read_sum) {
            this.read_sum = read_sum;
        }

        public String getImage_type() {
            return image_type;
        }

        public void setImage_type(String image_type) {
            this.image_type = image_type;
        }

        public String getOp_mark_icolor() {
            return op_mark_icolor;
        }

        public void setOp_mark_icolor(String op_mark_icolor) {
            this.op_mark_icolor = op_mark_icolor;
        }

        public String getArticle_id() {
            return article_id;
        }

        public void setArticle_id(String article_id) {
            this.article_id = article_id;
        }

        public String getRead_num() {
            return read_num;
        }

        public void setRead_num(String read_num) {
            this.read_num = read_num;
        }

        public long getBehot_time() {
            return behot_time;
        }

        public void setBehot_time(long behot_time) {
            this.behot_time = behot_time;
        }

        public int getStep() {
            return step;
        }

        public void setStep(int step) {
            this.step = step;
        }

        public String getCatname() {
            return catname;
        }

        public void setCatname(String catname) {
            this.catname = catname;
        }

        public String getAccount_name() {
            return account_name;
        }

        public void setAccount_name(String account_name) {
            this.account_name = account_name;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getIs_cache() {
            return is_cache;
        }

        public void setIs_cache(String is_cache) {
            this.is_cache = is_cache;
        }

        public String getArticle_type() {
            return article_type;
        }

        public void setArticle_type(String article_type) {
            this.article_type = article_type;
        }

        public String getOid() {
            return oid;
        }

        public void setOid(String oid) {
            this.oid = oid;
        }

        public List<String> getExtra() {
            return extra;
        }

        public void setExtra(List<String> extra) {
            this.extra = extra;
        }
    }
}
