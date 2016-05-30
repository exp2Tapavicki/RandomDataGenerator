package com.afowd.util;

import com.afowd.constants.BasicClassConstants;
import com.afowd.constants.Const;
import org.apache.log4j.Logger;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.*;

public class RandomUtil {
    public static final List<String> listOfBasicTypes = Arrays.asList(Enum.class.getName(), BigInteger.class.getName(), BigDecimal.class.getName(), Integer.class.getName(), Long.class.getName(), Short.class.getName(), Byte.class.getName(), Boolean.class.getName(), Double.class.getName(), Float.class.getName(),
            String.class.getName(), Date.class.getName(), int.class.getName(), long.class.getName(), short.class.getName(), byte.class.getName(), boolean.class.getName(), double.class.getName(), float.class.getName(), char.class.getName(),
            Character.class.getName(), BigDecimal.class.getName(), BigInteger.class.getName());
    public static final List<String> listOfHashTypes = Arrays.asList(HashMap.class.getName(), HashSet.class.getName(), TreeMap.class.getName(), Hashtable.class.getName());

    public static final List<String> listOfArrayTypes = Arrays.asList(ArrayList.class.getName(), Collections.class.getName(), List.class.getName());
    public static final Map<String, String> mMappingMap = new HashMap();
    private static final Random random = new Random();
    private final static Logger logger = Logger.getLogger(RandomUtil.class);

    static {
        mMappingMap.put(BasicClassConstants.sPrimitiveInt, Integer.class.getName());
        mMappingMap.put(BasicClassConstants.sPrimitiveLong, Long.class.getName());
        mMappingMap.put(BasicClassConstants.sPrimitiveDouble, Double.class.getName());
        mMappingMap.put(BasicClassConstants.sPrimitiveFloat, Float.class.getName());
        mMappingMap.put(BasicClassConstants.sPrimitiveBoolean, Boolean.class.getName());
        mMappingMap.put(BasicClassConstants.sPrimitiveChar, Character.class.getName());
        mMappingMap.put(BasicClassConstants.sPrimitiveByte, Byte.class.getName());
        mMappingMap.put(Const.VOID, Void.class.getName());
        mMappingMap.put(BasicClassConstants.sPrimitiveShort, Short.class.getName());
    }

    private static final DateTimeFormatter format = DateTimeFormat.forPattern(DateUtil.sDateFormat);
    private static final SimpleDateFormat sdf = new SimpleDateFormat(DateUtil.sDateFormat);

    /**
     * return random Boolean or null
     * @param bAllowNulls
     * @return Boolean or null
     */
    public static Boolean getRandomBoolean(boolean bAllowNulls) {
        if (bAllowNulls && getRandomValueOrNull()) {
            return null;
        }
        return random.nextBoolean();
    }

    /**
     * return random primitive type float for min, max Range
     *
     * @param minRange
     * @param maxRange
     * @param precision
     * @return float
     */
    public static float getRandomPrimitiveFloat(float minRange, float maxRange, int precision) {
        if (minRange == maxRange) {
            return minRange;
        } else {
            float value = Float.valueOf(String.valueOf(minRange + (Math.random() * (maxRange - minRange + Const.ONE))));
            BigDecimal bgValue = new BigDecimal(value);
            BigDecimal bgMinRange = new BigDecimal(String.valueOf(minRange));
            BigDecimal bgMaxRange = new BigDecimal(String.valueOf(maxRange));
            bgValue = bgValue.setScale(precision, RoundingMode.HALF_UP);
            if (minRange < maxRange) {
                if (bgValue.compareTo(bgMinRange) < 0) {
                    return minRange;
                } else if (bgValue.compareTo(bgMaxRange) > 0) {
                    return maxRange;
                }
            } else {
                if (bgValue.compareTo(bgMaxRange) < 0) {
                    return minRange;
                } else if (bgValue.compareTo(bgMinRange) > 0) {
                    return maxRange;
                }
            }
            if ((minRange < 0 || maxRange < 0) && !getPositiveOrNegative() && value * Const.MINUS_ONE >= minRange && value * Const.MINUS_ONE <= maxRange) {
                return bgValue.floatValue() * Const.MINUS_ONE;
            } else {
                return bgValue.floatValue();
            }
        }
    }

    /**
     * return random Float or null
     *
     * @param minRange
     * @param maxRange
     * @param precision
     * @param bAllowNulls
     * @return Float or null
     */
    public static Float getRandomFloat(Float minRange, Float maxRange, int precision, boolean bAllowNulls) {
        if (bAllowNulls && getRandomValueOrNull()) {
            return null;
        }
        if (minRange.compareTo(maxRange) == 0) {
            return minRange;
        } else {
            Float value = Float.valueOf(minRange + (float) (Math.random() * (maxRange - minRange + Const.ONE)));
            BigDecimal bgValue = new BigDecimal(value);
            BigDecimal bgMinRange = new BigDecimal(String.valueOf(minRange));
            BigDecimal bgMaxRange = new BigDecimal(String.valueOf(maxRange));
            bgValue = bgValue.setScale(precision, RoundingMode.HALF_UP);
            if (minRange.compareTo(maxRange) < 0) {
                if (bgValue.compareTo(bgMinRange) < 0) {
                    return minRange;
                } else if (bgValue.compareTo(bgMaxRange) > 0) {
                    return maxRange;
                }
            } else {
                if (bgValue.compareTo(bgMaxRange) < 0) {
                    return minRange;
                } else if (bgValue.compareTo(bgMinRange) > 0) {
                    return maxRange;
                }
            }
            if ((minRange.compareTo(Float.valueOf(0)) < 0 || maxRange.compareTo(Float.valueOf(0)) < 0) && !getPositiveOrNegative() && ((Float) (value * Const.MINUS_ONE)).compareTo(minRange) >= 0 && ((Float) (value * Const.MINUS_ONE)).compareTo(maxRange) <= 0) {
                return bgValue.floatValue() * Const.MINUS_ONE;
            } else {
                return bgValue.floatValue();
            }
        }
    }

    /**
     * return random primitive type double
     *
     * @param minRange
     * @param maxRange
     * @param  precision
     * @return double
     */
    public static double getRandomPrimitiveDouble(double minRange, double maxRange, int precision) {
        if (minRange == maxRange) {
            return minRange;
        } else {
            double value = Double.valueOf(minRange + (Math.random() * (maxRange - minRange + Const.ONE)));
            BigDecimal bgValue = new BigDecimal(value);
            BigDecimal bgMinRange = new BigDecimal(minRange);
            BigDecimal bgMaxRange = new BigDecimal(maxRange);
            bgValue = bgValue.setScale(precision, RoundingMode.HALF_UP);
            if (minRange < maxRange) {
                if (bgValue.compareTo(bgMinRange) < 0) {
                    return minRange;
                } else if (bgValue.compareTo(bgMaxRange) > 0) {
                    return maxRange;
                }
            } else {
                if (bgValue.compareTo(bgMaxRange) < 0) {
                    return minRange;
                } else if (bgValue.compareTo(bgMinRange) > 0) {
                    return maxRange;
                }
            }
            if ((minRange < 0 || maxRange < 0) && !getPositiveOrNegative() && value * Const.MINUS_ONE >= minRange && value * Const.MINUS_ONE <= maxRange) {
                return bgValue.doubleValue() * Const.MINUS_ONE;
            } else {
                return bgValue.doubleValue();
            }
        }
    }

    /**
     * return random Double or null
     *
     * @param minRange
     * @param maxRange
     * @param precision
     * @param bAllowNulls
     * @return Double or null
     */
    public static Double getRandomDouble(Double minRange, Double maxRange, int precision, boolean bAllowNulls) {
        if (bAllowNulls && getRandomValueOrNull()) {
            return null;
        }
        if (minRange.compareTo(maxRange) == 0) {
            return minRange;
        } else {
            Double value = Double.valueOf(minRange + (Math.random() * (maxRange - minRange + Const.ONE)));
            BigDecimal bgValue = new BigDecimal(value);
            BigDecimal bgMinRange = new BigDecimal(minRange);
            BigDecimal bgMaxRange = new BigDecimal(maxRange);
            bgValue = bgValue.setScale(precision, RoundingMode.HALF_UP);
            if (minRange.compareTo(maxRange) < 0) {
                if (bgValue.compareTo(bgMinRange) < 0) {
                    return minRange;
                } else if (bgValue.compareTo(bgMaxRange) > 0) {
                    return maxRange;
                }
            } else {
                if (bgValue.compareTo(bgMaxRange) < 0) {
                    return minRange;
                } else if (bgValue.compareTo(bgMinRange) > 0) {
                    return maxRange;
                }
            }
            if ((minRange.compareTo(Double.valueOf(0)) < 0 || maxRange.compareTo(Double.valueOf(0)) < 0) && !getPositiveOrNegative() && ((Double) (value * Const.MINUS_ONE)).compareTo(minRange) >= 0 && ((Double) (value * Const.MINUS_ONE)).compareTo(maxRange) <= 0) {
                return bgValue.doubleValue() * Const.MINUS_ONE;
            } else {
                return bgValue.doubleValue();
            }
        }
    }

    /**
     * return random primitive type int
     *
     * @param minRange
     * @param maxRange
     * @return int
     */
    public static int getRandomPrimitiveInt(int minRange, int maxRange) {
        if (minRange == maxRange) {
            return minRange;
        } else {
            int value = Integer.valueOf(minRange + (int) (Math.random() * (maxRange - minRange + Const.ONE)));
            if (minRange < maxRange) {
                if (value < minRange) {
                    return minRange;
                } else if (value > maxRange) {
                    return maxRange;
                }
            } else {
                if (value < maxRange) {
                    return minRange;
                } else if (value > minRange) {
                    return maxRange;
                }
            }
            if ((minRange < 0 || maxRange < 0) && !getPositiveOrNegative() && value * Const.MINUS_ONE >= minRange && value * Const.MINUS_ONE <= maxRange) {
                return value * Const.MINUS_ONE;
            } else {
                return value;
            }
        }
    }

    /**
     * get random positive or negative number
     * @return boolean
     */
    private static boolean getPositiveOrNegative() {
        int lRandomNumber = (int) (Math.random() * Const.THREE);
        return lRandomNumber % Const.TWO == Const.ZERO;
    }

    /**
     * return random Integer or null
     *
     * @param minRange
     * @param maxRange
     * @param bAllowNulls
     * @return Integer or null
     */
    public static Integer getRandomInteger(Integer minRange, Integer maxRange, boolean bAllowNulls) {
        if (bAllowNulls && getRandomValueOrNull()) {
            return null;
        }
        if (minRange.intValue() == maxRange.intValue()) {
            return minRange;
        } else {
            Integer value = Integer.valueOf((minRange + (int) (Math.random() * (maxRange - minRange + Const.ONE))));
            if (minRange.intValue() < maxRange.intValue()) {
                if (value.intValue() < minRange.intValue()) {
                    return minRange;
                } else if (value.intValue() > maxRange.intValue()) {
                    return maxRange;
                }
            } else {
                if (value.intValue() < maxRange.intValue()) {
                    return minRange;
                } else if (value.intValue() > minRange.intValue()) {
                    return maxRange;
                }
            }
            if ((minRange.intValue() < 0 || maxRange.intValue() < 0) && !getPositiveOrNegative() && value.intValue() * Const.MINUS_ONE >= minRange.intValue() && value.intValue() * Const.MINUS_ONE <= maxRange.intValue()) {
                return value * Const.MINUS_ONE;
            } else {
                return value;
            }
        }
    }

    /**
     * return random primitive type long
     *
     * @param minRange
     * @param maxRange
     * @return long
     */
    public static long getRandomPrimitiveLong(long minRange, long maxRange) {
        if (minRange == maxRange) {
            return minRange;
        } else {
            long value = Long.valueOf(minRange + (long) (Math.random() * (maxRange - minRange + Const.ONE)));
            if (minRange < maxRange) {
                if (value < minRange) {
                    return minRange;
                } else if (value > maxRange) {
                    return maxRange;
                }
            } else {
                if (value < maxRange) {
                    return minRange;
                } else if (value > minRange) {
                    return maxRange;
                }
            }
            if ((minRange < 0 || maxRange < 0) && !getPositiveOrNegative() && value * Const.MINUS_ONE >= minRange && value * Const.MINUS_ONE <= maxRange) {
                return value * Const.MINUS_ONE;
            } else {
                return value;
            }
        }
    }

    /**
     * return random Long or null
     *
     * @param minRange
     * @param maxRange
     * @param bAllowNulls
     * @return Long or null
     */
    public static Long getRandomLong(Long minRange, Long maxRange, boolean bAllowNulls) {
        if (bAllowNulls && getRandomValueOrNull()) {
            return null;
        }
        if (minRange.longValue() == maxRange.longValue()) {
            return minRange;
        } else {
            Long value = Long.valueOf((minRange + (long) (Math.random() * (maxRange - minRange + Const.ONE))));
            if (minRange.longValue() < maxRange.longValue()) {
                if (value.longValue() < minRange.longValue()) {
                    return minRange;
                } else if (value.longValue() > maxRange.longValue()) {
                    return maxRange;
                }
            } else {
                if (value.longValue() < maxRange.longValue()) {
                    return minRange;
                } else if (value.longValue() > minRange.longValue()) {
                    return maxRange;
                }
            }
            if ((minRange.longValue() < 0 || maxRange.longValue() < 0) && !getPositiveOrNegative() && value.longValue() * Const.MINUS_ONE >= minRange.longValue() && value.longValue() * Const.MINUS_ONE <= maxRange.longValue()) {
                return value * Const.MINUS_ONE;
            } else {
                return value;
            }
        }
    }

    /**
     * return random primitive type short
     *
     * @param minRange
     * @param maxRange
     * @return short
     */
    public static short getRandomPrimitiveShort(short minRange, short maxRange) {
        if (minRange == maxRange) {
            return minRange;
        } else {
            short value = Short.valueOf((short) (minRange + (short) (Math.random() * (maxRange - minRange + Const.ONE))));
            if (minRange < maxRange) {
                if (value < minRange) {
                    return minRange;
                } else if (value > maxRange) {
                    return maxRange;
                }
            } else {
                if (value < maxRange) {
                    return minRange;
                } else if (value > minRange) {
                    return maxRange;
                }
            }
            if ((minRange < 0 || maxRange < 0) && !getPositiveOrNegative() && value * Const.MINUS_ONE >= minRange && value * Const.MINUS_ONE <= maxRange) {
                return (short) (value * Const.MINUS_ONE);
            } else {
                return value;
            }
        }
    }

    /**
     * return random Short or null
     *
     * @param minRange
     * @param maxRange
     * @param bAllowNulls
     * @return Short or null
     */
    public static Short getRandomShort(Short minRange, Short maxRange, boolean bAllowNulls) {
        if (bAllowNulls && getRandomValueOrNull()) {
            return null;
        }
        if (minRange.shortValue() == maxRange.shortValue()) {
            return minRange;
        } else {
            Short value = Short.valueOf((short) (minRange + (short) (Math.random() * (maxRange - minRange + Const.ONE))));
            if (minRange.shortValue() < maxRange.shortValue()) {
                if (value.shortValue() < minRange.shortValue()) {
                    return minRange;
                } else if (value.shortValue() > maxRange.shortValue()) {
                    return maxRange;
                }
            } else {
                if (value.shortValue() < maxRange.shortValue()) {
                    return minRange;
                } else if (value.shortValue() > minRange.shortValue()) {
                    return maxRange;
                }
            }
            if ((minRange.shortValue() < 0 || maxRange.shortValue() < 0) && !getPositiveOrNegative() && value.shortValue() * Const.MINUS_ONE >= minRange.shortValue() && value.shortValue() * Const.MINUS_ONE <= maxRange.shortValue()) {
                return Short.valueOf((short) (value * Const.MINUS_ONE));
            } else {
                return value;
            }
        }
    }

    /**
     * return random primitive type byte
     *
     * @param minRange
     * @param maxRange
     * @return byte
     */
    public static byte getRandomPrimitiveByte(byte minRange, byte maxRange) {
        if (minRange == maxRange) {
            return minRange;
        } else {
            byte value = Byte.valueOf((byte) (minRange + (byte) (Math.random() * (maxRange - minRange + Const.ONE))));
            if (minRange < maxRange) {
                if (value < minRange) {
                    return minRange;
                } else if (value > maxRange) {
                    return maxRange;
                }
            } else {
                if (value < maxRange) {
                    return minRange;
                } else if (value > minRange) {
                    return maxRange;
                }
            }
            if ((minRange < 0 || maxRange < 0) && !getPositiveOrNegative() && value * Const.MINUS_ONE >= minRange && value * Const.MINUS_ONE <= maxRange) {
                return (byte) (value * Const.MINUS_ONE);
            } else {
                return value;
            }
        }
    }

    /**
     * return random Byte or null
     *
     * @param minRange
     * @param maxRange
     * @param bAllowNulls
     * @return Byte or null
     */
    public static Byte getRandomByte(Byte minRange, Byte maxRange, boolean bAllowNulls) {
        if (bAllowNulls && getRandomValueOrNull()) {
            return null;
        }
        if (minRange.shortValue() == maxRange.shortValue()) {
            return minRange;
        } else {
            Byte value = Byte.valueOf((byte) (minRange + (byte) (Math.random() * (maxRange - minRange + Const.ONE))));
            if (minRange.shortValue() < maxRange.shortValue()) {
                if (value.shortValue() < minRange.shortValue()) {
                    return minRange;
                } else if (value.shortValue() > maxRange.shortValue()) {
                    return maxRange;
                }
            } else {
                if (value.shortValue() < maxRange.shortValue()) {
                    return minRange;
                } else if (value.shortValue() > minRange.shortValue()) {
                    return maxRange;
                }
            }
            if ((minRange.shortValue() < 0 || maxRange.shortValue() < 0) && !getPositiveOrNegative() && value.shortValue() * Const.MINUS_ONE >= minRange.shortValue() && value.shortValue() * Const.MINUS_ONE <= maxRange.shortValue()) {
                return Byte.valueOf((byte) (value * Const.MINUS_ONE));
            } else {
                return value;
            }
        }
    }

    /**
     * return random true/false for value to be null
     *
     * @return random true/false for value to be null
     */
    private static boolean getRandomValueOrNull() {
        int lRandomNumber = (int) (Math.random() * Const.FIVE);
        return lRandomNumber % Const.FIVE == Const.ZERO;
    }

    /**
     * return random BigInteger in range of bytes or null
     *
     * @param minRange
     * @param maxRange
     * @param bAllowNulls
     * @return BigInteger or null
     */
    public static BigInteger getRandomBigInteger(BigInteger minRange, BigInteger maxRange, boolean bAllowNulls) {
        BigDecimal bigDecimal = getRandomBigDecimal(new BigDecimal(minRange), new BigDecimal(maxRange), 0, bAllowNulls);
        if ( bigDecimal != null){
            return bigDecimal.toBigInteger();
        } else {
            return null;
        }
    }

    /**
     * return random BigDecimal in range of bytes or null
     *
     * @param bgMinRange
     * @param bgMaxRange
     * @param precision
     * @param bAllowNulls
     * @return BigDecimal or null
     */
    public static BigDecimal getRandomBigDecimal(BigDecimal bgMinRange, BigDecimal bgMaxRange, int precision, boolean bAllowNulls) {
        if (bAllowNulls && getRandomValueOrNull()) {
            return null;
        }
        if (bgMinRange.compareTo(bgMaxRange) == 0) {
            return bgMinRange;
        } else {
            BigDecimal value = bgMinRange.add((bgMaxRange.subtract(bgMinRange).add(BigDecimal.valueOf(Const.ONE))).multiply(BigDecimal.valueOf(Math.random())));
            BigDecimal bgValue = value;
            bgValue = bgValue.setScale(precision, RoundingMode.HALF_UP);
            if (bgMinRange.compareTo(bgMaxRange) < 0) {
                if (bgValue.compareTo(bgMinRange) < 0) {
                    bgMinRange = bgMinRange.setScale(precision, BigDecimal.ROUND_HALF_UP);
                    return bgMinRange;
                } else if (bgValue.compareTo(bgMaxRange) > 0) {
                    bgMaxRange = bgMaxRange.setScale(precision, BigDecimal.ROUND_HALF_DOWN);
                    return bgMaxRange;
                }
            } else {
                if (bgValue.compareTo(bgMaxRange) < 0) {
                    bgMinRange = bgMinRange.setScale(precision, BigDecimal.ROUND_HALF_UP);
                    return bgMinRange;
                } else if (bgValue.compareTo(bgMinRange) > 0) {
                    bgMaxRange = bgMaxRange.setScale(precision, BigDecimal.ROUND_HALF_DOWN);
                    return bgMaxRange;
                }
            }
            if ((bgMinRange.compareTo(BigDecimal.ZERO) < 0 || bgMaxRange.compareTo(BigDecimal.ZERO) < 0) && !getPositiveOrNegative() && ((value.multiply(BigDecimal.valueOf(Const.MINUS_ONE)))).compareTo(bgMinRange) >= 0 && ((value.multiply(BigDecimal.valueOf(Const.MINUS_ONE)))).compareTo(bgMaxRange) <= 0) {
                return bgValue.multiply(BigDecimal.valueOf(Const.MINUS_ONE));
            } else {
                return bgValue;
            }
        }
    }

    /**
     * return random array of formatted characters or null
     *
     * @return String UUID or null
     */
    public static String getRandomUUID(boolean bAllowNulls) {
        if (bAllowNulls && getRandomValueOrNull()) {
            return null;
        }
        return UUID.randomUUID().toString();
    }

    /**
     * return random date in range of min max date
     *
     * @param minDate
     * @param maxDate
     * @param bAllowNulls
     * @return Date or null
     */
    public static Date getRandomDate(Date minDate, Date maxDate, boolean bAllowNulls) {
        if (bAllowNulls && getRandomValueOrNull()) {
            return null;
        }
        if (minDate.compareTo(maxDate) == 0) {
            return minDate;
        }
        final long beginTime = minDate.getTime();
        final long endTime = maxDate.getTime();// + TimeUnit.MILLISECONDS.convert(1, TimeUnit.DAYS);
        final long lNewDate = RandomUtil.getRandomPrimitiveLong(beginTime, endTime);
        if (beginTime < endTime) {
            if (lNewDate < beginTime) {
                return minDate;
            } else if (lNewDate > endTime) {
                return maxDate;
            }
        } else {
            if (lNewDate < endTime) {
                return minDate;
            } else if (lNewDate > beginTime) {
                return maxDate;
            }
        }
        return format.parseDateTime(sdf.format(new Date(lNewDate))).toDate();
    }

    /**
     * return random char
     * @return char
     */
    public static char getRandomChar() {
        return (char) getRandomPrimitiveInt(Const.ZERO_INTEGER, Const.MAX_CHAR);
    }

    /**
     * return random char in range min max
     * @param minChar
     * @param maxChar
     * @return char
     */
    public static char getRandomChar(int minChar, int maxChar) {
        return (char) getRandomPrimitiveInt(minChar, maxChar);
    }

    /**
     * return random names
     *
     * @param name
     * @param bAllowNulls
     * @return String or null
     */
    public static String getRandomNames(int name, boolean bAllowNulls) {
        String[] alOfFirstNames = {"Joe", "Donna", "Ronald", "Sarah", "David", "Courtney", "Irwin", "Linda", "Michael", "Cindy", "Tom", "Rebekah", "Todd", "Tracy", "Peter", "Nicole", "Marcelo", "Jennifer", "Rick", "Andrea", "Bruce", "Jaclyn", "Doug", "Shirley", "Steve", "Liz", "Waldo", "Theresa", "Scott", "Colby", "Beth", "Larry", "Emily", "Paul", "Kate", "Sam", "Dianne", "Dustin", "Alethea", "Wayne", "Kristina", "Christian", "Danny", "Breya", "Andrew", "Alison", "Tim", "Mary", "Chris", "Susie", "Jeremy", "Willy", "Jessica", "Marcus", "Kelly", "Kyle", "Stephanie", "Isaiah", "Hillary", "Eric", "Julia", "Donald", "Meredith", "Kevin", "Leslie", "Blake", "Angela", "Cliff", "Debbie", "Dylan", "Erin", "Alex", "Monica", "Nathan", "Wendy", "Josh", "Megan", "Adam", "Michelle", "Carey", "Ashley", "Brian", "Jason", "Melanie", "Jim", "Monica", "Jamie", "Rhonda", "Steven", "Perry", "Byron", "Laura", "Harry", "Brooke", "Drew", "Vicki", "Gary", "Anita", "Felipe", "Josie"};
        String[] alOfLastNames = {"Smith", "Johnson", "Williams", "Jones", "Brown", "Davis", "Miller", "Wilson", "Moore", "Taylor", "Washington", "Jefferson", "Lincoln", "Hamilton", "Jackson", "Grant", "Franklin", "McKinley", "Cleveland", "Madison", "Chase", "Nicholson", "Fauver", "Doe", "Southard", "Schmidt", "Hodson", "McDonald", "Stickley", "Miller", "Combs", "Bohus", "Krippner", "Amtower", "Banks", "Wallace", "Bannister", "Dehaven", "Yost", "Still", "Timbrook", "Peters", "Vaught", "Shellhammer", "Andrews", "Krippner", "McAlister", "Wright", "Kensinger", "McClellan", "Ganoe", "Shiley", "Layman", "Gearhart", "Yost", "Kushnir", "Bush", "Lowder", "Connolly", "Lowman", "Terveen", "Staton", "Settle", "Tinsman", "Nichols", "Baker", "Walters", "Dawe", "Renner", "Michaels", "Faircloth", "Looker", "Hastings", "Vaughan", "Anderson", "Zimmerman", "Deere", "Daher", "Lauck", "Stottlemyer", "Clinton", "Obama", "Reagan", "Montgomery", "Pugh", "Gavis", "Clark", "Bowers"};
        String sFirstName = alOfFirstNames[RandomUtil.getRandomPrimitiveInt(Const.ZERO, alOfFirstNames.length - Const.ONE)];
        String sLastName = alOfLastNames[RandomUtil.getRandomPrimitiveInt(Const.ZERO, alOfLastNames.length - Const.ONE)];
        char sMiddleName = RandomUtil.getRandomChar(Const.CHAR_A, Const.CHAR_Z);
        if (name == Const.NAME) {
            return sFirstName + " " + sMiddleName + " " + sLastName;
        } else if (name == Const.FIRST_NAME) {
            return sFirstName;
        } else if (name == Const.LAST_NAME) {
            return sLastName;
        } else if (name == Const.MIDDLE_NAME) {
            return String.valueOf(sMiddleName);
        } else {
            return null;
        }
    }

    /**
     * minimum date 01.00.0001 or long value -62135773200000
     *
     * @return minimum date 01.00.0001 or long value -62135773200000
     */
    public static long getMinimumDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(
                calendar.getActualMinimum(Calendar.YEAR),
                calendar.getActualMinimum(Calendar.MONTH),
                calendar.getActualMinimum(Calendar.DAY_OF_MONTH),
                calendar.getActualMinimum(Calendar.HOUR),
                calendar.getActualMinimum(Calendar.MINUTE),
                calendar.getActualMinimum(Calendar.SECOND)
        );

        calendar.set(Calendar.MILLISECOND, calendar.getActualMinimum(Calendar.MILLISECOND));
        return calendar.getTimeInMillis();
    }

    /**
     * maximum date 17.34.292269054 or long value -9223372025090751617
     *
     * @return maximum date 17.34.292269054 or long value -9223372025090751617
     */
    public static long getMaximumDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(
                calendar.getActualMaximum(Calendar.YEAR),
                calendar.getActualMaximum(Calendar.MONTH),
                calendar.getActualMaximum(Calendar.DAY_OF_MONTH),
                calendar.getActualMaximum(Calendar.HOUR),
                calendar.getActualMaximum(Calendar.MINUTE),
                calendar.getActualMaximum(Calendar.SECOND)
        );

        calendar.set(Calendar.MILLISECOND, calendar.getActualMaximum(Calendar.MILLISECOND));
        return calendar.getTimeInMillis();
    }

    /**
     * returns random enum value
     * in range min, max or
     * in range min, max from objEnum or
     * in range min, max of enumeration class
     * @param objMin
     * @param objMax
     * @param objEnum
     * @param cl Enum class
     * @return Object Enum value
     */
    public static Object getRandomEnum(int objMin, int objMax, String[] objEnum, Class cl) {
        Object enumeration;
        if (objMin == Const.MINUS_ONE) {
            objMin = Const.ZERO;
        }
        if (objMax == Const.MINUS_ONE) {
            if (objEnum != null) {
                objMax = objEnum.length - Const.ONE;
            }
        }
        if (objEnum != null) {
            enumeration = Enum.valueOf(cl, objEnum[RandomUtil.getRandomPrimitiveInt(objMin, objMax)].trim());
        } else {
            enumeration = cl.getEnumConstants()[RandomUtil.getRandomPrimitiveInt(objMin, objMax)];
        }
        return enumeration;
    }
}
