package chess;

public abstract class Piece{
	protected int x;
	protected int y;
	protected String color;
	
	
	public Piece(int x, int y, String color) {
		this.x = x;
		this.y = y;
		this.color = color;
	}
	
	public abstract int getX();
	public abstract int getY();
	public abstract String getColor();
	
}
