package model;

import java.util.ArrayList;
import java.util.List;

public class QueenMove implements MoveBehavior {
	@SuppressWarnings("unused")
	private Piece piece;
	private List<Coordinate> listCoordinate;
	private MoveBehavior rookMove;
	private MoveBehavior bishopMove;
	
	public QueenMove(Piece piece) {
		this.piece = piece;
		listCoordinate = new ArrayList<Coordinate>();
		rookMove = new RookMove(piece);
		bishopMove = new BishopMove(piece);
	}

	@Override
	public List<Coordinate> getPossibleMove() {
		listCoordinate.clear();
		listCoordinate.addAll(rookMove.getPossibleMove());
		listCoordinate.addAll(bishopMove.getPossibleMove());
		return listCoordinate;
	}
}
