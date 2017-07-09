package com.project.service;

import com.project.dao.DaoInterface;
import com.project.model.RandomDataGeneration;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import javax.validation.Validator;

@Service("randomDataGenService")
@Transactional
public class RandomDataGenServiceImpl implements ServiceInterface<RandomDataGeneration> {

    @Autowired
    private DaoInterface<RandomDataGeneration> dao;

    @Autowired
    private Validator validator;

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public RandomDataGeneration findByIdOrdinalNumber(int id, int ordinalNumber) {
        return dao.findByIdOrdinalNumber(id, ordinalNumber);
    }

    @Override
    public void save(RandomDataGeneration object) {
        dao.save(object);
    }

    @Override
    public void update(RandomDataGeneration object) {
        RandomDataGeneration entity = dao.findByIdOrdinalNumber(object.getId(), object.getOrdinalNumber());
        if (entity != null) {
            entity.setBasicClassConstants(object.getBasicClassConstants());
            entity.setObjEnum(object.getObjEnum());
            entity.setObjMax(object.getObjMax());
            entity.setObjMin(object.getObjMin());
            entity.setObjPrecision(object.getObjPrecision());
            entity.setbAllowNulls(object.isbAllowNulls());
            entity.setPropertyName(object.getPropertyName());
            entity.setOrdinalNumber(object.getOrdinalNumber());
        }
    }

    @Override
    public void delete(Integer id, Integer ordinalNumber) {
        dao.delete(id, ordinalNumber);
    }

    @Override
    public List<RandomDataGeneration> findAll() {
        return dao.findAll();
    }

    @Override
    public List<RandomDataGeneration> search(RandomDataGeneration object) {
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
