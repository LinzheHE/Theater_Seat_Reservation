package problem1.model;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import problem1.model.Row.RowFactory;

public class Theater extends ArrayList<Row> {

  public static final Integer DEFAULT_ROW_NUMBER = 20;
  public static final Integer DEFAULT_SEAT_NUMBER = 26;
  public static final String DEFAULT_THEATER_NAME = "Roxy";
  public static final List<Integer> DEFAULT_ACCESSIBLE_ROWS = Arrays.asList(1, 6, 11, 16);

  private String name;
  private List<Integer> accessibleRows;
  private Integer numOfRows;


  /**
   * Constructs an empty list with the specified initial capacity.
   *
   * @param numOfRows      - The number of rows in a theater
   * @param numOfSeats     - The number of seats in a row
   * @param name           - The theater's name
   * @param accessibleRows - A non-empty list or array of integers indicating which of the rows are
   *                       accessible
   * @throws IllegalArgumentException if the specified initial capacity is negative
   */
  private Theater(Integer numOfRows, Integer numOfSeats, String name, List<Integer> accessibleRows) throws IllegalArgumentException {
    super(numOfRows);
    this.name = name;
    this.numOfRows = numOfRows;
    this.accessibleRows = accessibleRows;
    addRows(numOfRows, numOfSeats);
  }

  /**
   * TheaterFactory class responsible for creating theater objects of the problem1.model.Theater class
   */
  public class TheaterFactory {
    public static Theater createTheater(Integer numOfRows, Integer numOfSeats, String name, List<Integer> accessibleRows) {
      if (numOfRows <= 0)
        throw new IllegalArgumentException("Invalid number of rows; the number of rows should be greater than zero");
      else if (accessibleRows.isEmpty())
        throw new IllegalArgumentException("At least one row in the theater is wheelchair accessible!");
      else
        return new Theater(numOfRows, numOfSeats, name, accessibleRows);
    }

    public static Theater createTheater() {
      return new Theater(DEFAULT_ROW_NUMBER, DEFAULT_SEAT_NUMBER, DEFAULT_THEATER_NAME, DEFAULT_ACCESSIBLE_ROWS);
    }
  }

  /**
   * Get the name of the theater
   * @return -- name of the theater
   */
  public String getName() {
    return name;
  }

  /**
   * Get the list of wheelchair accessible row numbers
   * @return -- the list of wheelchair accessible row numbers
   */
  public List<Integer> getAccessibleRows() {
    return accessibleRows;
  }

  /**
   * Get the total number of rows in the theater
   * @return -- the total number of rows in the theater
   */
  public Integer getNumOfRows() {
    return numOfRows;
  }

  /**
   * Helper method to build the ArrayList<Row> with priority
   * best not wheelchair accessible row to the worst not wheelchair accessible row; Then best wheelchair accessible row to the worst wheelchair accessible row
   * @param numOfRows -- The number of rows in a theater
   * @param numOfSeats -- The number of seats in a row
   */
  private void addRows(Integer numOfRows, Integer numOfSeats) {
    // Add non-accessible rows, and get the list of ordered row numbers of accessible rows
    List<Integer> orderedAccessibleRows = addNonAccessibleRows(numOfRows, numOfSeats);
    // Add accessible rows
    addAccessibleRows(orderedAccessibleRows, numOfSeats);
  }

  /**
   * Helper method to add non-accessible rows, from the best row to the worst row
   * @param numOfRows -- The number of rows in a theater
   * @param numOfSeats -- The number of seats in a row
   * @return -- List<Integer>, the ordered accessible row numbers
   */
  private List<Integer> addNonAccessibleRows(Integer numOfRows, Integer numOfSeats) {
    // currentRow is the row processing, starts from the middle; frontRow and backRow are flags to find the next row to process; frontRow is the most front row processed, backRow is the most back row processed
    Integer currentRow = numOfRows / 2;
    Integer frontRow = numOfRows / 2;
    Integer backRow = numOfRows / 2;

    // create a List<Integer> to store not added wheelchair accessible rows
    List<Integer> orderedAccessibleRows = new ArrayList<>();

    // add not wheelchair accessible rows, from best to worst
    while (currentRow >= 0 && currentRow < numOfRows) {

      // add current row if it is not wheelchair accessible
      if (!(this.accessibleRows.contains(currentRow + 1))) {
        this.add(RowFactory.createRow(numOfSeats, currentRow + 1, Boolean.FALSE));
      } else {
        // store current row if it is wheelchair accessible
        orderedAccessibleRows.add(currentRow + 1);
      }

      // update frontRow, backRow, currentRow
      if (frontRow > (numOfRows - 1 - backRow)) {
        frontRow -= 1;
        currentRow = frontRow;
      } else {
        backRow += 1;
        currentRow = backRow;
      }
    }

    return orderedAccessibleRows;
  }

  /**
   * Helper method to add accessible rows, from the best row to the worst row
   * @param orderedAccessibleRows -- the ordered accessible row numbers
   * @param numOfSeats -- The number of seats in a row
   */
  private void addAccessibleRows(List<Integer> orderedAccessibleRows, Integer numOfSeats) {

    for (Integer rowNum : orderedAccessibleRows) {
      this.add(RowFactory.createRow(numOfSeats, rowNum, Boolean.TRUE));
    }
  }

  /**
   * Method to reserve seats in a given row
   * @param rowToReserve -- the row to reserve seats
   * @param numOfReservation -- the number of seats to reserve for current reservation
   * @param nameOfPerson -- the customer's name for current reservation
   */
  public void reserve(Integer rowToReserve, Integer numOfReservation, String nameOfPerson) {
    for (Row currRow : this) {
      if (currRow.getRowNumber() == rowToReserve) {
        currRow.reserve(numOfReservation, nameOfPerson);
      }
    }
  }

  /**
   * show the information of a theater in the required format
   * @return the information of a theater in the required format
   */
  @Override
  public String toString() {
    String theater="";
    List<Row> orderedRows= this.sortRowsForDisplay();
    for (Row currRow : orderedRows) {
      theater+= currRow.toString();
    }
    return theater;
  }

  /**
   * helper method to sort rows for display
   * @return the sorted list of rows
   */
  private List<Row> sortRowsForDisplay(){
    List<Row> orderedRows = new ArrayList<>(this);
    Collections.sort(orderedRows, Comparator.comparingInt(Row::getRowNumber));
    return orderedRows;
  }


}