import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import net.miginfocom.swing.MigLayout; // Asumsi penggunaan MigLayout untuk tata letak

public class ProjectDetailUI5 extends JFrame {

    public ProjectDetailUI5() {
        // Konfigurasi Frame Utama
        setTitle("Detail Proyek: RNV-JKT-AXG-001");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Menggunakan MigLayout untuk Panel Utama
        // Layout: 3 Kolom (Col 1, Col 2, Col 3) dan 5 Baris (Row 1-5)
        // Kolom 1: Lebar minimal (min)
        // Kolom 2: Lebar yang tumbuh (grow)
        // Kolom 3: Lebar minimal (min)
        // Baris 1: Min, Baris 2-4: Tumbuh (grow), Baris 5: Min
        JPanel mainPanel = new JPanel(new MigLayout(
                "fill", // Layout Constraints
                "[min!][grow, fill][min!]", // Column Constraints (Col 1, Col 2, Col 3)
                "[min!][grow, fill][grow, fill][grow, fill][min!]" // Row Constraints (Row 1-5)
        ));
        
        // --- Row 1 Components ---
        
        // 1. Panel Client (Col 1, Row 1)
        JPanel panelClient = createClientPanel();
        mainPanel.add(panelClient, "cell 0 0, grow"); 

        // 2. Panel Information (Col 2, Row 1)
        JPanel panelInformation = createInformationPanel();
        mainPanel.add(panelInformation, "cell 1 0, grow"); 

        // 3. Panel Additional Information (Col 3, Row 1)
        JPanel panelAdditionalInformation = createAdditionalInformationPanel();
        mainPanel.add(panelAdditionalInformation, "cell 2 0, grow");
        
        // --- Row 2: Panel Product List --- (span 3 columns)
        JPanel panelProductList = createProductListPanel();
        mainPanel.add(panelProductList, "cell 0 1 3 1, grow"); 

        // --- Row 3: Panel Tasks --- (span 3 columns)
        JPanel panelTasks = createTasksPanel();
        mainPanel.add(panelTasks, "cell 0 2 3 1, grow");

        // --- Row 4: Panel Comments --- (span 3 columns)
        JPanel panelComments = createCommentsPanel();
        mainPanel.add(panelComments, "cell 0 3 3 1, grow");

        // --- Row 5: Panel Controls --- (span 3 columns)
        JPanel panelControls = createControlsPanel();
        mainPanel.add(panelControls, "cell 0 4 3 1, grow");

        // Tambahkan panel utama ke frame
        setContentPane(mainPanel);
        pack();
        setLocationRelativeTo(null); // Posisikan di tengah layar
    }

    /**
     * Membuat Panel Client (Col 1, Row 1)
     */
    private JPanel createClientPanel() {
        JPanel panel = new JPanel(new MigLayout("wrap 2"));
        panel.setBorder(BorderFactory.createTitledBorder("Client")); // TitledBorder 

        panel.add(new JLabel("Client ID:"), "align right");
        panel.add(new JLabel("101")); // [cite: 337]

        panel.add(new JLabel("Name:"), "align right");
        panel.add(new JLabel("Bapak Alex Gunawan")); // [cite: 338]

        panel.add(new JLabel("Phone:"), "align right");
        panel.add(new JLabel("(+62) 8123456789")); // [cite: 339]

        panel.add(new JLabel("Registration No:"), "align right");
        panel.add(new JLabel("RNV-JKT-AXG-001")); // [cite: 339]

        panel.add(new JButton("Details"), "span 2, align right");

        return panel;
    }

    /**
     * Membuat Panel Information (Col 2, Row 1)
     */
    private JPanel createInformationPanel() {
        JPanel panel = new JPanel(new MigLayout("wrap 3, insets 5 5 5 5"));
        panel.setBorder(BorderFactory.createTitledBorder("Information")); // TitledBorder 

        panel.add(new JLabel("Reserve days:"), "align right");
        panel.add(new JLabel("0 of 30"), "growx"); // [cite: 342]
        panel.add(new JButton("Add Days"), "wrap"); // [cite: 352]

        panel.add(new JLabel("Buyer:"), "align right");
        panel.add(new JLabel("Bapak Alex Gunawan"), "span 2, growx"); // [cite: 344]

        panel.add(new JLabel("Seller:"), "align right");
        panel.add(new JLabel("PT Bangun Jaya Abadi"), "span 2, growx"); // [cite: 345]

        panel.add(new JLabel("Address:"), "align right");
        panel.add(new JLabel("Jl. Raya Lohbener Baru, 778A"), "span 2, growx"); // [cite: 347]

        panel.add(new JLabel("Credit rating:"), "align right");
        panel.add(new JLabel("AAA"), "growx"); // [cite: 347]
        panel.add(new JButton("S&P Update"), "wrap"); // [cite: 360]

        JCheckBox approvedCheckbox = new JCheckBox("Approved:"); // JCheckBox 
        approvedCheckbox.setSelected(true); // Asumsi sudah tercentang
        approvedCheckbox.setEnabled(false);
        panel.add(approvedCheckbox, "align right");
        panel.add(new JLabel("Proyek renovasi telah disetujui, siap dimulai"), "span 2, growx"); // [cite: 348]

        return panel;
    }

    /**
     * Membuat Panel Additional Information (Col 3, Row 1)
     */
    private JPanel createAdditionalInformationPanel() {
        JPanel panel = new JPanel(new MigLayout("wrap 2"));
        panel.setBorder(BorderFactory.createTitledBorder("Additional Information")); // TitledBorder 

        panel.add(new JLabel("Estimated close:"), "align right");
        panel.add(new JLabel("2025-12-15")); // [cite: 354]
        
        panel.add(new JLabel("Creation date:"), "align right");
        panel.add(new JLabel("2025-10-15")); // [cite: 356]

        panel.add(new JLabel("Created by:"), "align right");
        panel.add(new JLabel("Admin")); // [cite: 357]

        panel.add(new JLabel("Last edit date:"), "align right");
        panel.add(new JLabel("2025-11-16")); // [cite: 358]

        panel.add(new JLabel("Last edited by:"), "align right");
        panel.add(new JLabel("Warnoto")); // [cite: 359]

        panel.add(new JLabel("Closed date:"), "align right");
        panel.add(new JLabel("null")); // [cite: 361]

        panel.add(new JLabel("Closed by:"), "align right");
        panel.add(new JLabel("null")); // [cite: 362]
        
        panel.add(new JButton("Edit"), "span 2, align right"); // [cite: 355]

        return panel;
    }

    /**
     * Membuat Panel Product List (Row 2)
     */
    private JPanel createProductListPanel() {
        JPanel panel = new JPanel(new MigLayout("fill"));
        panel.setBorder(BorderFactory.createTitledBorder("Product List")); // TitledBorder 

        // Data untuk JTable
        String[] columnNames = {"Renovation", "Description", "Part #", "Quantity", "List Price", "Discount", "Price", "Wholesale Discount", "Wholesaler Price"};
        Object[][] data = {
            {"Dapur", "Keramik Dinding Putih", "KW-PT-DLX-01", "50", "150000", "0", "7500000", "5", "7125000"}, // [cite: 364]
            {"Dapur", "Lem Keramik Instan", "LMI-GRY-STD", "5", "50000", "0", "250000", "0", "250000"}, // [cite: 364]
            {"Dapur", "Pipa PVC 3 inch", "PVC-3IN-STD", "12", "35000", "0", "420000", "10", "378000"} // [cite: 364]
        };
        
        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        JTable productTable = new JTable(model); // JTable 
        JScrollPane scrollPane = new JScrollPane(productTable); // JScrollPane 
        
        JPanel buttonPanel = new JPanel(new MigLayout("wrap 1, aligny top"));
        buttonPanel.add(new JButton("Add")); // [cite: 365]
        buttonPanel.add(new JButton("Edit")); // [cite: 366]
        buttonPanel.add(new JButton("Delete")); // [cite: 367]

        panel.add(scrollPane, "grow, push");
        panel.add(buttonPanel, "growy");
        
        // Panel untuk Subtotal di bawah tabel
        JPanel subtotalPanel = new JPanel(new MigLayout("wrap 2, align right"));
        subtotalPanel.add(new JLabel("Subtotal list price:"), "align right");
        subtotalPanel.add(new JLabel("Rp 15.570.000")); // [cite: 368, 369]
        
        subtotalPanel.add(new JLabel("Total retailer price:"), "align right");
        subtotalPanel.add(new JLabel("Rp 15.182.500")); // [cite: 370, 371]
        
        subtotalPanel.add(new JLabel("Total wholesaler price:"), "align right");
        subtotalPanel.add(new JLabel("Rp 14.249.625")); // [cite: 372, 373]
        
        panel.add(subtotalPanel, "newline, span, align right");

        return panel;
    }

    /**
     * Membuat Panel Tasks (Row 3)
     */
    private JPanel createTasksPanel() {
        JPanel panel = new JPanel(new MigLayout("fill"));
        panel.setBorder(BorderFactory.createTitledBorder("Tasks")); // TitledBorder 

        // Data untuk JTable Tasks
        String[] columnNames = {"State", "Task", "Assigner", "Executer", "Creation Date", "Estimated Date", "Executed Date"};
        Object[][] data = {
            {"Completed", "Pemasangan pipa PVC di area garasi", "Warnoto", "Goang", "2025-10-20", "2025-10-21", "2025-10-21"}, // [cite: 383, 384, 386, 387, 388, 389]
            {"Completed", "Pengecatan ulang ruang tamu", "Warnoto", "Toni", "2025-10-25", "2025-10-28", "2025-10-27"}, // [cite: 383, 385, 386, 391, 392, 393]
            {"Delayed", "Pemasangan keramik dinding dapur", "Warnoto", "Toni", "2025-11-01", "2025-11-04", ""} // [cite: 383, 394, 395, 396, 397, 398]
        };
        
        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        JTable tasksTable = new JTable(model); // JTable 
        JScrollPane scrollPane = new JScrollPane(tasksTable); // JScrollPane 
        
        JPanel buttonPanel = new JPanel(new MigLayout("wrap 1, aligny top"));
        buttonPanel.add(new JButton("Add")); // [cite: 382]
        buttonPanel.add(new JButton("Edit")); // [cite: 390]
        buttonPanel.add(new JButton("Delete")); // [cite: 399]

        panel.add(scrollPane, "grow, push");
        panel.add(buttonPanel, "growy");

        return panel;
    }

    /**
     * Membuat Panel Comments (Row 4)
     */
    private JPanel createCommentsPanel() {
        JPanel panel = new JPanel(new MigLayout("fill"));
        panel.setBorder(BorderFactory.createTitledBorder("Comments")); // TitledBorder 

        // Data untuk JTable Comments
        String[] columnNames = {"Date", "User", "Comment"};
        Object[][] data = {
            {"2025-10-26 10:15", "Toni", "Cat tembok sudah diolesi lapisan pertama. Menunggu kering sebelum lapisan kedua."}, // [cite: 405, 406, 411]
            {"2025-11-03 16:45", "Toni", "Cuaca hujan deras selama 2 hari, area kerja sedikit basah. Pemasangan ditunda besok pagi. Barang sudah sampai di lokasi."}, // [cite: 407, 408, 412]
            {"2025-11-15 14:30", "Goang", "Mulai proses pembongkaran closet lama sore ini."} // [cite: 409, 410, 413]
        };
        
        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        JTable commentsTable = new JTable(model); // JTable 
        JScrollPane scrollPane = new JScrollPane(commentsTable); // JScrollPane 
        
        JPanel buttonPanel = new JPanel(new MigLayout("wrap 1, aligny top"));
        buttonPanel.add(new JButton("Add")); // [cite: 404]
        buttonPanel.add(new JButton("Edit")); // [cite: 414]
        buttonPanel.add(new JButton("Delete")); // [cite: 415]

        panel.add(scrollPane, "grow, push");
        panel.add(buttonPanel, "growy");

        return panel;
    }

    /**
     * Membuat Panel Controls (Row 5)
     */
    private JPanel createControlsPanel() {
        JPanel panel = new JPanel(new MigLayout("align right, insets 5 5 5 5"));

        panel.add(new JButton("Export")); // [cite: 416]
        panel.add(new JSeparator(SwingConstants.VERTICAL)); // JSeparator 
        panel.add(new JButton("Save")); // [cite: 419]
        panel.add(new JButton("Cancel")); // [cite: 420]

        return panel;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                // Mengatur Look and Feel menjadi system Look and Feel
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            new ProjectDetailUI().setVisible(true);
        });
    }
}