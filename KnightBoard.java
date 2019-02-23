public class KnightBoard {
  private int[][] board;

  /**
  @throws IllegalArgumentException when either parameter is negative or zero
  Initializes the board to the correct size and make all spaces 0's
  */
  public KnightBoard(int startingRows,int startingCols) {
    if (startingCols <= 0 || startingRows <= 0) throw IllegalArgumentException();
    board = new int[startingRows][startingCols];
    clear();
  }

  //helper clear methods to fill boards with 0s
  private void clear() {
    for (int x = 0; x < board.length; x++) {
      for (int y = 0; y < board[x].length; y++) {
        board[x][y] = 0;
      }
    }
  }

  public String toString() {
    String ret = "";
    for (int x = 0; x < board.length; x++) {
      for (int y = 0; y < board[x].length; y++) {
        if (board[x][y] < 10) {
          ret = ret + " " + board[x][y] + " ";
        } else {
          ret = ret + board[x][y] + " ";
        }
        ret = ret + "\n";
      }
    }
    return ret;
  }

  /**
  Modifies the board by labeling the moves from 1 (at startingRow,startingCol)
  up to the area of the board in proper knight move steps.
  @throws IllegalStateException when the board contains non-zero values.
  @throws IllegalArgumentException when either parameter is negative
  or out of bounds.
  @returns true when the board is solvable from the specified starting position
  */
  public boolean solve(int startingRow, int startingCol) {
    if (startingRow < 0 || startingRow >= board.length) throw IllegalArgumentException();
    if (startingCol < 0 || startingRow >= board[0].length) throw IllegalArgumentException();
    detectNon0();
    solveH(startingRow,startingCol,1);
  }

  //solve helper method
  private boolean solveH(int row, int col, int moveNumber) {

  }

  //helper method: IllegalStateException thrown when nonzero value is found
  private void detectNon0() {
    for (int x = 0; x < board.length; x++) {
      for (int y = 0; y < board[x].length; y++) {
        if (board[x][y] != 0) throw IllegalStateException();
      }
    }
  }

  /**
  @throws IllegalStateException when the board contains non-zero values.
  @throws IllegalArgumentException when either parameter is negative or out of bounds.
  @returns the number of solutions from the starting position specified
  */
  public int countSolutions(int startingRow, int startingCol){


  }

}

//move in a set order [non optimzation] THEN use optimzation
