package com.project.service;

import java.util.List;

import javax.validation.Validator;

import org.hibernate.Session;

import com.project.model.RandomDataGeneration;

public interface RandomDataGenModelServiceInterface<T> {

    T findByIdOrdinalNumber(int id, int ordinalNumber);

    void save(T object);

    void update(T object);

    void delete(Integer id);

    List<T> findAll();

    List<T> search(T object);

    Validator getValidator();

    Session getSession();

}