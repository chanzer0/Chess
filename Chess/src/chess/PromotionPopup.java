package chess;

import java.awt.Component;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class PromotionPopup extends JPanel implements MouseListener {
	private JPanel pieceContainer = new JPanel(new GridLayout(0, 4));
	private Piece[] pieces;
	private Piece selected;
	
	public PromotionPopup(Player player, Tile moveTo) {
		this.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.add(new JLabel("Choose Piece to Promote to"));
		this.pieces = new Piece[] {
			new Queen(moveTo.row, moveTo.col, player.getColor(), PieceType.Queen),
			new Bishop(moveTo.row, moveTo.col, player.getColor(), PieceType.Bishop),
			new Knight(moveTo.row, moveTo.col, player.getColor(), PieceType.Knight),
			new Rook(moveTo.row, moveTo.col, player.getColor(), PieceType.Rook)
		};
		
		for (Piece c: pieces) {
			Image dimg = c.img.getScaledInstance(64, 64, Image.SCALE_SMOOTH);
			ImageIcon imageIcon = new ImageIcon(dimg);
			JLabel piece = new JLabel(imageIcon);
			piece.setName(c.identifier.name());
			piece.setBorder(Borders.CLEAR);
			piece.addMouseListener(this);
			pieceContainer.add(c.identifier.name(), piece);
		}
		
		this.add(pieceContainer);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		String name = e.getComponent().getName();
		JLabel piece = (JLabel) e.getComponent();
		
		if (this.selected != null) {
			for (Component p : pieceContainer.getComponents()) {
				JLabel selected = (JLabel) p;
				selected.setBorder(Borders.CLEAR);
			}
		}
		
		switch (name) {
			case "Queen":
				this.selected = pieces[0];
				break;
			case "Bishop":
				this.selected = pieces[1];
				break;
			case "Knight":
				this.selected = pieces[2];
				break;
			case "Rook":
				this.selected = pieces[3];
				break;
		}
		
		piece.setBorder(Borders.SELECTED);
	}
	
	public Piece getSelected() {
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
