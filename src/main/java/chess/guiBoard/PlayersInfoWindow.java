package chess.guiBoard;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class PlayersInfoWindow {

    private final JFrame playFrame;

    private final JTextField WhitePlayerTextField;
    private final JTextField BlackPlayerTextField;
    private final JFrame menu;
    
    private final ActionListener playEvent = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (!(WhitePlayerTextField.getText().isEmpty()) && !(BlackPlayerTextField.getText().isEmpty())) {
                String WhitePlayerName = WhitePlayerTextField.getText();
                String BlackPlayerName = BlackPlayerTextField.getText();
                playFrame.dispose();
                new GameWindow(WhitePlayerName, BlackPlayerName, menu);
            }
        }
    };

    public PlayersInfoWindow(JFrame menu) {
        this.menu = menu;
        menu.setVisible(false);
        playFrame = new JFrame(" Players Names ");
        playFrame.setResizable(false);
        playFrame.setUndecorated(true);
        playFrame.setLocation(450, 140);

        JPanel mainPanel = new JPanel(new BorderLayout(0, 0));

        JPanel panelOfPlayers = new JPanel(new BorderLayout(0, 0));
        panelOfPlayers.setLayout(new GridLayout(2, 1, 5, 5));

        JPanel panelOfButtons = new JPanel(new BorderLayout(0, 0));
        panelOfButtons.setBorder(new EmptyBorder(60, 200, 60, 200));
        panelOfButtons.setLayout(new GridLayout(2, 1, 5, 5));

        JPanel whitePlayerPanel = new JPanel(new BorderLayout(0, 0));
        whitePlayerPanel.setBorder(new EmptyBorder(40, 20, 20, 20));
        whitePlayerPanel.setLayout(new GridLayout(1, 2, 5, 5));
        panelOfPlayers.add(whitePlayerPanel);

        JPanel blackPlayerPanel = new JPanel(new BorderLayout(0, 0));
        blackPlayerPanel.setBorder(new EmptyBorder(40, 20, 20, 20));
        blackPlayerPanel.setLayout(new GridLayout(1, 2, 5, 5));
        panelOfPlayers.add(blackPlayerPanel);

        JLabel blackPlayerLabel = new JLabel(" Black Player Name ", SwingConstants.CENTER);
        blackPlayerLabel.setFont(blackPlayerLabel.getFont().deriveFont(8, 21.0f));
        blackPlayerLabel.setForeground(new Color(48, 56, 65));
        blackPlayerPanel.add(blackPlayerLabel);
        
        JLabel whitePlayerLabel = new JLabel(" White Player Name ", SwingConstants.CENTER);
        whitePlayerLabel.setFont(whitePlayerLabel.getFont().deriveFont(8, 21.0f));
        whitePlayerLabel.setForeground(new Color(48, 56, 65));
        whitePlayerPanel.add(whitePlayerLabel);

        WhitePlayerTextField = new JTextField(5);
        whitePlayerPanel.add(WhitePlayerTextField);

        BlackPlayerTextField = new JTextField(5);
        blackPlayerPanel.add(BlackPlayerTextField);

        JButton backbutton = new JButton(" Play ");
        backbutton.setFont(backbutton.getFont().deriveFont(7, 20.0f));
        backbutton.setBackground(new Color(48, 56, 65));
        backbutton.setForeground(new Color(238, 238, 238));
        backbutton.addActionListener(playEvent);
        panelOfButtons.add(backbutton);

        JButton playbutton = new JButton(" Back ");
        playbutton.setFont(playbutton.getFont().deriveFont(7, 20.0f));
        playbutton.setBackground(new Color(48, 56, 65));
        playbutton.setForeground(new Color(238, 238, 238));
        playbutton.addActionListener((ActionEvent e) -> {
            playFrame.dispose();
            menu.setVisible(true);
        });
        panelOfButtons.add(playbutton);

        mainPanel.add(panelOfPlayers, BorderLayout.CENTER);
        mainPanel.add(panelOfButtons, BorderLayout.SOUTH);
        playFrame.getContentPane().add(mainPanel);
        playFrame.pack();
        playFrame.setVisible(true);
    }
}
