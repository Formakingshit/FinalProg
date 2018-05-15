package service.isolation;

import model.user.User;
import service.IUserService;

import java.util.Optional;

public class UserService extends GenericService implements IUserService{
    @Override
    public Optional<User> getUserByLogin(String login) {
        return executeInNonTransactionalWrapper(transactionManager ->
                transactionManager.getUserDao().getUserByLogin(login));
    }

    @Override
    public User create(User user) {
        return  executeInRepeatableReadWrapper(transactionManager ->
                transactionManager.getUserDao().insert(user));
    }

    @Override
    public Optional<User> getById(int id) {
        return executeInNonTransactionalWrapper(transactionManager ->
                transactionManager.getUserDao().getById(id));
    }

    @Override
    public void update(User user) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void deleteById(int id) {
        throw new UnsupportedOperationException();
    }
}
