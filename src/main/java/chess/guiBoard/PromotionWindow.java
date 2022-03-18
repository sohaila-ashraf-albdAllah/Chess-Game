package chess.guiBoard;

import chess.pieces.PieceType;

import java.awt.Color;
import java.awt.Dialog;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;

public class PromotionWindow {
    
    private final JButton piecesButtons[];
    private final PiecesImages piecesImages;
    private PieceType chosenPiece;

    private final JDialog promotion;

    private final ActionListener choosingPieceEvent = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton button = (JButton) e.getSource();
            String name = button.getName();
            chosenPiece = PieceType.valueOf(name);
            promotion.setVisible(false);
        }
    };

    public PromotionWindow(PiecesImages piecesImages, JFrame owner, boolean isWhite) {

        this.piecesImages = piecesImages;

        promotion = new JDialog(owner);
        promotion.setModalityType(Dialog.ModalityType.DOCUMENT_MODAL);
        promotion.setSize(720, 100);
        promotion.setResizable(false);
        promotion.setLayout(new GridLayout(1, 4));
        promotion.setUndecorated(true);
        promotion.setLocation(720 / 2, 720 / 2);

        piecesButtons = new JButton[4];

        for (int i = 0; i < piecesButtons.length; i++) {
            piecesButtons[i] = new JButton();
            piecesButtons[i].setBorderPainted(true);
            piecesButtons[i].setFocusPainted(false);
            piecesButtons[i].setBackground(Color.LIGHT_GRAY);
            piecesButtons[i].addActionListener(choosingPieceEvent);
            promotion.add(piecesButtons[i]);
        }
        
       SetPiecesButtonsNames();
        SetImages(isWhite);
    }

    public PieceType GetChosenPiece() {
        promotion.setVisible(true);
        return chosenPiece;
    }

    private void SetPiecesButtonsNames() {
        piecesButtons[0].setName(PieceType.Queen.toString());
        piecesButtons[1].setName(PieceType.Bishop.toString());
        piecesButtons[2].setName(PieceType.Knight.toString());
        piecesButtons[3].setName(PieceType.Rook.toString());
    }

    private void SetImages(boolean Iswhite) {
        for (JButton piecesButton : piecesButtons) {
            piecesButton.setIcon(piecesImages.GetPieceRepresentation(Iswhite, PieceType.valueOf(piecesButton.getName())));
        }
    }

}
