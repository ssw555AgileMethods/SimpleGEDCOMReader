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
			
			while( (foobarString = reader.readLine()) != null) {
				// do something
				foobarString = foobarString.trim();
				System.out.println("--> "+foobarString);
				LineProcessor lp = new LineProcessor();
				lp.process(foobarString);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
