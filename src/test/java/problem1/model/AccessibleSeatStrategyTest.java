package problem1.model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import problem1.model.Theater.TheaterFactory;

class AccessibleSeatStrategyTest {

  Theater testTheater;
  List<Integer> testAccessibleRows;
  AccessibleSeatStrategy testStrategy;


  @BeforeEach
  void setUp() {
    testAccessibleRows = Arrays.asList(1, 2);
    testTheater = TheaterFactory.createTheater(5, 5, "Theater1", testAccessibleRows);
    testStrategy = new AccessibleSeatStrategy();
  }

  @Test
  void reserveSeats_Success() {
    Integer expectedRow = 2;
    assertEquals(expectedRow, testStrategy.reserveSeats(testTheater, 3, "Test"));
    expectedRow = 1;
    assertEquals(expectedRow, testStrategy.reserveSeats(testTheater, 4, "Test"));
  }

  @Test
  void reserveSeats_Fail() {
    testStrategy.reserveSeats(testTheater, 3, "Test");
    testStrategy.reserveSeats(testTheater, 4, "Test");
    Integer expectedReturn = -1;
    assertEquals(expectedReturn, testStrategy.reserveSeats(testTheater, 5, "Test"));
  }
}