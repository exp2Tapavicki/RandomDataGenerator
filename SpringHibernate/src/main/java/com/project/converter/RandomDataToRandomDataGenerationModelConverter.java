package com.project.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.project.model.RandomDataGenerationModel;
import com.project.service.ServiceInterface;

/**
 * A converter class used in views to map id's to actual vacancies objects.
 */
@Component
public class RandomDataToRandomDataGenerationModelConverter implements Converter<Object, RandomDataGenerationModel> {

    @Autowired
    ServiceInterface<RandomDataGenerationModel> randomDataGenModelService;
    
    /**
     * Gets RandomDataGenerationModel by Id
     *
     * @see org.springframework.core.convert.converter.Converter#convert(java.lang.Object)
     */
    public RandomDataGenerationModel convert(Object element) {
        RandomDataGenerationModel randomDataGenerationModel;
        if (element instanceof RandomDataGenerationModel) {
            return (RandomDataGenerationModel) element;
        } else {
            Integer id = Integer.parseInt((String) element);
            randomDataGenerationModel = randomDataGenModelService.findByIdOrdinalNumber(id, -1);
        }
        return randomDataGenerationModel;
    }
}