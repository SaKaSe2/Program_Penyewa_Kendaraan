package Modul;

import javax.swing.*;
import java.awt.*;

public class PanelPenyewa {
    private final JFrame frame;

    public PanelPenyewa() {
        frame = new JFrame("Panel Penyewa");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JLabel infoLabel = new JLabel("Fitur Panel Penyewa akan dikembangkan.", SwingConstants.CENTER);
        frame.add(infoLabel, BorderLayout.CENTER);

        frame.setVisible(true);
    }
}
