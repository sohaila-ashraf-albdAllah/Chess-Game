package chess.guiBoard;

import chess.pieces.PieceType;

public interface PiecesRepresentation {

    /**
     * It returns the piece image icon given the piece color and the piece type.
     *
     * @param isWhite the color of the piece. TRUE if WHITE. FALSE if BLACK.
     * @param type the type of the piece. It can be King, Queen, Bishop, Knight
     * Rook and Pawn
     * @return object which represents the piece of the given type and color.
     */
    Object GetPieceRepresentation(boolean isWhite, PieceType type);
}
