import javax.swing.*;
import java.awt.*;

public class ProjectDetailApp {

    public static void main(String[] args) {
        // Disarankan untuk menjalankan aplikasi Swing di Event Dispatch Thread (EDT)
        SwingUtilities.invokeLater(() -> createAndShowGUI());
    }

    private static void createAndShowGUI() {
        // --- 1. Setup Frame Utama ---
        JFrame frame = new JFrame("Detail Proyek RNV-JKT-AXG-001");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 700);
        frame.setLayout(new BorderLayout(10, 10)); // Border Layout untuk membagi menjadi area utama

        // --- 2. Panel Utama (Menggunakan Box atau Grid Layout untuk konten) ---
        JPanel mainPanel = new JPanel();
        // Menggunakan BoxLayout untuk mengatur komponen secara vertikal
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS)); 
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // --- 3. Tambahkan Komponen Sesuai Struktur UI ---

        // Bagian Header: Client, Information, Additional Information
        mainPanel.add(createHeaderPanel());
        mainPanel.add(Box.createVerticalStrut(15)); // Jarak vertikal

        // Bagian Product List
        mainPanel.add(createProductListPanel());
        mainPanel.add(Box.createVerticalStrut(15));

        // Bagian Tasks
        mainPanel.add(createTasksPanel());
        mainPanel.add(Box.createVerticalStrut(15));

        // Bagian Comments
        mainPanel.add(createCommentsPanel());
        mainPanel.add(Box.createVerticalStrut(15));

        // Bagian Footer (Save dan Export)
        frame.add(createFooterPanel(), BorderLayout.SOUTH);

        // Tambahkan panel utama ke frame
        frame.add(new JScrollPane(mainPanel), BorderLayout.CENTER);
        
        frame.setVisible(true);
    }

    // --- Metode untuk Membuat Panel Header (Client, Info, Add Info) ---
    private static JPanel createHeaderPanel() {
        JPanel headerPanel = new JPanel(new GridLayout(1, 3, 10, 0)); // 1 baris, 3 kolom
        headerPanel.add(createClientPanel());
        headerPanel.add(createInfoPanel());
        headerPanel.add(createAdditionalInfoPanel());
        return headerPanel;
    }

    private static JPanel createClientPanel() {
        // Kerangka sederhana untuk Detail Klien
        JPanel panel = new JPanel(new GridLayout(5, 1));
        panel.setBorder(BorderFactory.createTitledBorder("Client"));
        panel.add(new JLabel("Client ID: 101"));
        panel.add(new JLabel("Name: Bapak Alex Gunawan"));
        panel.add(new JLabel("Phone: (+62) 8123456789"));
        panel.add(new JLabel("Registration No: RNV-JKT-AXG-001"));
        panel.add(new JButton("Details"));
        return panel;
    }

    private static JPanel createInfoPanel() {
        // Kerangka sederhana untuk Information
        JPanel panel = new JPanel(new GridLayout(6, 1));
        panel.setBorder(BorderFactory.createTitledBorder("Information"));
        panel.add(new JLabel("Reserve days: 0 of 30"));
        panel.add(new JLabel("Buyer: Bapak Alex Gunawan"));
        panel.add(new JLabel("Seller: PT Bangun Jaya Abadi"));
        panel.add(new JLabel("Address: Jl. Raya Lohbener Baru, 778A"));
        panel.add(new JLabel("Credit Rating: AAA"));
        panel.add(new JCheckBox("Approved: Proyek renovasi telah disetujui"));
        return panel;
    }

    private static JPanel createAdditionalInfoPanel() {
        // Kerangka sederhana untuk Additional Information
        JPanel panel = new JPanel(new GridLayout(6, 1));
        panel.setBorder(BorderFactory.createTitledBorder("Additional Information"));
        panel.add(new JLabel("Estimated close: 2025-12-15"));
        panel.add(new JLabel("Creation date: 2025-10-15"));
        panel.add(new JLabel("Created by: Admin"));
        panel.add(new JLabel("Last edit date: 2025-11-16"));
        panel.add(new JLabel("Last edited by: Warnoto"));
        panel.add(new JLabel("Closed date: null"));
        return panel;
    }

    // --- Metode untuk Membuat Panel Product List ---
    private static JPanel createProductListPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Product List"));
        
        // Data sederhana untuk JTable
        String[] columnNames = {"Renovation", "Description", "Part #", "Quantity", "List Price", "Wholesaler Price"};
        Object[][] data = {
            {"Dapur", "Keramik Dinding Putih", "KW-PT-DLX-01", 50, 150000, 7125000},
            {"Dapur", "Lem Keramik Instan", "LMI-GRY-STD", 5, 50000, 250000},
            {"Dapur", "Pipa PVC 3 inch", "PVC-3IN-STD", 12, 35000, 378000}
        };

        JTable table = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Tombol Add, Edit, Delete (diletakkan di panel terpisah)
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(new JButton("Add"));
        buttonPanel.add(new JButton("Edit"));
        buttonPanel.add(new JButton("Delete"));
        panel.add(buttonPanel, BorderLayout.EAST); // Menyimpang dari gambar, tapi lebih mudah di Swing

        // Subtotal info (diletakkan di bawah tabel)
        JPanel subtotalPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        subtotalPanel.add(new JLabel("Subtotal list price: Rp 15.570.000"));
        panel.add(subtotalPanel, BorderLayout.SOUTH);

        return panel;
    }

    // --- Metode untuk Membuat Panel Tasks ---
    private static JPanel createTasksPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Tasks"));
        
        // Data sederhana untuk JTable
        String[] columnNames = {"State", "Task", "Assigner", "Executor", "Creation Date", "Estimated Date"};
        Object[][] data = {
            {"Completed", "Pemasangan pipa PVC di area garasi", "Warnoto", "Toni", "2025-10-20", "2025-10-21"},
            {"Completed", "Pengecatan ulang ruang tamu", "Warnoto", "Toni", "2025-10-25", "2025-10-28"},
            {"Delayed", "Pemasangan keramik dinding dapur", "Warnoto", "Toni", "2025-11-01", "2025-11-04"}
        };

        JTable table = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Tombol Add, Edit, Delete
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(new JButton("Add"));
        buttonPanel.add(new JButton("Edit"));
        buttonPanel.add(new JButton("Delete"));
        panel.add(buttonPanel, BorderLayout.EAST);

        return panel;
    }
    
    // --- Metode untuk Membuat Panel Comments ---
    private static JPanel createCommentsPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Comments"));

        // Data sederhana untuk JTable
        String[] columnNames = {"Date", "User", "Comment"};
        Object[][] data = {
            {"2025-10-26 10:15", "Toni", "Cat tembok sudah diolesi lapisan pertama."},
            {"2025-11-03 16:45", "Toni", "Cuaca hujan deras selama 2 hari, area sedikit basah. Pemasangan ditunda besok pagi."},
            {"2025-11-15 14:30", "Goang", "Barang sudah sampai di lokasi."}
        };

        JTable table = new JTable(data, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Tombol Add, Edit
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        buttonPanel.add(new JButton("Add"));
        buttonPanel.add(new JButton("Edit"));
        panel.add(buttonPanel, BorderLayout.EAST);

        return panel;
    }

    // --- Metode untuk Membuat Panel Footer (Export, Save) ---
    private static JPanel createFooterPanel() {
        JPanel footerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        footerPanel.add(new JButton("Export"));
        
        // Panel untuk tombol Save (dibuat di kanan)
        JPanel savePanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        savePanel.add(new JButton("Cancel")); // Tambahkan tombol Cancel (tidak ada di gambar, tapi umum)
        savePanel.add(new JButton("Save"));

        JPanel wrapperPanel = new JPanel(new BorderLayout());
        wrapperPanel.add(footerPanel, BorderLayout.WEST);
        wrapperPanel.add(savePanel, BorderLayout.EAST);
        wrapperPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 10, 10)); // Padding

        return wrapperPanel;
    }
}