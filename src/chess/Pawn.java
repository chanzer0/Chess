package chess;

public class Pawn extends Piece{

	public Pawn(int x, int y, String color) {
		super(x, y, color);
	}

	@Override
	public int getX() {
		return super.x;
	}

	@Override
	public int getY() {
		return super.y;
	}

	@Override
	public String getColor() {
		return super.color;
	}
}
