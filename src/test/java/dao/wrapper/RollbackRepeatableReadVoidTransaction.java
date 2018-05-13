package dao.wrapper;

import dao.connection.ITransactionManager;
import dao.connection.TransactionManagerFactory;

@FunctionalInterface
public interface RollbackRepeatableReadVoidTransaction {
    default void execute(){
        ITransactionManager daoManager = TransactionManagerFactory
                .getInstance().createTransactionManager();
        daoManager.beginRepeatableReadTransaction();
        processMethod(daoManager);
        daoManager.rollbackTransaction();
    }

    void processMethod(ITransactionManager transactionManager);
}
