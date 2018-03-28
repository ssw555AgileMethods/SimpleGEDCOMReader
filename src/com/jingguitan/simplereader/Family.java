package com.jingguitan.simplereader;

import java.util.ArrayList;

import com.jingguitan.simplereader.Individual.LineNumber;

public class Family implements Comparable{
	private String id;
	private String married; // marry time
	private String divorced; // "NA" or divorce time
	private String husbandId;
	private String wifeId;
	private String husbandName;
	private String wifeName;
	private ArrayList<String> children;
	LineNumber lineNumber;
	
	class LineNumber{
		int idLineNumber;
		int marriedLineNumber;
		int divorcedLineNumber;
		int husbandIdLineNumber;
		int wifeIdLineNumber;
		int husbandNameNumber;
		int wifeNameLineNumber;
		ArrayList<Integer> childrenLineNumber;
		
		public LineNumber() {
			childrenLineNumber = new ArrayList<Integer>();
		}
	}
	
	public Family() {
		lineNumber = new LineNumber();
		
		this.setMarried("NA", -1);
		this.setDivorced("NA", -1);
		children = new ArrayList<String>();
	}
	
	@Override
	public boolean equals(Object indi) {
		Family newIndi = (Family) indi;
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
		return "FAMID:"+id;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id, int line) {
		this.id=id;
		this.lineNumber.idLineNumber = line;
	}
	
	public String getMarried() {
		if (this.married.equals("NA")) {
			return this.married;
		}
		
		String[] date = this.married.split(" ",3);
		
		
		date[1] = GEDCOMDateFomater.format(date[1]);
		
		return date[2]+"-"+date[1]+"-"+GEDCOMDateFomater.formatMonthDay(date[0]);
	}
	
	public void setMarried(String married, int line) {
		this.married = married;
		this.lineNumber.marriedLineNumber = line;
	}
	
	public String getDivorced() {
		if(this.divorced.equals("NA")) {
			return this.divorced;
		}
		String[] date = this.divorced.split(" ",3);
		date[1] = GEDCOMDateFomater.format(date[1]);
		
		return date[2]+"-"+date[1]+"-"+GEDCOMDateFomater.formatMonthDay(date[0]);
	}
	
	public void setDivorced(String divorced, int line) {
		this.divorced = divorced;
		this.lineNumber.divorcedLineNumber = line;
	}
	
	public String getHusbandId() {
		return husbandId;
	}
	
	public void setHusbandId(String husbandId, int line) {
		this.husbandId = husbandId;
		this.lineNumber.husbandIdLineNumber = line;
	}
	
	
	public String getHusbandName() {
		return husbandName;
	}
	
	public void setHusbandName(String husbandName, int line) {
		this.husbandName = husbandName;
		this.lineNumber.husbandNameNumber = line;
	}
	
	public String getWifeId() {
		return wifeId;
	}
	
	public void setWifeId(String wifeId, int line) {
		this.wifeId = wifeId;
		this.lineNumber.wifeIdLineNumber = line;
	}
	
	public String getWifeName() {
		return wifeName;
	}

	public void setWifeName(String wifeName, int line) {
		this.wifeName = wifeName;
		this.lineNumber.wifeNameLineNumber = line;
	}
	public String getChildren() {
		String temp = "";
		if(this.children.size() == 0) {
			return "NA";
		}else {
			for (String id:children) {
				if (temp.equals("")) {
					temp = "'"+id+"'";
				}else{
					temp = temp+", "+"'"+id+"'";
				}
			}
		}
		return "{"+temp+"}";
	}
	
	public void addChildren(String childId, int line) {
		this.children.add(childId);
		this.lineNumber.childrenLineNumber.add(line);
	}
	
	public ArrayList<String> getChildrenId() {
		ArrayList<String> temp = new ArrayList<String>();
		if(this.children.size() == 0) {
			return null;
		}else {
			for (String id:children) {
				temp.add(id);
			}
			return temp;
		}
	}

	@Override
	public int compareTo(Object o) {
		Family newFam = (Family) o;
		return this.getId().compareTo(newFam.getId());
	}
	
}
