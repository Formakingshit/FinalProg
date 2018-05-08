package dao.jdbc;

import java.sql.Connection;

public abstract class AbstractDao {
    final ThreadLocal<Connection> connection=new ThreadLocal<Connection>();
}
