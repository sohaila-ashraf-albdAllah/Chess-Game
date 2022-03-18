package chess.guiBoard;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class MenuWindow {

    public final JFrame menuFrame;

    public MenuWindow() {
        Color Backgroundcolor = new Color(238, 238, 238); //Color of background of the frame
        
        menuFrame = new JFrame("Menu");
        menuFrame.setResizable(false);
        menuFrame.setLocation(400, 140);
        menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Panel that hold the whole contents
        JPanel mainPanel = new JPanel(new BorderLayout(0, 0));
        mainPanel.setBackground(Backgroundcolor);

        // Label that contains the name of our game 
        JLabel gameNameLabel = new JLabel(" Chess Game ", SwingConstants.CENTER);
        gameNameLabel.setFont(gameNameLabel.getFont().deriveFont(64.0f));
        gameNameLabel.setForeground(new Color(48, 56, 65)); // Color of the name of the game

        // Panel that contains the label of game name
        JPanel gameNamePanel = new JPanel(new GridLayout(1, 1, 0, 0));
        gameNamePanel.setBackground(Backgroundcolor);
        gameNamePanel.add(gameNameLabel);

        // Panel that contains the buttons; Play, About us, Exit.
        JPanel buttonsPanel = new JPanel(new GridLayout(3, 1, 5, 5));
        buttonsPanel.setBorder(new EmptyBorder(50, 200, 50, 200));
        buttonsPanel.setBackground(Backgroundcolor);

        // Initialize the buttons; Play, About us, Exit; and give its functionalities 
        JButton[] optionsButtons = new JButton[3];
        for (int i = 0; i < optionsButtons.length; i++) {
            optionsButtons[i] = new JButton();
            optionsButtons[i].setFont(optionsButtons[i].getFont().deriveFont(7, 27.0f));
            optionsButtons[i].setForeground(new Color(238, 238, 238)); // Color of the text
            optionsButtons[i].setBackground(new Color(48, 56, 65)); // Color of the buttons
            buttonsPanel.add(optionsButtons[i]);
        }
        
        optionsButtons[0].setText(" Play ");
        optionsButtons[0].addActionListener((ActionEvent e) -> {
            new PlayersInfoWindow(menuFrame);
        });
        
        optionsButtons[1].setText(" About Us ");
        optionsButtons[1].addActionListener((ActionEvent e) -> {
            new AboutUsWindow(menuFrame);
        });
        
        optionsButtons[2].setText(" Exit ");
        optionsButtons[2].addActionListener((ActionEvent e) -> {
            menuFrame.dispose();
            System.exit(0);
        });

        mainPanel.add(buttonsPanel, BorderLayout.CENTER);
        mainPanel.add(gameNamePanel, BorderLayout.PAGE_START);
        menuFrame.add(mainPanel);
        menuFrame.pack();

        menuFrame.setVisible(true);
    }
}
