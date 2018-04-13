package chess;

import java.awt.Image;
import java.awt.image.BufferedImage;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
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
		this.piece = piece;
		this.isOccupied = isOccupied;
	}
	
	public Tile(int row, int col) {
		this.row = row;
		this.col = col;
		this.isOccupied = false;
		this.piece = null;
	}
	
	public boolean moveTo(Tile moveTo, Board board) {
		if (moveTo.isOccupied && this.piece.canAttack(moveTo.piece)) {
			board.getCurrentPlayer().capturePiece(moveTo.piece, board);
		}
		
		boolean tempMoved = this.piece.hasMoved;
		Piece tempPiece = moveTo.piece;

		if (this.piece.move(moveTo, board)) {
			this.removePiece();
			board.doCheck();
			// this move put the player in check, undo
			if (board.getCurrentPlayer().getCheck()) {
				moveTo.piece.move(this, board);
				moveTo.piece.hasMoved = tempMoved;
				moveTo.setPiece(tempPiece);
				
				JOptionPane.showMessageDialog(board.gui.f, "This move would put your king in check, please try another move.");
				return false;
			}
			return true;
		}

		return false;
	}
	
	public void setPiece(Piece piece) {
		this.removeAll();
		this.isOccupied = piece == null ? false : true;
		this.piece = piece;
		this.setImage();
	}

	public void removePiece() {
		this.piece = null;
		this.isOccupied = false;
		this.setImage();
		this.setBorder(null);
	}
	
	public Piece getPiece(){
		
		return this.piece;
		
	}
	
	public void setImage() {
		this.removeAll();
		if (this.isOccupied) {
			Image dimg = this.piece.img.getScaledInstance(50, 60, Image.SCALE_SMOOTH);
			ImageIcon imageIcon = new ImageIcon(dimg);
			this.add(new JLabel(imageIcon));
		} else {
			ImageIcon icon = new ImageIcon(
                    new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB));
            this.add(new JLabel(icon));
		}
	}
}

