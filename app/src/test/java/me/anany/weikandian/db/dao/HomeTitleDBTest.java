package me.anany.weikandian.db.dao;

import de.greenrobot.dao.test.AbstractDaoTestLongPk;

import me.anany.weikandian.db.entity.HomeTitleDB;
import me.anany.weikandian.db.dao.HomeTitleDBDao;

public class HomeTitleDBTest extends AbstractDaoTestLongPk<HomeTitleDBDao, HomeTitleDB> {

    public HomeTitleDBTest() {
        super(HomeTitleDBDao.class);
    }

    @Override
    protected HomeTitleDB createEntity(Long key) {
        HomeTitleDB entity = new HomeTitleDB();
        entity.setId(key);
        return entity;
    }

}
