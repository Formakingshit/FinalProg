package dao.jdbc;

import dao.IUserDao;
import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao extends AbstractDao implements IUserDao{
    private static final String SELECT_ALL="SELECT id, Login, Password FROM mydb.user";

    public static final String ID_FIELD_USER ="id";
    public static final String LOGIN_FIELD_USER ="login";
    public static final String PASS_FIELD_USER ="pass";

    @Override
    public List<User> getUserByLogin(String login) {
        return null;
    }

    @Override
    public User insert(User obj) {
        return null;
    }

    @Override
    public void update(User obj) {

    }

    @Override
    public List<User> getAll() {
        try(PreparedStatement statement=connection.get().prepareStatement(SELECT_ALL)) {
            ResultSet resultSet=statement.executeQuery();
            return parseResultSet(resultSet);
        } catch (SQLException e) {
  //          throw new DaoException(e)
    //                .addLogMessage(LOG_MESSAGE_DB_ERROR_WHILE_GETTING_ALL);
        }
        return null;
    }

    @Override
    public void removeById(int id) {

    }
    private List<User> parseResultSet(ResultSet resultSet) throws SQLException {
        List<User> userList = new ArrayList<User>();
        while (resultSet.next()) {
            User user = new User.Builder()
                    .setId(resultSet.getInt(ID_FIELD_USER))
                    .setLogin(resultSet.getString(LOGIN_FIELD_USER))
                    .setPassword(resultSet.getString(PASS_FIELD_USER))
                    .build();

            userList.add(user);
        }
        return userList;
    }
}
