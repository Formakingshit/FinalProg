package service.isolation.wrapper;

import dao.connection.ITransactionManager;
import dao.connection.TransactionManagerFactory;

@FunctionalInterface
public interface ITransactionalWrapper<T>{
    default T execute() {
        try (ITransactionManager daoManager = TransactionManagerFactory
                .getInstance().createTransactionManager()) {
            daoManager.beginTransaction();
            T result =processMethod(daoManager);
            daoManager.commitTransaction();
            return result;
        }
    }

    T processMethod(ITransactionManager daoManager);
}
