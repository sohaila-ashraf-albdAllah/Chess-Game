package chess.pieces;

public class Bishop extends Piece {

    public Bishop(boolean isWhite, Object representation) {
        super(isWhite, representation, PieceType.Bishop, 3);
    }

    @Override
    public void GetValidMoves(int row, int column, Piece[][] tilesPieces, boolean[][] validMoves) {
        boolean II = true;
        boolean DI = true;
        boolean ID = true;
        boolean DD = true;
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
        }
    }
}
