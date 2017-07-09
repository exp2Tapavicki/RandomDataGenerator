package com.project.controller;

import java.io.BufferedInputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.URLConnection;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.validator.internal.engine.ConstraintViolationImpl;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.AuthenticationTrustResolver;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.project.model.Applicant;
import com.project.model.RandomData;
import com.project.model.RandomDataGeneration;
import com.project.model.RandomDataGenerationModel;
import com.project.model.User;
import com.project.model.UserProfile;
import com.project.model.UserProfileType;
import com.project.model.Vacancy;
import com.project.service.ServiceInterface;
import com.project.service.UserProfileService;
import com.project.service.UserService;
import com.rdg.boundary.Boundary;
import com.rdg.constants.BasicClassConstants;
import com.rdg.constants.Const;
import com.rdg.json.util.JSONUtil;
import com.rdg.util.DateUtil;
import com.rdg.util.RandomDataUtil;

@Controller
@RequestMapping("/rdg")
@SessionAttributes({ "vacancies, roles" })
public class RdgController {
    private static Map<String, String> classTypes;
    private static Map<String, List> visableFields;
    
    private static final String FILE_NAME="RandomDataGenerated";
    private static final String EXTENSION=".json";

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
        
        
        visableFields = new LinkedHashMap<>();
        visableFields.put("propertyName", Arrays.asList(
                "java.lang.Integer",
                "java.lang.Long",
                "java.lang.Short",
                "java.lang.Byte",
                "java.lang.Boolean",
                "java.lang.Double",
                "java.lang.Float",
                "java.lang.Character",
                "java.lang.String",
                "java.util.Date",
                "java.math.BigDecimal",
                "java.math.BigInteger",
                "java.lang.Enum",
                "int",
                "long",
                "short",
                "byte",
                "boolean",
                "float",
                "double",
                "char"
                ));
        visableFields.put("objMin", Arrays.asList(
                "java.lang.Integer",
                "java.lang.Long",
                "java.lang.Short",
                "java.lang.Byte",
                "java.lang.Double",
                "java.lang.Float",
                "java.lang.Character",
                "java.util.Date",
                "java.math.BigDecimal",
                "java.math.BigInteger",
                "java.lang.Enum",
                "int",
                "long",
                "short",
                "byte",
                "float",
                "double",
                "char"
                ));
        visableFields.put("objMax", Arrays.asList(
                "java.lang.Integer",
                "java.lang.Long",
                "java.lang.Short",
                "java.lang.Byte",
                "java.lang.Double",
                "java.lang.Float",
                "java.lang.Character",
                "java.util.Date",
                "java.math.BigDecimal",
                "java.math.BigInteger",
                "java.lang.Enum",
                "int",
                "long",
                "short",
                "byte",
                "float",
                "double",
                "char"
                ));
        visableFields.put("objPrecision", Arrays.asList(
                "java.lang.Double",
                "java.lang.Float",
                "java.math.BigDecimal",
                "float",
                "double"
                ));
        visableFields.put("bAllowNulls", Arrays.asList(
                "java.lang.Integer",
                "java.lang.Long",
                "java.lang.Short",
                "java.lang.Byte",
                "java.lang.Boolean",
                "java.lang.Double",
                "java.lang.Float",
                "java.lang.Character",
                "java.lang.String",
                "java.util.Date",
                "java.math.BigDecimal",
                "java.math.BigInteger"
                ));
        visableFields.put("objEnum", Arrays.asList(
                "java.lang.Enum"
                ));
    }

    @Autowired
    UserService userService;

    @Autowired
    UserProfileService userProfileService;

    @Autowired
    MessageSource messageSource;

    @Autowired
    PersistentTokenBasedRememberMeServices persistentTokenBasedRememberMeServices;

    @Autowired
    AuthenticationTrustResolver authenticationTrustResolver;

    @Autowired
    ServiceInterface<Applicant> applicantService;

    @Autowired
    ServiceInterface<Vacancy> vacancyService;

    @Autowired
    ServiceInterface<RandomDataGeneration> randomDataGenService;
    
    @Autowired
    ServiceInterface<RandomDataGenerationModel> randomDataGenModelService;

    /**
     * This method will list all existing applicants.
     */
    @RequestMapping(value = { "/" }, method = RequestMethod.GET)
    public String home(ModelMap model) {
        model.addAttribute("loggedinuser", getPrincipal());
        return "rdg/index";
    }

    /**
     * This method will list all existing applicants.
     */
    @RequestMapping(value = { "applicantslist" }, method = RequestMethod.GET)
    public String listApplicants(ModelMap model, HttpSession session) {
        List<Applicant> applicants = applicantService.findAll();
        List<Vacancy> vacancies = vacancyService.findAll();
        Applicant applicant = new Applicant();
        model.addAttribute("applicants", applicants);
        model.addAttribute("applicant", applicant);
        model.addAttribute("vacancies", vacancies);
        model.addAttribute("loggedinuser", getPrincipal());
        return "rdg/applicantslist";
    }

    /**
     * This method will list all existing random data generation.
     */
    @RequestMapping(value = { "/randomDataGeneration" }, method = RequestMethod.GET)
    public String randomDataGeneration(ModelMap model) {
        @SuppressWarnings("unused")
        RandomDataGenerationModel randomDataGenerationModel = new RandomDataGenerationModel();
        List<RandomDataGenerationModel> randomDataGenerationModelList = null;
        List<GrantedAuthority> roles = (List<GrantedAuthority>) SecurityContextHolder.getContext().getAuthentication().getAuthorities();
        Set<UserProfile> hsUserProfiles = new HashSet<>();
        User user = new User();
        UserProfile userProfile = null;
        for (GrantedAuthority role : roles) {
            if (role.toString().equals("ROLE_" +UserProfileType.ADMIN.toString())){
                userProfile = new UserProfile();
                userProfile.setType(UserProfileType.ADMIN.toString());
                hsUserProfiles.add(userProfile);
                userProfile = new UserProfile();
                userProfile.setType(UserProfileType.DBA.toString());
                hsUserProfiles.add(userProfile);
                userProfile = new UserProfile();
                userProfile.setType(UserProfileType.USER.toString());
                hsUserProfiles.add(userProfile);
            } else if (role.toString().equals("ROLE_" +UserProfileType.DBA.toString())){
                userProfile = new UserProfile();
                userProfile.setType(UserProfileType.DBA.toString());
                hsUserProfiles.add(userProfile);
                userProfile = new UserProfile();
                userProfile.setType(UserProfileType.USER.toString());
                hsUserProfiles.add(userProfile);
            } else {
                user.setSsoId(getPrincipal());
            }
        }
        if (!hsUserProfiles.isEmpty()){
            user.setUserProfiles(hsUserProfiles);
        }
        randomDataGenerationModel.setUser(user);
        randomDataGenerationModelList = randomDataGenModelService.search(randomDataGenerationModel);
       
        RandomData randomData = new RandomData();
        randomData.setRandomDataGenerationModels(randomDataGenerationModelList);
        model.addAttribute("randomData", randomData);
        model.addAttribute("randomDataGenerationModelList", randomDataGenerationModelList);
        model.addAttribute("loggedinuser", getPrincipal());
        return "rdg/randomDataGeneration";
    }

    /**
     * This method will be called on form submission, handling POST request for
     * generating random data in database.
     * @return 
     * @throws IOException 
     */
    @RequestMapping(value = { "/randomDataGeneration" }, method = RequestMethod.POST)
    public String randomDataGeneration(HttpServletResponse response, @Valid RandomData randomData, BindingResult result, ModelMap model) throws IOException {
        if (result.hasErrors() || randomData.getNumberOfDataApplicant() == null
                || randomData.getNumberOfDataApplicant().intValue() <= 0) {
            return "redirect:/rdg/randomDataGeneration";
        }
        RandomDataGeneration randomDataGenerationSearch = new RandomDataGeneration();
        randomDataGenerationSearch.setRandomDataGenerationModel(randomData.getRandomDataGenerationModels().get(0));
        List<RandomDataGeneration> randomDataGenerationList = randomDataGenService.search(randomDataGenerationSearch);
        StringBuilder sbRandomData = new StringBuilder();
        sbRandomData.append("[").append("\n").append("\t");

        for (int i = 0; i < randomData.getNumberOfDataApplicant(); i++) {
            sbRandomData.append("{").append("\n").append("\t");
            for (int j = 0; j < randomDataGenerationList.size(); j++) {
                RandomDataGeneration randomDataGeneration = randomDataGenerationList.get(j);
                HashMap<String, Boundary> hmBoundary = new HashMap<>();
                Boundary boundary = new Boundary();
                boundary.setObjMin(RandomDataUtil.getDinamiclyCastWithValue(
                        randomDataGeneration.getBasicClassConstants(), randomDataGeneration.getObjMin()));
                boundary.setObjMax(RandomDataUtil.getDinamiclyCastWithValue(
                        randomDataGeneration.getBasicClassConstants(), randomDataGeneration.getObjMax()));
                boundary.setObjPrecision(randomDataGeneration.getObjPrecision());
                boundary.setObjEnum(randomDataGeneration.getObjEnum().split(","));
                boundary.setbAllowNulls(randomDataGeneration.isbAllowNulls());
                if (boundary.getObjEnum() != null && boundary.getObjEnum().length > 1) {
                    boundary.setObjMin(0);
                    if (boundary.getObjMax() == null
                            || (((int) boundary.getObjMax()) > boundary.getObjEnum().length - 1)) {
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
                    value = RandomDataUtil.getRandomBasicObjectValue(randomDataGeneration.getBasicClassConstants(),
                            randomDataGeneration.isbAllowNulls());
                    sbRandomData.append(value);
                    break;
                case BasicClassConstants.sDate:
                case BasicClassConstants.sCharacter:
                case BasicClassConstants.sEnum:
                case BasicClassConstants.sPrimitiveChar:
                    value = RandomDataUtil.getRandomBasicObjectValue(randomDataGeneration.getBasicClassConstants(),
                            randomDataGeneration.isbAllowNulls());
                    if (value != null && !value.toString().trim().equals("")) {
                        sbRandomData.append("\"").append(value).append("\"");
                    } else {
                        sbRandomData.append("\"null\"");
                    }
                    break;
                case BasicClassConstants.sString:
                    value = RandomDataUtil.getRandomString(randomDataGeneration.getPropertyName(),
                            randomDataGeneration.isbAllowNulls());
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

        
        if (randomData.isDownloadFile()){
            File temp = null;
            try {
                // Create temp file.
                temp = File.createTempFile(FILE_NAME, EXTENSION);
    
                // Delete temp file when program exits.
                temp.deleteOnExit();
    
                // Write to temp file
                BufferedWriter out = new BufferedWriter(new FileWriter(temp));
                out.write(sbRandomData.toString());
                out.close();
            } catch (IOException e) {
            }
            
            String mimeType= URLConnection.guessContentTypeFromName(temp.getName());
            if(mimeType==null){
                mimeType = "application/octet-stream";
            }
            response.setContentType(mimeType);
            response.setHeader("Content-Disposition", String.format("inline; filename=\"" + temp.getName() +"\""));
            response.setHeader("Content-Disposition", String.format("attachment; filename=\"%s\"", temp.getName()));
            response.setContentLength((int)temp.length());
            InputStream inputStream = new BufferedInputStream(new FileInputStream(temp));
            FileCopyUtils.copy(inputStream, response.getOutputStream());
            return null;
        } else {
            model.addAttribute("success", " Data has been generated successfully");
            model.addAttribute("randomDataGenerated", sbRandomData.toString());
            model.addAttribute("loggedinuser", getPrincipal());
            return "rdg/registrationsuccess";    
        }
    }

    /**
     * This method will provide the medium to add a new Random Data Generation.
     */
    @RequestMapping(value = { "/newRandomDataGeneration-{randomDataGenerationModelId}-{ordinalNumber}" }, method = RequestMethod.GET)
    public String newRandomDataGeneration(ModelMap model, @PathVariable Integer randomDataGenerationModelId, @PathVariable Integer ordinalNumber) {
        RandomDataGeneration randomDataGeneration = new RandomDataGeneration();
        randomDataGeneration.setId(randomDataGenerationModelId);    
        randomDataGeneration.setOrdinalNumber(ordinalNumber);
        randomDataGeneration.setBasicClassConstants("java.lang.Integer");
        randomDataGeneration.setObjPrecision("5");
        model.addAttribute("randomDataGeneration", randomDataGeneration);
        model.put("classTypes", classTypes);
        model.put("visableFields", visableFields);
        model.addAttribute("edit", false);
        model.addAttribute("loggedinuser", getPrincipal());
        return "rdg/addRandomDataGeneration";
    }
    
    /**
     * This method will be called on form submission, handling POST request for
     * saving Random Data Generation in database.
     */
    @RequestMapping(value = { "/newRandomDataGeneration-{randomDataGenerationModelId}-{ordinalNumber}" }, method = RequestMethod.POST)
    public String newRandomDataGeneration(@Valid RandomDataGeneration randomDataGeneration, BindingResult result,
            ModelMap model, @PathVariable Integer randomDataGenerationModelId, @PathVariable Integer ordinalNumber) {
        if (result.hasErrors()) {
            model.put("classTypes", classTypes);
            model.put("visableFields", visableFields);
            return "rdg/addRandomDataGeneration";
        }
        randomDataGenService.save(randomDataGeneration);
        model.addAttribute("success", "Field added successfully");
        model.addAttribute("loggedinuser", getPrincipal());
        return "rdg/registrationsuccess";
    }
    
    /**
     * This method will provide the medium to update an existing Random Data
     * Genration.
     */
    @RequestMapping(value = { "/edit-randomDataGeneration-{id}-{ordinalNumber}" }, method = RequestMethod.GET)
    public String editRandomDataGeneration(@PathVariable Integer id, @PathVariable Integer ordinalNumber, ModelMap model) {
        RandomDataGeneration randomDataGeneration = randomDataGenService.findByIdOrdinalNumber(id, ordinalNumber);
        model.addAttribute("randomDataGeneration", randomDataGeneration);
        model.addAttribute("edit", true);
        model.addAttribute("loggedinuser", getPrincipal());
        model.put("classTypes", classTypes);
        model.put("visableFields", visableFields);
        return "rdg/addRandomDataGeneration";
    }

    /**
     * This method will be called on form submission, handling POST request for
     * updating Random Data Generation in database.
     */
    @RequestMapping(value = { "/edit-randomDataGeneration-{id}-{ordinalNumber}" }, method = RequestMethod.POST)
    public String updateRandomDataGeneration(@Valid RandomDataGeneration randomDataGeneration, BindingResult result,
            ModelMap model, @PathVariable Integer id, @PathVariable Integer ordinalNumber) {
        if (result.hasErrors()) {
            return "rdg/addRandomDataGeneration";
        }
        randomDataGenService.update(randomDataGeneration);
        model.addAttribute("success", "Field updated successfully");
        model.addAttribute("loggedinuser", getPrincipal());
        return "rdg/registrationsuccess";
    }

    /**
     * This method will delete an Random Data Generation by it's id.
     */
    @RequestMapping(value = { "/delete-randomDataGeneration-{id}-{ordinalNumber}" }, method = RequestMethod.GET)
    public String deleteRandomDataGeneration(@PathVariable Integer id, @PathVariable Integer ordinalNumber) {
        if (randomDataGenService.findByIdOrdinalNumber(id, ordinalNumber) != null) {
            randomDataGenService.delete(id, ordinalNumber);
        }
        return "redirect:/rdg/randomDataGeneration";
    }
    
    /**
     * This method will provide the medium to add a new Random Data Generation Model.
     */
    @RequestMapping(value = { "/newRandomDataGenerationModel"}, method = RequestMethod.GET)
    public String newRandomDataGenerationModel(ModelMap model) {
        RandomDataGenerationModel randomDataGenerationModel = new RandomDataGenerationModel();
        model.addAttribute("randomDataGenerationModel", randomDataGenerationModel);
        model.addAttribute("edit", false);
        model.addAttribute("loggedinuser", getPrincipal());
        return "rdg/addRandomDataGenerationModel";
    }
    
    /**
     * This method will be called on form submission, handling POST request for
     * saving Random Data Generation Model in database.
     */
    @RequestMapping(value = { "/newRandomDataGenerationModel" }, method = RequestMethod.POST)
    public String newRandomDataGeneration(@Valid RandomDataGenerationModel randomDataGenerationModel, BindingResult result,
            ModelMap model) {
        if (result.hasErrors()) {
            return "rdg/newRandomDataGenerationModel";
        }
        User user = userService.findBySSO(getPrincipal());
        randomDataGenerationModel.setUser(user);
        randomDataGenModelService.save(randomDataGenerationModel);
        model.addAttribute("success", "Field added successfully");
        model.addAttribute("loggedinuser", getPrincipal());
        return "rdg/registrationsuccess";
    }
    
    /**
     * This method will provide the medium to update an existing Random Data
     * Genration.
     */
    @RequestMapping(value = { "/edit-randomDataGenerationModel-{id}" }, method = RequestMethod.GET)
    public String editRandomDataGeneration(@PathVariable Integer id, ModelMap model) {
        RandomDataGenerationModel randomDataGenerationModel = randomDataGenModelService.findByIdOrdinalNumber(id, -1);
        model.addAttribute("randomDataGenerationModel", randomDataGenerationModel);
        model.addAttribute("edit", true);
        model.addAttribute("loggedinuser", getPrincipal());
        return "rdg/addRandomDataGenerationModel";
    }
    
    /**
     * This method will be called on form submission, handling POST request for
     * updating Random Data Generation Model in database.
     */
    @RequestMapping(value = { "/edit-randomDataGenerationModel-{id}" }, method = RequestMethod.POST)
    public String updateRandomDataGeneration(@Valid RandomDataGenerationModel randomDataGenerationModel, BindingResult result,
            ModelMap model, @PathVariable Integer id) {
        if (result.hasErrors()) {
            return "rdg/addRandomDataGeneration";
        }
        RandomDataGenerationModel randomDataGenerationModelDb = randomDataGenModelService.findByIdOrdinalNumber(id, -1);
        randomDataGenerationModel.setUser(randomDataGenerationModelDb.getUser());
        randomDataGenModelService.update(randomDataGenerationModel);
        model.addAttribute("success", "Field updated successfully");
        model.addAttribute("loggedinuser", getPrincipal());
        return "rdg/registrationsuccess";
    }
    
    /**
     * This method will delete an Random Data Generation Model by it's id.
     */
    @RequestMapping(value = { "/delete-randomDataGenerationModel-{id}" }, method = RequestMethod.GET)
    public String deleteRandomDataGeneration(@PathVariable Integer id) {
        RandomDataGenerationModel randomDataGenerationModel = randomDataGenModelService.findByIdOrdinalNumber(id, -1);
        if (randomDataGenerationModel != null) {
            for (RandomDataGeneration randomDataGeneration : randomDataGenerationModel.getRandomDataGenerations()) {
                randomDataGenService.delete(randomDataGeneration.getId(), randomDataGeneration.getOrdinalNumber());
            }
            randomDataGenModelService.delete(id, -1);
        }
        return "redirect:/rdg/randomDataGeneration";
    }

    /**
     * This method will list all existing applicants.
     */
    @RequestMapping(value = { "/applicantslist" }, method = RequestMethod.POST)
    public String listApplicants(@Valid Applicant applicant, BindingResult result, ModelMap model) {
        List<Applicant> applicants = applicantService.search(applicant);
        model.addAttribute("applicants", applicants);
        model.addAttribute("applicant", applicant);
        model.addAttribute("loggedinuser", getPrincipal());
        return "rdg/applicantslist";
    }

    /**
     * This method will provide the medium to add a new applicants.
     */
    @RequestMapping(value = { "/newapplicant" }, method = RequestMethod.GET)
    public String newApplicants(ModelMap model) {
        Applicant applicant = new Applicant();
        model.addAttribute("applicant", applicant);
        model.addAttribute("edit", false);
        model.addAttribute("loggedinuser", getPrincipal());
        return "rdg/addapplicant";
    }

    /**
     * This method will be called on form submission, handling POST request for
     * saving applicants in database. It also validates the user input
     */
    @RequestMapping(value = { "/newapplicant" }, method = RequestMethod.POST)
    public String saveApplicants(@Valid Applicant applicant, BindingResult result, ModelMap model) {
        if (result.hasErrors()) {
            return "rdg/addapplicant";
        }
        applicantService.save(applicant);
        model.addAttribute("success",
                "Applicant " + applicant.getFirstName() + " " + applicant.getLastName() + " updated successfully");
        model.addAttribute("loggedinuser", getPrincipal());
        return "rdg/registrationsuccess";
    }

    /**
     * This method will provide the medium to update an existing applicant.
     */
    @RequestMapping(value = { "/edit-applicant-{id}" }, method = RequestMethod.GET)
    public String editApplicants(@PathVariable Integer id, ModelMap model) {
        Applicant applicant = applicantService.findByIdOrdinalNumber(id, -1);
        model.addAttribute("applicant", applicant);
        model.addAttribute("edit", true);
        model.addAttribute("loggedinuser", getPrincipal());
        return "rdg/addapplicant";
    }

    /**
     * This method will be called on form submission, handling POST request for
     * updating applicant in database. It also validates the user input
     */
    @RequestMapping(value = { "/edit-applicant-{id}" }, method = RequestMethod.POST)
    public String updateApplicants(@Valid Applicant applicant, BindingResult result, ModelMap model,
            @PathVariable Integer id) {
        if (result.hasErrors()) {
            return "rdg/addapplicant";
        }
        applicantService.update(applicant);
        model.addAttribute("success",
                "Applicant " + applicant.getFirstName() + " " + applicant.getLastName() + " updated successfully");
        model.addAttribute("loggedinuser", getPrincipal());
        return "rdg/registrationsuccess";
    }

    /**
     * This method will delete an applicant by it's id.
     */
    @RequestMapping(value = { "/delete-applicant-{id}" }, method = RequestMethod.GET)
    public String deleteApplicants(@PathVariable Integer id) {
        if (applicantService.findByIdOrdinalNumber(id, -1) != null) {
            applicantService.delete(id, -1);
        }
        return "redirect:/rdg/applicantslist";
    }

    /**
     * This method will list all existing vacancies.
     */
    @RequestMapping(value = { "/vacancieslist" }, method = RequestMethod.GET)
    public String listVacancies(ModelMap model) {
        List<Vacancy> vacancies = vacancyService.findAll();
        model.addAttribute("vacancies", vacancies);
        model.addAttribute("loggedinuser", getPrincipal());
        return "rdg/vacancieslist";
    }

    /**
     * This method will provide the medium to add a new vacancies.
     */
    @RequestMapping(value = { "/newvacancy" }, method = RequestMethod.GET)
    public String newVacancy(ModelMap model) {
        Vacancy vacancy = new Vacancy();
        model.addAttribute("vacancy", vacancy);
        model.addAttribute("edit", false);
        model.addAttribute("loggedinuser", getPrincipal());
        return "rdg/addvacancy";
    }

    /**
     * This method will provide the medium to generate random data.
     */
    @RequestMapping(value = { "/generateRandomData" }, method = RequestMethod.GET)
    public String generateRandomData(ModelMap model) {
        RandomData randomData = new RandomData();
        model.addAttribute("randomData", randomData);
        model.addAttribute("edit", false);
        model.addAttribute("loggedinuser", getPrincipal());
        return "rdg/generateRandomData";
    }

    /**
     * This method will be called on form submission, handling POST request for
     * saving generated Random Data in database.
     * @throws IOException 
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @RequestMapping(value = { "/generateRandomData" }, method = RequestMethod.POST)
    public String generateRandomData(HttpServletResponse response, @Valid RandomData randomData, BindingResult result, ModelMap model) throws IOException {
        if (result.hasErrors() || randomData.getNumberOfDataApplicant() == null
                || randomData.getNumberOfDataApplicant().intValue() <= 0
                || randomData.getNumberOfDataVacancies() == null
                || randomData.getNumberOfDataVacancies().intValue() <= 0) {
            return "redirect:/rdg/generateRandomData";
        }
        RandomDataUtil.setDeepLevel(1);
        RandomDataUtil.setNumberOfData(randomData.getNumberOfDataApplicant().intValue());
        RandomDataUtil.setNumberOfDataForOneToManyRelation(randomData.getNumberOfDataVacancies().intValue());
        RandomDataUtil.setbAllowNulls(Boolean.FALSE);
        RandomDataUtil.setPrecision(Const.FIVE);
        RandomDataUtil.setsPackageFromClass("com.project.model");
        RandomDataUtil.setsPackageFromEnum(null);
        RandomDataUtil.setsClassName(Applicant.class.getName());
        HashMap<String, Boundary> hmBoundary = new HashMap<String, Boundary>();
        final DateTimeFormatter format = DateTimeFormat.forPattern(DateUtil.sDateFormat);
        Boundary boundary = new Boundary(format.parseDateTime("1900/01/01 20:27:05").toDate(),
                format.parseDateTime("2030/01/01 20:27:05").toDate(), Boolean.FALSE, null, null);
        hmBoundary.put("Applicant.updateTime", boundary);
        Boundary boundary1 = new Boundary(1900, 2000, Boolean.FALSE, null, null);
        hmBoundary.put("Applicant.yearOfBirth", boundary1);
        RandomDataUtil.setHmBoundary(hmBoundary);
        ArrayList<Object> alData = RandomDataUtil.getFilledData();
        
        
        if (!randomData.isDownloadFile()){
            Set errors = new HashSet<>();
    
            Session session = null;
            Transaction transaction = null;
            try {
                session = applicantService.getSession();
                transaction = session.beginTransaction();
                for (Object object : alData) {
                    Applicant applicant = (Applicant) object;
                    for (Vacancy vacancy : applicant.getVacancies()) {
                        vacancy.setApplicants(new HashSet<>());
                        errors.addAll(randomDataGenService.getValidator().validate(vacancy));
                        session.save(vacancy);
                    }
                    errors.addAll(randomDataGenService.getValidator().validate(applicant));
                    session.save(applicant);
                }
                session.getTransaction().commit();
            } catch (RuntimeException e) {
                try {
                    transaction.rollback();
                } catch (RuntimeException rbe) {
                }
            } finally {
                if (session != null) {
                    session.close();
                }
            }
            model.addAttribute("loggedinuser", getPrincipal());
            if (!errors.isEmpty()) {
                HashMap<String, String> errorsFormated = new HashMap<String, String>();
                for (Iterator iterator = errors.iterator(); iterator.hasNext();) {
                    ConstraintViolationImpl constraintViolationImpl = (ConstraintViolationImpl) iterator.next();
                    errorsFormated.put(constraintViolationImpl.getPropertyPath().toString(),
                            constraintViolationImpl.getMessage());
                }
                model.addAttribute("errors", errorsFormated);
                return "rdg/generateRandomData";
            }
            model.addAttribute("success", " Data has been generated successfully");
            return "rdg/registrationsuccess";
        } else {
            File temp = null;
            try {
                // Create temp file.
                temp = File.createTempFile(FILE_NAME, EXTENSION);
    
                // Delete temp file when program exits.
                temp.deleteOnExit();
    
                // Write to temp file
                BufferedWriter out = new BufferedWriter(new OutputStreamWriter(
                        new FileOutputStream(temp), "UTF-8"));
                for (Object object : alData) {
                    Applicant applicant = (Applicant) object;
                    JsonFactory factory = JSONUtil.objectMapper.getJsonFactory();
                    
                    PrintWriter printWriter = new PrintWriter(out);
                    JsonGenerator gen = factory.createGenerator(printWriter);
                    gen.useDefaultPrettyPrinter();
                    gen.writeObject(applicant);
//                    
//                    JSONUtil.objectMapper.getSerializerProvider().setNullKeySerializer(new NullKeySerializer());
//                    JSONUtil.objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.OBJECT_AND_NON_CONCRETE);
//                    JSONUtil.objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(applicant);

                }
//                out.write(sbRandomData.toString());
                out.close();
            } catch (IOException e) {
            }
            
            String mimeType= URLConnection.guessContentTypeFromName(temp.getName());
            if(mimeType==null){
                mimeType = "application/octet-stream";
            }
            response.setContentType(mimeType);
            response.setHeader("Content-Disposition", String.format("inline; filename=\"" + temp.getName() +"\""));
            response.setHeader("Content-Disposition", String.format("attachment; filename=\"%s\"", temp.getName()));
            response.setContentLength((int)temp.length());
            InputStream inputStream = new BufferedInputStream(new FileInputStream(temp));
            FileCopyUtils.copy(inputStream, response.getOutputStream());
            return null;
        }
    }

    /**
     * This method will be called on form submission, handling POST request for
     * saving vacancies in database. It also validates the user input
     */
    @RequestMapping(value = { "/newvacancy" }, method = RequestMethod.POST)
    public String saveVacancy(@Valid Vacancy vacancy, BindingResult result, ModelMap model) {
        if (result.hasErrors()) {
            return "rdg/addvacancy";
        }
        vacancyService.save(vacancy);
        model.addAttribute("success",
                " Vacancy " + vacancy.getVacancyName() + " " + vacancy.getVacancyCode() + " added successfully");
        model.addAttribute("loggedinuser", getPrincipal());
        return "rdg/registrationsuccess";
    }

    /**
     * This method will provide the medium to update an existing vacancy.
     */
    @RequestMapping(value = { "/edit-vacancy-{id}" }, method = RequestMethod.GET)
    public String editVacancy(@PathVariable Integer id, ModelMap model) {
        Vacancy vacancy = vacancyService.findByIdOrdinalNumber(id, -1);
        model.addAttribute("vacancy", vacancy);
        model.addAttribute("edit", true);
        model.addAttribute("loggedinuser", getPrincipal());
        return "rdg/addvacancy";
    }

    /**
     * This method will be called on form submission, handling POST request for
     * updating vacancy in database. It also validates the user input
     */
    @RequestMapping(value = { "/edit-vacancy-{id}" }, method = RequestMethod.POST)
    public String updateVacancy(@Valid Vacancy vacancy, BindingResult result, ModelMap model,
            @PathVariable Integer id) {
        if (result.hasErrors()) {
            return "rdg/addvacancy";
        }
        vacancyService.update(vacancy);
        model.addAttribute("success",
                " Vacancy " + vacancy.getVacancyName() + " " + vacancy.getVacancyCode() + " added successfully");
        model.addAttribute("loggedinuser", getPrincipal());
        return "rdg/registrationsuccess";
    }

    /**
     * This method will delete an vacancy by it's id.
     */
    @RequestMapping(value = { "/delete-vacancy-{id}" }, method = RequestMethod.GET)
    public String deleteVacancy(@PathVariable Integer id) {
        if (vacancyService.findByIdOrdinalNumber(id, -1) != null) {
            vacancyService.delete(id, -1);
        }
        return "redirect:/rdg/vacancieslist";
    }

    /**
     * This method will provide vacancies list to views
     */
    @ModelAttribute("vacancies")
    public List<Vacancy> initializeVacancies() {
        return vacancyService.findAll();
    }
    
    /*
     * Download a file 
     */
    @RequestMapping(value="/download/{type}", method = RequestMethod.GET)
    public void downloadFile(HttpServletResponse response, @PathVariable("fileName") String fileName) throws IOException {
     
        File file = null;
         
        ClassLoader classloader = Thread.currentThread().getContextClassLoader();
        file = new File(classloader.getResource(fileName).getFile());
         
        if(!file.exists()){
            String errorMessage = "Sorry. The file you are looking for does not exist";
            OutputStream outputStream = response.getOutputStream();
            outputStream.write(errorMessage.getBytes(Charset.forName("UTF-8")));
            outputStream.close();
            return;
        }
         
        String mimeType= URLConnection.guessContentTypeFromName(file.getName());
        if(mimeType==null){
            mimeType = "application/octet-stream";
        }
        response.setContentType(mimeType);
        response.setHeader("Content-Disposition", String.format("inline; filename=\"" + file.getName() +"\""));
        response.setHeader("Content-Disposition", String.format("attachment; filename=\"%s\"", file.getName()));
        response.setContentLength((int)file.length());
        InputStream inputStream = new BufferedInputStream(new FileInputStream(file));
 
        FileCopyUtils.copy(inputStream, response.getOutputStream());
    }
    

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("dd/MM/yyyy"), true, 10));
    }

    /**
     * This method handles Access-Denied redirect.
     */
    @RequestMapping(value = "/Access_Denied", method = RequestMethod.GET)
    public String accessDeniedPage(ModelMap model) {
        model.addAttribute("loggedinuser", getPrincipal());
        return "../accessDenied";
    }

    /**
     * This method returns the principal[user-name] of logged-in user.
     */
    private String getPrincipal() {
        String userName = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            userName = ((UserDetails) principal).getUsername();
        } else {
            userName = principal.toString();
        }
        return userName;
    }
    
    /**
     * This method returns true if users is already authenticated [logged-in],
     * else false.
     */
    private boolean isCurrentAuthenticationAnonymous() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authenticationTrustResolver.isAnonymous(authentication);
    }
}
