package model;

public class Knight extends Piece {

	public Knight(boolean white, Board board, Coordinate coor) {
		super(white, board, coor);
		this.name = (this.white == true) ? "White_Knight" : "Black_Knight";
		this.move = new KnightMove(this);
	}
}
