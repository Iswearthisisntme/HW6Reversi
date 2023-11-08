package homework5.reversi.model;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;

/**
 * Tests the functionality of the Reversi model.
 */

public class ReversiTest {

  Reversi reversiSizeThree;
  Reversi reversiSizeFour;
  Reversi reversiSizeFive;

  StringBuilder sb;

  @Before
  public void init() {
    reversiSizeThree = new Reversi(3);
    reversiSizeFour = new Reversi(4);
    reversiSizeFive = new Reversi(5);
  }

  @Test
  public void testInvalidBoardSize() {
    Assert.assertThrows(IllegalArgumentException.class,
        () -> new Reversi(1));
    Assert.assertThrows(IllegalArgumentException.class,
        () -> new Reversi(-10));
    Assert.assertThrows(IllegalArgumentException.class,
        () -> new Reversi(-20000));
  }

  @Test
  public void testGetCellColor() {
    Assert.assertThrows(IllegalArgumentException.class,
        () -> reversiSizeThree.getCellColor(0, 3, false));
    Assert.assertThrows(IllegalArgumentException.class,
        () -> reversiSizeThree.getCellColor(-3, 2, false));
    Assert.assertThrows(IllegalArgumentException.class,
        () -> reversiSizeThree.getCellColor(2, 1, false));
    Assert.assertThrows(IllegalArgumentException.class,
        () -> reversiSizeThree.getCellColor(20, 30, false));
  }

  @Test

  public void testPassTurn() {
    Assert.assertEquals(CellColor.NONE, reversiSizeFour.getCellColor(1, 1, false));
    reversiSizeFour.makePlay(1, 1);
    Assert.assertEquals(CellColor.BLACK, reversiSizeFour.getCellColor(1, 1, false));
    reversiSizeFour.passTurn();
    reversiSizeFour.makePlay(-1, -1);
    Assert.assertEquals(CellColor.BLACK, reversiSizeFour.getCellColor(-1, -1, false));

    reversiSizeFour.makePlay(-2, -1);
    Assert.assertEquals(CellColor.WHITE, reversiSizeFour.getCellColor(-1, -1, false));
    reversiSizeFour.passTurn();
    Assert.assertEquals(CellColor.WHITE, reversiSizeFour.getCurrentPlayersColor());
    reversiSizeFour.passTurn();
    Assert.assertTrue(reversiSizeFour.isGameOver());
  }

  @Test

  public void testMakePlay() {
    Assert.assertEquals(CellColor.NONE, reversiSizeThree.getCellColor(1, 1, false));
    reversiSizeThree.makePlay(1, 1);
    Assert.assertEquals(CellColor.BLACK, reversiSizeThree.getCellColor(1, 1, false));
    Assert.assertEquals(CellColor.BLACK, reversiSizeThree.getCellColor(0, 1, false));

    Assert.assertEquals(CellColor.NONE, reversiSizeThree.getCellColor(-1, 2, false));
    reversiSizeThree.makePlay(-1, 2);
    Assert.assertEquals(CellColor.WHITE, reversiSizeThree.getCellColor(-1, 2, false));
    Assert.assertEquals(CellColor.WHITE, reversiSizeThree.getCellColor(-1, 1, false));

    Assert.assertEquals(CellColor.NONE, reversiSizeThree.getCellColor(-2, 1, false));
    reversiSizeThree.makePlay(-2, 1);
    Assert.assertEquals(CellColor.BLACK, reversiSizeThree.getCellColor(-2, 1, false));
    Assert.assertEquals(CellColor.BLACK, reversiSizeThree.getCellColor(-1, 1, false));

    Assert.assertEquals(CellColor.NONE, reversiSizeThree.getCellColor(-1, -1, false));
    reversiSizeThree.makePlay(-1, -1);
    Assert.assertEquals(CellColor.WHITE, reversiSizeThree.getCellColor(-1, -1, false));
    Assert.assertEquals(CellColor.WHITE, reversiSizeThree.getCellColor(-1, 0, false));
    Assert.assertEquals(CellColor.WHITE, reversiSizeThree.getCellColor(-1, 1, false));

    Assert.assertEquals(CellColor.NONE, reversiSizeThree.getCellColor(1, -2, false));
    reversiSizeThree.makePlay(1, -2);
    Assert.assertEquals(CellColor.BLACK, reversiSizeThree.getCellColor(1, -2, false));
    Assert.assertEquals(CellColor.BLACK, reversiSizeThree.getCellColor(1, -1, false));
    Assert.assertEquals(CellColor.BLACK, reversiSizeThree.getCellColor(1, 0, false));
  }

  @Test
  public void testInvalidMoveNoNeighbor() {
    //no neighbor for black
    Assert.assertThrows(IllegalStateException.class, () -> reversiSizeFive.makePlay(-3, 0));
    reversiSizeFive.passTurn();
    //no neighbor for white
    Assert.assertThrows(IllegalStateException.class, () -> reversiSizeFive.makePlay(1, 2));

    //make sure it throws even after putting down a piece
    reversiSizeFive.makePlay(-2, 1);
    Assert.assertThrows(IllegalStateException.class, () -> reversiSizeFive.makePlay(0, -2));
    reversiSizeFive.passTurn();
    Assert.assertThrows(IllegalStateException.class, () -> reversiSizeFive.makePlay(1, 2));
  }

  @Test
  public void testInvalidMoveNoFlippable() {
    //putting down a black piece right next to black piece where
    //there's no flippable piece in between
    Assert.assertThrows(IllegalStateException.class, () -> reversiSizeThree.makePlay(2, 0));
    //same for white
    reversiSizeFive.passTurn();
    Assert.assertThrows(IllegalStateException.class, () -> reversiSizeThree.makePlay(0, 2));
  }

  @Test
  public void testPlaceOnOccupied() {
    Assert.assertThrows(IllegalArgumentException.class, () -> reversiSizeThree.makePlay(1, 0));
    Assert.assertThrows(IllegalArgumentException.class, () -> reversiSizeThree.makePlay(-1, 1));
    reversiSizeThree.makePlay(1, 1);
    Assert.assertThrows(IllegalArgumentException.class, () -> reversiSizeThree.makePlay(1, 1));
  }

  @Test
  public void testIsGameOver() {
    reversiSizeThree.makePlay(1, 1);
    reversiSizeThree.makePlay(-1, 2);
    reversiSizeThree.makePlay(-2, 1);
    reversiSizeThree.makePlay(-1, -1);
    reversiSizeThree.makePlay(1, -2);
    reversiSizeThree.makePlay(2, -1);
    Assert.assertTrue(reversiSizeThree.isGameOver());
    reversiSizeFive.passTurn();
    reversiSizeFive.passTurn();
    Assert.assertTrue(reversiSizeFive.isGameOver());
  }

  @Test
  public void testIsMoveValid() {
    // Initially, there should be valid moves for the first player (black).
    Assert.assertTrue(reversiSizeThree.isMoveValid(1, 1));
    Assert.assertTrue(reversiSizeThree.isMoveValid(-1, 2));
    Assert.assertTrue(reversiSizeThree.isMoveValid(-2, 1));

    // Make some moves to fill the board.
    reversiSizeThree.makePlay(1, 1);
    reversiSizeThree.makePlay(-1, 2);
    reversiSizeThree.makePlay(-2, 1);
    reversiSizeThree.makePlay(-1, -1);
    reversiSizeThree.makePlay(1, -2);
    reversiSizeThree.makePlay(2, -1);

    // Now, certain positions should no longer have valid moves.
    Assert.assertFalse(reversiSizeThree.isMoveValid(1, 1)); // Occupied cell
    Assert.assertFalse(reversiSizeThree.isMoveValid(-1, 2)); // Occupied cell
    Assert.assertFalse(reversiSizeThree.isMoveValid(-2, 1)); // Occupied cell
    Assert.assertFalse(reversiSizeThree.isMoveValid(0, 0)); // Empty cell, but no neighbors
    Assert.assertFalse(reversiSizeThree.isMoveValid(1, 0)); // Empty cell, but no flippable pieces
  }

  @Test
  public void testGetCurrentPlayersColor() {
    Assert.assertEquals(CellColor.BLACK, reversiSizeThree.getCurrentPlayersColor());

    reversiSizeThree.passTurn();
    reversiSizeThree.makePlay(1, 1);

    Assert.assertEquals(CellColor.BLACK, reversiSizeThree.getCurrentPlayersColor());
    reversiSizeThree.passTurn();
    Assert.assertEquals(CellColor.WHITE, reversiSizeThree.getCurrentPlayersColor());
  }

  @Test

  public void testGetScore() {
    Map<CellColor, Integer> score = reversiSizeThree.getScore();
    Assert.assertEquals(3, score.get(CellColor.BLACK).intValue());
    Assert.assertEquals(3, score.get(CellColor.WHITE).intValue());

    // Make some moves to change the score.
    reversiSizeThree.makePlay(1, 1);
    reversiSizeThree.makePlay(-1, 2);
    reversiSizeThree.makePlay(-2, 1);
    reversiSizeThree.makePlay(-1, -1);
    reversiSizeThree.makePlay(1, -2);
    reversiSizeThree.makePlay(2, -1);

    score = reversiSizeThree.getScore();
    Assert.assertEquals(4, score.get(CellColor.BLACK).intValue());
    Assert.assertEquals(8, score.get(CellColor.WHITE).intValue());
  }

  @Test

  public void testGetRowSize() {
    Assert.assertEquals(3, reversiSizeThree.getRowSize(0));
    Assert.assertEquals(4, reversiSizeThree.getRowSize(1));
    Assert.assertEquals(5, reversiSizeThree.getRowSize(2));
  }

  @Test

  public void testGetDiameter() {
    Assert.assertEquals(5, reversiSizeThree.getDiameter());
    Assert.assertEquals(7, reversiSizeFour.getDiameter());
    Assert.assertEquals(9, reversiSizeFive.getDiameter());
  }
}