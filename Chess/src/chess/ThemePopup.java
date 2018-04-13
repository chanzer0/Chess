package chess;

import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class ThemePopup extends JPanel implements MouseListener {
	private JPanel boardContainer = new JPanel(new GridLayout(0, 4));
	private Theme[] themes;
	private Theme selected;
	
	public ThemePopup() {
		this.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.add(new JLabel("Choose A Theme"));
		this.themes = new Theme[] {
				new Theme(Color.decode("#efd0a7"), Color.decode("#bf7007"), "classic/"),
				new Theme(Color.decode("#d30b0d"), Color.decode("#428bca"), "politics/"),
			    new Theme(Color.decode("#F1BE48"),Color.decode("#C8102E"),"cyclone/"),
			    new Theme(Color.decode("#b6c1ff"),Color.decode("#ffb6c1"),"cottonCandy/"),
			    new Theme(Color.decode("#AB988B"),Color.decode("#738F9B"),"seaside/"),
			    new Theme(Color.decode("#2b3990"), Color.decode("#000000"), "retro/"),
			    new Theme(Color.decode("#BA9653"),Color.decode("#007A33"),"celtic/"),
			    new Theme(Color.decode("#ab8fc0"),Color.decode("#dc3148"),"rose/")
		};
		
		selected = themes[0];
		
		for (Theme th: themes) {
			JPanel theme = new JPanel(new GridLayout(2, 2));
			theme.setSize(new Dimension(200, 200));
			for (int row = 0; row < 2; row++) {
				for (int col = 0; col < 2; col++) {
					Tile t = new Tile(row, col);
					if (row == 0 && col == 0) {
						t.setBackground(th.light);
						King k = new King(0, 1, PlayerEnum.White, PieceType.King);
						k.setImage(PlayerEnum.Black, PieceType.King, th);
						t.setPiece(k);
					} else if (row == 1 && col == 0) {
						t.setBackground(th.dark);
						
					} else if (row == 0 && col == 1) {
						t.setBackground(th.dark);
					} else {
						t.setBackground(th.light);
						King k = new King(1, 0, PlayerEnum.White, PieceType.King);
						k.setImage(PlayerEnum.White, PieceType.King, th);
						t.setPiece(k);
					}
					theme.add(t);
				}
			}
			theme.setName(th.filepath);
			theme.setBorder(Borders.CLEAR);
			theme.addMouseListener(this);
			boardContainer.add(th.filepath, theme);
		}
		
		this.add(boardContainer);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		String name = e.getComponent().getName();
		JPanel theme = (JPanel) e.getComponent();
		
		if (this.selected != null) {
			for (Component p : boardContainer.getComponents()) {
				JPanel selected = (JPanel) p;
				selected.setBorder(Borders.CLEAR);
			}
		}
		
		switch (name) {
			case "classic/":
				this.selected = themes[0];
				break;
			case "politics/":
				this.selected = themes[1];
				break;
			case "cyclone/":
				this.selected = themes[2];
				break;
			case "cottonCandy/":
				this.selected = themes[3];
				break;
			case "seaside/":
				this.selected = themes[4];
				break;
			case "retro/":
				this.selected = themes[5];
				break;
			case "celtic/":
				this.selected = themes[6];
				break;
			case "rose/":
				this.selected = themes[7];
				break;
		}
		
		theme.setBorder(Borders.SELECTED);
	}
	
	public Theme getSelected() {
		return this.selected;
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