public class nQueens {

    public static void main(String[] args) {
        // Board and queen object
        // Queen queen = new Queen(0, 0);

        int[][] boardArray = new int[8][8];
        Board board = new Board(boardArray, 8); // This the number of queens to be placed

        if (placeQueens(board, 0)) {
            System.out.println("0 means empty space, 1 means queen");
            board.printBoard();
        } else {
            System.out.print("No Solution");
        }

    }

    // Make a class Queen that has a row and column
    public static class Queen {
        private int row;
        private int col;

        public Queen(int row, int col) {
            this.row = row;
            this.col = col;
        }

        public int getRow() {
            return row;
        }

        public int getCol() {
            return col;
        }

        public void setRow(int row) {
            this.row = row;
        }

        public void setCol(int col) {
            this.col = col;
        }

        // Method to see the ways a queen can move
        public boolean canMove(int row, int col) {
            // If the row or column is the same, then it can move
            if (this.row == row || this.col == col) {
                return true;
            }

            // If the row and column are the same, then it can move diagonally
            if (Math.abs(this.row - row) == Math.abs(this.col - col)) {
                return true;
            }

            return false;
        }
    }

    // Board class

    public static class Board {
        private int[][] board;
        private int size;

        public Board(int[][] board, int size) {
            this.board = board;
            this.size = size;
        }

        // Print the board
        public void printBoard() {
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[i].length; j++) {
                    if (board[i][j] == 0) {
                        System.out.print("- ");
                    } else {
                        System.out.print("Q ");
                    }
                }
                System.out.println();
            }
        }

        // Method to see if the queen can be placed in the board
        public boolean canPlace(int row, int col) {
            // Check if the existing queen can move to the row and column
            for (int i = 0; i < size; i++) {
                for (int j = 0; j < size; j++) {
                    if (this.board[i][j] == 1) {
                        Queen queen = new Queen(i, j);
                        if (queen.canMove(row, col)) {
                            return false;
                        }
                    }
                }

            }

            return true;
        }

        // Method to place the queen in the board
        public void placeQueen(int row, int col) {
            board[row][col] = 1;
        }

        // Method to remove the queen from the board
        public void removeQueen(int row, int col) {
            board[row][col] = 0;
        }

    }

    // Recursive method to place the queens
    public static boolean placeQueens(Board board, int noQueens) {
        // If the column is greater than the size of the board, then return true because
        // all the queens have been placed
        if (noQueens >= board.size) {
            return true;
        }

        // Loop through the board to place the queen in the row and column
        // If it can't be placed, then remove the queen and try the next row
        // If the queen can't be placed in any row, then return false
        for (int i = 0; i < board.board.length; i++) {
            if (board.canPlace(i, noQueens)) {
                board.placeQueen(i, noQueens);
                // If the queen can be placed, then place the next queen
                if (placeQueens(board, noQueens + 1)) {
                    // If the queen can be placed, then return true
                    return true;
                }
                // If the queen can't be placed, then remove the queen and try the next row
                board.removeQueen(i, noQueens);
            }
        }
        // If the queen can't be placed in any row, then return false
        return false;
    }

}