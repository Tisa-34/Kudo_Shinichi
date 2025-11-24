import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class ProjectDetailApp4 {

    public static void main(String[] args) {
        // Set Look and Feel to Nimbus for a more modern appearance
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            // If Nimbus is not available, you can set the GUI to another look and feel.
            try {
                UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
            } catch (Exception ex) {
                // ignore
            }
        }

        SwingUtilities.invokeLater(() -> createAndShowGUI());
    }

    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Detail Proyek RNV-JKT-AXG-001");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(950, 800);
        frame.setLocationRelativeTo(null); // Center the frame
        frame.setLayout(new BorderLayout());

        JPanel mainContentPanel = new JPanel(new GridBagLayout());
        mainContentPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Outer padding
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH; // Allow components to fill both horizontally and vertically
        gbc.insets = new Insets(5, 5, 5, 5); // Padding between major components

        // --- Header Panel (Client, Information, Additional Info) ---
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1.0; // Header panel should take all available horizontal space
        gbc.weighty = 0.0; // Header panel should not grow vertically
        mainContentPanel.add(createHeaderPanel(), gbc);

        // --- Product List Panel ---
        gbc.gridy = 1;
        gbc.weighty = 0.4; // Allow product list to take more vertical space
        mainContentPanel.add(createProductListPanel(), gbc);

        // --- Tasks Panel ---
        gbc.gridy = 2;
        gbc.weighty = 0.3; // Tasks panel takes some vertical space
        mainContentPanel.add(createTasksPanel(), gbc);

        // --- Comments Panel ---
        gbc.gridy = 3;
        gbc.weighty = 0.3; // Comments panel takes remaining vertical space
        mainContentPanel.add(createCommentsPanel(), gbc);

        JScrollPane scrollPane = new JScrollPane(mainContentPanel);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        frame.add(scrollPane, BorderLayout.CENTER);

        frame.add(createFooterPanel(), BorderLayout.SOUTH);

        frame.setVisible(true);
    }

    // ---------------------------------------------
    // --- HEADER COMPONENTS ---
    // ---------------------------------------------

    private static JPanel createHeaderPanel() {
        JPanel headerPanel = new JPanel(new GridLayout(1, 3, 10, 0)); // 1 row, 3 columns, 10px horizontal gap
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

        gbc.gridx = 0; gbc.gridy = 0; detail.add(new JLabel("Client ID:"), gbc);
        gbc.gridx = 1; gbc.weightx = 1.0; gbc.fill = GridBagConstraints.HORIZONTAL; detail.add(new JLabel("<html><b>101</b></html>"), gbc);

        gbc.gridx = 0; gbc.gridy = 1; detail.add(new JLabel("Name:"), gbc);
        gbc.gridx = 1; gbc.gridwidth = 2; detail.add(new JLabel("<html><b>Bapak Alex Gunawan</b></html>"), gbc);

        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 1; detail.add(new JLabel("Phone:"), gbc);
        gbc.gridx = 1; gbc.gridwidth = 2; detail.add(new JLabel("(+62) 8123456789"), gbc);

        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 1; detail.add(new JLabel("Registration No:"), gbc);
        gbc.gridx = 1; gbc.gridwidth = 2; detail.add(new JLabel("<html><b>RNV-JKT-AXG-001</b></html>"), gbc);
        
        // Add some vertical space before the button
        gbc.gridx = 0; gbc.gridy = 4; gbc.gridwidth = 3; gbc.weighty = 1.0; detail.add(Box.createVerticalGlue(), gbc);

        JPanel buttonWrapper = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0)); // No internal padding
        buttonWrapper.add(new JButton("Details"));
        
        panel.add(detail, BorderLayout.CENTER); // Use center to allow stretching
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

        // Row 1: Reserve days
        gbc.gridx = 0; gbc.gridy = 0; detail.add(new JLabel("Reserve days:"), gbc);
        gbc.gridx = 1; gbc.weightx = 1.0; gbc.fill = GridBagConstraints.HORIZONTAL; detail.add(new JTextField("0 of 30", 6), gbc);
        gbc.gridx = 2; gbc.weightx = 0.0; gbc.fill = GridBagConstraints.NONE; detail.add(new JButton("Add Days"), gbc);
        
        // Row 2 to 5: Buyer, Seller, Address, Credit Rating
        gbc.fill = GridBagConstraints.HORIZONTAL; gbc.gridwidth = 1;
        
        gbc.gridx = 0; gbc.gridy = 1; detail.add(new JLabel("Buyer:"), gbc);
        gbc.gridx = 1; gbc.gridwidth = 2; detail.add(new JLabel("Bapak Alex Gunawan"), gbc);
        
        gbc.gridx = 0; gbc.gridy = 2; gbc.gridwidth = 1; detail.add(new JLabel("Seller:"), gbc);
        gbc.gridx = 1; gbc.gridwidth = 2; detail.add(new JLabel("PT Bangun Jaya Abadi"), gbc);
        
        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 1; detail.add(new JLabel("Address:"), gbc);
        gbc.gridx = 1; gbc.gridwidth = 2; detail.add(new JLabel("Jl. Raya Lohbener Baru, 778A"), gbc);
        
        gbc.gridx = 0; gbc.gridy = 4; gbc.gridwidth = 1; detail.add(new JLabel("Credit Rating:"), gbc);
        gbc.gridx = 1; gbc.gridwidth = 1; detail.add(new JLabel("AAA"), gbc);
        gbc.gridx = 2; detail.add(new JButton("S&P Update"), gbc);

        // Row 6: Approved
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

        // Row 0: Estimated Close + Edit Button
        gbc.gridx = 0; gbc.gridy = 0; detail.add(new JLabel("Estimated close:"), gbc);
        gbc.gridx = 1; gbc.weightx = 1.0; gbc.fill = GridBagConstraints.HORIZONTAL; detail.add(new JLabel("2025-12-15"), gbc);
        gbc.gridx = 2; gbc.weightx = 0.0; gbc.fill = GridBagConstraints.NONE; detail.add(new JButton("Edit"), gbc);

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

        panel.add(detail, BorderLayout.NORTH); // NORTH to push content to top
        return panel;
    }

    // ---------------------------------------------
    // --- BODY (LISTS) COMPONENTS ---
    // ---------------------------------------------

    private static JPanel createProductListPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 0)); // Horizontal gap between table and buttons
        panel.setBorder(BorderFactory.createTitledBorder("Product List"));

        String[] columnNames = {"Renovation", "Description", "Part #", "Quantity", "List Price", "Price", "Wholesale Discount", "Wholesaler Price"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        tableModel.addRow(new Object[]{"Dapur", "Keramik Dinding Putih", "KW-PT-DLX-01", 50, 150000, 750000, 5, 7125000});
        tableModel.addRow(new Object[]{"Dapur", "Lem Keramik Instan", "LMI-GRY-STD", 5, 50000, 250000, 0, 250000}); // Index 1 for highlight
        tableModel.addRow(new Object[]{"Dapur", "Pipa PVC 3 inch", "PVC-3IN-STD", 12, 35000, 420000, 10, 378000});

        JTable table = new JTable(tableModel) {
            @Override
            public Component prepareRenderer(javax.swing.table.TableCellRenderer renderer, int row, int column) {
                Component c = super.prepareRenderer(renderer, row, column);
                if (row == 1) { // Highlight "Lem Keramik Instan" row
                    c.setBackground(new Color(153, 50, 204, 50)); // Light purple with alpha
                } else {
                    c.setBackground(Color.WHITE); // Default
                }
                return c;
            }
        };
        table.setFillsViewportHeight(true); // Table fills the height of its scrollpane

        JScrollPane scrollPane = new JScrollPane(table);
        panel.add(scrollPane, BorderLayout.CENTER);

        // Right Control Panel (Buttons and Subtotals)
        JPanel rightControlPanel = new JPanel(new GridBagLayout());
        rightControlPanel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 0)); // Padding to the left of buttons

        GridBagConstraints gbcRight = new GridBagConstraints();
        gbcRight.anchor = GridBagConstraints.NORTH; // Anchor buttons to top
        gbcRight.insets = new Insets(0, 0, 5, 0); // Spacing between buttons
        gbcRight.fill = GridBagConstraints.HORIZONTAL;

        // Buttons
        gbcRight.gridx = 0; gbcRight.gridy = 0; rightControlPanel.add(new JButton("Add"), gbcRight);
        gbcRight.gridy = 1; rightControlPanel.add(new JButton("Edit"), gbcRight);
        gbcRight.gridy = 2; rightControlPanel.add(new JButton("Delete"), gbcRight);

        // Spacer to push subtotals to bottom
        gbcRight.gridy = 3; gbcRight.weighty = 1.0; rightControlPanel.add(Box.createVerticalGlue(), gbcRight);

        // Subtotal Details (right aligned)
        JPanel subtotalDetail = new JPanel(new GridLayout(3, 1));
        subtotalDetail.add(new JLabel("Subtotal list price: Rp 15.570.000", SwingConstants.RIGHT));
        subtotalDetail.add(new JLabel("Total retailer price: Rp 15.182.500", SwingConstants.RIGHT));
        subtotalDetail.add(new JLabel("Total wholesaler price: Rp 14.249.625", SwingConstants.RIGHT));
        
        gbcRight.gridy = 4; gbcRight.weighty = 0.0; // Don't allow it to stretch
        gbcRight.fill = GridBagConstraints.HORIZONTAL; // Fill horizontally
        rightControlPanel.add(subtotalDetail, gbcRight);

        panel.add(rightControlPanel, BorderLayout.EAST);
        return panel;
    }

    private static JPanel createTasksPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 0));
        panel.setBorder(BorderFactory.createTitledBorder("Tasks"));

        String[] columnNames = {"State", "Task", "Assigner", "Executor", "Creation Date", "Estimated Date", "Executed Date"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        tableModel.addRow(new Object[]{"Completed", "Pemasangan pipa PVC di area garasi", "Warnoto", "Toni", "2025-10-20", "2025-10-21", "2025-10-21"});
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
    // --- FOOTER COMPONENTS ---
    // ---------------------------------------------
    
    private static JPanel createFooterPanel() {
        JPanel wrapperPanel = new JPanel(new BorderLayout());
        wrapperPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 10, 10)); // Padding

        // Export Button on the left
        JPanel leftPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        leftPanel.add(new JButton("Export"));

        // Save/Cancel Buttons on the right
        JPanel rightPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        rightPanel.add(new JButton("Cancel"));
        rightPanel.add(new JButton("Save"));

        wrapperPanel.add(leftPanel, BorderLayout.WEST);
        wrapperPanel.add(rightPanel, BorderLayout.EAST);

        return wrapperPanel;
    }
}
