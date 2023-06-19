package problem1.controller;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import problem1.model.Theater;
import problem1.model.Theater.TheaterFactory;

class ReservationsServiceTest {
  Theater testTheater;
  List<Integer> testAccessibleRows;
  ReservationsService testService;

  @BeforeEach
  void setUp() {
    testAccessibleRows = Arrays.asList(1, 2);
    testTheater = TheaterFactory.createTheater(5, 5, "Theater1", testAccessibleRows);
    testService=new ReservationsService(testTheater);
  }

  @Test
  void reserve() {
    testService.reserve(3, "Test Person", Boolean.FALSE);
    String expected=
        "1 = = = = = \n"
            + "2 = = = = = \n"
            + "3 X X X _ _ \n"
            + "4 _ _ _ _ _ \n"
            + "5 _ _ _ _ _ \n";
    assertEquals(expected,testTheater.toString());

    testService.reserve(3, "Test Person", Boolean.TRUE);
    String expected2=
        "1 = = = = = \n"
            + "2 X X X = = \n"
            + "3 X X X _ _ \n"
            + "4 _ _ _ _ _ \n"
            + "5 _ _ _ _ _ \n";
    assertEquals(expected2,testTheater.toString());

    String expectedName = "Test Person";
    assertEquals(expectedName, testTheater.get(0).get(0).getReservedFor());


  }
}