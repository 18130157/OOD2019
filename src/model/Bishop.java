package model;

public class Bishop extends Piece {

	public Bishop(boolean white, Board board, Coordinate coor) {
		super(white, board, coor);
		this.name = (white == true) ? "White_Bishop" : "Black_Bishop";
		this.move = new BishopMove(this);
	}
}
