package com.project.dao;

import java.util.List;


public interface DaoInterface<T> {

    T findByIdOrdinalNumber(int id, int ordinalNumber);

    void save(T applicant);

    void delete(Integer id, Integer ordinalNumber);

    List<T> findAll();

    List<T> search(T object);

}

