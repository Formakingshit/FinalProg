package service.isolation.wrapper;

import dao.connection.ITransactionManager;

import dao.connection.TransactionManagerFactory;

@FunctionalInterface
public interface INoTransactionalVoidWrapper {
    default void execute(){
        try (ITransactionManager daoManager = TransactionManagerFactory
                .getInstance().createTransactionManager()) {
            processMethod(daoManager);
        }
    }

    void processMethod(ITransactionManager transactionManager);
}
