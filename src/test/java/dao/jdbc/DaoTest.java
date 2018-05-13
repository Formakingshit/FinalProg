package dao.jdbc;

import dao.wrapper.NoTransactionalWrapper;
import dao.wrapper.RollbackReadCommitedVoidTransaction;
import dao.wrapper.RollbackRepeatableReadVoidTransaction;


public class DaoTest {
    void executeInReadCommitedVoidRollbackWrapper(RollbackReadCommitedVoidTransaction wrapper){
        wrapper.execute();
    }

    void executeInRepeatableReadVoidRollbackWrapper(RollbackRepeatableReadVoidTransaction wrapper){
        wrapper.execute();
    }

    void executeInNoTransactionalWrapper(NoTransactionalWrapper wrapper){
        wrapper.execute();
    }
}
