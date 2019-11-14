package org.auk.entity;

import javax.persistence.NoResultException;

@FunctionalInterface
public interface DaoRetriever<T> {
    T retrieve() throws NoResultException;
}
