import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;

public class ProjectDetailApp5
 {

    public static void main(String[] args) {
        // Set Look and Feel to Nimbus for a modern appearance
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            // Fallback to default L&F if Nimbus fails
            // System.out.println("Nimbus L&F not available.");
        }

        SwingUtilities.invokeLater(() -> createAndShowGUI());
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Detail Proyek RNV-JKT-AXG-001");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 850); // Ukuran sedikit diperbesar
        frame.setLocationRelativeTo(null);
        frame.setLayout(new BorderLayout());

        // --- Panel Konten Utama (GridBagLayout untuk Responsif Vertikal) ---
        JPanel mainContentPanel = new JPanel(new GridBagLayout());
        mainContentPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); 
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH; 
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.weightx = 1.0; 

        // 1. Header Panel (Tidak responsif vertikal)
        gbc.gridx = 0; gbc.gridy = 0; gbc.weighty = 0.0;
        mainContentPanel.add(createHeaderPanel(), gbc);

        // 2. Product List Panel (Paling responsif, ambil ruang 40%)
        gbc.gridy = 1; gbc.weighty = 0.4;
        mainContentPanel.add(createProductListPanel(), gbc);

        // 3. Tasks Panel (Ambil ruang 30%)
        gbc.gridy = 2; gbc.weighty = 0.3;
        mainContentPanel.add(createTasksPanel(), gbc);

        // 4. Comments Panel (Ambil ruang 30%)
        gbc.gridy = 3; gbc.weighty = 0.3;
        mainContentPanel.add(createCommentsPanel(), gbc);

        JScrollPane scrollPane = new JScrollPane(mainContentPanel);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        frame.add(scrollPane, BorderLayout.CENTER);

        frame.add(createFooterPanel(), BorderLayout.SOUTH);

        frame.setVisible(true);
    }
    
    // ---------------------------------------------
    // --- KOMPONEN HEADER ---
    // ---------------------------------------------

    private static JPanel createHeaderPanel() {
        // GridLayout: 1 baris, 3 kolom. Memberikan bobot rata ke setiap panel
        JPanel headerPanel = new JPanel(new GridLayout(1, 3, 10, 0)); 
        headerPanel.add(createClientPanel());
        headerPanel.add(createInfoPanel());
        headerPanel.add(createAdditionalInfoPanel());
        return headerPanel;
    }

    private static JPanel createClientPanel() {
        JPanel panel = new JPanel(new BorderLayout(5, 5));
        panel.setBorder(BorderFactory.createTitledBorder("Client"));
        
        JPanel detail = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(2, 2, 2, 2);
        gbc.fill = GridBagConstraints.HORIZONTAL; // Label mengisi ruang horizontal

        // Set bold text for all values
        String boldStart = "<html><b>";
        String boldEnd = "</b></html>";

        gbc.gridx = 0; gbc.gridy = 0; gbc.weightx = 0.3; detail.add(new JLabel("Client ID:"), gbc);
        gbc.gridx = 1; gbc.weightx = 0.7; detail.add(new JLabel(boldStart + "102" + boldEnd), gbc);

        gbc.gridx = 0; gbc.gridy = 1; gbc.weightx = 0.3; detail.add(new JLabel("Name:"), gbc);
        gbc.gridx = 1; gbc.weightx = 0.7; detail.add(new JLabel(boldStart + "Bapak Alex Gunawan" + boldEnd), gbc);

        gbc.gridx = 0; gbc.gridy = 2; gbc.weightx = 0.3; detail.add(new JLabel("Phone:"), gbc);
        gbc.gridx = 1; gbc.weightx = 0.7; detail.add(new JLabel(boldStart + "(+62) 8123456789" + boldEnd), gbc);

        gbc.gridx = 0; gbc.gridy = 3; gbc.weightx = 0.3; detail.add(new JLabel("Registration No:"), gbc);
        gbc.gridx = 1; gbc.weightx = 0.7; detail.add(new JLabel(boldStart + "RNV-JKT-AXG-001" + boldEnd), gbc);
        
        // Spacer (memastikan detail di atas menempel di atas)
        gbc.gridx = 0; gbc.gridy = 4; gbc.weighty = 1.0; detail.add(Box.createVerticalGlue(), gbc);

        // Tombol Details (Di tempatkan di Selatan dan mengisi penuh)
        JButton detailsButton = new JButton("Details");
        detailsButton.setBackground(new Color(153, 50, 204)); // Warna Ungu
        detailsButton.setForeground(Color.WHITE);
        detailsButton.setFont(detailsButton.getFont().deriveFont(Font.BOLD, 12));
        
        JPanel buttonWrapper = new JPanel(new GridLayout(1, 1)); // Paksa tombol mengisi lebar wrapper
        buttonWrapper.setBorder(BorderFactory.createEmptyBorder(5, 0, 0, 0)); // Jarak ke detail di atas
        buttonWrapper.add(detailsButton);
        
        panel.add(detail, BorderLayout.CENTER);
        panel.add(buttonWrapper, BorderLayout.SOUTH);

        return panel;
    }

    private static JPanel createInfoPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Information"));
        
        JPanel detail = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(2, 2, 2, 2);

        // Baris 1: Reserve days
        gbc.gridx = 0; gbc.gridy = 0; detail.add(new JLabel("Reserve days:"), gbc);
        gbc.gridx = 1; gbc.weightx = 1.0; gbc.fill = GridBagConstraints.HORIZONTAL; detail.add(new JTextField("0 of 20", 8), gbc);
        gbc.gridx = 2; gbc.weightx = 0.0; gbc.fill = GridBagConstraints.NONE; detail.add(new JButton("Add Days"), gbc);
        
        // Baris 2 sampai 5: Buyer, Seller, Address, Credit Rating
        gbc.fill = GridBagConstraints.HORIZONTAL; gbc.gridwidth = 1;
        
        gbc.gridx = 0; gbc.gridy = 1; detail.add(new JLabel("Buyer:"), gbc);
        gbc.gridx = 1; gbc.gridwidth = 2; detail.add(new JLabel("<html><b>Bapak Ales Gunawan</b></html>"), gbc); // Sesuai gambar
        
        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 1; detail.add(new JLabel("Seller:"), gbc);
        gbc.gridx = 1; gbc.gridwidth = 2; detail.add(new JLabel("PT Bangun Jaya Abadi"), gbc);
        
        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 1; detail.add(new JLabel("Address:"), gbc);
        gbc.gridx = 1; gbc.gridwidth = 2; detail.add(new JLabel("Jl. Raya Lohbenern.177&A"), gbc);
        
        gbc.gridx = 0; gbc.gridy = 4; gbc.gridwidth = 1; detail.add(new JLabel("Credit rating:"), gbc);
        gbc.gridx = 1; gbc.gridwidth = 1; detail.add(new JLabel("AAA"), gbc);
        gbc.gridx = 2; detail.add(new JButton("S&P Update"), gbc);

        // Baris 6: Approved
        gbc.gridx = 0; gbc.gridy = 5; gbc.gridwidth = 3; 
        detail.add(new JCheckBox("Approved: Proyet renovasi telah disetujui, siap dimulai"), gbc);

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

        // Baris 0: Estimated Close + Edit Button
        gbc.gridx = 0; gbc.gridy = 0; detail.add(new JLabel("Estimated close:"), gbc);
        gbc.gridx = 1; gbc.weightx = 1.0; gbc.fill = GridBagConstraints.HORIZONTAL; detail.add(new JLabel("2025-12-15"), gbc);
        gbc.gridx = 2; gbc.weightx = 0.0; gbc.fill = GridBagConstraints.NONE; detail.add(new JButton("Edit"), gbc);

        String[][] data = {
            {"Creation date:", "2025-10-15"},
            {"Created by:", "<html><b>Admin</b></html>"},
            {"Last edit date:", "2025-11-18"}, // Sesuai gambar
            {"Last edited by:", "<html><b>Admin</b></html>"}, // Sesuai gambar
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
    
    // ---------------------------------------------
    // --- KOMPONEN BODY (LISTS) ---
    // ---------------------------------------------

    private static JPanel createProductListPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 0)); 
        panel.setBorder(BorderFactory.createTitledBorder("Product List"));
        
        // Model Data Produk
        String[] columnNames = {"Renovation", "Description", "Part #", "Quantity", "List price", "Discount", "Price", "Wholesale Discount", "Wholesaler Price"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        tableModel.addRow(new Object[]{"Dapur", "Keramik Dinding Putih", "KW-PT-DLX-01", 50, 150000, 0, 750000, 5, 250000}); // Data dummy
        tableModel.addRow(new Object[]{"Dapur", "Lem Keramik instan", "LMI-GRY-STD", 5, 50000, 0, 250000, 0, 373000});
        tableModel.addRow(new Object[]{"Dapur", "Pipa PVC 3 inch", "PVC-3IN-STD", 12, 35000, 0, 420000, 10, 7125000});

        JTable table = new JTable(tableModel);
        
        // Custom Renderer untuk baris terpilih (Ungu)
        table.setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            private final Color HIGHLIGHT_COLOR = new Color(153, 50, 204, 50); // Ungu muda transparan

            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);

                // Baris kedua (indeks 1) di-highlight (Lem Keramik Instan)
                if (row == 1) { 
                    c.setBackground(HIGHLIGHT_COLOR);
                } else if (!isSelected) {
                    c.setBackground(Color.WHITE);
                } else {
                    // Jika baris lain dipilih
                    c.setBackground(table.getSelectionBackground());
                }
                return c;
            }
        });
        
        table.setFillsViewportHeight(true); 

        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Right Control Panel (Buttons and Subtotals)
        JPanel rightControlPanel = new JPanel(new GridBagLayout());
        rightControlPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));

        GridBagConstraints gbcRight = new GridBagConstraints();
        gbcRight.anchor = GridBagConstraints.NORTH; 
        gbcRight.insets = new Insets(0, 0, 5, 0); 
        gbcRight.fill = GridBagConstraints.HORIZONTAL;

        // Tombol
        gbcRight.gridx = 0; gbcRight.gridy = 0; rightControlPanel.add(new JButton("Add"), gbcRight);
        gbcRight.gridy = 1; rightControlPanel.add(new JButton("Edit"), gbcRight);
        gbcRight.gridy = 2; rightControlPanel.add(new JButton("Delete"), gbcRight);

        // Spacer untuk mendorong subtotal ke bawah
        gbcRight.gridy = 3; gbcRight.weighty = 1.0; rightControlPanel.add(Box.createVerticalGlue(), gbcRight);

        // Detail Subtotal (Right aligned)
        JPanel subtotalDetail = new JPanel(new GridLayout(3, 1));
        subtotalDetail.add(new JLabel("Subtotal list price: Rp 15.590.800", SwingConstants.RIGHT));
        subtotalDetail.add(new JLabel("Total retailer price: Rp 15.182.500", SwingConstants.RIGHT));
        subtotalDetail.add(new JLabel("Total wholesaler price: Rp 10.149.025", SwingConstants.RIGHT));
        
        gbcRight.gridy = 4; gbcRight.weighty = 0.0; 
        gbcRight.fill = GridBagConstraints.HORIZONTAL; 
        rightControlPanel.add(subtotalDetail, gbcRight);

        panel.add(rightControlPanel, BorderLayout.EAST);
        return panel;
    }

    private static JPanel createTasksPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 0));
        panel.setBorder(BorderFactory.createTitledBorder("Tasks"));

        String[] columnNames = {"State", "Task", "Assigner", "Executor", "Creation Date", "Estimated Date", "Executed Date"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        tableModel.addRow(new Object[]{"Completed", "Pemasangan pipa PVC di area garasi", "Warnoto", "Goang", "2025-10-20", "2025-10-21", "2025-10-21"});
        tableModel.addRow(new Object[]{"Completed", "Pengecatan ulang ruang tamu", "Warnoto", "Toni", "2025-10-25", "2025-10-28", "2025-10-27"});
        tableModel.addRow(new Object[]{"Delayed", "Pemasangan keramik dinding dapur", "Warnoto", "Toni", "2025-11-01", "2025-11-04", null});

        JTable table = new JTable(tableModel);
        table.setFillsViewportHeight(true);
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Right Control Panel (Buttons)
        JPanel rightControlPanel = new JPanel(new GridBagLayout());
        rightControlPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));

        GridBagConstraints gbcRight = new GridBagConstraints();
        gbcRight.anchor = GridBagConstraints.NORTH;
        gbcRight.insets = new Insets(0, 0, 5, 0);
        gbcRight.fill = GridBagConstraints.HORIZONTAL;

        gbcRight.gridx = 0; gbcRight.gridy = 0; rightControlPanel.add(new JButton("Add"), gbcRight);
        gbcRight.gridy = 1; rightControlPanel.add(new JButton("Edit"), gbcRight);
        gbcRight.gridy = 2; rightControlPanel.add(new JButton("Delete"), gbcRight);
        gbcRight.gridy = 3; gbcRight.weighty = 1.0; rightControlPanel.add(Box.createVerticalGlue(), gbcRight); // Spacer

        panel.add(rightControlPanel, BorderLayout.EAST);
        return panel;
    }

    private static JPanel createCommentsPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 0));
        panel.setBorder(BorderFactory.createTitledBorder("Comments"));

        String[] columnNames = {"Date", "User", "Comment"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        tableModel.addRow(new Object[]{"2025-10-26 10:15", "Toni", "Cat tembok sudah diolesi lapisan pertama. Menunggu kering sebelum lapisan kedua."});
        tableModel.addRow(new Object[]{"2025-11-03 16:45", "Toni", "Cuaca hujan deras selama 2 hari, area sedikit basah. Pemasangan ditunda besok pagi."});
        tableModel.addRow(new Object[]{"2025-11-15 14:30", "Goang", "Barang sudah sampai di lokasi. Mulai proses pembongkaran closet lama sore ini."});

        JTable table = new JTable(tableModel);
        table.setFillsViewportHeight(true);
        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Right Control Panel (Buttons)
        JPanel rightControlPanel = new JPanel(new GridBagLayout());
        rightControlPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0));

        GridBagConstraints gbcRight = new GridBagConstraints();
        gbcRight.anchor = GridBagConstraints.NORTH;
        gbcRight.insets = new Insets(0, 0, 5, 0);
        gbcRight.fill = GridBagConstraints.HORIZONTAL;

        gbcRight.gridx = 0; gbcRight.gridy = 0; rightControlPanel.add(new JButton("Add"), gbcRight);
        gbcRight.gridy = 1; rightControlPanel.add(new JButton("Edit"), gbcRight);
        gbcRight.gridy = 2; gbcRight.weighty = 1.0; rightControlPanel.add(Box.createVerticalGlue(), gbcRight); // Spacer

        panel.add(rightControlPanel, BorderLayout.EAST);
        return panel;
    }

    // ---------------------------------------------
    // --- KOMPONEN FOOTER ---
    // ---------------------------------------------
    
    private static JPanel createFooterPanel() {
        JPanel wrapperPanel = new JPanel(new BorderLayout());
        wrapperPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 10, 10));

        // Tombol Export (Kiri)
        JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JButton exportButton = new JButton("Export");
        leftPanel.add(exportButton);

        // Tombol Save/Cancel (Kanan)
        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        rightPanel.add(new JButton("Cancel"));
        
        // Tombol Save (Warna Ungu)
        JButton saveButton = new JButton("Save");
        saveButton.setBackground(new Color(153, 50, 204)); // Warna Ungu
        saveButton.setForeground(Color.WHITE);
        saveButton.setFont(saveButton.getFont().deriveFont(Font.BOLD));
        rightPanel.add(saveButton);

        wrapperPanel.add(leftPanel, BorderLayout.WEST);
        wrapperPanel.add(rightPanel, BorderLayout.EAST);

        return wrapperPanel;
    }
}