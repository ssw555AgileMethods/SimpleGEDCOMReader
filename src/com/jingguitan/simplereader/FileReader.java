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
			
			
			
			GEDCOMPrinter.printIndividual(lp.getIndiList());
			GEDCOMPrinter.printFamily(lp.getFamList());
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
