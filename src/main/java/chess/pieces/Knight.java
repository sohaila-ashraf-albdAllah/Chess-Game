package chess.pieces;

public class Knight extends Piece {

    private static final int[] dRow = new int[]{
        -2, -2, 2, 2, 1, 1, -1, -1
    };

    private static final int[] dCol = new int[]{
        1, -1, 1, -1, 2, -2, 2, -2
    };

    public Knight(boolean isWhite, Object representation) {
        super(isWhite, representation, PieceType.Knight, 3);
    }

    @Override
    public void GetValidMoves(int row, int column, Piece[][] tilesPieces, boolean[][] validMoves) {
        for (int k = 0; k < 8; ++k) {
            TryToSet(row + dRow[k], column + dCol[k], tilesPieces, validMoves);
        }
    }
}
