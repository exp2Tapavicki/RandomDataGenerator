package com.project.controller;

import com.project.model.Applicant;
import com.project.model.RandomData;
import com.project.model.RandomDataGeneration;
import com.project.model.Vacancy;
import com.project.service.ServiceInterface;
import com.rdg.boundary.Boundary;
import com.rdg.constants.BasicClassConstants;
import com.rdg.constants.Const;
import com.rdg.util.DateUtil;
import com.rdg.util.RandomDataUtil;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.*;


@Controller
@RequestMapping("/")
@SessionAttributes({"vacancies"})
public class AppController {
    private static Map<String, String> classTypes;

    static {
        classTypes = new LinkedHashMap<>();
        classTypes.put("java.lang.Integer", "java.lang.Integer");
        classTypes.put("java.lang.Long", "java.lang.Long");
        classTypes.put("java.lang.Short", "java.lang.Short");
        classTypes.put("java.lang.Byte", "java.lang.Byte");
        classTypes.put("java.lang.Boolean", "java.lang.Boolean");
        classTypes.put("java.lang.Double", "java.lang.Double");
        classTypes.put("java.lang.Float", "java.lang.Float");
        classTypes.put("java.lang.Character", "java.lang.Character");
        classTypes.put("java.lang.String", "java.lang.String");
        classTypes.put("java.util.Date", "java.util.Date");
        classTypes.put("int", "int");
        classTypes.put("long", "long");
        classTypes.put("short", "short");
        classTypes.put("byte", "byte");
        classTypes.put("boolean", "boolean");
        classTypes.put("float", "float");
        classTypes.put("double", "double");
        classTypes.put("char", "char");
        classTypes.put("java.math.BigDecimal", "java.math.BigDecimal");
        classTypes.put("java.math.BigInteger", "java.math.BigInteger");
        classTypes.put("java.lang.Enum", "java.lang.Enum");
    }


    @Autowired
    ServiceInterface<Applicant> applicantService;

    @Autowired
    ServiceInterface<Vacancy> vacancyService;

    @Autowired
    ServiceInterface<RandomDataGeneration> randomDataGenService;

    @Autowired
    MessageSource messageSource;

    /**
     * This method will list all existing applicants.
     */
    @RequestMapping(value = {"/"}, method = RequestMethod.GET)
    public String home(ModelMap model) {
        return "index";
    }

    /**
     * This method will list all existing applicants.
     */
    @RequestMapping(value = {"/applicantslist"}, method = RequestMethod.GET)
    public String listApplicants(ModelMap model, HttpSession session) {
        List<Applicant> applicants = applicantService.findAll();
        List<Vacancy> vacancies = vacancyService.findAll();
        Applicant applicant = new Applicant();
        model.addAttribute("applicants", applicants);
        model.addAttribute("applicant", applicant);
        model.addAttribute("vacancies", vacancies);
        return "applicantslist";
    }

    /**
     * This method will list all existing random data generation.
     */
    @RequestMapping(value = {"/randomDataGeneration"}, method = RequestMethod.GET)
    public String randomDataGeneration(ModelMap model) {
        List<RandomDataGeneration> randomDataGenerationList = randomDataGenService.findAll();
        RandomData randomData = new RandomData();
        model.addAttribute("randomData", randomData);
        model.addAttribute("randomDataGenerationList", randomDataGenerationList);
        return "randomDataGeneration";
    }

    /**
     * This method will be called on form submission, handling POST request for
     * generating random data in database.
     */
    @RequestMapping(value = {"/randomDataGeneration"}, method = RequestMethod.POST)
    public String randomDataGeneration(@Valid RandomData randomData, BindingResult result, ModelMap model) {
        if (result.hasErrors() || randomData.getNumberOfDataApplicant() == null || randomData.getNumberOfDataApplicant().intValue() <= 0) {
            return "redirect:/randomDataGeneration";
        }
        List<RandomDataGeneration> randomDataGenerationList = randomDataGenService.findAll();
        StringBuilder sbRandomData = new StringBuilder();
        sbRandomData.append("[").append("\n").append("\t");

        for (int i = 0; i < randomData.getNumberOfDataApplicant(); i++) {
            sbRandomData.append("{").append("\n").append("\t");
            for (int j = 0; j < randomDataGenerationList.size(); j++) {
                RandomDataGeneration randomDataGeneration = randomDataGenerationList.get(j);
                HashMap<String, Boundary> hmBoundary = new HashMap<>();
                Boundary boundary = new Boundary();
                boundary.setObjMin(RandomDataUtil.getDinamiclyCastWithValue(randomDataGeneration.getBasicClassConstants(), randomDataGeneration.getObjMin()));
                boundary.setObjMax(RandomDataUtil.getDinamiclyCastWithValue(randomDataGeneration.getBasicClassConstants(), randomDataGeneration.getObjMax()));
                boundary.setObjPrecision(randomDataGeneration.getObjPrecision());
                boundary.setObjEnum(randomDataGeneration.getObjEnum().split(","));
                boundary.setbAllowNulls(randomDataGeneration.isbAllowNulls());
                if (boundary.getObjEnum() != null && boundary.getObjEnum().length > 1) {
                    boundary.setObjMin(0);
                    if (boundary.getObjMax() == null || (((int) boundary.getObjMax()) > boundary.getObjEnum().length - 1)) {
                        boundary.setObjMax(boundary.getObjEnum().length - 1);
                    }
                }
                hmBoundary.put(randomDataGeneration.getBasicClassConstants(), boundary);
                RandomDataUtil.setHmBoundary(hmBoundary);
                sbRandomData.append("\"").append(randomDataGeneration.getPropertyName()).append("\":");
                Object value;
                switch (randomDataGeneration.getBasicClassConstants()) {
                    case BasicClassConstants.sBigDecimal:
                    case BasicClassConstants.sBigInteger:
                    case BasicClassConstants.sBoolean:
                    case BasicClassConstants.sByte:
                    case BasicClassConstants.sDouble:
                    case BasicClassConstants.sFloat:
                    case BasicClassConstants.sInteger:
                    case BasicClassConstants.sLong:
                    case BasicClassConstants.sPrimitiveBoolean:
                    case BasicClassConstants.sPrimitiveByte:
                    case BasicClassConstants.sPrimitiveDouble:
                    case BasicClassConstants.sPrimitiveFloat:
                    case BasicClassConstants.sPrimitiveInt:
                    case BasicClassConstants.sPrimitiveLong:
                    case BasicClassConstants.sPrimitiveShort:
                    case BasicClassConstants.sShort:
                        value = RandomDataUtil.getRandomBasicObjectValue(randomDataGeneration.getBasicClassConstants(), randomDataGeneration.isbAllowNulls());
                        sbRandomData.append(value);
                        break;
                    case BasicClassConstants.sDate:
                    case BasicClassConstants.sCharacter:
                    case BasicClassConstants.sEnum:
                    case BasicClassConstants.sPrimitiveChar:
                        value = RandomDataUtil.getRandomBasicObjectValue(randomDataGeneration.getBasicClassConstants(), randomDataGeneration.isbAllowNulls());
                        if (value != null && !value.toString().trim().equals("")) {
                            sbRandomData.append("\"").append(value).append("\"");
                        } else {
                            sbRandomData.append("\"null\"");
                        }
                        break;
                    case BasicClassConstants.sString:
                        value = RandomDataUtil.getRandomString(randomDataGeneration.getPropertyName(), randomDataGeneration.isbAllowNulls());
                        if (value != null && !value.toString().trim().equals("")) {
                            sbRandomData.append("\"").append(value).append("\"");
                        } else {
                            sbRandomData.append("\"null\"");
                        }
                        break;
                    default:
                        break;
                }
                if (j != randomDataGenerationList.size() - 1) {
                    sbRandomData.append(", \n\t");
                } else {
                    sbRandomData.append("\n\t");
                }
            }
            if (i != randomData.getNumberOfDataApplicant() - 1) {
                sbRandomData.append("}, \n\t");
            } else {
                sbRandomData.append("}\n");
            }

        }
        sbRandomData.append("]");
        model.addAttribute("success", " Data has been generated successfully");
        model.addAttribute("randomDataGenerated", sbRandomData.toString());
        return "registrationsuccess";
    }

    /**
     * This method will provide the medium to add a new Random Data Generation.
     */
    @RequestMapping(value = {"/newRandomDataGeneration"}, method = RequestMethod.GET)
    public String newRandomDataGeneration(ModelMap model) {
        RandomDataGeneration randomDataGeneration = new RandomDataGeneration();
        model.addAttribute("randomDataGeneration", randomDataGeneration);
        model.put("classTypes", classTypes);
        model.addAttribute("edit", false);
        return "addRandomDataGeneration";
    }

    /**
     * This method will be called on form submission, handling POST request for
     * saving Random Data Generation in database.
     */
    @RequestMapping(value = {"/newRandomDataGeneration"}, method = RequestMethod.POST)
    public String newRandomDataGeneration(@Valid RandomDataGeneration randomDataGeneration, BindingResult result, ModelMap model) {
        if (result.hasErrors()) {
            model.put("classTypes", classTypes);
            return "addRandomDataGeneration";
        }
        randomDataGenService.save(randomDataGeneration);
        model.addAttribute("success", "Field added successfully");
        return "registrationsuccess";
    }

    /**
     * This method will provide the medium to update an existing Random Data Genration.
     */
    @RequestMapping(value = {"/edit-randomDataGeneration-{id}"}, method = RequestMethod.GET)
    public String editRandomDataGeneration(@PathVariable Integer id, ModelMap model) {
        RandomDataGeneration randomDataGeneration = randomDataGenService.findById(id);
        model.addAttribute("randomDataGeneration", randomDataGeneration);
        model.addAttribute("edit", true);
        return "addRandomDataGeneration";
    }

    /**
     * This method will be called on form submission, handling POST request for
     * updating Random Data Generation in database.
     */
    @RequestMapping(value = {"/edit-randomDataGeneration-{id}"}, method = RequestMethod.POST)
    public String updateRandomDataGeneration(@Valid RandomDataGeneration randomDataGeneration, BindingResult result, ModelMap model, @PathVariable Integer id) {
        if (result.hasErrors()) {
            return "addRandomDataGeneration";
        }
        randomDataGenService.update(randomDataGeneration);
        model.addAttribute("success", "Field updated successfully");
        return "registrationsuccess";
    }


    /**
     * This method will delete an Random Data Generation by it's id.
     */
    @RequestMapping(value = {"/delete-randomDataGeneration-{id}"}, method = RequestMethod.GET)
    public String deleteRandomDataGeneration(@PathVariable Integer id) {
        randomDataGenService.delete(id);
        return "redirect:/randomDataGeneration";
    }

    /**
     * This method will list all existing applicants.
     */
    @RequestMapping(value = {"/applicantslist"}, method = RequestMethod.POST)
    public String listApplicants(@Valid Applicant applicant, BindingResult result, ModelMap model) {
        List<Applicant> applicants = applicantService.search(applicant);
        model.addAttribute("applicants", applicants);
        model.addAttribute("applicant", applicant);
        return "applicantslist";
    }


    /**
     * This method will provide the medium to add a new applicants.
     */
    @RequestMapping(value = {"/newapplicant"}, method = RequestMethod.GET)
    public String newApplicants(ModelMap model) {
        Applicant applicant = new Applicant();
        model.addAttribute("applicant", applicant);
        model.addAttribute("edit", false);
        return "addapplicant";
    }

    /**
     * This method will be called on form submission, handling POST request for
     * saving applicants in database. It also validates the user input
     */
    @RequestMapping(value = {"/newapplicant"}, method = RequestMethod.POST)
    public String saveApplicants(@Valid Applicant applicant, BindingResult result, ModelMap model) {
        if (result.hasErrors()) {
            return "addapplicant";
        }
        applicantService.save(applicant);
        model.addAttribute("success", "Applicant " + applicant.getFirstName() + " " + applicant.getLastName() + " updated successfully");
        return "registrationsuccess";
    }


    /**
     * This method will provide the medium to update an existing applicant.
     */
    @RequestMapping(value = {"/edit-applicant-{id}"}, method = RequestMethod.GET)
    public String editApplicants(@PathVariable Integer id, ModelMap model) {
        Applicant applicant = applicantService.findById(id);
        model.addAttribute("applicant", applicant);
        model.addAttribute("edit", true);
        return "addapplicant";
    }

    /**
     * This method will be called on form submission, handling POST request for
     * updating applicant in database. It also validates the user input
     */
    @RequestMapping(value = {"/edit-applicant-{id}"}, method = RequestMethod.POST)
    public String updateApplicants(@Valid Applicant applicant, BindingResult result, ModelMap model, @PathVariable Integer id) {
        if (result.hasErrors()) {
            return "addapplicant";
        }
        applicantService.update(applicant);
        model.addAttribute("success", "Applicant " + applicant.getFirstName() + " " + applicant.getLastName() + " updated successfully");
        return "registrationsuccess";
    }


    /**
     * This method will delete an applicant by it's id.
     */
    @RequestMapping(value = {"/delete-applicant-{id}"}, method = RequestMethod.GET)
    public String deleteApplicants(@PathVariable Integer id) {
        applicantService.delete(id);
        return "redirect:/applicantslist";
    }

    /**
     * This method will list all existing vacancies.
     */
    @RequestMapping(value = {"/vacancieslist"}, method = RequestMethod.GET)
    public String listVacancies(ModelMap model) {
        List<Vacancy> vacancies = vacancyService.findAll();
        model.addAttribute("vacancies", vacancies);
        return "vacancieslist";
    }

    /**
     * This method will provide the medium to add a new vacancies.
     */
    @RequestMapping(value = {"/newvacancy"}, method = RequestMethod.GET)
    public String newVacancy(ModelMap model) {
        Vacancy vacancy = new Vacancy();
        model.addAttribute("vacancy", vacancy);
        model.addAttribute("edit", false);
        return "addvacancy";
    }

    /**
     * This method will provide the medium to generate random data.
     */
    @RequestMapping(value = {"/generateRandomData"}, method = RequestMethod.GET)
    public String generateRandomData(ModelMap model) {
        RandomData randomData = new RandomData();
        model.addAttribute("randomData", randomData);
        model.addAttribute("edit", false);
        return "generateRandomData";
    }

    /**
     * This method will be called on form submission, handling POST request for
     * saving generated Random Data in database.
     */
    @RequestMapping(value = {"/generateRandomData"}, method = RequestMethod.POST)
    public String generateRandomData(@Valid RandomData randomData, BindingResult result, ModelMap model) {
        if (result.hasErrors() || randomData.getNumberOfDataApplicant() == null || randomData.getNumberOfDataApplicant().intValue() <= 0
                || randomData.getNumberOfDataVacancies() == null || randomData.getNumberOfDataVacancies().intValue() <= 0) {
            return "redirect:/generateRandomData";
        }
        RandomDataUtil.setDeepLevel(1);
        RandomDataUtil.setNumberOfData(randomData.getNumberOfDataApplicant().intValue());
        RandomDataUtil.setNumberOfDataForOneToManyRelation(randomData.getNumberOfDataVacancies().intValue());
        RandomDataUtil.setbAllowNulls(Boolean.FALSE);
        RandomDataUtil.setPrecision(Const.FIVE);
        RandomDataUtil.setsPackageFromClass("com.project.model");
        RandomDataUtil.setsPackageFromEnum(null);
        RandomDataUtil.setsClassName(Applicant.class.getName());
        HashMap hmBoundary = new HashMap<String, Boundary>();
        final DateTimeFormatter format = DateTimeFormat.forPattern(DateUtil.sDateFormat);
        Boundary boundary = new Boundary(format.parseDateTime("1900/01/01 20:27:05").toDate(), format.parseDateTime("2030/01/01 20:27:05").toDate(), Boolean.FALSE, null, null);
        hmBoundary.put("Applicant.updateTime", boundary);
        Boundary boundary1 = new Boundary(1900, 2000, Boolean.FALSE, null, null);
        hmBoundary.put("Applicant.yearOfBirth", boundary1);
        RandomDataUtil.setHmBoundary(hmBoundary);
        ArrayList<Object> alData = RandomDataUtil.getFilledData();
        for (Object object : alData) {
            Applicant applicant = (Applicant) object;
            for (Vacancy vacancy : applicant.getVacancies()) {
                vacancy.setApplicants(new HashSet<>());
                vacancyService.save(vacancy);
            }
            applicantService.save(applicant);
        }
        model.addAttribute("success", " Data has been generated successfully");
        return "registrationsuccess";
    }

    /**
     * This method will be called on form submission, handling POST request for
     * saving vacancies in database. It also validates the user input
     */
    @RequestMapping(value = {"/newvacancy"}, method = RequestMethod.POST)
    public String saveVacancy(@Valid Vacancy vacancy, BindingResult result, ModelMap model) {
        if (result.hasErrors()) {
            return "addvacancy";
        }
        vacancyService.save(vacancy);
        model.addAttribute("success", " Vacancy " + vacancy.getVacancyName() + " " + vacancy.getVacancyCode() + " added successfully");
        return "registrationsuccess";
    }


    /**
     * This method will provide the medium to update an existing vacancy.
     */
    @RequestMapping(value = {"/edit-vacancy-{id}"}, method = RequestMethod.GET)
    public String editVacancy(@PathVariable Integer id, ModelMap model) {
        Vacancy vacancy = vacancyService.findById(id);
        model.addAttribute("vacancy", vacancy);
        model.addAttribute("edit", true);
        return "addvacancy";
    }

    /**
     * This method will be called on form submission, handling POST request for
     * updating vacancy in database. It also validates the user input
     */
    @RequestMapping(value = {"/edit-vacancy-{id}"}, method = RequestMethod.POST)
    public String updateVacancy(@Valid Vacancy vacancy, BindingResult result, ModelMap model, @PathVariable Integer id) {
        if (result.hasErrors()) {
            return "addvacancy";
        }
        vacancyService.update(vacancy);
        model.addAttribute("success", " Vacancy " + vacancy.getVacancyName() + " " + vacancy.getVacancyCode() + " added successfully");
        return "registrationsuccess";
    }


    /**
     * This method will delete an vacancy by it's id.
     */
    @RequestMapping(value = {"/delete-vacancy-{id}"}, method = RequestMethod.GET)
    public String deleteVacancy(@PathVariable Integer id) {
        vacancyService.delete(id);
        return "redirect:/vacancieslist";
    }


    /**
     * This method will provide vacancies list to views
     */
    @ModelAttribute("vacancies")
    public List<Vacancy> initializeVacancies() {
        return vacancyService.findAll();
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("dd/MM/yyyy"), true, 10));
    }
}
