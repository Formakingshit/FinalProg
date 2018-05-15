package service.isolation.wrapper;

import dao.connection.ITransactionManager;
import dao.connection.TransactionManagerFactory;

@FunctionalInterface
public interface INoTransactionalWrapper<T>{

    default T execute(){
        try (ITransactionManager daoManager = TransactionManagerFactory
                .getInstance().createTransactionManager()) {
            return processMethod(daoManager);
        }
    }

    T processMethod(ITransactionManager transactionManager);
}
