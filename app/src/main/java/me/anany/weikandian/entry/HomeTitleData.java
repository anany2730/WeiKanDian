package me.anany.weikandian.entry;

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
     * items : [{"id":"1453","name":"视频","sname":"视频","pic":"http://file.weixinkd.com/201607_15_15m_578840993864e.png/120x120","bookcount":"23","description":"","realcount":"23"},{"id":"21","name":"生活","sname":"生活","pic":"http://file.weixinkd.com/201604_17_17o_5713320dd941f.png/120x120","bookcount":"5500","description":"","realcount":"5500"},{"id":"5","name":"笑话","sname":"笑话","pic":"http://file.weixinkd.com/201604_17_17e_571332f452537.png/120x120","bookcount":"6694","description":"","realcount":"6694"},{"id":"2","name":"美文","sname":"美文","pic":"http://file.weixinkd.com/201604_17_17u_571332d419016.png/120x120","bookcount":"6312","description":"","realcount":"6312"},{"id":"3","name":"健康","sname":"健康","pic":"http://file.weixinkd.com/201604_17_17g_571332b056082.png/120x120","bookcount":"9717","description":"","realcount":"9717"},{"id":"19","name":"娱乐","sname":"娱乐","pic":"http://file.weixinkd.com/201604_17_178_5713328bb414a.png/120x120","bookcount":"9959","description":"","realcount":"9959"},{"id":"25","name":"社会","sname":"社会","pic":"http://file.weixinkd.com/201604_17_17p_5713326d10019.png/120x120","bookcount":"8947","description":"","realcount":"8947"},{"id":"10","name":"科技","sname":"科技","pic":"http://file.weixinkd.com/201604_17_173_5713324da07af.png/120x120","bookcount":"9334","description":"","realcount":"9334"},{"id":"11","name":"财经","sname":"财经","pic":"http://file.weixinkd.com/201604_17_177_571333526ff59.png/120x120","bookcount":"6429","description":"","realcount":"6429"},{"id":"13","name":"汽车","sname":"汽车","pic":"http://file.weixinkd.com/201604_17_17b_571331b9627ed.png/120x120","bookcount":"5325","description":"","realcount":"5325"},{"id":"1307","name":"体育","sname":"体育","pic":"http://file.weixinkd.com/201606_07_07h_5756d1c2290d4.jpg/120x120","bookcount":"4","description":"","realcount":"4"},{"id":"23","name":"探索","sname":"探索","pic":"http://file.weixinkd.com/201604_17_17x_5713311a617c1.png/120x120","bookcount":"6227","description":"","realcount":"6227"},{"id":"6","name":"职场","sname":"职场","pic":"http://file.weixinkd.com/201604_17_17u_571330dec1379.png/120x120","bookcount":"7399","description":"","realcount":"7399"},{"id":"4","name":"美食","sname":"美食","pic":"http://file.weixinkd.com/201604_17_17k_571330bc138f1.png/120x120","bookcount":"7952","description":"","realcount":"7952"},{"id":"17","name":"时尚","sname":"时尚","pic":"http://file.weixinkd.com/201604_17_172_5713307ba9af5.png/120x120","bookcount":"7506","description":"","realcount":"7506"},{"id":"7","name":"旅游","sname":"旅游","pic":"http://file.weixinkd.com/201604_17_17r_57133047d0fbe.png/120x120","bookcount":"7768","description":"","realcount":"7768"},{"id":"8","name":"育儿","sname":"育儿","pic":"http://file.weixinkd.com/201604_17_17a_57133025b9c77.png/120x120","bookcount":"5138","description":"","realcount":"5138"},{"id":"9","name":"教育","sname":"教育","pic":"http://file.weixinkd.com/201604_17_175_57132fead2276.png/120x120","bookcount":"7388","description":"","realcount":"7388"}]
     */

    private boolean success;
    private String error_code;
    private String message;
    private List<HomeTitle> items;

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

    public List<HomeTitle> getItems() {
        return items;
    }

    public void setItems(List<HomeTitle> items) {
        this.items = items;
    }
}
