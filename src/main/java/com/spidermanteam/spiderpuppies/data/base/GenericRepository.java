package com.spidermanteam.spiderpuppies.data.base;

import java.util.List;

public interface GenericRepository<T> {

  void create(T model);

  T findById(int id);

  List<T> listAll();

  void update(T model);

  void delete(int id);
}

