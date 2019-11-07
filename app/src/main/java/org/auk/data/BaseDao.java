package org.auk.data;

import java.util.List;

public interface BaseDao<T> {

    void save(T t);

    void update(T t);

    void delete(T t);

    List<T> getAll();
}
