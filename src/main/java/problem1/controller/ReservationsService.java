package problem1.controller;

import problem1.model.AccessibleSeatStrategy;
import problem1.model.NormalSeatStrategy;
import problem1.model.ReservationStrategy;
import problem1.model.Theater;

/**
 * Class to handle the reservation service
 */
public class ReservationsService {
  private Theater theater;
  private ReservationStrategy reservationStrategy;


  /**
   * Constructor for the reservation service class
   * @param theater -- Theater, the theater to reserve seats in
   */
  public ReservationsService(Theater theater) {
    this.theater = theater;
  }

  /**
   * Method to call corresponding reservation strategies based on given information
   * @param numOfReservation -- Integer, number of seats to reserve
   * @param nameOfPerson -- String, customer's name for the reservation
   * @param accessibleNeed -- Boolean, whether need wheelchair accessible rows
   * @return -- Integer, the optical row number for the reservation
   */
  public Integer reserve(Integer numOfReservation, String nameOfPerson, Boolean accessibleNeed) {
    this.setReservationStrategy(accessibleNeed);
    return this.reservationStrategy.reserveSeats(this.theater, numOfReservation, nameOfPerson);
  }

  /**
   * Helper method to set the suitable reservation strategy
   * @param accessibleNeed -- Boolean, whether need wheelchair accessible rows
   */
  private void setReservationStrategy(Boolean accessibleNeed) {
    if (accessibleNeed)
      this.reservationStrategy = new AccessibleSeatStrategy();
    else
      this.reservationStrategy = new NormalSeatStrategy();
  }
}
