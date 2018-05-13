package dao;

import model.user.User;
import java.util.List;
import java.util.Optional;

public interface IUserDao extends IGenericDao<User> {
    Optional<User> getUserByLogin(String login);
}

