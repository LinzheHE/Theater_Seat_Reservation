package problem1.view;


import problem1.model.Theater;

/**
 * Class to display information in the command lines
 */
public class ReservationView {

  private Theater theater;

  /**
   * Constructor for the ReservationView
   * @param theater -- Theater, the theater to process the reservation
   */
  public ReservationView(Theater theater) {
    this.theater = theater;
  }

  /**
   * Method to show current seats arrangement of the theater
   */
  public void displayTheater() {
    System.out.println(this.theater.toString());
  }

  /**
   * Method to display reservation confirmation after successful reservation
   * @param numOfReservation -- Integer, the number of seats of the reservation
   * @param rowNumber -- Integer, the reserved row number
   * @param nameOfPerson -- String, the name of the person reserved for
   */
  public void displayReservationConfirmation(Integer numOfReservation, Integer rowNumber, String nameOfPerson) {
    System.out.println("I’ve reserved " + numOfReservation + " seats for you at the " + this.theater.getName() + " in row " + rowNumber + ", " + nameOfPerson + ".");
  }

  /**
   * Static method to display failed reservation message when no seats available
   */
  public static void displayFailedReservationMessage() {
    System.out.println("Sorry, we don’t have that many seats together for you.");
  }

  /**
   * Static method to display prompt message
   * @param prompt -- String, prompt message to display
   */
  public static void displayPrompt(String prompt) {
    System.out.println(prompt);
  }

  /**
   * Static method to display command line instruction when user input something unexpected
   */
  public static void displayCommandInstruction() {
    System.out.println("Please try again! You may enter:");
    System.out.println("\"reserve <number>\" to reserve that number of seats");
    System.out.println("\"show\" to display the current available seating in the theater");
    System.out.println("\"done\" to shut down the system");
  }



}
