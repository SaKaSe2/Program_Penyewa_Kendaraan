package Modul;

import java.util.ArrayList;
import java.util.List;

class Kendaraan {
    String jenis;
    String nama;
    String lokasi;
    int harga;
    String metode;
    String fotoPath; // Path atau URL gambar kendaraan
    boolean isRented; // Status apakah kendaraan sedang disewa
    List<RentalHistory> history; // Daftar riwayat penyewaan

    // Constructor
    public Kendaraan(String jenis, String nama, String lokasi, int harga, String metode, String fotoPath) {
        this.jenis = jenis;
        this.nama = nama;
        this.lokasi = lokasi;
        this.harga = harga;
        this.metode = metode;
        this.fotoPath = fotoPath;
        this.isRented = false; // Default: belum disewa
        this.history = new ArrayList<>();
    }

    // Metode untuk menyewa kendaraan
    public void rent(String renterName, String rentalDate) {
        if (!isRented) {
            isRented = true;
            System.out.println(nama + " berhasil disewa.");
            history.add(new RentalHistory(renterName, rentalDate, null));
        } else {
            System.out.println(nama + " sudah disewa, tidak dapat disewa lagi.");
        }
    }

    // Metode untuk mengembalikan kendaraan
    public void returnVehicle(String returnDate) {
        if (isRented) {
            isRented = false;
            System.out.println(nama + " berhasil dikembalikan.");
            // Tambahkan tanggal pengembalian ke riwayat terakhir
            if (!history.isEmpty()) {
                RentalHistory lastRental = history.get(history.size() - 1);
                lastRental.setReturnDate(returnDate);
            }
        } else {
            System.out.println(nama + " belum disewa.");
        }
    }

    // Metode untuk mencetak riwayat penyewaan
    public void printRentalHistory() {
        System.out.println("Riwayat Penyewaan untuk " + nama + ":");
        for (RentalHistory h : history) {
            System.out.println(h);
        }
    }

    @Override
    public String toString() {
        return jenis + " - " + nama + " di " + lokasi + " (Rp" + harga + ") - " + metode + " | Status: " + (isRented ? "Disewa" : "Tersedia");
    }
}

class RentalHistory {
    String renterName;
    String rentalDate;
    String returnDate;

    public RentalHistory(String renterName, String rentalDate, String returnDate) {
        this.renterName = renterName;
        this.rentalDate = rentalDate;
        this.returnDate = returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    @Override
    public String toString() {
        return "Penyewa: " + renterName + ", Mulai: " + rentalDate + ", Selesai: " + (returnDate != null ? returnDate : "Belum dikembalikan");
    }
}
