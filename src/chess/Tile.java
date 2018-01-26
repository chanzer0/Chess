package chess;

public class Tile {
	public int x;
	public int y;
	public boolean isOccupied;
	public Piece piece;
	
	public Tile(int x, int y, boolean isOccupied, Piece piece) {
		this.x = x;
		this.y = y;
		this.isOccupied = isOccupied;
		this.piece = piece;
	}
}
