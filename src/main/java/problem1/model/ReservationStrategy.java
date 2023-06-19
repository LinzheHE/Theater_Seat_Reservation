package problem1.model;

public interface ReservationStrategy {

  /**
   * @param theater - the theater to be reserved
   * @param numOfReservation - the number of seat to be reserved
   * @param nameOfPerson - the name of the person who request reservation
   * @return - row number of the seats that have been successfully reserved
   */
  Integer reserveSeats(Theater theater, Integer numOfReservation, String nameOfPerson);
}
