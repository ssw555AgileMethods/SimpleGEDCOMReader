package com.jingguitan.simplereader;

/**
 * This class is used to validate data of Individual and Family.
 * Each method realize one of more User Stories.
 * 
 * @author Administrator
 *
 */

public class DataValidator {
	
	// US03 Birth before death
	public boolean birtBeforeDeat(Individual indi) {
		//may not die
		if(indi.getAlive().equals("True")) {
			return true;
		}
		return this.compareDate(indi.getBirthday(), indi.getDeathday());
	}
	
	// US04 Marriage before divorce
	public boolean birtBeforeDivo(Family fam) {
		
		//may not divorce
		if(fam.getDivorced().equals("NA")) {
			return true;
		}
		return this.compareDate(fam.getMarried(), fam.getDivorced());
	}
	
	private boolean compareDate(String date1, String date2) {
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
