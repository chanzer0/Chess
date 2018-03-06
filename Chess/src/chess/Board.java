package chess;

abstract public class Board {
	protected Tile[][] board = new Tile[8][8];
	
	public Board() {
		this.initBoard();
	}
	
	public Tile[][] getBoard() {
		return this.board;
	}
	
	abstract public void initBoard();
	
	public void printBoard() {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				if (board[i][j].isOccupied) {
					if (j == 0) {
						System.out.print("|");
					}
					System.out.print(board[i][j].piece.identifier + "|");
				} else {
					System.out.print(" ");
				}
			}
			System.out.print("\n");
		}
	}
	
	public void emptyBoard() {
		for (int i = 0; i < 8; i++) {
			for (int j = 0; j < 8; j++) {
				this.board[i][j] = new Tile(i, j, false, null);
			}
		}
		return;
	}
	
	public void placeTile(Tile tile) {
		this.board[tile.row][tile.col] = tile;
	}
}
