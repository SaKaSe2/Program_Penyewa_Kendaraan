package Modul;

public class Kendaraan {
    private final String jenis;
    private final String nama;
    private final String lokasi;
    private final int harga;
    private final String metode;

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
