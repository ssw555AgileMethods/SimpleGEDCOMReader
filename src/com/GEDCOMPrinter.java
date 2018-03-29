//package com.jingguitan.simplereader;

import java.util.ArrayList;
import java.util.Collections;

public class GEDCOMPrinter {
  
  
  public static void printIndividual(ArrayList<Individual> arrayList) {
    Collections.sort(arrayList);
    System.out.println("Individuals");
    System.out.println("+------------------------+--------------------------"
        + "+--------+------------+-----+-------"
        + "+------------------+----------------------------"
        + "+-------------------------------------------------------+");
    System.out.println("|           ID           |           Name           "
        + "| Gender |  Birthday  | Age | Alive "
        + "|       Death      |            Child           "
        + "|                         Spouse                        |");
    System.out.println("+------------------------+--------------------------"
        + "+--------+------------+-----+-------"
        + "+------------------+----------------------------"
        + "+-------------------------------------------------------+");
    for (Individual indi: arrayList) {
      System.out.print("| ");
      System.out.print(indi.getId());
      System.out.print(" | ");
      System.out.printf("%-25s",indi.getName());
      System.out.print("|   ");
      System.out.print(indi.getGender());
      System.out.print("    | ");
      System.out.printf("%-11s",indi.getBirthday());
      System.out.print("| ");
      System.out.printf("%-4s",indi.getAge());
      System.out.print("| ");
      System.out.printf("%-6s",indi.getAlive());
      System.out.print("| ");
      System.out.printf("%-17s",indi.getDeathday());
      System.out.print("| ");
      System.out.printf("%-27s",indi.getChild());
      System.out.print("| ");
      System.out.printf("%-54s",indi.getSpouse());
      System.out.println("|");
    }
    System.out.println("+------------------------+--------------------------"
        + "+--------+------------+-----+-------"
        + "+------------------+----------------------------"
        + "+-------------------------------------------------------+");
  }
  
  public static void printFamily(ArrayList<Family> arrayList) {
    Collections.sort(arrayList);
    System.out.println("Families");
    
    System.out.println("+------------------------+------------"
        + "+------------+------------------------+--------------------------"
        + "+------------------------" + "+--------------------------"
        + "+-------------------------------------------------------+");
    System.out.println("|           ID           |   Married  "
        + "|  Divorced  |       Husband Id       |       Husband Name       "
        + "|        Wife Id         "+  "|         Wife Name        "
        + "|                        Children                       |");
    System.out.println("+------------------------+------------"
        + "+------------+------------------------+--------------------------"
        + "+------------------------" + "+--------------------------"
        + "+-------------------------------------------------------+");
    
    for (Family fam: arrayList) {
      System.out.print("| ");
      System.out.printf("%-23s",fam.getId());
      System.out.print("| ");
      System.out.printf("%-11s",fam.getMarried());
      System.out.print("| ");
      System.out.printf("%-11s",fam.getDivorced());
      System.out.print("| ");
      System.out.printf("%-23s",fam.getHusbandId());
      System.out.print("| ");
      System.out.printf("%-25s",fam.getHusbandName());
      System.out.print("| ");
      System.out.printf("%-23s",fam.getWifeId());
      System.out.print("| ");
      System.out.printf("%-25s",fam.getWifeName());
      System.out.print("| ");
      System.out.printf("%-54s",fam.getChildren());
      System.out.println("|");
    }
    
    System.out.println("+------------------------+------------"
        + "+------------+------------------------+--------------------------"
        + "+------------------------" + "+--------------------------"
        + "+-------------------------------------------------------+");
  }
}
