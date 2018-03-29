//package com.jingguitan.simplereader;

import java.util.ArrayList;
import java.util.Calendar;

public class Individual implements Comparable {
  private String firstName;
  private String lastName;
  
  private String id;
  private String name;
  private String gender;
  private String birthday;
  private String age;
  private boolean alive; // true for alive, and false for not 
  private String deathday;
  private String child;
  private ArrayList<String> spouse; // fam id
  
  public Individual() {
    this.setAlive(true);
    this.setChild("NA");
    this.setBirthday("NA");
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
  
  public void setId(String id) {
    this.id = id;
  }
  
  public String getName() {
    return name;
  }
  
  public void setName(String name) {
    this.name = name;
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
  
  public void setGender(String gender) {
    this.gender = gender;
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
    
    return date[2]+"-"+date[1]+"-"+GEDCOMDateFomater.formatDay(date[0]);
  }
  
  public void setBirthday(String birthday) {
    this.birthday = birthday;
  }
  
  
  public String getAge() {
    // 2 cases
    // 1. For dead, deathday - birthday
    // 2¡£  For alive, currentTime - birthday
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
    
    
    //Michael test case for US07; doesn't matter if alive/dead, age should not be >150
    if (age>150) {
      System.out.println("Error US07: "+this.name+" should be less than 150 years of age.");
    }
    
    return Integer.toString(age);
  }
  
  @Deprecated
  public void setAge(String age) {
    this.age = age;
  }
  
  
  public String getAlive() {
    return alive?"True":"Flase";
  }
  
  public void setAlive(boolean alive) {
    this.alive = alive;
  }
  
  public String getDeathday() {
    //for alive ,return 'NA'
    if (alive) {
      return "NA";
    }
    
    String[] date = this.deathday.split(" ",3);
    date[1] = GEDCOMDateFomater.format(date[1]);
    
    return date[2]+"-"+date[1]+"-"+GEDCOMDateFomater.formatDay(date[0]);
  }
  
  public void setDeathday(String deathday) {
    this.deathday = deathday;
  }
  
  public String getChild() {
    // "NA" or "{'F000000000'}"
    if (this.child.equals("NA")) {
      return this.child;
    }else {
      return "{'"+this.child+"'}";
    }
  }
  
  public void setChild(String child) {
    this.child = child;
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
  
  public void addSpouse(String spouse) {
    this.spouse.add(spouse);
  }

  @Override
  public int compareTo(Object o) {
    Individual newIndi = (Individual) o;
    return this.getId().compareTo(newIndi.getId());
  }
}
