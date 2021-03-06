package chess;

import boardgame.Board;
import boardgame.Piece;
import boardgame.Position;
import chess.pieces.King;
import chess.pieces.Rook;

public class ChessMatch {

	private Board board;

	public ChessMatch() {
		board = new Board(8, 8);
		initialSetUp();
	}

	public ChessPiece[][] getPieces() {
		ChessPiece[][] mat = new ChessPiece[board.getRow()][board.getColumns()];
		for (int i = 0; i < board.getRow(); i++) {
			for (int j = 0; j < board.getColumns(); j++) {
				mat[i][j] = (ChessPiece) board.piece(i, j);
			}
		}
		return mat;

	}

	public ChessPiece performChessMove(ChessPosition sourcePosition, ChessPosition targetPosition) {
		Position source = sourcePosition.toPosition();
		Position target = targetPosition.toPosition();

		validadeTargetPosition(source, target);
		validateSourcePosition(source);

		Piece capturedPiece = makeMove(source, target);
		return (ChessPiece) capturedPiece;
	}

	private Piece makeMove(Position source, Position target) {
		Piece p = board.removePiece(source);
		Piece capturedPiece = board.removePiece(target);
		board.pleasePiece(p, target);
		return capturedPiece;
	}

	private void validateSourcePosition(Position position) {
		if (!(board.thereIsAPiece(position))) {
			throw new ChessExcepition("There is no piece source position.");
		}
	}

	private void validadeTargetPosition(Position source, Position target) {
		if(!board.piece(source).possibleMove(target)){
			throw new ChessExcepition("The cosen piece can't move to target position");
		}
	}

	private void placeNewPiece(char column, int row, ChessPiece piece) {
		board.pleasePiece(piece, new ChessPosition(column, row).toPosition());
	}

	private void initialSetUp() {
		placeNewPiece('b', 6, new Rook(board, Color.WHITE));
		placeNewPiece('e', 8, new King(board, Color.BLACK));
		placeNewPiece('a', 1, new Rook(board, Color.WHITE));
		placeNewPiece('g', 3, new King(board, Color.BLACK));
		placeNewPiece('d', 4, new Rook(board, Color.WHITE));

	}
}
