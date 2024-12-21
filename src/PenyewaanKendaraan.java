import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class PenyewaanKendaraan {

    public static void main(String[] args) {
        new LoginPage();
    }
}

// Kelas LoginPage
class LoginPage {
    JFrame frame;
    JTextField usernameField;
    JPasswordField passwordField;

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

        pesewaLoginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new PanelPesewa();
                frame.dispose();
            }
        });

        penyewaLoginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new PanelPenyewa();
                frame.dispose();
            }
        });

        frame.setVisible(true);
    }
}

// Kelas PanelPesewa
class PanelPesewa {
    JFrame frame;
    List<Kendaraan> katalog;

    public PanelPesewa() {
        katalog = new ArrayList<>();

        frame = new JFrame("Panel Pesewa");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JButton addButton = new JButton("Tambah Kendaraan");
        JButton viewHistoryButton = new JButton("Lihat History");
        JButton editButton = new JButton("Edit Kendaraan");
        JButton deleteButton = new JButton("Hapus Kendaraan");

        JPanel buttonPanel = new JPanel(new GridLayout(1, 4));
        buttonPanel.add(addButton);
        buttonPanel.add(viewHistoryButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);

        frame.add(buttonPanel, BorderLayout.SOUTH);

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new TambahKendaraanDialog(katalog);
            }
        });

        viewHistoryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "Fitur Lihat History akan dikembangkan.", "Info", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "Fitur Edit Kendaraan akan dikembangkan.", "Info", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame, "Fitur Hapus Kendaraan akan dikembangkan.", "Info", JOptionPane.INFORMATION_MESSAGE);
            }
        });

        frame.setVisible(true);
    }
}

// Kelas TambahKendaraanDialog
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
                }
            }
        });

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
            }
        });

        dialog.setVisible(true);
    }
}

// Kelas Kendaraan
class Kendaraan {
    String jenis;
    String nama;
    String lokasi;
    int harga;
    String metode;

    public Kendaraan(String jenis, String nama, String lokasi, int harga, String metode) {
        this.jenis = jenis;
        this.nama = nama;
        this.lokasi = lokasi;
        this.harga = harga;
        this.metode = metode;
    }

    @Override
    public String toString() {
        return jenis + " - " + nama + " di " + lokasi + " (Rp" + harga + ") - " + metode;
    }
}

// Kelas PanelPenyewa (Placeholder untuk pengembangan lanjutan)
class PanelPenyewa {
    JFrame frame;

    public PanelPenyewa() {
        frame = new JFrame("Panel Penyewa");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JLabel infoLabel = new JLabel("Fitur Panel Penyewa akan dikembangkan.", SwingConstants.CENTER);
        frame.add(infoLabel, BorderLayout.CENTER);

        frame.setVisible(true);
    }
}