package dao;

import model.user.User;
import java.util.List;

public interface IUserDao extends IGenericDao<User> {
    List<User> getUserByLogin(String login);
}

