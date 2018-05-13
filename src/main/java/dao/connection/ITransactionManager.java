package dao.connection;

import dao.*;

public interface ITransactionManager extends AutoCloseable{
    /**
     * Defines begin of transaction
     */
    void beginTransaction();

    /**
     * Defines begin of transaction with high isolation level
     */
    void beginRepeatableReadTransaction();

    /**
     * Saves transaction.
     */
    void commitTransaction();

    /**
     * rolls back transaction
     */
    void rollbackTransaction();

    /**
     * Closes connection.
     *
     * IMPORTANT.
     * It MUST call ROLLBACK IF TRANSACTION has been begun
     * but was NOT COMMITTED before close method was called, f.e. if any exception was thrown
     */
    @Override
    void close();

    IUserDao getUserDao();
}
