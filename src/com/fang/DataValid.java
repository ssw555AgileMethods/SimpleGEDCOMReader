import static org.junit.Assert.*;

import org.junit.Test;

public class DataValid {

  @Test
  public void testGetAge1() {
    Individual ind1 = new Individual();
    ind1.setName("Michael", 1);
    ind1.setAlive(true, 1);
    ind1.setBirthday("29 MAR 1988", 1);
    ind1.setGender("male", 1);
    //System.out.println(ind1.getName());
    //System.out.println(ind1.getBirthday());
    assertEquals("True", ind1.getAlive());
    assertEquals("NA", ind1.getDeathday());
    assertEquals("male", ind1.getGender());
    assertEquals ("29", ind1.getAge());
    //fail("Not yet implemented");
  }
  @Test
  public void testGetAge2() {
    Individual ind1 = new Individual();
    ind1.setName("JKP", 1);
    ind1.setAlive(true, 1);
    ind1.setBirthday("27 APR 1988", 1);
    ind1.setGender("female", 1);
    assertEquals("True", ind1.getAlive());
    assertEquals("NA", ind1.getDeathday());
    assertEquals("female", ind1.getGender());
    assertEquals ("29", ind1.getAge());
    //fail("Not yet implemented");
  }
  
  @Test
  public void testGetAge3() {
    Individual ind1 = new Individual();
    ind1.setName("OldMan", 1);
    ind1.setAlive(false, 1);
    ind1.setBirthday("14 JAN 1788", 1);
    ind1.setGender("male", 1);
    ind1.setDeathday("23 JUL 2004", 1);
    assertEquals("Flase", ind1.getAlive()); //misspelling of False in Individual.java
    System.out.println(ind1.getAge());
  }

}
