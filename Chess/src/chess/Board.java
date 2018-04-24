package chess;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class Board implements MouseListener {
	protected Tile[][] board = new Tile[8][8];
	protected Player[] players;
	public Player pBlack;
	public Player pWhite;
	protected PlayerEnum currentPlayer;
	public ChessGUI gui;
	public Tile selected, checkingTile;
	public CPU ai = new CPU(0, this);
	
	public Board(ChessGUI gui) {
		this.gui = gui;
		pBlack = new Player(PlayerEnum.Black);
		pWhite = new Player(PlayerEnum.White);
		this.currentPlayer = PlayerEnum.White;
		this.players = new Player[] { pWhite, pBlack };
		emptyBoard();
		this.setPieces();
	}
	
	public Tile[][] getBoard() {
		return this.board;
	}
	
	public void setPieces() {}
	
	public Tile getTile(int row, int col) {
		return this.board[row][col];
	}
	
	public void switchPlayers() {
		this.currentPlayer = this.currentPlayer == PlayerEnum.White ? PlayerEnum.Black : PlayerEnum.White;
		this.gui.sidebar.setPlayer();
		
		if (this.gui.getMode() == "CPU" && this.currentPlayer == PlayerEnum.Black) {
			ai.doMove();
		}
	}
	
	public Player getCurrentPlayer() {
		return this.players[this.currentPlayer.ordinal()];
	}
	
	public Player getOpponent() {
		PlayerEnum opp = this.currentPlayer == PlayerEnum.White ? PlayerEnum.Black : PlayerEnum.White;
		return this.players[opp.ordinal()];
	}
	
	public Player getOpponent(Player p) {
		PlayerEnum opp = p.getColor() == PlayerEnum.White ? PlayerEnum.Black : PlayerEnum.White;
		return this.players[opp.ordinal()];
	}
	
	public Tile getKing(Player p) {
		for (int row = 0; row < 8; row++) {
			for (int col = 0; col < 8; col++) {
				if (board[row][col].piece != null) {
					Piece piece = board[row][col].piece;
					if (piece.identifier == PieceType.King && piece.color == piece.color) {
						return board[row][col];
					}
				}
			}
		}
		return null;
	}
	
	public void emptyBoard() {
		for (int row = 0; row < 8; row++) {
			for (int col = 0; col < 8; col++) {
				this.board[row][col] = new Tile(row, col);
			}
		}
		return;
	}
	
	// for testing purposes
	public void placeTile(Tile tile) {
		this.board[tile.row][tile.col] = tile;
	}
	
	public void doCheck() {
		this.inCheck(this.players[0]);
		this.inCheck(this.players[1]);
	}
	
	public boolean inCheck(Player p) {
		Tile tKing = this.getKing(p);
		
		for (int row = 0; row < 8; row++) {
			for (int col = 0; col < 8; col++) {
				if (this.board[row][col].isOccupied) {
					Piece piece = this.board[row][col].piece;
					if (piece.isValidMove(board, row, col, tKing.row, tKing.col) && piece.color != p.getColor()) {
						p.setCheck(true);
						this.checkingTile = board[row][col];
						if (inCheckmate(p)) {
							// this player is in checkmate, the opponent wins
							this.gui.gameOver(this.getOpponent(p));
						}
						return true;
					}
				}
			}
		}
		p.setCheck(false);
		return false;
	}
	
	public boolean inCheckmate(Player p) {
		Tile tKing = this.getKing(p);
		Tile[][] tempBoard = new Tile[8][8];
		
		for (int row = 0; row < 8; row++) {
			for (int col = 0; col < 8; col++) {
				Tile temp = this.board[row][col];
				tempBoard[row][col] = new Tile(temp.row, temp.col, temp.isOccupied, temp.piece);
			}
		}
		
		for (int row = 0; row < 8; row++) {
			for (int col = 0; col < 8; col++) {
				if (this.board[row][col].isOccupied) {
					ArrayList<Tile> moves = this.board[row][col].piece.getAvailableMoves(this);
					
					for (Tile t : moves) {
						tryMove(this.board[row][col], t, tempBoard);
						
						if (t.isOccupied &&
							t.piece.isValidMove(tempBoard, t.row, t.col, tKing.row, tKing.col) &&
							t.piece.color != p.getColor()) {
							return true;
						}
					}
				}
			}
		}
		
		return false;
	}
	
	/**
	 * Used for checkmate
	 * @param tile
	 * @param moveTo
	 * @param board
	 */
	private void tryMove(Tile tile, Tile moveTo, Tile[][] board) {
		board[tile.row][tile.col].piece = null;
		board[tile.row][tile.col].isOccupied = false;
		
		board[moveTo.row][moveTo.col] = new Tile(tile.row, tile.col, tile.isOccupied, tile.piece);
	}
	
	private void tryMove(Tile tile) {
		if (tile.getBorder() == Borders.AVAILABLE) {
			if (this.selected.moveTo(tile, this)) {
				gui.removeBorders();
				this.selected = null;
				
				// change players
				switchPlayers();
			} else {
				gui.showAvailableMoves(this.selected);
				this.selected.setBorder(Borders.SELECTED);
			}
			this.doCheck();
		}
	}
	
	public void selectTile(Tile tile) {
		if (this.selected == null) {
			if (tile.isOccupied && tile.piece.color == this.currentPlayer) {
				this.selected = tile;
				tile.setBorder(Borders.SELECTED);
				gui.showAvailableMoves(tile);
			}
		} else if (this.selected == tile) {
			gui.removeBorders();
			this.selected = null;
		} else {
			tryMove(tile);
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		Tile temp = (Tile) e.getComponent();
		Tile tile = getTile(temp.row, temp.col);
		
		selectTile(tile);
	}

	@Override
	public void mousePressed(MouseEvent e) {}
	@Override
	public void mouseReleased(MouseEvent e) {}
	@Override
	public void mouseEntered(MouseEvent e) {}
	@Override
	public void mouseExited(MouseEvent e) {}
}
