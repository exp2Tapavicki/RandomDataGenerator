package com.afowd.util;

import java.util.ArrayList;
import java.util.Iterator;

import com.afowd.ormClasses.Worker;
import com.afowd.range.filter.RangeNumberFilter;


public class FilterUtil {
	
	/**	Filtering received data of workers with received filters. All values can be null except bInRangeTotal and bInRangeOrg. If any of this value for filtering is null it should not filter and add data if other filters
	 * 	satisfy requirement. If filters are not null and value in worker for years of experience is null it should not filter that data and should add them too.
	 * 
	 * @param alElements 					ArrayList of Workers with years of experience
	 * @param iYearsOfExpirenceTotalFrom	filter for total years lower range filtering 
	 * @param iYearsOfExpirenceTotalTo		filter for total years higher range filtering
	 * @param bInRangeTotal					if true it is giving in range filter for iYearsOfExpirenceTotalFrom and iYearsOfExpirenceTotalTo variables else it is telling that they are out of that range
	 * @param iAgeFrom						filter for age from 
	 * @param iAgeTo						filter for age to
	 * @param bInRangeOrg					if true it is giving in range filter for iAgeFrom and iAgeTo else it is telling that they are out of that range
	 * @return alWorkersTemp				filtered ArrayList of workers
	 */
	public static ArrayList<Worker> filterDataByYearsOfExperienceAndAge( ArrayList<Worker> alWorkers, Integer iYearsOfExpirenceTotalFrom , Integer iYearsOfExpirenceTotalTo, boolean bInRangeTotal, Integer iAgeFrom, Integer iAgeTo, boolean bInRangeOrg){
		ArrayList<Worker> alWorkersTemp = new ArrayList<Worker>();
		if ( alWorkers != null && !alWorkers.isEmpty()){
			for (Iterator<Worker> iterator = alWorkers.iterator(); iterator.hasNext();) {
				Worker worker = (Worker) iterator.next();
				Integer iYearsOfExpirenceTotal = worker.getYearsOfExpirenceTotal();
				Integer iAge= worker.getAge();
				if ((iYearsOfExpirenceTotalFrom != null ? ( iYearsOfExpirenceTotal != null ? ( bInRangeTotal ? iYearsOfExpirenceTotal.intValue() >= iYearsOfExpirenceTotalFrom.intValue() : iYearsOfExpirenceTotal.intValue() <= iYearsOfExpirenceTotalFrom.intValue()) : true ) : true)){
					if (iYearsOfExpirenceTotalTo != null ? ( iYearsOfExpirenceTotal != null ? ( bInRangeTotal ? iYearsOfExpirenceTotal.intValue() <= iYearsOfExpirenceTotalTo.intValue() : iYearsOfExpirenceTotal.intValue() >= iYearsOfExpirenceTotalTo.intValue()) : true ) : true){
						if (iAgeFrom != null ? ( iAge != null ? ( bInRangeOrg ? iAge.intValue() >= iAgeFrom.intValue() : iAge.intValue() <= iAgeFrom.intValue()) : true ): true){
							if (iAgeTo != null ? ( iAge != null ? ( bInRangeOrg ? iAge.intValue() <= iAgeTo.intValue() : iAge.intValue() >= iAgeTo.intValue()) : true ): true){
								alWorkersTemp.add(worker);	
							}
						}
					}
				}
			}	
		}
		return alWorkersTemp;
	}
	/**
	 * filter array list with one range filter
	 * @param alData
	 * @param rangeFilter
	 * @return
	 */
	public static ArrayList<Object> filterRangeDataNumbers (ArrayList<Object> alData, RangeNumberFilter rangeFilter){
		return RangeFilterUtil.filterRangeDataNumbers(alData, rangeFilter);
	}
	/**
	 * filter array list with multiple range filters
	 * @param alData
	 * @param alRangeFilter
	 * @return
	 */
	public static ArrayList<Object> filterRangeDataNumbers (ArrayList<Object> alData, ArrayList<RangeNumberFilter> alRangeFilter){
		return RangeFilterUtil.filterRangeDataNumbers(alData, alRangeFilter);
	}
}
