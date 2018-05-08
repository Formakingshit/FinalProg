package dao;

import java.sql.Connection;

public interface IDaoFactory {
    IUserDao getUserDao(Connection connection);
}

