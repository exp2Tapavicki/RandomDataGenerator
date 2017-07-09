package com.project.dao;

import com.project.model.RandomDataGeneration;
import com.project.model.User;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("randomDataGenerationDao")
public class RandomDataGenerationDaoImpl extends AbstractDao<Integer, RandomDataGeneration>
        implements DaoInterface<RandomDataGeneration> {
    @Override
    public RandomDataGeneration findByIdOrdinalNumber(int id, int ordinalNumber) {
        return getByKey(id, ordinalNumber);
    }

    @Override
    public List<RandomDataGeneration> findAll() {
        Criteria criteria = createEntityCriteria();
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        return criteria.list();
    }

    @Override
    public void save(RandomDataGeneration randomDataGeneration) {
        getSession().save(randomDataGeneration);
    }

    @Override
    public void delete(Integer id, Integer ordinalNumber) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("id", id));
        crit.add(Restrictions.eq("ordinalNumber", ordinalNumber));
        RandomDataGeneration randomDataGeneration = (RandomDataGeneration) crit.uniqueResult();
        delete(randomDataGeneration);
    }

    @Override
    public List<RandomDataGeneration> search(RandomDataGeneration object) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("id", object.getRandomDataGenerationModel().getId()));
        return crit.list();
    }

}
