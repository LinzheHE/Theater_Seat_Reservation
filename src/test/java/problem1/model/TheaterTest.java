package problem1.model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import problem1.model.Theater.TheaterFactory;

class TheaterTest {

  Theater testTheater;
  Theater defaultTheater;
  List<Integer> testAccessibleRows;

  @BeforeEach
  void setUp() {
    testAccessibleRows = Arrays.asList(1, 2);
    testTheater = TheaterFactory.createTheater(5, 5, "Theater1", testAccessibleRows);
    defaultTheater = TheaterFactory.createTheater();
  }

  @Test
  void createTheater_Success() {
    // expected row nums in test theater: 3, 4, 5, 2, 1
    assertEquals(3, testTheater.get(0).getRowNumber());
    assertEquals(4, testTheater.get(1).getRowNumber());
    assertEquals(5, testTheater.get(2).getRowNumber());
    assertEquals(2, testTheater.get(3).getRowNumber());
    assertEquals(1, testTheater.get(4).getRowNumber());
  }

  @Test
  void createTheater_NegativeRowNum() {
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      TheaterFactory.createTheater(-10, 5, "Theater1", testAccessibleRows);
    });
  }

  @Test
  void createTheater_EmptyAccessibleRows() {
    List<Integer> emptyList = new ArrayList<>();
    Assertions.assertThrows(IllegalArgumentException.class, () -> {
      TheaterFactory.createTheater(5, 5, "Theater1", emptyList);
    });
  }

  @Test
  void getName() {
    String expectedName = "Roxy";
    assertEquals(expectedName, defaultTheater.getName());
  }

  @Test
  void getAccessibleRows() {
    List<Integer> expectedList = Arrays.asList(1, 6, 11, 16);
    assertEquals(expectedList, defaultTheater.getAccessibleRows());
  }

  @Test
  void getNumOfRows() {
    Integer expectedRows = 20;
    assertEquals(expectedRows, defaultTheater.getNumOfRows());
  }

  @Test
  void reserve() {
    testTheater.reserve(3, 3, "Test Person");
    String expectedPerson = "Test Person";
    assertEquals(expectedPerson, testTheater.get(0).get(0).getReservedFor());
    assertEquals(expectedPerson, testTheater.get(0).get(1).getReservedFor());
    assertEquals(expectedPerson, testTheater.get(0).get(2).getReservedFor());
  }

  @Test
  void testToString() {
    testTheater.reserve(3, 3, "Test Person");
    String expected=
        "1 = = = = = \n"
            + "2 = = = = = \n"
            + "3 X X X _ _ \n"
            + "4 _ _ _ _ _ \n"
            + "5 _ _ _ _ _ \n";
    assertEquals(expected,testTheater.toString());
  }

}