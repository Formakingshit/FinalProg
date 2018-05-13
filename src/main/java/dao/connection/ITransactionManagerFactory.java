package dao.connection;


@FunctionalInterface
public interface ITransactionManagerFactory {
    /**
     * Method to get new instance of TransactionManager
     * @return TransactionManager
     */
    ITransactionManager createTransactionManager();
}
