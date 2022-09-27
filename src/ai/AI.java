package ai;

import java.util.ArrayList;
import java.util.List;

import controller.Controller;
import model.Board;
import model.Coordinate;
import model.Piece;

public class AI {
	private boolean whitePiece;
	private Controller controller;
	private Board board;
	private Piece king;
	private List<Coordinate> allMoveOfEnemy;

	public AI(Controller controller) {
		this.controller = controller;
		whitePiece = false;
		allMoveOfEnemy = new ArrayList<Coordinate>();
	}

	public void setBoard(Board board) {
		if (this.board != board) {
			this.board = board;
			// do là fix cứng AI là Black và Black ở trên
			// nên vị trí ban đầu của Vua luôn là [0, 4]
			king = board.getPieceAt(0, 4);
		}
	}

	public void play() {
		if (isBeChecked())
			moveKing();
		else
			attack();
	}

	public boolean isBeChecked() {
		getAllPossibleMoveOfEnemy();
		if (!allMoveOfEnemy.isEmpty())
			if (allMoveOfEnemy.contains(king.getCoordinate()))
				return true;
		return false;
	}

	public void getAllPossibleMoveOfEnemy() {
		allMoveOfEnemy.clear();
		for (int row = 0; row < 8; row++)
			for (int col = 0; col < 8; col++)
				if (board.getPieceAt(row, col) != null)
					if (!board.getPieceAt(row, col).equalsColor(this.whitePiece))
						allMoveOfEnemy.addAll(board.getPieceAt(row, col).getPossibleMove());
	}

	private void moveKing() {
		if (!king.getPossibleMove().isEmpty()) {
			controller.requestSelect(king.getRow(), king.getCol());
			int row = king.getPossibleMove().get(0).getRow();
			int col = king.getPossibleMove().get(0).getCol();
			controller.requestSelect(row, col);
			king = board.getPieceAt(row, col);
		} else
			attack();
	}

	private void attack() {
		for (int row = 0; row < 8; row++)
			for (int col = 0; col < 8; col++)
				if (board.getPieceAt(row, col) != null)
					if (board.getPieceAt(row, col).equalsColor(this.whitePiece))
						if (!board.getPieceAt(row, col).isKing())
							if (board.getPieceAt(row, col).getPossibleMove().size() > 0) {
								controller.requestSelect(row, col);
								controller.requestSelect(board.getPieceAt(row, col).getPossibleMove().get(0).getRow(),
										board.getPieceAt(row, col).getPossibleMove().get(0).getCol());
								return;
							}
		moveKing();
	}

}
