package chess.guiBoard;

import chess.pieces.PieceType;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

public class PiecesImages implements PiecesRepresentation {

    private final ImageIcon[] blackPiecesImages;
    private final ImageIcon[] whitePiecesImages;

    /**
     * It loads the Images of the Chess Pieces
     */
    public static final PieceType[] pieceTypes = new PieceType[]{
        PieceType.King, PieceType.Knight, PieceType.Bishop,
        PieceType.Queen, PieceType.Pawn, PieceType.Rook
    };

    public PiecesImages() {
        blackPiecesImages = new ImageIcon[6];
        whitePiecesImages = new ImageIcon[6];

        for (int i = 0; i < pieceTypes.length; ++i) {
            try {
                blackPiecesImages[i]
                        = new ImageIcon(ImageIO.read(getClass().getResource("/Pieces/Black " + pieceTypes[i] + ".png")));
                whitePiecesImages[i]
                        = new ImageIcon(ImageIO.read(getClass().getResource("/Pieces/White " + pieceTypes[i] + ".png")));
            } catch (IOException ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    @Override
    public ImageIcon GetPieceRepresentation(boolean isWhite, PieceType type) {
        int index = -1;
        for (int i = 0; i < pieceTypes.length; ++i) {
            if (type == pieceTypes[i]) {
                index = i;
                break;
            }
        }
        if (isWhite) {
            return whitePiecesImages[index];
        }
        return blackPiecesImages[index];
    }
}
