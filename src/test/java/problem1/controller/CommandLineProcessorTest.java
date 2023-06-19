package problem1.controller;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import problem1.model.Theater;
import problem1.model.Theater.TheaterFactory;
import problem1.view.ReservationView;



class CommandLineProcessorTest {

  String input1;
  String input2;
  ReservationView testView;
  ReservationsService testService;
  Theater testTheater;
  List<Integer> testAccessibleRows;
  CommandLineProcessor testCommandLineProcessor;


  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this);
    input1 = "done";
    input2 = "reserve -2";
    testAccessibleRows = Arrays.asList(1, 2);
    testTheater = TheaterFactory.createTheater(5, 5, "Theater1", testAccessibleRows);

    testView = new ReservationView(testTheater);
    testService = new ReservationsService(testTheater);
    testCommandLineProcessor = new CommandLineProcessor(testService, testView);

  }

  @Test
  void processCaseEnd() {
    ByteArrayOutputStream outContent1 = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outContent1));
    testCommandLineProcessor.process(input1);
    assertEquals("Have a nice day!", outContent1.toString().trim());
  }

  @Test
  void processCaseReserveInvalidInput() {
    ByteArrayOutputStream outContent2 = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outContent2));
    testCommandLineProcessor.process(input2);
    String expected = "Number of seats request to be reserved should be in the range 1 to 26 inclusively";
    assertEquals(expected, outContent2.toString().trim());
  }


  @Test
  void requestReservationInfo() {
    String input = "John\nyes\n";

    // Redirect standard input stream to simulate user input
    System.setIn(new ByteArrayInputStream(input.getBytes()));

    // Call the method under test
    testCommandLineProcessor.requestReservationInfo();

    // Assert the expected results
    assertEquals("John", testCommandLineProcessor.getNameOfPerson());
    assertTrue(testCommandLineProcessor.getAccessibleNeed());

  }
}




