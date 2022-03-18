package chess.guiBoard;

import chess.pieces.Bishop;
import chess.pieces.King;
import chess.pieces.Knight;
import chess.pieces.Pawn;
import chess.pieces.Piece;
import chess.pieces.PieceType;
import chess.pieces.Queen;
import chess.pieces.Rook;

public class Game {

    private final PiecesRepresentation piecesRepresentation;

    private static final int INACTIVE_STATE_POSITION = -1;

    private final boolean[][] validPositions = new boolean[8][8];
    private final Piece[][] pieces = new Piece[8][8];

    private boolean isWhite = true;

    private int WhitePlayerScore = 0;
    private int BlackPlayerScore = 0;

    private int ActivePieceRow = INACTIVE_STATE_POSITION;
    private int ActivePieceColumn = INACTIVE_STATE_POSITION;

    private int PromotedRow = INACTIVE_STATE_POSITION;
    private int PromotedColumn = INACTIVE_STATE_POSITION;

    public Game(PiecesRepresentation piecesRepresentation) {
        this.piecesRepresentation = piecesRepresentation;
        InitializeBoard();
    }

    private void InitializeBoard() {
        // Assuming row 0, 1 are black and row 6, 7 are white.
        // Initialize row of Pawns
        // Black
        SetRowOfPawns(1, false);
        // White
        SetRowOfPawns(6, true);

        // Initialize Rooks
        // Black
        SetRowOfTwoRooks(0, false);
        // White
        SetRowOfTwoRooks(7, true);

        // Initialize Knights
        // Black
        SetRowOfTwoKnights(0, false);
        // White
        SetRowOfTwoKnights(7, true);

        // Initialize Bishop
        // Black
        SetRowOfTwoBishops(0, false);
        // White
        SetRowOfTwoBishops(7, true);

        // Initialize Queen
        // Black
        pieces[0][3] = new Queen(false, piecesRepresentation.GetPieceRepresentation(false, PieceType.Queen));
        // White
        pieces[7][3] = new Queen(true, piecesRepresentation.GetPieceRepresentation(true, PieceType.Queen));
        validPositions[7][3] = true;

        // Initialize Kings
        // Black
        pieces[0][4] = new King(false, piecesRepresentation.GetPieceRepresentation(false, PieceType.King));
        // White
        pieces[7][4] = new King(true, piecesRepresentation.GetPieceRepresentation(true, PieceType.King));
        validPositions[7][4] = true;
    }

    private void SetRowOfPawns(int row, boolean isWhite) {
        Object pawnImage = piecesRepresentation.GetPieceRepresentation(isWhite, PieceType.Pawn);
        for (int column = 0; column < 8; ++column) {
            pieces[row][column] = new Pawn(isWhite, pawnImage);
            validPositions[row][column] = isWhite;
        }
    }

    private void SetRowOfTwoRooks(int row, boolean isWhite) {
        Object rookImage = piecesRepresentation.GetPieceRepresentation(isWhite, PieceType.Rook);
        pieces[row][0] = new Rook(isWhite, rookImage);
        pieces[row][7] = new Rook(isWhite, rookImage);
        validPositions[row][0] = validPositions[row][7] = isWhite;
    }

    private void SetRowOfTwoKnights(int row, boolean isWhite) {
        Object knightImage = piecesRepresentation.GetPieceRepresentation(isWhite, PieceType.Knight);
        pieces[row][1] = new Knight(isWhite, knightImage);
        pieces[row][6] = new Knight(isWhite, knightImage);
        validPositions[row][1] = validPositions[row][6] = isWhite;
    }

    private void SetRowOfTwoBishops(int row, boolean isWhite) {
        Object bishopImage = piecesRepresentation.GetPieceRepresentation(isWhite, PieceType.Bishop);
        pieces[row][2] = new Bishop(isWhite, bishopImage);
        pieces[row][5] = new Bishop(isWhite, bishopImage);
        validPositions[row][2] = validPositions[row][5] = isWhite;
    }

    // Switch between players.
    private void TogglePlayer() {
        isWhite = !isWhite;
    }

    public void ActivateCurrentPlayerPieces() {
        for (int row = 0; row < 8; ++row) {
            for (int column = 0; column < 8; ++column) {
                /*
                Pieces[row][column] has not to be null because if it's null,
                it means that there is no piece on this tile, so it shouldn't
                be activated.
                Only Pieces of the same color as the player should be Activated.
                 */
                validPositions[row][column] = (pieces[row][column] != null && pieces[row][column].IsWhite == isWhite);
            }
        }
    }

    /*
    Bi-State
    1 - No Piece is Active, but pieces of the current player:
        On pressing a piece, the piece is Activated,
        and the valid moves of the piece is added to the
        board.
    
    2 - Piece is Active and the pieces of the current player:
        = On pressing the active piece, the game goes to state-1.
        = On pressing an Inactive piece of the current player, the
            the game goes to state-2 again, but with the new activated
            piece.
        = On pressing an empty but valid tile or a vaild movement of the
            current player piece with an opponent piece, the movement is
            performed with the current player moved to the position and if
            it has a piece, the pieced is removed and adds the score. The
            game goes to state-1 with a toggled player.
     */
    public void ActivateTile(int row, int column) {
        if (ActivePieceRow == INACTIVE_STATE_POSITION && ActivePieceColumn == INACTIVE_STATE_POSITION) {
            PerformState1(row, column);
        } else {
            PerformState2(row, column);
        }
    }

    private void DeactivatePiece() {
        ActivePieceRow = INACTIVE_STATE_POSITION;
        ActivePieceColumn = INACTIVE_STATE_POSITION;
    }

    /*
    No Piece is Active, but pieces of the current player:
    On pressing a piece, the piece is Activated,
    and the valid moves of the piece is added to the
    board.
     */
    private void PerformState1(int row, int column) {
        ActivePieceRow = row;
        ActivePieceColumn = column;
        pieces[row][column].GetValidMoves(row, column, pieces, validPositions);
    }

    /*
    Piece is Active and the pieces of the current player:
        = On pressing the active piece, the game goes to state-1.
        = On pressing an Inactive piece of the current player, the
            the game goes to state-2 again, but with the new activated
            piece.
        = On pressing an empty but valid tile or a vaild movement of the
            current player piece with an opponent piece, the movement is
            performed with the current player moved to the position and if
            it has a piece, the pieced is removed. The game goes to state-1
            with a toggled player.
     */
    private void PerformState2(int row, int column) {
        if (row == ActivePieceRow && column == ActivePieceColumn) {
            ActivateCurrentPlayerPieces();
            DeactivatePiece();
        } else {
            if (pieces[row][column] != null && pieces[row][column].IsWhite == isWhite) {
                ActivateCurrentPlayerPieces();
                PerformState1(row, column);
            } else {
                AddScore(row, column);
                pieces[row][column] = pieces[ActivePieceRow][ActivePieceColumn];
                pieces[ActivePieceRow][ActivePieceColumn] = null;
                DeactivatePiece();
                TogglePlayer();
                ActivateCurrentPlayerPieces();
                CheckPromotion(row, column);
            }
        }
    }

    private void AddScore(int row, int column) {
        if (pieces[row][column] != null) {
            if (isWhite) {
                WhitePlayerScore += pieces[row][column].Score;
            } else {
                BlackPlayerScore += pieces[row][column].Score;
            }
        }
    }

    private void CheckPromotion(int row, int column) {
        if (pieces[row][column].Type == PieceType.Pawn) {
            if ((pieces[row][column].IsWhite && row == 0)
                    || (!pieces[row][column].IsWhite && row == 7)) {
                PromotedRow = row;
                PromotedColumn = column;
            }
        }
    }

    public int GetBlackScore() {
        return BlackPlayerScore;
    }

    public int GetWhiteScore() {
        return WhitePlayerScore;
    }

    public boolean GetValidTile(int row, int column) {
        return validPositions[row][column];
    }

    public Object GetRepresentation(int row, int column) {
        if (pieces[row][column] != null) {
            return pieces[row][column].Representation;
        }
        return null;
    }

    public boolean PromotionOcurred() {
        return !(PromotedRow == INACTIVE_STATE_POSITION && PromotedColumn == INACTIVE_STATE_POSITION);
    }

    public boolean GetPromotedColor() {
        return pieces[PromotedRow][PromotedColumn].IsWhite;
    }

    public void SetPromotion(PieceType piece) {
        boolean pieceColor = pieces[PromotedRow][PromotedColumn].IsWhite;
        switch (piece) {
            case Knight ->
                pieces[PromotedRow][PromotedColumn] = new Knight(pieceColor, piecesRepresentation.GetPieceRepresentation(pieceColor, piece));

            case Queen ->
                pieces[PromotedRow][PromotedColumn] = new Queen(pieceColor, piecesRepresentation.GetPieceRepresentation(pieceColor, piece));

            case Bishop ->
                pieces[PromotedRow][PromotedColumn] = new Bishop(pieceColor, piecesRepresentation.GetPieceRepresentation(pieceColor, piece));

            case Rook ->
                pieces[PromotedRow][PromotedColumn] = new Rook(pieceColor, piecesRepresentation.GetPieceRepresentation(pieceColor, piece));
        }
        PromotedRow = PromotedColumn = INACTIVE_STATE_POSITION;
    }

    public boolean IsRunning() {
        return true;
    }
}
