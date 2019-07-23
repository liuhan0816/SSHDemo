package com.arthur.web.core.service;

import com.arthur.web.core.hibernate.TransactionUtil;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionStatus;


/**
 * @ClassName BaseService
 * @Description TODO
 * @Author liuhan
 * @Date 2019/7/4 16:31
 * @Version 1.0
**/
@Service
public abstract class BaseService {
    private HibernateTransactionManager htm;

    public HibernateTransactionManager getHtm() {
        return htm;
    }

    public void setHtm(HibernateTransactionManager htm) {
        this.htm = htm;
    }

    /**
     * 提交
     */
    protected void commit() {
        TransactionStatus ts = TransactionUtil.getTransaction();
        if (ts != null && !ts.isCompleted()) {
            htm.commit(ts);
        }
    }

    /**
     * 回滚
     */
    protected void rollback() {
        TransactionStatus ts = TransactionUtil.getTransaction();
        if (ts != null && !ts.isCompleted()) {
            htm.rollback(ts);
        }
    }
}
