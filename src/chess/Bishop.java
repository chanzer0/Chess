package chess;

public class Bishop extends Piece{

	public Bishop(int x, int y, String color) {
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
