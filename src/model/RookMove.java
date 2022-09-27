package model;

import java.util.ArrayList;
import java.util.List;

public class RookMove implements MoveBehavior {
	private Piece piece;
	List<Coordinate> listCoordinate;
	private int row;
	private int col;
	private int rowTemp;
	private int colTemp;
	private Piece p;

	public RookMove(Piece piece) {
		this.piece = piece;
		listCoordinate = new ArrayList<Coordinate>();
	}

	@Override
	public List<Coordinate> getPossibleMove() {
		listCoordinate.clear();
		 row = piece.getRow();
		 col = piece.getCol();

		for (int i = 0; i < 4; i++) {
			rowTemp = row;
			colTemp = col;
			while (true) {
				switch (i) {
				case 0:
					rowTemp--;
					break;
				case 1:
					rowTemp++;
					break;
				case 2:
					colTemp++;
					break;
				default:
					colTemp--;
					break;
				}
				if (Coordinate.isInvalidCoordinate(rowTemp, colTemp))
					break;
				p = piece.getBoard().getPieceAt(rowTemp, colTemp);
				if (p == null)
					listCoordinate.add(new Coordinate(rowTemp, colTemp));
				else {
					if (piece.isEnemy(p)) {
						listCoordinate.add(new Coordinate(rowTemp, colTemp));
						break;
					} else
						break;
				}
			}
		}

		return listCoordinate;
	}

}
