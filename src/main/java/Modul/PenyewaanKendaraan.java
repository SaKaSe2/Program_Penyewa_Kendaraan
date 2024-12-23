package Modul;

public class PenyewaanKendaraan {
    public static void main(String[] args) {
        new LoginPage(); // Memulai aplikasi dari sini
        TampilanLoginPenyewa loginPenyewa = new TampilanLoginPenyewa();
        loginPenyewa.showPenyewaLogin();


    }
}