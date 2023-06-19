package problem1.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import problem1.model.Row.RowFactory;

class RowTest {

  Row testRow;

  @BeforeEach
  void setUp() {
    testRow = RowFactory.createRow(7, 1, Boolean.TRUE);
  }

  @Test
  void setUpSuccess() {
    String expectedName = "A";
    assertEquals(expectedName, testRow.get(0).getName());
    expectedName = "B";
    assertEquals(expectedName, testRow.get(1).getName());
    expectedName = "G";
    assertEquals(expectedName, testRow.get(6).getName());
  }

  @Test
  void setUpFail_NegativeSeats(){
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      RowFactory.createRow(-10, 1, Boolean.TRUE);
    });
  }

  @Test
  void setUpFail_TooManySeats(){
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      RowFactory.createRow(37, 1, Boolean.TRUE);
    });
  }

  @Test
  void getNumSeats() {
    Integer expectedSeats = 7;
    assertEquals(expectedSeats, testRow.getNumSeats());
  }

  @Test
  void getRowNumber() {
    Integer expectedRowNumber = 1;
    assertEquals(expectedRowNumber, testRow.getRowNumber());
  }

  @Test
  void isWheelchairAccessible() {
    assertTrue(testRow.isWheelchairAccessible());
  }

  @Test
  void getNumOfAvailable() {
    Integer expectedAvailableNum = 7;
    assertEquals(expectedAvailableNum, testRow.getNumOfAvailable());
  }

  @Test
  void isRowAvailable_YES() {
    assertTrue(testRow.isRowAvailable(5));
  }

  @Test
  void isRowAvailable_NO() {
    assertFalse(testRow.isRowAvailable(9));
  }

  @Test
  void reserve() {
    testRow.reserve(1, "Test Person");
    Integer expectedNumOfAvailable = 6;
    assertEquals(expectedNumOfAvailable, testRow.getNumOfAvailable());
    String expectedName = "Test Person";
    assertEquals(expectedName, testRow.get(0).getReservedFor());
  }

  @Test
  void testToString() {
    testRow.reserve(1, "Test Person");
    String expected="1 X = = = = = = "+"\n";
    assertEquals(expected,testRow.toString());
  }
}