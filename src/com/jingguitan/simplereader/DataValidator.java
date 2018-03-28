package com.jingguitan.simplereader;

import java.util.ArrayList;

/**
 * This class is used to validate data of Individual and Family.
 * Each method realize one of more User Stories.
 * 
 * @author Jinggui
 *
 */

public class DataValidator {
	
	//add your each validator into this method and add system log to print
	
	//for individuals
	public static void validateIndividualsInfo(ArrayList<Individual> indiArray) {
		for(Individual indi: indiArray) {
			//US03
			if (birtBeforeDeat(indi) == false) {
				System.err.println("Err:Line "+indi.lineNumber.birthdayLineNumber
				+ ": Birth after death.");
				System.err.println("Birth:"+indi.getBirthday()+"/Death:"+indi.getDeathday());
			}
		}
	}
	
	// for families
	public static void validateFamiliesInfo(ArrayList<Family> famArray) {
		for(Family fam: famArray) {
			//US04
			if (marryBeforeDivo(fam) == false) {
				System.err.println("Err:Line "+fam.lineNumber.marriedLineNumber
				+ ": Marry after death.");
				System.err.println("Marry:"+fam.getMarried()+"/Divorce:"+fam.getDivorced());
			}
		}
	}
	
	// below are data validators
	
	// US03 Birth before death
	public static boolean birtBeforeDeat(Individual indi) {
		//may not die
		if(indi.getAlive().equals("True")) {
			return true;
		}
		return compareDate(indi.getBirthday(), indi.getDeathday());
	}
	
	// US04 Marriage before divorce
	public static boolean marryBeforeDivo(Family fam) {
		
		//may not divorce
		if(fam.getDivorced().equals("NA")) {
			return true;
		}
		return compareDate(fam.getMarried(), fam.getDivorced());
	}
	
	private static boolean compareDate(String date1, String date2) {
		int year1 = Integer.parseInt(date1.substring(0, 4));
		int month1 = Integer.parseInt(date1.substring(5, 7));
		int day1 = Integer.parseInt(date1.substring(9));
		
		int year2 = Integer.parseInt(date2.substring(0, 4));
		int month2 = Integer.parseInt(date2.substring(5, 7));
		int day2 = Integer.parseInt(date2.substring(9));
		
		if(year1 < year2) {
			return true;
		}else if(year1 > year2){
			return false;
		}else if(year1 == year2){
			if(month1 < month2) {
				return true;
			}else if(month1 > month2) {
				return false;
			}else if(month1 == month2) {
				if(day1 < day2) {
					return true;
				}else if(day1 > day2) {
					return false;
				}else if(day1 == day2) {
					// birth and die in the same day or marry and divorce in the same day
					return true;
				}
			}
		}
		return false;
	}
}
