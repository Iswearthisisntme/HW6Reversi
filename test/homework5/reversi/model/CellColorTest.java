package homework5.reversi.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests the functionaility of the Color class.
 */

public class CellColorTest {
  CellColor empty;
  CellColor white;
  CellColor black;

  @Before
  public void init() {
    empty = CellColor.NONE;
    white = CellColor.WHITE;
    black = CellColor.BLACK;
  }

  @Test
  public void testGetName() {
    Assert.assertEquals(empty.getName(), "none");
    Assert.assertEquals(white.getName(), "White");
    Assert.assertEquals(black.getName(), "Black");
  }

  @Test
  public void testGetColor() {
    Assert.assertEquals(empty.getColor(), java.awt.Color.GRAY);
    Assert.assertEquals(white.getColor(), java.awt.Color.WHITE);
    Assert.assertEquals(black.getColor(), java.awt.Color.BLACK);
  }

  @Test

  public void testGetSymbol() {
    Assert.assertEquals(empty.getSymbol(), "_");
    Assert.assertEquals(white.getSymbol(), "O");
    Assert.assertEquals(black.getSymbol(), "X");
  }

  @Test

  public void testValues() {
    Assert.assertEquals(CellColor.NONE, CellColor.NONE);
    Assert.assertEquals(CellColor.WHITE, CellColor.WHITE);
    Assert.assertEquals(CellColor.BLACK, CellColor.BLACK);
  }

  @Test

  public void testValueOf() {
    // Test equality between instances with the same values
    Assert.assertEquals(empty, CellColor.NONE);
    Assert.assertEquals(white, CellColor.WHITE);
    Assert.assertEquals(black, CellColor.BLACK);

    // Test inequality between different instances
    Assert.assertNotEquals(empty, white);
    Assert.assertNotEquals(white, black);
    Assert.assertNotEquals(black, empty);
  }
}