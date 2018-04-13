package chess;

import java.awt.*;
import java.util.ArrayList;
import java.awt.event.*;
import javax.swing.Timer;
import javax.swing.border.LineBorder;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class SideBar extends JPanel {
	private int t = 600;
	private int t2 = 600;
	private Board board;
	private Player player;
	private JLabel pName;
	private JLabel pPoints;
	private JPanel capturedPieces;
	private JLabel whiteTimer, blackTimer;
	private JPanel container = new JPanel();
	private Timer whiteTime, blackTime;
	
	public SideBar(Board board) {
		this.board = board;
		this.player = board.getCurrentPlayer();
		this.setLayout(new BorderLayout(3, 3));
		
		container.setPreferredSize(new Dimension(400, 600));
		container.setLayout(new FlowLayout(FlowLayout.CENTER));
		container.setBorder(new LineBorder(Color.BLACK));
		
		Font font = new Font("Verdana", Font.PLAIN, 20);
		
		pName = new JLabel("Player: " + player.getColor().name());
		pPoints = new JLabel("Points: " + player.getScore());
		pPoints.setFont(font);
		pName.setFont(font);
		pPoints.setPreferredSize(new Dimension(350, 30));
		pName.setPreferredSize(new Dimension(350, 30));
		
		whiteTimer = new JLabel("White: " + (t / 60) + ":" + t % 60);
		blackTimer = new JLabel("Black: " + (t2 / 60) + ":" + t2 % 60);
		whiteTimer.setFont(font);
		blackTimer.setFont(font);
		whiteTimer.setPreferredSize(new Dimension(350, 30));
		blackTimer.setPreferredSize(new Dimension(350, 50));
		capturedPieces = new JPanel(new GridLayout(0, 3));
		
		this.add(new JLabel(" "), BorderLayout.PAGE_START);
		container.add(whiteTimer);
		container.add(blackTimer);
		container.add(pPoints);
		container.add(pName);
		container.add(capturedPieces);
		this.add(container, BorderLayout.CENTER);
	}
	
	public void setPlayer() {
		player = this.board.getCurrentPlayer();
		if (player.getColor() == PlayerEnum.Black) {
			whiteTime.stop();
			blackTime.start();
		} else if (player.getColor() == PlayerEnum.White) {
			blackTime.stop();
			whiteTime.start();
		}
		
		pName.setText("Player: " + player.getColor().name());
		pPoints.setText("Points: "  + player.getScore());
		
		capturedPieces.removeAll();
		ArrayList<Piece> captured = player.getCapturedPieces();
		
		for (Piece c: captured) {
			Image dimg = c.img.getScaledInstance(64, 64, Image.SCALE_SMOOTH);
			ImageIcon imageIcon = new ImageIcon(dimg);
			capturedPieces.add(new JLabel(imageIcon));
		}
	}
	
	public void setBoard(Board board) {
		this.board = board;
		this.startTimer();
		this.setPlayer();
		this.startTimer();
	}
	
	private void startTimer() {
		t = 600;
		t2 = 600;
		
		if (whiteTime != null && whiteTime.isRunning()) whiteTime.stop();
		if (blackTime != null && blackTime.isRunning()) blackTime.stop();
		
        ActionListener taskPerformerWhite = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
            	whiteTimer.setText("White: " + (t / 60) + ":" + (t % 60 < 10 ? "0" + t % 60 : t % 60));
            	t--;
            	repaint();
            }
        };
        ActionListener taskPerformerBlack = new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
            	blackTimer.setText("Black: " + (t2 / 60) + ":" + (t2 % 60 < 10 ? "0" + t2 % 60 : t2 % 60));
            	t2--;
            	repaint();
            }
        };
        whiteTime = new Timer(1000, taskPerformerWhite);
        blackTime = new Timer(1000, taskPerformerBlack);
        whiteTime.setRepeats(true);
        blackTime.setRepeats(true);
	}
}
