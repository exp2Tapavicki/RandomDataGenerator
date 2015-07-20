package com.afowd.counter;

import com.afowd.constants.Const;
import com.afowd.internationalizationManager.InternationalizationManager;

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
	 * 
	 * @return string counted used time
	 */
	public String printUsedTime(){
		stopTime = System.currentTimeMillis();
		return "\n" + InternationalizationManager.getString(Const.WORKING_TIME) + " " + ((stopTime - startTime) / 1000 / 60 / 60) % 24 + " " + InternationalizationManager.getString(Const.HOURS) + " " + ((stopTime - startTime) / 1000 / 60) % 60 + " " + InternationalizationManager.getString(Const.MINUTES) + " " + ((stopTime - startTime) / 1000) % 60 + " " + InternationalizationManager.getString(Const.SECONDS);
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
