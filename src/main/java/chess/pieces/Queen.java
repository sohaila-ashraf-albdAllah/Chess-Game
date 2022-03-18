package chess.pieces;

public class Queen extends Piece {

    public Queen(boolean isWhite, Object representation) {
        super(isWhite, representation, PieceType.Queen, 9);
    }

    @Override
    public void GetValidMoves(int row, int column, Piece[][] tilesPieces, boolean[][] validMoves) {
        boolean II = true;
        boolean DI = true;
        boolean ID = true;
        boolean DD = true;
        boolean IRow = true;
        boolean DRow = true;
        boolean IColumn = true;
        boolean DColumn = true;
        for (int k = 1; k < 8; ++k) {
            if (II) {
                II = TryToSet(row + k, column + k, tilesPieces, validMoves);
            }
            if (DI) {
                DI = TryToSet(row - k, column + k, tilesPieces, validMoves);
            }
            if (ID) {
                ID = TryToSet(row + k, column - k, tilesPieces, validMoves);
            }
            if (DD) {
                DD = TryToSet(row - k, column - k, tilesPieces, validMoves);
            }
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
