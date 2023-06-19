package problem1.model;

public class NormalSeatStrategy implements ReservationStrategy{

  /**
   * @param theater - the theater to be reserved
   * @param numOfReservation - the number of seat to be reserved
   * @param nameOfPerson - the name of the person who request reservation
   * @return - row number of the seats that have been successfully reserved
   */
  @Override
  public Integer reserveSeats(Theater theater, Integer numOfReservation, String nameOfPerson) {

    for (Row currRow : theater) {
      if (currRow.isRowAvailable(numOfReservation)) {
        theater.reserve(currRow.getRowNumber(), numOfReservation, nameOfPerson);
        return currRow.getRowNumber();
      }
    }

    // if no available row, return -1
    return -1;
  }
}

