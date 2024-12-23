package Modul;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

class TambahKendaraanDialog {

    public TambahKendaraanDialog(List<Kendaraan> katalog) {
        JDialog dialog = new JDialog();
        dialog.setTitle("Tambah Kendaraan");
        dialog.setSize(400, 500);
        dialog.setLayout(new GridLayout(9, 2));

        ButtonGroup jenisGroup = new ButtonGroup();
        JRadioButton mobilButton = new JRadioButton("Mobil");
        JRadioButton motorButton = new JRadioButton("Motor");
        JRadioButton minibusButton = new JRadioButton("Minibus");
        jenisGroup.add(mobilButton);
        jenisGroup.add(motorButton);
        jenisGroup.add(minibusButton);

        ButtonGroup namaGroup = new ButtonGroup();
        JRadioButton yamahaButton = new JRadioButton("Yamaha");
        JRadioButton suzukiButton = new JRadioButton("Suzuki");
        JRadioButton hondaButton = new JRadioButton("Honda");
        JRadioButton ktmButton = new JRadioButton("KTM");
        JRadioButton subaruButton = new JRadioButton("Subaru");

        namaGroup.add(yamahaButton);
        namaGroup.add(suzukiButton);
        namaGroup.add(hondaButton);
        namaGroup.add(ktmButton);
        namaGroup.add(subaruButton);

        JTextField lokasiField = new JTextField();
        JTextField hargaField = new JTextField();
        JTextField metodeField = new JTextField();
        JButton uploadButton = new JButton("Upload Foto");
        JLabel fotoLabel = new JLabel("Belum ada foto yang diupload.", SwingConstants.CENTER);

        JButton saveButton = new JButton("Simpan");

        dialog.add(new JLabel("Jenis Kendaraan:"));
        JPanel jenisPanel = new JPanel(new FlowLayout());
        jenisPanel.add(mobilButton);
        jenisPanel.add(motorButton);
        jenisPanel.add(minibusButton);
        dialog.add(jenisPanel);

        dialog.add(new JLabel("Nama/Merek Kendaraan:"));
        JPanel namaPanel = new JPanel(new FlowLayout());
        namaPanel.add(yamahaButton);
        namaPanel.add(suzukiButton);
        namaPanel.add(hondaButton);
        namaPanel.add(ktmButton);
        namaPanel.add(subaruButton);
        dialog.add(namaPanel);

        yamahaButton.setEnabled(false);
        suzukiButton.setEnabled(false);
        hondaButton.setEnabled(false);
        ktmButton.setEnabled(false);
        subaruButton.setEnabled(false);

        mobilButton.addActionListener(e -> {
            yamahaButton.setEnabled(false);
            suzukiButton.setEnabled(true);
            hondaButton.setEnabled(true);
            ktmButton.setEnabled(true);
            subaruButton.setEnabled(true);
        });

        motorButton.addActionListener(e -> {
            yamahaButton.setEnabled(true);
            suzukiButton.setEnabled(true);
            hondaButton.setEnabled(true);
            ktmButton.setEnabled(true);
            subaruButton.setEnabled(false);
        });

        minibusButton.addActionListener(e -> {
            yamahaButton.setEnabled(false);
            suzukiButton.setEnabled(false);
            hondaButton.setEnabled(false);
            ktmButton.setEnabled(false);
            subaruButton.setEnabled(false);
        });

        dialog.add(new JLabel("Keterangan/Lokasi Kendaraan:"));
        dialog.add(lokasiField);
        dialog.add(new JLabel("Harga Sewa Kendaraan:"));
        dialog.add(hargaField);
        dialog.add(new JLabel("Metode Pembayaran:"));
        dialog.add(metodeField);
        dialog.add(uploadButton);
        dialog.add(fotoLabel);
        dialog.add(new JLabel());
        dialog.add(saveButton);

        uploadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFileChooser fileChooser = new JFileChooser();
                int result = fileChooser.showOpenDialog(dialog);
                if (result == JFileChooser.APPROVE_OPTION) {
                    String fotoPath = fileChooser.getSelectedFile().getAbsolutePath();
                    fotoLabel.setText("Foto diupload: " + fotoPath);
                    fotoLabel.putClientProperty("fotoPath", fotoPath); // Simpan path di properti label
                }
            }
        });

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    // Validasi jenis kendaraan
                    String jenis = mobilButton.isSelected() ? "Mobil" : motorButton.isSelected() ? "Motor" : "Minibus";
                    if (jenis.equals("Minibus") && !minibusButton.isSelected()) {
                        throw new Exception("Jenis kendaraan harus dipilih.");
                    }

                    // Validasi nama kendaraan
                    String nama = yamahaButton.isSelected() ? "Yamaha" :
                            suzukiButton.isSelected() ? "Suzuki" :
                                    hondaButton.isSelected() ? "Honda" :
                                            ktmButton.isSelected() ? "KTM" : "Subaru";
                    if (nama.equals("Subaru") && !subaruButton.isSelected()) {
                        throw new Exception("Nama kendaraan harus dipilih.");
                    }

                    // Validasi lokasi, harga, dan metode pembayaran
                    String lokasi = lokasiField.getText();
                    if (lokasi.isEmpty()) {
                        throw new Exception("Lokasi kendaraan harus diisi.");
                    }

                    int harga;
                    try {
                        harga = Integer.parseInt(hargaField.getText());
                    } catch (NumberFormatException ex) {
                        throw new Exception("Harga sewa harus berupa angka.");
                    }

                    String metode = metodeField.getText();
                    if (metode.isEmpty()) {
                        throw new Exception("Metode pembayaran harus diisi.");
                    }

                    // Validasi foto
                    String fotoPath = (String) fotoLabel.getClientProperty("fotoPath");
                    if (fotoPath == null || fotoPath.isEmpty()) {
                        throw new Exception("Foto harus diupload.");
                    }

                    // Simpan data kendaraan
                    Kendaraan kendaraan = new Kendaraan(jenis, nama, lokasi, harga, metode, fotoPath);
                    katalog.add(kendaraan);

                    JOptionPane.showMessageDialog(dialog, "Kendaraan berhasil ditambahkan!", "Info", JOptionPane.INFORMATION_MESSAGE);
                    dialog.dispose();

                    // Update the table in PanelPenyewa
                    new PanelPenyewa(katalog);  // Create new PanelPenyewa with updated katalog
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(dialog, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        dialog.setVisible(true);
    }
}
