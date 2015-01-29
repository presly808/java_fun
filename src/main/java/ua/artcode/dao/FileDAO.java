package ua.artcode.dao;

import java.util.Collection;
import java.util.List;

/**
 * Created by admin on 01.12.2014.
 */
public interface FileDAO<T> {

    void saveAll(List<T> values);

    List<T> loadAll();


}
