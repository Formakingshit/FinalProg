package dao.jdbc;

import dao.IUserDao;
import dao.exception.DaoException;
import model.user.User;
import model.user.UserRole;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDao extends AbstractDao implements IUserDao {
    private static final String SELECT_ALL =
            "SELECT id_user, name, soname, login, password, email, role, rating, pay " +
                    "FROM mydb.user";

    private static final String SELECT_USER_BY_ID = SELECT_ALL + " WHERE id_user =?";

    private static final String SELECT_USER_BY_LOGIN = SELECT_ALL + " WHERE login like '%'||?||'%'";

    private static final String UPDATE_USER_BY_ID = "UPDATE mydb.user" +
            "   SET name=?, soname=?, login=?, password=?, email=?, role=?, rating=?, pay=?" +
            " WHERE id_user=?";

    private static final String INSERT_USER = "INSERT INTO mydb.user" +
            "(name, soname, login, password, email, role, rating, pay)\n" +
            "    VALUES (?, ?, ?, ?, ?, ?, ?, ?);";

    public static final String ID_FIELD_USER = "id";
    public static final String LOGIN_FIELD_USER = "login";
    public static final String NAME_FIELD_USER = "name";
    public static final String SONAME_FIELD_USER = "soname";
    public static final String PASS_FIELD_USER = "password";
    public static final String EMAIL_FIELD_USER = "email";
    public static final String ROLE_FIELD_USER = "role";
    public static final String RATING_FIELD_USER = "rating";
    public static final String PAY_FIELD_USER = "pay";
    private static final String TABLE = "mydb.user";

    private static class InstanceHolder {
        private static UserDao INSTANCE = new UserDao();
    }

    public static UserDao getInstance(Connection connection) {
        /*set ThreadLocal variable*/
        InstanceHolder.INSTANCE.connection.set(connection);
        return InstanceHolder.INSTANCE;
    }

    private UserDao() {
    }

    @Override
    public User insert(User user) {
        checkForNull(user);
        checkIsUnsaved(user);
        try (PreparedStatement statement = connection.get().prepareStatement(INSERT_USER,
                Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getSoname());
            statement.setString(3, user.getLogin());
            statement.setString(4, user.getPassword());
            statement.setString(5, user.getEmail());
            statement.setString(6, String.valueOf(user.getRole()));
            statement.setInt(7, user.getRating());
            statement.setInt(8, user.getPay());
            int userId = executeInsertStatement(statement);
            user.setId(userId);

        } catch (SQLException e) {
            throw new DaoException(e)
                    .addLogMessage(LOG_MESSAGE_DB_ERROR_WHILE_INSERTING + user.toString());
        }
        return user;
    }

    @Override
    public void update(User user) {
        checkForNull(user);
        checkIsSaved(user);
        try (PreparedStatement statement = connection.get().prepareStatement(UPDATE_USER_BY_ID)) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getSoname());
            statement.setString(3, user.getLogin());
            statement.setString(4, user.getPassword());
            statement.setString(5, user.getEmail());
            statement.setString(6, String.valueOf(user.getRole()));
            statement.setInt(7, user.getRating());
            statement.setInt(8, user.getPay());
            statement.setInt(9, user.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException(e)
                    .addLogMessage(LOG_MESSAGE_DB_ERROR_WHILE_UPDATING + user.toString());
        }
    }

    @Override
    public List<User> getAll() {
        try (PreparedStatement statement = connection.get().prepareStatement(SELECT_ALL)) {
            ResultSet resultSet = statement.executeQuery();
            return parseResultSet(resultSet);
        } catch (SQLException e) {
            throw new DaoException(e)
                    .addLogMessage(LOG_MESSAGE_DB_ERROR_WHILE_GETTING_ALL);
        }
    }

    @Override
    public Optional<User> getById(int id) {
        try (PreparedStatement statement = connection.get().prepareStatement(SELECT_USER_BY_ID)) {
            statement.setInt(1, id);
            ResultSet resultSet = statement.executeQuery();
            List<User> userList = parseResultSet(resultSet);
            checkSingleResult(userList);

            return userList.isEmpty() ?
                    Optional.empty() :
                    Optional.of(userList.get(0));

        } catch (SQLException e) {
            throw new DaoException(e)
                    .addLogMessage(LOG_MESSAGE_DB_ERROR_WHILE_GETTING_ALL);
        }
    }

    @Override
    public void removeById(int id) {
        super.deleteById(TABLE, id);
    }

    @Override
    public Optional<User> getUserByLogin(String login) {
        try (PreparedStatement statement = connection.get().prepareStatement(SELECT_USER_BY_LOGIN)) {
            statement.setString(1, login);
            ResultSet resultSet = statement.executeQuery();
            List<User> users = parseResultSet(resultSet);
            return users.isEmpty()
                    ? Optional.empty()
                    : Optional.of(users.get(0));

        } catch (SQLException e) {
            throw new DaoException(e)
                    .addLogMessage(LOG_MESSAGE_DB_ERROR_WHILE_GETTING_ALL);
        }
    }

    private List<User> parseResultSet(ResultSet resultSet) throws SQLException {
        List<User> userList = new ArrayList<>();
        while (resultSet.next()) {
            User user = new User.Builder()
                    .setId(resultSet.getInt(ID_FIELD_USER))
                    .setName(resultSet.getString(NAME_FIELD_USER))
                    .setSoname(resultSet.getString(SONAME_FIELD_USER))
                    .setLogin(resultSet.getString(LOGIN_FIELD_USER))
                    .setPassword(resultSet.getString(PASS_FIELD_USER))
                    .setEmail(resultSet.getString(EMAIL_FIELD_USER))
                    .setRole(UserRole.valueOf(resultSet.getString(ROLE_FIELD_USER)))
                    .setRating(resultSet.getInt(RATING_FIELD_USER))
                    .setPay(resultSet.getInt(PAY_FIELD_USER))
                    .build();

            userList.add(user);
        }
        return userList;
    }
}
