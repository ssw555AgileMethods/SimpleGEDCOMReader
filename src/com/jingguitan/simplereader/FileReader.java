package com.jingguitan.simplereader;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;

public class FileReader {
	public void readFrom(String filePath) {
		File file = new File(filePath);
		BufferedReader reader = null;
		
		try {
			
			reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
			String foobarString = null;
			
			// read file by line
			LineProcessor lp = new LineProcessor();
			int lineNumber = 0;
			
			while( (foobarString = reader.readLine()) != null) {
				// do something
				lineNumber++;
				foobarString = foobarString.trim();
				//System.out.println("--> "+foobarString);
				lp.process(foobarString, lineNumber);
			}
			
			DataValidator.validateIndividualsInfo(lp.getIndiList());
			DataValidator.validateFamiliesInfo(lp.getFamList());
			DataValidation.dateShouldBeforeIndi(lp.getIndiList());
			DataValidation.dateShouldBeforeFam(lp.getFamList());
			DataValidation.birthBeforeMarriage(lp.getIndiList(),lp.getFamList());
			DataValidation.divorceBeforeDeath(lp.getIndiList(),lp.getFamList());
			DataValidation.noBigamy(lp.getFamList());
			DataValidation.uniqueDataCheck(lp.getIndiList(),lp.getFamList());
			ListMembers.listOfDeceased(lp.getIndiList());
			ListMembers.listLivingMarried(lp.getIndiList(),lp.getFamList());
			GEDCOMPrinter printer = new GEDCOMPrinter();
			printer.printIndividual(lp.getIndiList());
			printer.printFamily(lp.getFamList());
			printer.printLivingSingle(lp.getIndiList(), lp.getFamList());
			printer.printMultipleBirth(lp.getIndiList(), lp.getFamList());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
