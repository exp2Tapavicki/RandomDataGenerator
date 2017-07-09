package com.project.converter;

import com.project.model.Vacancy;
import com.project.service.ServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * A converter class used in views to map id's to actual vacancies objects.
 */
@Component
public class VacancyToApplicantConverter implements Converter<Object, Vacancy> {

    @Autowired
    ServiceInterface<Vacancy> vacancyService;

    /**
     * Gets Vacancy by Id
     *
     * @see org.springframework.core.convert.converter.Converter#convert(java.lang.Object)
     */
    public Vacancy convert(Object element) {
        Vacancy vacancy;
        if (element instanceof Vacancy) {
            return (Vacancy) element;
        } else {
            Integer id = Integer.parseInt((String) element);
            vacancy = vacancyService.findByIdOrdinalNumber(id, -1);
        }
        return vacancy;
    }
}