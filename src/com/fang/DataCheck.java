
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.ArrayList;
import java.util.Calendar;

//to do: US20,21

public class DataCheck {
  
  //US07 (less than 150 years)
  public static void ageLessThan150(ArrayList<Individual> arrayList) throws ParseException
  {
    //Collections.sort(arrayList); //never implemented (placeholder?)
    //SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    //Date date1 = sdf.parse("2018-3-28");//current date
    
    //get ages and compare
    for (Individual indi: arrayList) {
      int indiAge = indi.getAge();//already accounts for alive/dead
      if (indiAge>150) {
        System.out.println("Anomaly: " + indi.getName() + " is over 150 years old");
      }
    }
  }
  
  //US10 (marriage after age 14)
  public static void marriageAfter14(ArrayList<Individual> arrayList1, ArrayList<Family> arrayList2) throws ParseException 
  {
    Collections.sort(arrayList1);
    Collections.sort(arrayList2);
    for (Family fam: arrayList2) {
      if(fam.getMarried()!="NA"){ //only check married families
        String HusbandID = fam.getHusbandId();
        String WifeID = fam.getWifeId();
        for (Individual indi: arrayList1) {
          String thisID = indi.getId();
          if (thisID==HusbandID) {
            int MarryAge= Integer.parseInt(getMarriageAge(indi, fam));
            if (MarryAge <=14 ) {
              System.out.println("Anomaly: Husband was not older than 14 when he married");
            }
          }
          if (thisID==WifeID) {
            int MarryAge= Integer.parseInt(getMarriageAge(indi, fam));
            if (MarryAge <=14 ) {
              System.out.println("Anomaly: Wife was not older than 14 when he married");
            }
          }
        }
      }
    }
  }
  
  //US14 (multiple births <=5)
  public static void multiBirth(ArrayList<Family> arrayList1) throws ParseException 
  {
    Collections.sort(arrayList1);
    for (Family fam: arrayList1) {
      String FamilyChildren = fam.getChildren();
      //parse Children String IDs
      String delims = "[ ',{}]+"; //spaces, commas, apostrophes, open and closed curly braces
      String [] Children = FamilyChildren.split(delims);
      int multiCount=0; //will need to count to 6 since it will count itself
      for (String childID: Children) {
        //???
      }
    }
  }
  //US15 (fewer than 15 siblings)
  public static void multiSiblings(ArrayList<Family> arrayList1) throws ParseException 
  {
    Collections.sort(arrayList1);
    for (Family fam: arrayList1) {
      String FamilyChildren = fam.getChildren();
      //parse Children String IDs
      String delims = "[ ',{}]+"; //spaces, commas, apostrophes, open and closed curly braces
      String [] Children = FamilyChildren.split(delims);
      int multiCount=0; //will need to count to 6 since it will count itself
      for (String childID: Children) {
        multiCount++;
      }
      if (multiCount>15) {
        System.out.println("Anomaly: Family has more than 15 siblings");
      }
    }
  }
  
  //US18 (siblings should not marry)
  public static void siblingMarriage (ArrayList<Family> arrayList1) throws ParseException{
 //joe's code here
  }
  
  //US20 (Aunts/Uncles should not marry nephew/niece)
  public static void secondDegree(ArrayList<Family> arrayList1, ArrayList<Individual> arrayList2) throws ParseException {

  }
  
  //US21 (correct gender roles)
  public static void genderRoles (Family fam) throws ParseException {
    /*
     *  for(String id: fam.getChildrenId()) {
      if(!compareDate(fam.getMarried(), 
          LineProcessor.getIndiById(id).getBirthday())) {
        result = false;
        return result;
      }
     */
    String husbId = fam.getHusbandId();
    String husbGender = LineProcessor.getIndiById(husbId).getGender();
    if (husbGender!="M") {
      System.err.println("husband should be male.");
    }
    
    String wifeId = fam.getWifeId();
    String wifeGender = LineProcessor.getIndiById(wifeId).getGender();
    if (wifeGender!="F") {
      System.err.println("wife should be female.");
    }
  }
  
  public static Boolean dateChecker(Date date1, Date date2) { //checks if same date.
    
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
  
  public static String getMarriageAge(Individual indi, Family fam) {
    // 1 case: marriageDay-birthday
    // 1. For dead, deathday - marriageDay 
    // 2. For alive, currentTime - marriageDay
    
    if (fam.getMarried().equals("NA")) {
      return fam.getMarried();
    }
    
    int birtYear = 0;
    int birtMonth = 0;
    int birtDay = 0;
    int marrYear=0;
    int marrMonth=0;
    int marrDay=0;
    String[] dateMarried = fam.getMarried().split(" ",3);
    marrYear = Integer.valueOf(dateMarried[2]);
    marrMonth = Integer.valueOf(GEDCOMDateFomater.format(dateMarried[1]));
    marrDay = Integer.valueOf(dateMarried[0]);
    
    String[] dateBirth = indi.getBirthday().split(" ",3);
    birtYear = Integer.valueOf(dateBirth[2]);
    birtMonth = Integer.valueOf(GEDCOMDateFomater.format(dateBirth[1]));
    birtDay = Integer.valueOf(dateBirth[0]);
    
    int age = marrYear - birtYear;
    
    if (marrMonth <= birtMonth)  
        {  
            if (marrMonth == birtMonth)  
            {  
                if (marrDay < birtDay)  
                    age--;  
            }  
            else  
            {  
                age--;  
            }  
        }  
    
    return Integer.toString(age);
  }

}