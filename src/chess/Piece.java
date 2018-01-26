package chess;

public interface Piece {
	String position = null;
	
	public String getPosition();
	public String getLegalMoves();
	
}
