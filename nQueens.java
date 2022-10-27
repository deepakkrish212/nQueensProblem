import java.util.Scanner;

public class nQueens {

    public static void main(String[] args) {
        // Board and queen object
        // Queen queen = new Queen(0, 0);

        int[][] boardArray = new int[8][8];
        Board board = new Board(boardArray, 8);

        if (placeQueens(board, 0)) {
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
        private int queens;

        public Board(int[][] board, int queens) {
            this.board = board;
            this.queens = queens;
        }

        // Print the board
        public void printBoard() {
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[i].length; j++) {
                    System.out.print(board[i][j] + " ");
                }
                System.out.println();
            }
        }

        // Method to see if the queen can be placed in the board
        public boolean canPlace(Queen q) {
            // Check if the queen can move to the row and column
            for (int i = 0; i < queens; i++) {
                for (int j = 0; j < queens; j++) {
                    if (this.board[i][j] == 1) {
                        if (q.canMove(q.getRow(), q.getCol())) {
                            return false;
                        }
                    }
                }

            }

            return true;
        }

        // Method to place the queen in the board
        public void placeQueen(Queen q) {
            board[q.getRow()][q.getCol()] = 1;
        }

        // Method to remove the queen from the board
        public void removeQueen(Queen q) {
            board[q.getRow()][q.getCol()] = 0;
        }

    }

    // Recursive method to place the queens
    public static boolean placeQueens(Board board, int column) {
        // If the number of column is 0, then we have placed all the column
        if (column >= board.queens) {
            return true;
        }

        // Loop through the board
        for (int i = 0; i < board.board.length; i++) {
            for (int j = 0; j < board.board[i].length; j++) {
                // Create a new queen
                Queen q = new Queen(i, j);

                // If the queen can be placed in the board
                if (board.canPlace(q)) {
                    // Place the queen in the board
                    board.placeQueen(q);

                    // If the queen can be placed in the board
                    if (placeQueens(board, column + 1)) {
                        return true;
                    }

                    // Remove the queen from the board
                    board.removeQueen(q);
                }
            }
        }
        return false;
    }

}