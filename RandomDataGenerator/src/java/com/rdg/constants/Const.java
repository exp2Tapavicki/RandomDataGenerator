package com.rdg.constants;

import java.math.BigDecimal;
import java.math.BigInteger;

/*
* Copyright 2014 Radislav Tapavicki <radislavtt@gmail.com>
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
* http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

public class Const {
    public static final int FILL_HASH_DATA = -1;
    public static final int DEAFULT_NUMBER_OF_DATA = 50;
    public static final int DEAFULT_NUMBER_OF_DATA_FOR_ONE_TO_MANY_RELATION = 10;
    public static final int ZERO = 0;
    public static final int ONE = 1;
    public static final int TWO = 2;
    public static final int THREE = 3;
    public static final int FOUR = 4;
    public static final int FIVE = 5;
    public static final int NO_SORT = -1;
    public static final int SORT_ASC = 0;
    public static final int SORT_DESC = 1;

    public static final int MAX_EIGHT_DIGITS_BIG_TYPE = 25;
    public static final int PRECISION_TWO = 25;
    public static final int PRECISION_THREE = 25;
    public static final int PRECISION_FIVE = 5;
    public static final int MINUS_ONE = -1;

    public static final int COMPARE_LESSER = -1;
    public static final String STRING_GET = "get";
    public static final String STRING_EMPTY = "";
    public static final String STRING_SET = "set";
    public static final String STRING_SET_ID = "setid";
    public static final String STRING_NAME = "name";
    public static final String STRING_FIRST_NAME = "firstname";
    public static final String STRING_LAST_NAME = "lastname";
    public static final String STRING_EMAIL = "email";
    public static final String STRING_EMAIL_AT = "@somewhere.org";
    public static final int NAME = 0;
    public static final int FIRST_NAME = 1;
    public static final int LAST_NAME = 2;
    public static final int MIDDLE_NAME = 3;
    public static final int EMAIL = 4;
    public static final String MIN_VALUE = "MIN_VALUE";
    public static final String MAX_VALUE = "MAX_VALUE";
    public static final BigInteger MIN_VALUE_BIGINTEGER = new BigInteger("-99999999999");
    public static final BigInteger MAX_VALUE_BIGINTEGER = new BigInteger("99999999999");
    public static final BigDecimal MIN_VALUE_BIGDECIMAL = new BigDecimal("-99999999999");
    public static final BigDecimal MAX_VALUE_BIGDECIMAL = new BigDecimal("99999999999");

    public static final String DOT_DELIMITER = ".";
    public static final String COMMA_DELIMITER = ",";
    public static final String SUFIX_MIN = "min";
    public static final String SUFIX_MAX = "max";
    public static final String SUFIX_ALLOW_NULLS = "allow_nulls";
    public static final String SUFIX_PRECISION = "precision";
    public static final String SUFIX_ENUM = "enum";

    public static final Integer ZERO_INTEGER = 0;
    public static final Integer ONE_INTEGER = 1;
    public static final Long ZERO_LONG = new Long("0");
    public static final Short ZERO_SHORT = new Short("0");
    public static final byte ZERO_BYTE = 0;
    public static final Double ZERO_DOUBLE = new Double("0.00");
    public static final Float ZERO_FLOAT = new Float("0");
    public static final String ZERO_STRING = "0";

    public static final int MAX_CHAR = 256;
    public static final int CHAR_A = 65;
    public static final int CHAR_Z = 90;

    public static final String VOID = "void";
    public static final String CLASS_PACKAGE = "com.rdg.ormClasses";
    public static final String ENUM_PACKAGE = "com.rdg.enumeration";
    public static final String XML = "xml";
    public static final String JSON = "json";
    public static final String UTF_8 = "UTF-8";
    public static final String SLASH = "/";
    public static final String NEW_LINE = "\n";
    public static final String TAB = "\t";
    public static final String COLON = ":";
    public static final String HASH = "#";
    public static final String SPACE = " ";
    public static final String PROPERTIES = "properties";
    public static final String NULL_STRING = "(Null)";
    public static final String NULL = "null";
    public static final String THERE_IS_NO_CLASS_WITH_THE_NAME = "THERE_IS_NO_CLASS_WITH_THE_NAME";
    public static final String THERE_IS_NO_FIELD_WITH_THE_NAME = "THERE_IS_NO_FIELD_WITH_THE_NAME";
    public static final String COULD_NOT_INSTANCE_CLASS = "COULD_NOT_INSTANCE_CLASS";
    public static final String CLASS_NOT_EXIST = "CLASS_NOT_EXIST";
    public static final String IN_METHOD = "IN_METHOD";
    public static final String ILLEGAL_ACESS_EXEPTION_IN_METHOD = "ILLEGAL_ACESS_EXEPTION_IN_METHOD";
    public static final String RANGE_NUMBER_FILTER_FOR_PROPERTY = "RANGE_NUMBER_FILTER_FOR_PROPERTY";
    public static final String RANGE_NUMBER_FILTER_FROM = "RANGE_NUMBER_FILTER_FROM";
    public static final String RANGE_NUMBER_FILTER_TO = "RANGE_NUMBER_FILTER_TO";
    public static final String IN_RANGE = "IN_RANGE";
    public static final String TRUE = "TRUE";
    public static final String FALSE = "FALSE";
    public static final String SORT_CLASS = "SORT_CLASS";
    public static final String SORT_PROPERTY = "SORT_PROPERTY";
    public static final String ALLOW_NULLS = "ALLOW_NULLS";
    public static final String SIZE = "SIZE";
    public static final String FROM = "FROM";
    public static final String ERROR_NO_DATA = "ERROR_NO_DATA";
    public static final String ERROR_LOADING_INTERNATIONALIZATION_FILE = "ERROR_LOADING_INTERNATIONALIZATION_FILE";
    public static final String ERROR_LOADING_FILE = "ERROR_LOADING_FILE";
    public static final String ERROR_NO_INTERNATIONALIZATION_FOR_IDENTIFIER = "ERROR_NO_INTERNATIONALIZATION_FOR_IDENTIFIER";
    public static final String ERROR_NO_PROPERTY_FOR_IDENTIFIER = "ERROR_NO_PROPERTY_FOR_IDENTIFIER";
    public static final String ERROR_IN_JSON_WRITE = "ERROR_IN_JSON_WRITE";
    public static final String ERROR_IN_JSON_READ = "ERROR_IN_JSON_READ";
    public static final String ERROR_IN_XML_WRITE = "ERROR_IN_XML_WRITE";
    public static final String ERROR_IN_XML_READ = "ERROR_IN_XML_READ";
    public static final String WORKING_TIME_FOR_METHOD = "WORKING_TIME_FOR_METHOD";
    public static final String WORKING_TIME = "WORKING_TIME";
    public static final String HOURS = "HOURS";
    public static final String MINUTES = "MINUTES";
    public static final String SECONDS = "SECONDS";

    public static final String HASH_ARRAY = "##############################################################################################################################################################################################";
}
