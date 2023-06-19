package problem1.model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import problem1.model.Seat.SeatFactory;

class SeatTest {

  Seat testSeat;

  @BeforeEach
  void setUp() {
    testSeat = SeatFactory.createSeat("A");
  }

  @Test
  void getName() {
    String expectedName = "A";
    assertEquals(expectedName, testSeat.getName());
  }

  @Test
  void getReservedFor() {
    testSeat.reserve("Test Name");
    String expectedString = "Test Name";
    assertEquals(expectedString, testSeat.getReservedFor());
  }

  @Test
  void reserve() {
    String testName = "AAA BB";
    testSeat.reserve(testName);
    assertEquals(testName, testSeat.getReservedFor());
  }


  @Test
  void toStringWithOptions() {
    assertEquals("_ ",testSeat.toStringWithOptions(Boolean.FALSE));
    assertEquals("= ",testSeat.toStringWithOptions(Boolean.TRUE));
    testSeat.reserve("Test Name");
    assertEquals("X ",testSeat.toStringWithOptions(Boolean.FALSE));

  }
}