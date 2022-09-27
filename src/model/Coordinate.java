package model;

public class Coordinate {
	private int row;
	private int col;

	public Coordinate(int row, int col) {
		this.row = row;
		this.col = col;
	}

	public static boolean isInvalidCoordinate(int row, int col) {
		if (row < 0 || col < 0 || row > 7 || col > 7)
			return true;
		return false;
	}

	@Override
	public boolean equals(Object obj) {
		try {
			Coordinate that = (Coordinate) obj;
			return this.row == that.row && this.col == that.col;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public String toString() {
		return String.format("[%d, %d]", row, col);
	}

	// getters & setters
	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getCol() {
		return col;
	}

	public void setCol(int col) {
		this.col = col;
	}

}
