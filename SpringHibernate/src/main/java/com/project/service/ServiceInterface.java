package com.project.service;

import java.util.List;


public interface ServiceInterface<T> {

    T findById(int id);

    void save(T object);

    void update(T object);

    void delete(Integer id);

    List<T> findAll();

    List<T> search(T object);

}