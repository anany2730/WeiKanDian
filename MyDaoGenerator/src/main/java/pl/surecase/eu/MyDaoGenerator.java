package pl.surecase.eu;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

public class MyDaoGenerator {

    public static void main(String args[]) throws Exception {
        Schema schema = new Schema( 1, "me.anany.bean");
        // 1: 数据库版本号
        // com.xxx.bean:自动生成的Bean对象会放到/java-gen/com/xxx/bean中

        schema.setDefaultJavaPackageDao("me.anany.dao");
        // DaoMaster.java、DaoSession.java、BeanDao.java会放到/java-gen/com/xxx/dao中

        // 上面这两个文件夹路径都可以自定义，也可以不设置

        initUserBean(schema); // 初始化Bean了

        new DaoGenerator().generateAll(schema, args[0]);// 自动创建
    }

    private static void initUserBean(Schema schema) {
        Entity homeNewsDataItem = schema.addEntity("HomeNewsDataItem");// 表名
        homeNewsDataItem.setTableName("HomeNewsDataItem"); // 可以对表重命名

        homeNewsDataItem.addStringProperty("id").primaryKey().index();
        //userBean.addStringProperty("id").primaryKey().index();// 主键，索引
        homeNewsDataItem.addStringProperty("is_read");
        homeNewsDataItem.addStringProperty("step");
        homeNewsDataItem.addStringProperty("ct");
        homeNewsDataItem.addStringProperty("catid");
        homeNewsDataItem.addStringProperty("thumb");
        homeNewsDataItem.addStringProperty("idx");
        homeNewsDataItem.addStringProperty("account_id");
        homeNewsDataItem.addStringProperty("isext");
        homeNewsDataItem.addStringProperty("title");
        homeNewsDataItem.addStringProperty("read_num");
        homeNewsDataItem.addStringProperty("like_num");
        homeNewsDataItem.addStringProperty("share_num");
        homeNewsDataItem.addStringProperty("wurl");
        homeNewsDataItem.addStringProperty("video");
        homeNewsDataItem.addStringProperty("image_type");
        homeNewsDataItem.addStringProperty("op_mark");
        homeNewsDataItem.addStringProperty("op_mark_iurl");
        homeNewsDataItem.addStringProperty("op_mark_icolor");
        homeNewsDataItem.addStringProperty("account_name");
        homeNewsDataItem.addStringProperty("url");
        homeNewsDataItem.addStringProperty("is_cache");
        homeNewsDataItem.addStringProperty("article_type");
        homeNewsDataItem.addStringProperty("behot_time");
        homeNewsDataItem.addStringProperty("extraImg");

    }
}
