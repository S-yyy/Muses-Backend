package com.mu.muses.dao;

import org.springframework.data.domain.Example;

public interface NewDao<T> {
    <S extends T> Iterable<S> findAll(Example<S> example);
}
