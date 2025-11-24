import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ProjectDetailApp1 {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> createAndShowGUI());
    }

    private static void createAndShowGUI() {
        // --- 1. Setup Frame Utama ---
        JFrame frame = new JFrame("Detail Proyek RNV-JKT-AXG-001");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Mengatur ukuran agar mirip dengan tampilan Kanan
        frame.setSize(950, 800); 
        frame.setLayout(new BorderLayout());

        // --- 2. Panel Utama (Menggunakan GridBagLayout untuk Konten Fleksibel) ---
        JPanel mainContentPanel = new JPanel(new GridBagLayout());
        mainContentPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5); // Padding antar komponen

        // --- 3. Header Panel (Client, Information, Additional Info) ---
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0; // Ambil semua ruang horizontal
        mainContentPanel.add(createHeaderPanel(), gbc);

        // --- 4. Product List Panel ---
        gbc.gridy = 1;
        gbc.weighty = 0.0; // Tidak membesar secara vertikal
        gbc.fill = GridBagConstraints.BOTH; 
        mainContentPanel.add(createProductListPanel(), gbc);

        // --- 5. Tasks Panel ---
        gbc.gridy = 2;
        gbc.weighty = 0.0;
        mainContentPanel.add(createTasksPanel(), gbc);

        // --- 6. Comments Panel ---
        gbc.gridy = 3;
        gbc.weighty = 1.0; // Biarkan Comments membesar jika jendela diperluas
        mainContentPanel.add(createCommentsPanel(), gbc);

        // Tambahkan panel konten ke frame utama (di dalam JScrollPane agar bisa discroll)
        JScrollPane scrollPane = new JScrollPane(mainContentPanel);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        frame.add(scrollPane, BorderLayout.CENTER);

        // --- 7. Footer Panel (Export, Save, Cancel) ---
        frame.add(createFooterPanel(), BorderLayout.SOUTH);
        
        frame.setVisible(true);
    }
    
    // ---------------------------------------------
    // --- KOMPONEN UTAMA ---
    // ---------------------------------------------

    private static JPanel createHeaderPanel() {
        // Menggunakan GridLayout(1, 3) untuk membagi rata 3 kolom di bagian atas
        JPanel headerPanel = new JPanel(new GridLayout(1, 3, 10, 0)); 
        headerPanel.add(createClientPanel());
        headerPanel.add(createInfoPanel());
        headerPanel.add(createAdditionalInfoPanel());
        return headerPanel;
    }

    private static JPanel createClientPanel() {
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        panel.setBorder(BorderFactory.createTitledBorder("Client"));
        
        JPanel detail = new JPanel(new GridLayout(5, 2));
        detail.add(new JLabel("Client ID:")); 
        detail.add(new JLabel("<html><b>101</b></html>"));
        detail.add(new JLabel("Name:"));
        detail.add(new JLabel("<html><b>Bapak Alex Gunawan</b></html>"));
        detail.add(new JLabel("Phone:"));
        detail.add(new JLabel("(+62) 8123456789"));
        detail.add(new JLabel("Registration No:"));
        detail.add(new JLabel("RNV-JKT-AXG-001"));
        
        JPanel buttonWrapper = new JPanel(new FlowLayout(FlowLayout.LEFT));
        buttonWrapper.add(new JButton("Details"));
        
        panel.add(detail, BorderLayout.NORTH);
        panel.add(buttonWrapper, BorderLayout.SOUTH);

        return panel;
    }

    private static JPanel createInfoPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Information"));
        
        // Menggunakan GridBagLayout di dalam panel ini untuk kontrol presisi
        JPanel detail = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(2, 2, 2, 2);

        // Baris 1: Reserve days (TextField dan Add Days Button)
        gbc.gridx = 0; gbc.gridy = 0; detail.add(new JLabel("Reserve days:"), gbc);
        gbc.gridx = 1; gbc.weightx = 1.0; detail.add(new JTextField("0 of 30", 8), gbc);
        gbc.gridx = 2; gbc.weightx = 0.0; detail.add(new JButton("Add Days"), gbc);
        
        // Baris 2 sampai 5: Buyer, Seller, Address, Credit Rating
        gbc.gridx = 0; gbc.gridy = 1; detail.add(new JLabel("Buyer:"), gbc);
        gbc.gridx = 1; gbc.gridwidth = 2; detail.add(new JLabel("Bapak Alex Gunawan"), gbc);
        
        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 1; detail.add(new JLabel("Seller:"), gbc);
        gbc.gridx = 1; gbc.gridwidth = 2; detail.add(new JLabel("PT Bangun Jaya Abadi"), gbc);
        
        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 1; detail.add(new JLabel("Address:"), gbc);
        gbc.gridx = 1; gbc.gridwidth = 2; detail.add(new JLabel("Jl. Raya Lohbener Baru, 778A"), gbc);
        
        gbc.gridx = 0; gbc.gridy = 4; gbc.gridwidth = 1; detail.add(new JLabel("Credit Rating:"), gbc);
        gbc.gridx = 1; gbc.gridwidth = 1; detail.add(new JLabel("AAA"), gbc);
        gbc.gridx = 2; detail.add(new JButton("S&P Update"), gbc);

        // Baris 6: Approved
        gbc.gridx = 0; gbc.gridy = 5; gbc.gridwidth = 3; 
        detail.add(new JCheckBox("Approved: Proyek renovasi telah disetujui, siap dimulai"), gbc);

        panel.add(detail, BorderLayout.CENTER);
        return panel;
    }

    private static JPanel createAdditionalInfoPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Additional Information"));
        
        JPanel detail = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(2, 2, 2, 2);

        // Helper untuk Baris Info dan tombol Edit
        // gbc.gridx = 0 (Label), gbc.gridx = 1 (Value), gbc.gridx = 2 (Button)
        
        gbc.gridx = 0; gbc.gridy = 0; detail.add(new JLabel("Estimated close:"), gbc);
        gbc.gridx = 1; gbc.weightx = 1.0; detail.add(new JLabel("2025-12-15"), gbc);
        gbc.gridx = 2; gbc.weightx = 0.0; detail.add(new JButton("Edit"), gbc);

        String[][] data = {
            {"Creation date:", "2025-10-15"},
            {"Created by:", "<html><b>Admin</b></html>"},
            {"Last edit date:", "2025-11-16"},
            {"Last edited by:", "<html><b>Warnoto</b></html>"},
            {"Closed date:", "null"},
            {"Closed by:", "null"}
        };

        for (int i = 0; i < data.length; i++) {
            gbc.gridx = 0; gbc.gridy = i + 1; detail.add(new JLabel(data[i][0]), gbc);
            gbc.gridx = 1; gbc.gridwidth = 2; detail.add(new JLabel(data[i][1]), gbc);
        }

        panel.add(detail, BorderLayout.NORTH);
        return panel;
    }
    
    private static JPanel createProductListPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Product List"));
        
        // Model Data Product List (kolom disesuaikan dengan tampilan Kanan)
        String[] columnNames = {"Renovation", "Description", "Part #", "Quantity", "List Price", "Discount", "Wholesale Discount", "Wholesaler Price"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        tableModel.addRow(new Object[]{"Dapur", "Keramik Dinding Putih", "KW-PT-DLX-01", 50, 150000, 0, 5, 7125000});
        tableModel.addRow(new Object[]{"Dapur", "Lem Keramik Instan", "LMI-GRY-STD", 5, 50000, 0, 10, 250000});
        tableModel.addRow(new Object[]{"Dapur", "Pipa PVC 3 inch", "PVC-3IN-STD", 12, 35000, 0, 10, 378000});

        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Panel Kontrol & Subtotal di Kanan
        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));

        // Tombol Add, Edit, Delete (diletakkan vertikal di samping tabel)
        JPanel buttonPanel = new JPanel(new GridLayout(3, 1, 0, 5));
        buttonPanel.add(new JButton("Add"));
        buttonPanel.add(new JButton("Edit"));
        buttonPanel.add(new JButton("Delete"));

        // Detail Subtotal
        JPanel subtotalDetail = new JPanel(new GridLayout(3, 1));
        subtotalDetail.add(new JLabel("Subtotal list price: Rp 15.570.000"));
        subtotalDetail.add(new JLabel("Total retailer price: Rp 15.182.500"));
        subtotalDetail.add(new JLabel("Total wholesaler price: Rp 14.249.625"));
        
        rightPanel.add(buttonPanel);
        rightPanel.add(Box.createVerticalGlue()); // Spacer
        rightPanel.add(subtotalDetail);

        panel.add(rightPanel, BorderLayout.EAST);
        return panel;
    }

    private static JPanel createTasksPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Tasks"));
        
        // Model Data Tasks
        String[] columnNames = {"State", "Task", "Assigner", "Executor", "Creation Date", "Estimated Date", "Executed Date"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        tableModel.addRow(new Object[]{"Completed", "Pemasangan pipa PVC di area garasi", "Warnoto", "Toni", "2025-10-20", "2025-10-21", "2025-10-21"});
        tableModel.addRow(new Object[]{"Completed", "Pengecatan ulang ruang tamu", "Warnoto", "Toni", "2025-10-25", "2025-10-28", "2025-10-27"});
        tableModel.addRow(new Object[]{"Delayed", "Pemasangan keramik dinding dapur", "Warnoto", "Toni", "2025-11-01", "2025-11-04", null});

        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Tombol Add, Edit, Delete (vertikal)
        JPanel buttonPanel = new JPanel(new GridLayout(3, 1, 0, 5));
        buttonPanel.add(new JButton("Add"));
        buttonPanel.add(new JButton("Edit"));
        buttonPanel.add(new JButton("Delete"));
        
        JPanel rightWrapper = new JPanel(new BorderLayout());
        rightWrapper.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
        rightWrapper.add(buttonPanel, BorderLayout.NORTH); // Taruh di atas
        
        panel.add(rightWrapper, BorderLayout.EAST);
        return panel;
    }

    private static JPanel createCommentsPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Comments"));

        // Model Data Comments
        String[] columnNames = {"Date", "User", "Comment"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        tableModel.addRow(new Object[]{"2025-10-26 10:15", "Toni", "Cat tembok sudah diolesi lapisan pertama. Menunggu kering sebelum lapisan kedua."});
        tableModel.addRow(new Object[]{"2025-11-03 16:45", "Toni", "Cuaca hujan deras selama 2 hari, area sedikit basah. Pemasangan ditunda besok pagi."});
        tableModel.addRow(new Object[]{"2025-11-15 14:30", "Goang", "Barang sudah sampai di lokasi. Mulai proses pembongkaran closet lama sore ini."});

        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Tombol Add, Edit (vertikal)
        JPanel buttonPanel = new JPanel(new GridLayout(2, 1, 0, 5));
        buttonPanel.add(new JButton("Add"));
        buttonPanel.add(new JButton("Edit"));

        JPanel rightWrapper = new JPanel(new BorderLayout());
        rightWrapper.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));
        rightWrapper.add(buttonPanel, BorderLayout.NORTH);

        panel.add(rightWrapper, BorderLayout.EAST);
        return panel;
    }
    
    private static JPanel createFooterPanel() {
        JPanel wrapperPanel = new JPanel(new BorderLayout());
        wrapperPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 10, 10));

        // Tombol Export di Kiri
        JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        leftPanel.add(new JButton("Export"));

        // Tombol Save/Cancel di Kanan
        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        rightPanel.add(new JButton("Cancel"));
        rightPanel.add(new JButton("Save"));

        wrapperPanel.add(leftPanel, BorderLayout.WEST);
        wrapperPanel.add(rightPanel, BorderLayout.EAST);

        return wrapperPanel;
    }
    
}