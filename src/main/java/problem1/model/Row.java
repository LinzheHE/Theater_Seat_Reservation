package problem1.model;

import java.util.ArrayList;
import problem1.model.Seat.SeatFactory;

public class Row extends ArrayList<Seat> {
  private Integer numSeats;
  private Integer rowNumber;
  private Boolean wheelchairAccessible;
  private Integer numOfAvailable;

  /**
   * Constructs an empty list with the specified initial capacity.
   * @param numSeats the number of seats in a row
   * @param rowNumber - a row number
   * @param wheelchairAccessible - whether it is wheelchair accessible.
   *
   * @throws IllegalArgumentException if the specified initial capacity is negative
   */
  private Row(Integer numSeats, Integer rowNumber, Boolean wheelchairAccessible) throws IllegalArgumentException{
    super(numSeats);
    this.numSeats = numSeats;
    this.rowNumber = rowNumber;
    this.wheelchairAccessible = wheelchairAccessible;
    this.numOfAvailable = numSeats;
    for (int i = 0; i < numSeats; i++) {
      this.add(SeatFactory.createSeat((char) ('A' + i) + ""));
    }
  }

  /**
   * RowFactory class responsible for creating row objects of the problem1.model.Row class
   */
  public class RowFactory {
    public static Row createRow(Integer numSeats, Integer rowNumber, Boolean wheelchairAccessible) {
      if (numSeats <= 0 || numSeats > 26)
        throw new IllegalArgumentException("Invalid seat number; the number of seats should be greater than zero and not greater than 26");
      else
        return new Row(numSeats, rowNumber, wheelchairAccessible);
    }
  }

  /**
   * Get the number of seats capacity of the row
   * @return -- the number of seats capacity of the row
   */
  public Integer getNumSeats() {
    return numSeats;
  }

  /**
   * Get the row number
   * @return -- the row number
   */
  public Integer getRowNumber() {
    return rowNumber;
  }

  /**
   * Get the wheelchair accessibility of the row
   * @return -- ture if the row is wheelchair accessible
   */
  public Boolean isWheelchairAccessible() {
    return wheelchairAccessible;
  }

  /**
   * Get the number of available seats in the row
   * @return -- the number of available seats
   */
  public Integer getNumOfAvailable(){
    return numOfAvailable;
  }

  /**
   * Method to check whether the row has enough seats to reserve
   * @param numOfReservation -- the number of seats to reserve for current reservation
   * @return -- true if there are enough seats available in this row
   */
  public Boolean isRowAvailable(Integer numOfReservation) {
    if (this.numOfAvailable >= numOfReservation) {
      return Boolean.TRUE;
    } else {
      return Boolean.FALSE;
    }
  }

  /**
   * Method to reserve seats in this row, and update this row's numOfAvailable
   * Always reserve from the left to the right
   * @param numOfReservation -- the number of seats to reserve for current reservation
   * @param nameOfPerson -- the customer's name for current reservation
   */
  public void reserve(Integer numOfReservation, String nameOfPerson) {
    // reserve seats: update the reserveFor for each newly reserved seats
    int startSeatIndex = this.numSeats - this.numOfAvailable;
    for (int i = 0; i < numOfReservation; i++) {
      this.get(startSeatIndex + i).reserve(nameOfPerson);
    }

    // update this.numOfAvailable
    this.numOfAvailable -= numOfReservation;
  }

  /**
   * show the information of a row in the required format
   * @return the information of a row in the required format
   */
  @Override
  public String toString() {
    String seatInfo = "";
    for (Seat currSeat : this) {
      seatInfo += currSeat.toStringWithOptions(this.isWheelchairAccessible());

    }
    return rowNumber + " " + seatInfo + "\n";
  }

}