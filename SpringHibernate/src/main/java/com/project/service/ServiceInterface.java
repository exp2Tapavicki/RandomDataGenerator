package com.project.service;

import java.util.List;

import javax.validation.Validator;

import org.hibernate.Session;

import com.project.model.RandomDataGeneration;

public interface ServiceInterface<T> {

    T findByIdOrdinalNumber(int id, int ordinalNumber);

    void save(T object);

    void update(T object);

    void delete(Integer id, Integer ordinalNumber);

    List<T> findAll();

    List<T> search(T object);

    Validator getValidator();

    Session getSession();

}