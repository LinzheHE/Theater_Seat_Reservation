package problem1.controller;

import java.util.Scanner;
import problem1.model.Theater;
import problem1.model.Theater.TheaterFactory;
import problem1.view.ReservationView;

public class ReservationSystem {

  public static boolean sessionStatus = true;

  public static void main(String[] args) {

    // Since this is a system focusing on reservation process, here we set up theater with default name, accessible rows and number of rows and seats; if needed, system manager/administrator can adopt other overloaded createTheater method from the inner TheaterFactory class.
    Theater theater = TheaterFactory.createTheater();

    // Create a reservation service for the theater
    ReservationsService service = new ReservationsService(theater);

    // Create a reservation view to display the user interface
    ReservationView view = new ReservationView(theater);

    // Create a CLI processor to handle user input and trigger actions
    CommandLineProcessor processor = new CommandLineProcessor(service, view);

    // User interaction
    Scanner requestScan = new Scanner(System.in);

    while (sessionStatus) {
      ReservationView.displayPrompt("What would you like to do?");
      sessionStatus = processor.process(requestScan.nextLine());
    }



  }
}


