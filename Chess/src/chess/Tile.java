package chess;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Tile extends JPanel {
	public int row;
	public int col;
	public boolean isOccupied;
	public Piece piece;
	public String color;
	
	public Tile(int row, int col, boolean isOccupied, Piece piece) {
		this.row = row;
		this.col = col;
		this.isOccupied = isOccupied;
		this.piece = piece;
	}
	
	public void moveTo(Tile moveTo, Tile[][] board) {
		if (moveTo.isOccupied) {
			if (!this.piece.canAttack(moveTo.piece)) return;
		}
		// this is tile you are moving, tile is
		this.piece.move(moveTo.row, moveTo.col);
		// Castling contingent
		{
			// East
			if(this.piece.identifier == PieceType.King && !this.piece.hasMoved
					&& moveTo.col == 6){
				//Move east rook to tile[moveTo.row][5]
				board[moveTo.row][5].piece = board[moveTo.row][7].piece;
				board[moveTo.row][5].isOccupied = true;
				board[moveTo.row][7].removePiece();
				Image dimg = board[moveTo.row][5].piece.img.getScaledInstance(60, 60, Image.SCALE_SMOOTH);
				ImageIcon imageIcon = new ImageIcon(dimg);
				board[moveTo.row][7].add(new JLabel(imageIcon));
			}
			// West
			if(this.piece.identifier == PieceType.King && !this.piece.hasMoved
					&& moveTo.col == 2){
				//Move west rook to tile[moveTo.row][3]
				board[moveTo.row][3].piece = board[moveTo.row][0].piece;
				board[moveTo.row][3].isOccupied = true;
				board[moveTo.row][0].removePiece();
				Image dimg = board[moveTo.row][3].piece.img.getScaledInstance(60, 60, Image.SCALE_SMOOTH);
				ImageIcon imageIcon = new ImageIcon(dimg);
				board[moveTo.row][0].add(new JLabel(imageIcon));
			}
		}
		moveTo.removeAll();
		moveTo.isOccupied = true;
		moveTo.piece = this.piece;
		this.piece.hasMoved = true;
		this.removePiece();
		Image dimg = moveTo.piece.img.getScaledInstance(60, 60, Image.SCALE_SMOOTH);
		ImageIcon imageIcon = new ImageIcon(dimg);
		moveTo.add(new JLabel(imageIcon));
	}

	public void removePiece() {
		this.piece = null;
		this.isOccupied = false;
		this.removeAll();
		this.setBorder(null);
	}
}
