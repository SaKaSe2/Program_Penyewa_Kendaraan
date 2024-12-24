package Modul;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class KendaraanTest {
    private Kendaraan kendaraan;

    @BeforeEach
    void setUp() {
        kendaraan = new Kendaraan("Mobil", "Toyota Avanza", "Jakarta", 500000, "Tunai", "path/to/image.jpg");
    }

    @Test
    void testRentSuccess() {
        kendaraan.rent("John Doe", "2024-12-01");
        assertTrue(kendaraan.isRented);
        assertEquals(1, kendaraan.history.size());
        RentalHistory history = kendaraan.history.get(0);
        assertEquals("John Doe", history.renterName);
        assertEquals("2024-12-01", history.rentalDate);
        assertNull(history.returnDate);
    }

    @Test
    void testRentAlreadyRented() {
        kendaraan.rent("John Doe", "2024-12-01");
        kendaraan.rent("Jane Doe", "2024-12-02");
        assertTrue(kendaraan.isRented);
        assertEquals(1, kendaraan.history.size());
    }

    @Test
    void testReturnVehicleSuccess() {
        kendaraan.rent("John Doe", "2024-12-01");
        kendaraan.returnVehicle("2024-12-02");
        assertFalse(kendaraan.isRented);
        RentalHistory history = kendaraan.history.get(0);
        assertEquals("2024-12-02", history.returnDate);
    }

    @Test
    void testReturnVehicleNotRented() {
        kendaraan.returnVehicle("2024-12-02");
        assertFalse(kendaraan.isRented);
        assertTrue(kendaraan.history.isEmpty());
    }

    @Test
    void testToString() {
        String expected = "Mobil - Toyota Avanza di Jakarta (Rp500000) - Tunai | Status: Tersedia";
        assertEquals(expected, kendaraan.toString());
        kendaraan.rent("John Doe", "2024-12-01");
        String expectedRented = "Mobil - Toyota Avanza di Jakarta (Rp500000) - Tunai | Status: Disewa";
        assertEquals(expectedRented, kendaraan.toString());
    }

    @Test
    void testPrintRentalHistory() {
        kendaraan.rent("John Doe", "2024-12-01");
        kendaraan.returnVehicle("2024-12-02");
        List<RentalHistory> history = kendaraan.history;
        assertEquals(1, history.size());
        assertEquals("John Doe", history.get(0).renterName);
        assertEquals("2024-12-01", history.get(0).rentalDate);
        assertEquals("2024-12-02", history.get(0).returnDate);
    }
}
