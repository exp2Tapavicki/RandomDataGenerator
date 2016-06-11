package com.project.dao;

import com.project.model.RandomDataGeneration;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("randomDataGenerationDao")
public class RandomDataGenerationDaoImpl extends AbstractDao<Integer, RandomDataGeneration>
        implements DaoInterface<RandomDataGeneration> {
    @Override
    public RandomDataGeneration findById(int id) {
        return getByKey(id);
    }

    @Override
    public List<RandomDataGeneration> findAll() {
        Criteria criteria = createEntityCriteria();
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        return criteria.list();
    }

    @Override
    public void save(RandomDataGeneration randomDataGeneration) {
        persist(randomDataGeneration);
    }

    @Override
    public void delete(Integer id) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("id", id));
        RandomDataGeneration randomDataGeneration = (RandomDataGeneration) crit.uniqueResult();
        delete(randomDataGeneration);
    }

    @Override
    public List<RandomDataGeneration> search(RandomDataGeneration object) {
        return null;
    }

}
