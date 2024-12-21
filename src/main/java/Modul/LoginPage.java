package Modul;

import javax.swing.*;
import java.awt.*;

public class LoginPage {
    private final JFrame frame;

    public LoginPage() {
        frame = new JFrame("Login - Aplikasi Penyewaan Kendaraan");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new GridLayout(4, 1));

        JLabel titleLabel = new JLabel("Selamat Datang!", SwingConstants.CENTER);
        JButton pesewaLoginButton = new JButton("Login sebagai Pesewa");
        JButton penyewaLoginButton = new JButton("Login sebagai Penyewa");

        frame.add(titleLabel);
        frame.add(pesewaLoginButton);
        frame.add(penyewaLoginButton);

        pesewaLoginButton.addActionListener(e -> {
            new PanelPesewa();
            frame.dispose();
        });

        penyewaLoginButton.addActionListener(e -> {
            new PanelPenyewa();
            frame.dispose();
        });

        frame.setVisible(true);
    }
}
