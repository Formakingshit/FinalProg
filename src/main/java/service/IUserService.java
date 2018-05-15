package service;

import model.user.User;
import java.util.Optional;

public interface IUserService extends ICrudService<User> {
    Optional<User> getUserByLogin(String login);
}