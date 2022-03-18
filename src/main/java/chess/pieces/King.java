package chess.pieces;

public class King extends Piece {

    private static final int[] dRow = new int[]{
        1, -1, 0, 0, 1, 1, -1, -1
    };

    private static final int[] dCol = new int[]{
        0, 0, 1, -1, 1, -1, 1, -1
    };

    public King(boolean isWhite, Object representation) {
        super(isWhite, representation, PieceType.King, 0);
    }

    @Override
    public void GetValidMoves(int row, int column, Piece[][] tilesPieces, boolean[][] validMoves) {
        for (int k = 0; k < 8; ++k) {
            TryToSet(row + dRow[k], column + dCol[k], tilesPieces, validMoves);
        }
    }
}
