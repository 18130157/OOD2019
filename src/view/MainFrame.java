package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import controller.Controller;
import model.Board;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {
	public static final String TURN_WHITE = "TURN OF WHITE";
	public static final String TURN_BLACK = "TURN OF BLACK";
	private JPanel pLeft;
	private JPanel pRight;
	private JPanel pButton;
	private JPanel pBtnPlaying;
	private JPanel pImage;
	private BoardPanel boardPanel;
	private JButton btn1Player;
	private JButton btn2Player;
	private JButton btnPlayOnline;
	private JButton btnSettings;
	private JButton btnExit;
	private JButton btnDraw; // hoà trong chess game là draw
	private JButton btnMainMenu;
	private JButton btnSurr;
	private ImageIcon icon;
	private JLabel labelImg;
	private JLabel labelTurn;
	private Controller controller;

	private void createComponent() {
		pLeft = new JPanel(new BorderLayout());
		pLeft.setBackground(Color.BLUE);
		pLeft.setPreferredSize(new Dimension(600, 600));

		icon = new ImageIcon("images/MAIN.jpg");
		labelImg = new JLabel();
		labelImg.setIcon(icon);
		pImage = new JPanel(new BorderLayout());
		pImage.add(labelImg);

		pLeft.add(pImage);

		pRight = new JPanel();

		pRight.setPreferredSize(new Dimension(200, 600));
		Dimension dButton = new Dimension(150, 50);
		pButton = new JPanel(new GridLayout(5, 1, 0, 50));
		pButton.add(btn1Player = new JButton("Player vs. Computer"));
		btn1Player.setPreferredSize(dButton);
		btn1Player.setFocusPainted(false);
		pButton.add(btn2Player = new JButton("Player vs. Player"));
		btn2Player.setPreferredSize(dButton);
		btn2Player.setFocusPainted(false);
		pButton.add(btnPlayOnline = new JButton("Play Online (LAN)"));
		btnPlayOnline.setPreferredSize(dButton);
		btnPlayOnline.setFocusPainted(false);
		pButton.add(btnSettings = new JButton("Settings"));
		btnSettings.setPreferredSize(dButton);
		btnSettings.setFocusPainted(false);
		pButton.add(btnExit = new JButton("Exit"));
		btnExit.setPreferredSize(dButton);
		btnExit.setFocusPainted(false);

		pBtnPlaying = new JPanel(new GridLayout(4, 0, 0, 100));
		pBtnPlaying.add(labelTurn = new JLabel(TURN_WHITE, JLabel.CENTER));

		pBtnPlaying.add(btnDraw = new JButton("OFFER DRAW"));
		btnDraw.setPreferredSize(dButton);
		btnDraw.setFocusPainted(false);

		pBtnPlaying.add(btnSurr = new JButton("SURRENDER"));
		btnSurr.setPreferredSize(dButton);
		btnSurr.setFocusPainted(false);

		pBtnPlaying.add(btnMainMenu = new JButton("MAIN MENU"));
		btnMainMenu.setPreferredSize(dButton);
		btnMainMenu.setFocusPainted(false);

		pRight.add(pButton);
	}

	public MainFrame() {
		createComponent();
		controller = new Controller(this);
		boardPanel = new BoardPanel(controller);
		
		Container contentPane = getContentPane();
		GridBagLayout gridbag = new GridBagLayout();
		GridBagConstraints c = new GridBagConstraints();
		contentPane.setLayout(gridbag);
		c.fill = GridBagConstraints.BOTH;

		c.gridx = 0;
		c.gridy = 0;
		c.anchor = GridBagConstraints.WEST;
		c.insets = new Insets(0, 0, 0, 50);

		gridbag.setConstraints(pLeft, c);
		contentPane.add(pLeft);

		c.insets = new Insets(0, 0, 0, 0);
		c.gridx = 1;
		c.gridy = 0;
		c.anchor = GridBagConstraints.EAST;
		gridbag.setConstraints(pRight, c);
		contentPane.add(pRight);

		addListener();
		init();
	}

	public void changeLabelTurn() {
		labelTurn.setText((labelTurn.getText().equals(TURN_WHITE)) ? TURN_BLACK : TURN_WHITE);
	}

	public void resetLabelTurn() {
		labelTurn.setText(TURN_WHITE);
	}

	public void backToMainMenu() {
		pLeft.removeAll();
		pLeft.add(pImage);
		pLeft.revalidate();
		pLeft.repaint();

		pRight.removeAll();
		pRight.add(pButton);
		pRight.revalidate();
		pRight.repaint();
	}

	private void addListener() {
		btn1Player.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				boardPanel.setBoard(new Board(Board.MODE_PVSAI));
				labelTurn.setText(TURN_WHITE);
				pLeft.removeAll();
				pLeft.add(boardPanel);
				pLeft.revalidate();
				pLeft.repaint();

				pRight.removeAll();
				pRight.add(pBtnPlaying);
				pRight.revalidate();
				pRight.repaint();
			}
		});

		btn2Player.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				boardPanel.setBoard(new Board(Board.MODE_PVSP));
				labelTurn.setText(TURN_WHITE);
				pLeft.removeAll();
				pLeft.add(boardPanel);
				pLeft.revalidate();
				pLeft.repaint();

				pRight.removeAll();
				pRight.add(pBtnPlaying);
				pRight.revalidate();
				pRight.repaint();
			}
		});

		btnSurr.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (labelTurn.getText().equals(TURN_WHITE))
					controller.showDialogSurr(true);
				else
					controller.showDialogSurr(false);
			}
		});

		btnExit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

		btnMainMenu.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent arg0) {
				controller.showDialogMainMenu();
			}
		});

		btnDraw.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if (labelTurn.getText().equals(TURN_WHITE))
					controller.showDialogDraw(true);
				else
					controller.showDialogDraw(false);
			}
		});
	}

	private void init() {
		setTitle("Chess game");
		setSize(900, 700);
		setMinimumSize(new Dimension(900, 700));
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

}
