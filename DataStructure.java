//Stores & Sorts all coordinates and stores the amount of outgoing moves

public class DataStructure {
  private int row, column, moves;

  public DataStructure (int ROW, int COLUMN, int MOVES) {
    row = ROW;
    column = COLUMN;
    moves = MOVES;
  }

  public int compareTo(DataStructure x) {
    if (getMoves() > x.getMoves()) return 1;
    if (getMoves() < x.getMoves()) return -1;
    return 0; //if they are equal
  }

//accessor methods
  public int getRow() {
    return row;
  }
  public int getCol() {
    return col;
  }
  public int getMoves() {
    return moves;
  }

  public void decreaseMove() {
    if (moves > 0) moves = moves -1;
  }

  public void setZero() {
    moves = 0;
  }
}
