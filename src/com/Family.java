//package com.jingguitan.simplereader;

import java.util.ArrayList;

public class Family implements Comparable{
  private String id;
  private String married; // marry time
  private String divorced; // "NA" or divorce time
  private String husbandId;
  private String wifeId;
  private String husbandName;
  private String wifeName;
  private ArrayList<String> children;
  
  public Family() {
    this.setMarried("NA");
    this.setDivorced("NA");
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
  
  public void setId(String id) {
    this.id=id;
  }
  
  public String getMarried() { //return married date
    if (this.married.equals("NA")) {
      return this.married;
    }
    
    
    String[] date = this.married.split(" ",3);
    
    
    date[1] = GEDCOMDateFomater.format(date[1]);
    
    return date[2]+"-"+date[1]+"-"+GEDCOMDateFomater.formatDay(date[0]);
  }
  
  public void setMarried(String married) {
    this.married = married;
  }
  
  public String getDivorced() {
    if(this.divorced.equals("NA")) {
      return this.divorced;
    }
    String[] date = this.divorced.split(" ",3);
    date[1] = GEDCOMDateFomater.format(date[1]);
    
    return date[2]+"-"+date[1]+"-"+GEDCOMDateFomater.formatDay(date[0]);
  }
  
  public void setDivorced(String divorced) {
    this.divorced = divorced;
  }
  
  public String getHusbandId() {
    return husbandId;
  }
  
  public void setHusbandId(String husbandId) {
    this.husbandId = husbandId;
  }
  
  
  public String getHusbandName() {
    return husbandName;
  }
  
  public void setHusbandName(String husbandName) {
    this.husbandName = husbandName;
  }
  
  public String getWifeId() {
    return wifeId;
  }
  
  public void setWifeId(String wifeId) {
    this.wifeId = wifeId;
  }
  
  public String getWifeName() {
    return wifeName;
  }

  public void setWifeName(String wifeName) {
    this.wifeName = wifeName;
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
  
  public void addChildren(String childId) {
    children.add(childId);
  }

  @Override
  public int compareTo(Object o) {
    Family newFam = (Family) o;
    return this.getId().compareTo(newFam.getId());
  }
  
}
