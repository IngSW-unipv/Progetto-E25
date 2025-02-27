package it.unipv.ingsw.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LogOutView {
    private JButton confirmButton;
    private JFrame frame;

    public LogOutView() {
        frame = new JFrame("Conferma Uscita");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 150);  
        frame.setLocationRelativeTo(null);  
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        JLabel label = new JLabel("Sei sicuro di voler uscire?", JLabel.CENTER);
        panel.add(label, BorderLayout.CENTER);
        confirmButton = new JButton("Conferma");
        panel.add(confirmButton, BorderLayout.SOUTH);
        frame.add(panel);
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                JFrame loggedOutFrame = new JFrame("Disconnessione");
                loggedOutFrame.setSize(300, 150);
                loggedOutFrame.setLocationRelativeTo(null); 

                JPanel loggedOutPanel = new JPanel();
                JLabel loggedOutLabel = new JLabel("Sei stato disconnesso.", JLabel.CENTER);
                loggedOutPanel.add(loggedOutLabel);

                loggedOutFrame.add(loggedOutPanel);
                loggedOutFrame.setVisible(true);
            }
        });

        frame.setVisible(true);
    }

    public JButton getConfirmButton() {
        return confirmButton;
    }

    public static void main(String[] args) {
        new LogOutView();
    }

}
