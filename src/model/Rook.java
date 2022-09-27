package model;

public class Rook extends Piece {

	public Rook(boolean white, Board board, Coordinate coor) {
		super(white, board, coor);
		this.name = (this.white == true) ? "White_Rook" : "Black_Rook";
		this.move = new RookMove(this);
	}
}
