package chess;

public class guiTest {
	public static void main(String [] args) {
		chess960Board board = new chess960Board();
		chessBoardGUI gui = new chessBoardGUI(board);
	}
}