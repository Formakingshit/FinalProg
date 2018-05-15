package service.isolation;

import service.isolation.wrapper.*;

abstract class GenericService {
    GenericService(){}

    <T> T executeInNonTransactionalWrapper(INoTransactionalWrapper<T> wrapper){
        return wrapper.execute();
    }

    <T> T executeInTransactionalWrapper(ITransactionalWrapper<T> wrapper){
        return wrapper.execute();
    }

    <T> T executeInRepeatableReadWrapper(IRepeatableReadTransactionWrapper<T> wrapper){
        return wrapper.execute();
    }

    void executeInRepeatableReadVoidWrapper(IRepeatableReadTransactionVoidWrapper wrapper){
        wrapper.execute();
    }

    void executeInTransactionalVoidWrapper(ITransactionalVoidWrapper wrapper){
        wrapper.execute();
    }

    void executeInNonTransactionalVoidWrapper(INoTransactionalVoidWrapper wrapper){
        wrapper.execute();
    }
}
