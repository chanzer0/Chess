package chess;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public abstract class Piece {
	protected int row;
	protected int col;
	protected PlayerEnum color;
	protected PieceType identifier;
	protected boolean captured;
	protected boolean hasMoved;
	public BufferedImage img;
	
	

	public Piece(int row, int col, PlayerEnum color, PieceType identifier) {
		this.row = row;
		this.col = col;
		this.color = color;
		this.identifier = identifier;
		this.setImage(color, identifier);
		this.hasMoved = false;
	}
	
	public Piece(Piece piece) {
		this.row = piece.row;
		this.col = piece.col;
		this.color = piece.color;
		this.identifier = piece.identifier;
		this.setImage(color, identifier);
		this.hasMoved = false;
	}
	
	public int getPoints(){
		if(identifier.equals(PieceType.Pawn)){
			return 1;
		}
		if(identifier.equals(PieceType.Bishop)){
			return 3;
		}
		if(identifier.equals(PieceType.Knight)){
			return 3;
		}
		if(identifier.equals(PieceType.Rook)){
			return 5;
		}
		if(identifier.equals(PieceType.Queen)){
			return 9;
		}
		return 0;
	}
	
	
	public boolean canAttack(Piece toAttack) {
		if (toAttack.color == this.color) return false;
		return true;
	}
	
	public boolean move(Tile moveTo, Board board) {
		this.row = moveTo.row;
		this.col = moveTo.col;
		
		moveTo.setPiece(this);
		this.hasMoved = true;
		return true;
	}
	
	
	void setImage(PlayerEnum color, PieceType identifier) {
		String url = "assets/classic/";
		url += identifier.name().toLowerCase() + "-" + color.name().toLowerCase() + ".png";
		BufferedImage img = null;
		try {
		    img = ImageIO.read(new File(url));
		} catch (IOException e) {
		    e.printStackTrace();
		}
		this.img = img;
	}
	
	void setImage(PlayerEnum color, PieceType identifier, Theme theme) {
		String url = "assets/";
		url += theme.filepath + "/" + identifier.name().toLowerCase() + "-" + color.name().toLowerCase() + ".png";
		BufferedImage img = null;
		try {
		    img = ImageIO.read(new File(url));
		} catch (IOException e) {
		    e.printStackTrace();
		}
		this.img = img;
	}
	
	public boolean isOpponent(PlayerEnum opp) {
		return this.color != opp && (opp == PlayerEnum.White || opp == PlayerEnum.Black);
	}
	
	public boolean isValidMove(Tile[][] board, int fromRow, int fromCol, int toRow, int toCol) {
		if (fromRow == toRow && fromCol == toCol) return false;
		if (toRow > 7 || toRow < 0 || toCol > 7 || toCol < 0) return false;
		return true;
	}

	public abstract ArrayList<Tile> getAvailableMoves(Board board);
}

