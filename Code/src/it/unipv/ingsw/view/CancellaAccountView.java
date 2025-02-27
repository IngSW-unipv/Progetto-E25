package it.unipv.ingsw.view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CancellaAccountView {
    private JButton confirmButton;
    private JFrame frame;

    public CancellaAccountView() {
        frame = new JFrame("Conferma cancellazione account");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 150);  
        frame.setLocationRelativeTo(null);  
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        JLabel label = new JLabel("Sei sicuro di voler cancellare questo account?", JLabel.CENTER);
        panel.add(label, BorderLayout.CENTER);
        confirmButton = new JButton("Conferma");
        panel.add(confirmButton, BorderLayout.SOUTH);
        frame.add(panel);
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                JFrame cancellaAccountFrame = new JFrame("Cancellazione");
                cancellaAccountFrame.setSize(300, 150);
                cancellaAccountFrame.setLocationRelativeTo(null); 

                JPanel cancellaAccountPanel = new JPanel();
                JLabel cancellaAccountLabel = new JLabel("Account cancellato.", JLabel.CENTER);
                cancellaAccountPanel.add(cancellaAccountLabel);

                cancellaAccountFrame.add(cancellaAccountPanel);
                cancellaAccountFrame.setVisible(true);
            }
        });

        frame.setVisible(true);
    }

    public JButton getConfirmButton() {
        return confirmButton;
    }

    public static void main(String[] args) {
        new CancellaAccountView();
    }

}
