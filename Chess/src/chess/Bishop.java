package chess;

import java.util.ArrayList;

public class Bishop extends Piece {

	public Bishop(int row, int col, Color color, PieceType identifier) {
		super(row, col, color, identifier);
	}

	@Override
	public Tile[] getAvailableMoves(Tile[][] board) {
		ArrayList<Tile> legalMoves = new ArrayList<Tile>();
		boolean flag = false;

		int row1 = row - 1;
		int col1 = col - 1;
		// Check up diagonal left
		while (row1 >= 0 && col1 >= 0) {
			if (!board[row1][col1].isOccupied && !flag) {
				legalMoves.add(board[row1][col1]);
			} else if (board[row1][col1].isOccupied && board[row1][col1].piece.color != this.color && !flag) {
				legalMoves.add(board[row1][col1]);
				flag = true;
			} else if (board[row1][col1].isOccupied && board[row1][col1].piece.color == this.color && !flag) {
				flag = true;
			}
			col1 -= 1;
			row1 -= 1;
		}
		flag = false;

		row1 = row - 1;
		col1 = col + 1;
		// Check up diagonal right
		while (row1 >= 0 && col1 < 8) {
			if (!board[row1][col1].isOccupied && !flag) {
				legalMoves.add(board[row1][col1]);
			} else if (board[row1][col1].isOccupied && board[row1][col1].piece.color != this.color && !flag) {
				legalMoves.add(board[row1][col1]);
				flag = true;
			} else if (board[row1][col1].isOccupied && board[row1][col1].piece.color == this.color && !flag) {
				flag = true;
			}
			col1 += 1;
			row1 -= 1;
		}
		flag = false;

		row1 = row + 1;
		col1 = col - 1;
		// Check down diagonal left on the file
		while (row1 < 8 && col1 >= 0) {
			if (!board[row1][col1].isOccupied && !flag) {
				legalMoves.add(board[row1][col1]);
			} else if (board[row1][col1].isOccupied && board[row1][col1].piece.color != this.color && !flag) {
				legalMoves.add(board[row1][col1]);
				flag = true;
			} else if (board[row1][col1].isOccupied && board[row1][col1].piece.color == this.color && !flag) {
				flag = true;
			}
			col1 -= 1;
			row1 += 1;
		}
		flag = false;

		row1 = row + 1;
		col1 = col + 1;
		// Check down diagonal right on the file
		while (row1 < 8 && col1 < 8) {
			if (!board[row1][col1].isOccupied && !flag) {
				legalMoves.add(board[row1][col1]);
			} else if (board[row1][col1].isOccupied && board[row1][col1].piece.color != this.color && !flag) {
				legalMoves.add(board[row1][col1]);
				flag = true;
			} else if (board[row1][col1].isOccupied && board[row1][col1].piece.color == this.color && !flag) {
				flag = true;
			}
			col1 += 1;
			row1 += 1;

		}
		flag = false;

		Tile[] ret = new Tile[legalMoves.size()];
		System.out.print("arraylist: " + legalMoves.size() + " ");
		System.out.print("array: " + ret.length + "\n");
		return legalMoves.toArray(ret);
	}
}
