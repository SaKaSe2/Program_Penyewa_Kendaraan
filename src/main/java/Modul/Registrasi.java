package Modul;

import javax.swing.*;
import java.awt.*;

class Registrasi {
    private final JFrame frame;

    public Registrasi() {
        frame = new JFrame("Registrasi - Aplikasi Penyewaan Kendaraan");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new GridBagLayout());

        // Set warna latar belakang frame
        frame.getContentPane().setBackground(Color.decode("#FFC107")); // Warna kuning

        // Panel utama dengan warna latar belakang gelap
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.decode("#212121")); // Warna hitam gelap
        panel.setPreferredSize(new Dimension(400, 500)); // Ukuran panel

        // Gunakan GridBagConstraints untuk mengatur posisi komponen
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15); // Margin di sekitar komponen
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Judul
        JLabel titleLabel = new JLabel("Registrasi");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        titleLabel.setForeground(Color.WHITE); // Warna teks putih
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(titleLabel, gbc);

        // Subjudul
        JLabel subtitleLabel = new JLabel("Silahkan Daftar dengan Username Anda!");
        subtitleLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        subtitleLabel.setForeground(Color.WHITE);
        gbc.gridy = 1;
        panel.add(subtitleLabel, gbc);

        // Label Username
        JLabel usernameLabel = new JLabel("Username");
        usernameLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        usernameLabel.setForeground(Color.WHITE);
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(usernameLabel, gbc);

        // Field Username
        JTextField usernameField = new JTextField(20);
        usernameField.setFont(new Font("Arial", Font.PLAIN, 16));
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(usernameField, gbc);

        // Label Password
        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        passwordLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(passwordLabel, gbc);

        // Field Password
        JPasswordField passwordField = new JPasswordField(20);
        passwordField.setFont(new Font("Arial", Font.PLAIN, 16));
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(passwordField, gbc);

        // Tombol Register
        JButton registerButton = new JButton("Register");
        registerButton.setFont(new Font("Arial", Font.BOLD, 16));
        registerButton.setBackground(Color.decode("#009688")); // Warna hijau
        registerButton.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(registerButton, gbc);

        // Label untuk login
        JLabel loginLabel = new JLabel("Sudah Punya Akun?");
        loginLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        loginLabel.setForeground(Color.WHITE);
        gbc.gridy = 5;
        gbc.gridwidth = 1;
        panel.add(loginLabel, gbc);

        // Tombol Login
        JButton loginButton = new JButton("Login");
        loginButton.setFont(new Font("Arial", Font.BOLD, 16));
        loginButton.setBackground(Color.decode("#F44336")); // Warna merah
        loginButton.setForeground(Color.WHITE);
        gbc.gridx = 1;
        panel.add(loginButton, gbc);

        // Tambahkan panel ke frame
        frame.add(panel);

        // Tambahkan aksi untuk tombol Register
        registerButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            if (username.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(frame, "Username dan Password tidak boleh kosong!", "Error", JOptionPane.ERROR_MESSAGE);
            } else if (LoginPage.getUsers().containsKey(username)) {
                JOptionPane.showMessageDialog(frame, "Username sudah terdaftar!", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                LoginPage.getUsers().put(username, password); // Simpan data ke HashMap
                JOptionPane.showMessageDialog(frame, "Akun berhasil dibuat!", "Info", JOptionPane.INFORMATION_MESSAGE);
                frame.dispose();
            }
        });

        // Tambahkan aksi untuk tombol Login
        loginButton.addActionListener(e -> {
            frame.dispose(); // Menutup jendela registrasi
            new LoginPage(); // Buka halaman login
        });

        frame.pack();
        frame.setLocationRelativeTo(null); // Pusatkan frame di layar
        frame.setVisible(true);
    }
}
