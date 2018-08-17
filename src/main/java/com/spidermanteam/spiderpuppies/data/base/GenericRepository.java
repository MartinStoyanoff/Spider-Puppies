package com.spidermanteam.spiderpuppies.data.base;

import java.util.List;

public interface GenericRepository<T> {

    List<T> listAll();

    T findById(int id);

    void create(T model);

    void update(T model);

    void delete(int id);
}

