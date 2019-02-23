public class KnightBoard {
  private int[][] board1; //actual return board
//  private int[][] board2; //background board that keeps count of # of moves
  private boolean solve;

  /**
  @throws IllegalArgumentException when either parameter is negative or zero
  Initializes the board to the correct size and make all spaces 0's
  */
  public KnightBoard(int startingRows,int startingCols) {
    if (startingCols =< 0 || startingRows =< 0) throw IllegalArgumentException();
    board1 = new int[startingRows][startingCols];
    board2 = new int[startingRows][startingCols];
    clear1();
  //  clear2();
  }

  //helper clear methods to fill boards with 0s
  private void clear1() {
    for (int x = 0; x < board1.length; x++) {
      for (int y = 0; y < board1[x].length; x++) {
        board1[x][y] = 0;
      }
    }
  }

/*
  private void clear2() {
    for (int x = 0; x < board2.length; x++) {
      for (int y = 0; y < board2[x].length; x++) {
        board1[x][y] = 0;
      }
    }
  }
*/


  public String toString() {
    String ret = "";
    for (int x = 0; x < board1.length; x++) {
      for (int y = 0; y < board1[x].length; y++) {
        if (board1[x][y] < 10) {
          ret = ret + " " + board1[x][y] + " ";
        } else {
          ret = ret + board1[x][y] + " ";
        }
        ret = ret + "\n";
      }
    }
    return ret;
  }

  //public boolean solve(int startingRow, int startingCol) {}

  //public int countSolutions(int startingRow, int startingCol){}

}

//move in a set order [non optimzation] THEN use optimzation
