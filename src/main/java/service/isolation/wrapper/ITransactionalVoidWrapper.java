package service.isolation.wrapper;

import dao.connection.ITransactionManager;
import dao.connection.TransactionManagerFactory;

@FunctionalInterface
public interface ITransactionalVoidWrapper {
    default void execute() {
        try (ITransactionManager daoManager = TransactionManagerFactory
                .getInstance().createTransactionManager()) {
            daoManager.beginTransaction();
            processMethod(daoManager);
            daoManager.commitTransaction();

        }
    }

    void processMethod(ITransactionManager daoManager);
}
