package chess.pieces;

public abstract class Piece {

    public final boolean IsWhite;
    public final PieceType Type;
    public final int Score;

    public final Object Representation;

    public Piece(boolean isWhite, Object representation, PieceType type, int score) {
        IsWhite = isWhite;
        Representation = representation;
        Type = type;
        Score = score;
    }

    public abstract void GetValidMoves(int row, int column, Piece[][] tilesPieces, boolean[][] validMoves);

    protected boolean TryToSet(int row, int column, Piece[][] tilesPieces, boolean[][] validMoves) {
        if (!IsValidPosition(row, column)) {
            return false;
        }
        if (tilesPieces[row][column] == null) {
            validMoves[row][column] = true;
            return true;
        }

        if (tilesPieces[row][column].IsWhite != IsWhite) {
            validMoves[row][column] = true;
        }

        return false;
    }

    protected static boolean IsValidPosition(int row, int column) {

        return !(row < 0 || 7 < row || column < 0 || 7 < column);
    }
}
