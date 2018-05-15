package dao.wrapper;


import dao.connection.ITransactionManager;
import dao.connection.TransactionManagerFactory;


@FunctionalInterface
public interface NoTransactionalWrapper {
    default void execute(){
        ITransactionManager daoManager = TransactionManagerFactory
                .getInstance().createTransactionManager();

        processMethod(daoManager);

    }

    void processMethod(ITransactionManager transactionManager);
}
