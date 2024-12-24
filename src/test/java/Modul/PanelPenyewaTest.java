package Modul;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class PanelPenyewaTest {
    private List<Kendaraan> katalog;
    private PanelPenyewa panelPenyewa;

    @BeforeEach
    void setUp() {
        // Buat daftar kendaraan untuk katalog
        katalog = new ArrayList<>();
        katalog.add(new Kendaraan("Mobil", "Honda Jazz", "Jakarta", 500000, "Cash", "path/to/image1.jpg"));
        katalog.add(new Kendaraan("Motor", "Yamaha NMAX", "Bandung", 150000, "Cash", "path/to/image2.jpg"));
        katalog.add(new Kendaraan("Bus", "Bus Pariwisata", "Surabaya", 2000000, "Transfer", ""));

        // Inisialisasi panel penyewa
        panelPenyewa = new PanelPenyewa(katalog);
    }

    @Test
    void testPanelInitialization() {
        JFrame frame = panelPenyewa.frame;
        assertNotNull(frame);
        assertEquals("Panel Penyewa", frame.getTitle());
        assertEquals(JFrame.DISPOSE_ON_CLOSE, frame.getDefaultCloseOperation());
    }

    @Test
    void testKatalogContent() {
        assertEquals(3, katalog.size());
        assertEquals("Mobil", katalog.get(0).jenis);
        assertEquals("Honda Jazz", katalog.get(0).nama);
        assertEquals("Jakarta", katalog.get(0).lokasi);
        assertEquals(500000, katalog.get(0).harga);
        assertFalse(katalog.get(0).isRented);
    }

    @Test
    void testRentKendaraan() {
        Kendaraan kendaraan = katalog.get(0);
        assertFalse(kendaraan.isRented);

        kendaraan.rent("User1", "2024-12-24");
        assertTrue(kendaraan.isRented);
    }

    @Test
    void testReturnKendaraan() {
        Kendaraan kendaraan = katalog.get(0);
        kendaraan.rent("User1", "2024-12-24");
        assertTrue(kendaraan.isRented);

        kendaraan.returnVehicle("2024-12-25");
        assertFalse(kendaraan.isRented);
    }

    @Test
    void testHistoryPenyewaan() {
        Kendaraan kendaraan = katalog.get(0);
        kendaraan.rent("User1", "2024-12-24");

        assertEquals(1, kendaraan.history.size());
        assertEquals("User1", kendaraan.history.get(0).renterName);
        assertEquals("2024-12-24", kendaraan.history.get(0).rentalDate);
        assertNull(kendaraan.history.get(0).returnDate);

        kendaraan.returnVehicle("2024-12-25");
        assertEquals("2024-12-25", kendaraan.history.get(0).returnDate);
    }
}
