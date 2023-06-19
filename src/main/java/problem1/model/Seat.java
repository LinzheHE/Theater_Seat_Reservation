package problem1.model;

public class Seat {

  /**
   * class problem1.model.Seat represents every single seat in rows in the theater. It stores the information of
   * the seat's name and the name of the person for whom it has been reserved
   */
  private String name;
  private String reservedFor;

  /**
   * the private constructor of the class
   * @param name - A name, which is a string value representing a capital letter from A to Z.
   * the "reserved for" value is set to null when initializing.
   */
  private Seat(String name) {
    this.name = name;
    this.reservedFor = null;
  }

  /**
   * SeatFactory class responsible for creating seat objects of the problem1.model.Seat class
   */
  public static class SeatFactory {
    public static Seat createSeat(String name) {
      return new Seat(name);
    }
  }


  /**
   * get the seat's name
   * @return the name of the seat
   */
  public String getName() {
    return name;
  }

  /**
   * get the name of the person for whom it has been reserved
   * @return the name of the person
   */
  public String getReservedFor() {
    return reservedFor;
  }

  /**
   * reserve the seat
   * @param nameOfPerson - the name of the person for whom it has been reserved
   */
  public void reserve(String nameOfPerson) {
    this.reservedFor = nameOfPerson;
  }


  /**
   * show a particular format of the seat depending on whether the seat is in rows that are wheelchair-accessible.
   * @param isAccessible - whether the seat is in rows that are wheelchair-accessible.
   * @return a particular format of the seat
   */
  public String toStringWithOptions(Boolean isAccessible) {
    if (this.reservedFor != null) {
      return "X ";
    } else {
      if (isAccessible) {
        return "= ";
      } else {
        return "_ ";
      }
    }
  }
}