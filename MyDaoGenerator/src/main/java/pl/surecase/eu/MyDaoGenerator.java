package pl.surecase.eu;

import de.greenrobot.daogenerator.DaoGenerator;
import de.greenrobot.daogenerator.Entity;
import de.greenrobot.daogenerator.Schema;

public class MyDaoGenerator {

    private MyDaoGenerator() {
    }

    public static void main(String args[]) throws Exception {
        Schema schema = new Schema( 1, "me.anany.bean");
        // 1: 数据库版本号
        // com.xxx.bean:自动生成的Bean对象会放到/java-gen/com/xxx/bean中

        schema.setDefaultJavaPackageDao("me.anany.dao");
        // DaoMaster.java、DaoSession.java、BeanDao.java会放到/java-gen/com/xxx/dao中

        // 上面这两个文件夹路径都可以自定义，也可以不设置

        addHomeDataItem(schema); // 初始化HomeDataItem了

        addHomeTitle(schema);

        addHomeChannel(schema);

        new DaoGenerator().generateAll(schema, args[0]);// 自动创建
    }



    private static void addHomeTitle(Schema schema) {

        Entity homeTitle = schema.addEntity("HomeTitleDB");// 表名
        homeTitle.setTableName("HomeTitleDB"); // 可以对表重命名

        homeTitle.addLongProperty("id").primaryKey().index().autoincrement();
        homeTitle.addStringProperty("name");
        homeTitle.addStringProperty("sort");
        homeTitle.addStringProperty("ut");// 时间
        homeTitle.addStringProperty("pic");
        homeTitle.addStringProperty("cat_id");// cat_id
        homeTitle.addStringProperty("description");
        homeTitle.addStringProperty("is_new");
        homeTitle.addStringProperty("is_use");

    }

    private static void addHomeDataItem(Schema schema) {
        Entity homeNewsDataItem = schema.addEntity("HomeItemDB");// 表名
        homeNewsDataItem.setTableName("HomeItemDB"); // 可以对表重命名

        homeNewsDataItem.addLongProperty("id").primaryKey().index().autoincrement();
        homeNewsDataItem.addStringProperty("is_read");
        homeNewsDataItem.addStringProperty("position");
        homeNewsDataItem.addStringProperty("step");
        homeNewsDataItem.addStringProperty("ct");
        homeNewsDataItem.addStringProperty("input_time");
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

    private static void addHomeChannel(Schema schema) {

        Entity homeChannelItem = schema.addEntity("HomeChannelDB");// 表名
        homeChannelItem.setTableName("HomeChannelDB"); // 可以对表重命名

        homeChannelItem.addLongProperty("id").primaryKey().index().autoincrement();
        homeChannelItem.addStringProperty("channle_id");
        homeChannelItem.addStringProperty("is_use");
        homeChannelItem.addStringProperty("name");
        homeChannelItem.addStringProperty("pic");
        homeChannelItem.addStringProperty("sname");
    }
}
