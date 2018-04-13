package chess;

import java.util.ArrayList;

public class Queen extends Piece {

	public Queen(int row, int col, PlayerEnum color, PieceType identifier) {
		super(row, col, color, identifier);
	}
	
	public Queen(Piece piece) {
		super(piece);
	}

	public ArrayList<Tile> getAvailableMoves(Board board) {
		ArrayList<Tile> legalMoves = new ArrayList<Tile>();
		
		legalMoves.addAll(new Rook(this).getAvailableMoves(board));
		legalMoves.addAll(new Bishop(this).getAvailableMoves(board));
		
		return legalMoves;
	}
	
	@Override
	public boolean isValidMove(Tile[][] board, int fromRow, int fromCol, int toRow, int toCol) {
		boolean r = new Rook(this).isValidMove(board, fromRow, fromCol, toRow, toCol);
		boolean b = new Bishop(this).isValidMove(board, fromRow, fromCol, toRow, toCol);
		
		return r | b;
	}
}
