package dao.wrapper;

import dao.connection.ITransactionManager;
import dao.connection.TransactionManager;
import dao.connection.TransactionManagerFactory;

@FunctionalInterface
public interface RollbackReadCommitedVoidTransaction {
    default void execute(){
        ITransactionManager daoManager = TransactionManagerFactory
                .getInstance().createTransactionManager();
        daoManager.beginTransaction();
        processMethod((TransactionManager) daoManager);
        daoManager.rollbackTransaction();
    }

    void processMethod(ITransactionManager transactionManager);
}
