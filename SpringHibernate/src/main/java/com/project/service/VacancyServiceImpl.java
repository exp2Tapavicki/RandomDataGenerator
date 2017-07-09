package com.project.service;

import com.project.dao.DaoInterface;
import com.project.model.Vacancy;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import javax.validation.Validator;

@Service("vacancyService")
@Transactional
public class VacancyServiceImpl implements ServiceInterface<Vacancy> {

    @Autowired
    DaoInterface<Vacancy> dao;

    @Autowired
    private Validator validator;

    @Autowired
    private SessionFactory sessionFactory;

    public Vacancy findByIdOrdinalNumber(int id, int ordinalNumber) {
        return dao.findByIdOrdinalNumber(id, -1);
    }

    public void save(Vacancy vacancy) {
        if (dao.search(vacancy) == null) {
            dao.save(vacancy);
        }
    }

    @Override
    public void update(Vacancy vacancy) {
        Vacancy entity = dao.findByIdOrdinalNumber(vacancy.getId(), -1);
        if (entity != null) {
            entity.setVacancyCode(vacancy.getVacancyCode());
            entity.setVacancyName(vacancy.getVacancyName());
        }
    }

    @Override
    public void delete(Integer id, Integer ordinalNumber) {
        dao.delete(id, -1);
    }

    @Override
    public List<Vacancy> findAll() {
        return dao.findAll();
    }

    @Override
    public List<Vacancy> search(Vacancy object) {
        return dao.search(object);
    }

    @Override
    public Validator getValidator() {
        return validator;
    }

    @Override
    public Session getSession() {
        return sessionFactory.openSession();
    }
}
