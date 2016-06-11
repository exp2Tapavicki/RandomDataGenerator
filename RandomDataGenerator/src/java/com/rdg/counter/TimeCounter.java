package com.rdg.counter;

import com.rdg.constants.Const;
import com.rdg.internationalizationManager.InternationalizationManager;

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

public class TimeCounter {
    private Long startTime;
    private Long stopTime;

    public TimeCounter() {
        startTime = System.currentTimeMillis();
    }

    public TimeCounter(Long startTime) {
        this.startTime = startTime;
    }

    public TimeCounter(Long startTime, Long stopTime) {
        this.startTime = startTime;
        this.stopTime = stopTime;
    }

    /**
     * @return string counted used time
     */
    public String printUsedTime() {
        stopTime = System.currentTimeMillis();
        return Const.NEW_LINE + InternationalizationManager.getString(Const.WORKING_TIME) + " " + ((stopTime - startTime) / 1000 / 60 / 60) % 24 + " " + InternationalizationManager.getString(Const.HOURS) + " " + ((stopTime - startTime) / 1000 / 60) % 60 + " " + InternationalizationManager.getString(Const.MINUTES) + " " + ((stopTime - startTime) / 1000) % 60 + " " + InternationalizationManager.getString(Const.SECONDS);
    }

    public Long getStartTime() {
        return startTime;
    }

    public void setStartTime(Long startTime) {
        this.startTime = startTime;
    }

    public Long getStopTime() {
        return stopTime;
    }

    public void setStopTime(Long stopTime) {
        this.stopTime = stopTime;
    }
}
