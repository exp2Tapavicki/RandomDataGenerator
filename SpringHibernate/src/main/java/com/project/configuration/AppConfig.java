package com.project.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.format.FormatterRegistry;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import com.project.converter.RandomDataToRandomDataGenerationModelConverter;
import com.project.converter.RoleToUserProfileConverter;
import com.project.converter.VacancyToApplicantConverter;


@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "com.project")
public class AppConfig extends WebMvcConfigurerAdapter {

    @Autowired
    VacancyToApplicantConverter vacancyToApplicantConverter;
    
	@Autowired
	RoleToUserProfileConverter roleToUserProfileConverter;
	
   @Autowired
   RandomDataToRandomDataGenerationModelConverter randomDataToRandomDataGenerationModelConverter;
	
    /**
     * Configure Converter to be used.
     * In our example, we need a converter to convert string values[vacancies] to Vacancy in newapplication.jsp
     */
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(vacancyToApplicantConverter);
        registry.addConverter(roleToUserProfileConverter);
        registry.addConverter(randomDataToRandomDataGenerationModelConverter);
    }

    /**
     * Configure ViewResolvers to deliver preferred views.
     */
    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {

        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");
        viewResolver.setOrder(0);
        registry.viewResolver(viewResolver);
        
        viewResolver = new InternalResourceViewResolver();
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/WEB-INF/views/rdg/");
        viewResolver.setSuffix(".jsp");
        viewResolver.setOrder(1);
        
        viewResolver = new InternalResourceViewResolver();
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/WEB-INF/views/admin/");
        viewResolver.setSuffix(".jsp");
        viewResolver.setOrder(2);
        
        viewResolver = new InternalResourceViewResolver();
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/WEB-INF/views/user/");
        viewResolver.setSuffix(".jsp");
        viewResolver.setOrder(3);
        
        viewResolver = new InternalResourceViewResolver();
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/WEB-INF/views/dba/");
        viewResolver.setSuffix(".jsp");
        viewResolver.setOrder(4);
        registry.viewResolver(viewResolver);
    }

    /**
     * Configure ResourceHandlers to serve static resources like CSS/ Javascript etc...
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**/**/**/**/**/**").addResourceLocations("/static/");
    }

    /**
     * Configure MessageSource to lookup any validation/error message in internationalized property files
     */
    @Bean
    public MessageSource messageSource() {
        ResourceBundleMessageSource messageSource = new ResourceBundleMessageSource();
        messageSource.setBasename("messages");
        return messageSource;
    }
    
	@Bean
	public javax.validation.Validator localValidatorFactoryBean() {
	   return new LocalValidatorFactoryBean();
	}

    /**
     * Optional. It's only required when handling '.' in @PathVariables which otherwise ignore everything after last '.' in @PathVaidables argument.
     * It's a known bug in Spring [https://jira.spring.io/browse/SPR-6164], still present in Spring 4.1.7.
     * This is a workaround for this issue.
     */
    @Override
    public void configurePathMatch(PathMatchConfigurer matcher) {
        matcher.setUseRegisteredSuffixPatternMatch(true);
    }
}

