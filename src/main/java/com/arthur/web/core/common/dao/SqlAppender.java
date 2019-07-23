package com.arthur.web.core.common.dao;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName SqlAppender
 * @Description 动态拼装sql或hql语句，使用占位符或命名参数
 * 两种方式只能选择一种来拼装，要么全是占位符，要么全用命名参数
 * @Author liuhan
 * @Date 2019/7/17 11:38
 * @Version 1.0
 **/
public class SqlAppender {
    private StringBuffer psSql;

    private List paramValues;

    //private List namedParams;

    public void addParam(Object paraValue){
        paramValues.add(paraValue);
    }

    public SqlAppender(String hql) {
        this.psSql = new StringBuffer(hql);
        this.paramValues = new ArrayList();

    }

    public SqlAppender(SqlAppender sqlAppender) {
        this.psSql = new StringBuffer(sqlAppender.psSql.toString());
        this.paramValues = new ArrayList(sqlAppender.paramValues);

    }

    public SqlAppender append(String cond,Object paraValue) {
        this.psSql.append(cond);
        this.paramValues.add(paraValue);
        return this;
    }

    public SqlAppender append(String cond) {
        this.psSql.append(cond);
        return this;
    }


    public SqlAppender append(String cond,char paraValue) {
        return append(cond,new Character(paraValue));
    }

    public SqlAppender append(String cond,int paraValue) {
        return append(cond,new Integer(paraValue));
    }

    public SqlAppender append(String cond,long paraValue) {
        return append(cond,new Long(paraValue));
    }

    public SqlAppender append(String cond,float paraValue) {
        return append(cond,new Float(paraValue));
    }

    public SqlAppender append(String cond,double paraValue) {
        return append(cond,new Double(paraValue));
    }

    public String getPsSql() {
        return this.psSql.toString();
    }

    public Object[] getParamsValues() {
        return this.paramValues.toArray();
    }


    public void setPsSql(String sql) {
        this.psSql = new StringBuffer(sql);
    }
}
