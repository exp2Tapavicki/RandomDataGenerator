package com.project.service;

import com.project.dao.DaoInterface;
import com.project.model.Applicant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;


@Service("applicantService")
@Transactional
public class ApplicantServiceImpl implements ServiceInterface<Applicant> {

    @Autowired
    private DaoInterface<Applicant> dao;

    @Override
    public Applicant findById(int id) {
        return dao.findById(id);
    }

    @Override
    public void save(Applicant applicant) {
        dao.save(applicant);
    }

    @Override
    public void update(Applicant applicant) {
        Applicant entity = dao.findById(applicant.getId());
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
    public void delete(Integer id) {
        dao.delete(id);
    }

    @Override
    public List<Applicant> findAll() {
        return dao.findAll();
    }

    @Override
    public List<Applicant> search(Applicant applicant) {
        return dao.search(applicant);
    }
}
