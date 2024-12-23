package Modul;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

class PanelPesewa {
    JFrame frame;
    List<Kendaraan> katalog;

    public PanelPesewa() {
        katalog = new ArrayList<>();

        frame = new JFrame("Panel Pesewa");
        frame.setSize(800, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Panel Utama
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        // latar hitam
        frame.add(mainPanel, BorderLayout.CENTER);

        // Header
        JLabel titleLabel = new JLabel("Selamat Datang", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setBackground(Color.CYAN);
        titleLabel.setOpaque(true);
        titleLabel.setPreferredSize(new Dimension(800, 50));
        mainPanel.add(titleLabel, BorderLayout.NORTH);

        // Panel Tombol
        JPanel buttonPanel = new JPanel(new GridLayout(1, 4, 10, 10));
        buttonPanel.setBackground(Color.LIGHT_GRAY);

        // Tombol dengan Ikon
        JButton addButton = new JButton("Tambah Kendaraan");
        addButton.setIcon(new ImageIcon("icons/add.png"));
        addButton.setBackground(new Color(0, 150, 136));
        addButton.setForeground(Color.WHITE);

        JButton viewHistoryButton = new JButton("Lihat History");
        viewHistoryButton.setIcon(new ImageIcon("icons/history.png"));
        viewHistoryButton.setBackground(new Color(33, 150, 243));
        viewHistoryButton.setForeground(Color.WHITE);

        JButton editButton = new JButton("Edit Kendaraan");
        editButton.setIcon(new ImageIcon("icons/edit.png"));
        editButton.setBackground(new Color(255, 165, 0));
        editButton.setForeground(Color.WHITE);

        JButton deleteButton = new JButton("Hapus Kendaraan");
        deleteButton.setIcon(new ImageIcon("icons/delete.png"));
        deleteButton.setBackground(new Color(255, 82, 82));
        deleteButton.setForeground(Color.WHITE);

        buttonPanel.add(addButton);
        buttonPanel.add(viewHistoryButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);

        mainPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Fungsi Tombol
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TambahKendaraanDialog(katalog);
            }
        });

        viewHistoryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (katalog.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Belum ada kendaraan di katalog.", "Info", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    StringBuilder history = new StringBuilder();
                    for (Kendaraan kendaraan : katalog) {
                        history.append(kendaraan).append("\n");
                    }
                    JOptionPane.showMessageDialog(frame, history.toString(), "History Kendaraan", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (katalog.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Tidak ada kendaraan untuk diedit.", "Info", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }

                String[] kendaraanList = katalog.stream().map(Kendaraan::toString).toArray(String[]::new);
                String selected = (String) JOptionPane.showInputDialog(frame, "Pilih kendaraan yang ingin diedit:",
                        "Edit Kendaraan", JOptionPane.QUESTION_MESSAGE, null, kendaraanList, kendaraanList[0]);

                if (selected != null) {
                    int index = -1;
                    for (int i = 0; i < katalog.size(); i++) {
                        if (katalog.get(i).toString().equals(selected)) {
                            index = i;
                            break;
                        }
                    }

                    if (index != -1) {
                        Kendaraan kendaraan = katalog.get(index);
                        String newLokasi = JOptionPane.showInputDialog(frame, "Masukkan lokasi baru:", kendaraan.lokasi);
                        String newHarga = JOptionPane.showInputDialog(frame, "Masukkan harga baru:", kendaraan.harga);
                        String newMetode = JOptionPane.showInputDialog(frame, "Masukkan metode pembayaran baru:", kendaraan.metode);

                        try {
                            kendaraan.lokasi = newLokasi;
                            kendaraan.harga = Integer.parseInt(newHarga);
                            kendaraan.metode = newMetode;
                            JOptionPane.showMessageDialog(frame, "Kendaraan berhasil diedit!", "Info", JOptionPane.INFORMATION_MESSAGE);
                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(frame, "Input tidak valid!", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (katalog.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Tidak ada kendaraan untuk dihapus.", "Info", JOptionPane.INFORMATION_MESSAGE);
                    return;
                }

                String[] kendaraanList = katalog.stream().map(Kendaraan::toString).toArray(String[]::new);
                String selected = (String) JOptionPane.showInputDialog(frame, "Pilih kendaraan yang ingin dihapus:",
                        "Hapus Kendaraan", JOptionPane.QUESTION_MESSAGE, null, kendaraanList, kendaraanList[0]);

                if (selected != null) {
                    katalog.removeIf(kendaraan -> kendaraan.toString().equals(selected));
                    JOptionPane.showMessageDialog(frame, "Kendaraan berhasil dihapus!", "Info", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });

        frame.setVisible(true);
    }
}

