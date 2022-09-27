package model;

import java.util.ArrayList;
import java.util.List;

public class PawnMove implements MoveBehavior {
	private Piece piece;
	private List<Coordinate> listCoordinate;
	private int row;
	private int col;
	private int rowTemp;
	private int colTemp;

	public PawnMove(Piece piece) {
		this.piece = piece;
		listCoordinate = new ArrayList<Coordinate>();
	}

	@Override
	public List<Coordinate> getPossibleMove() {
		listCoordinate.clear();
		row = piece.getRow();
		col = piece.getCol();

		rowTemp = row;
		colTemp = col;

		if (piece.isWhite()) { // White Pawn
			for (int i = 0; i < 2; i++) {
				rowTemp--;
				if (!(Coordinate.isInvalidCoordinate(rowTemp, colTemp))
						&& piece.getBoard().isEmptyCellAt(rowTemp, colTemp))
					listCoordinate.add(new Coordinate(rowTemp, colTemp));
				else
					break;
				if (row != 6) // do là fix cứng nên Tốt trắng luôn ở dòng 6, nước đi đầu tiên được tiến 2 ô
					break;
			}

			// when eating piece
			for (int i = 0; i < 2; i++) {
				rowTemp = row;
				colTemp = col;
				switch (i) {
				case 0:
					rowTemp--;
					colTemp--;
					break;
				default:
					rowTemp--;
					colTemp++;
					break;
				}
				if (!(Coordinate.isInvalidCoordinate(rowTemp, colTemp))
						&& piece.getBoard().getPieceAt(rowTemp, colTemp) != null)
					if (piece.isEnemy(piece.getBoard().getPieceAt(rowTemp, colTemp)))
						listCoordinate.add(new Coordinate(rowTemp, colTemp));
			}
		} else { // Black Pawn
			for (int i = 0; i < 2; i++) {
				rowTemp++;
				if (!(Coordinate.isInvalidCoordinate(rowTemp, colTemp))
						&& piece.getBoard().isEmptyCellAt(rowTemp, colTemp))
					listCoordinate.add(new Coordinate(rowTemp, colTemp));
				else
					break;
				if (row != 1)
					break;
			}

			// when eating piece
			for (int i = 0; i < 2; i++) {
				rowTemp = row;
				colTemp = col;
				switch (i) {
				case 0:
					rowTemp++;
					colTemp--;
					break;
				default:
					rowTemp++;
					colTemp++;
					break;
				}
				if (!(Coordinate.isInvalidCoordinate(rowTemp, colTemp))
						&& piece.getBoard().getPieceAt(rowTemp, colTemp) != null)
					if (piece.isEnemy(piece.getBoard().getPieceAt(rowTemp, colTemp)))
						listCoordinate.add(new Coordinate(rowTemp, colTemp));
			}
		}
		return listCoordinate;
	}

}
