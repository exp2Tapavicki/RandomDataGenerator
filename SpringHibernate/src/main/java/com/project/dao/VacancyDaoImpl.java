package com.project.dao;

import com.project.model.Vacancy;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;


@Repository("vacancyDao")
public class VacancyDaoImpl extends AbstractDao<Integer, Vacancy> implements DaoInterface<Vacancy> {

    @Override
    public Vacancy findById(int id) {
        return getByKey(id);
    }

    @Override
    public List<Vacancy> search(Vacancy object) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("vacancyCode", object.getVacancyCode()));
        List<Vacancy> list = null;
        Vacancy vacancy = (Vacancy) crit.uniqueResult();
        if (vacancy != null) {
            list = new ArrayList<>();
            list.add(vacancy);
        }
        return list;
    }

    @Override
    public List<Vacancy> findAll() {
        Criteria criteria = createEntityCriteria();
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        return (List<Vacancy>) criteria.list();
    }

    @Override
    public void save(Vacancy vacancy) {
        persist(vacancy);
    }

    @Override
    public void delete(Integer id) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("id", id));
        Vacancy vacancy = (Vacancy) crit.uniqueResult();
        delete(vacancy);
    }
}
