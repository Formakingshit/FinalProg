package service;

import model.User;
import java.util.List;

public interface IUserService extends ICrudService<User> {
    List<User> getUserByLogin(String login);
}