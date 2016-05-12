package me.anany.weikandian.model;

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
     * 请求参数：
     * access : WIFI
     * app_version : 2.0.4
     * catid : 5 【每个title的id】
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
     * step : 6
     * count : 12
     * stime : 0.1291
     * items : [{"id":"5813843","catid":"19","thumb":"http://file.weixinkd.com/article_201601_06_066_568c54c721783.jpg/180x135","idx":"5","account_id":"47127","isext":"1","extra":["http://file.weixinkd.com/article_201601_06_066_568c54c721783.jpg/180x135","http://file.weixinkd.com/article_201601_06_060_568c54c78e8d8.jpg/180x135","http://file.weixinkd.com/article_201601_06_06m_568c54c8083b9.jpg/180x135"],"title":"樊少皇贾晓晨低调登记成婚 女方收40万钻戒","read_num":"14190","like_num":"31","share_num":"29","wurl":"http://m2.people.cn/r/MV80XzU5MjA4MzNfOTU5XzE0NTIwMzU1MDE=?__from=yidian","video":"0","image_type":"2","input_time":"1452035501","op_mark":"","op_mark_iurl":"","op_mark_icolor":"","account_name":"手机人民网","url":"http://www.weixinkd.com/n/5813843","is_cache":"1","article_type":"0","behot_time":1452046036},{"id":"5815384","catid":"19","thumb":"http://file.weixinkd.com/article_201601_06_06o_568c6a96c2359.jpg/180x135","idx":"9","account_id":"7235","isext":"1","extra":["http://file.weixinkd.com/article_201601_06_06o_568c6a96c2359.jpg/180x135","http://file.weixinkd.com/article_201601_06_06n_568c6a9729002.jpg/180x135","http://file.weixinkd.com/article_201601_06_060_568c6a97869ef.jpg/180x135"],"title":"独家：胡歌幽会富二代美女 疑似新恋情曝光","read_num":"17626","like_num":"33","share_num":"14","wurl":"http://www.quanminxingtan.com/news/568b868eac9bdf48fa05ebfd/share","video":"0","image_type":"2","input_time":"1452042251","op_mark":"推荐","op_mark_iurl":"http://file.weixinkd.com/201510_30_30w_5633543d9ce97.png","op_mark_icolor":"#5796ff","account_name":"全民星探","url":"http://www.weixinkd.com/n/5815384","is_cache":"1","article_type":"0","behot_time":1452045876},{"id":"5814161","catid":"25","thumb":"http://file.weixinkd.com/article_201601_06_063_568c5a5908a6e.jpg/180x135","idx":"8","account_id":"52486","isext":"0","extra":[],"title":"男子借变声软件约出前女友后用鞋带将其勒死(图)","read_num":"60173","like_num":"13","share_num":"8","wurl":"http://www.yidianzixun.com/mp/content?id=2625429","video":"0","image_type":"1","input_time":"1452037402","op_mark":"社会","op_mark_iurl":"http://file.weixinkd.com/201511_06_062_563c3f685d2f5.png","op_mark_icolor":"#fc9b00","account_name":"社会本色","url":"http://www.weixinkd.com/n/5814161","is_cache":"1","article_type":"0","behot_time":1452045716},{"id":"5812384","catid":"25","thumb":"http://file.weixinkd.com/article_201601_06_06g_568c3e1b73ecb.jpg/180x135","idx":"6","account_id":"5236","isext":"1","extra":["http://file.weixinkd.com/article_201601_06_06g_568c3e1b73ecb.jpg/180x135","http://file.weixinkd.com/article_201601_06_06b_568c3e1bc6fbf.jpg/180x135","http://file.weixinkd.com/article_201601_06_06s_568c3e1c515c8.jpg/180x135"],"title":"多部委晒新年计划 多个重大改革方案有望出台","read_num":"37603","like_num":"2","share_num":"8","wurl":"http://www.chinanews.com/gn/2016/01-06/7702820.shtml","video":"0","image_type":"2","input_time":"1452030438","op_mark":"要闻","op_mark_iurl":"http://file.weixinkd.com/201511_19_19h_564db66e1402d.png","op_mark_icolor":"#74b7db","account_name":"中国新闻网","url":"http://www.weixinkd.com/n/5812384","is_cache":"1","article_type":"0","behot_time":1452045556},{"id":"5814733","catid":"19","thumb":"http://file.weixinkd.com/article_201601_06_066_568c62e33e0a4.jpg/180x135","idx":"4","account_id":"47142","isext":"1","extra":["http://file.weixinkd.com/article_201601_06_066_568c62e33e0a4.jpg/180x135","http://file.weixinkd.com/article_201601_06_06i_568c62e451c71.jpg/180x135","http://file.weixinkd.com/article_201601_06_06h_568c62e4c03cc.jpg/180x135"],"title":"67岁洪金宝短袖抵京 变\u201c重量级行李\u201d逗乐众人","read_num":"81440","like_num":"4","share_num":"1","wurl":"http://ient.ifeng.com/yidian/42557277/news.shtml?ch=ref_zbs_ydzx_news","video":"0","image_type":"2","input_time":"1452039562","op_mark":"","op_mark_iurl":"","op_mark_icolor":"","account_name":"凤凰图片","url":"http://www.weixinkd.com/n/5814733","is_cache":"1","article_type":"0","behot_time":1452045396},{"id":"5814864","catid":"25","thumb":"http://file.weixinkd.com/article_201601_06_06l_568c651c82a40.jpg/180x135","idx":"9","account_id":"47195","isext":"0","extra":[],"title":"街头白色轿车被喷\u201c小三\u201d 当事人被人肉","read_num":"15146","like_num":"1","share_num":"3","wurl":"http://m.cnr.cn/finance/20160106/t20160106_521039907_yd.html","video":"0","image_type":"1","input_time":"1452035760","op_mark":"","op_mark_iurl":"","op_mark_icolor":"","account_name":"手机央广网","url":"http://www.weixinkd.com/n/5814864","is_cache":"1","article_type":"0","behot_time":1452045236},{"id":"5799092","catid":"24","thumb":"http://file.weixinkd.com/article_201601_05_05b_568b2876a494f.jpg/180x135","idx":"4","account_id":"1660","isext":"1","extra":["http://file.weixinkd.com/article_201601_05_05b_568b2876a494f.jpg/180x135","http://file.weixinkd.com/article_201601_05_05h_568b287747dea.jpg/180x135","http://file.weixinkd.com/article_201601_05_05b_568b287871700.jpg/180x135"],"title":"这些汪星人只要一坐下，就要被导演潜规则了！","read_num":"21971","like_num":"0","share_num":"4","wurl":"http://toutiao.com/a6236114028353175810/?iid=2927750443&app=news_article","video":"0","image_type":"2","input_time":"1451959278","op_mark":"","op_mark_iurl":"","op_mark_icolor":"","step":"5","account_name":"好狗狗","url":"http://www.weixinkd.com/n/5799092","is_cache":"1","article_type":"0","behot_time":1452045076},{"id":"5803479","catid":"6","thumb":"http://file.weixinkd.com/article_201601_05_05i_568b578ec0ab1.jpg/180x135","idx":"4","account_id":"5628","isext":"0","extra":[],"title":"纪中展：创始人公关必修课\u2014狼来了喊两次就好，不要喊三次","read_num":"67083","like_num":"0","share_num":"0","wurl":"http://mp.weixin.qq.com/s?__biz=MjM5NjI1NDEyMg==&mid=402349654&idx=1&sn=0ced1059f75b9995c7ddbdf7812974c6&3rd=MjM5NzM2NjUzNg==&sc","video":"0","image_type":"1","input_time":"1451961918","op_mark":"","op_mark_iurl":"","op_mark_icolor":"","step":"5","account_name":"老纪读书","url":"http://www.weixinkd.com/n/5803479","is_cache":"1","article_type":"0","behot_time":1452044916},{"id":"5805318","catid":"3","thumb":"http://file.weixinkd.com/article_201601_05_05d_568b6827c2c90.jpg/180x135","idx":"1","account_id":"49653","isext":"0","extra":[],"title":"湿疹治疗偏方大全 湿疹最佳治疗方法","read_num":"83121","like_num":"20","share_num":"11","wurl":"http://www.ithello.cn/chuangye/20160105/19695.html","video":"0","image_type":"1","input_time":"1451962020","op_mark":"","op_mark_iurl":"","op_mark_icolor":"","step":"5","account_name":"ithello","url":"http://www.weixinkd.com/n/5805318","is_cache":"1","article_type":"0","behot_time":1452044756},{"id":"5805850","catid":"17","thumb":"http://file.weixinkd.com/article_201601_05_052_568b6d87549d4.jpg/180x135","idx":"3","account_id":"5628","isext":"1","extra":["http://file.weixinkd.com/article_201601_05_052_568b6d87549d4.jpg/180x135","http://file.weixinkd.com/article_201601_05_05f_568b6d881ac31.jpg/180x135","http://file.weixinkd.com/article_201601_05_05t_568b6d88947c0.jpg/180x135"],"title":"肌肉小腿？瘦腿就要这样做，8天就会瘦，牙签腿指日可待！","read_num":"86038","like_num":"0","share_num":"7","wurl":"http://www.yidianzixun.com/mp/content?id=2619261","video":"0","image_type":"2","input_time":"1451966585","op_mark":"","op_mark_iurl":"","op_mark_icolor":"","step":"5","account_name":"开心满天飞","url":"http://www.weixinkd.com/n/5805850","is_cache":"1","article_type":"0","behot_time":1452044596},{"id":"5807187","catid":"13","thumb":"http://file.weixinkd.com/article_201601_05_05w_568b7c5eec666.jpg/180x135","idx":"8","account_id":"5628","isext":"0","extra":[],"title":"乐视要做年轻人的第一辆电动汽车 没戏！","read_num":"96465","like_num":"0","share_num":"0","wurl":"http://niubsir.baijia.baidu.com/article/286553","video":"0","image_type":"1","input_time":"1451974620","op_mark":"","op_mark_iurl":"","op_mark_icolor":"","step":"5","account_name":"牛扬","url":"http://www.weixinkd.com/n/5807187","is_cache":"1","article_type":"0","behot_time":1452044436},{"id":"5809006","catid":"16","thumb":"http://file.weixinkd.com/article_201601_05_05c_568b8e40dfd0f.jpg/180x135","idx":"5","account_id":"5628","isext":"1","extra":["http://file.weixinkd.com/article_201601_05_05c_568b8e40dfd0f.jpg/180x135","http://file.weixinkd.com/article_201601_05_05v_568b8e414d030.jpg/180x135","http://file.weixinkd.com/article_201601_05_05k_568b8e41af5d1.jpg/180x135"],"title":"美的让人羡慕，这些星座女神的美，惊艳了时光！","read_num":"64446","like_num":"0","share_num":"1","wurl":"http://3g.d1xz.net/astro/shishang/art117767.aspx","video":"0","image_type":"2","input_time":"1451982328","op_mark":"","op_mark_iurl":"","op_mark_icolor":"","step":"5","account_name":"第一星座网","url":"http://www.weixinkd.com/n/5809006","is_cache":"1","article_type":"0","behot_time":1452044276}]
     */

    private boolean success;
    private String error_code;
    private String message;
    private int step;
    private int count;
    private String stime;
    /**
     * id : 5813843
     * catid : 19
     * thumb : http://file.weixinkd.com/article_201601_06_066_568c54c721783.jpg/180x135
     * idx : 5
     * account_id : 47127
     * isext : 1
     * extra : ["http://file.weixinkd.com/article_201601_06_066_568c54c721783.jpg/180x135","http://file.weixinkd.com/article_201601_06_060_568c54c78e8d8.jpg/180x135","http://file.weixinkd.com/article_201601_06_06m_568c54c8083b9.jpg/180x135"]
     * title : 樊少皇贾晓晨低调登记成婚 女方收40万钻戒
     * read_num : 14190
     * like_num : 31
     * share_num : 29
     * wurl : http://m2.people.cn/r/MV80XzU5MjA4MzNfOTU5XzE0NTIwMzU1MDE=?__from=yidian
     * video : 0
     * image_type : 2
     * input_time : 1452035501
     * op_mark :
     * op_mark_iurl :
     * op_mark_icolor :
     * account_name : 手机人民网
     * url : http://www.weixinkd.com/n/5813843
     * is_cache : 1
     * article_type : 0
     * behot_time : 1452046036
     */

    private List<HomeNewsDataItem> items;

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

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getStime() {
        return stime;
    }

    public void setStime(String stime) {
        this.stime = stime;
    }

    public List<HomeNewsDataItem> getItems() {
        return items;
    }

    public void setItems(List<HomeNewsDataItem> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        return "HomeNewsData{" +
                "success=" + success +
                ", error_code='" + error_code + '\'' +
                ", message='" + message + '\'' +
                ", step=" + step +
                ", count=" + count +
                ", stime='" + stime + '\'' +
                ", items=" + items +
                '}';
    }
}
