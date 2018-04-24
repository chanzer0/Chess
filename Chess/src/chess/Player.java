package chess;

import java.util.ArrayList;

public class Player {
	private ArrayList<Piece> remainingPieces = new ArrayList<Piece>();
	private ArrayList<Piece> capturedPieces = new ArrayList<Piece>();
	private PlayerEnum player;
	private short score;
	private boolean inCheck;
	private boolean inCheckmate;
	
	public Player (PlayerEnum player) {
		this.player = player;
		this.score = 0;
		this.inCheck = false;

	}

	public boolean getCheck() {
		return this.inCheck;
	}
	
	public void setCheck(boolean b) {
		this.inCheck = b;
	}
	
	public boolean getCheckMate() {
		return this.inCheckmate;
	}
	
	public void setCheckmate(boolean b) {
		this.inCheckmate = b;
	}
	
	public PlayerEnum getColor() {
		return this.player;
	}

	public short getScore() {
		return this.score;
	}

	public void setColor(PlayerEnum player) {
		this.player = player;
	}

	public void setScore(short score) {
		this.score = score;
	}

	public void addCapturedPieces(Piece piece) {
		this.score += piece.getPoints();
		this.capturedPieces.add(piece);
	}

	public void addCapturedPieces(Piece[] pieces) {
		for (Piece p : this.capturedPieces) {
			this.score += p.getPoints();
			this.capturedPieces.add(p);
		}
	}

	public ArrayList<Piece> getCapturedPieces() {
		return this.capturedPieces;
	}
	
	public void capturePiece(Piece piece, Board b) {
		this.capturedPieces.add(piece);
		
		Player opp = b.getOpponent(this);
		opp.remainingPieces.remove(piece);
	}
	
	public void addRemainingPieces(Piece piece) {
		this.remainingPieces.add(piece);
		this.remainingPieces.trimToSize();
	}

	public void addRemainingPieces(Piece[] pieces) {
		for (Piece p : pieces) {
			this.remainingPieces.add(p);
		}
	}

	public ArrayList<Piece> getRemainingPieces() {
		this.remainingPieces.trimToSize();
		return this.remainingPieces;
	}
}
