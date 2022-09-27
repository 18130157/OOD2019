package view;

import java.util.Random;

import javax.swing.JOptionPane;

import controller.Controller;

public class MyDialog {
	public static final String WHITE_WIN = "THE WHITE TEAM WINS\nDO YOU WANT TO PLAY NEWGAME ?";
	public static final String BLACK_WIN = "THE BLACK TEAM WINS\nDO YOU WANT TO PLAY NEWGAME ?";
	public static final String WHITE_SURR = "IF YOU SURRENDER, THE BLACK TEAM WINS ?";
	public static final String BLACK_SURR = "IF YOU SURRENDER, THE WHITE TEAM WINS ?";
	public static final String WHITE_DRAW = "THE WHITE TEAM WANTS TO DRAW, DO YOU AGREE ?";
	public static final String BLACK_DRAW = "THE BLACK TEAM WANTS TO DRAW, DO YOU AGREE ?";
	public static final String MAIN_MENU = "THE GAME MAY NOT BE SAVED\nARE YOU SURE WANT TO BACK MAIN MENU";
	private Controller controller;
	private int option;
	private Random rd;

	public MyDialog(Controller controller) {
		this.controller = controller;
		rd = new Random();
	}

	public void showDialogKingDie(boolean white) {
		if (white) // white == true => Black wins
			option = JOptionPane.showConfirmDialog(null, BLACK_WIN, "CONGRATULATIONS", JOptionPane.YES_NO_OPTION);
		else
			option = JOptionPane.showConfirmDialog(null, WHITE_WIN, "CONGRATULATIONS", JOptionPane.YES_NO_OPTION);

		if (option == JOptionPane.NO_OPTION || option == JOptionPane.CLOSED_OPTION)
			controller.backToMainMenu();
		else if (option == JOptionPane.YES_OPTION)
			controller.newGameAgain();
	}

	public void showDialogSurr(boolean white) {
		if (white)
			option = JOptionPane.showConfirmDialog(null, WHITE_SURR, "SURRENDER", JOptionPane.YES_NO_OPTION);
		else
			option = JOptionPane.showConfirmDialog(null, BLACK_SURR, "SURRENDER", JOptionPane.YES_NO_OPTION);

		if (option == JOptionPane.YES_OPTION)
			controller.showDialogKingDie(white);
	}

	public void showDialogDraw(boolean white) {
		if (controller.isModeVsAI()) {
			if (rd.nextInt(2) == 1) // random ra 0 hoặc 1
				showDialogAgreeDraw();
			else
				showDialogAIDisagreeDraẉ̣();
			return;
		}
		if (white)
			option = JOptionPane.showConfirmDialog(null, WHITE_DRAW, "OFFER DRAW", JOptionPane.YES_NO_OPTION);
		else
			option = JOptionPane.showConfirmDialog(null, BLACK_DRAW, "OFFER DRAW", JOptionPane.YES_NO_OPTION);

		if (option == JOptionPane.YES_OPTION)
			showDialogAgreeDraw();
	}

	public void showDialogAgreeDraw() {
		option = JOptionPane.showConfirmDialog(null, "DRAW GAME\nDO YOU WANT TO PLAY NEWGAME ?", "DRAW ! ! !",
				JOptionPane.YES_NO_OPTION);
		if (option == JOptionPane.NO_OPTION || option == JOptionPane.CLOSED_OPTION)
			controller.backToMainMenu();
		else if (option == JOptionPane.YES_OPTION)
			controller.newGameAgain();
	}

	public void showDialogAIDisagreeDraẉ̣() {
		JOptionPane.showMessageDialog(null, "THE AI DOES NOT AGREE TO DRAW !", "DISAGREE",
				JOptionPane.INFORMATION_MESSAGE);
	}

	public void showDialogMainMenu() {
		option = JOptionPane.showConfirmDialog(null, MAIN_MENU, "BACK TO MAIN MENU", JOptionPane.YES_NO_OPTION);
		if (option == JOptionPane.YES_OPTION)
			controller.backToMainMenu();
	}

}
