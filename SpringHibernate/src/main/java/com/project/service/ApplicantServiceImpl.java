package com.project.service;

import com.project.dao.DaoInterface;
import com.project.model.Applicant;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;

import javax.validation.Validator;

@Service("applicantService")
@Transactional
public class ApplicantServiceImpl implements ServiceInterface<Applicant> {

    @Autowired
    private DaoInterface<Applicant> dao;

    @Autowired
    private Validator validator;

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Applicant findByIdOrdinalNumber(int id, int ordinalNumber) {
        return dao.findByIdOrdinalNumber(id, ordinalNumber);
    }

    @Override
    public void save(Applicant applicant) {
        dao.save(applicant);
    }

    @Override
    public void update(Applicant applicant) {
        Applicant entity = dao.findByIdOrdinalNumber(applicant.getId(), -1);
        if (entity != null) {
            entity.setFirstName(applicant.getFirstName());
            entity.setLastName(applicant.getLastName());
            entity.setEmail(applicant.getEmail());
            entity.setHiredAfter(applicant.getHiredAfter());
            entity.setJmbg(applicant.getJmbg());
            entity.setPhone(applicant.getPhone());
            entity.setRemark(applicant.getRemark());
            if (applicant.getHiredAfter() && applicant.getUpdateTime() == null) {
                entity.setUpdateTime(new Date(System.currentTimeMillis()));
            } else if (!applicant.getHiredAfter()) {
                entity.setUpdateTime(null);
            } else {
                entity.setUpdateTime(applicant.getUpdateTime());
            }
            entity.setYearOfBirth(applicant.getYearOfBirth());
            entity.setVacancies(applicant.getVacancies());
        }
    }

    @Override
    public void delete(Integer id, Integer ordinalNumber) {
        dao.delete(id, -1);
    }

    @Override
    public List<Applicant> findAll() {
        return dao.findAll();
    }

    @Override
    public List<Applicant> search(Applicant applicant) {
        return dao.search(applicant);
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
