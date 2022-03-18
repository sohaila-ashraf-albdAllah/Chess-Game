package chess.pieces;

public class Pawn extends Piece {

    public Pawn(boolean isWhite, Object representation) {
        super(isWhite, representation, PieceType.Pawn, 1);
    }

    @Override
    public void GetValidMoves(int row, int column, Piece[][] tilesPieces, boolean[][] validMoves) {
        int direction = IsWhite ? -1 : +1;
        for (int i = 1; i <= 2; ++i) {
            if (i == 2 && (row != 1 && row != 6)
                    || !tryAdvancing(row + i * direction, column, tilesPieces, validMoves)) {
                break;
            }
        }
        tryAttacking(row + direction, column + 1, tilesPieces, validMoves);
        tryAttacking(row + direction, column - 1, tilesPieces, validMoves);
    }

    private boolean tryAdvancing(int row, int column, Piece[][] tilesPieces, boolean[][] validMoves) {
        if (!IsValidPosition(row, column)) {
            return false;
        }
        validMoves[row][column] = tilesPieces[row][column] == null;
        return validMoves[row][column];
    }
    
    private void tryAttacking(int row, int column, Piece[][] tilesPieces, boolean[][] validMoves) {
        if (!IsValidPosition(row, column)) {
            return;
        }
        Piece piece = tilesPieces[row][column];
        if (piece != null && piece.Type != PieceType.King && piece.IsWhite != IsWhite) {
            validMoves[row][column] = true;
        }
    }
}
