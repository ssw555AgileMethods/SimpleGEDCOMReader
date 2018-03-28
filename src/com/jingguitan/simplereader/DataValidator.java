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
				+ ": Marry should be before divorce.");
				System.err.println("Marry:"+fam.getMarried()+"/Divorce:"+fam.getDivorced());
			}
			
			// US08
			if (birthBeforeMarriageOfParents(fam) == false) {
				System.err.println("Err:Line "+fam.lineNumber.marriedLineNumber 
				+ "\r\n"
				+ "FamId: " + fam.getId() + "\r\n"
				+ "Reason: Children should be born after marriage of parents (and not more than 9 months after their divorce)");
			}
			
			// US09
			if (birthBeforeDeathOfParents(fam) == false) {
				System.err.println("Err:Line "+LineProcessor.getIndiById(fam.getHusbandId()) 
						+ "or " + LineProcessor.getIndiById(fam.getWifeId()) + "\r\n"
						+ "FamId: " + fam.getId() + "\r\n"
						+ "Reason: Child should be born before death of mother and before 9 months after death of father. \\r\\n");
				
			}
			
		}
	}
	
	// mixing for both indi and fam
	public static void validateIndiFamInfo(ArrayList<Family> famArray,
			ArrayList<Individual> indiArray) {
		
	}
	
	
	// below are data validators 
	
	// US03 Birth before death
	public static boolean birtBeforeDeat(Individual indi) {
		//may not die
		if(indi.getAlive() == true) {
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
	
	// US08 Birth before marriage of parents
	public static boolean birthBeforeMarriageOfParents(Family fam) {
		if (fam.getChildrenId() == null) return true;
		if (fam.getMarried().equals("NA")) return true;
		boolean result = true;
		for(String id: fam.getChildrenId()) {
			if(!compareDate(fam.getMarried(), 
					LineProcessor.getIndiById(id).getBirthday())) {
				result = false;
				return result;
			}
			
			if(!compareDate(LineProcessor.getIndiById(id).getBirthday(),
					dayAfter(fam.getDivorced(),1,9))) {
				result = false;
				return result;
			}
		}
		return result;
		
	}
	
	
	// US09 Birth before death of parents
	public static boolean birthBeforeDeathOfParents(Family fam) {
		if (fam.getChildrenId() == null) return true;
		if (LineProcessor.getIndiById(fam.getHusbandId()).getAlive() == true) {
			return true;
		}
		if (LineProcessor.getIndiById(fam.getWifeId()).getAlive() == true) {
			return true;
		}
		
		boolean result = true;
		for(String id: fam.getChildrenId()) {
			if(!compareDate(LineProcessor.getIndiById(id).getBirthday(),
					LineProcessor.getIndiById(fam.getWifeId()).getDeathday())) {
				result = false;
				return result;
			}
			
			if(!compareDate(LineProcessor.getIndiById(id).getBirthday(),
					dayAfter(LineProcessor.getIndiById(fam.getHusbandId()).getDeathday(),1,9))) {
				result = false;
				return result;
			}
		}
		return result;
	}

	/*
	 * return true if date1 < date2
	 */
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
	
	/*
	 * index 0 for year
	 * 1 for month
	 * 2 for day -> not implemented
	 */
	private static String dayAfter(String date, int index,int number) {
		if(index > 2 || index < 0) return date;
		if(index == 0) {
			return Integer.toString((Integer.parseInt(date.substring(0, 4))+number))
					+ date.substring(4);
		}
		if(index == 1) {
			int currMonth = Integer.parseInt(date.substring(5, 7));
			int targetMonth = currMonth + number;
			
			return Integer.toString((Integer.parseInt(date.substring(0, 4))+(targetMonth-1)/12))
					+ "-"
					+ GEDCOMDateFomater.formatMonthDay(Integer.toString((targetMonth-1)%12+1))
					+ date.substring(7);
		}
		
		return null;
	}
	
}
