package com.rdg.util;

import com.rdg.constants.Const;
import com.rdg.enumeration.Gender;
import com.rdg.enumeration.Gender1;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

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

public class RandomUtilTest {
    private static final int numberOfIteration = 100000;
    private final boolean bAllowNullsTrue = Boolean.TRUE;
    private final boolean bAllowNullsFalse = Boolean.FALSE;

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void getRandomBoolean() throws Exception {
        for (int i = Const.ZERO; i < numberOfIteration; i++) {
            assertThat(RandomUtil.getRandomBoolean(bAllowNullsTrue), anyOf(is(Boolean.TRUE), is(Boolean.FALSE), nullValue()));
            assertThat(RandomUtil.getRandomBoolean(bAllowNullsFalse), anyOf(is(Boolean.TRUE), is(Boolean.FALSE)));
        }
    }

    @Test
    public void getRandomPrimitiveFloat() throws Exception {
        final float minTest1 = -9.99999f;
        final float maxTest1 = -9.99999f;

        final float minTest2 = 0f;
        final float maxTest2 = 0f;

        final float minTest3 = 15.12312312f;
        final float maxTest3 = 15.12312312f;

        final float minTest4 = -1.22f;
        final float maxTest4 = 1.23f;

        final float minTest5 = -1.23f;
        final float maxTest5 = 1.22f;

        final float minTest6 = 0f;
        final float maxTest6 = 1512312.12312312f;

        final float minTest7 = 0f;
        final float maxTest7 = -1512312.12312312f;

        final float minTest8 = -2312.12312312f;
        final float maxTest8 = -1512312.12312312f;

        final float minTest9 = -1512312.12312312f;
        final float maxTest9 = -2312.12312312f;

        final float minTest10 = 2312.12312312f;
        final float maxTest10 = 1512312.12312312f;

        final float minTest11 = 1512312.12312312f;
        final float maxTest11 = 2312.12312312f;

        final float minTest12 = -15f;
        final float maxTest12 = 2f;

        final float minTest13 = -2f;
        final float maxTest13 = 15f;

        float number, number1, number2, number3, number4, number5, number6, number7, number8, number9, number10, number11, number12, number13;

        for (int i = Const.ZERO; i < numberOfIteration; i++) {
            number = RandomUtil.getRandomPrimitiveFloat(Float.MIN_VALUE, Float.MAX_VALUE, Const.PRECISION_FIVE);
            number1 = RandomUtil.getRandomPrimitiveFloat(minTest1, maxTest1, Const.PRECISION_FIVE);
            number2 = RandomUtil.getRandomPrimitiveFloat(minTest2, maxTest2, Const.PRECISION_FIVE);
            number3 = RandomUtil.getRandomPrimitiveFloat(minTest3, maxTest3, Const.PRECISION_FIVE);
            number4 = RandomUtil.getRandomPrimitiveFloat(minTest4, maxTest4, Const.PRECISION_FIVE);
            number5 = RandomUtil.getRandomPrimitiveFloat(minTest5, maxTest5, Const.PRECISION_FIVE);
            number6 = RandomUtil.getRandomPrimitiveFloat(minTest6, maxTest6, Const.PRECISION_FIVE);
            number7 = RandomUtil.getRandomPrimitiveFloat(minTest7, maxTest7, Const.PRECISION_FIVE);
            number8 = RandomUtil.getRandomPrimitiveFloat(minTest8, maxTest8, Const.PRECISION_FIVE);
            number9 = RandomUtil.getRandomPrimitiveFloat(minTest9, maxTest9, Const.PRECISION_FIVE);
            number10 = RandomUtil.getRandomPrimitiveFloat(minTest10, maxTest10, Const.PRECISION_FIVE);
            number11 = RandomUtil.getRandomPrimitiveFloat(minTest11, maxTest11, Const.PRECISION_FIVE);
            number12 = RandomUtil.getRandomPrimitiveFloat(minTest12, maxTest12, Const.PRECISION_FIVE);
            number13 = RandomUtil.getRandomPrimitiveFloat(minTest13, maxTest13, Const.PRECISION_FIVE);

            assertTrue(number >= Float.MIN_VALUE && number <= Float.MAX_VALUE);
            assertTrue(number1 >= minTest1 && number1 <= maxTest1);
            assertTrue(number2 >= minTest2 && number2 <= maxTest2);
            assertTrue(number3 >= minTest3 && number3 <= maxTest3);
            assertTrue(number4 >= minTest4 && number4 <= maxTest4);
            assertTrue(number5 >= minTest5 && number5 <= maxTest5);
            assertTrue(number6 >= minTest6 && number6 <= maxTest6);
            if (minTest7 > maxTest7) {
                assertTrue(minTest7 >= number7 && maxTest7 <= number7);
            } else {
                assertTrue(number7 >= minTest7 && number7 <= maxTest7);
            }
            if (minTest8 > maxTest8) {
                assertTrue(minTest8 >= number8 && maxTest8 <= number8);
            } else {
                assertTrue(number8 >= minTest8 && number8 <= maxTest8);
            }
            assertTrue(number9 >= minTest9 && number9 <= maxTest9);
            assertTrue(number10 >= minTest10 && number10 <= maxTest10);
            if (minTest11 > maxTest11) {
                assertTrue(minTest11 >= number11 && maxTest11 <= number11);
            } else {
                assertTrue(number11 >= minTest11 && number11 <= maxTest11);
            }
            assertTrue(number12 >= minTest12 && number12 <= maxTest12);
            assertTrue(number13 >= minTest13 && number13 <= maxTest13);
        }
    }

    @Test
    public void getRandomFloat() throws Exception {
        final Float minTest1 = -9.99999f;
        final Float maxTest1 = -9.99999f;

        final Float minTest2 = 0f;
        final Float maxTest2 = 0f;

        final Float minTest3 = 15.12312312f;
        final Float maxTest3 = 15.12312312f;

        final Float minTest4 = -1.22f;
        final Float maxTest4 = 1.23f;

        final Float minTest5 = -1.23f;
        final Float maxTest5 = 1.22f;

        final Float minTest6 = 0f;
        final Float maxTest6 = 1512312.12312312f;

        final Float minTest7 = 0f;
        final Float maxTest7 = -1512312.12312312f;

        final Float minTest8 = -2312.12312312f;
        final Float maxTest8 = -1512312.12312312f;

        final Float minTest9 = -1512312.12312312f;
        final Float maxTest9 = -2312.12312312f;

        final Float minTest10 = 2312.12312312f;
        final Float maxTest10 = 1512312.12312312f;

        final Float minTest11 = 1512312.12312312f;
        final Float maxTest11 = 2312.12312312f;

        final Float minTest12 = -15f;
        final Float maxTest12 = 2f;

        final Float minTest13 = -2f;
        final Float maxTest13 = 15f;

        Float number, number1, number2, number3, number4, number5, number6, number7, number8, number9, number10, number11, number12, number13;

        for (int i = 0; i < numberOfIteration; i++) {
            number = RandomUtil.getRandomFloat(Float.MIN_VALUE, Float.MAX_VALUE, Const.PRECISION_FIVE, bAllowNullsTrue);
            number1 = RandomUtil.getRandomFloat(minTest1, maxTest1, Const.PRECISION_FIVE, bAllowNullsFalse);
            number2 = RandomUtil.getRandomFloat(minTest2, maxTest2, Const.PRECISION_FIVE, bAllowNullsFalse);
            number3 = RandomUtil.getRandomFloat(minTest3, maxTest3, Const.PRECISION_FIVE, bAllowNullsFalse);
            number4 = RandomUtil.getRandomFloat(minTest4, maxTest4, Const.PRECISION_FIVE, bAllowNullsFalse);
            number5 = RandomUtil.getRandomFloat(minTest5, maxTest5, Const.PRECISION_FIVE, bAllowNullsFalse);
            number6 = RandomUtil.getRandomFloat(minTest6, maxTest6, Const.PRECISION_FIVE, bAllowNullsFalse);
            number7 = RandomUtil.getRandomFloat(minTest7, maxTest7, Const.PRECISION_FIVE, bAllowNullsFalse);
            number8 = RandomUtil.getRandomFloat(minTest8, maxTest8, Const.PRECISION_FIVE, bAllowNullsFalse);
            number9 = RandomUtil.getRandomFloat(minTest9, maxTest9, Const.PRECISION_FIVE, bAllowNullsFalse);
            number10 = RandomUtil.getRandomFloat(minTest10, maxTest10, Const.PRECISION_FIVE, bAllowNullsFalse);
            number11 = RandomUtil.getRandomFloat(minTest11, maxTest11, Const.PRECISION_FIVE, bAllowNullsFalse);
            number12 = RandomUtil.getRandomFloat(minTest12, maxTest12, Const.PRECISION_FIVE, bAllowNullsFalse);
            number13 = RandomUtil.getRandomFloat(minTest13, maxTest13, Const.PRECISION_FIVE, bAllowNullsFalse);

            if (number != null) {
                assertTrue(number >= Float.MIN_VALUE && number <= Float.MAX_VALUE);
            } else {
                assertNull(number);
            }
            assertTrue(number1.compareTo(minTest1) >= 0 && number1.compareTo(maxTest1) <= 0);
            assertTrue(number2.compareTo(minTest2) >= 0 && number2.compareTo(maxTest2) <= 0);
            assertTrue(number3.compareTo(minTest3) >= 0 && number3.compareTo(maxTest3) <= 0);
            assertTrue(number4.compareTo(minTest4) >= 0 && number4.compareTo(maxTest4) <= 0);
            assertTrue(number5.compareTo(minTest5) >= 0 && number5.compareTo(maxTest5) <= 0);
            assertTrue(number6.compareTo(minTest6) >= 0 && number6.compareTo(maxTest6) <= 0);
            if (minTest7.compareTo(maxTest7) > 0) {
                assertTrue(minTest7.compareTo(number7) >= 0 && maxTest7.compareTo(number7) <= 0);
            } else {
                assertTrue(number7.compareTo(minTest7) >= 0 && number7.compareTo(maxTest7) <= 0);
            }
            if (minTest8.compareTo(maxTest8) > 0) {
                assertTrue(minTest8.compareTo(number8) >= 0 && maxTest8.compareTo(number8) <= 0);
            } else {
                assertTrue(number8.compareTo(minTest8) >= 0 && number8.compareTo(maxTest8) <= 0);
            }
            assertTrue(number9.compareTo(minTest9) >= 0 && number9.compareTo(maxTest9) <= 0);
            assertTrue(number10.compareTo(minTest10) >= 0 && number10.compareTo(maxTest10) <= 0);
            if (minTest11.compareTo(maxTest11) > 0) {
                assertTrue(minTest11.compareTo(number11) >= 0 && maxTest11.compareTo(maxTest11) <= 0);
            } else {
                assertTrue(number11.compareTo(minTest11) >= 0 && number11.compareTo(maxTest11) <= 0);
            }
            assertTrue(number12.compareTo(minTest12) >= 0 && number12.compareTo(maxTest12) <= 0);
            assertTrue(number13.compareTo(minTest13) >= 0 && number13.compareTo(maxTest13) <= 0);
        }
    }

    @Test
    public void getRandomPrimitiveDouble() throws Exception {
        final double minTest1 = -9.99999;
        final double maxTest1 = -9.99999;

        final double minTest2 = 0;
        final double maxTest2 = 0;

        final double minTest3 = 15.12312312;
        final double maxTest3 = 15.12312312;

        final double minTest4 = -1.22;
        final double maxTest4 = 1.23;

        final double minTest5 = -1.23;
        final double maxTest5 = 1.22;

        final double minTest6 = 0;
        final double maxTest6 = 1512312.12312312;

        final double minTest7 = 0;
        final double maxTest7 = -1512312.12312312;

        final double minTest8 = -2312.12312312;
        final double maxTest8 = -1512312.12312312;

        final double minTest9 = -1512312.12312312;
        final double maxTest9 = -2312.12312312;

        final double minTest10 = 2312.12312312;
        final double maxTest10 = 1512312.12312312;

        final double minTest11 = 1512312.12312312;
        final double maxTest11 = 2312.12312312;

        final double minTest12 = -15;
        final double maxTest12 = 2;

        final double minTest13 = -2;
        final double maxTest13 = 15;

        double number, number1, number2, number3, number4, number5, number6, number7, number8, number9, number10, number11, number12, number13;

        for (int i = Const.ZERO; i < numberOfIteration; i++) {
            number = RandomUtil.getRandomPrimitiveDouble(Float.MIN_VALUE, Float.MAX_VALUE, Const.PRECISION_FIVE);
            number1 = RandomUtil.getRandomPrimitiveDouble(minTest1, maxTest1, Const.PRECISION_FIVE);
            number2 = RandomUtil.getRandomPrimitiveDouble(minTest2, maxTest2, Const.PRECISION_FIVE);
            number3 = RandomUtil.getRandomPrimitiveDouble(minTest3, maxTest3, Const.PRECISION_FIVE);
            number4 = RandomUtil.getRandomPrimitiveDouble(minTest4, maxTest4, Const.PRECISION_FIVE);
            number5 = RandomUtil.getRandomPrimitiveDouble(minTest5, maxTest5, Const.PRECISION_FIVE);
            number6 = RandomUtil.getRandomPrimitiveDouble(minTest6, maxTest6, Const.PRECISION_FIVE);
            number7 = RandomUtil.getRandomPrimitiveDouble(minTest7, maxTest7, Const.PRECISION_FIVE);
            number8 = RandomUtil.getRandomPrimitiveDouble(minTest8, maxTest8, Const.PRECISION_FIVE);
            number9 = RandomUtil.getRandomPrimitiveDouble(minTest9, maxTest9, Const.PRECISION_FIVE);
            number10 = RandomUtil.getRandomPrimitiveDouble(minTest10, maxTest10, Const.PRECISION_FIVE);
            number11 = RandomUtil.getRandomPrimitiveDouble(minTest11, maxTest11, Const.PRECISION_FIVE);
            number12 = RandomUtil.getRandomPrimitiveDouble(minTest12, maxTest12, Const.PRECISION_FIVE);
            number13 = RandomUtil.getRandomPrimitiveDouble(minTest13, maxTest13, Const.PRECISION_FIVE);

            assertTrue(number >= Double.MIN_VALUE && number <= Double.MAX_VALUE);
            assertTrue(number1 >= minTest1 && number1 <= maxTest1);
            assertTrue(number2 >= minTest2 && number2 <= maxTest2);
            assertTrue(number3 >= minTest3 && number3 <= maxTest3);
            assertTrue(number4 >= minTest4 && number4 <= maxTest4);
            assertTrue(number5 >= minTest5 && number5 <= maxTest5);
            assertTrue(number6 >= minTest6 && number6 <= maxTest6);
            if (minTest7 > maxTest7) {
                assertTrue(minTest7 >= number7 && maxTest7 <= number7);
            } else {
                assertTrue(number7 >= minTest7 && number7 <= maxTest7);
            }
            if (minTest8 > maxTest8) {
                assertTrue(minTest8 >= number8 && maxTest8 <= number8);
            } else {
                assertTrue(number8 >= minTest8 && number8 <= maxTest8);
            }
            assertTrue(number9 >= minTest9 && number9 <= maxTest9);
            assertTrue(number10 >= minTest10 && number10 <= maxTest10);
            if (minTest11 > maxTest11) {
                assertTrue(minTest11 >= number11 && maxTest11 <= number11);
            } else {
                assertTrue(number11 >= minTest11 && number11 <= maxTest11);
            }
            assertTrue(number12 >= minTest12 && number12 <= maxTest12);
            assertTrue(number13 >= minTest13 && number13 <= maxTest13);
        }
    }

    @Test
    public void getRandomDouble() throws Exception {
        final Double minTest1 = new Double("-9.99999");
        final Double maxTest1 = new Double("-9.99999");

        final Double minTest2 = new Double("0");
        final Double maxTest2 = new Double("0");

        final Double minTest3 = new Double("15.12312312");
        final Double maxTest3 = new Double("15.12312312");

        final Double minTest4 = new Double("-1.22");
        final Double maxTest4 = new Double("1.23");

        final Double minTest5 = new Double("-1.23");
        final Double maxTest5 = new Double("1.22");

        final Double minTest6 = new Double("0");
        final Double maxTest6 = new Double("1512312.12312312");

        final Double minTest7 = new Double("0");
        final Double maxTest7 = new Double("-1512312.12312312");

        final Double minTest8 = new Double("-2312.12312312");
        final Double maxTest8 = new Double("-1512312.12312312");

        final Double minTest9 = new Double("-1512312.12312312");
        final Double maxTest9 = new Double("-2312.12312312");

        final Double minTest10 = new Double("2312.12312312");
        final Double maxTest10 = new Double("1512312.12312312");

        final Double minTest11 = new Double("1512312.12312312");
        final Double maxTest11 = new Double("2312.12312312");

        final Double minTest12 = new Double("-15");
        final Double maxTest12 = new Double("2");

        final Double minTest13 = new Double("-2");
        final Double maxTest13 = new Double("5");

        Double number, number1, number2, number3, number4, number5, number6, number7, number8, number9, number10, number11, number12, number13;

        for (int i = Const.ZERO; i < numberOfIteration; i++) {
            number = RandomUtil.getRandomDouble(Double.MIN_VALUE, Double.MAX_VALUE, Const.PRECISION_FIVE, bAllowNullsTrue);
            number1 = RandomUtil.getRandomDouble(minTest1, maxTest1, Const.PRECISION_FIVE, bAllowNullsFalse);
            number2 = RandomUtil.getRandomDouble(minTest2, maxTest2, Const.PRECISION_FIVE, bAllowNullsFalse);
            number3 = RandomUtil.getRandomDouble(minTest3, maxTest3, Const.PRECISION_FIVE, bAllowNullsFalse);
            number4 = RandomUtil.getRandomDouble(minTest4, maxTest4, Const.PRECISION_FIVE, bAllowNullsFalse);
            number5 = RandomUtil.getRandomDouble(minTest5, maxTest5, Const.PRECISION_FIVE, bAllowNullsFalse);
            number6 = RandomUtil.getRandomDouble(minTest6, maxTest6, Const.PRECISION_FIVE, bAllowNullsFalse);
            number7 = RandomUtil.getRandomDouble(minTest7, maxTest7, Const.PRECISION_FIVE, bAllowNullsFalse);
            number8 = RandomUtil.getRandomDouble(minTest8, maxTest8, Const.PRECISION_FIVE, bAllowNullsFalse);
            number9 = RandomUtil.getRandomDouble(minTest9, maxTest9, Const.PRECISION_FIVE, bAllowNullsFalse);
            number10 = RandomUtil.getRandomDouble(minTest10, maxTest10, Const.PRECISION_FIVE, bAllowNullsFalse);
            number11 = RandomUtil.getRandomDouble(minTest11, maxTest11, Const.PRECISION_FIVE, bAllowNullsFalse);
            number12 = RandomUtil.getRandomDouble(minTest12, maxTest12, Const.PRECISION_FIVE, bAllowNullsFalse);
            number13 = RandomUtil.getRandomDouble(minTest13, maxTest13, Const.PRECISION_FIVE, bAllowNullsFalse);

            if (number != null) {
                assertTrue(number.compareTo(Double.MIN_VALUE) >= 0 && number.compareTo(Double.MAX_VALUE) <= 0);
            } else {
                assertNull(number);
            }
            assertTrue(number1.compareTo(minTest1) >= 0 && number1.compareTo(maxTest1) <= 0);
            assertTrue(number2.compareTo(minTest2) >= 0 && number2.compareTo(maxTest2) <= 0);
            assertTrue(number3.compareTo(minTest3) >= 0 && number3.compareTo(maxTest3) <= 0);
            assertTrue(number4.compareTo(minTest4) >= 0 && number4.compareTo(maxTest4) <= 0);
            assertTrue(number5.compareTo(minTest5) >= 0 && number5.compareTo(maxTest5) <= 0);
            assertTrue(number6.compareTo(minTest6) >= 0 && number6.compareTo(maxTest6) <= 0);
            if (minTest7.compareTo(maxTest7) > 0) {
                assertTrue(minTest7.compareTo(number7) >= 0 && maxTest7.compareTo(number7) <= 0);
            } else {
                assertTrue(number7.compareTo(minTest7) >= 0 && number7.compareTo(maxTest7) <= 0);
            }
            if (minTest8.compareTo(maxTest8) > 0) {
                assertTrue(minTest8.compareTo(number8) >= 0 && maxTest8.compareTo(number8) <= 0);
            } else {
                assertTrue(number8.compareTo(minTest8) >= 0 && number8.compareTo(maxTest8) <= 0);
            }
            assertTrue(number9.compareTo(minTest9) >= 0 && number9.compareTo(maxTest9) <= 0);
            assertTrue(number10.compareTo(minTest10) >= 0 && number10.compareTo(maxTest10) <= 0);
            if (minTest11.compareTo(maxTest11) > 0) {
                assertTrue(minTest11.compareTo(number11) >= 0 && maxTest11.compareTo(number11) <= 0);
            } else {
                assertTrue(number11.compareTo(minTest11) >= 0 && number11.compareTo(maxTest11) <= 0);
            }
            assertTrue(number12.compareTo(minTest12) >= 0 && number12.compareTo(maxTest12) <= 0);
            assertTrue(number13.compareTo(minTest13) >= 0 && number13.compareTo(maxTest13) <= 0);
        }
    }

    @Test
    public void getRandomPrimitiveInt() throws Exception {
        final int minTest1 = -9;
        final int maxTest1 = -9;

        final int minTest2 = 0;
        final int maxTest2 = 0;

        final int minTest3 = 15;
        final int maxTest3 = 15;

        final int minTest4 = -122;
        final int maxTest4 = 123;

        final int minTest5 = -123;
        final int maxTest5 = 122;

        final int minTest6 = 0;
        final int maxTest6 = 1512312;

        final int minTest7 = 0;
        final int maxTest7 = -1512312;

        final int minTest8 = -2312;
        final int maxTest8 = -1512312;

        final int minTest9 = -1512312;
        final int maxTest9 = -2312;

        final int minTest10 = 2312;
        final int maxTest10 = 1512312;

        final int minTest11 = 1512312;
        final int maxTest11 = 2312;

        final int minTest12 = -15;
        final int maxTest12 = 2;

        final int minTest13 = -2;
        final int maxTest13 = 15;

        int number, number1, number2, number3, number4, number5, number6, number7, number8, number9, number10, number11, number12, number13;

        for (int i = 0; i < numberOfIteration; i++) {
            number = RandomUtil.getRandomPrimitiveInt(Integer.MIN_VALUE, Integer.MAX_VALUE);
            number1 = RandomUtil.getRandomPrimitiveInt(minTest1, maxTest1);
            number2 = RandomUtil.getRandomPrimitiveInt(minTest2, maxTest2);
            number3 = RandomUtil.getRandomPrimitiveInt(minTest3, maxTest3);
            number4 = RandomUtil.getRandomPrimitiveInt(minTest4, maxTest4);
            number5 = RandomUtil.getRandomPrimitiveInt(minTest5, maxTest5);
            number6 = RandomUtil.getRandomPrimitiveInt(minTest6, maxTest6);
            number7 = RandomUtil.getRandomPrimitiveInt(minTest7, maxTest7);
            number8 = RandomUtil.getRandomPrimitiveInt(minTest8, maxTest8);
            number9 = RandomUtil.getRandomPrimitiveInt(minTest9, maxTest9);
            number10 = RandomUtil.getRandomPrimitiveInt(minTest10, maxTest10);
            number11 = RandomUtil.getRandomPrimitiveInt(minTest11, maxTest11);
            number12 = RandomUtil.getRandomPrimitiveInt(minTest12, maxTest12);
            number13 = RandomUtil.getRandomPrimitiveInt(minTest13, maxTest13);

            assertTrue(number >= Integer.MIN_VALUE && number <= Integer.MAX_VALUE);
            assertTrue(number1 >= minTest1 && number1 <= maxTest1);
            assertTrue(number2 >= minTest2 && number2 <= maxTest2);
            assertTrue(number3 >= minTest3 && number3 <= maxTest3);
            assertTrue(number4 >= minTest4 && number4 <= maxTest4);
            assertTrue(number5 >= minTest5 && number5 <= maxTest5);
            assertTrue(number6 >= minTest6 && number6 <= maxTest6);
            if (minTest7 > maxTest7) {
                assertTrue(minTest7 >= number7 && maxTest7 <= number7);
            } else {
                assertTrue(number7 >= minTest7 && number7 <= maxTest7);
            }
            if (minTest8 > maxTest8) {
                assertTrue(minTest8 >= number8 && maxTest8 <= number8);
            } else {
                assertTrue(number8 >= minTest8 && number8 <= maxTest8);
            }
            assertTrue(number9 >= minTest9 && number9 <= maxTest9);
            assertTrue(number10 >= minTest10 && number10 <= maxTest10);
            if (minTest11 > maxTest11) {
                assertTrue(minTest11 >= number11 && maxTest11 <= number11);
            } else {
                assertTrue(number11 >= minTest11 && number11 <= maxTest11);
            }
            assertTrue(number12 >= minTest12 && number12 <= maxTest12);
            assertTrue(number13 >= minTest13 && number13 <= maxTest13);
        }
    }

    @Test
    public void getRandomInteger() throws Exception {
        final Integer minTest1 = new Integer("-9");
        final Integer maxTest1 = new Integer("-9");

        final Integer minTest2 = new Integer("0");
        final Integer maxTest2 = new Integer("0");

        final Integer minTest3 = new Integer("15");
        final Integer maxTest3 = new Integer("15");

        final Integer minTest4 = new Integer("-122");
        final Integer maxTest4 = new Integer("123");

        final Integer minTest5 = new Integer("-123");
        final Integer maxTest5 = new Integer("122");

        final Integer minTest6 = new Integer("0");
        final Integer maxTest6 = new Integer("1512312");

        final Integer minTest7 = new Integer("0");
        final Integer maxTest7 = new Integer("-1512312");

        final Integer minTest8 = new Integer("-2312");
        final Integer maxTest8 = new Integer("-1512312");

        final Integer minTest9 = new Integer("-1512312");
        final Integer maxTest9 = new Integer("-2312");

        final Integer minTest10 = new Integer("2312");
        final Integer maxTest10 = new Integer("1512312");

        final Integer minTest11 = new Integer("1512312");
        final Integer maxTest11 = new Integer("2312");

        final Integer minTest12 = new Integer("-15");
        final Integer maxTest12 = new Integer("2");

        final Integer minTest13 = new Integer("-2");
        final Integer maxTest13 = new Integer("15");

        Integer number, number1, number2, number3, number4, number5, number6, number7, number8, number9, number10, number11, number12, number13;

        for (int i = 0; i < numberOfIteration; i++) {
            number = RandomUtil.getRandomInteger(Integer.MIN_VALUE, Integer.MAX_VALUE, bAllowNullsTrue);
            number1 = RandomUtil.getRandomInteger(minTest1, maxTest1, bAllowNullsFalse);
            number2 = RandomUtil.getRandomInteger(minTest2, maxTest2, bAllowNullsFalse);
            number3 = RandomUtil.getRandomInteger(minTest3, maxTest3, bAllowNullsFalse);
            number4 = RandomUtil.getRandomInteger(minTest4, maxTest4, bAllowNullsFalse);
            number5 = RandomUtil.getRandomInteger(minTest5, maxTest5, bAllowNullsFalse);
            number6 = RandomUtil.getRandomInteger(minTest6, maxTest6, bAllowNullsFalse);
            number7 = RandomUtil.getRandomInteger(minTest7, maxTest7, bAllowNullsFalse);
            number8 = RandomUtil.getRandomInteger(minTest8, maxTest8, bAllowNullsFalse);
            number9 = RandomUtil.getRandomInteger(minTest9, maxTest9, bAllowNullsFalse);
            number10 = RandomUtil.getRandomInteger(minTest10, maxTest10, bAllowNullsFalse);
            number11 = RandomUtil.getRandomInteger(minTest11, maxTest11, bAllowNullsFalse);
            number12 = RandomUtil.getRandomInteger(minTest12, maxTest12, bAllowNullsFalse);
            number13 = RandomUtil.getRandomInteger(minTest13, maxTest13, bAllowNullsFalse);

            if (number != null) {
                assertTrue(number.compareTo(Integer.MIN_VALUE) >= 0 && number.compareTo(Integer.MAX_VALUE) <= 0);
            } else {
                assertNull(number);
            }
            assertTrue(number1.compareTo(minTest1) >= 0 && number1.compareTo(maxTest1) <= 0);
            assertTrue(number2.compareTo(minTest2) >= 0 && number2.compareTo(maxTest2) <= 0);
            assertTrue(number3.compareTo(minTest3) >= 0 && number3.compareTo(maxTest3) <= 0);
            assertTrue(number4.compareTo(minTest4) >= 0 && number4.compareTo(maxTest4) <= 0);
            assertTrue(number5.compareTo(minTest5) >= 0 && number5.compareTo(maxTest5) <= 0);
            assertTrue(number6.compareTo(minTest6) >= 0 && number6.compareTo(maxTest6) <= 0);
            if (minTest7.compareTo(maxTest7) > 0) {
                assertTrue(minTest7.compareTo(number7) >= 0 && maxTest7.compareTo(number7) <= 0);
            } else {
                assertTrue(number7.compareTo(minTest7) >= 0 && number7.compareTo(maxTest7) <= 0);
            }
            if (minTest8.compareTo(maxTest8) > 0) {
                assertTrue(minTest8.compareTo(number8) >= 0 && maxTest8.compareTo(number8) <= 0);
            } else {
                assertTrue(number8.compareTo(minTest8) >= 0 && number8.compareTo(maxTest8) <= 0);
            }
            assertTrue(number9.compareTo(minTest9) >= 0 && number9.compareTo(maxTest9) <= 0);
            assertTrue(number10.compareTo(minTest10) >= 0 && number10.compareTo(maxTest10) <= 0);
            if (minTest11.compareTo(maxTest11) > 0) {
                assertTrue(minTest11.compareTo(number11) >= 0 && maxTest11.compareTo(number11) <= 0);
            } else {
                assertTrue(number11.compareTo(minTest11) >= 0 && number11.compareTo(maxTest11) <= 0);
            }
            assertTrue(number12.compareTo(minTest12) >= 0 && number12.compareTo(maxTest12) <= 0);
            assertTrue(number13.compareTo(minTest13) >= 0 && number13.compareTo(maxTest13) <= 0);
        }
    }

    @Test
    public void getRandomPrimitiveLong() throws Exception {
        final long minTest1 = -9L;
        final long maxTest1 = -9L;

        final long minTest2 = 0L;
        final long maxTest2 = 0L;

        final long minTest3 = 15L;
        final long maxTest3 = 15L;

        final long minTest4 = -122L;
        final long maxTest4 = 123L;

        final long minTest5 = -123L;
        final long maxTest5 = 122L;

        final long minTest6 = 0L;
        final long maxTest6 = 1512312L;

        final long minTest7 = 0L;
        final long maxTest7 = -1512312L;

        final long minTest8 = -2312L;
        final long maxTest8 = -1512312L;

        final long minTest9 = -1512312L;
        final long maxTest9 = -2312L;

        final long minTest10 = 2312L;
        final long maxTest10 = 1512312L;

        final long minTest11 = 1512312L;
        final long maxTest11 = 2312L;

        final long minTest12 = -15L;
        final long maxTest12 = 2L;

        final long minTest13 = -2L;
        final long maxTest13 = 15L;

        long number, number1, number2, number3, number4, number5, number6, number7, number8, number9, number10, number11, number12, number13;

        for (int i = 0; i < numberOfIteration; i++) {
            number = RandomUtil.getRandomPrimitiveLong(Long.MIN_VALUE, Long.MAX_VALUE);
            number1 = RandomUtil.getRandomPrimitiveLong(minTest1, maxTest1);
            number2 = RandomUtil.getRandomPrimitiveLong(minTest2, maxTest2);
            number3 = RandomUtil.getRandomPrimitiveLong(minTest3, maxTest3);
            number4 = RandomUtil.getRandomPrimitiveLong(minTest4, maxTest4);
            number5 = RandomUtil.getRandomPrimitiveLong(minTest5, maxTest5);
            number6 = RandomUtil.getRandomPrimitiveLong(minTest6, maxTest6);
            number7 = RandomUtil.getRandomPrimitiveLong(minTest7, maxTest7);
            number8 = RandomUtil.getRandomPrimitiveLong(minTest8, maxTest8);
            number9 = RandomUtil.getRandomPrimitiveLong(minTest9, maxTest9);
            number10 = RandomUtil.getRandomPrimitiveLong(minTest10, maxTest10);
            number11 = RandomUtil.getRandomPrimitiveLong(minTest11, maxTest11);
            number12 = RandomUtil.getRandomPrimitiveLong(minTest12, maxTest12);
            number13 = RandomUtil.getRandomPrimitiveLong(minTest13, maxTest13);

            assertTrue(number >= Long.MIN_VALUE && number <= Long.MAX_VALUE);
            assertTrue(number1 >= minTest1 && number1 <= maxTest1);
            assertTrue(number2 >= minTest2 && number2 <= maxTest2);
            assertTrue(number3 >= minTest3 && number3 <= maxTest3);
            assertTrue(number4 >= minTest4 && number4 <= maxTest4);
            assertTrue(number5 >= minTest5 && number5 <= maxTest5);
            assertTrue(number6 >= minTest6 && number6 <= maxTest6);
            if (minTest7 > maxTest7) {
                assertTrue(minTest7 >= number7 && maxTest7 <= number7);
            } else {
                assertTrue(number7 >= minTest7 && number7 <= maxTest7);
            }
            if (minTest8 > maxTest8) {
                assertTrue(minTest8 >= number8 && maxTest8 <= number8);
            } else {
                assertTrue(number8 >= minTest8 && number8 <= maxTest8);
            }
            assertTrue(number9 >= minTest9 && number9 <= maxTest9);
            assertTrue(number10 >= minTest10 && number10 <= maxTest10);
            if (minTest11 > maxTest11) {
                assertTrue(minTest11 >= number11 && maxTest11 <= number11);
            } else {
                assertTrue(number11 >= minTest11 && number11 <= maxTest11);
            }
            assertTrue(number12 >= minTest12 && number12 <= maxTest12);
            assertTrue(number13 >= minTest13 && number13 <= maxTest13);
        }
    }

    @Test
    public void getRandomLong() throws Exception {
        final Long minTest1 = new Long("-9");
        final Long maxTest1 = new Long("-9");

        final Long minTest2 = new Long("0");
        final Long maxTest2 = new Long("0");

        final Long minTest3 = new Long("15");
        final Long maxTest3 = new Long("15");

        final Long minTest4 = new Long("-122");
        final Long maxTest4 = new Long("123");

        final Long minTest5 = new Long("-123");
        final Long maxTest5 = new Long("122");

        final Long minTest6 = new Long("0");
        final Long maxTest6 = new Long("1512312");

        final Long minTest7 = new Long("0");
        final Long maxTest7 = new Long("-1512312");

        final Long minTest8 = new Long("-2312");
        final Long maxTest8 = new Long("-1512312");

        final Long minTest9 = new Long("-1512312");
        final Long maxTest9 = new Long("-2312");

        final Long minTest10 = new Long("2312");
        final Long maxTest10 = new Long("1512312");

        final Long minTest11 = new Long("1512312");
        final Long maxTest11 = new Long("2312");

        final Long minTest12 = new Long("-15");
        final Long maxTest12 = new Long("2");

        final Long minTest13 = new Long("-2");
        final Long maxTest13 = new Long("15");

        Long number, number1, number2, number3, number4, number5, number6, number7, number8, number9, number10, number11, number12, number13;

        for (int i = 0; i < numberOfIteration; i++) {
            number = RandomUtil.getRandomLong(Long.MIN_VALUE, Long.MAX_VALUE, bAllowNullsTrue);
            number1 = RandomUtil.getRandomLong(minTest1, maxTest1, bAllowNullsFalse);
            number2 = RandomUtil.getRandomLong(minTest2, maxTest2, bAllowNullsFalse);
            number3 = RandomUtil.getRandomLong(minTest3, maxTest3, bAllowNullsFalse);
            number4 = RandomUtil.getRandomLong(minTest4, maxTest4, bAllowNullsFalse);
            number5 = RandomUtil.getRandomLong(minTest5, maxTest5, bAllowNullsFalse);
            number6 = RandomUtil.getRandomLong(minTest6, maxTest6, bAllowNullsFalse);
            number7 = RandomUtil.getRandomLong(minTest7, maxTest7, bAllowNullsFalse);
            number8 = RandomUtil.getRandomLong(minTest8, maxTest8, bAllowNullsFalse);
            number9 = RandomUtil.getRandomLong(minTest9, maxTest9, bAllowNullsFalse);
            number10 = RandomUtil.getRandomLong(minTest10, maxTest10, bAllowNullsFalse);
            number11 = RandomUtil.getRandomLong(minTest11, maxTest11, bAllowNullsFalse);
            number12 = RandomUtil.getRandomLong(minTest12, maxTest12, bAllowNullsFalse);
            number13 = RandomUtil.getRandomLong(minTest13, maxTest13, bAllowNullsFalse);

            if (number != null) {
                assertTrue(number.compareTo(Long.MIN_VALUE) >= 0 && number.compareTo(Long.MAX_VALUE) <= 0);
            } else {
                assertNull(number);
            }
            assertTrue(number1.compareTo(minTest1) >= 0 && number1.compareTo(maxTest1) <= 0);
            assertTrue(number2.compareTo(minTest2) >= 0 && number2.compareTo(maxTest2) <= 0);
            assertTrue(number3.compareTo(minTest3) >= 0 && number3.compareTo(maxTest3) <= 0);
            assertTrue(number4.compareTo(minTest4) >= 0 && number4.compareTo(maxTest4) <= 0);
            assertTrue(number5.compareTo(minTest5) >= 0 && number5.compareTo(maxTest5) <= 0);
            assertTrue(number6.compareTo(minTest6) >= 0 && number6.compareTo(maxTest6) <= 0);
            if (minTest7.compareTo(maxTest7) > 0) {
                assertTrue(minTest7.compareTo(number7) >= 0 && maxTest7.compareTo(number7) <= 0);
            } else {
                assertTrue(number7.compareTo(minTest7) >= 0 && number7.compareTo(maxTest7) <= 0);
            }
            if (minTest8.compareTo(maxTest8) > 0) {
                assertTrue(minTest8.compareTo(number8) >= 0 && maxTest8.compareTo(number8) <= 0);
            } else {
                assertTrue(number8.compareTo(minTest8) >= 0 && number8.compareTo(maxTest8) <= 0);
            }
            assertTrue(number9.compareTo(minTest9) >= 0 && number9.compareTo(maxTest9) <= 0);
            assertTrue(number10.compareTo(minTest10) >= 0 && number10.compareTo(maxTest10) <= 0);
            if (minTest11.compareTo(maxTest11) > 0) {
                assertTrue(minTest11.compareTo(number11) >= 0 && maxTest11.compareTo(number11) <= 0);
            } else {
                assertTrue(number11.compareTo(minTest11) >= 0 && number11.compareTo(maxTest11) <= 0);
            }
            assertTrue(number12.compareTo(minTest12) >= 0 && number12.compareTo(maxTest12) <= 0);
            assertTrue(number13.compareTo(minTest13) >= 0 && number13.compareTo(maxTest13) <= 0);
        }
    }

    @Test
    public void getRandomPrimitiveShort() throws Exception {
        final short minTest1 = -9;
        final short maxTest1 = -9;

        final short minTest2 = 0;
        final short maxTest2 = 0;

        final short minTest3 = 15;
        final short maxTest3 = 15;

        final short minTest4 = -122;
        final short maxTest4 = 123;

        final short minTest5 = -123;
        final short maxTest5 = 122;

        final short minTest6 = 0;
        final short maxTest6 = 15123;

        final short minTest7 = 0;
        final short maxTest7 = -15123;

        final short minTest8 = -2312;
        final short maxTest8 = -15123;

        final short minTest9 = -15123;
        final short maxTest9 = -2312;

        final short minTest10 = 2312;
        final short maxTest10 = 15123;

        final short minTest11 = 15123;
        final short maxTest11 = 2312;

        final short minTest12 = -15;
        final short maxTest12 = 2;

        final short minTest13 = -2;
        final short maxTest13 = 15;

        short number, number1, number2, number3, number4, number5, number6, number7, number8, number9, number10, number11, number12, number13;

        for (int i = 0; i < numberOfIteration; i++) {
            number = RandomUtil.getRandomPrimitiveShort(Short.MIN_VALUE, Short.MAX_VALUE);
            number1 = RandomUtil.getRandomPrimitiveShort(minTest1, maxTest1);
            number2 = RandomUtil.getRandomPrimitiveShort(minTest2, maxTest2);
            number3 = RandomUtil.getRandomPrimitiveShort(minTest3, maxTest3);
            number4 = RandomUtil.getRandomPrimitiveShort(minTest4, maxTest4);
            number5 = RandomUtil.getRandomPrimitiveShort(minTest5, maxTest5);
            number6 = RandomUtil.getRandomPrimitiveShort(minTest6, maxTest6);
            number7 = RandomUtil.getRandomPrimitiveShort(minTest7, maxTest7);
            number8 = RandomUtil.getRandomPrimitiveShort(minTest8, maxTest8);
            number9 = RandomUtil.getRandomPrimitiveShort(minTest9, maxTest9);
            number10 = RandomUtil.getRandomPrimitiveShort(minTest10, maxTest10);
            number11 = RandomUtil.getRandomPrimitiveShort(minTest11, maxTest11);
            number12 = RandomUtil.getRandomPrimitiveShort(minTest12, maxTest12);
            number13 = RandomUtil.getRandomPrimitiveShort(minTest13, maxTest13);

            assertTrue(number >= Short.MIN_VALUE && number <= Short.MAX_VALUE);
            assertTrue(number1 >= minTest1 && number1 <= maxTest1);
            assertTrue(number2 >= minTest2 && number2 <= maxTest2);
            assertTrue(number3 >= minTest3 && number3 <= maxTest3);
            assertTrue(number4 >= minTest4 && number4 <= maxTest4);
            assertTrue(number5 >= minTest5 && number5 <= maxTest5);
            assertTrue(number6 >= minTest6 && number6 <= maxTest6);
            if (minTest7 > maxTest7) {
                assertTrue(minTest7 >= number7 && maxTest7 <= number7);
            } else {
                assertTrue(number7 >= minTest7 && number7 <= maxTest7);
            }
            if (minTest8 > maxTest8) {
                assertTrue(minTest8 >= number8 && maxTest8 <= number8);
            } else {
                assertTrue(number8 >= minTest8 && number8 <= maxTest8);
            }
            assertTrue(number9 >= minTest9 && number9 <= maxTest9);
            assertTrue(number10 >= minTest10 && number10 <= maxTest10);
            if (minTest11 > maxTest11) {
                assertTrue(minTest11 >= number11 && maxTest11 <= number11);
            } else {
                assertTrue(number11 >= minTest11 && number11 <= maxTest11);
            }
            assertTrue(number12 >= minTest12 && number12 <= maxTest12);
            assertTrue(number13 >= minTest13 && number13 <= maxTest13);
        }
    }

    @Test
    public void getRandomShort() throws Exception {
        final Short minTest1 = new Short("-9");
        final Short maxTest1 = new Short("-9");

        final Short minTest2 = new Short("0");
        final Short maxTest2 = new Short("0");

        final Short minTest3 = new Short("15");
        final Short maxTest3 = new Short("15");

        final Short minTest4 = new Short("-122");
        final Short maxTest4 = new Short("123");

        final Short minTest5 = new Short("-123");
        final Short maxTest5 = new Short("122");

        final Short minTest6 = new Short("0");
        final Short maxTest6 = new Short("15123");

        final Short minTest7 = new Short("0");
        final Short maxTest7 = new Short("-15123");

        final Short minTest8 = new Short("-2312");
        final Short maxTest8 = new Short("-15123");

        final Short minTest9 = new Short("-15123");
        final Short maxTest9 = new Short("-2312");

        final Short minTest10 = new Short("2312");
        final Short maxTest10 = new Short("15123");

        final Short minTest11 = new Short("15123");
        final Short maxTest11 = new Short("2312");

        final Short minTest12 = new Short("-15");
        final Short maxTest12 = new Short("2");

        final Short minTest13 = new Short("-2");
        final Short maxTest13 = new Short("15");

        Short number, number1, number2, number3, number4, number5, number6, number7, number8, number9, number10, number11, number12, number13;

        for (int i = 0; i < numberOfIteration; i++) {
            number = RandomUtil.getRandomShort(Short.MIN_VALUE, Short.MAX_VALUE, bAllowNullsTrue);
            number1 = RandomUtil.getRandomShort(minTest1, maxTest1, bAllowNullsFalse);
            number2 = RandomUtil.getRandomShort(minTest2, maxTest2, bAllowNullsFalse);
            number3 = RandomUtil.getRandomShort(minTest3, maxTest3, bAllowNullsFalse);
            number4 = RandomUtil.getRandomShort(minTest4, maxTest4, bAllowNullsFalse);
            number5 = RandomUtil.getRandomShort(minTest5, maxTest5, bAllowNullsFalse);
            number6 = RandomUtil.getRandomShort(minTest6, maxTest6, bAllowNullsFalse);
            number7 = RandomUtil.getRandomShort(minTest7, maxTest7, bAllowNullsFalse);
            number8 = RandomUtil.getRandomShort(minTest8, maxTest8, bAllowNullsFalse);
            number9 = RandomUtil.getRandomShort(minTest9, maxTest9, bAllowNullsFalse);
            number10 = RandomUtil.getRandomShort(minTest10, maxTest10, bAllowNullsFalse);
            number11 = RandomUtil.getRandomShort(minTest11, maxTest11, bAllowNullsFalse);
            number12 = RandomUtil.getRandomShort(minTest12, maxTest12, bAllowNullsFalse);
            number13 = RandomUtil.getRandomShort(minTest13, maxTest13, bAllowNullsFalse);

            if (number != null) {
                assertTrue(number.compareTo(Short.MIN_VALUE) >= 0 && number.compareTo(Short.MAX_VALUE) <= 0);
            } else {
                assertNull(number);
            }
            assertTrue(number1.compareTo(minTest1) >= 0 && number1.compareTo(maxTest1) <= 0);
            assertTrue(number2.compareTo(minTest2) >= 0 && number2.compareTo(maxTest2) <= 0);
            assertTrue(number3.compareTo(minTest3) >= 0 && number3.compareTo(maxTest3) <= 0);
            assertTrue(number4.compareTo(minTest4) >= 0 && number4.compareTo(maxTest4) <= 0);
            assertTrue(number5.compareTo(minTest5) >= 0 && number5.compareTo(maxTest5) <= 0);
            assertTrue(number6.compareTo(minTest6) >= 0 && number6.compareTo(maxTest6) <= 0);
            if (minTest7.compareTo(maxTest7) > 0) {
                assertTrue(minTest7.compareTo(number7) >= 0 && maxTest7.compareTo(number7) <= 0);
            } else {
                assertTrue(number7.compareTo(minTest7) >= 0 && number7.compareTo(maxTest7) <= 0);
            }
            if (minTest8.compareTo(maxTest8) > 0) {
                assertTrue(minTest8.compareTo(number8) >= 0 && maxTest8.compareTo(number8) <= 0);
            } else {
                assertTrue(number8.compareTo(minTest8) >= 0 && number8.compareTo(maxTest8) <= 0);
            }
            assertTrue(number9.compareTo(minTest9) >= 0 && number9.compareTo(maxTest9) <= 0);
            assertTrue(number10.compareTo(minTest10) >= 0 && number10.compareTo(maxTest10) <= 0);
            if (minTest11.compareTo(maxTest11) > 0) {
                assertTrue(minTest11.compareTo(number11) >= 0 && maxTest11.compareTo(number11) <= 0);
            } else {
                assertTrue(number11.compareTo(minTest11) >= 0 && number11.compareTo(maxTest11) <= 0);
            }
            assertTrue(number12.compareTo(minTest12) >= 0 && number12.compareTo(maxTest12) <= 0);
            assertTrue(number13.compareTo(minTest13) >= 0 && number13.compareTo(maxTest13) <= 0);
        }
    }

    @Test
    public void getRandomPrimitiveByte() throws Exception {
        final byte minTest1 = -9;
        final byte maxTest1 = -9;

        final byte minTest2 = 0;
        final byte maxTest2 = 0;

        final byte minTest3 = 15;
        final byte maxTest3 = 15;

        final byte minTest4 = -122;
        final byte maxTest4 = 123;

        final byte minTest5 = -123;
        final byte maxTest5 = 122;

        final byte minTest6 = 0;
        final byte maxTest6 = 121;

        final byte minTest7 = 0;
        final byte maxTest7 = -121;

        final byte minTest8 = -89;
        final byte maxTest8 = -121;

        final byte minTest9 = -121;
        final byte maxTest9 = -89;

        final byte minTest10 = 89;
        final byte maxTest10 = 121;

        final byte minTest11 = 121;
        final byte maxTest11 = 89;

        final byte minTest12 = -15;
        final byte maxTest12 = 2;

        final byte minTest13 = -2;
        final byte maxTest13 = 15;

        byte number, number1, number2, number3, number4, number5, number6, number7, number8, number9, number10, number11, number12, number13;

        for (int i = 0; i < numberOfIteration; i++) {
            number = RandomUtil.getRandomPrimitiveByte(Byte.MIN_VALUE, Byte.MAX_VALUE);
            number1 = RandomUtil.getRandomPrimitiveByte(minTest1, maxTest1);
            number2 = RandomUtil.getRandomPrimitiveByte(minTest2, maxTest2);
            number3 = RandomUtil.getRandomPrimitiveByte(minTest3, maxTest3);
            number4 = RandomUtil.getRandomPrimitiveByte(minTest4, maxTest4);
            number5 = RandomUtil.getRandomPrimitiveByte(minTest5, maxTest5);
            number6 = RandomUtil.getRandomPrimitiveByte(minTest6, maxTest6);
            number7 = RandomUtil.getRandomPrimitiveByte(minTest7, maxTest7);
            number8 = RandomUtil.getRandomPrimitiveByte(minTest8, maxTest8);
            number9 = RandomUtil.getRandomPrimitiveByte(minTest9, maxTest9);
            number10 = RandomUtil.getRandomPrimitiveByte(minTest10, maxTest10);
            number11 = RandomUtil.getRandomPrimitiveByte(minTest11, maxTest11);
            number12 = RandomUtil.getRandomPrimitiveByte(minTest12, maxTest12);
            number13 = RandomUtil.getRandomPrimitiveByte(minTest13, maxTest13);

            assertTrue(number >= Short.MIN_VALUE && number <= Short.MAX_VALUE);
            assertTrue(number1 >= minTest1 && number1 <= maxTest1);
            assertTrue(number2 >= minTest2 && number2 <= maxTest2);
            assertTrue(number3 >= minTest3 && number3 <= maxTest3);
            assertTrue(number4 >= minTest4 && number4 <= maxTest4);
            assertTrue(number5 >= minTest5 && number5 <= maxTest5);
            assertTrue(number6 >= minTest6 && number6 <= maxTest6);
            if (minTest7 > maxTest7) {
                assertTrue(minTest7 >= number7 && maxTest7 <= number7);
            } else {
                assertTrue(number7 >= minTest7 && number7 <= maxTest7);
            }
            if (minTest8 > maxTest8) {
                assertTrue(minTest8 >= number8 && maxTest8 <= number8);
            } else {
                assertTrue(number8 >= minTest8 && number8 <= maxTest8);
            }
            assertTrue(number9 >= minTest9 && number9 <= maxTest9);
            assertTrue(number10 >= minTest10 && number10 <= maxTest10);
            if (minTest11 > maxTest11) {
                assertTrue(minTest11 >= number11 && maxTest11 <= number11);
            } else {
                assertTrue(number11 >= minTest11 && number11 <= maxTest11);
            }
            assertTrue(number12 >= minTest12 && number12 <= maxTest12);
            assertTrue(number13 >= minTest13 && number13 <= maxTest13);
        }
    }

    @Test
    public void getRandomByte() throws Exception {
        final Byte minTest1 = new Byte("-9");
        final Byte maxTest1 = new Byte("-9");

        final Byte minTest2 = new Byte("0");
        final Byte maxTest2 = new Byte("0");

        final Byte minTest3 = new Byte("15");
        final Byte maxTest3 = new Byte("15");

        final Byte minTest4 = new Byte("-122");
        final Byte maxTest4 = new Byte("123");

        final Byte minTest5 = new Byte("-123");
        final Byte maxTest5 = new Byte("122");

        final Byte minTest6 = new Byte("0");
        final Byte maxTest6 = new Byte("121");

        final Byte minTest7 = new Byte("0");
        final Byte maxTest7 = new Byte("-121");

        final Byte minTest8 = new Byte("-89");
        final Byte maxTest8 = new Byte("-121");

        final Byte minTest9 = new Byte("-121");
        final Byte maxTest9 = new Byte("-89");

        final Byte minTest10 = new Byte("89");
        final Byte maxTest10 = new Byte("121");

        final Byte minTest11 = new Byte("121");
        final Byte maxTest11 = new Byte("89");

        final Byte minTest12 = new Byte("-15");
        final Byte maxTest12 = new Byte("2");

        final Byte minTest13 = new Byte("-2");
        final Byte maxTest13 = new Byte("15");

        Byte number, number1, number2, number3, number4, number5, number6, number7, number8, number9, number10, number11, number12, number13;

        for (int i = 0; i < numberOfIteration; i++) {
            number = RandomUtil.getRandomByte(Byte.MIN_VALUE, Byte.MAX_VALUE, bAllowNullsTrue);
            number1 = RandomUtil.getRandomByte(minTest1, maxTest1, bAllowNullsFalse);
            number2 = RandomUtil.getRandomByte(minTest2, maxTest2, bAllowNullsFalse);
            number3 = RandomUtil.getRandomByte(minTest3, maxTest3, bAllowNullsFalse);
            number4 = RandomUtil.getRandomByte(minTest4, maxTest4, bAllowNullsFalse);
            number5 = RandomUtil.getRandomByte(minTest5, maxTest5, bAllowNullsFalse);
            number6 = RandomUtil.getRandomByte(minTest6, maxTest6, bAllowNullsFalse);
            number7 = RandomUtil.getRandomByte(minTest7, maxTest7, bAllowNullsFalse);
            number8 = RandomUtil.getRandomByte(minTest8, maxTest8, bAllowNullsFalse);
            number9 = RandomUtil.getRandomByte(minTest9, maxTest9, bAllowNullsFalse);
            number10 = RandomUtil.getRandomByte(minTest10, maxTest10, bAllowNullsFalse);
            number11 = RandomUtil.getRandomByte(minTest11, maxTest11, bAllowNullsFalse);
            number12 = RandomUtil.getRandomByte(minTest12, maxTest12, bAllowNullsFalse);
            number13 = RandomUtil.getRandomByte(minTest13, maxTest13, bAllowNullsFalse);
            if (number != null) {
                assertTrue(number.compareTo(Byte.MIN_VALUE) >= 0 && number.compareTo(Byte.MAX_VALUE) <= 0);
            } else {
                assertNull(number);
            }
            assertTrue(number1.compareTo(minTest1) >= 0 && number1.compareTo(maxTest1) <= 0);
            assertTrue(number2.compareTo(minTest2) >= 0 && number2.compareTo(maxTest2) <= 0);
            assertTrue(number3.compareTo(minTest3) >= 0 && number3.compareTo(maxTest3) <= 0);
            assertTrue(number4.compareTo(minTest4) >= 0 && number4.compareTo(maxTest4) <= 0);
            assertTrue(number5.compareTo(minTest5) >= 0 && number5.compareTo(maxTest5) <= 0);
            assertTrue(number6.compareTo(minTest6) >= 0 && number6.compareTo(maxTest6) <= 0);
            if (minTest7.compareTo(maxTest7) > 0) {
                assertTrue(minTest7.compareTo(number7) >= 0 && maxTest7.compareTo(number7) <= 0);
            } else {
                assertTrue(number7.compareTo(minTest7) >= 0 && number7.compareTo(maxTest7) <= 0);
            }
            if (minTest8.compareTo(maxTest8) > 0) {
                assertTrue(minTest8.compareTo(number8) >= 0 && maxTest8.compareTo(number8) <= 0);
            } else {
                assertTrue(number8.compareTo(minTest8) >= 0 && number8.compareTo(maxTest8) <= 0);
            }
            assertTrue(number9.compareTo(minTest9) >= 0 && number9.compareTo(maxTest9) <= 0);
            assertTrue(number10.compareTo(minTest10) >= 0 && number10.compareTo(maxTest10) <= 0);
            if (minTest11.compareTo(maxTest11) > 0) {
                assertTrue(minTest11.compareTo(number11) >= 0 && maxTest11.compareTo(number11) <= 0);
            } else {
                assertTrue(number11.compareTo(minTest11) >= 0 && number11.compareTo(maxTest11) <= 0);
            }
            assertTrue(number12.compareTo(minTest12) >= 0 && number12.compareTo(maxTest12) <= 0);
            assertTrue(number13.compareTo(minTest13) >= 0 && number13.compareTo(maxTest13) <= 0);
        }
    }

    @Test
    public void getRandomBigInteger() throws Exception {
        final BigInteger minTest1 = new BigInteger("-9");
        final BigInteger maxTest1 = new BigInteger("-9");

        final BigInteger minTest2 = new BigInteger("0");
        final BigInteger maxTest2 = new BigInteger("0");

        final BigInteger minTest3 = new BigInteger("15");
        final BigInteger maxTest3 = new BigInteger("15");

        final BigInteger minTest4 = new BigInteger("-122");
        final BigInteger maxTest4 = new BigInteger("123");

        final BigInteger minTest5 = new BigInteger("-123");
        final BigInteger maxTest5 = new BigInteger("122");

        final BigInteger minTest6 = new BigInteger("0");
        final BigInteger maxTest6 = new BigInteger("15123");

        final BigInteger minTest7 = new BigInteger("0");
        final BigInteger maxTest7 = new BigInteger("-15123");

        final BigInteger minTest8 = new BigInteger("-2312");
        final BigInteger maxTest8 = new BigInteger("-15123");

        final BigInteger minTest9 = new BigInteger("-15123");
        final BigInteger maxTest9 = new BigInteger("-2312");

        final BigInteger minTest10 = new BigInteger("2312");
        final BigInteger maxTest10 = new BigInteger("15123");

        final BigInteger minTest11 = new BigInteger("15123");
        final BigInteger maxTest11 = new BigInteger("2312");

        final BigInteger minTest12 = new BigInteger("-15");
        final BigInteger maxTest12 = new BigInteger("2");

        final BigInteger minTest13 = new BigInteger("-2");
        final BigInteger maxTest13 = new BigInteger("15");

        BigInteger number, number1, number2, number3, number4, number5, number6, number7, number8, number9, number10, number11, number12, number13;

        for (int i = 0; i < numberOfIteration; i++) {
            number = RandomUtil.getRandomBigInteger(Const.MIN_VALUE_BIGINTEGER, Const.MAX_VALUE_BIGINTEGER, bAllowNullsTrue);
            number1 = RandomUtil.getRandomBigInteger(minTest1, maxTest1, bAllowNullsFalse);
            number2 = RandomUtil.getRandomBigInteger(minTest2, maxTest2, bAllowNullsFalse);
            number3 = RandomUtil.getRandomBigInteger(minTest3, maxTest3, bAllowNullsFalse);
            number4 = RandomUtil.getRandomBigInteger(minTest4, maxTest4, bAllowNullsFalse);
            number5 = RandomUtil.getRandomBigInteger(minTest5, maxTest5, bAllowNullsFalse);
            number6 = RandomUtil.getRandomBigInteger(minTest6, maxTest6, bAllowNullsFalse);
            number7 = RandomUtil.getRandomBigInteger(minTest7, maxTest7, bAllowNullsFalse);
            number8 = RandomUtil.getRandomBigInteger(minTest8, maxTest8, bAllowNullsFalse);
            number9 = RandomUtil.getRandomBigInteger(minTest9, maxTest9, bAllowNullsFalse);
            number10 = RandomUtil.getRandomBigInteger(minTest10, maxTest10, bAllowNullsFalse);
            number11 = RandomUtil.getRandomBigInteger(minTest11, maxTest11, bAllowNullsFalse);
            number12 = RandomUtil.getRandomBigInteger(minTest12, maxTest12, bAllowNullsFalse);
            number13 = RandomUtil.getRandomBigInteger(minTest13, maxTest13, bAllowNullsFalse);

            if (number != null) {
                assertTrue(number.compareTo(Const.MIN_VALUE_BIGINTEGER) >= 0 && number.compareTo(Const.MAX_VALUE_BIGINTEGER) <= 0);
            } else {
                assertNull(number);
            }
            assertTrue(number1.compareTo(minTest1) >= 0 && number1.compareTo(maxTest1) <= 0);
            assertTrue(number2.compareTo(minTest2) >= 0 && number2.compareTo(maxTest2) <= 0);
            assertTrue(number3.compareTo(minTest3) >= 0 && number3.compareTo(maxTest3) <= 0);
            assertTrue(number4.compareTo(minTest4) >= 0 && number4.compareTo(maxTest4) <= 0);
            assertTrue(number5.compareTo(minTest5) >= 0 && number5.compareTo(maxTest5) <= 0);
            assertTrue(number6.compareTo(minTest6) >= 0 && number6.compareTo(maxTest6) <= 0);
            if (minTest7.compareTo(maxTest7) > 0) {
                assertTrue(minTest7.compareTo(number7) >= 0 && maxTest7.compareTo(number7) <= 0);
            } else {
                assertTrue(number7.compareTo(minTest7) >= 0 && number7.compareTo(maxTest7) <= 0);
            }
            if (minTest8.compareTo(maxTest8) > 0) {
                assertTrue(minTest8.compareTo(number8) >= 0 && maxTest8.compareTo(number8) <= 0);
            } else {
                assertTrue(number8.compareTo(minTest8) >= 0 && number8.compareTo(maxTest8) <= 0);
            }
            assertTrue(number9.compareTo(minTest9) >= 0 && number9.compareTo(maxTest9) <= 0);
            assertTrue(number10.compareTo(minTest10) >= 0 && number10.compareTo(maxTest10) <= 0);
            if (minTest11.compareTo(maxTest11) > 0) {
                assertTrue(minTest11.compareTo(number11) >= 0 && maxTest11.compareTo(number11) <= 0);
            } else {
                assertTrue(number11.compareTo(minTest11) >= 0 && number11.compareTo(maxTest11) <= 0);
            }
            assertTrue(number12.compareTo(minTest12) >= 0 && number12.compareTo(maxTest12) <= 0);
            assertTrue(number13.compareTo(minTest13) >= 0 && number13.compareTo(maxTest13) <= 0);
        }
    }

    @Test
    public void getRandomBigDecimal() throws Exception {
        final BigDecimal minTest1 = new BigDecimal("-9.99999");
        final BigDecimal maxTest1 = new BigDecimal("-9.99999");

        final BigDecimal minTest2 = new BigDecimal("0");
        final BigDecimal maxTest2 = new BigDecimal("0");

        final BigDecimal minTest3 = new BigDecimal("15.123");
        final BigDecimal maxTest3 = new BigDecimal("15.123");

        final BigDecimal minTest4 = new BigDecimal("-122123");
        final BigDecimal maxTest4 = new BigDecimal("123123");

        final BigDecimal minTest5 = new BigDecimal("-123.123");
        final BigDecimal maxTest5 = new BigDecimal("122.122");

        final BigDecimal minTest6 = new BigDecimal("0");
        final BigDecimal maxTest6 = new BigDecimal("15123.123");

        final BigDecimal minTest7 = new BigDecimal("0");
        final BigDecimal maxTest7 = new BigDecimal("-15123.123");

        final BigDecimal minTest8 = new BigDecimal("-2312.122");
        final BigDecimal maxTest8 = new BigDecimal("-15123.123");

        final BigDecimal minTest9 = new BigDecimal("-15123.123");
        final BigDecimal maxTest9 = new BigDecimal("-2312.122");

        final BigDecimal minTest10 = new BigDecimal("2312.122");
        final BigDecimal maxTest10 = new BigDecimal("15123.123");

        final BigDecimal minTest11 = new BigDecimal("15123.123");
        final BigDecimal maxTest11 = new BigDecimal("2312.122");

        final BigDecimal minTest12 = new BigDecimal("-15.123");
        final BigDecimal maxTest12 = new BigDecimal("2.122");

        final BigDecimal minTest13 = new BigDecimal("-2.122");
        final BigDecimal maxTest13 = new BigDecimal("15.123");

        BigDecimal number, number1, number2, number3, number4, number5, number6, number7, number8, number9, number10, number11, number12, number13;
        for (int i = 0; i < numberOfIteration; i++) {
            number = RandomUtil.getRandomBigDecimal(Const.MIN_VALUE_BIGDECIMAL, Const.MAX_VALUE_BIGDECIMAL, Const.PRECISION_FIVE, bAllowNullsTrue);
            number1 = RandomUtil.getRandomBigDecimal(minTest1, maxTest1, Const.PRECISION_FIVE, bAllowNullsFalse);
            number2 = RandomUtil.getRandomBigDecimal(minTest2, maxTest2, Const.PRECISION_FIVE, bAllowNullsFalse);
            number3 = RandomUtil.getRandomBigDecimal(minTest3, maxTest3, Const.PRECISION_FIVE, bAllowNullsFalse);
            number4 = RandomUtil.getRandomBigDecimal(minTest4, maxTest4, Const.PRECISION_FIVE, bAllowNullsFalse);
            number5 = RandomUtil.getRandomBigDecimal(minTest5, maxTest5, Const.PRECISION_FIVE, bAllowNullsFalse);
            number6 = RandomUtil.getRandomBigDecimal(minTest6, maxTest6, Const.PRECISION_FIVE, bAllowNullsFalse);
            number7 = RandomUtil.getRandomBigDecimal(minTest7, maxTest7, Const.PRECISION_FIVE, bAllowNullsFalse);
            number8 = RandomUtil.getRandomBigDecimal(minTest8, maxTest8, Const.PRECISION_FIVE, bAllowNullsFalse);
            number9 = RandomUtil.getRandomBigDecimal(minTest9, maxTest9, Const.PRECISION_FIVE, bAllowNullsFalse);
            number10 = RandomUtil.getRandomBigDecimal(minTest10, maxTest10, Const.PRECISION_FIVE, bAllowNullsFalse);
            number11 = RandomUtil.getRandomBigDecimal(minTest11, maxTest11, Const.PRECISION_FIVE, bAllowNullsFalse);
            number12 = RandomUtil.getRandomBigDecimal(minTest12, maxTest12, Const.PRECISION_FIVE, bAllowNullsFalse);
            number13 = RandomUtil.getRandomBigDecimal(minTest13, maxTest13, Const.PRECISION_FIVE, bAllowNullsFalse);

            if (number != null) {
                assertTrue(number.compareTo(Const.MIN_VALUE_BIGDECIMAL) >= 0 && number.compareTo(Const.MAX_VALUE_BIGDECIMAL) <= 0);
            } else {
                assertNull(number);
            }
            assertTrue(number1.compareTo(minTest1) >= 0 && number1.compareTo(maxTest1) <= 0);
            assertTrue(number2.compareTo(minTest2) >= 0 && number2.compareTo(maxTest2) <= 0);
            assertTrue(number3.compareTo(minTest3) >= 0 && number3.compareTo(maxTest3) <= 0);
            assertTrue(number4.compareTo(minTest4) >= 0 && number4.compareTo(maxTest4) <= 0);
            assertTrue(number5.compareTo(minTest5) >= 0 && number5.compareTo(maxTest5) <= 0);
            assertTrue(number6.compareTo(minTest6) >= 0 && number6.compareTo(maxTest6) <= 0);
            if (minTest7.compareTo(maxTest7) > 0) {
                assertTrue(minTest7.compareTo(number7) >= 0 && maxTest7.compareTo(number7) <= 0);
            } else {
                assertTrue(number7.compareTo(minTest7) >= 0 && number7.compareTo(maxTest7) <= 0);
            }
            if (minTest8.compareTo(maxTest8) > 0) {
                assertTrue(minTest8.compareTo(number8) >= 0 && maxTest8.compareTo(number8) <= 0);
            } else {
                assertTrue(number8.compareTo(minTest8) >= 0 && number8.compareTo(maxTest8) <= 0);
            }
            assertTrue(number9.compareTo(minTest9) >= 0 && number9.compareTo(maxTest9) <= 0);
            assertTrue(number10.compareTo(minTest10) >= 0 && number10.compareTo(maxTest10) <= 0);
            if (minTest11.compareTo(maxTest11) > 0) {
                assertTrue(minTest11.compareTo(number11) >= 0 && maxTest11.compareTo(number11) <= 0);
            } else {
                assertTrue(number11.compareTo(minTest11) >= 0 && number11.compareTo(maxTest11) <= 0);
            }
            assertTrue(number12.compareTo(minTest12) >= 0 && number12.compareTo(maxTest12) <= 0);
            assertTrue(number13.compareTo(minTest13) >= 0 && number13.compareTo(maxTest13) <= 0);
        }
    }

    @Test
    public void getRandomUUID() throws Exception {
        for (int i = 0; i < numberOfIteration; i++) {
            assertThat(RandomUtil.getRandomUUID(bAllowNullsTrue), anyOf(isA(String.class), nullValue()));
            assertThat(RandomUtil.getRandomUUID(bAllowNullsFalse), anyOf(isA(String.class)));
        }
    }

    @Test
    public void getRandomDate() throws Exception {

        final DateTimeFormatter format = DateTimeFormat.forPattern(DateUtil.sDateFormat);
        final Date minTest1 = format.parseDateTime("2011/01/01 20:27:05").toDate();
        final Date maxTest1 = format.parseDateTime("2011/01/01 20:27:05").toDate();

        final Date minTest2 = format.parseDateTime("2016/05/24 20:25:05").toDate();
        final Date maxTest2 = format.parseDateTime("2016/05/24 20:27:05").toDate();

        final Date minTest3 = format.parseDateTime("2016/05/24 20:27:05").toDate();
        final Date maxTest3 = format.parseDateTime("2016/05/24 20:25:05").toDate();

        final Date minTest4 = format.parseDateTime("2011/01/01 20:27:05").toDate();
        final Date maxTest4 = format.parseDateTime("2014/01/01 20:27:05").toDate();

        final Date minTest5 = format.parseDateTime("2011/01/01 20:27:05").toDate();
        final Date maxTest5 = format.parseDateTime("2014/05/05 20:27:05").toDate();

        final Date minTest6 = format.parseDateTime("2011/05/05 23:59:05").toDate();
        final Date maxTest6 = format.parseDateTime("2011/01/01 20:27:05").toDate();

        final Date minTest7 = format.parseDateTime("2014/01/01 20:27:05").toDate();
        final Date maxTest7 = format.parseDateTime("2011/01/01 20:27:05").toDate();

        final Date minTest8 = format.parseDateTime("2011/01/01 20:27:05").toDate();
        final Date maxTest8 = format.parseDateTime("2011/01/02 20:27:05").toDate();

        final Date minTest9 = format.parseDateTime("2011/01/02 20:27:05").toDate();
        final Date maxTest9 = format.parseDateTime("2011/01/01 20:27:05").toDate();

        final Date dateMinimum = new Date(0L);
        final Date dateMaximum = new Date(Long.MAX_VALUE);

        Date date, date1, date2, date3, date4, date5, date6, date7, date8, date9;

        for (int i = 0; i < numberOfIteration; i++) {
            date = RandomUtil.getRandomDate(dateMinimum, dateMaximum, bAllowNullsTrue);
            date1 = RandomUtil.getRandomDate(minTest1, maxTest1, bAllowNullsFalse);
            date2 = RandomUtil.getRandomDate(minTest2, maxTest2, bAllowNullsFalse);
            date3 = RandomUtil.getRandomDate(minTest3, maxTest3, bAllowNullsFalse);
            date4 = RandomUtil.getRandomDate(minTest4, maxTest4, bAllowNullsFalse);
            date5 = RandomUtil.getRandomDate(minTest5, maxTest5, bAllowNullsFalse);
            date6 = RandomUtil.getRandomDate(minTest6, maxTest6, bAllowNullsFalse);
            date7 = RandomUtil.getRandomDate(minTest7, maxTest7, bAllowNullsFalse);
            date8 = RandomUtil.getRandomDate(minTest8, maxTest8, bAllowNullsFalse);
            date9 = RandomUtil.getRandomDate(minTest9, maxTest9, bAllowNullsFalse);

            if (date != null) {
                assertTrue(date.compareTo(dateMinimum) >= 0 && date.compareTo(dateMaximum) <= 0);
            } else {
                assertNull(date);
            }
            assertTrue(date1.compareTo(minTest1) >= 0 && date1.compareTo(maxTest1) <= 0);
            assertTrue(date2.compareTo(minTest2) >= 0 && date2.compareTo(maxTest2) <= 0);
            if (minTest3.compareTo(maxTest3) > 0) {
                assertTrue(minTest3.compareTo(date3) >= 0 && maxTest3.compareTo(date3) <= 0);
            } else {
                assertTrue(date3.compareTo(minTest3) >= 0 && date3.compareTo(maxTest3) <= 0);
            }
            assertTrue(date4.compareTo(minTest4) >= 0 && date4.compareTo(maxTest4) <= 0);
            assertTrue(date5.compareTo(minTest5) >= 0 && date5.compareTo(maxTest5) <= 0);
            if (minTest6.compareTo(maxTest6) > 0) {
                assertTrue(minTest6.compareTo(date6) >= 0 && maxTest6.compareTo(date6) <= 0);
            } else {
                assertTrue(date6.compareTo(minTest6) >= 0 && date6.compareTo(maxTest6) <= 0);
            }
            if (minTest7.compareTo(maxTest7) > 0) {
                assertTrue(minTest7.compareTo(date7) >= 0 && maxTest7.compareTo(date7) <= 0);
            } else {
                assertTrue(date7.compareTo(minTest7) >= 0 && date7.compareTo(maxTest7) <= 0);
            }
            assertTrue(date8.compareTo(minTest8) >= 0 && date8.compareTo(maxTest8) <= 0);
            if (minTest9.compareTo(maxTest9) > 0) {
                assertTrue(minTest9.compareTo(date9) >= 0 && maxTest9.compareTo(date9) <= 0);
            } else {
                assertTrue(date9.compareTo(minTest9) >= 0 && date9.compareTo(maxTest9) <= 0);
            }
        }
    }

    @Test
    public void getRandomChar() throws Exception {
        char randomChar;
        for (int i = 0; i < numberOfIteration; i++) {
            randomChar = RandomUtil.getRandomChar();
            assertTrue(randomChar >= Const.ZERO && randomChar <= Const.MAX_CHAR);
        }
    }

    @Test
    public void getRandomChar1() throws Exception {

        final int minTest1 = -9;
        final int maxTest1 = -9;

        final int minTest2 = 0;
        final int maxTest2 = 0;

        final int minTest3 = 15;
        final int maxTest3 = 15;

        final int minTest4 = -122;
        final int maxTest4 = 123;

        final int minTest5 = -123;
        final int maxTest5 = 122;

        final int minTest6 = 0;
        final int maxTest6 = 121;

        final int minTest7 = 0;
        final int maxTest7 = -121;

        final int minTest8 = -89;
        final int maxTest8 = -121;

        final int minTest9 = -121;
        final int maxTest9 = -89;

        final int minTest10 = 89;
        final int maxTest10 = 121;

        final int minTest11 = 121;
        final int maxTest11 = 89;

        final int minTest12 = -15;
        final int maxTest12 = 2;

        final int minTest13 = -2;
        final int maxTest13 = 15;

        char number, number1, number2, number3, number4, number5, number6, number7, number8, number9, number10, number11, number12, number13;

        for (int i = 0; i < numberOfIteration; i++) {
            number = RandomUtil.getRandomChar(Byte.MIN_VALUE, Byte.MAX_VALUE);
            number1 = RandomUtil.getRandomChar(minTest1, maxTest1);
            number2 = RandomUtil.getRandomChar(minTest2, maxTest2);
            number3 = RandomUtil.getRandomChar(minTest3, maxTest3);
            number4 = RandomUtil.getRandomChar(minTest4, maxTest4);
            number5 = RandomUtil.getRandomChar(minTest5, maxTest5);
            number6 = RandomUtil.getRandomChar(minTest6, maxTest6);
            number7 = RandomUtil.getRandomChar(minTest7, maxTest7);
            number8 = RandomUtil.getRandomChar(minTest8, maxTest8);
            number9 = RandomUtil.getRandomChar(minTest9, maxTest9);
            number10 = RandomUtil.getRandomChar(minTest10, maxTest10);
            number11 = RandomUtil.getRandomChar(minTest11, maxTest11);
            number12 = RandomUtil.getRandomChar(minTest12, maxTest12);
            number13 = RandomUtil.getRandomChar(minTest13, maxTest13);

            assertTrue((byte) number >= Byte.MIN_VALUE && (byte) number <= Byte.MAX_VALUE);
            assertTrue((byte) number1 >= minTest1 && (byte) number1 <= maxTest1);
            assertTrue((byte) number2 >= minTest2 && (byte) number2 <= maxTest2);
            assertTrue((byte) number3 >= minTest3 && (byte) number3 <= maxTest3);
            assertTrue((byte) number4 >= minTest4 && (byte) number4 <= maxTest4);
            assertTrue((byte) number5 >= minTest5 && (byte) number5 <= maxTest5);
            assertTrue((byte) number6 >= minTest6 && (byte) number6 <= maxTest6);
            if (minTest7 > maxTest7) {
                assertTrue(minTest7 >= (byte) number7 && maxTest7 <= (byte) number7);
            } else {
                assertTrue((byte) number7 >= minTest7 && (byte) number7 <= maxTest7);
            }
            if (minTest8 > maxTest8) {
                assertTrue(minTest8 >= (byte) number8 && maxTest8 <= (byte) number8);
            } else {
                assertTrue((byte) number8 >= minTest8 && (byte) number8 <= maxTest8);
            }
            assertTrue((byte) number9 >= minTest9 && (byte) number9 <= maxTest9);
            assertTrue((byte) number10 >= minTest10 && (byte) number10 <= maxTest10);
            if (minTest11 > maxTest11) {
                assertTrue(minTest11 >= (byte) number11 && maxTest11 <= (byte) number11);
            } else {
                assertTrue((byte) number11 >= minTest11 && (byte) number11 <= maxTest11);
            }
            assertTrue((byte) number12 >= minTest12 && (byte) number12 <= maxTest12);
            assertTrue((byte) number13 >= minTest13 && (byte) number13 <= maxTest13);
        }
    }

    @Test
    public void getRandomNames() throws Exception {
        String sName, sFirstName, sLastname, sMiddleName, sEmail;
        for (int i = 0; i < numberOfIteration; i++) {
            assertThat(RandomUtil.getRandomNames(Const.NAME, bAllowNullsTrue), anyOf(isA(String.class), nullValue()));
            assertThat(RandomUtil.getRandomNames(Const.FIRST_NAME, bAllowNullsTrue), anyOf(isA(String.class), nullValue()));
            assertThat(RandomUtil.getRandomNames(Const.LAST_NAME, bAllowNullsTrue), anyOf(isA(String.class), nullValue()));
            assertThat(RandomUtil.getRandomNames(Const.MIDDLE_NAME, bAllowNullsTrue), anyOf(isA(String.class), nullValue()));
            assertThat(RandomUtil.getRandomNames(Const.EMAIL, bAllowNullsTrue), anyOf(isA(String.class), nullValue()));

            sName = RandomUtil.getRandomNames(Const.NAME, bAllowNullsFalse);
            sFirstName = RandomUtil.getRandomNames(Const.FIRST_NAME, bAllowNullsFalse);
            sLastname = RandomUtil.getRandomNames(Const.LAST_NAME, bAllowNullsFalse);
            sMiddleName = RandomUtil.getRandomNames(Const.MIDDLE_NAME, bAllowNullsFalse);
            sEmail = RandomUtil.getRandomNames(Const.EMAIL, bAllowNullsFalse);

            assertTrue(sName.replaceAll(RegularExpUtil.WHITE_SPACE, Const.STRING_EMPTY).length() == Const.TWO);
            assertTrue(sFirstName.replaceAll(RegularExpUtil.WHITE_SPACE, Const.STRING_EMPTY).length() == Const.ZERO);
            assertTrue(sLastname.replaceAll(RegularExpUtil.WHITE_SPACE, Const.STRING_EMPTY).length() == Const.ZERO);
            assertTrue(sMiddleName.replaceAll(RegularExpUtil.WHITE_SPACE, Const.STRING_EMPTY).length() == Const.ZERO && sEmail.contains(Const.STRING_EMAIL_AT));
        }
    }

    @Test
    public void getRandomEnum() throws Exception {
        String[] enumValues = new String[]{"MALE", "FEMALE"};
        String[] enumValues1 = new String[]{"MALE", "MALE"};
        String[] enumValues2 = new String[]{"MALE"};
        for (int i = 0; i < numberOfIteration; i++) {
            assertThat(RandomUtil.getRandomEnum(Const.ZERO, Gender.class.getEnumConstants().length - Const.ONE, null, Gender.class), instanceOf(Gender.class));
            assertThat(RandomUtil.getRandomEnum(Const.MINUS_ONE, Const.MINUS_ONE, enumValues2, Gender.class), instanceOf(Gender.class));
            assertThat(RandomUtil.getRandomEnum(Const.ZERO, Gender.class.getEnumConstants().length - Const.ONE, enumValues, Gender1.class), instanceOf(Gender1.class));
            assertThat(RandomUtil.getRandomEnum(Const.ZERO, Gender.class.getEnumConstants().length - Const.ONE, enumValues1, Gender1.class), instanceOf(Gender1.class));
        }
    }
}