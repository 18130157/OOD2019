package model;

public class Queen extends Piece {

	public Queen(boolean white, Board board, Coordinate coor) {
		super(white, board, coor);
		this.name = (this.white == true) ? "White_Queen" : "Black_Queen";
		this.move = new QueenMove(this);
	}
}
