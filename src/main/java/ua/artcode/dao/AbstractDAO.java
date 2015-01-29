package ua.artcode.dao;

import java.util.List;

/**
 * For Hibernate realization in future
 */
public abstract class AbstractDAO<T> implements DAO<T> {

    Class<T> clType;

    protected AbstractDAO(Class<T> clType) {
        this.clType = clType;
    }

    @Override
    public void create(T t) {

    }

    @Override
    public T getById(Long id) {
        return null;
    }

    @Override
    public T delete() {
        return null;
    }

    @Override
    public boolean update(T t) {
        return false;
    }

    @Override
    public List<T> all() {
        return null;
    }
}
