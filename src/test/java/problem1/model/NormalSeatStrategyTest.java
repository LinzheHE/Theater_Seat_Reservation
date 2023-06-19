package problem1.model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import problem1.model.Theater.TheaterFactory;

class NormalSeatStrategyTest {

  Theater testTheater;
  List<Integer> testAccessibleRows;
  NormalSeatStrategy testStrategy;

  @BeforeEach
  void setUp() {
    testAccessibleRows = Arrays.asList(1, 2);
    testTheater = TheaterFactory.createTheater(5, 5, "Theater1", testAccessibleRows);
    testStrategy = new NormalSeatStrategy();
  }

  @Test
  void reserveSeats_Success() {
    Integer expectedRow = 3;
    assertEquals(expectedRow, testStrategy.reserveSeats(testTheater, 3, "Name"));
    expectedRow = 4;
    assertEquals(expectedRow, testStrategy.reserveSeats(testTheater, 3, "Name"));
  }

  @Test
  void reserveSeats_Fail() {
    // reserve all seats
    testStrategy.reserveSeats(testTheater, 5, "Name");
    testStrategy.reserveSeats(testTheater, 5, "Name");
    testStrategy.reserveSeats(testTheater, 5, "Name");
    testStrategy.reserveSeats(testTheater, 5, "Name");
    testStrategy.reserveSeats(testTheater, 5, "Name");
    Integer expectReturn = -1;
    assertEquals(expectReturn, testStrategy.reserveSeats(testTheater, 1, "Name"));
  }
}