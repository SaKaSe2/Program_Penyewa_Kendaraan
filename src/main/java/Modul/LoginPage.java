package Modul;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;

public class LoginPage {
    private final JFrame frame;
    private static final String ADMIN_USERNAME = "admin";
    private static final String ADMIN_PASSWORD = "admin123";
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
        adminButton.addActionListener(e -> showPenyewaLogin());
        memberButton.addActionListener(e -> showPesewaLogin());

        frame.pack();
        frame.setLocationRelativeTo(null); // Pusatkan frame di layar
        frame.setVisible(true);
    }

    private void showPenyewaLogin() {
        JFrame adminFrame = new JFrame("Login - Penyewa");
        adminFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        adminFrame.setLayout(new GridBagLayout());
        adminFrame.getContentPane().setBackground(Color.decode("#FFC107"));

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.decode("#212121"));
        panel.setPreferredSize(new Dimension(400, 400));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel titleLabel = new JLabel("Login Penyewa");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        titleLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(titleLabel, gbc);

        JLabel usernameLabel = new JLabel("Username");
        usernameLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        usernameLabel.setForeground(Color.WHITE);
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(usernameLabel, gbc);

        JTextField usernameField = new JTextField(20);
        usernameField.setFont(new Font("Arial", Font.PLAIN, 16));
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(usernameField, gbc);

        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        passwordLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(passwordLabel, gbc);

        JPasswordField passwordField = new JPasswordField(20);
        passwordField.setFont(new Font("Arial", Font.PLAIN, 16));
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(passwordField, gbc);

        JButton loginButton = new JButton("Login");
        loginButton.setFont(new Font("Arial", Font.BOLD, 16));
        loginButton.setBackground(Color.decode("#009688"));
        loginButton.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(loginButton, gbc);

        adminFrame.add(panel);

        loginButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            if (ADMIN_USERNAME.equals(username) && ADMIN_PASSWORD.equals(password)) {
                JOptionPane.showMessageDialog(adminFrame, "Login berhasil sebagai Penyewa!", "Info", JOptionPane.INFORMATION_MESSAGE);
                new PanelPenyewa();
                adminFrame.dispose();
            } else {
                JOptionPane.showMessageDialog(adminFrame, "Username atau Password Penyewa salah!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        adminFrame.pack();
        adminFrame.setLocationRelativeTo(null);
        adminFrame.setVisible(true);
    }

    private void showPesewaLogin() {
        JFrame memberFrame = new JFrame("Login - Pesewa");
        memberFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        memberFrame.setLayout(new GridBagLayout());
        memberFrame.getContentPane().setBackground(Color.decode("#FFC107"));

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.decode("#212121"));
        panel.setPreferredSize(new Dimension(400, 400));

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel titleLabel = new JLabel("Login Pesewa");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        titleLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(titleLabel, gbc);

        JLabel usernameLabel = new JLabel("Username");
        usernameLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        usernameLabel.setForeground(Color.WHITE);
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(usernameLabel, gbc);

        JTextField usernameField = new JTextField(20);
        usernameField.setFont(new Font("Arial", Font.PLAIN, 16));
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(usernameField, gbc);

        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        passwordLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.WEST;
        panel.add(passwordLabel, gbc);

        JPasswordField passwordField = new JPasswordField(20);
        passwordField.setFont(new Font("Arial", Font.PLAIN, 16));
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.EAST;
        panel.add(passwordField, gbc);

        JButton loginButton = new JButton("Login");
        loginButton.setFont(new Font("Arial", Font.BOLD, 16));
        loginButton.setBackground(Color.decode("#009688"));
        loginButton.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.CENTER;
        panel.add(loginButton, gbc);

        JButton registerButton = new JButton("Buat Akun");
        registerButton.setFont(new Font("Arial", Font.BOLD, 16));
        registerButton.setBackground(Color.decode("#F44336"));
        registerButton.setForeground(Color.WHITE);
        gbc.gridx = 1;
        panel.add(registerButton, gbc);

        memberFrame.add(panel);

        loginButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            if (users.containsKey(username) && users.get(username).equals(password)) {
                JOptionPane.showMessageDialog(memberFrame, "Login berhasil sebagai Pesewa!", "Info", JOptionPane.INFORMATION_MESSAGE);
                new PanelPesewa();
                memberFrame.dispose();
            } else {
                JOptionPane.showMessageDialog(memberFrame, "Username atau Password Pesewa salah!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        registerButton.addActionListener(e -> new Registrasi());

        memberFrame.pack();
        memberFrame.setLocationRelativeTo(null);
        memberFrame.setVisible(true);
    }

    public static HashMap<String, String> getUsers() {
        return users;
    }
}
