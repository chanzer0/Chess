package chess;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public abstract class Piece {
	protected int row;
	protected int col;
	protected Color color;
	protected PieceType identifier;
	protected boolean captured;
	protected boolean hasMoved;
	public BufferedImage img;

	public Piece(int row, int col, Color color, PieceType identifier) {
		this.row = row;
		this.col = col;
		this.color = color;
		this.identifier = identifier;
		this.setImage(color, identifier);
		this.hasMoved = false;
	}
	
	public boolean canAttack(Piece toAttack) {
		if (toAttack.color == this.color) return false;
		return true;
	}
	
	public void move(int row, int col) {
		this.row = row;
		this.col = col;
	}
	
	void setImage(Color color, PieceType identifier) {
		String url = "assets/";
		url += identifier.name().toLowerCase() + "-" + color.name().toLowerCase() + ".png";
		BufferedImage img = null;
		try {
		    img = ImageIO.read(new File(url));
		} catch (IOException e) {
		    e.printStackTrace();
		}
		this.img = img;
	}
	
	public boolean isOpponent(Color opp) {
		return this.color != opp && (opp == Color.White || opp == Color.Black);
	}
	
	public boolean isValid(Board board, int fromRow, int fromCol, int toRow, int toCol) {
		if (fromRow == toRow && fromCol == toCol) return false;
		if (toRow > 7 || toRow < 0 || toCol > 7 || toCol < 0) return false;
		return true;
	}

	public abstract Tile[] getAvailableMoves(Tile[][] board);
}
