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

public class AboutUsWindow {

    private final JFrame aboutUsFrame;
    private final JFrame menu;

    public AboutUsWindow(JFrame menu) {
        this.menu = menu;
        menu.setVisible(false);

        aboutUsFrame = new JFrame(" About Us ");
        aboutUsFrame.setResizable(false);
        aboutUsFrame.setUndecorated(true);
        aboutUsFrame.setLocation(320, 150);
        aboutUsFrame.setBackground(new Color(238, 238, 238));

        JPanel mainPanel = new JPanel(new BorderLayout(0, 0));
        mainPanel.setSize(490, 490);

        JPanel infoPanel = new JPanel(new BorderLayout(0, 0));
        infoPanel.setBorder(new EmptyBorder(40, 0, 20, 20));
        infoPanel.setLayout(new GridLayout(6, 1, 5, 5));

        JPanel[] programmerInfoPanels = new JPanel[6];
        for (int i = 0; i < programmerInfoPanels.length; i++) {
            programmerInfoPanels[i] = new JPanel();
            programmerInfoPanels[i].setLayout(new GridLayout(1, 2, 5, 5));
            programmerInfoPanels[i].setBorder(new EmptyBorder(5, 0, 5, 0));
            infoPanel.add(programmerInfoPanels[i], BorderLayout.CENTER);
        }

        JPanel backButtonPanel = new JPanel(new BorderLayout(0, 0));
        backButtonPanel.setBorder(new EmptyBorder(50, 320, 50, 320));

        JButton button = new JButton(" Back ");
        button.setFont(button.getFont().deriveFont(7, 20.0f));
        button.setBackground(new Color(48, 56, 65));//Colorof button
        button.setForeground(Color.WHITE);
        button.addActionListener((ActionEvent e) -> {
            aboutUsFrame.dispose();
            menu.setVisible(true);
        });
        backButtonPanel.add(button);

        JLabel[] names = new JLabel[6];
        for (int i = 0; i < names.length; i++) {
            names[i] = new JLabel(" ", SwingConstants.CENTER);
            names[i].setFont(names[i].getFont().deriveFont(8, 21.0f));
            programmerInfoPanels[i].add(names[i]);
        }
        // Set Names Of Team ..
        names[0].setText(" Mostafa Labib ");
        names[1].setText(" Mostafa Moharram ");
        names[2].setText(" Aisha Soliman ");
        names[3].setText(" Mostafa Ashraf ");
        names[4].setText(" Mostafa Ayman ");
        names[5].setText(" Sohaila Ashraf ");

        JLabel[] emails = new JLabel[6];
        for (int i = 0; i < emails.length; i++) {
            emails[i] = new JLabel(" ", SwingConstants.CENTER);
            emails[i].setFont(emails[i].getFont().deriveFont(8, 19.0f));
            programmerInfoPanels[i].add(emails[i]);
        }
        emails[0].setText(" Mostafa20191700646@cis.asu.edu.eg ");
        emails[1].setText(" Mostafa20191700641@cis.asu.edu.eg ");
        emails[2].setText(" Aisha20191700330@cis.asu.edu.eg ");
        emails[3].setText(" Mostafa20191700639@cis.asu.edu.eg ");
        emails[4].setText(" Mostafa20191700640@cis.asu.edu.eg ");
        emails[5].setText(" Sohaila20191700304@cis.asu.edu.eg ");

        for (JPanel programmerInfoPanel : programmerInfoPanels) {
            infoPanel.add(programmerInfoPanel);
        }

        mainPanel.add(infoPanel, BorderLayout.CENTER);
        mainPanel.add(backButtonPanel, BorderLayout.SOUTH);
        aboutUsFrame.add(mainPanel);
        aboutUsFrame.pack();
        aboutUsFrame.setVisible(true);
    }
}
