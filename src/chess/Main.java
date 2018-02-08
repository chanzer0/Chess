package chess;

public class Main {
	public static void main(String[] args) {
		classicalChessBoard classicalBoard = new classicalChessBoard();
		chess960Board chess960Board = new chess960Board();
		classicalBoard.printBoard();
		System.out.print("------------------\n");
		chess960Board.printBoard();
	}
}
