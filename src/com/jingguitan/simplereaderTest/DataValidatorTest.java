package com.jingguitan.simplereaderTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.jingguitan.simplereader.DataValidator;
import com.jingguitan.simplereader.Family;
import com.jingguitan.simplereader.Individual;

class DataValidatorTest {

	Individual indi;
	Family fam;
	
	@BeforeEach
	void setUp() throws Exception {
		indi = new Individual();
		fam = new Family();
	}

	@DisplayName("US03 Birth before death:01 day")
	@Test
	void testUS03A() {
		indi.setAlive(false, 99);
		indi.setBirthday("14 NOV 1981", 99);
		indi.setDeathday("13 NOV 1981", 101);
		assertFalse(DataValidator.birtBeforeDeat(indi));
	}
	
	@DisplayName("US03 Birth before death:02 month")
	@Test
	void testUS03B() {
		indi.setAlive(false, 99);
		indi.setBirthday("13 NOV 1981", 99);
		indi.setDeathday("13 JAN 1981", 101);
		assertFalse(DataValidator.birtBeforeDeat(indi));
	}
	
	@DisplayName("US03 Birth before death:03 year")
	@Test
	void testUS03C() {
		indi.setAlive(false, 99);
		indi.setBirthday("13 NOV 1981", 99);
		indi.setDeathday("13 NOV 1980", 101);
		assertFalse(DataValidator.birtBeforeDeat(indi));
	}
	
	@DisplayName("US03 Birth before death:04 same")
	@Test
	void testUS03D() {
		indi.setAlive(false, 99);
		indi.setBirthday("13 NOV 1981", 99);
		indi.setDeathday("13 NOV 1981", 101);
		assertTrue(DataValidator.birtBeforeDeat(indi));
	}
	
	@DisplayName("US03 Birth before death:05 alive")
	@Test
	void testUS03E() {
		indi.setAlive(true, 99);
		indi.setBirthday("13 NOV 1981", 99);
		assertTrue(DataValidator.birtBeforeDeat(indi));
	}
	
	@DisplayName("US03 Marry before divorce:01 day")
	@Test
	void testUS04A() {
		fam.setMarried("13 NOV 1981", 99);
		fam.setDivorced("12 NOV 1981", 101);
		assertFalse(DataValidator.marryBeforeDivo(fam));
	}
	
	@DisplayName("US03 Marry before divorce:02 month")
	@Test
	void testUS04B() {
		fam.setMarried("13 NOV 1981", 99);
		fam.setDivorced("13 JAN 1981", 101);
		assertFalse(DataValidator.marryBeforeDivo(fam));
	}
	
	@DisplayName("US03 Marry before divorce:03 year")
	@Test
	void testUS04C() {
		fam.setMarried("13 NOV 1981", 99);
		fam.setDivorced("13 JAN 1980", 101);
		assertFalse(DataValidator.marryBeforeDivo(fam));
	}
	
	@DisplayName("US03 Marry before divorce:04 same")
	@Test
	void testUS04D() {
		fam.setMarried("13 NOV 1981", 99);
		fam.setDivorced("13 NOV 1981", 101);
		assertTrue(DataValidator.marryBeforeDivo(fam));
	}
	
	@DisplayName("US03 Marry before divorce:05 not divorce")
	@Test
	void testUS04E() {
		fam.setMarried("13 NOV 1981", 99);
		fam.setDivorced("NA", 101);
		assertTrue(DataValidator.marryBeforeDivo(fam));
	}

}
