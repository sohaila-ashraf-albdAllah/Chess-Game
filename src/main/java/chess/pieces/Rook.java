package chess.pieces;

public class Rook extends Piece {

    public Rook(boolean isWhite, Object representation) {
        super(isWhite, representation, PieceType.Rook, 5);
    }

    @Override
    public void GetValidMoves(int row, int column, Piece[][] tilesPieces, boolean[][] validMoves) {
        boolean IRow = true;
        boolean DRow = true;
        boolean IColumn = true;
        boolean DColumn = true;
        for (int k = 1; k < 8; ++k) {
            if (IRow) {
                IRow = TryToSet(row + k, column, tilesPieces, validMoves);
            }
            if (DRow) {
                DRow = TryToSet(row - k, column, tilesPieces, validMoves);
            }
            if (IColumn) {
                IColumn = TryToSet(row, column + k, tilesPieces, validMoves);
            }
            if (DColumn) {
                DColumn = TryToSet(row, column - k, tilesPieces, validMoves);
            }
        }
    }
}
