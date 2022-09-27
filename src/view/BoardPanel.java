package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.Controller;
import model.Board;
import model.Coordinate;
import model.Piece;

@SuppressWarnings("serial")
public class BoardPanel extends JPanel {
	public static final int WIDEBOARD = 600;
	public static final int HEIGHTBOARD = 600;
	public final static int WIDEBOX = 600;
	public final static int HEIGTHBOX = 600;
	private Board board;
	private Controller controller;

	public BoardPanel(Controller controller) {
		this.controller = controller;
		controller.setbPanel(this);
		setLayout(new GridLayout(8, 8));
		setPreferredSize(new Dimension(WIDEBOARD, HEIGHTBOARD));
		addListener();
	}

	public void setBoard(Board board) {
		this.board = board;
		this.board.setController(controller);
		controller.setBoard(this.board);
		paintChessBox(null, null);
	}

	public void paintChessBox(Piece pieceSelected, List<Coordinate> listPieceGo) {
		this.removeAll();
		for (int iRow = 1; iRow <= 8; iRow++) {
			for (int iCol = 1; iCol <= 8; iCol++) {
				// create chessbox
				JPanel square = new JPanel();
				square.setPreferredSize(new Dimension(BoardPanel.WIDEBOX, BoardPanel.HEIGTHBOX));

				// Box selected
				if (pieceSelected != null)
					if (iRow == pieceSelected.getCoordinate().getRow() + 1
							&& iCol == pieceSelected.getCoordinate().getCol() + 1)
						square.setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));

				// List ches piece can go
				if (listPieceGo != null) {
					for (int i = 0; i < listPieceGo.size(); i++) {
						if (iRow == listPieceGo.get(i).getRow() + 1 && iCol == listPieceGo.get(i).getCol() + 1)
							square.setBorder(BorderFactory.createLineBorder(Color.YELLOW, 3));
					}
				}

				// Paint color board
				if (iRow % 2 != 0)
					if (iCol % 2 != 0)
						square.setBackground(Color.LIGHT_GRAY);
					else
						square.setBackground(Color.GRAY);
				else {
					if (iCol % 2 != 0)
						square.setBackground(Color.GRAY);
					else
						square.setBackground(Color.LIGHT_GRAY);
				}

				if (board.getPieceAt(iRow - 1, iCol - 1) != null) {
					ImageIcon icon = new ImageIcon("images/" + board.getPieceAt(iRow - 1, iCol - 1).getName() + ".png");
					JLabel label = new JLabel();
					label.setIcon(icon);
					square.add(label);
				}
				this.add(square);
			}
		}
		this.revalidate();
		this.repaint();
	}

	private void addListener() {
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				super.mouseClicked(arg0);
				int row = arg0.getY() / 75;
				int col = arg0.getX() / 75;
				controller.requestSelect(row, col);
			}
		});
	}

	public void newGameAgain() {
		this.setBoard(new Board(board.getMode()));
		controller.resetLabelTurn();
	}

}
