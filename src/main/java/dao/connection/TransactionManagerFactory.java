package dao.connection;


import dao.IDaoFactory;
import dao.connection.jdbc.JdbcPooledDataSource;
import dao.exception.DaoException;
import dao.jdbc.DaoFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class TransactionManagerFactory implements ITransactionManagerFactory {
    private static final String LOG_MESSAGE_SQL_CONNECTION_CAN_NOT_BE_NULL =
            "SQL connection can not be null. Datasource returned no connection.";

    private DataSource dataSource= JdbcPooledDataSource.getInstance();
    private IDaoFactory daoFactory= DaoFactory.getInstance();
    private TransactionManagerFactory(){}

    /*thread safe singleton*/
    private static class LazyInstanceHolder{
        private static final TransactionManagerFactory INSTANCE=new TransactionManagerFactory();
    }

    public static TransactionManagerFactory getInstance(){
        return LazyInstanceHolder.INSTANCE;
    }

    @Override
    public TransactionManager createTransactionManager() {
        Connection connection;
        try {
            connection=dataSource.getConnection();

        } catch (SQLException e) {
            throw new DaoException(e);
        }

        if(connection==null){
            throw new DaoException()
                    .addLogMessage(LOG_MESSAGE_SQL_CONNECTION_CAN_NOT_BE_NULL);
        }

        return new TransactionManager(connection,daoFactory);
    }
}
