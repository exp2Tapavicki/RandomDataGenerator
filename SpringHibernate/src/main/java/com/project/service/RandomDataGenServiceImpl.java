package com.project.service;

import com.project.dao.DaoInterface;
import com.project.model.RandomDataGeneration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service("randomDataGenService")
@Transactional
public class RandomDataGenServiceImpl implements ServiceInterface<RandomDataGeneration> {

    @Autowired
    private DaoInterface<RandomDataGeneration> dao;

    @Override
    public RandomDataGeneration findById(int id) {
        return dao.findById(id);
    }

    @Override
    public void save(RandomDataGeneration object) {
        dao.save(object);
    }

    @Override
    public void update(RandomDataGeneration object) {
        RandomDataGeneration entity = dao.findById(object.getId());
        if (entity != null) {
            entity.setBasicClassConstants(object.getBasicClassConstants());
            entity.setObjEnum(object.getObjEnum());
            entity.setObjMax(object.getObjMax());
            entity.setObjMin(object.getObjMin());
            entity.setObjPrecision(object.getObjPrecision());
            entity.setbAllowNulls(object.isbAllowNulls());
            entity.setPropertyName(object.getPropertyName());
        }
    }

    @Override
    public void delete(Integer id) {
        dao.delete(id);
    }

    @Override
    public List<RandomDataGeneration> findAll() {
        return dao.findAll();
    }

    @Override
    public List<RandomDataGeneration> search(RandomDataGeneration object) {
        return null;
    }
}
