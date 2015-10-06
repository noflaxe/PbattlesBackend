package com.pbattles.db.dao;

import java.util.List;

/**
 * Created by Nazar_Sheremeta on 4/2/14.
 */
public interface CRUDDao<T> {
    public void insert(T entity);
    public T findById(Object id);
    public List<T> findAll();
    public void update(T entity);
    public void remove(T entity);
}
