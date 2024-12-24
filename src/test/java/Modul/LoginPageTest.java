package Modul;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javax.swing.*;
import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class LoginPageTest {
    private LoginPage loginPage;

    @BeforeEach
    void setUp() {
        loginPage = new LoginPage();
    }

    @Test
    void testFrameInitialization() {
        JFrame frame = loginPage.frame;
        assertNotNull(frame);
        assertEquals("Login - Aplikasi Penyewaan Kendaraan", frame.getTitle());
        assertEquals(JFrame.EXIT_ON_CLOSE, frame.getDefaultCloseOperation());
        assertEquals(Color.decode("#FFC107"), frame.getContentPane().getBackground());
    }

    @Test
    void testPanelComponents() {
        Container contentPane = loginPage.frame.getContentPane();
        assertTrue(contentPane.getLayout() instanceof GridBagLayout);

        Component[] components = contentPane.getComponents();
        assertEquals(1, components.length);
        assertTrue(components[0] instanceof JPanel);

        JPanel panel = (JPanel) components[0];
        assertEquals(Color.decode("#212121"), panel.getBackground());
        assertTrue(panel.getLayout() instanceof GridBagLayout);

        Component[] panelComponents = panel.getComponents();
        assertEquals(4, panelComponents.length); // Judul, subjudul, 2 tombol

        // Verifikasi label judul
        assertTrue(panelComponents[0] instanceof JLabel);
        JLabel titleLabel = (JLabel) panelComponents[0];
        assertEquals("Login", titleLabel.getText());
        assertEquals(Color.WHITE, titleLabel.getForeground());
        assertEquals(new Font("Arial", Font.BOLD, 30), titleLabel.getFont());

        // Verifikasi label subjudul
        assertTrue(panelComponents[1] instanceof JLabel);
        JLabel subtitleLabel = (JLabel) panelComponents[1];
        assertEquals("Pilih jenis login Anda", subtitleLabel.getText());
        assertEquals(Color.WHITE, subtitleLabel.getForeground());
        assertEquals(new Font("Arial", Font.PLAIN, 16), subtitleLabel.getFont());

        // Verifikasi tombol
        assertTrue(panelComponents[2] instanceof JButton);
        JButton adminButton = (JButton) panelComponents[2];
        assertEquals("Login sebagai Pesewa", adminButton.getText());
        assertEquals(Color.decode("#009688"), adminButton.getBackground());
        assertEquals(Color.WHITE, adminButton.getForeground());

        assertTrue(panelComponents[3] instanceof JButton);
        JButton memberButton = (JButton) panelComponents[3];
        assertEquals("Login sebagai Penyewa", memberButton.getText());
        assertEquals(Color.decode("#F44336"), memberButton.getBackground());
        assertEquals(Color.WHITE, memberButton.getForeground());
    }

    @Test
    void testUsersStorage() {
        LoginPage.getUsers().put("user1", "password1");
        LoginPage.getUsers().put("user2", "password2");

        assertEquals(2, LoginPage.getUsers().size());
        assertTrue(LoginPage.getUsers().containsKey("user1"));
        assertEquals("password1", LoginPage.getUsers().get("user1"));
    }
}
