package com.jingguitan.simplereader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * 
 * all information of Individual and Family are stored in {@value indiList} and {@value famList},
 * use {@code getIndiList} and {@code getFamList} to get these information.
 * 
 * @author Jinggui Tan
 */

public class LineProcessor {
	private boolean indiEntry;
	private boolean famEntry;
	
	private boolean birtEntry;
	private boolean deatEntry;
	
	private boolean marrEntry;
	private boolean divEntry;
	
	// store individual info
	private ArrayList<Individual> indiList;
	// store fam info
	private ArrayList<Family> famList;
	
	
	private Individual currIndi;
	private Family currFam;
	
	public LineProcessor() {
		currIndi = null;
		currFam = null;
		indiList = new ArrayList<Individual>();
		famList = new ArrayList<Family>();
		
		indiEntry = false;
		famEntry = false;
		
		birtEntry = false;
		deatEntry = false;
		
		marrEntry = false;
		divEntry = false;
	}
	
	public void process(String line, int lineNumber) {
		String[] words = line.split(" ", 3);
		
// for 1st assigment		
//		if(!Character.isDigit(line.toCharArray()[0])) {
//			System.out.println("<-- ERROR LINES");
//		}else {
//			if (words.length == 2 ) {
//				System.out.print("<-- "+words[0]+"|"+words[1]+"|");
//				boolean validation = checkValidation(words[0],words[1]);
//				if(validation == true) {
//					System.out.print("Y");
//				}else {
//					System.out.print("N");
//				}
//				System.out.println("|");
//				//System.out.println("<-- ERROR LINES");
//			} else if (words.length == 1) {
//				System.out.println("<-- ERROR LINES");
//			}else {
//				boolean validation = checkValidation(words[0],words[1],words[2]);
//				if(words[2].equals( "INDI") || words[2].equals("FAM")) {
//					System.out.print("<-- "+words[0]+"|"+words[2]+"|");
//					if(validation == true) {
//						System.out.print("Y");
//					}else {
//						System.out.print("N");
//					}
//					System.out.println("|"+words[1]);
//				}else {
//					System.out.print("<-- "+words[0]+"|"+words[1]+"|");
//					if(validation == true) {
//						System.out.print("Y");
//					}else {
//						System.out.print("N");
//					}
//					System.out.println("|"+words[2]);
//				}
//			}
//		}
		
		
		//for 2nd assignment

		// detect 1st entry for indi 
		if (indiEntry == false) {
			if (words.length == 3 && words[0].equals("0") && words[2].equals("INDI")) {
				if(famEntry == true) {
					famList.add(currFam);
				}
				indiEntry = true;
				famEntry = false;
				
				currIndi = new Individual();
				//id
				currIndi.setId(words[1].trim(), lineNumber);

			}
		}
		// detect 1st entry for fam
		if (famEntry == false) {
			if (words.length == 3 && words[0].equals("0") && words[2].equals("FAM")) {
				if(indiEntry == true) {
					indiList.add(currIndi);
				}
				indiEntry = false;
				famEntry = true;
				
				currFam = new Family();
				// id
				currFam.setId(words[1], lineNumber);
			}
		}
		
		//read and import data into memory
		// for individual
		if(indiEntry == true && famEntry == false) {
			// name
			if (words.length == 3 && words[0].equals("1") && words[1].equals("NAME")) {
				currIndi.setName(words[2].trim(), lineNumber);
			}
			
			// Gender
			if (words.length == 3 && words[0].equals("1") && words[1].equals("SEX")) {
				currIndi.setGender(words[2].trim(), lineNumber);
			}
			
			// Birthday
			if (words.length == 2 && words[0].equals("1") && words[1].equals("BIRT")) {
				birtEntry = true;
			}
			if (words.length == 3 && birtEntry == true &&  words[0].equals("2") && words[1].equals("DATE")) {
				currIndi.setBirthday(words[2], lineNumber);
				birtEntry = false;
			}
			
			// Alive
			if (words.length == 2 && words[0].equals("1") && words[1].equals("DEAT")) {
				deatEntry = true;
				currIndi.setAlive(false, lineNumber);
			}
			if (words.length == 3 && deatEntry == true &&  words[0].equals("2") && words[1].equals("DATE")) {
				currIndi.setDeathday(words[2], lineNumber);
				deatEntry = false;
			}
			
			// ifChild
			if (words.length == 3 &&  words[0].equals("1") && words[1].equals("FAMC")) {
				currIndi.setChild(words[2], lineNumber);
			}
			
			// ifSpouse
			if (words.length == 3 &&  words[0].equals("1") && words[1].equals("FAMS")) {
				currIndi.addSpouse(words[2], lineNumber);
			}
			
			// detect next indi or fam
			if (words.length == 3 && words[0].equals("0") && words[2].equals("INDI")) {
				if (currIndi.getName() != null) {
					indiList.add(currIndi);
					currIndi = new Individual();
					// id
					currIndi.setId(words[1], lineNumber);
				}
				
			}
			if (words.length == 3 && words[0].equals("0") && words[2].equals("FAM")) {
				indiList.add(currIndi);
				indiEntry = false;
				famEntry = true;
			}
			
		}else if (indiEntry == false && famEntry == true) { // for family
			// married
			if (words.length == 2 && words[0].equals("1") && words[1].equals("MARR")) {
				marrEntry = true;
			}
			if (words.length == 3 && marrEntry == true && words[0].equals("2") && words[1].equals("DATE")) {
				currFam.setMarried(words[2], lineNumber);
				marrEntry = false;
			}
			
			// divorced
			if (words.length == 2 && words[0].equals("1") && words[1].equals("DIV")) {
				divEntry = true;
			}
			if (words.length == 3 && divEntry == true && words[0].equals("2") && words[1].equals("DATE")) {
				currFam.setDivorced(words[2], lineNumber);
				divEntry = false;
			}
			
			// hunsband id and name
			if (words.length == 3 && words[0].equals("1") && words[1].equals("HUSB")) {
				currFam.setHusbandId(words[2], lineNumber);
				currFam.setHusbandName(getNameById(words[2]), lineNumber);
			}
			
			// wife id and name
			if (words.length == 3 && words[0].equals("1") && words[1].equals("WIFE")) {
				currFam.setWifeId(words[2], lineNumber);
				currFam.setWifeName(getNameById(words[2]), lineNumber);
			}
			
			// add children
			if (words.length == 3 && words[0].equals("1") && words[1].equals("CHIL")) {
				currFam.addChildren(words[2], lineNumber);
			}
			
			// detect next indi or fam
			if (words.length == 3 && words[0].equals("0") && words[2].equals("INDI")) {
				famList.add(currFam);
				currIndi = new Individual();
				currIndi.setId(words[1], lineNumber);
				
				indiEntry = true;
				famEntry = false;
			}
			if (words.length == 3 && words[0].equals("0") && words[2].equals("FAM")) {
				if(currFam.getHusbandId()!=null) {
					famList.add(currFam);
					currFam = new Family();
					// id
					currFam.setId(words[1], lineNumber);
				}
			}
			
			// end of fam
			if (words.length == 2 && words[0].equals("0") && words[1].equals("TRLR")) {
				famList.add(currFam);
			}
			
		}
		
			//in other cases, do nothing
			
	}
	
	
	public boolean checkValidation(String level, String tag1, String tag2) {
		//in most cases, we only need to check tag1
		//for DATE and NAME, we need to check level number.
		//for INDI and FAM, we need to check the real tag(@tag2)
		
		if(tagNameSet.contains(tag1)) {
			if((tag1.equals("DATE") && level.equals("2")) || (tag1.equals("NAME") && level.equals("1"))) {
				return false;
			}else {
				return true;
			}
		}else if(tag2.equals( "INDI") || tag2.equals("FAM")) {
			return true;
		}
		return false;
		
	}
	
	public boolean checkValidation(String level, String tag1) {
		//in most cases, we only need to check tag1
		//for DATE and NAME, we need to check level number.
		//for INDI and FAM, we need to check the real tag(@tag2)
		
		if(tagNameSet.contains(tag1)) {
			if((tag1.equals("DATE") && level.equals("2")) || (tag1.equals("NAME") && level.equals("1"))) {
				return false;
			}else {
				return true;
			}
		}
		return false;
		
	}
	
	private String getNameById(String id) {
		for (Individual indi: indiList) {
			if(indi.getId().equals(id)) {
				return indi.getName();
			}
		}
		return "NA";
	}
	
	public ArrayList<Individual> getIndiList(){
		return indiList;
	}
	
	public ArrayList<Family> getFamList(){
		return famList;
	}
	
	
	
	private String[] tagArray = new String[] {
			"INDI", "NAME", "SEX", "BIRT", "DEAT", "FAMC", "FAMS", 
			"FAM", "MARR", "HUSB", "WIFE", "CHIL", "DIV", "DATE", "HEAD",
			"TRLR", "NOTE"};
	private Set<String> tagNameSet = new HashSet<String>(Arrays.asList(tagArray));
	
}
