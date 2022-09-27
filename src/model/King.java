package model;

public class King extends Piece {

	public King(boolean white, Board board, Coordinate coor) {
		super(white, board, coor);
		this.name = (white == true) ? "White_King" : "Black_King";
		this.move = new KingMove(this);
	}
}
