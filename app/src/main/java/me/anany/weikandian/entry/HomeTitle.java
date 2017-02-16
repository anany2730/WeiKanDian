package me.anany.weikandian.entry;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by anany on 16/02/2017.
 */
@Entity
public class HomeTitle {
    /**
     * id : 1453
     * name : 视频
     * sname : 视频
     * pic : http://file.weixinkd.com/201607_15_15m_578840993864e.png/120x120
     * bookcount : 23
     * description :
     * realcount : 23
     */
    @Id
    private Long _id;
    private String id;
    private String name;
    private String sname;
    private String pic;
    private String bookcount;
    private String description;
    private String realcount;
    @Generated(hash = 850189659)
    public HomeTitle(Long _id, String id, String name, String sname, String pic,
            String bookcount, String description, String realcount) {
        this._id = _id;
        this.id = id;
        this.name = name;
        this.sname = sname;
        this.pic = pic;
        this.bookcount = bookcount;
        this.description = description;
        this.realcount = realcount;
    }
    @Generated(hash = 869810785)
    public HomeTitle() {
    }
    public Long get_id() {
        return this._id;
    }
    public void set_id(Long _id) {
        this._id = _id;
    }
    public String getId() {
        return this.id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getSname() {
        return this.sname;
    }
    public void setSname(String sname) {
        this.sname = sname;
    }
    public String getPic() {
        return this.pic;
    }
    public void setPic(String pic) {
        this.pic = pic;
    }
    public String getBookcount() {
        return this.bookcount;
    }
    public void setBookcount(String bookcount) {
        this.bookcount = bookcount;
    }
    public String getDescription() {
        return this.description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getRealcount() {
        return this.realcount;
    }
    public void setRealcount(String realcount) {
        this.realcount = realcount;
    }

}
