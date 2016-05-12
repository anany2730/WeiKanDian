package me.anany.weikandian.db.dao;

import de.greenrobot.dao.test.AbstractDaoTestLongPk;

import me.anany.weikandian.db.entity.HomeChannelDB;
import me.anany.weikandian.db.dao.HomeChannelDBDao;

public class HomeChannelDBTest extends AbstractDaoTestLongPk<HomeChannelDBDao, HomeChannelDB> {

    public HomeChannelDBTest() {
        super(HomeChannelDBDao.class);
    }

    @Override
    protected HomeChannelDB createEntity(Long key) {
        HomeChannelDB entity = new HomeChannelDB();
        entity.setId(key);
        return entity;
    }

}
