package model;

import controller.Controller;

public class Board {
	public static final String MODE_PVSP = "PvsP";
	public static final String MODE_PVSAI = "PvsAI";

	private Piece[][] board = new Piece[8][8];
	private String mode;
	private boolean turnWhite;
	private boolean hasKingDie;
	private Piece pieceSelected = null;
	private Controller controller;

	public Board(String mode) {
		this.mode = mode;
		turnWhite = true;
		hasKingDie = false;
		// chess Black
		board[0][0] = new Rook(false, this, new Coordinate(0, 0));
		board[0][7] = new Rook(false, this, new Coordinate(0, 7));
		board[0][1] = new Knight(false, this, new Coordinate(0, 1));
		board[0][6] = new Knight(false, this, new Coordinate(0, 6));
		board[0][2] = new Bishop(false, this, new Coordinate(0, 2));
		board[0][5] = new Bishop(false, this, new Coordinate(0, 5));
		board[0][3] = new Queen(false, this, new Coordinate(0, 3));
		board[0][4] = new King(false, this, new Coordinate(0, 4));
		for (int i = 0; i < 8; i++)
			board[1][i] = new Pawn(false, this, new Coordinate(1, i));

		// chess White
		board[7][0] = new Rook(true, this, new Coordinate(7, 0));
		board[7][7] = new Rook(true, this, new Coordinate(7, 7));
		board[7][1] = new Knight(true, this, new Coordinate(7, 1));
		board[7][6] = new Knight(true, this, new Coordinate(7, 6));
		board[7][2] = new Bishop(true, this, new Coordinate(7, 2));
		board[7][5] = new Bishop(true, this, new Coordinate(7, 5));
		board[7][3] = new Queen(true, this, new Coordinate(7, 3));
		board[7][4] = new King(true, this, new Coordinate(7, 4));
		for (int i = 0; i < 8; i++)
			board[6][i] = new Pawn(true, this, new Coordinate(6, i));

		// Cell empty
		for (int iRow = 2; iRow <= 5; iRow++)
			for (int iCol = 0; iCol <= 7; iCol++)
				board[iRow][iCol] = null;
	}

	public boolean isEmptyCellAt(int row, int col) {
		return (getPieceAt(row, col) == null) ? true : false;
	}

	public boolean isEmptyCellAt(Coordinate coor) {
		return isEmptyCellAt(coor.getRow(), coor.getCol());
	}

	public Piece getPieceAt(int row, int col) {
		return board[row][col];
	}

	public Piece getPieceAt(Coordinate coor) {
		return getPieceAt(coor.getRow(), coor.getCol());
	}

	public void select(int row, int col) {
		if (pieceSelected == null) {
			if (getPieceAt(row, col) != null)
				if (turnWhite == getPieceAt(row, col).isWhite()) {
					pieceSelected = getPieceAt(row, col);
					controller.updateView(pieceSelected, pieceSelected.getPossibleMove());
				}
		} else {
			// nếu đã chọn quân cờ và click tiếp theo là move
			if (pieceSelected.isCanMoveTo(row, col)) {
				movePieceTo(row, col, pieceSelected);
				checkKingDie(); // kiểm tra vua chết
				if (!hasKingDie) {
					if (!checkCrown()) // kiểm tra phong hậu
						change();
					if (isModeVsAi()) {
						controller.requestAIPlay();
						controller.updateView(null, null);
						return;
					}
				}
			} else {
				// click ra ngoài các ô có thể đi => bỏ chọn quân cờ
				pieceSelected = null;
				controller.updateView(null, null);
			}

			if (!hasKingDie)
				// đã chọn quân cờ nhưng đổi chọn quân cờ khác
				if (getPieceAt(row, col) != null)
					if (turnWhite == getPieceAt(row, col).isWhite()) {
						pieceSelected = getPieceAt(row, col);
						controller.updateView(pieceSelected, pieceSelected.getPossibleMove());
					} else {
						// click vào quân cờ đối phương trong khi đang lượt của mình => bỏ chọn quân cờ
						pieceSelected = null;
						controller.updateView(null, null);
					}
		}
	}

	private void change() {
		pieceSelected = null;
		changeTurn();
		controller.updateView(pieceSelected, null);
		controller.changeLabelTurn();
	}

	private void changeTurn() {
		turnWhite = (turnWhite == true) ? false : true;
	}

	public void movePieceTo(int row, int col, Piece piece) {
		board[piece.getRow()][piece.getCol()] = null;
		board[row][col] = piece;
		piece.setCoordinate(row, col);
	}

	private boolean checkKingDie() {
		boolean whiteKingAlive = false;
		boolean blackKingAlive = false;
		for (int row = 0; row < 8; row++)
			for (int col = 0; col < 8; col++) {
				if (getPieceAt(row, col) != null && getPieceAt(row, col).getName().equals("White_King"))
					whiteKingAlive = true;
				if (getPieceAt(row, col) != null && getPieceAt(row, col).getName().equals("Black_King"))
					blackKingAlive = true;
			}
		if (whiteKingAlive == true && blackKingAlive == true)
			return hasKingDie = false;

		if (whiteKingAlive == false) {
			controller.updateView(null, null);
			controller.showDialogKingDie(true);
			return hasKingDie = true;
		} else if (blackKingAlive == false) {
			controller.updateView(null, null);
			controller.showDialogKingDie(false);
			return hasKingDie = true;
		}
		return false;
	}

	private boolean checkCrown() {
		if (pieceSelected instanceof Pawn) {
			if (pieceSelected.isWhite() && pieceSelected.getRow() == 0) {
				controller.queening(pieceSelected);
				return true;
			}

			if (!pieceSelected.isWhite() && pieceSelected.getRow() == 7) {
				controller.queening(pieceSelected);
				return true;
			}
		}
		return false;
	}

	public void queening(Coordinate c, String type) {
		switch (type) {
		case "Queen":
			setPieceAt(c, new Queen(getPieceAt(c).isWhite(), this, c));
			change();
			break;
		case "Bishop":
			setPieceAt(c, new Bishop(getPieceAt(c).isWhite(), this, c));
			change();
			break;
		case "Knight":
			setPieceAt(c, new Knight(getPieceAt(c).isWhite(), this, c));
			change();
			break;
		case "Rook":
			setPieceAt(c, new Rook(getPieceAt(c).isWhite(), this, c));
			change();
			break;
		}

		if (isModeVsAi()) {
			controller.requestAIPlay();
			controller.updateView(null, null);
		}
	}

	public void setPieceAt(Coordinate c, Piece p) {
		board[c.getRow()][c.getCol()] = p;
	}

	public boolean isModeVsAi() {
		return this.mode.equals(MODE_PVSAI);
	}

	// getters & setters
	public String getMode() {
		return mode;
	}

	public void setController(Controller controller) {
		this.controller = controller;
	}
}
