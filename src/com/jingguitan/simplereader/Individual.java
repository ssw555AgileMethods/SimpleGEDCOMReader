package com.jingguitan.simplereader;

import java.util.ArrayList;
import java.util.Calendar;

public class Individual implements Comparable {
	
	private String id;
	private String name;
	private String gender;
	private String birthday;
	private String age;
	private boolean alive; // true for alive, and false for not 
	private String deathday;
	private String child;
	private ArrayList<String> spouse; // fam id
	LineNumber lineNumber;
	
	class LineNumber{
		int idLineNumber;
		int nameLineNumber;
		int genderLineNumber;
		int birthdayLineNumber;
		int ageLineNumber;
		int aliveLineNumber;
		int deathdayLineNumber;
		int childLineNumber;
		ArrayList<Integer> spouseLineNumber;
		
		public LineNumber() {
			spouseLineNumber = new ArrayList<Integer>();
		}
	}
	
	public Individual() {
		lineNumber = new LineNumber();
		
		this.setAlive(true, -1);
		this.setChild("NA", -1);
		this.setBirthday("NA", -1);
		spouse = new ArrayList<String>();
	}
	
	@Override
	public boolean equals(Object indi) {
		Individual newIndi = (Individual) indi;
		if(newIndi.getId().equals(this.getId())) {
			return true;
		}else {
			return false;
		}
	}
	@Override
	public int hashCode() {
		return id.hashCode();
	}
	
	@Override
	public String toString() {
		return "INDIID:"+id;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id, int line) {
		this.id = id;
		this.lineNumber.idLineNumber = line;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name, int line) {
		this.name = name;
		this.lineNumber.nameLineNumber = line;
	}
	
	// not needed
	@Deprecated
	public String getFisrName() {
		return "";

	};
	// not needed
	@Deprecated
	public String getLastName() {
		return "";
	}
	
	public String getGender() {
		return gender;
	}
	
	public void setGender(String gender, int line) {
		this.gender = gender;
		this.lineNumber.genderLineNumber = line;
	}
	
	public String getBirthday() {
		// do something process
		// origin: 02 Jan 2001
		// output: 2001-01-02
		if(this.birthday.equals("NA")) {
			return this.birthday;
		}
		
		String[] date = this.birthday.split(" ",3);
		date[1] = GEDCOMDateFomater.format(date[1]);
		
		return date[2]+"-"+date[1]+"-"+GEDCOMDateFomater.formatMonthDay(date[0]);
	}
	
	public void setBirthday(String birthday, int line) {
		this.birthday = birthday;
		this.lineNumber.birthdayLineNumber = line;
	}
	
	
	public String getAge() {
		// 2 cases
		// 1. For dead, deathday - birthday
		// 2��  For alive, currentTime - birthday
		if(this.birthday.equals("NA")) {
			return this.birthday;
		}
		
		int lastYear = 0;
		int lastMonth = 0;
		int lastDay = 0;
		int birtYear = 0;
		int birtMonth = 0;
		int birtDay = 0;
		
		String[] date = this.birthday.split(" ",3);
		birtYear = Integer.valueOf(date[2]);
		birtMonth = Integer.valueOf(GEDCOMDateFomater.format(date[1]));
		birtDay = Integer.valueOf(date[0]);
		
		if (this.alive == true) {
			Calendar cal = Calendar.getInstance();
			lastYear = cal.get(Calendar.YEAR);
			lastMonth = cal.get(Calendar.MONTH);
			lastDay = cal.get(Calendar.DAY_OF_MONTH);
		}else {
			date = this.deathday.split(" ",3);
			lastYear = Integer.valueOf(date[2]);
			lastMonth = Integer.valueOf(GEDCOMDateFomater.format(date[1]));
			lastDay = Integer.valueOf(date[0]);
		}
		int age = lastYear - birtYear;
		
		if (lastMonth <= birtMonth)  
        {  
            if (lastMonth == birtMonth)  
            {  
                if (lastDay < birtDay)  
                    age--;  
            }  
            else  
            {  
                age--;  
            }  
        }  
		
		return Integer.toString(age);
	}
	
	@Deprecated
	public void setAge(String age) {
		this.age = age;
	}
	
	
	public Boolean getAlive() {
		return alive?true:false;
	}
	
	public void setAlive(boolean alive, int line) {
		this.alive = alive;
		this.lineNumber.aliveLineNumber = line;
	}
	
	public String getDeathday() {
		//for alive ,return 'NA'
		if (alive) {
			return "NA";
		}
		
		String[] date = this.deathday.split(" ",3);
		date[1] = GEDCOMDateFomater.format(date[1]);
		
		return date[2]+"-"+date[1]+"-"+GEDCOMDateFomater.formatMonthDay(date[0]);
	}
	
	public void setDeathday(String deathday, int line) {
		this.deathday = deathday;
		this.lineNumber.deathdayLineNumber = line;
	}
	
	public String getChild() {
		// "NA" or "{'F000000000'}"
		if (this.child.equals("NA")) {
			return this.child;
		}else {
			return "{'"+this.child+"'}";
		}
	}
	
	public void setChild(String child, int line) {
		this.child = child;
		this.lineNumber.childLineNumber = line;
	}
	
	public String getSpouse() {
		// "NA" or "{'F0000000000'}"
		String temp = "";
		if(this.spouse.size() == 0) {
			return "NA";
		}else {
			for (String id:spouse) {
				if (temp.equals("")) {
					temp = "'"+id+"'";
				}else{
					temp = temp+", "+"'"+id+"'";
				}
			}
		}
		return "{"+temp+"}";
	}
	
	public void addSpouse(String spouse, int line) {
		this.spouse.add(spouse);
		this.lineNumber.spouseLineNumber.add(line);
	}

	@Override
	public int compareTo(Object o) {
		Individual newIndi = (Individual) o;
		return this.getId().compareTo(newIndi.getId());
	}
}
