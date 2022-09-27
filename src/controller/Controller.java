package controller;

import java.util.List;

import ai.AI;
import model.Board;
import model.Coordinate;
import model.Piece;
import view.BoardPanel;
import view.Crown;
import view.MainFrame;
import view.MyDialog;

public class Controller {
	private Board board;
	private BoardPanel bPanel;
	private MainFrame mainFrame;
	private MyDialog dialog;
	private AI ai;

	public Controller(MainFrame mainFrame) {
		this.mainFrame = mainFrame;
		dialog = new MyDialog(this);
		ai = new AI(this);
	}

	public void requestSelect(int row, int col) {
		board.select(row, col);
	}

	public void updateView(Piece pieceSelected, List<Coordinate> listPieceGo) {
		bPanel.paintChessBox(pieceSelected, listPieceGo);
	}

	public void changeLabelTurn() {
		mainFrame.changeLabelTurn();
	}

	public void queening(Piece piece) {
		Crown.getInstance(this).queening(piece);
	}

	public void queening(Coordinate c, String type) {
		board.queening(c, type);
	}

	public void showDialogKingDie(boolean white) {
		dialog.showDialogKingDie(white);
	}

	public void backToMainMenu() {
		mainFrame.backToMainMenu();
	}

	public void newGameAgain() {
		bPanel.newGameAgain();
	}

	public void resetLabelTurn() {
		mainFrame.resetLabelTurn();
	}

	public void showDialogSurr(boolean white) {
		dialog.showDialogSurr(white);
	}

	public void showDialogDraw(boolean white) {
		dialog.showDialogDraw(white);
	}

	public void showDialogMainMenu() {
		dialog.showDialogMainMenu();
	}

	public void requestAIPlay() {
		ai.setBoard(board);
		ai.play();
	}

	// getters & setters
	public boolean isModeVsAI() {
		return board.isModeVsAi();
	}

	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	public BoardPanel getbPanel() {
		return bPanel;
	}

	public void setbPanel(BoardPanel bPanel) {
		this.bPanel = bPanel;
	}
}
