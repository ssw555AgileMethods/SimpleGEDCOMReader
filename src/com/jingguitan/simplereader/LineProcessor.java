package com.jingguitan.simplereader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class LineProcessor {
	public void process(String line) {
		String[] words = line.split(" ", 3);
		
		
		if(!Character.isDigit(line.toCharArray()[0])) {
			System.out.println("<-- ERROR LINES");
		}else {
			if (words.length == 2 ) {
				System.out.print("<-- "+words[0]+"|"+words[1]+"|");
				boolean validation = checkValidation(words[0],words[1]);
				if(validation == true) {
					System.out.print("Y");
				}else {
					System.out.print("N");
				}
				System.out.println("|");
				//System.out.println("<-- ERROR LINES");
			} else if (words.length == 1) {
				System.out.println("<-- ERROR LINES");
			}else {
				boolean validation = checkValidation(words[0],words[1],words[2]);
				if(words[2].equals( "INDI") || words[2].equals("FAM")) {
					System.out.print("<-- "+words[0]+"|"+words[2]+"|");
					if(validation == true) {
						System.out.print("Y");
					}else {
						System.out.print("N");
					}
					System.out.println("|"+words[1]);
				}else {
					System.out.print("<-- "+words[0]+"|"+words[1]+"|");
					if(validation == true) {
						System.out.print("Y");
					}else {
						System.out.print("N");
					}
					System.out.println("|"+words[2]);
				}
			}
		}
		
		
		
		
//		if (words.length==3 ) {
//			
//			
//		}else {
//			if (words[0]!="0" || words[0]!="1" || words[0]!="2" || words[0]!="3") {
//				
//			}
//		}
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
	
	
	private String[] tagArray = new String[] {
			"INDI", "NAME", "SEX", "BIRT", "DEAT", "FAMC", "FAMS", 
			"FAM", "MARR", "HUSB", "WIFE", "CHIL", "DIV", "DATE", "HEAD",
			"TRLR", "NOTE"};
	private Set tagNameSet = new HashSet<String>(Arrays.asList(tagArray));
	
}
