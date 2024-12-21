package Modul;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class TambahKendaraanDialog {
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

        saveButton.addActionListener(e -> {
            try {
                String jenis = mobilButton.isSelected() ? "Mobil" : motorButton.isSelected() ? "Motor" : "Minibus";
                String nama = yamahaButton.isSelected() ? "Yamaha" : suzukiButton.isSelected() ? "Suzuki" : hondaButton.isSelected() ? "Honda" : ktmButton.isSelected() ? "KTM" : "Subaru";
                String lokasi = lokasiField.getText();
                int harga = Integer.parseInt(hargaField.getText());
                String metode = metodeField.getText();

                Kendaraan kendaraan = new Kendaraan(jenis, nama, lokasi, harga, metode);
                katalog.add(kendaraan);

                JOptionPane.showMessageDialog(dialog, "Kendaraan berhasil ditambahkan!", "Info", JOptionPane.INFORMATION_MESSAGE);
                dialog.dispose();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(dialog, "Input tidak valid!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        dialog.setVisible(true);
    }
}
