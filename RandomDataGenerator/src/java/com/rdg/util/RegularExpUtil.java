package com.rdg.util;

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

public class RegularExpUtil {
    public static final String ONLY_DIGITS = "[0-9]";
    public static final String WHITE_SPACE = "[^ ]";
    public static final String sDatePattern = "([0-9]{2}).([0-9]{2}).([0-9]{4})";
    public static final String MATCH_DOT = "\\.";
    public static final String MATCH_COMMA = "\\,";
}
