package com.project.dao;

import com.project.model.Applicant;
import com.project.model.Vacancy;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository("applicantDao")
public class ApplicantDaoImpl extends AbstractDao<Integer, Applicant> implements DaoInterface<Applicant> {

    @Override
    public Applicant findByIdOrdinalNumber(int id, int ordinalNumber) {
        Applicant applicant = getByKey(id, -1);
        if (applicant != null) {
            Hibernate.initialize(applicant.getVacancies());
        }
        return applicant;
    }

    @Override
    public List<Applicant> findAll() {
        Criteria criteria = createEntityCriteria().addOrder(Order.asc("firstName"));
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);// To avoid
                                                                     // duplicates.
        List<Applicant> applicants = criteria.list();
        for (Applicant applicant : applicants) {
            Hibernate.initialize(applicant.getVacancies());
        }
        return applicants;
    }

    @Override
    public void save(Applicant applicant) {
        persist(applicant);
    }

    @Override
    public void delete(Integer id, Integer ordinalNumber) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("id", id));
        Applicant applicant = (Applicant) crit.uniqueResult();
        delete(applicant);
    }

    @Override
    public List<Applicant> search(Applicant applicant) {
        Criteria crit = createEntityCriteria();
        if (applicant != null) {
            if (applicant.getFirstName() != null && !applicant.getFirstName().equals("")) {
                crit.add(Restrictions.eq("firstName", applicant.getFirstName()));
            }
            if (applicant.getFirstName() != null && !applicant.getFirstName().equals("")) {
                crit.add(Restrictions.eq("firstName", applicant.getFirstName()));
            }
            if (applicant.getLastName() != null && !applicant.getLastName().equals("")) {
                crit.add(Restrictions.eq("lastName", applicant.getLastName()));
            }
            if (applicant.getJmbg() != null && !applicant.getJmbg().equals("")) {
                crit.add(Restrictions.eq("jmbg", applicant.getJmbg()));
            }
            if (applicant.getVacancies() != null && !applicant.getVacancies().isEmpty()) {
                ArrayList<Integer> alKeys = applicant.getVacancies().stream().map(Vacancy::getId)
                        .collect(Collectors.toCollection(ArrayList::new));
                crit.createAlias("vacancies", "vacanciesAlias");
                crit.add(Restrictions.in("vacanciesAlias.id", alKeys));
            }
        }
        return crit.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
    }
}
