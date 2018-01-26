package chess;

public class Chessboard {
	private Tile[][] tileArray = null;
	
	public Chessboard(String gametype) {
		if (gametype == "Classical") {
			initClassical();
		} else if (gametype == "Chess960") {
			initChess960();
		}
	}
	
	private void initClassical() {
		
		return;
	}
	
	private void initChess960() {
		
		return;
	}
}
