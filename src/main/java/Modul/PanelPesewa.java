package Modul;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class PanelPesewa {
    private final JFrame frame;
    private final JPanel mainPanel;
    private final List<Kendaraan> katalog; // Daftar kendaraan

    public PanelPesewa() {
        katalog = TampilanLoginPesewa.getKatalog(); // Ambil data katalog
        frame = new JFrame("Panel Pesewa");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new GridBagLayout());
        frame.getContentPane().setBackground(Color.decode("#212121")); // Warna latar belakang gelap

        // Panel utama
        mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(Color.decode("#212121"));
        mainPanel.setPreferredSize(new Dimension(800, 600));

        // Judul
        JLabel titleLabel = new JLabel("Katalog Kendaraan");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        titleLabel.setForeground(Color.WHITE);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(20, 0, 20, 0);
        gbc.anchor = GridBagConstraints.CENTER;
        frame.add(titleLabel, gbc);

        // Tambahkan panel utama ke frame
        gbc.gridy = 1;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        frame.add(mainPanel, gbc);

        // Tombol kembali
        JButton backButton = new JButton("Keluar");
        backButton.setFont(new Font("Arial", Font.BOLD, 16));
        backButton.setBackground(Color.decode("#F44336")); // Warna merah
        backButton.setForeground(Color.WHITE);
        backButton.addActionListener(e -> frame.dispose());

        gbc.gridy = 2;
        gbc.weighty = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        frame.add(backButton, gbc);

        // Refresh tabel dengan data katalog
        refreshTable();

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void refreshTable() {
        String[] columnNames = {"Jenis", "Nama", "Lokasi", "Harga", "Metode", "Status"};
        Object[][] data = new Object[katalog.size()][6];

        for (int i = 0; i < katalog.size(); i++) {
            Kendaraan kendaraan = katalog.get(i);
            data[i][0] = kendaraan.jenis;
            data[i][1] = kendaraan.nama;
            data[i][2] = kendaraan.lokasi;
            data[i][3] = "Rp" + kendaraan.harga;
            data[i][4] = kendaraan.metode;
            data[i][5] = kendaraan.isRented ? "Disewa" : "Tersedia";
        }

        JTable table = new JTable(data, columnNames);
        table.setFont(new Font("Arial", Font.PLAIN, 14));
        table.setRowHeight(25);
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 16));
        JScrollPane tableScrollPane = new JScrollPane(table);

        mainPanel.removeAll();

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);
        gbc.fill = GridBagConstraints.BOTH;

        // Tambahkan tabel ke panel
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 0.8;
        mainPanel.add(tableScrollPane, gbc);

        // Tambahkan tombol Tambah Kendaraan
        JButton addButton = new JButton("Tambah Kendaraan");
        addButton.setFont(new Font("Arial", Font.BOLD, 16));
        addButton.setBackground(Color.decode("#009688")); // Warna hijau
        addButton.setForeground(Color.WHITE);
        addButton.addActionListener(e -> new TambahKendaraanDialog(katalog, this::refreshTable));

        gbc.gridy = 1;
        gbc.weighty = 0.2;
        mainPanel.add(addButton, gbc);

        mainPanel.revalidate();
        mainPanel.repaint();
    }
}
