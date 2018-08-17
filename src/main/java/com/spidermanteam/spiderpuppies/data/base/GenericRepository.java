package com.spidermanteam.spiderpuppies.data.base;

import java.util.List;

public interface GenericRepository<T> {
    List<T> listAll();


    T findById(int id);

    void create(T restaurant);

    void update(int id, T restaurant);

    void delete(int id);
}
