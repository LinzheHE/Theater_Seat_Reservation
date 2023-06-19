package problem1.view;

import static org.junit.jupiter.api.Assertions.*;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import problem1.model.Theater;
import problem1.model.Theater.TheaterFactory;

class ReservationViewTest {
  Theater testTheater;
  ReservationView testView;
  List<Integer> testAccessibleRows;

  @BeforeEach
  void setUp() {
    testAccessibleRows = Arrays.asList(1, 2);
    testTheater = TheaterFactory.createTheater(5, 5, "Theater1", testAccessibleRows);
    testView=new ReservationView(testTheater);
  }

  @Test
  void displayTheater() {
    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outContent));
    testView.displayTheater();
    String expected = testTheater.toString();
    String actual= outContent.toString();

    // handle the extra "\n" caused by println() method without using trim() method
    if (actual.endsWith("\n")){
      actual=actual.substring(0,actual.length()-1);
    }
    assertEquals(expected,actual);
  }

  @Test
  void displayReservationConfirmation() {
    ByteArrayOutputStream outContent= new ByteArrayOutputStream();
    System.setOut(new PrintStream(outContent));
    testView.displayReservationConfirmation(3,3,"Test Person");
    String expected = "I’ve reserved 3 seats for you at the Theater1 in row 3, Test Person.";
    assertEquals(expected,outContent.toString().trim());

  }

  @Test
  void displayFailedReservationMessage() {
    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outContent));
    testView.displayFailedReservationMessage();
    String expected ="Sorry, we don’t have that many seats together for you.";
    assertEquals(expected,outContent.toString().trim());

  }

  @Test
  void displayPrompt() {
    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outContent));
    testView.displayPrompt("test prompt");
    String expected = "test prompt";
    assertEquals(expected,outContent.toString().trim());
  }

  @Test
  void displayCommandInstruction() {
    ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    System.setOut(new PrintStream(outContent));
    testView.displayCommandInstruction();
    String expected = "Please try again! You may enter:"+'\n'+
        "\"reserve <number>\" to reserve that number of seats"+'\n'+
        "\"show\" to display the current available seating in the theater"+'\n'+
        "\"done\" to shut down the system";
    assertEquals(expected,outContent.toString().trim());
  }
}