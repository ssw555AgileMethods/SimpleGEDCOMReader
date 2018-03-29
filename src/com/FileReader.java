//package com.jingguitan.simplereader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class FileReader {
  public void readFrom(String filePath) {
    File file = new File(filePath);
    BufferedReader reader = null;
    
    try {
      reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
      String foobarString = null;
      LineProcessor lp = new LineProcessor();
      while( (foobarString = reader.readLine()) != null) {
        // do something
        foobarString = foobarString.trim();
        //System.out.println("--> "+foobarString);
        lp.process(foobarString);
        
        
      }
      GEDCOMPrinter.printIndividual(lp.getIndiList());
      GEDCOMPrinter.printFamily(lp.getFamList());
      
      //Michael US10: check age of marriage is after age 14
      
      ArrayList<Individual> indList = lp.getIndiList();
      
      for (int i=0; i<indList.size(); i++) {
        for (Individual indi: indList) {
          if (!indi.getSpouse().equals("NA")) { //if married
            int indAge = Integer.parseInt(indi.getAge());
            if (indAge<=14) {
              System.out.println("Error US10: "+indi.getName()+" was not married after age 14.");
            }
          }
        }
      }
      
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
