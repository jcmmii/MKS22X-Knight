public class KnightBoard {
  private int[][] board;

  /**
  @throws IllegalArgumentException when either parameter is negative or zero
  Initializes the board to the correct size and make all spaces 0's
  */
  public KnightBoard(int startingRows,int startingCols) {
    if (startingCols <= 0 || startingRows <= 0) throw new IllegalArgumentException();
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

  //helper methods: adding & removing Knights
  private boolean addKnight(int row, int col, int num) {
    if (row < 0 || row >= board.length) return false;
    if (col < 0 || col >= board[0].length) return false;
    if (board[row][col]==0) {
      board[row][col] = num;
      return true;
    }
    return false;
  }

  private boolean removeKnight(int row, int col) {
    board[row][col] = 0;
    return true;
  }

  /**
  Blank board returned if solve()is never called or when there is no solution
  0s are displayed as underscores
  @returns the properly formatted string
  */
  public String toString() {
    String ret = "";
    for (int x = 0; x < board.length; x++) {
      for (int y = 0; y < board[x].length; y++) {
        if (board[x][y] == 0) {
          ret = ret + " _ ";
        }else {
          if (board[x][y] < 10) {
            ret = ret + " " + board[x][y] + " ";
          } else {
            ret = ret + board[x][y] + " ";
          }
        }
      }
      ret = ret + "\n";
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
    if (startingRow < 0 || startingRow >= board.length) throw new IllegalArgumentException();
    if (startingCol < 0 || startingRow >= board[0].length) throw new IllegalArgumentException();
    detectNon0();
    return solveH(startingRow,startingCol,1);
  }

  //solve helper method
  private boolean solveH(int row, int col, int moveNumber) {
    if (moveNumber > board.length * board[0].length) return true;
    if (addKnight(row,col,moveNumber)) {
      if (solveH(row+2,col-1,moveNumber+1)) return true;
      if (solveH(row+2,col+1,moveNumber+1)) return true;
      if (solveH(row+1,col-2,moveNumber+1)) return true;
      if (solveH(row+1,col+2,moveNumber+1)) return true;

      if (solveH(row-2,col-1,moveNumber+1)) return true;
      if (solveH(row-2,col+1,moveNumber+1)) return true;
      if (solveH(row-1,col-2,moveNumber+1)) return true;
      if (solveH(row-1,col+2,moveNumber+1)) return true;
      removeKnight(row,col);
    }
    return false;
  }

  //helper method: IllegalStateException thrown when nonzero value is found
  private void detectNon0() {
    for (int x = 0; x < board.length; x++) {
      for (int y = 0; y < board[x].length; y++) {
        if (board[x][y] != 0) throw new IllegalStateException();
      }
    }
  }

  /**
  @throws IllegalStateException when the board contains non-zero values.
  @throws IllegalArgumentException when either parameter is negative or out of bounds.
  @returns the number of solutions from the starting position specified
  */
  public int countSolutions(int startingRow, int startingCol) {
    detectNon0();
    if (startingRow < 0 || startingRow >= board.length) throw new IllegalArgumentException();
    if (startingCol < 0 || startingRow >= board[0].length) throw new IllegalArgumentException();
    int ret = countSolutionsH(startingRow,startingCol,0);
    clear();
    return ret;
  }

  private int countSolutionsH(int startingRow, int startingCol, int count) {
    if (

  }

}

//move in a set order [non optimzation] THEN use optimzation
