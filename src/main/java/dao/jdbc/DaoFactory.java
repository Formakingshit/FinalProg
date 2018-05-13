package dao.jdbc;

import dao.IDaoFactory;
import dao.IUserDao;

import java.sql.Connection;

public class DaoFactory implements IDaoFactory{
    private static class InstanceHolder{
        private static final DaoFactory INSTANCE=new DaoFactory();
    }

    private DaoFactory(){}

    public static DaoFactory getInstance(){return  InstanceHolder.INSTANCE;}

    @Override
    public IUserDao getUserDao(Connection connection) {
        return UserDao.getInstance(connection);
    }
}
