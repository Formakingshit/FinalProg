package dao;

import model.User;
import java.util.List;

public interface IUserDao extends IGenericDao<User> {
    List<User> getUserByLogin(String login);
}

