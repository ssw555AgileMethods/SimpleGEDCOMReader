package com.jingguitan.simplereaderTest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.jingguitan.simplereader.Family;
import com.jingguitan.simplereader.GEDCOMPrinter;
import com.jingguitan.simplereader.Individual;

class GEDCOMPrinterTest {

	ArrayList<Individual> indiList;
	ArrayList<Family> famList;
	
	@BeforeEach
	void setUp() throws Exception {
		indiList = new ArrayList<Individual>();
		famList = new ArrayList<Family>();
		
		Individual indi = new Individual();
		indi.setId("I0001", 0);
		indi.setName("Name /I0001/", 1);
		indi.setGender("F", 2);
		indi.setBirthday("2 JAN 1963", 3);
		indi.setDeathday("3 FEB 2013", 4);
		indi.addSpouse("I0002", 5);
		indiList.add(indi);
		
		indi = new Individual();
		indi.setId("I0002", 0);
		indi.setName("Name /I0002/", 1);
		indi.setGender("M", 2);
		indi.setBirthday("12 OCT 1964", 3);
		indi.setDeathday("13 DEC 2014", 4);
		indi.addSpouse("I0001", 5);
		indiList.add(indi);
		
		Family fam = new Family();
		fam.setId("F0001", 0);
		fam.setMarried("22 JAN 1983", 1);
		fam.setHusbandId("I0001", 2);
		fam.setHusbandName("Name /I0001/", 3);
		fam.setWifeId("I0002", 4);
		fam.setWifeName("Name /I0002/", 5);
		famList.add(fam);
		
	}

	@DisplayName("Printer test")
	@Test
	void test() throws Exception {
		Integer[] width1= {16,18,8,12,5,7,12,12,12};
		Integer[] width2= {16,12,12,16,18,16,18,12};
		GEDCOMPrinter printer = new GEDCOMPrinter(width1, width2);
		printer.printIndividual(indiList);
		printer.printFamily(famList);
		assertTrue(true);
	}

}
