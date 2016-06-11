package com.project.service;

import com.project.dao.DaoInterface;
import com.project.model.Vacancy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service("vacancyService")
@Transactional
public class VacancyServiceImpl implements ServiceInterface<Vacancy> {

    @Autowired
    DaoInterface<Vacancy> dao;

    public Vacancy findById(int id) {
        return dao.findById(id);
    }

    public void save(Vacancy vacancy) {
        if (dao.search(vacancy) == null) {
            dao.save(vacancy);
        }
    }

    @Override
    public void update(Vacancy vacancy) {
        Vacancy entity = dao.findById(vacancy.getId());
        if (entity != null) {
            entity.setVacancyCode(vacancy.getVacancyCode());
            entity.setVacancyName(vacancy.getVacancyName());
        }
    }

    @Override
    public void delete(Integer id) {
        dao.delete(id);
    }

    @Override
    public List<Vacancy> findAll() {
        return dao.findAll();
    }

    @Override
    public List<Vacancy> search(Vacancy object) {
        return search(object);
    }
}
