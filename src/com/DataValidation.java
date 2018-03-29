//package com.jingguitan.simplereader;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.ArrayList;

public class DataValidation {
  public static void dateShouldBeforeIndi(ArrayList<Individual> arrayList) throws ParseException
  {  //User story 01
    Collections.sort(arrayList);
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    Date date1 = sdf.parse("2018-2-25");//current date
    
    //checking birth day is not after the current date
    for (Individual indi: arrayList) {
      Date date2 = sdf.parse(indi.getBirthday());//dates of individual birthdays
      if(indi.getAlive()!="True"){
        Date date3 = sdf.parse(indi.getDeathday());//dates of individual deathdays
        dateChecker(date1,date3);
      }
      //Date date3 = sdf.parse(indi.getDeathday());//dates of individual deathdays
      dateChecker(date1,date2);
      //dateChecker(date1,date3);
    }
  }
  
  public static void dateShouldBeforeFam(ArrayList<Family> arrayList) throws ParseException
  {   //User Story 01
    Collections.sort(arrayList);
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    Date date1 = sdf.parse("2018-2-25");
    for (Family fam: arrayList) {
      if(fam.getMarried()!="NA"){
        Date date4 = sdf.parse(fam.getMarried());//dates of family marriage
        dateChecker(date1,date4);
      }
      if(fam.getDivorced()!="NA"){
      Date date5 = sdf.parse(fam.getDivorced());//dates of family divorce
      dateChecker(date1,date5);
      }
    }
  }
  
  public static void birthBeforeMarriage(ArrayList<Individual> arrayList1, ArrayList<Family> arrayList2) throws ParseException
  {   //User Story 02
    Collections.sort(arrayList1);
    Collections.sort(arrayList2);
    
    SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
    
    for(Individual indi: arrayList1)
    {
      for (Family fam:arrayList2){
        Date date6 =sdf.parse(indi.getBirthday());//dates of individual birthdays
        Date date7=sdf.parse(fam.getMarried());//dates of family marriage
        dateChecker(date7,date6);
      }
    }
  }

  public static Boolean dateChecker(Date date1, Date date2) {
    
    if (date1.compareTo(date2) <= 0) {
      //System.out.println("Date invalid");
      return false;
    }
    else if(date1==null || date2==null || date1==null && date2==null)
    {return false;}
    
    else
    {
      //System.out.println("Date is correct");
      return true;
    }
  }
  
}
