package com.arthur.web.core.hibernate;

import org.springframework.transaction.TransactionStatus;

/**
 * @ClassName TransactionUtil
 * @Description TODO
 * @Author liuhan
 * @Date 2019/7/4 16:30
 * @Version 1.0
 **/
public class TransactionUtil {
    public  static ThreadLocal<TransactionStatus> transactionStatus = new ThreadLocal<TransactionStatus>();

    public static void setTransaction(TransactionStatus status){
        transactionStatus.set(status);
    }

    public static TransactionStatus getTransaction(){
        return transactionStatus.get();
    }

    public static void unbindTransaction(){
        transactionStatus.set(null);
    }
}
