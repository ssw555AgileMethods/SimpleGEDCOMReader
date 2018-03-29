import static org.junit.Assert.*;

import org.junit.Test;

public class IndividualTest {

  @Test
  public void testGetAge1() {
    Individual ind1 = new Individual();
    ind1.setName("Michael");
    ind1.setAlive(true);
    ind1.setBirthday("29 MAR 1988");
    ind1.setGender("male");
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
    ind1.setName("JKP");
    ind1.setAlive(true);
    ind1.setBirthday("27 APR 1988");
    ind1.setGender("female");
    assertEquals("True", ind1.getAlive());
    assertEquals("NA", ind1.getDeathday());
    assertEquals("female", ind1.getGender());
    assertEquals ("29", ind1.getAge());
    //fail("Not yet implemented");
  }
  
  @Test
  public void testGetAge3() {
    Individual ind1 = new Individual();
    ind1.setName("OldMan");
    ind1.setAlive(false);
    ind1.setBirthday("14 JAN 1788");
    ind1.setGender("male");
    ind1.setDeathday("23 JUL 2004");
    assertEquals("Flase", ind1.getAlive()); //misspelling of False in Individual.java
    System.out.println(ind1.getAge());
  }
  

}
