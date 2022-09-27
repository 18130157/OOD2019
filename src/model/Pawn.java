package model;

public class Pawn extends Piece {

	public Pawn(boolean white, Board board, Coordinate coor) {
		super(white, board, coor);
		this.name = (this.white == true) ? "White_Pawn" : "Black_Pawn";
		this.move = new PawnMove(this);
	}
}
