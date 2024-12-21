package Modul;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PanelPesewa {
    private final JFrame frame;
    private final List<Kendaraan> katalog;

    public PanelPesewa() {
        katalog = new ArrayList<>();
        frame = new JFrame("Panel Pesewa");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JButton addButton = new JButton("Tambah Kendaraan");
        JButton viewHistoryButton = new JButton("Lihat History");
        JButton editButton = new JButton("Edit Kendaraan");
        JButton deleteButton = new JButton("Hapus Kendaraan");

        JPanel buttonPanel = new JPanel(new GridLayout(1, 4));
        buttonPanel.add(addButton);
        buttonPanel.add(viewHistoryButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);

        frame.add(buttonPanel, BorderLayout.SOUTH);

        addButton.addActionListener(e -> new TambahKendaraanDialog(katalog));
        viewHistoryButton.addActionListener(e -> JOptionPane.showMessageDialog(frame, "Fitur Lihat History akan dikembangkan.", "Info", JOptionPane.INFORMATION_MESSAGE));
        editButton.addActionListener(e -> JOptionPane.showMessageDialog(frame, "Fitur Edit Kendaraan akan dikembangkan.", "Info", JOptionPane.INFORMATION_MESSAGE));
        deleteButton.addActionListener(e -> JOptionPane.showMessageDialog(frame, "Fitur Hapus Kendaraan akan dikembangkan.", "Info", JOptionPane.INFORMATION_MESSAGE));

        frame.setVisible(true);
    }
}
