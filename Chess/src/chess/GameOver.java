package chess;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class GameOver extends JPanel {
	private ChessGUI gui;
	private JButton classical = new JButton("New Classical Game");
	private JButton b960 = new JButton("New 960 Game");
	private JButton menu = new JButton("Main Menu");
	private ImageIcon bImg;
	private ImageIcon wImg;
	private JPanel container = new JPanel();
	
	public GameOver(ChessGUI gui, Player winner) {
		this.gui = gui;
		setLayout();
		setButtons();
		
		JLabel main = new JLabel("Player " + winner.getColor().name() + " Wins!");
		main.setFont(new Font("Verdana", Font.PLAIN, 25));
		main.setHorizontalAlignment(JLabel.CENTER);
		
		JPanel header = new JPanel(new GridLayout(0, 3));
		header.setBackground(Color.decode("#efd0a7"));
		header.add(new JLabel(wImg));
		header.add(main);
		header.add(new JLabel(bImg));
		
		this.add(header, BorderLayout.PAGE_START);
		this.add(container, BorderLayout.CENTER);
	}
	
	private void setLayout() {
		this.setLayout(new BorderLayout(3, 3));
		this.setBackground(Color.decode("#efd0a7"));
		this.setBorder(new EmptyBorder(100, 100, 100, 100));
		
	}
	
	private void setButtons() {
		container.setLayout(new GridLayout(0, 1));
		container.setBorder(new EmptyBorder(0, 150, 0, 150));
		
		JButton[] buttons = new JButton[] {
			classical,
			b960,
			menu
		};
		
		for (JButton b: buttons) {
			b.addActionListener(this.gui);
			container.add(b);
		}

		container.setBackground(Color.decode("#efd0a7"));
	}
}
