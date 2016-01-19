package me.anany.weikandian.model;

import java.util.List;

/**
 * Created by anany on 16/1/6.
 * <p/>
 *
 *  首页新闻title  ： 推荐/笑话/美文/健康
 *
 * Email:zhujun2730@gmail.com
 */
public class HomeTitleData {


    /**
     * success : true
     * error_code : 0
     * message : 成功
     * items : [{"id":"5","name":"笑话","sname":"笑话","pic":"http://file.weixinkd.com/article_201512_25_258_567ce26b9fe1a.jpg/120x120","bookcount":"6690","description":"","realcount":"6690"},{"id":"2","name":"美文","sname":"美文","pic":"http://file.weixinkd.com/article_201512_25_253_567ce26c21244.jpg/120x120","bookcount":"6307","description":"","realcount":"6307"},{"id":"3","name":"健康","sname":"健康","pic":"http://file.weixinkd.com/article_201512_25_25y_567ce26cf34a3.jpg/120x120","bookcount":"9712","description":"","realcount":"9712"},{"id":"19","name":"娱乐","sname":"娱乐","pic":"http://file.weixinkd.com/article_201512_25_25l_567ce26d70461.jpg/120x120","bookcount":"9955","description":"","realcount":"9955"},{"id":"25","name":"社会","sname":"社会","pic":"http://file.weixinkd.com/article_201512_25_25l_567ce26e1dfce.jpg/120x120","bookcount":"8945","description":"","realcount":"8945"},{"id":"10","name":"科技","sname":"科技","pic":"http://file.weixinkd.com/article_201512_25_25e_567ce26e792d5.jpg/120x120","bookcount":"9333","description":"","realcount":"9333"},{"id":"21","name":"生活","sname":"生活","pic":"http://file.weixinkd.com/article_201512_25_25a_567ce26ed0f7e.jpg/120x120","bookcount":"5498","description":"","realcount":"5498"},{"id":"11","name":"财经","sname":"财经","pic":"http://file.weixinkd.com/article_201512_25_25e_567ce26f36e55.jpg/120x120","bookcount":"6427","description":"","realcount":"6427"},{"id":"13","name":"汽车","sname":"汽车","pic":"http://file.weixinkd.com/article_201512_25_25t_567ce26f9bacf.jpg/120x120","bookcount":"5324","description":"","realcount":"5324"},{"id":"23","name":"探索","sname":"探索","pic":"http://file.weixinkd.com/article_201512_25_25m_567ce271bdab4.jpg/120x120","bookcount":"6225","description":"","realcount":"6225"},{"id":"6","name":"职场","sname":"职场","pic":"http://file.weixinkd.com/article_201512_25_256_567ce27217fe9.jpg/120x120","bookcount":"7398","description":"","realcount":"7398"},{"id":"4","name":"美食","sname":"美食","pic":"http://file.weixinkd.com/article_201512_25_25f_567ce27273c78.jpg/120x120","bookcount":"7946","description":"","realcount":"7946"},{"id":"17","name":"时尚","sname":"时尚","pic":"http://file.weixinkd.com/article_201512_25_25d_567ce272e45bf.jpg/120x120","bookcount":"7501","description":"","realcount":"7501"},{"id":"7","name":"旅游","sname":"旅游","pic":"http://file.weixinkd.com/article_201512_25_25x_567ce273522c6.jpg/120x120","bookcount":"7765","description":"","realcount":"7765"},{"id":"8","name":"育儿","sname":"育儿","pic":"http://file.weixinkd.com/article_201512_25_256_567ce273c616c.jpg/120x120","bookcount":"5137","description":"","realcount":"5137"},{"id":"9","name":"教育","sname":"教育","pic":"http://file.weixinkd.com/article_201512_25_25s_567ce2743af9b.jpg/120x120","bookcount":"7385","description":"","realcount":"7385"}]
     */

    private boolean success;
    private String error_code;
    private String message;
    /**
     * id : 5
     * name : 笑话
     * sname : 笑话
     * pic : http://file.weixinkd.com/article_201512_25_258_567ce26b9fe1a.jpg/120x120
     * bookcount : 6690
     * description :
     * realcount : 6690
     */

    private List<HomeTitleItem> items;

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public void setError_code(String error_code) {
        this.error_code = error_code;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setItems(List<HomeTitleItem> items) {
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

    public List<HomeTitleItem> getItems() {
        return items;
    }

    public static class HomeTitleItem {
        private String id;
        private String name;
        private String sname;
        private String pic;
        private String bookcount;
        private String description;
        private String realcount;

        public void setId(String id) {
            this.id = id;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setSname(String sname) {
            this.sname = sname;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public void setBookcount(String bookcount) {
            this.bookcount = bookcount;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public void setRealcount(String realcount) {
            this.realcount = realcount;
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

        public String getPic() {
            return pic;
        }

        public String getBookcount() {
            return bookcount;
        }

        public String getDescription() {
            return description;
        }

        public String getRealcount() {
            return realcount;
        }

        @Override
        public String toString() {
            return "HomeFragmentTitleItem{" +
                    "id='" + id + '\'' +
                    ", name='" + name + '\'' +
                    ", sname='" + sname + '\'' +
                    ", pic='" + pic + '\'' +
                    ", bookcount='" + bookcount + '\'' +
                    ", description='" + description + '\'' +
                    ", realcount='" + realcount + '\'' +
                    '}';
        }
    }

    @Override
    public String toString() {
        return "HomeTitleData{" +
                "success=" + success +
                ", error_code='" + error_code + '\'' +
                ", message='" + message + '\'' +
                ", items=" + items +
                '}';
    }
}
