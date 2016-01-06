package me.anany.weikandian.model;

import java.util.List;

/**
 * Created by anany on 16/1/6.
 * <p/>
 *
 *  首页 【频道管理】
 *
 * Email:zhujun2730@gmail.com
 */
public class HomeTitleManage {


    /**
     * success : true
     * error_code : 0
     * message : 返回成功
     * items : [{"id":"5","name":"笑话","sname":"笑话","is_use":"1","pic":"http://file.weixinkd.com/article_201512_25_258_567ce26b9fe1a.jpg/120x120"},{"id":"2","name":"美文","sname":"美文","is_use":"1","pic":"http://file.weixinkd.com/article_201512_25_253_567ce26c21244.jpg/120x120"},{"id":"3","name":"健康","sname":"健康","is_use":"1","pic":"http://file.weixinkd.com/article_201512_25_25y_567ce26cf34a3.jpg/120x120"},{"id":"19","name":"娱乐","sname":"娱乐","is_use":"1","pic":"http://file.weixinkd.com/article_201512_25_25l_567ce26d70461.jpg/120x120"},{"id":"25","name":"社会","sname":"社会","is_use":"1","pic":"http://file.weixinkd.com/article_201512_25_25l_567ce26e1dfce.jpg/120x120"},{"id":"10","name":"科技","sname":"科技","is_use":"1","pic":"http://file.weixinkd.com/article_201512_25_25e_567ce26e792d5.jpg/120x120"},{"id":"21","name":"生活","sname":"生活","is_use":"1","pic":"http://file.weixinkd.com/article_201512_25_25a_567ce26ed0f7e.jpg/120x120"},{"id":"11","name":"财经","sname":"财经","is_use":"1","pic":"http://file.weixinkd.com/article_201512_25_25e_567ce26f36e55.jpg/120x120"},{"id":"13","name":"汽车","sname":"汽车","is_use":"1","pic":"http://file.weixinkd.com/article_201512_25_25t_567ce26f9bacf.jpg/120x120"},{"id":"15","name":"体育","sname":"体育","is_use":"0","pic":"http://file.weixinkd.com/article_201512_25_25m_567ce2705eb84.jpg/120x120"},{"id":"18","name":"情感","sname":"情感","is_use":"0","pic":"http://file.weixinkd.com/article_201512_25_25o_567ce270d3e81.jpg/120x120"},{"id":"23","name":"探索","sname":"探索","is_use":"1","pic":"http://file.weixinkd.com/article_201512_25_25m_567ce271bdab4.jpg/120x120"},{"id":"6","name":"职场","sname":"职场","is_use":"1","pic":"http://file.weixinkd.com/article_201512_25_256_567ce27217fe9.jpg/120x120"},{"id":"4","name":"美食","sname":"美食","is_use":"1","pic":"http://file.weixinkd.com/article_201512_25_25f_567ce27273c78.jpg/120x120"},{"id":"17","name":"时尚","sname":"时尚","is_use":"1","pic":"http://file.weixinkd.com/article_201512_25_25d_567ce272e45bf.jpg/120x120"},{"id":"7","name":"旅游","sname":"旅游","is_use":"1","pic":"http://file.weixinkd.com/article_201512_25_25x_567ce273522c6.jpg/120x120"},{"id":"8","name":"育儿","sname":"育儿","is_use":"1","pic":"http://file.weixinkd.com/article_201512_25_256_567ce273c616c.jpg/120x120"},{"id":"9","name":"教育","sname":"教育","is_use":"1","pic":"http://file.weixinkd.com/article_201512_25_25s_567ce2743af9b.jpg/120x120"},{"id":"16","name":"星座","sname":"星座","is_use":"0","pic":"http://file.weixinkd.com/article_201512_25_255_567ce27493f98.jpg/120x120"},{"id":"24","name":"宠物","sname":"宠物","is_use":"0","pic":"http://file.weixinkd.com/article_201512_25_25k_567ce27508009.jpg/120x120"},{"id":"151","name":"明星八卦","sname":"明星八卦","is_use":"1","pic":"http://file.weixinkd.com/article_201509_23_23l_56024893ed212.jpg/120x120"},{"id":"1274","name":"社会资讯","sname":"社会资讯","is_use":"1","pic":"http://file.weixinkd.com/201509_23_23w_56028b6f0ee4c.jpg/120x120"},{"id":"542","name":"国内财经","sname":"国内财经","is_use":"1","pic":"http://file.weixinkd.com/article_201509_23_231_560249f7e9c06.jpg/120x120"},{"id":"1297","name":"要闻","sname":"要闻","is_use":"1","pic":"http://file.weixinkd.com/201511_19_190_564d97702379b.jpg/120x120"},{"id":"149","name":"内地娱乐","sname":"内地娱乐","is_use":"1","pic":"http://file.weixinkd.com/article_201509_23_23h_560248936ab81.jpg/120x120"},{"id":"1256","name":"图片笑话","sname":"图片笑话","is_use":"1","pic":"http://file.weixinkd.com/201509_23_23l_560275e2cb26c.jpg/120x120"},{"id":"150","name":"港台娱乐","sname":"港台娱乐","is_use":"1","pic":"http://file.weixinkd.com/article_201509_23_23u_56024893a861f.jpg/120x120"},{"id":"48","name":"养生","sname":"养生","is_use":"1","pic":"http://file.weixinkd.com/article_201509_23_23i_56024833965f1.jpg/120x120"},{"id":"285","name":"民生政策","sname":"民生政策","is_use":"1","pic":"http://file.weixinkd.com/article_201509_23_236_560248ec21f40.jpg/120x120"},{"id":"284","name":"法制案例","sname":"法制案例","is_use":"1","pic":"http://file.weixinkd.com/article_201509_23_230_560248ebca63b.jpg/120x120"},{"id":"736","name":"野史趣闻","sname":"野史趣闻","is_use":"1","pic":"http://file.weixinkd.com/article_201509_23_236_56024ab947fa9.jpg/120x120"},{"id":"1260","name":"经典语录","sname":"经典语录","is_use":"1","pic":"http://file.weixinkd.com/201509_23_23a_560276cb1d134.jpg/120x120"},{"id":"148","name":"电视剧","sname":"电视剧","is_use":"1","pic":"http://file.weixinkd.com/article_201509_23_23r_56024892e6e1d.jpg/120x120"},{"id":"146","name":"电影","sname":"电影","is_use":"1","pic":"http://file.weixinkd.com/article_201509_23_23i_560248923199c.jpg/120x120"},{"id":"49","name":"男性健康","sname":"男性健康","is_use":"1","pic":"http://file.weixinkd.com/article_201509_23_23w_56024833dc8d5.jpg/120x120"},{"id":"50","name":"女性健康","sname":"女性健康","is_use":"1","pic":"http://file.weixinkd.com/article_201509_23_23y_560248343eb00.jpg/120x120"},{"id":"1293","name":"足球","sname":"足球","is_use":"1","pic":"http://file.weixinkd.com/201509_23_23o_560298967d777.jpg/120x120"},{"id":"543","name":"国际财经","sname":"国际财经","is_use":"1","pic":"http://file.weixinkd.com/article_201509_23_23b_560249f847fdb.jpg/120x120"},{"id":"1257","name":"文字笑话","sname":"文字笑话","is_use":"1","pic":"http://file.weixinkd.com/201509_23_23f_5602767052bee.png/120x120"},{"id":"1258","name":"GIF图","sname":"GIF图","is_use":"1","pic":"http://file.weixinkd.com/201509_23_237_56027652752d5.jpg/120x120"},{"id":"1300","name":"国防力量","sname":"国防力量","is_use":"1","pic":"http://file.weixinkd.com/201512_08_08m_5666ab4632c5a.jpg/120x120"},{"id":"147","name":"音乐","sname":"音乐","is_use":"1","pic":"http://file.weixinkd.com/article_201509_23_23b_56024892a3bf1.jpg/120x120"},{"id":"1141","name":"饮食营养","sname":"饮食营养","is_use":"1","pic":"http://file.weixinkd.com/article_201509_23_23j_56024c85d8f83.jpg/120x120"},{"id":"153","name":"日韩娱乐","sname":"日韩娱乐","is_use":"1","pic":"http://file.weixinkd.com/article_201509_23_23u_56024894bdc73.jpg/120x120"},{"id":"286","name":"家庭伦理","sname":"家庭伦理","is_use":"1","pic":"http://file.weixinkd.com/article_201509_23_231_560248ec8eb2c.jpg/120x120"},{"id":"1275","name":"人间百态","sname":"人间百态","is_use":"1","pic":"http://file.weixinkd.com/201509_23_23v_56028beb2c94e.jpg/120x120"},{"id":"1259","name":"心灵鸡汤","sname":"心灵鸡汤","is_use":"1","pic":"http://file.weixinkd.com/201509_23_232_560276d958097.jpg/120x120"},{"id":"619","name":"汽车资讯","sname":"汽车资讯","is_use":"1","pic":"http://file.weixinkd.com/article_201509_23_23d_56024a3fcd32e.jpg/120x120"},{"id":"803","name":"企业管理","sname":"企业管理","is_use":"1","pic":"http://file.weixinkd.com/article_201509_23_23b_56024b0ce3410.jpg/120x120"},{"id":"1023","name":"旅游攻略","sname":"旅游攻略","is_use":"1","pic":"http://file.weixinkd.com/article_201509_23_235_56024c1fbb3c7.jpg/120x120"}]
     */

    private boolean success;
    private String error_code;
    private String message;
    /**
     * id : 5
     * name : 笑话
     * sname : 笑话
     * is_use : 1
     * pic : http://file.weixinkd.com/article_201512_25_258_567ce26b9fe1a.jpg/120x120
     */

    private List<ItemsEntity> items;

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setError_code(String error_code) {
        this.error_code = error_code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setItems(List<ItemsEntity> items) {
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

    public List<ItemsEntity> getItems() {
        return items;
    }

    public static class ItemsEntity {
        private String id;
        private String name;
        private String sname;
        private String is_use;
        private String pic;

        public void setId(String id) {
            this.id = id;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setSname(String sname) {
            this.sname = sname;
        }

        public void setIs_use(String is_use) {
            this.is_use = is_use;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public String getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public String getSname() {
            return sname;
        }

        public String getIs_use() {
            return is_use;
        }

        public String getPic() {
            return pic;
        }
    }
}
