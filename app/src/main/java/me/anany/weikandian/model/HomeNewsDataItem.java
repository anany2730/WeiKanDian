package me.anany.weikandian.model;

import java.io.Serializable;
import java.util.List;


/**
 * Created by anany on 16/1/11.
 * <p>
 * Email:zhujun2730@gmail.com
 */
public class HomeNewsDataItem implements Serializable {

    private String id;

    private int is_read;// 默认是0；-1表示已读

    private String step;

    private String ct; // 请求时间

    private String catid;
    private String thumb;
    private String idx;
    private String account_id;
    private String isext; //  是否已经缓存web
    private String title;
    private String read_num;
    private String like_num;
    private String share_num;
    private String wurl;
    private String video;
    private String image_type;
    private String input_time;
    private String op_mark;
    private String op_mark_iurl;
    private String op_mark_icolor;
    private String account_name;
    private String url;
    private String is_cache;
    private String article_type;
    private long behot_time;
    private List<String> extra;
    private String extraImg;

    public int getIs_read() {
        return is_read;
    }

    public void setIs_read(int is_read) {
        this.is_read = is_read;
    }

    public String getStep() {
        return step;
    }

    public void setStep(String step) {
        this.step = step;
    }

    public String getCt() {
        return ct;
    }

    public void setCt(String ct) {
        this.ct = ct;
    }

    public String getExtraImg() {
        return extraImg;
    }

    public void setExtraImg(String extraImg) {
        this.extraImg = extraImg;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCatid() {
        return catid;
    }

    public void setCatid(String catid) {
        this.catid = catid;
    }

    public String getThumb() {
        return thumb;
    }

    public void setThumb(String thumb) {
        this.thumb = thumb;
    }

    public String getIdx() {
        return idx;
    }

    public void setIdx(String idx) {
        this.idx = idx;
    }

    public String getAccount_id() {
        return account_id;
    }

    public void setAccount_id(String account_id) {
        this.account_id = account_id;
    }

    public String getIsext() {
        return isext;
    }

    public void setIsext(String isext) {
        this.isext = isext;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRead_num() {
        return read_num;
    }

    public void setRead_num(String read_num) {
        this.read_num = read_num;
    }

    public String getLike_num() {
        return like_num;
    }

    public void setLike_num(String like_num) {
        this.like_num = like_num;
    }

    public String getShare_num() {
        return share_num;
    }

    public void setShare_num(String share_num) {
        this.share_num = share_num;
    }

    public String getWurl() {
        return wurl;
    }

    public void setWurl(String wurl) {
        this.wurl = wurl;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }

    public String getImage_type() {
        return image_type;
    }

    public void setImage_type(String image_type) {
        this.image_type = image_type;
    }

    public String getInput_time() {
        return input_time;
    }

    public void setInput_time(String input_time) {
        this.input_time = input_time;
    }

    public String getOp_mark() {
        return op_mark;
    }

    public void setOp_mark(String op_mark) {
        this.op_mark = op_mark;
    }

    public String getOp_mark_iurl() {
        return op_mark_iurl;
    }

    public void setOp_mark_iurl(String op_mark_iurl) {
        this.op_mark_iurl = op_mark_iurl;
    }

    public String getOp_mark_icolor() {
        return op_mark_icolor;
    }

    public void setOp_mark_icolor(String op_mark_icolor) {
        this.op_mark_icolor = op_mark_icolor;
    }

    public String getAccount_name() {
        return account_name;
    }

    public void setAccount_name(String account_name) {
        this.account_name = account_name;
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

    public long getBehot_time() {
        return behot_time;
    }

    public void setBehot_time(int behot_time) {
        this.behot_time = behot_time;
    }

    public List<String> getExtra() {
        return extra;
    }

    public void setExtra(List<String> extra) {
        this.extra = extra;
    }

}
