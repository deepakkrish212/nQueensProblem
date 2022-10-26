# 8 Queens problem

# Create a Queen class, which has a row and column attribute
class Queen:
    def __init__(self, row, col):
        self.row = row
        self.col = col

    # method to see the ways in which the queen can attack given the x nd y coordinates
    def canAttack(self, x, y):
        if self.row == x or self.col == y:
            return True
        # Check if the queen is on the same diagonal
        elif abs(self.row - x) == abs(self.col - y):
            return True
        else:
            return False

# create a board class with a 2D array


class Board:
    def __init__(self, size):
        self.board = [["-" for i in range(size)] for j in range(size)]
        self.size = size

    # method to print the board
    def printBoard(self):
        for i in range(self.size):
            for j in range(self.size):
                print(self.board[i][j], end=" ")
            print()

    # method to check if the queen can be placed at a given position
    def canPlace(self, row, col):
        for i in range(self.size):
            for j in range(self.size):
                if self.board[i][j] == "Q":
                    if Queen(i, j).canAttack(row, col):
                        return False
        return True

    # method to place the queen at a given position
    def placeQueen(self, row, col):
        self.board[row][col] = "Q"

    # method to remove the queen from a given position
    def removeQueen(self, row, col):
        self.board[row][col] = "-"


# Recursive function to solve the 8 queens problem
def solve(board, col):
    if col >= board.size:
        return True
    for i in range(board.size):
        if board.canPlace(i, col):
            board.placeQueen(i, col)
            if solve(board, col + 1):
                return True
            board.removeQueen(i, col)
    return False


# Main function
def main():
    board = Board(int(input("Enter the size of the board: ")))
    # no_of_queens = int(input("Enter the number of queens: "))
    if solve(board, 0):
        board.printBoard()
    else:
        print("No solution")


main()
