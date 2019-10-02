package org.auk.data;

import java.util.List;

interface DataSourceInterface<T> {

    T getById(int id);

    List<T> getAll();

    void add(T T);

    void remove(int id);
}
