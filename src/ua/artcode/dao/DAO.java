package ua.artcode.dao;

import java.util.List;

/**
 * Created by admin on 01.12.2014.
 */
public interface DAO <T> {

    public void create(T t);

    T getById(Long id);

    T delete();

    boolean update(T t);

    List<T> all();

}
