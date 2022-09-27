package model;

import java.util.ArrayList;
import java.util.List;

public class KnightMove implements MoveBehavior {
	private Piece piece;
	private List<Coordinate> listCoordinate;
	private int row;
	private int col;
	private int rowTemp;
	private int colTemp;
	private Piece p;

	public KnightMove(Piece piece) {
		this.piece = piece;
		listCoordinate = new ArrayList<Coordinate>();
	}

	@Override
	public List<Coordinate> getPossibleMove() {
		listCoordinate.clear();
		row = piece.getRow();
		col = piece.getCol();

		for (int i = 0; i < 8; i++) {
			rowTemp = row;
			colTemp = col;
			switch (i) {
			case 0:
				rowTemp -= 2;
				colTemp--;
				break;
			case 1:
				rowTemp -= 2;
				colTemp++;
				break;
			case 2:
				rowTemp--;
				colTemp += 2;
				break;
			case 3:
				rowTemp++;
				colTemp += 2;
				break;
			case 4:
				rowTemp += 2;
				colTemp++;
				break;
			case 5:
				rowTemp += 2;
				colTemp--;
				break;
			case 6:
				rowTemp++;
				colTemp -= 2;
				break;
			default:
				rowTemp--;
				colTemp -= 2;
				break;
			}
			if (!Coordinate.isInvalidCoordinate(rowTemp, colTemp)) {
				p = piece.getBoard().getPieceAt(rowTemp, colTemp);
				if (p == null)
					listCoordinate.add(new Coordinate(rowTemp, colTemp));
				else if (piece.isEnemy(p))
					listCoordinate.add(new Coordinate(rowTemp, colTemp));
			}
		}
		return listCoordinate;
	}

}
