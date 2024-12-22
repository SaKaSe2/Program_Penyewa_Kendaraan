package Modul;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class LoginPage {
    private final JFrame frame;
    static final HashMap<String, String> users = new HashMap<>(); // Penyimpanan data akun pengguna biasa

    public LoginPage() {
        frame = new JFrame("Login - Aplikasi Penyewaan Kendaraan");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridBagLayout());

        // Set warna latar belakang frame
        frame.getContentPane().setBackground(Color.decode("#FFC107")); // Warna kuning

        // Panel utama
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.decode("#212121")); // Warna hitam gelap
        panel.setPreferredSize(new Dimension(500, 400));

        // Gunakan GridBagConstraints
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Margin di sekitar komponen
        gbc.fill = GridBagConstraints.NONE; // Tidak memaksa elemen mengisi ruang
        gbc.weightx = 1.0; // Memastikan elemen di tengah secara horizontal
        gbc.weighty = 1.0; // Memastikan elemen di tengah secara vertikal

        // Judul
        JLabel titleLabel = new JLabel("Login");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        titleLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER; // Pastikan di tengah
        panel.add(titleLabel, gbc);

        // Subjudul
        JLabel subtitleLabel = new JLabel("Pilih jenis login Anda");
        subtitleLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        subtitleLabel.setForeground(Color.WHITE);
        gbc.gridy = 1;
        panel.add(subtitleLabel, gbc);

        // Tombol Penyewa
        JButton adminButton = new JButton("Login sebagai Penyewa");
        adminButton.setFont(new Font("Arial", Font.BOLD, 14));
        adminButton.setBackground(Color.decode("#009688")); // Warna hijau
        adminButton.setForeground(Color.WHITE);
        gbc.gridy = 2;
        gbc.gridwidth = 1; // Tombol kembali menjadi 1 kolom
        panel.add(adminButton, gbc);

        // Tombol Pesewa
        JButton memberButton = new JButton("Login sebagai Pesewa");
        memberButton.setFont(new Font("Arial", Font.BOLD, 14));
        memberButton.setBackground(Color.decode("#F44336")); // Warna merah
        memberButton.setForeground(Color.WHITE);
        gbc.gridx = 1; // Kolom kedua
        panel.add(memberButton, gbc);

        // Tambahkan panel ke frame
        frame.add(panel);

        // Tambahkan aksi untuk tombol-tombol
        adminButton.addActionListener(e -> new TampilanLoginPenyewa().showPenyewaLogin());
        memberButton.addActionListener(e -> new TampilanLoginPesewa().showPesewaLogin());

        frame.pack();
        frame.setLocationRelativeTo(null); // Pusatkan frame di layar
        frame.setVisible(true);
    }

    public static HashMap<String, String> getUsers() {
        return users;
    }
}
