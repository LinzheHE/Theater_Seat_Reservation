package problem1.controller;


import java.util.Arrays;
import java.util.Objects;
import java.util.Scanner;
import problem1.view.ReservationView;


/**
 * Class CommandLineProcessor is responsible for parsing user input, extracting relevant information, performs necessary validation and trigger appropriate actions.
 */
public class CommandLineProcessor {
  public static final String END_SESSION = "done";
  public static final String RESERVE = "reserve";
  public static final String SHOW = "show";
  public static final Integer RESERVATION_FAILURE = -1;

  private Boolean accessibleNeed;
  private Integer numOfReservation;
  private String nameOfPerson;


  private ReservationsService reservationService;
  private ReservationView reservationView;

  /**
   * constructor for the command line processor
   * @param reservationService -- ReservationsService, the reservation service object to process on
   * @param reservationView -- ReservationView, the reservation view object to work with
   */
  public CommandLineProcessor(ReservationsService reservationService, ReservationView reservationView) {
    this.reservationService = reservationService;
    this.reservationView = reservationView;
  }

  /**
   * Get the accessible need for current reservation
   * @return -- Boolean, true when need wheelchair accessible row
   */
  public Boolean getAccessibleNeed() {
    return this.accessibleNeed;
  }

  /**
   * Get the number of seats to reserve for current reservation
   * @return -- Integer, the number of seats to reserve
   */
  public Integer getNumOfReservation() {
    return this.numOfReservation;
  }

  /**
   * Get the customer's name for current reservation
   * @return -- String, the customer's name
   */
  public String getNameOfPerson() {
    return this.nameOfPerson;
  }

  /**
   * Key method to parse and validate user input, then trigger appropriate actions
   * @param input -- String, user input in the command line
   */
  public boolean process(String input) {
    String[] args = transform(input);
    try {
      switch(args[0]) {
        case END_SESSION:
          ReservationView.displayPrompt("Have a nice day!");
          return false;
        case SHOW:
          this.reservationView.displayTheater();
          break;
        case RESERVE:
          int seatRequest = Integer.parseInt(args[1]);
          if (seatRequest <= 0 || seatRequest > 26) {
            ReservationView.displayPrompt(
                "Number of seats request to be reserved should be in the range 1 to 26 inclusively");
          }
          else {
            this.numOfReservation = seatRequest;
            requestReservationInfo();
            processReservation();
          }
          break;
        default:
          ReservationView.displayPrompt("Invalid Command");
          ReservationView.displayCommandInstruction();
      }
    } catch (NumberFormatException e1) {
        ReservationView.displayPrompt("Reserve command must be followed by an integer.");
    } catch (IndexOutOfBoundsException e2) {
        ReservationView.displayPrompt("Invalid Command");
        ReservationView.displayCommandInstruction();
    }
    return true;
  }


  /**
   * Helper method to get and set nameOfPerson and accessibleNeed
   */
  public void requestReservationInfo() {
      Scanner infoScan = new Scanner(System.in);
      ReservationView.displayPrompt("What’s your name?");
      this.nameOfPerson = infoScan.nextLine();

      String answer = "";
      while (!(answer.equals("yes") || answer.equals("no"))) {
        ReservationView.displayPrompt("Do you need wheelchair accessible seats? Please input Yes or No");
        answer = infoScan.nextLine().trim().toLowerCase();
      }
      if (answer.equals("yes"))
        this.accessibleNeed = Boolean.TRUE;
      else
        this.accessibleNeed = Boolean.FALSE;
  }

  /**
   * Helper method to process the reservation, and display reservation result
   */
  private void processReservation() {
    Integer rowNumber = this.reservationService.reserve(this.numOfReservation, this.nameOfPerson, this.accessibleNeed);
    if (Objects.equals(rowNumber, RESERVATION_FAILURE))
      ReservationView.displayFailedReservationMessage();
    else
      this.reservationView.displayReservationConfirmation(this.numOfReservation, rowNumber, nameOfPerson);
  }

  /**
   * Helper method to parse the command line input
   * @param input -- String, the information that users input in the command line
   * @return -- an array of String, which are the non-empty input split by space
   */
  private static String[] transform(String input) {
    input = input.trim().toLowerCase();
    String[] stringList = input.split(" ");
    return Arrays.stream(stringList).filter(s -> !s.isEmpty()).toArray(String[]::new);
  }



}
