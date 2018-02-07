package chess;

public abstract class Piece{
	protected int x;
	protected int y;
	protected String color;
	protected String identifier;
	
	public Piece(int x, int y, String color, String identifier) {
		this.x = x;
		this.y = y;
		this.color = color;
		this.identifier = identifier;
	}
	
	
}
