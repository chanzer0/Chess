package chess;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class MainMenu extends JPanel {
	private ChessGUI gui;
	private JButton classical = new JButton("New Classical Game");
	private JButton b960 = new JButton("New 960 Game");
	private JButton theme = new JButton("Theme");
	private ImageIcon bImg;
	private ImageIcon wImg;
	private JPanel container = new JPanel();
	
	public MainMenu(ChessGUI gui) {
		this.gui = gui;
		
		setLayout();
		setButtons();
		
		JLabel main = new JLabel("Main Menu");
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
		this.setBorder(new EmptyBorder(100, 200, 200, 200));
		
		King wKing = new King(0, 0, PlayerEnum.White, PieceType.King);
		King bKing = new King(0, 0, PlayerEnum.Black, PieceType.King);
		
		Image wDimg = wKing.img.getScaledInstance(64, 64, Image.SCALE_SMOOTH);
		wImg = new ImageIcon(wDimg);
		
		Image bDimg = bKing.img.getScaledInstance(64, 64, Image.SCALE_SMOOTH);
		bImg = new ImageIcon(bDimg);
	}
	
	private void setButtons() {
		container.setLayout(new GridLayout(0, 1));
		container.setBorder(new EmptyBorder(0, 150, 0, 150));
		
		JButton[] buttons = new JButton[] {
			classical,
			b960,
			theme
		};
		
		for (JButton b: buttons) {
			b.addActionListener(this.gui);
			container.add(b);
		}
		container.setBackground(Color.decode("#efd0a7"));
	}
}
