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
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(3, 1));

        JLabel titleLabel = new JLabel("Selamat Datang di Aplikasi Penyewaan Kendaraan!", SwingConstants.CENTER);
        JButton adminButton = new JButton("Login sebagai Penyewa");
        JButton memberButton = new JButton("Login sebagai Pesewa");

        frame.add(titleLabel);
        frame.add(adminButton);
        frame.add(memberButton);

        adminButton.addActionListener(e -> showPenyewaLogin());
        memberButton.addActionListener(e -> showPesewaLogin());

        frame.setVisible(true);
    }

    private void showPenyewaLogin() {
        JFrame adminFrame = new JFrame("Login - Penyewa");
        adminFrame.setSize(400, 400);
        adminFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        adminFrame.setLayout(new GridLayout(4, 1));

        JLabel Jusername = new JLabel("Username");
        JTextField usernameField = new JTextField();
        JLabel Jpassword = new JLabel("Password");
        JPasswordField passwordField = new JPasswordField();
        JButton loginButton = new JButton("Login");

        adminFrame.add(Jusername);
        adminFrame.add(usernameField);
        adminFrame.add(Jpassword);
        adminFrame.add(passwordField);
        adminFrame.add(loginButton);

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

        adminFrame.setVisible(true);
    }

    private void showPesewaLogin() {
        JFrame memberFrame = new JFrame("Login - Member");
        memberFrame.setSize(400, 400);
        memberFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        memberFrame.setLayout(new GridLayout(4, 1));

        JLabel Jusername = new JLabel("Username");
        JTextField usernameField = new JTextField();
        JLabel Jpassword = new JLabel("Password");
        JPasswordField passwordField = new JPasswordField();
        JButton loginButton = new JButton("Login");
        JButton registerButton = new JButton("Buat Akun");

        memberFrame.add(Jusername);
        memberFrame.add(usernameField);
        memberFrame.add(Jpassword);
        memberFrame.add(passwordField);
        memberFrame.add(loginButton);
        memberFrame.add(registerButton);

        loginButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            if (users.containsKey(username) && users.get(username).equals(password)) {
                JOptionPane.showMessageDialog(memberFrame, "Login berhasil sebagai Member!", "Info", JOptionPane.INFORMATION_MESSAGE);
                new PanelPesewa();
                memberFrame.dispose();
            } else {
                JOptionPane.showMessageDialog(memberFrame, "Username atau Password Member salah!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        registerButton.addActionListener(e -> new Registrasi());

        memberFrame.setVisible(true);
    }

    public static HashMap<String, String> getUsers() {
        return users;
    }
}
