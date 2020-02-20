package boardgame;

public abstract class Piece {

	protected Position position;
	private Board board;

	public Piece(Board board) {
		this.board = board;
		position = null;
	}

	protected Board getBoard() {
		return board;
	}

	public abstract boolean[][] possibleMvoves();

	public boolean possibleMove(Position position) {
		return possibleMvoves()[position.getRow()][position.getColumn()];
	}
}
