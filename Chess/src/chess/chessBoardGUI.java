package chess;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;
import javax.swing.border.Border;

public class chessBoardGUI implements MouseListener, ActionListener {
	
	private JFrame chessFrame;
	private JPanel chessPanel;
	private static Dimension boardDimensions = new Dimension(600,600);
    private final java.awt.Color lightTileColor = java.awt.Color.decode("#efd0a7");
    private final java.awt.Color darkTileColor = java.awt.Color.decode("#bf7007");
    private Border selectedBorder = BorderFactory.createBevelBorder(0, java.awt.Color.green, java.awt.Color.GREEN);
    private Border availableBorder = BorderFactory.createBevelBorder(0, java.awt.Color.white, java.awt.Color.white);
    private Board board;
    private Tile selected;
    private Color playerColor;
    private Tile[] available;
    private Player playerWhite;
    private Player playerBlack;
    
    
	public chessBoardGUI(Board board) {
		this.board = board;
		this.chessFrame = new JFrame("Chess");
		this.chessFrame.setSize(boardDimensions);
		
		final JMenuBar menuBar = new JMenuBar();
		populateMenuBar(menuBar);
		this.chessFrame.setJMenuBar(menuBar);
		
		setUpPanel();
		
		this.playerColor = Color.White;
		
		this.chessFrame.add(chessPanel);
		this.chessPanel.setVisible(true);
		this.chessFrame.setVisible(true);
		this.chessFrame.repaint();
	}
	
	private void setUpPanel() {
		this.chessPanel = new JPanel(new GridLayout(8,8));
		Tile[][] board = this.board.board;
		
		for (int row = 0; row < 8; row++) {
			for (int col = 0; col < 8; col++) {
				Tile currTile = board[row][col];
				currTile.addMouseListener(this);
				
				boolean dark = false;
				dark = col % 2 == 0;
				if (row % 2 == 0) dark = !dark;
				
				currTile.setBackground(dark ? darkTileColor : lightTileColor);
				setImage(currTile);
				currTile.setVisible(true);
				this.chessPanel.add(currTile);
			}
		}
	}
	
	private void tryMove(Tile tile) {
		if (tile.getBorder() == availableBorder) {
			this.selected.moveTo(tile, board.board);
			for (int i = 0; i < this.available.length; i++) {
				if (this.available[i] != null) {
					this.available[i].setBorder(null);
				}
			}
			this.available = new Tile[8];
			this.selected = null;
			this.playerColor = this.playerColor == Color.White ? Color.Black : Color.White;
		}
	}
	
	private void showAvailableMoves(Tile tile) {
		this.available = tile.piece.getAvailableMoves(this.board.board);
		for (int i = 0; i < this.available.length; i++) {
			if (this.available[i] != null) {
				if (this.available[i].getBorder() == null) {
					if (this.available[i].piece != null) {
						System.out.println("x: " + this.available[i].row + ", col: " + this.available[i].col);
					}
					this.available[i].setBorder(this.availableBorder);
				} else {
					this.available[i].setBorder(null);
				}
			}
		}
	}
	
	private void setImage(Tile tile) {
		if (tile.isOccupied) {
			Image dimg = tile.piece.img.getScaledInstance(60, 60, Image.SCALE_SMOOTH);
			ImageIcon imageIcon = new ImageIcon(dimg);
			tile.add(new JLabel(imageIcon));
		} else {
			tile.removeAll();
		}
	}

	private void populateMenuBar(JMenuBar menuBar) {
		
		JMenu file = new JMenu("File");
		
		JMenuItem restart = new JMenuItem("Restart");
		restart.addActionListener(this);
		file.add(restart);
		
		JMenuItem classical = new JMenuItem("New Classical");
		classical.addActionListener(this);
		file.add(classical);
		
		JMenuItem board960 = new JMenuItem("New 960");
		board960.addActionListener(this);
		file.add(board960);

		menuBar.add(file);
		menuBar.add(new JMenu("Options"));
		menuBar.add(new JMenu("Player Turn"));
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Tile tile = (Tile) e.getComponent();
		if (this.selected == null) {
			if (tile.isOccupied && tile.piece.color == this.playerColor) {
				this.selected = tile;
				tile.setBorder(selectedBorder);
				showAvailableMoves(tile);
			}
		} else if (this.selected == tile) {
			this.selected = null;
			tile.setBorder(null);
			showAvailableMoves(tile);
		} else {
			tryMove(tile);
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String type = e.getActionCommand();
		chessFrame.dispose();
		chess960Board b960 = new chess960Board();
		classicalChessBoard classical = new classicalChessBoard();
		
		if (type.equals("New Classical")) {
			new chessBoardGUI(classical);
		}
		if (type.equals("New 960")) {
			new chessBoardGUI(b960);
		}
		if (type.equals("Restart")) {
			if (this.board.getClass().equals(classical.getClass())) {
				new chessBoardGUI(classical);
			} else {
				new chessBoardGUI(b960);
			}
		}
	}
	
	
	

}