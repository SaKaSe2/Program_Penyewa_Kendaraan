package Modul;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PanelPesewaTest {
    private List<Kendaraan> katalog;
    private PanelPesewa panelPesewa;

    @BeforeEach
    void setUp() {
        katalog = new ArrayList<>();
        katalog.add(new Kendaraan("Mobil", "Toyota Avanza", "Jakarta", 350000, "Harian", "Tersedia"));
        katalog.add(new Kendaraan("Motor", "Yamaha NMAX", "Bandung", 150000, "Harian", "Tersedia"));
        TampilanLoginPesewa.setKatalog(katalog); // Simulasikan pengisian katalog kendaraan
        panelPesewa = new PanelPesewa();
    }

    @Test
    void testKatalogInitialization() {
        assertEquals(2, katalog.size(), "Katalog kendaraan seharusnya memiliki 2 item.");
        assertEquals("Toyota Avanza", katalog.get(0).getNama(), "Item pertama dalam katalog harus Toyota Avanza.");
        assertEquals("Yamaha NMAX", katalog.get(1).getNama(), "Item kedua dalam katalog harus Yamaha NMAX.");
    }

    @Test
    void testFrameInitialization() {
        JFrame frame = panelPesewa.getFrame();
        assertNotNull(frame, "Frame tidak boleh null.");
        assertEquals("Panel Pesewa", frame.getTitle(), "Judul frame harus 'Panel Pesewa'.");
        assertTrue(frame.isVisible(), "Frame harus terlihat.");
    }

    @Test
    void testRefreshTable() {
        panelPesewa.refreshTable();

        // Periksa bahwa panel utama memiliki komponen yang benar
        JPanel mainPanel = panelPesewa.getMainPanel();
        assertNotNull(mainPanel.getComponent(0), "Tabel harus ada di panel utama.");
        assertTrue(mainPanel.getComponent(0) instanceof JScrollPane, "Komponen pertama harus JScrollPane.");

        JScrollPane scrollPane = (JScrollPane) mainPanel.getComponent(0);
        JTable table = (JTable) scrollPane.getViewport().getView();

        // Periksa data dalam tabel
        assertEquals(2, table.getRowCount(), "Tabel harus memiliki 2 baris.");
        assertEquals("Mobil", table.getValueAt(0, 0), "Baris pertama harus menunjukkan kendaraan jenis Mobil.");
        assertEquals("Motor", table.getValueAt(1, 0), "Baris kedua harus menunjukkan kendaraan jenis Motor.");
    }

    @Test
    void testTambahKendaraan() {
        // Simulasikan penambahan kendaraan baru
        Kendaraan baru = new Kendaraan("Motor", "Honda Vario", "Surabaya", 120000, "Harian", "Tersedia");
        katalog.add(baru);
        panelPesewa.refreshTable();

        // Periksa bahwa katalog telah diperbarui
        assertEquals(3, katalog.size(), "Katalog kendaraan seharusnya memiliki 3 item setelah penambahan.");
        assertEquals("Honda Vario", katalog.get(2).getNama(), "Kendaraan baru harus bernama Honda Vario.");
    }

    @Test
    void testKeluarButton() {
        JButton backButton = (JButton) ((JPanel) panelPesewa.getFrame().getContentPane()).getComponent(2);
        assertNotNull(backButton, "Tombol keluar harus ada.");
        assertEquals("Keluar", backButton.getText(), "Teks pada tombol keluar harus 'Keluar'.");
    }
}
