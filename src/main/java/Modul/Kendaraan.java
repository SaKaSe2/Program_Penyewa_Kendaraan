package Modul;

class Kendaraan {
    String jenis;
    String nama;
    String lokasi;
    int harga;
    String metode;
    String fotoPath; // Path atau URL gambar kendaraan

    public Kendaraan(String jenis, String nama, String lokasi, int harga, String metode, String fotoPath) {
        this.jenis = jenis;
        this.nama = nama;
        this.lokasi = lokasi;
        this.harga = harga;
        this.metode = metode;
        this.fotoPath = fotoPath;
    }

    @Override
    public String toString() {
        return jenis + " - " + nama + " di " + lokasi + " (Rp" + harga + ") - " + metode;
    }
}