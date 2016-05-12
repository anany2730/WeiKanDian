package me.anany.weikandian.db.dao;

import de.greenrobot.dao.test.AbstractDaoTestLongPk;

import me.anany.weikandian.db.entity.HomeItemDB;
import me.anany.weikandian.db.dao.HomeItemDBDao;

public class HomeItemDBTest extends AbstractDaoTestLongPk<HomeItemDBDao, HomeItemDB> {

    public HomeItemDBTest() {
        super(HomeItemDBDao.class);
    }

    @Override
    protected HomeItemDB createEntity(Long key) {
        HomeItemDB entity = new HomeItemDB();
        entity.setId(key);
        return entity;
    }

}
