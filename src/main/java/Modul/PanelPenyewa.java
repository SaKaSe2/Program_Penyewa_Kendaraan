package Modul;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.ArrayList;


class PanelPenyewa {
    JFrame frame;

    private void refreshTable(List<Kendaraan> katalog) {
        String[] columnNames = {"Jenis", "Nama", "Lokasi", "Harga", "Metode"};
        Object[][] data = new Object[katalog.size()][5];

        for (int i = 0; i < katalog.size(); i++) {
            Kendaraan kendaraan = katalog.get(i);
            data[i][0] = kendaraan.jenis;
            data[i][1] = kendaraan.nama;
            data[i][2] = kendaraan.lokasi;
            data[i][3] = "Rp" + kendaraan.harga;
            data[i][4] = kendaraan.metode;
        }

        JTable table = new JTable(data, columnNames);
        JScrollPane tableScrollPane = new JScrollPane(table);

        // Area untuk menampilkan gambar kendaraan
        JLabel imageLabel = new JLabel("Pilih kendaraan untuk melihat gambar.", SwingConstants.CENTER);
        table.getSelectionModel().addListSelectionListener(e -> {
            int selectedRow = table.getSelectedRow();
            if (selectedRow >= 0 && selectedRow < katalog.size()) {
                String fotoPath = katalog.get(selectedRow).fotoPath;
                if (fotoPath != null && !fotoPath.isEmpty()) {
                    ImageIcon icon = new ImageIcon(fotoPath);
                    Image img = icon.getImage().getScaledInstance(300, 200, Image.SCALE_SMOOTH);
                    imageLabel.setIcon(new ImageIcon(img));
                } else {
                    imageLabel.setIcon(null);
                    imageLabel.setText("Tidak ada gambar untuk kendaraan ini.");
                }
            }
        });

        frame.getContentPane().removeAll();  // Remove previous content
        frame.add(tableScrollPane, BorderLayout.CENTER);  // Add new table
        frame.add(imageLabel, BorderLayout.SOUTH);  // Add image label again
        frame.revalidate();  // Revalidate to refresh the layout
        frame.repaint();  // Repaint the frame
    }

    public PanelPenyewa(List<Kendaraan> katalog) {
        frame = new JFrame("Panel Penyewa");
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        refreshTable(katalog); // Panggil metode refreshTable dengan katalog

        frame.setVisible(true);
    }
}
