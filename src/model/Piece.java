package model;

import java.util.List;

public abstract class Piece {
	protected boolean white;
	protected Board board;
	protected Coordinate coor;
	protected String name;
	protected MoveBehavior move;

	public Piece(boolean white, Board board, Coordinate coor) {
		this.white = white;
		this.board = board;
		this.coor = coor;
	}

	public List<Coordinate> getPossibleMove() {
		return move.getPossibleMove();
	}

	public boolean isEnemy(Piece that) {
		return (this.white != that.white) ? true : false;
	}

	public boolean isCanMoveTo(int row, int col) {
		Coordinate c = new Coordinate(row, col);
		for (Coordinate coor : getPossibleMove())
			if (coor.equals(c))
				return true;
		return false;
	}

	public int getRow() {
		return getCoordinate().getRow();
	}

	public int getCol() {
		return getCoordinate().getCol();
	}

	public void setCoordinate(int row, int col) {
		setCoordinate(new Coordinate(row, col));
	}

	public boolean isKing() {
		return (this instanceof King);
	}

	// kiểm tra có phải là quân hàm gì và màu gì
	// ví dụ (King, false) => kiểm tra có phải là Black King không
	public boolean isPieceType(String type, boolean white) {
		switch (type) {
		case "Pawn":
			if (this instanceof Pawn)
				return this.equalsColor(white);
			break;
		case "Rook":
			if (this instanceof Rook)
				return this.equalsColor(white);
			break;
		case "Knight":
			if (this instanceof Knight)
				return this.equalsColor(white);
			break;
		case "Bishop":
			if (this instanceof Bishop)
				return this.equalsColor(white);
			break;
		case "Queen":
			if (this instanceof Queen)
				return this.equalsColor(white);
			break;
		case "King":
			if (this instanceof King)
				return this.equalsColor(white);
			break;
		}
		return false;
	}

	public boolean equalsColor(boolean white) {
		return this.white == white;
	}

	// getters & setters
	public boolean isWhite() {
		return white;
	}

	public String getName() {
		return name;
	}

	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	public Coordinate getCoordinate() {
		return coor;
	}

	public void setCoordinate(Coordinate coor) {
		this.coor = coor;
	}

}
