package homework5.reversi.view;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;

import homework5.reversi.model.Reversi;

/**
 * Tests the functionality of the ReversiTextualView class.
 */

public class ReversiTextualViewTest {
  Reversi reversiSizeThree;
  Reversi reversiSizeFour;
  ReversiTextualView reversiTextualView;

  StringBuilder sb;

  @Before
  public void init() {
    reversiSizeThree = new Reversi(3);
    reversiSizeFour = new Reversi(4);
    sb = new StringBuilder();
  }

  @Test
  public void testTestToStringSizeThree() {
    reversiTextualView = new ReversiTextualView(reversiSizeThree, sb);
    String expected = "  _ _ _ \n"
            + " _ X O _ \n"
            + "_ O _ X _ \n"
            + " _ X O _ \n"
            + "  _ _ _ \n";
    String actual = reversiTextualView.toString();
    Assert.assertEquals(expected, actual);
    reversiSizeThree.makePlay(1, 1);
  }

  @Test
  public void testTestToStringSizeFour() {
    reversiTextualView = new ReversiTextualView(reversiSizeFour, sb);
    String expected1 = "   _ _ _ _ \n"
            + "  _ _ _ _ _ \n"
            + " _ _ X O _ _ \n"
            + "_ _ O _ X _ _ \n"
            + " _ _ X O _ _ \n"
            + "  _ _ _ _ _ \n"
            + "   _ _ _ _ \n";
    String actual1 = reversiTextualView.toString();
    Assert.assertEquals(expected1, actual1);
  }

  @Test

  public void testRender() throws IOException {
    reversiSizeFour.makePlay(1, 1);
    String expected = "   _ _ _ _ \n"
            + "  _ _ _ _ _ \n"
            + " _ _ X O _ _ \n"
            + "_ _ O _ X _ _ \n"
            + " _ _ X X X _ \n"
            + "  _ _ _ _ _ \n"
            + "   _ _ _ _ \n";
    reversiTextualView = new ReversiTextualView(reversiSizeFour, sb);
    reversiTextualView.render();
    Assert.assertEquals(expected, sb.toString());
  }
}