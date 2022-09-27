package view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

import controller.Controller;
import model.Coordinate;
import model.Piece;

@SuppressWarnings("serial")
public class Crown extends JFrame {
	private volatile static Crown uniqueInstance;

	private JButton btn_Queen;
	private JButton btn_Bishop;
	private JButton btn_Knight;
	private JButton btn_Rook;

	private ImageIcon icon_WhiteQueen;
	private ImageIcon icon_WhiteBishop;
	private ImageIcon icon_WhiteKnight;
	private ImageIcon icon_WhiteRook;
	private ImageIcon icon_BlackQueen;
	private ImageIcon icon_BlackBishop;
	private ImageIcon icon_BlackKnight;
	private ImageIcon icon_BlackRook;

	private Controller controller;
	private Coordinate coorPieceWillCrown;

	private Crown(Controller controller) {
		super("Crown");
		this.controller = controller;
		setLayout(new FlowLayout(FlowLayout.CENTER, 32, 16));
		createComponent();
		add(btn_Queen);
		add(btn_Bishop);
		add(btn_Knight);
		add(btn_Rook);

		addListener();

		setSize(500, 150);
		setLocationRelativeTo(null);
	}

	public static Crown getInstance(Controller controller) {
		if (uniqueInstance == null)
			synchronized (Crown.class) {
				if (uniqueInstance == null)
					uniqueInstance = new Crown(controller);
			}
		return uniqueInstance;
	}

	private void createComponent() {
		btn_Queen = new JButton();
		btn_Bishop = new JButton();
		btn_Knight = new JButton();
		btn_Rook = new JButton();

		Dimension dButton = new Dimension(80, 80);
		btn_Queen.setPreferredSize(dButton);
		btn_Bishop.setPreferredSize(dButton);
		btn_Knight.setPreferredSize(dButton);
		btn_Rook.setPreferredSize(dButton);

		btn_Queen.setFocusPainted(false);
		btn_Bishop.setFocusPainted(false);
		btn_Knight.setFocusPainted(false);
		btn_Rook.setFocusPainted(false);

		icon_WhiteQueen = new ImageIcon("images/White_Queen.png");
		icon_WhiteBishop = new ImageIcon("images/White_Bishop.png");
		icon_WhiteKnight = new ImageIcon("images/White_Knight.png");
		icon_WhiteRook = new ImageIcon("images/White_Rook.png");

		icon_BlackQueen = new ImageIcon("images/Black_Queen.png");
		icon_BlackBishop = new ImageIcon("images/Black_Bishop.png");
		icon_BlackKnight = new ImageIcon("images/Black_Knight.png");
		icon_BlackRook = new ImageIcon("images/Black_Rook.png");
	}

	private void setIconButton(boolean white) {
		if (white) {
			btn_Queen.setIcon(icon_WhiteQueen);
			btn_Bishop.setIcon(icon_WhiteBishop);
			btn_Knight.setIcon(icon_WhiteKnight);
			btn_Rook.setIcon(icon_WhiteRook);
		} else {
			btn_Queen.setIcon(icon_BlackQueen);
			btn_Bishop.setIcon(icon_BlackBishop);
			btn_Knight.setIcon(icon_BlackKnight);
			btn_Rook.setIcon(icon_BlackRook);
		}
	}

	private void addListener() {
		MyListener listener = new MyListener();
		btn_Queen.addActionListener(listener);
		btn_Bishop.addActionListener(listener);
		btn_Knight.addActionListener(listener);
		btn_Rook.addActionListener(listener);

		// mặc định sẽ phong thành quân Hậu nếu Close JFrame mà không chọn 1 trong 4
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				super.windowClosing(e);
				btn_Queen.doClick();
			}
		});
	}

	public void queening(Piece piece) {
		setIconButton(piece.isWhite());
		coorPieceWillCrown = piece.getCoordinate();
		setVisible(true);
	}

	class MyListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			if (e.getSource() == btn_Queen)
				controller.queening(coorPieceWillCrown, "Queen");

			else if (e.getSource() == btn_Bishop)
				controller.queening(coorPieceWillCrown, "Bishop");

			else if (e.getSource() == btn_Knight)
				controller.queening(coorPieceWillCrown, "Knight");

			else if (e.getSource() == btn_Rook)
				controller.queening(coorPieceWillCrown, "Rook");

			setVisible(false);
		}

	}
}
