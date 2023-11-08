package homework5.reversi.model;


import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests all the method in the HexagonalBoard class.
 */
public class HexagonalBoardTest {
  HexagonalBoard boardSizeThree;
  HexagonalBoard boardSizeFour;

  @Before
  public void init() {
    boardSizeThree = new HexagonalBoard(3);
    boardSizeFour = new HexagonalBoard(4);
  }

  @Test

  public void testInitializeBoard() {
    Assert.assertThrows(IllegalArgumentException.class, () ->
            new HexagonalBoard(-2));
    Assert.assertThrows(IllegalArgumentException.class, () ->
            new HexagonalBoard(-100));
    Assert.assertThrows(IllegalArgumentException.class, () ->
            new HexagonalBoard(1));
    HexagonalBoard validBoard = new HexagonalBoard(2);
    Assert.assertTrue(CellColor.WHITE == validBoard.getHexagonColor(0, 1));
    Assert.assertTrue(CellColor.BLACK == validBoard.getHexagonColor(1, 0));
    Assert.assertTrue(CellColor.WHITE == validBoard.getHexagonColor(1, -1));
    Assert.assertTrue(CellColor.BLACK == validBoard.getHexagonColor(0, -1));
    Assert.assertTrue(CellColor.WHITE == validBoard.getHexagonColor(-1, 0));
    Assert.assertTrue(CellColor.BLACK == validBoard.getHexagonColor(-1, 1));


  }

  @Test

  public void testGetRowSize() {
    Assert.assertEquals(3, boardSizeThree.getRowSize(0));
    Assert.assertEquals(4, boardSizeThree.getRowSize(1));
    Assert.assertEquals(5, boardSizeThree.getRowSize(2));
    Assert.assertEquals(4, boardSizeFour.getRowSize(0));
    Assert.assertEquals(5, boardSizeFour.getRowSize(1));
    Assert.assertEquals(6, boardSizeFour.getRowSize(2));
  }

  @Test

  public void testPutPiece() {
    boardSizeThree.putPiece(0, 0, true);
    Assert.assertEquals(CellColor.BLACK, boardSizeThree.getHexagonColor(0, 0));

    boardSizeFour.putPiece(-2, 1, false);
    Assert.assertEquals(CellColor.WHITE, boardSizeFour.getHexagonColor(-2, 1));

    Assert.assertThrows(IllegalArgumentException.class,
        () -> boardSizeThree.putPiece(10, 0, true));
    Assert.assertThrows(IllegalArgumentException.class,
        () -> boardSizeThree.putPiece(293, -20, true));
  }

  @Test

  public void testFlipPiece() {
    boardSizeThree.putPiece(0, 0, true);
    boardSizeThree.flipPiece(0, 0, false);
    Assert.assertEquals(CellColor.WHITE, boardSizeThree.getHexagonColor(0, 0));

    boardSizeFour.putPiece(-2, 1, false);
    boardSizeFour.flipPiece(-2, 1, true);
    Assert.assertEquals(CellColor.BLACK, boardSizeFour.getHexagonColor(-2, 1));

    //flipping when the cell is empty
    Assert.assertThrows(IllegalArgumentException.class,
        () -> boardSizeThree.flipPiece(0, -2, true));
  }

  @Test

  public void testGetHexagon() {
    Hexagon hexagon1 = boardSizeThree.getHexagon(0, 0);
    Hexagon hexagon2 = boardSizeFour.getHexagon(-2, 1);

    Assert.assertNotNull(hexagon1);
    Assert.assertNotNull(hexagon2);

    Assert.assertEquals(CellColor.NONE, hexagon1.cellColor);
    Assert.assertEquals(CellColor.NONE, hexagon2.cellColor);
  }

  @Test

  public void testConvertFromAxialCoord() {
    Coordinate arrayCoordinate1 = boardSizeThree.convertFromAxialCoord(1, 0);
    Assert.assertEquals(2, arrayCoordinate1.getQ());
    Assert.assertEquals(3, arrayCoordinate1.getR());

    Coordinate arrayCoordinate2 = boardSizeThree.convertFromAxialCoord(3, -1);
    Assert.assertEquals(1, arrayCoordinate2.getQ());
    Assert.assertEquals(4, arrayCoordinate2.getR());
  }

  @Test

  public void testConvertToAxialCoord() {
    Coordinate axialCoordinate1 = boardSizeThree.convertToAxialCoord(2, 3);
    Assert.assertEquals(1, axialCoordinate1.getQ());
    Assert.assertEquals(0, axialCoordinate1.getR());
    Coordinate axialCoordinate2 = boardSizeThree.convertToAxialCoord(1, 4);
    Assert.assertEquals(3, axialCoordinate2.getQ());
    Assert.assertEquals(-1, axialCoordinate2.getR());

  }

  @Test

  public void testIsInBound() {
    Assert.assertTrue(boardSizeThree.isInBound(-2, 0));
    Assert.assertTrue(boardSizeThree.isInBound(2, 0));

    Assert.assertTrue(boardSizeFour.isInBound(-2, -1));
    Assert.assertTrue(boardSizeFour.isInBound(-2, -1));


    Assert.assertFalse(boardSizeThree.isInBound(-2, -1));
    Assert.assertFalse(boardSizeThree.isInBound(2, 1));

    Assert.assertFalse(boardSizeFour.isInBound(-2, -2));
    Assert.assertFalse(boardSizeFour.isInBound(2, 2));
  }

  @Test

  public void testGetHexagonColor() {
    // Test getting hexagon color after putting pieces
    boardSizeThree.putPiece(0, 0, true);
    Assert.assertEquals(CellColor.BLACK, boardSizeThree.getHexagonColor(0, 0));

    boardSizeFour.putPiece(-2, 1, false);
    Assert.assertEquals(CellColor.WHITE, boardSizeFour.getHexagonColor(-2, 1));
  }

  @Test

  public void testGetBoardSize() {
    Assert.assertEquals(3, boardSizeThree.getBoardSize());
    Assert.assertEquals(4, boardSizeFour.getBoardSize());
  }
}