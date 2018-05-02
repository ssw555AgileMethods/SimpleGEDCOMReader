package com.jingguitan.simplereader;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class GEDCOMPrinter {
	
//	private int indiIdWidth = 24;
//	private int indiNameWidth = 26;
//	private int indiGenderWidth = 8;
//	private int indiBirthWidth = 12;
//	private int indiAgeWidth = 5;
//	private int indiAliveWidth = 7;
//	private int indiDeathWidth = 18;
//	private int indiChildWidth = 28;
//	private int indiSpouseWidth = 55;
//	
//	private int famIdWidth = 24;
//	private int famMarriedWidth = 12;
//	private int famDivorcedWidth = 12;
//	private int famHusbandIdWidth = 24;
//	private int famHusbandNameWidth = 26;
//	private int famWifeIdWidth = 24;
//	private int famWifeNameWidth = 26;
//	private int famChildrenWidth = 55;
	
	private  List<Integer> indiSheetWidthArray;
	private  List<Integer> famSheetWidthArray;
	
	private  List<String> indiSheetHeaderArray;
	private  List<String> famSheetHeaderArray;
	
	public GEDCOMPrinter(){
		initWidths();
	}
	
	public GEDCOMPrinter(Integer[] indiWidth,Integer[] famWidth,
			String[] indiHeader,String[] famHeader){
		initWidths(indiWidth, famWidth, indiHeader, famHeader);
	}
	
	public GEDCOMPrinter(Integer[] indiWidth,Integer[] famWidth){
		initWidths(indiWidth, famWidth);
	}
	
	private void initWidths() {
		indiSheetWidthArray = Arrays.asList(24,26,8,12,5,7,12,28,54);
		famSheetWidthArray = Arrays.asList(24,12,12,24,26,24,26,54);
		
		indiSheetHeaderArray = Arrays.asList("ID","Name","Gender",
				"Birthday","Age","Alive","Death","Child","Spouse");
		famSheetHeaderArray = Arrays.asList("ID","Married","Divorced",
				"Husband ID","Husband Name","Wife ID","Wife Name",
				"Children");
	}
	
	private void initWidths(Integer[] indiWidth, Integer[] famWidth,
			String[] indiHeader,String[] famHeader) {
		indiSheetWidthArray = Arrays.asList(indiWidth);
		famSheetWidthArray = Arrays.asList(famWidth);
		
		indiSheetHeaderArray = Arrays.asList(indiHeader);
		famSheetHeaderArray = Arrays.asList(famHeader);
	}
	
	private void initWidths(Integer[] indiWidth, Integer[] famWidth) {
		indiSheetWidthArray = Arrays.asList(indiWidth);
		famSheetWidthArray = Arrays.asList(famWidth);
		indiSheetHeaderArray = Arrays.asList("ID","Name","Gender",
				"Birthday","Age","Alive","Death","Child","Spouse");
		famSheetHeaderArray = Arrays.asList("ID","Married","Divorced",
				"Husband ID","Husband Name","Wife ID","Wife Name",
				"Children");
	}
	
	private void printRowLine(int index) throws Exception {
		
		
		List<Integer> tempWidth;
		
		if(index == 0) {
			tempWidth = indiSheetWidthArray;
		}else if (index == 1) {
			tempWidth = famSheetWidthArray;
		}else {
			throw new Exception("Wrong print configuration.");
		}
		
		for(Integer i:tempWidth) {
			System.out.print("+");
			while(i>0) {
				System.out.print("-");
				i--;
			}
		}
		System.out.println("+");
	}
	
	private void printSheetHead(int index) throws Exception {
		List<Integer> tempWidth;
		List<String> tempHeader;
		
		if(index == 0) {
			tempWidth = indiSheetWidthArray;
			tempHeader = indiSheetHeaderArray;
		}else if (index == 1) {
			tempWidth = famSheetWidthArray;
			tempHeader = famSheetHeaderArray;
		}else {
			throw new Exception("Wrong print configuration.");
		}
		
		printRowLine(index);
		for(int i=0;i<tempHeader.size();i++) {
			System.out.print("|");
			int widthBefore = 
					(tempWidth.get(i)-tempHeader.get(i).length())/2;
			int widthAfter =
					tempWidth.get(i)-tempHeader.get(i).length() 
					-widthBefore;
			while(widthBefore>0) {
				System.out.print(" ");
				widthBefore--;
			}
			System.out.print(tempHeader.get(i));
			while(widthAfter>0) {
				System.out.print(" ");
				widthAfter--;
			}
		}
		System.out.println("|");
		printRowLine(index);
	}
	
	private void printContent(int width, String content) {
		int widthAfter = width-content.length()-1;
		System.out.print(" ");
		System.out.print(content);
		while(widthAfter>0) {
			System.out.print(" ");
			widthAfter--;
		}
	}
	
	private void printIndiContent(ArrayList<Individual> arrayList) {
		String content = "";
		for(Individual indi:arrayList) {
			for(int j=0;j<indiSheetHeaderArray.size();j++) {
				if(j==0) {
					content = indi.getId();
				}else if(j==1) {
					content = indi.getName();
				}else if(j==2) {
					content = indi.getGender();
				}else if(j==3) {
					content = indi.getBirthday();
				}else if(j==4) {
					content = Integer.toString(indi.getAge());
				}else if(j==5) {
					if(indi.getAlive())	{
						content = "True";
					}else {
						content = "False";
					}
				}else if(j==6) {
					content = indi.getDeathday();
				}else if(j==7) {
					content = indi.getChild();
				}else if(j==8) {
					content = indi.getSpouse();
				}
				System.out.print("|");
				printContent(indiSheetWidthArray.get(j),content);
			}
			System.out.println("|");
		}
		
	}
	
	private void printFamContent(ArrayList<Family> arrayList) {
		String content = "";
		for(Family fam:arrayList) {
			for(int j=0;j<famSheetHeaderArray.size();j++) {
				if(j==0) {
					content = fam.getId();
				}else if(j==1) {
					content = fam.getMarried();
				}else if(j==2) {
					content = fam.getDivorced();
				}else if(j==3) {
					content = fam.getHusbandId();
				}else if(j==4) {
					content = fam.getHusbandName();
				}else if(j==5) {
					content = fam.getWifeId();
				}else if(j==6) {
					content = fam.getWifeName();
				}else if(j==7) {
					content = fam.getChildren();
				}
				System.out.print("|");
				printContent(famSheetWidthArray.get(j),content);
			}
			System.out.println("|");
		}
	}
	
	public void printIndividual(ArrayList<Individual> arrayList) throws Exception {
		Collections.sort(arrayList);
		System.out.println("Individuals");
		printSheetHead(0);
		printIndiContent(arrayList);
		printRowLine(0);
	}
	
	public void printFamily(ArrayList<Family> arrayList) throws Exception {
		Collections.sort(arrayList);
		System.out.println("Families");
		printSheetHead(1);
		printFamContent(arrayList);
		printRowLine(1);
	}
	
	public void printLivingSingle(ArrayList<Individual> indiList, ArrayList<Family> famList)throws Exception {
		ArrayList<Individual> result = new ArrayList<Individual>();
		boolean showFlag = false;
		for(int i=0; i<indiList.size(); i++) {
			Individual indi = indiList.get(i);
			for(int j=0; j<famList.size(); j++) {
				Family fam = famList.get(j);
				if(fam.getHusbandId().equals(indi.getId()) || fam.getWifeId().equals(indi.getId())) {
					showFlag = true;
					break;
				}
			}
			if(!showFlag) {
				result.add(indi);
			}
			showFlag = false;
		}
		
		System.out.println("Living Single");
		printSheetHead(0);
		printIndiContent(result);
		printRowLine(0);
	}
	
	public void printMultipleBirth(ArrayList<Individual> indiList, ArrayList<Family> famList)throws Exception{
		ArrayList<ArrayList<Individual>> result = new ArrayList<ArrayList<Individual>>();
		for(int i=0; i<famList.size(); i++) {
			ArrayList<Individual> temp = new ArrayList<Individual>();
			ArrayList<Individual> foobar = null;
			Family fam = famList.get(i);
			if(fam.getChildrenId() == null) continue;
			for(String id:fam.getChildrenId()) {
				Individual indi = LineProcessor.getIndiById(id);
				if(temp.contains(indi)) continue;
				temp.add(indi);
			}
			while(temp.size()>0) {
				foobar = new ArrayList<Individual>();
				Individual indi = temp.get(0);
				for(int j=0; j<temp.size();j++) {
					if(j == 0) continue;
					Individual indiComp = temp.get(j);
					if(indi.getId().equals(indiComp.getId())) continue;
					if(indi.getBirthday().equals(indiComp.getBirthday())) {
						if(foobar.size() == 0) {
							foobar.add(indi);
						}
						foobar.add(indiComp);
						temp.remove(j);
						j--;
					}
					
				}
				temp.remove(0);
				if(foobar.size()>0) result.add(foobar);
			}
		}
		
		if(result == null) return;
		for(int i=0; i<result.size();i++) {
			System.out.println("Multiple Births");
			printSheetHead(0);
			printIndiContent(result.get(i));
			printRowLine(0);
		}
	}
	
	
}
