package chess;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.*;

public class ChessGUI implements ActionListener {
	
	public JFrame f;
	private JPanel game = new JPanel();
	CardLayout layout = new CardLayout(3, 3);
	private JPanel chessBoard = new JPanel(new GridLayout(0,8));
	private JPanel boardContainer = new JPanel(new BorderLayout(3, 3));
	public final JPanel gui = new JPanel();
	public GameOver over;
	public SideBar sidebar;
	public MainMenu mainMenu = new MainMenu(this);
	public Theme theme;
    public Theme classic = new Theme(Color.decode("#efd0a7"), Color.decode("#bf7007"), "classic/");
    public Theme politics = new Theme(Color.decode("#d30b0d"), Color.decode("#428bca"), "politics/");
    public Theme retro = new Theme(Color.decode("#2b3990"), Color.decode("#000000"), "retro/");
    public Theme cyclone = new Theme(Color.decode("#F1BE48"),Color.decode("#C8102E"),"cyclone/");
    public Theme cottonCandy = new Theme(Color.decode("#b6c1ff"),Color.decode("#ffb6c1"),"cottonCandy/");
    public Theme seaside = new Theme(Color.decode("#AB988B"),Color.decode("#738F9B"),"seaside/");
    public Theme celtic = new Theme(Color.decode("#BA9653"),Color.decode("#007A33"),"celtic/");
    public Theme rose = new Theme(Color.decode("#ab8fc0"),Color.decode("#dc3148"),"rose/");
    private Board board;
    private ArrayList<Tile> available;
    private static final String COLS = "ABCDEFGH";
    private String mode;
    
    
	public ChessGUI() {
		
		mode = "User";
		
		this.board = new Board(this);
		this.theme = classic;
		setUpPanel();
		
		// numbered rows
		JPanel rows = new JPanel(new GridLayout(0, 1));
		for (int i = 8; i > 0; i--) {
			rows.add(new JLabel(" " + i + "  "));
		}
		
		// set up columns
		JPanel cols = new JPanel(new GridLayout(0, 8));

		for (int i = 0; i < 8; i++) {
			cols.add(new JLabel(COLS.substring(i, i + 1),
                    SwingConstants.CENTER));
		}
		
		boardContainer.add(chessBoard, BorderLayout.LINE_END);
		boardContainer.add(rows, BorderLayout.LINE_START);
		boardContainer.add(cols, BorderLayout.PAGE_START);
		chessBoard.setBorder(new LineBorder(Color.BLACK));
		
		sidebar = new SideBar(board);
		game.setLayout(new BorderLayout(3, 3));
		game.add(sidebar, BorderLayout.LINE_END);
		game.add(boardContainer, BorderLayout.LINE_START);
		
		gui.setLayout(layout);
		
		gui.add(game, "Game");
		gui.add(mainMenu, "Menu");
		
		layout.show(gui, "Menu");
		
		setFrame();
	}
	
	private void setFrame() {
		f = new JFrame("Chess");
        f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        f.setLocationByPlatform(true);
        
		final MenuBar menuBar = new MenuBar(this);
		f.setJMenuBar(menuBar);
        
		f.add(gui);

        // ensures the frame is the minimum size it needs to be
        // in order display the components within it
        f.pack();
        // ensures the minimum size is enforced.
        f.setMinimumSize(f.getSize());
        f.setVisible(true);
	}
	
	private void setUpPanel() {
		chessBoard.removeAll();
		for (int row = 0; row < 8; row++) {
			for (int col = 0; col < 8; col++) {
				Tile t = this.board.getTile(row, col);
				t.removeMouseListener(this.board);
				
				if (t.isOccupied) {
					t.piece.setImage(t.piece.color, t.piece.identifier, this.theme);
				}
				t.addMouseListener(this.board);
				
				boolean dark = false;
				dark = col % 2 == 0;
				if (row % 2 == 0) dark = !dark;
				
				t.setBackground(dark ? this.theme.dark : this.theme.light);
				t.setImage();
				t.setVisible(true);
				t.setBorder(Borders.CLEAR);
				chessBoard.add(t);
			}
		}
		
		
		
		chessBoard.setVisible(true);
	}
	
	public void gameOver(Player winner) {
		over = new GameOver(this, winner);
		gui.add(over, "gameover");
		layout.show(gui, "gameover");
	}
	
	public void setBoard(Board board) {
		this.board = board;
		this.sidebar.setBoard(board);
		this.changeTheme(this.theme);
		setUpPanel();
	}
	
	public void showAvailableMoves(Tile tile) {
		this.available = tile.piece.getAvailableMoves(this.board);
		for (Tile t: this.available) {
			t.setBorder(Borders.AVAILABLE);
		}
	}
	
	public void removeBorders() {
		for (Tile t : this.available) {
			t.setBorder(Borders.CLEAR);
		}
		
		this.board.selected.setBorder(Borders.CLEAR);
	}
	
	public void changeTheme(Theme theme) {
		this.theme = theme;
		
		for (int row = 0; row < 8; row++) {
			for (int col = 0; col < 8; col++) {
				Tile t = this.board.getTile(row, col);
				
				if (t.isOccupied) {
					t.piece.setImage(t.piece.color, t.piece.identifier, theme);
				}
			}
		}
		
		this.setUpPanel();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Board960 b960 = new Board960(this);
		ChessClassical classical = new ChessClassical(this);
		
		switch (e.getActionCommand()) {
		case "New Classical Game":
			setBoard(classical);
			layout.show(gui, "Game");
			break;
		case "New 960 Game":
			setBoard(b960);
			layout.show(gui, "Game");
			break;
		case "Theme":
			ThemePopup popup = new ThemePopup();
			JOptionPane.showMessageDialog(board.gui.f, popup);
			changeTheme(popup.getSelected());
			break;
		case "Restart":
			if (this.board.getClass().equals(classical.getClass())) {
				setBoard(classical);
			} else {
				setBoard(b960);
			}
			layout.show(gui, "Game");
			break;
		case "Retro":
			this.changeTheme(retro);
			this.setUpPanel();
			break;
		case "Classic":
			this.changeTheme(classic);
			break;
		case "Cyclone":
			this.changeTheme(cyclone);
			break;
		case "Cotton Candy":
			this.changeTheme(cottonCandy);
			break;
		case "Politics":
			this.changeTheme(politics);
			break;
		case "SeaSide":
			this.changeTheme(seaside);
			break;
		case "Celtic":
			this.changeTheme(celtic);
			break;
		case "Rose":
			this.changeTheme(rose);
		case "CPU":
			mode = "CPU";
			break;
		case "User":
			mode = "User";
			break;
		}
		
		f.repaint();
	}
	
	public String getMode() {
		return mode;
	}
	
	public void setMode(String mode) {
		this.mode = mode;
	}
	
	public static void main(String[] args) {
		ChessGUI gui = new ChessGUI();
		ChessClassical c = new ChessClassical(gui);
		gui.setBoard(c);
	}

}
