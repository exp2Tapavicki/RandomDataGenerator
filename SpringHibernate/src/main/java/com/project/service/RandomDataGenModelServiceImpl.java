package com.project.service;

import com.project.dao.DaoInterface;
import com.project.model.RandomDataGenerationModel;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import javax.validation.Validator;

@Service("randomDataGenModelService")
@Transactional
public class RandomDataGenModelServiceImpl implements ServiceInterface<RandomDataGenerationModel> {

    @Autowired
    private DaoInterface<RandomDataGenerationModel> dao;

    @Autowired
    private Validator validator;

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public RandomDataGenerationModel findByIdOrdinalNumber(int id, int ordinalNumber) {
        return dao.findByIdOrdinalNumber(id, -1);
    }

    @Override
    public void save(RandomDataGenerationModel object) {
        dao.save(object);
    }

    @Override
    public void update(RandomDataGenerationModel object) {
        RandomDataGenerationModel entity = dao.findByIdOrdinalNumber(object.getId(), -1);
        if (entity != null) {
            entity.setId(object.getId());
            entity.setModelName(object.getModelName());
            entity.setUser(object.getUser());
            entity.setRandomDataGenerations(object.getRandomDataGenerations());
        }
    }

    @Override
    public void delete(Integer id, Integer ordinalNumber) {
        dao.delete(id, -1);
    }

    @Override
    public List<RandomDataGenerationModel> findAll() {
        return dao.findAll();
    }

    @Override
    public List<RandomDataGenerationModel> search(RandomDataGenerationModel object) {
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
