public class KnightBoard {
  private int[][] board1;
  private int[][] board2;

  /**
  @throws IllegalArgumentException when either parameter is negative.
  Initializes the board to the correct size and make all spaces 0's
  */
  public KnightBoard(int startingRows,int startingCols) {
    if (startingCols < 0 || startingRows < 0) throw IllegalArgumentException();
    board1 = new int[startingRows][startingCols];
    board2 = new int[startingRows][startingCols];
    clear1();
    clear2();
  }

  private void clear1(){
    for (int x = 0; x < board1.length; x++) {
      for (int y = 0; y < board1[x].length; x++) {
        board1[x][y] = 0;
      }
    }
  }

  private void clear2(){
    for (int x = 0; x < board2.length; x++) {
      for (int y = 0; y < board2[x].length; x++) {
        board1[x][y] = 0;
      }
    }
  }


}
