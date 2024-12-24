package Modul;

import javax.swing.*;
import java.awt.*;
import java.util.List;

class TambahKendaraanDialog {

    public TambahKendaraanDialog(List<Kendaraan> katalog, Runnable refreshCallback) {
        JDialog dialog = new JDialog();
        dialog.setTitle("Tambah Kendaraan");
        dialog.setSize(450, 600);
        dialog.setLayout(new GridBagLayout());
        dialog.getContentPane().setBackground(Color.decode("#212121")); // Warna gelap seperti PanelPesewa

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Judul
        JLabel titleLabel = new JLabel("Tambah Kendaraan");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setForeground(Color.WHITE);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        dialog.add(titleLabel, gbc);

        // Jenis Kendaraan
        gbc.gridwidth = 1;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.gridx = 0;
        gbc.gridy = 1;
        dialog.add(createLabel("Jenis Kendaraan:"), gbc);

        JPanel jenisPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        jenisPanel.setBackground(Color.decode("#212121"));
        ButtonGroup jenisGroup = new ButtonGroup();
        JRadioButton mobilButton = createRadioButton("Mobil");
        JRadioButton motorButton = createRadioButton("Motor");
        JRadioButton minibusButton = createRadioButton("Minibus");
        jenisGroup.add(mobilButton);
        jenisGroup.add(motorButton);
        jenisGroup.add(minibusButton);
        jenisPanel.add(mobilButton);
        jenisPanel.add(motorButton);
        jenisPanel.add(minibusButton);

        gbc.gridx = 1;
        dialog.add(jenisPanel, gbc);

        // Nama Kendaraan
        gbc.gridx = 0;
        gbc.gridy = 2;
        dialog.add(createLabel("Nama Kendaraan:"), gbc);
        JTextField namaField = createTextField();
        gbc.gridx = 1;
        dialog.add(namaField, gbc);

        // Lokasi
        gbc.gridx = 0;
        gbc.gridy = 3;
        dialog.add(createLabel("Lokasi Kendaraan:"), gbc);
        JTextField lokasiField = createTextField();
        gbc.gridx = 1;
        dialog.add(lokasiField, gbc);

        // Harga
        gbc.gridx = 0;
        gbc.gridy = 4;
        dialog.add(createLabel("Harga Sewa (Rp):"), gbc);
        JTextField hargaField = createTextField();
        gbc.gridx = 1;
        dialog.add(hargaField, gbc);

        // Metode Pembayaran
        gbc.gridx = 0;
        gbc.gridy = 5;
        dialog.add(createLabel("Metode Pembayaran:"), gbc);
        JTextField metodeField = createTextField();
        gbc.gridx = 1;
        dialog.add(metodeField, gbc);

        // Upload Foto
        gbc.gridx = 0;
        gbc.gridy = 6;
        dialog.add(createLabel("Upload Foto:"), gbc);
        JButton uploadButton = createButton("Upload");
        JLabel fotoLabel = new JLabel("Belum ada foto yang diupload");
        fotoLabel.setForeground(Color.LIGHT_GRAY);
        fotoLabel.setFont(new Font("Arial", Font.ITALIC, 14));
        gbc.gridx = 1;
        dialog.add(uploadButton, gbc);

        gbc.gridx = 1;
        gbc.gridy = 7;
        dialog.add(fotoLabel, gbc);

        uploadButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            int result = fileChooser.showOpenDialog(dialog);
            if (result == JFileChooser.APPROVE_OPTION) {
                String fotoPath = fileChooser.getSelectedFile().getAbsolutePath();
                fotoLabel.setText("Foto diupload: " + fotoPath);
                fotoLabel.putClientProperty("fotoPath", fotoPath);
            }
        });

        // Tombol Simpan
        gbc.gridx = 0;
        gbc.gridy = 8;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        JButton saveButton = createButton("Simpan");
        dialog.add(saveButton, gbc);

        saveButton.addActionListener(e -> {
            try {
                String jenis = mobilButton.isSelected() ? "Mobil" : motorButton.isSelected() ? "Motor" : "Minibus";
                String nama = namaField.getText();
                String lokasi = lokasiField.getText();
                int harga = Integer.parseInt(hargaField.getText());
                String metode = metodeField.getText();
                String fotoPath = (String) fotoLabel.getClientProperty("fotoPath");

                if (nama.isEmpty() || lokasi.isEmpty() || metode.isEmpty() || fotoPath == null) {
                    throw new Exception("Semua data harus diisi.");
                }

                Kendaraan kendaraan = new Kendaraan(jenis, nama, lokasi, harga, metode, fotoPath);
                katalog.add(kendaraan);

                JOptionPane.showMessageDialog(dialog, "Kendaraan berhasil ditambahkan!", "Info", JOptionPane.INFORMATION_MESSAGE);
                refreshCallback.run();
                dialog.dispose();

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(dialog, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        dialog.setLocationRelativeTo(null);
        dialog.setVisible(true);
    }

    private JLabel createLabel(String text) {
        JLabel label = new JLabel(text);
        label.setForeground(Color.WHITE);
        label.setFont(new Font("Arial", Font.PLAIN, 16));
        return label;
    }

    private JTextField createTextField() {
        JTextField textField = new JTextField(20);
        textField.setFont(new Font("Arial", Font.PLAIN, 16));
        return textField;
    }

    private JRadioButton createRadioButton(String text) {
        JRadioButton radioButton = new JRadioButton(text);
        radioButton.setForeground(Color.WHITE);
        radioButton.setBackground(Color.decode("#212121"));
        radioButton.setFont(new Font("Arial", Font.PLAIN, 16));
        return radioButton;
    }

    private JButton createButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 16));
        button.setBackground(Color.decode("#009688")); // Hijau seperti di PanelPesewa
        button.setForeground(Color.WHITE);
        button.setFocusPainted(false);
        return button;
    }
}
