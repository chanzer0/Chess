package chess;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public class CPU {

	private int level;
	private Board board;
	
	public CPU(int l, Board b) {
		this.level = l;
		this.board = b;
	}
	
	public void doMove() {
		Random rand = new Random();
		Piece toMove = getPiece();
		
		// select this tile, show available moves and all that junk
		this.board.selectTile(this.board.getTile(toMove.row, toMove.col));
		
		ArrayList<Tile> moves = toMove.getAvailableMoves(this.board);
		
		Tile moveTo = moves.get(rand.nextInt(moves.size()));
		
		for (Tile t: moves) {
			if (t.isOccupied && t.piece.color != toMove.color) moveTo = t;
		}
		
		this.board.selectTile(moveTo);
	}
	
	public Piece getPiece() {
		Random rand = new Random();
		ArrayList<Tile> moves = new ArrayList<Tile>();
		ArrayList<Piece> pieces = this.board.getCurrentPlayer().getRemainingPieces();
		boolean noMoves = false;
		Piece piece = null;
		
		while (true) {
			if (this.level == 0 || noMoves) {
				piece = pieces.get(rand.nextInt(pieces.size()));
				while (moves.isEmpty()) {
					piece = pieces.get(rand.nextInt(pieces.size()));
					moves = piece.getAvailableMoves(this.board);
				}
				return piece;
			} else if (this.level == 1) {
				for (Piece p : pieces) {
					moves = p.getAvailableMoves(board);
					// check if there is a piece we can capture
					for (Tile t: moves) {
						if (t.isOccupied && t.getPiece().color != p.color) {
							return p;
						}
					}
				}
				moves.clear();
				noMoves = true;
			}
		}
	}
}
