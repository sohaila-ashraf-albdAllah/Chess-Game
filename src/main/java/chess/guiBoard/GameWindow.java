package chess.guiBoard;

import chess.pieces.PieceType;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import javax.swing.border.EmptyBorder;

public class GameWindow {

    private static final Color WHITE_TILE_COLOR = new Color(245, 230, 205);
    private static final Color BLACK_TILE_COLOR = new Color(205, 172, 129);
    private final JFrame menu;
    private final JFrame Frame;

    private final JLabel WhitePlayerNameLabel;
    private final JLabel WhitePlayerScoreLabel;

    private final JLabel BlackPlayerNameLabel;
    private final JLabel BlackPlayerScoreLabel;

    private Game game;

    private final JButton[][] Tiles;

    private PiecesImages piecesImages;

    private final ActionListener Activationback = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            Frame.dispose();
            menu.setVisible(true);
        }
    };
    private final ActionListener onTileActivation = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (!game.IsRunning()) {
                return;
            }
            JButton tile = (JButton) e.getSource();
            String name = tile.getName();
            int num = Integer.parseInt(name, 4, name.length(), 10);
            int row = num / 8;
            int col = num % 8;
            game.ActivateTile(row, col);
            if (game.PromotionOcurred()) {
                PromotionWindow promotionwindow = new PromotionWindow(piecesImages, Frame, game.GetPromotedColor());
                PieceType pieceType = promotionwindow.GetChosenPiece();
                game.SetPromotion(pieceType);
            }
            update();
        }
    };

    public GameWindow(String firstPlayerName, String secondPlayerName, JFrame menu) {
        this.menu = menu;
        menu.setVisible(false);
        piecesImages = new PiecesImages();
        game = new Game(piecesImages);

        JButton backbutton = new JButton();
        backbutton.addActionListener(Activationback);
        backbutton.setText("Back");
        backbutton.setFont(backbutton.getFont().deriveFont(Font.BOLD, 20.0f));
        backbutton.setBackground(new Color(48, 56, 65));
        backbutton.setForeground(new Color(238, 238, 238));

        Frame = new JFrame("Chess");
        Frame.setLayout(new GridLayout(1, 1, 5, 5));
        Frame.setSize(720, 720);
        Frame.setResizable(false);
        Frame.setUndecorated(true);

        // Draw Player Name Label and Player Score Label
        WhitePlayerNameLabel = new JLabel(firstPlayerName, SwingConstants.CENTER);
        WhitePlayerScoreLabel = new JLabel("0", SwingConstants.CENTER);

        BlackPlayerNameLabel = new JLabel(secondPlayerName, SwingConstants.CENTER);
        BlackPlayerScoreLabel = new JLabel("0", SwingConstants.CENTER);
        ////**********************
        JPanel playersPanel = new JPanel(new GridLayout(1, 1, 7, 7));
        playersPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
///************************
        JPanel whitePlayerPanel = new JPanel(new GridLayout(2, 1, 5, 5));
        whitePlayerPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        whitePlayerPanel.setBackground(new Color(221, 221, 221));
        whitePlayerPanel.add(WhitePlayerNameLabel, 0);
        whitePlayerPanel.add(WhitePlayerScoreLabel, 1);
///*****************
        JPanel blackPlayerPanel = new JPanel(new GridLayout(2, 1, 5, 5));
        blackPlayerPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
        blackPlayerPanel.setBackground(new Color(170, 170, 170));

        blackPlayerPanel.add(BlackPlayerNameLabel, 0);
        blackPlayerPanel.add(BlackPlayerScoreLabel, 1);

        playersPanel.add(whitePlayerPanel, 0);
        playersPanel.add(blackPlayerPanel, 1);
        playersPanel.add(backbutton, 2);
        playersPanel.setSize(640, 100);
        playersPanel.setBackground(Color.WHITE);
//***********************
        JPanel playPanel = new JPanel(new GridLayout(8, 8, 0, 0));

        playPanel.setSize(560, 560);
        playPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

        Tiles = new JButton[8][8];

        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {

                Tiles[row][col] = new JButton();

                Tiles[row][col].setName("Tile" + (row * 8 + col));

                Tiles[row][col].addActionListener(onTileActivation);

                // Setting color according to the position
                Tiles[row][col].setBackground(((row + col) % 2 == 0)
                        ? WHITE_TILE_COLOR : BLACK_TILE_COLOR
                );

                Tiles[row][col].setBorderPainted(false);
                Tiles[row][col].setFocusPainted(false);
                playPanel.add(Tiles[row][col]);
            }
        }
//************************
        JPanel mainPanel = new JPanel(new BorderLayout(0, 0));
        mainPanel.setBorder(new EmptyBorder(4, 4, 4, 4));
        mainPanel.setSize(710, 710);

        mainPanel.add(playPanel, BorderLayout.CENTER);
        mainPanel.add(playersPanel, BorderLayout.SOUTH);

        Frame.getContentPane().add(mainPanel);

        Frame.setLocationRelativeTo(null);

        Frame.setVisible(true);
        update();
    }

    private void update() {
        WhitePlayerScoreLabel.setText(Integer.toString(game.GetWhiteScore()));
        BlackPlayerScoreLabel.setText(Integer.toString(game.GetBlackScore()));
        for (int row = 0; row < 8; ++row) {
            for (int col = 0; col < 8; ++col) {
                Tiles[row][col].setIcon((ImageIcon) game.GetRepresentation(row, col));
                Tiles[row][col].setEnabled(game.GetValidTile(row, col));
            }
        }
    }
}
