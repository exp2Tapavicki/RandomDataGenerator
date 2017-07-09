package com.project.dao;

import com.project.model.RandomDataGenerationModel;
import com.project.model.UserProfile;
import com.project.model.UserProfileType;
import com.project.model.Vacancy;

import org.hibernate.Criteria;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Repository("randomDataGenerationModelDao")
public class RandomDataGenerationModelDaoImpl extends AbstractDao<Integer, RandomDataGenerationModel>
        implements DaoInterface<RandomDataGenerationModel> {
    @Override
    public RandomDataGenerationModel findByIdOrdinalNumber(int id, int ordinalNumber) {
        return getByKey(id, -1);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<RandomDataGenerationModel> findAll() {
        Criteria criteria = createEntityCriteria();
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        return criteria.list();
    }

    @Override
    public void save(RandomDataGenerationModel randomDataGenerationModel) {
        getSession().save(randomDataGenerationModel);
    }

    @Override
    public void delete(Integer id, Integer ordinalNumber) {
        Criteria crit = createEntityCriteria();
        crit.add(Restrictions.eq("id", id));
        RandomDataGenerationModel randomDataGenerationModel = (RandomDataGenerationModel) crit.uniqueResult();
        delete(randomDataGenerationModel);
    }

    @Override
    public List<RandomDataGenerationModel> search(RandomDataGenerationModel object) {
        Criteria criteria = createEntityCriteria();
        Disjunction disjunction =Restrictions.disjunction();
        criteria.createCriteria("user","userAlias");
        if (object.getUser() != null && object.getUser().getUserProfiles() != null && object.getUser().getUserProfiles().size() > 0){
            criteria.createCriteria("userAlias.userProfiles","userProfilesAlias");
            for (UserProfile userProfile : object.getUser().getUserProfiles()) {
                  Criterion criterion = Restrictions.eq("userProfilesAlias.type", userProfile.getType());
                  disjunction.add(Restrictions.eq("userProfilesAlias.type", userProfile.getType()));                    
            }
            criteria.add(disjunction);
        } else if (object.getUser().getSsoId() != null && !object.getUser().getSsoId().isEmpty()){
            criteria.add(Restrictions.eq("userAlias.ssoId", object.getUser().getSsoId()));
        }
        criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
        return criteria.list();
    }

}
