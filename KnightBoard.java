import java.util.ArrayList;
import java.util.Collections;

public class KnightBoard {
  private int[][] board;
  private DataStructure[][] board2;

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

  //helper addKnight for optimization
  private boolean addKnightO(int row, int col) {
    if (row < 0 || row >= board.length) return false;
    if (col < 0 || col >= board[0].length) return false;
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
  Leaves board in a solved state
  */
  public boolean solve(int startingRow, int startingCol) {
    if (startingRow < 0 || startingRow >= board.length) throw new IllegalArgumentException();
    if (startingCol < 0 || startingRow >= board[0].length) throw new IllegalArgumentException();
    detectNon0();
    return solveH(startingRow,startingCol,1);
  }

  //solve helper method
  private boolean solveH(int row, int col, int moveNumber) {
    //base case: if the moveNumber > area, then all spaces are filled, and thus, true
    if (moveNumber > board.length * board[0].length) return true;
    if (addKnight(row,col,moveNumber)) {
      //tests each possible move by branching out - if base case is reached, returns true
      if (solveH(row+2,col-1,moveNumber+1)) return true;
      if (solveH(row+2,col+1,moveNumber+1)) return true;
      if (solveH(row+1,col-2,moveNumber+1)) return true;
      if (solveH(row+1,col+2,moveNumber+1)) return true;

      if (solveH(row-2,col-1,moveNumber+1)) return true;
      if (solveH(row-2,col+1,moveNumber+1)) return true;
      if (solveH(row-1,col-2,moveNumber+1)) return true;
      if (solveH(row-1,col+2,moveNumber+1)) return true;
      removeKnight(row,col); //otherwise, removes Knight, and deems it unsolvable
    }
    return false;
  }

  //helper method: IllegalStateException() thrown when nonzero value is found
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
  Does NOT leave board in solved state
  */
  public int countSolutions(int startingRow, int startingCol) {
    detectNon0();
    if (startingRow < 0 || startingRow >= board.length) throw new IllegalArgumentException();
    if (startingCol < 0 || startingRow >= board[0].length) throw new IllegalArgumentException();
    int ret = countSolutionsH(startingRow,startingCol,1);
    clear();
    return ret;
  }

  private int countSolutionsH(int row, int col, int moveNumber) {
    int count = 0;
    if (addKnight(row,col,moveNumber)) {
      //base case: if moveNumber == area, then that means one solution is found
      if (moveNumber == board.length * board[0].length) {
        removeKnight(row,col);
        return 1;
      } else {
        //tests each possible move by branching out
        //count adds on to itself
        //if the knight can't be placed, count is returned, goes onto the next test case
        //keeps on adding count onto itself until all branches are filled
          count = count + countSolutionsH(row+2,col-1,moveNumber+1);
          count = count + countSolutionsH(row+2,col+1,moveNumber+1);
          count = count + countSolutionsH(row+1,col-2,moveNumber+1);
          count = count + countSolutionsH(row+1,col+2,moveNumber+1);

          count = count + countSolutionsH(row-2,col-1,moveNumber+1);
          count = count + countSolutionsH(row-2,col+1,moveNumber+1);
          count = count + countSolutionsH(row-1,col-2,moveNumber+1);
          count = count + countSolutionsH(row-1,col+2,moveNumber+1);
      }
      removeKnight(row,col);
    }
    return count;
  }

  //Optimization time yay
  private boolean solveOptimize(int row, int col) {
    if (startingRow < 0 || startingRow >= board.length) throw new IllegalArgumentException();
    if (startingCol < 0 || startingRow >= board[0].length) throw new IllegalArgumentException();
    detectNon0();
    return solveOptimizeH(row,col,1);
  }

  //determines number of outgoing moves
  private void determineOutMoves() {
    for (int x = 0; x < board2.length; x++) {
      for (int y = 0; y < board2[0].length; y++) {
        int move = 0;
        if (addKnight(x+2,y-1)) move=move+1;
        if (addKnight(x+2,y+1)) move=move+1;
        if (addKnight(x+1,y-2)) move=move+1;
        if (addKnight(x+1,y+2)) move=move+1;

        if (addKnight(x-2,y+1)) move=move+1;
        if (addKnight(x-2,y-1)) move=move+1;
        if (addKnight(x-1,y+2)) move=move+1;
        if (addKnight(x-1,y-2)) move=move+1;
        board2[x][y] = new DataStructure(x,y,move);
      }
    }
  }

  private boolean solveOptimizeH(int row, int col, int move) {
    ArrayList<DataStructure> moveList = new ArrayList<DataStructure>();
    if (move > board.length * board[0].length) return true;

    


  }
}
