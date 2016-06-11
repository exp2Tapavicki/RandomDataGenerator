package com.project.dao;

import java.util.List;


public interface DaoInterface<T> {

    T findById(int id);

    void save(T applicant);

    void delete(Integer id);

    List<T> findAll();

    List<T> search(T object);

}

