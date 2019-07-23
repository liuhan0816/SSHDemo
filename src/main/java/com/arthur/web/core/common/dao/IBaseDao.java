package com.arthur.web.core.common.dao;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName IBaseDao
 * @Description dao基础实现接口
 * @Author liuhan
 * @Date 2019/7/3 10:58
 * @Version 1.0
**/
public interface IBaseDao<T> {
    public void save(T t);
    public void delete(T t);
    public void update(T t);
    public T load(Class<T> entityClass, Serializable id);
    public List<T> executeQuery(String hql, Object[] args);
}
