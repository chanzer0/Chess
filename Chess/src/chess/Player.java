package chess;

public class Player {
	private Piece[] remainingPieces;
	private Piece[] capturedPieces;
	private Color color;
	private short score;
	
	
	public Player (Color color) {
		this.color = color;
	}
	
	
	public Piece[] getRemainingPieces() {
		return this.remainingPieces;
	}
	
	public Piece[] getCapturedPieces() {
		return this.capturedPieces;
	}
	
	public Color getColor() {
		return this.color;
	}
	
	public short getScore() {
		return this.score;
	}
	
	public void setColor(Color color) {
		this.color = color;
	}
	
	public void setScore(short score) {
		this.score = score;
	}
	
	public void addCapturedPieces(Piece piece) {
		Piece[] temp = new Piece[this.capturedPieces.length + 1];
		temp[temp.length - 1] = piece;
		this.capturedPieces = temp;
	}
	
	public void addCapturedPieces(Piece[] pieces) {
		Piece[] temp = new Piece[this.capturedPieces.length + pieces.length];
		for (int i = this.capturedPieces.length - 1; i < temp.length; i++) {
			temp[i] = pieces[i - this.capturedPieces.length + 1];
		}
		this.capturedPieces = temp;
	}
	
	public void addRemainingPieces(Piece piece) {
		Piece[] temp = new Piece[this.remainingPieces.length + 1];
		temp[temp.length - 1] = piece;
		this.remainingPieces = temp;
	}
	
	public void addRemainingPieces(Piece[] pieces) {
		Piece[] temp = new Piece[this.remainingPieces.length + pieces.length];
		for (int i = this.capturedPieces.length - 1; i < temp.length; i++) {
			temp[i] = pieces[i - this.capturedPieces.length + 1];
		}
		this.remainingPieces = temp;
	}
}
