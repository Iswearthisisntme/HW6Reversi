package homework5.reversi.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests the functionality of the Coordinate class.
 */
public class CoordinateTest {
  Coordinate zeroOne;
  Coordinate oneTwo;
  Coordinate thirtyTwo;

  @Before
  public void init() {
    zeroOne = new Coordinate(0, 1);
    oneTwo = new Coordinate(1, 2);
    thirtyTwo = new Coordinate(30, 2);
  }

  @Test
  public void testGetQ() {
    Assert.assertEquals(0, zeroOne.getQ());
    Assert.assertEquals(1, oneTwo.getQ());
    Assert.assertEquals(30, thirtyTwo.getQ());
  }

  @Test
  public void testGetR() {
    Assert.assertEquals(1, zeroOne.getR());
    Assert.assertEquals(2, oneTwo.getR());
    Assert.assertEquals(2, thirtyTwo.getR());
  }
}