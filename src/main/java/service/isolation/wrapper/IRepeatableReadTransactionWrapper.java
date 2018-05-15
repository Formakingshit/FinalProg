package service.isolation.wrapper;

import dao.connection.ITransactionManager;
import dao.connection.TransactionManagerFactory;

@FunctionalInterface
public interface IRepeatableReadTransactionWrapper<T> {
    default T execute() {
        try (ITransactionManager daoManager = TransactionManagerFactory
                .getInstance().createTransactionManager()) {
            daoManager.beginRepeatableReadTransaction();
            T result = processMethod(daoManager);
            daoManager.commitTransaction();
            return result;
        }
    }

    T processMethod(ITransactionManager transactionManager);
}
