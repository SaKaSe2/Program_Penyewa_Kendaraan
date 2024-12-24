package Modul;

import javax.swing.*;
import java.awt.*;

import static Modul.TampilanLoginPesewa.katalog;

public class TampilanLoginPenyewa {
    public void showPenyewaLogin() {
        JFrame memberFrame = new JFrame("Login - Penyewa");
        memberFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        memberFrame.setLayout(new GridBagLayout());
        memberFrame.getContentPane().setBackground(Color.decode("#FFC107"));

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

            if (LoginPage.getUsers().containsKey(username) && LoginPage.getUsers().get(username).equals(password)) {
                JOptionPane.showMessageDialog(memberFrame, "Login berhasil sebagai Penyewa!", "Info", JOptionPane.INFORMATION_MESSAGE);
                new PanelPenyewa(katalog);
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
}
