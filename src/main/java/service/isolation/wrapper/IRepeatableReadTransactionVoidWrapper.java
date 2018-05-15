package service.isolation.wrapper;

import dao.connection.ITransactionManager;
import dao.connection.TransactionManagerFactory;

@FunctionalInterface
public interface IRepeatableReadTransactionVoidWrapper {
    default void execute() {
        try (ITransactionManager daoManager = TransactionManagerFactory
                .getInstance().createTransactionManager()) {

            daoManager.beginRepeatableReadTransaction();
            processMethod(daoManager);
        }
    }

    void processMethod(ITransactionManager transactionManager);
}
