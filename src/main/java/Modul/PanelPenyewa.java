package Modul;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PanelPenyewa {
    final JFrame frame;
    private final List<Kendaraan> rentalHistory = new ArrayList<>(); // Menyimpan kendaraan yang disewa

    private void refreshTable(List<Kendaraan> katalog, JPanel mainPanel) {
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

        // Area untuk menampilkan gambar kendaraan
        JLabel imageLabel = new JLabel("Pilih kendaraan untuk melihat gambar.", SwingConstants.CENTER);
        imageLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        imageLabel.setForeground(Color.WHITE);

        table.getSelectionModel().addListSelectionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow >= 0 && selectedRow < katalog.size()) {
                String fotoPath = katalog.get(selectedRow).fotoPath;
                if (fotoPath != null && !fotoPath.isEmpty()) {
                    ImageIcon icon = new ImageIcon(fotoPath);
                    Image img = icon.getImage().getScaledInstance(300, 200, Image.SCALE_SMOOTH);
                    imageLabel.setIcon(new ImageIcon(img));
                    imageLabel.setText(""); // Hapus teks saat gambar ditampilkan
                } else {
                    imageLabel.setIcon(null);
                    imageLabel.setText("Tidak ada gambar untuk kendaraan ini.");
                }
            }
        });

        mainPanel.removeAll();

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);
        gbc.fill = GridBagConstraints.BOTH;

        // Tambahkan tabel ke panel
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.weightx = 1;
        gbc.weighty = 0.7;
        mainPanel.add(tableScrollPane, gbc);

        // Tambahkan label gambar ke panel
        gbc.gridy = 1;
        gbc.weighty = 0.3;
        mainPanel.add(imageLabel, gbc);

        // Tambahkan tombol Sewa
        JButton rentButton = new JButton("Sewa Kendaraan");
        rentButton.setFont(new Font("Arial", Font.BOLD, 16));
        rentButton.setBackground(Color.decode("#009688")); // Warna hijau
        rentButton.setForeground(Color.WHITE);
        rentButton.addActionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow >= 0 && selectedRow < katalog.size()) {
                Kendaraan selectedKendaraan = katalog.get(selectedRow);
                if (selectedKendaraan.isRented) {
                    JOptionPane.showMessageDialog(frame, "Kendaraan ini sudah disewa!", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    rentalHistory.add(selectedKendaraan);
                    selectedKendaraan.isRented = true;
                    JOptionPane.showMessageDialog(frame, "Kendaraan berhasil disewa!", "Info", JOptionPane.INFORMATION_MESSAGE);
                    refreshTable(katalog, mainPanel);
                }
            } else {
                JOptionPane.showMessageDialog(frame, "Pilih kendaraan terlebih dahulu!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        gbc.gridy = 2;
        gbc.weighty = 0;
        gbc.gridwidth = 1;
        mainPanel.add(rentButton, gbc);

        // Tombol untuk melihat dan menghapus history penyewaan
        JButton historyButton = new JButton("History Penyewaan");
        historyButton.setFont(new Font("Arial", Font.BOLD, 16));
        historyButton.setBackground(Color.decode("#FF5722")); // Warna oranye
        historyButton.setForeground(Color.WHITE);
        historyButton.addActionListener(e -> {
            if (rentalHistory.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Belum ada kendaraan yang disewa.", "Info", JOptionPane.INFORMATION_MESSAGE);
            } else {
                StringBuilder history = new StringBuilder("History Penyewaan:\n");
                for (Kendaraan rented : rentalHistory) {
                    history.append("- ").append(rented.nama).append("\n");
                }
                int choice = JOptionPane.showConfirmDialog(frame, history.toString() + "\nApakah Anda ingin membatalkan penyewaan?", "Penyewaan saat ini.", JOptionPane.YES_NO_OPTION);
                if (choice == JOptionPane.YES_OPTION) {
                    // Set semua kendaraan di history menjadi tersedia kembali
                    for (Kendaraan rented : rentalHistory) {
                        rented.isRented = false;
                    }
                    rentalHistory.clear();
                    JOptionPane.showMessageDialog(frame, "Penyewaan di batalkan!", "Info", JOptionPane.INFORMATION_MESSAGE);
                    refreshTable(katalog, mainPanel);
                }
            }
        });

        gbc.gridx = 1;
        mainPanel.add(historyButton, gbc);

        mainPanel.revalidate();
        mainPanel.repaint();
    }

    public PanelPenyewa(List<Kendaraan> katalog) {
        frame = new JFrame("Panel Penyewa");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new GridBagLayout());
        frame.getContentPane().setBackground(Color.decode("#212121")); // Warna latar belakang gelap

        // Panel utama
        JPanel mainPanel = new JPanel(new GridBagLayout());
        mainPanel.setBackground(Color.decode("#212121")); // Warna hitam gelap
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

        refreshTable(katalog, mainPanel);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
