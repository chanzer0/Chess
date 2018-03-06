package chess;

import java.util.ArrayList;

public class Knight extends Piece{
	
	public Knight(int row, int col, Color color, PieceType identifier) {
		super(row, col, color, identifier);
	}

	public Tile[] getAvailableMoves(Tile[][] board) {
		ArrayList<Tile> legalMoves = new ArrayList<Tile>();
		
		if(((this.row + 2) <= 7) && ((this.col + 1) <= 7) && ((board[this.row+2][this.col+1].isOccupied == false) || (board[this.row+2][this.col+1].piece.color != this.color))){
			legalMoves.add(board[this.row+2][this.col+1]);
		}
		if(((this.row + 2) <= 7) && ((this.col - 1) >= 0) && ((board[this.row+2][this.col-1].isOccupied == false) || (board[this.row+2][this.col-1].piece.color != this.color))){
			legalMoves.add(board[this.row+2][this.col-1]);
		}
		if(((this.row - 2) >= 0) && ((this.col + 1) <= 7) && ((board[this.row-2][this.col+1].isOccupied == false) || (board[this.row-2][this.col+1].piece.color != this.color))){
			legalMoves.add(board[this.row-2][this.col+1]);
		}
		if(((this.row - 2) >= 0) && ((this.col - 1) >= 0) && ((board[this.row-2][this.col-1].isOccupied == false) || (board[this.row-2][this.col-1].piece.color != this.color))){
			legalMoves.add(board[this.row-2][this.col-1]);
		}
		if(((this.col + 2) <= 7) && ((this.row + 1) <= 7) && ((board[this.row+1][this.col+2].isOccupied == false) || (board[this.row+1][this.col+2].piece.color != this.color))){
			legalMoves.add(board[this.row+1][this.col+2]);
		}
		if(((this.col + 2) <= 7) && ((this.row - 1) >= 0) && ((board[this.row-1][this.col+2].isOccupied == false) || (board[this.row-1][this.col+2].piece.color != this.color))){
			legalMoves.add(board[this.row-1][this.col+2]);
		}
		if(((this.col - 2) >= 0) && ((this.row + 1) <= 7) && ((board[this.row+1][this.col-2].isOccupied == false) || (board[this.row+1][this.col-2].piece.color != this.color))){
			legalMoves.add(board[this.row+1][this.col-2]);
		}
		if(((this.col - 2) >= 0) && ((this.row - 1) >= 0) && ((board[this.row-1][this.col-2].isOccupied == false) || (board[this.row-1][this.col-2].piece.color != this.color))){
			legalMoves.add(board[this.row-1][this.col-2]);
		}
		
		Tile[] temp = new Tile[legalMoves.size()];
		return legalMoves.toArray(temp);
	}
}
