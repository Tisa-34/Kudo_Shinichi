import com.formdev.flatlaf.FlatLightLaf;
import net.miginfocom.swing.MigLayout;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.Vector;
import java.util.Arrays; // Import untuk Arrays

public class ProjectDetailUI6 extends JFrame {

    private DefaultTableModel productTableModel;
    private DefaultTableModel tasksTableModel;
    private DefaultTableModel commentsTableModel;

    public ProjectDetailUI6() {
        // --- 1. SET FLATLAF ---
        try {
            FlatLightLaf.setup();
        } catch (Exception ex) {
            System.err.println("Failed to initialize FlatLaf: " + ex);
        }
        // ----------------------

        // --- Konfigurasi Frame Utama ---
        setTitle("Detail Proyek: RNV-JKT-AXG-001");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // MigLayout Constraints menggunakan String Literal
        JPanel mainPanel = new JPanel(new MigLayout(
                "fill, insets 10",
                "[min!][grow, fill][min!]", // Column Constraints
                "[min!][grow, fill][grow, fill][grow, fill][min!]" // Row Constraints
        ));

        // --- Layout Penempatan Panel ---
        mainPanel.add(createClientPanel(), "cell 0 0, grow");
        mainPanel.add(createInformationPanel(), "cell 1 0, grow");
        mainPanel.add(createAdditionalInformationPanel(), "cell 2 0, grow");

        mainPanel.add(createProductListPanel(), "cell 0 1 3 1, grow");
        mainPanel.add(createTasksPanel(), "cell 0 2 3 1, grow");
        mainPanel.add(createCommentsPanel(), "cell 0 3 3 1, grow");

        mainPanel.add(createControlsPanel(), "cell 0 4 3 1, grow");

        setContentPane(mainPanel);
        pack();
        setLocationRelativeTo(null);
    }

    // =========================================================================
    //                            PANEL CREATION METHODS
    // =========================================================================

    private JPanel createClientPanel() {
        JPanel panel = new JPanel(new MigLayout("wrap 2, insets 5 5 5 5"));
        panel.setBorder(BorderFactory.createTitledBorder("Client"));
        panel.add(new JLabel("Client ID:"), "align right"); panel.add(new JLabel("101"));
        panel.add(new JLabel("Name:"), "align right"); panel.add(new JLabel("Bapak Alex Gunawan"));
        panel.add(new JLabel("Phone:"), "align right"); panel.add(new JLabel("(+62) 8123456789"));
        panel.add(new JLabel("Registration No:"), "align right"); panel.add(new JLabel("RNV-JKT-AXG-001"));
        panel.add(new JButton("Details"), "span 2, align right");
        return panel;
    }

    private JPanel createInformationPanel() {
        JPanel panel = new JPanel(new MigLayout("wrap 3, insets 5 5 5 5"));
        panel.setBorder(BorderFactory.createTitledBorder("Information"));
        panel.add(new JLabel("Reserve days:"), "align right"); panel.add(new JLabel("0 of 30"), "growx");
        panel.add(new JButton("Add Days"), "wrap");
        panel.add(new JLabel("Buyer:"), "align right"); panel.add(new JLabel("Bapak Alex Gunawan"), "span 2, growx");
        panel.add(new JLabel("Seller:"), "align right"); panel.add(new JLabel("PT Bangun Jaya Abadi"), "span 2, growx");
        panel.add(new JLabel("Address:"), "align right"); panel.add(new JLabel("Jl. Raya Lohbener Baru, 778A"), "span 2, growx");
        panel.add(new JLabel("Credit rating:"), "align right"); panel.add(new JLabel("AAA"), "growx");
        panel.add(new JButton("S&P Update"), "wrap");
        JCheckBox approvedCheckbox = new JCheckBox("Approved:");
        approvedCheckbox.setSelected(true); approvedCheckbox.setEnabled(false);
        panel.add(approvedCheckbox, "align right");
        panel.add(new JLabel("Proyek renovasi telah disetujui, siap dimulai"), "span 2, growx");
        return panel;
    }

    private JPanel createAdditionalInformationPanel() {
        JPanel panel = new JPanel(new MigLayout("wrap 2, insets 5 5 5 5"));
        panel.setBorder(BorderFactory.createTitledBorder("Additional Information"));
        panel.add(new JLabel("Estimated close:"), "align right"); panel.add(new JLabel("2025-12-15"));
        panel.add(new JLabel("Creation date:"), "align right"); panel.add(new JLabel("2025-10-15"));
        panel.add(new JLabel("Created by:"), "align right"); panel.add(new JLabel("Admin"));
        panel.add(new JLabel("Last edit date:"), "align right"); panel.add(new JLabel("2025-11-16"));
        panel.add(new JLabel("Last edited by:"), "align right"); panel.add(new JLabel("Warnoto"));
        panel.add(new JLabel("Closed date:"), "align right"); panel.add(new JLabel("null"));
        panel.add(new JLabel("Closed by:"), "align right"); panel.add(new JLabel("null"));
        panel.add(new JButton("Edit"), "span 2, align right");
        return panel;
    }

    /**
     * Membuat Panel Product List (Row 2) dengan interaksi CRUD.
     */
    private JPanel createProductListPanel() {
        JPanel panel = new JPanel(new MigLayout("fill"));
        panel.setBorder(BorderFactory.createTitledBorder("Product List"));

        // Definisi Column Names (Menggunakan Tanda Kurung Kurawal {})
        String[] columnNames = {"Renovation", "Description", "Part #", "Quantity", "List Price", "Discount", "Price", "Wholesale Discount", "Wholesaler Price"};
        
        // Data awal
        Vector<Vector<Object>> data = new Vector<>();
        data.add(createProductRow("Dapur", "Keramik Dinding Putih", "KW-PT-DLX-01", 50, 150000, 0, 7500000, 5, 7125000));
        data.add(createProductRow("Dapur", "Lem Keramik Instan", "LMI-GRY-STD", 5, 50000, 0, 250000, 0, 250000));
        data.add(createProductRow("Dapur", "Pipa PVC 3 inch", "PVC-3IN-STD", 12, 35000, 0, 420000, 10, 378000));

        // Inisialisasi Model
        productTableModel = new DefaultTableModel(data, new Vector<>(Arrays.asList(columnNames)));
        JTable productTable = new JTable(productTableModel);
        JScrollPane scrollPane = new JScrollPane(productTable);

        // Panel Tombol
        JPanel buttonPanel = new JPanel(new MigLayout("wrap 1, aligny top"));
        JButton btnAdd = new JButton("Add");
        JButton btnEdit = new JButton("Edit");
        JButton btnDelete = new JButton("Delete");
        buttonPanel.add(btnAdd);
        buttonPanel.add(btnEdit);
        buttonPanel.add(btnDelete);

        // --- Interaksi CRUD ---
        btnAdd.addActionListener(e -> addProduct(productTable));
        btnEdit.addActionListener(e -> editProduct(productTable));
        btnDelete.addActionListener(e -> deleteRow(productTable, productTableModel));
        // -----------------------

        panel.add(scrollPane, "grow, push");
        panel.add(buttonPanel, "growy");

        // Panel Subtotal
        JPanel subtotalPanel = new JPanel(new MigLayout("wrap 2, align right"));
        subtotalPanel.add(new JLabel("Subtotal list price:"), "align right"); subtotalPanel.add(new JLabel("Rp 15.570.000"));
        subtotalPanel.add(new JLabel("Total retailer price:"), "align right"); subtotalPanel.add(new JLabel("Rp 15.182.500"));
        subtotalPanel.add(new JLabel("Total wholesaler price:"), "align right"); subtotalPanel.add(new JLabel("Rp 14.249.625"));
        panel.add(subtotalPanel, "newline, span, align right");

        return panel;
    }

    /**
     * Membuat Panel Tasks (Row 3) dengan interaksi CRUD.
     */
    private JPanel createTasksPanel() {
        JPanel panel = new JPanel(new MigLayout("fill"));
        panel.setBorder(BorderFactory.createTitledBorder("Tasks"));

        // Definisi Column Names (Menggunakan Tanda Kurung Kurawal {})
        String[] columnNames = {"State", "Task", "Assigner", "Executer", "Creation Date", "Estimated Date", "Executed Date"};
        
        // Data awal
        Vector<Vector<Object>> data = new Vector<>();
        data.add(createTaskRow("Completed", "Pemasangan pipa PVC di area garasi", "Warnoto", "Goang", "2025-10-20", "2025-10-21", "2025-10-21"));
        data.add(createTaskRow("Completed", "Pengecatan ulang ruang tamu", "Warnoto", "Toni", "2025-10-25", "2025-10-28", "2025-10-27"));
        data.add(createTaskRow("Delayed", "Pemasangan keramik dinding dapur", "Warnoto", "Toni", "2025-11-01", "2025-11-04", ""));

        // Inisialisasi Model
        tasksTableModel = new DefaultTableModel(data, new Vector<>(Arrays.asList(columnNames)));
        JTable tasksTable = new JTable(tasksTableModel);
        JScrollPane scrollPane = new JScrollPane(tasksTable);

        // Panel Tombol
        JPanel buttonPanel = new JPanel(new MigLayout("wrap 1, aligny top"));
        JButton btnAdd = new JButton("Add");
        JButton btnEdit = new JButton("Edit");
        JButton btnDelete = new JButton("Delete");
        buttonPanel.add(btnAdd);
        buttonPanel.add(btnEdit);
        buttonPanel.add(btnDelete);

        // --- Interaksi CRUD ---
        btnAdd.addActionListener(e -> addTask(tasksTable));
        btnEdit.addActionListener(e -> editTask(tasksTable));
        btnDelete.addActionListener(e -> deleteRow(tasksTable, tasksTableModel));
        // -----------------------

        panel.add(scrollPane, "grow, push");
        panel.add(buttonPanel, "growy");

        return panel;
    }

    /**
     * Membuat Panel Comments (Row 4) dengan interaksi CRUD (hanya Delete yang umum).
     */
    private JPanel createCommentsPanel() {
        JPanel panel = new JPanel(new MigLayout("fill"));
        panel.setBorder(BorderFactory.createTitledBorder("Comments"));

        // Definisi Column Names (Menggunakan Tanda Kurung Kurawal {})
        String[] columnNames = {"Date", "User", "Comment"};
        
        // Data awal
        Vector<Vector<Object>> data = new Vector<>();
        data.add(createCommentRow("2025-10-26 10:15", "Toni", "Cat tembok sudah diolesi lapisan pertama. Menunggu kering sebelum lapisan kedua."));
        data.add(createCommentRow("2025-11-03 16:45", "Toni", "Cuaca hujan deras selama 2 hari, area kerja sedikit basah. Pemasangan ditunda besok pagi. Barang sudah sampai di lokasi."));
        data.add(createCommentRow("2025-11-15 14:30", "Goang", "Mulai proses pembongkaran closet lama sore ini."));

        // Inisialisasi Model
        commentsTableModel = new DefaultTableModel(data, new Vector<>(Arrays.asList(columnNames)));
        JTable commentsTable = new JTable(commentsTableModel);
        JScrollPane scrollPane = new JScrollPane(commentsTable);

        // Panel Tombol
        JPanel buttonPanel = new JPanel(new MigLayout("wrap 1, aligny top"));
        JButton btnAdd = new JButton("Add");
        JButton btnEdit = new JButton("Edit");
        JButton btnDelete = new JButton("Delete");
        buttonPanel.add(btnAdd);
        buttonPanel.add(btnEdit);
        buttonPanel.add(btnDelete);

        // --- Interaksi CRUD ---
        btnAdd.addActionListener(e -> addComment(commentsTable));
        btnEdit.addActionListener(e -> editComment(commentsTable));
        btnDelete.addActionListener(e -> deleteRow(commentsTable, commentsTableModel));
        // -----------------------

        panel.add(scrollPane, "grow, push");
        panel.add(buttonPanel, "growy");

        return panel;
    }

    private JPanel createControlsPanel() {
        JPanel panel = new JPanel(new MigLayout("align right, insets 5 5 5 5"));
        panel.add(new JButton("Export"));
        panel.add(new JSeparator(SwingConstants.VERTICAL));
        panel.add(new JButton("Save"));
        panel.add(new JButton("Cancel"));
        return panel;
    }

    // =========================================================================
    //                            CRUD UTILITY & LOGIC
    // =========================================================================

    // Utility untuk membuat Vector data Produk
    private Vector<Object> createProductRow(String ren, String desc, String part, int qty, int listP, int disc, int price, int wDisc, int wPrice) {
        Vector<Object> row = new Vector<>();
        row.add(ren); row.add(desc); row.add(part); row.add(qty); row.add(listP);
        row.add(disc); row.add(price); row.add(wDisc); row.add(wPrice);
        return row;
    }
    
    // Utility untuk membuat Vector data Task
    private Vector<Object> createTaskRow(String state, String task, String assigner, String executer, String cDate, String eDate, String exDate) {
        Vector<Object> row = new Vector<>();
        row.add(state); row.add(task); row.add(assigner); row.add(executer);
        row.add(cDate); row.add(eDate); row.add(exDate);
        return row;
    }

    // Utility untuk membuat Vector data Komentar
    private Vector<Object> createCommentRow(String date, String user, String comment) {
        Vector<Object> row = new Vector<>();
        row.add(date); row.add(user); row.add(comment);
        return row;
    }


    /** Menambahkan baris baru ke tabel Produk. */
    private void addProduct(JTable table) {
        productTableModel.addRow(new Object[]{"Baru", "Deskripsi Produk Baru", "NEW-001", 1, 100000, 0, 100000, 0, 100000});
        int lastRow = productTableModel.getRowCount() - 1;
        table.setRowSelectionInterval(lastRow, lastRow);
    }

    /** Mengedit baris yang dipilih (Contoh sederhana: membuka JOptionPane). */
    private void editProduct(JTable table) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            String currentDesc = productTableModel.getValueAt(selectedRow, 1).toString();
            String newDesc = JOptionPane.showInputDialog(this, "Edit Deskripsi Produk:", currentDesc);
            if (newDesc != null && !newDesc.trim().isEmpty()) {
                productTableModel.setValueAt(newDesc, selectedRow, 1);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Pilih baris yang akan diedit.", "Peringatan", JOptionPane.WARNING_MESSAGE);
        }
    }

    /** Menambah Task. */
    private void addTask(JTable table) {
        tasksTableModel.addRow(new Object[]{"Pending", "Task Baru Ditambahkan", "Admin", "Toni", "2025-11-17", "2025-11-20", ""});
        int lastRow = tasksTableModel.getRowCount() - 1;
        table.setRowSelectionInterval(lastRow, lastRow);
    }
    
    /** Mengedit Task. */
    private void editTask(JTable table) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            String currentTask = tasksTableModel.getValueAt(selectedRow, 1).toString();
            String newTask = JOptionPane.showInputDialog(this, "Edit Deskripsi Task:", currentTask);
            if (newTask != null && !newTask.trim().isEmpty()) {
                tasksTableModel.setValueAt(newTask, selectedRow, 1);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Pilih baris yang akan diedit.", "Peringatan", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    /** Menambah Comment. */
    private void addComment(JTable table) {
        commentsTableModel.addRow(new Object[]{"2025-11-17 08:30", "Anda", "Komentar baru ditambahkan."});
        int lastRow = commentsTableModel.getRowCount() - 1;
        table.setRowSelectionInterval(lastRow, lastRow);
    }
    
    /** Mengedit Comment. */
    private void editComment(JTable table) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            String currentComment = commentsTableModel.getValueAt(selectedRow, 2).toString();
            String newComment = JOptionPane.showInputDialog(this, "Edit Komentar:", currentComment);
            if (newComment != null && !newComment.trim().isEmpty()) {
                commentsTableModel.setValueAt(newComment, selectedRow, 2);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Pilih baris yang akan diedit.", "Peringatan", JOptionPane.WARNING_MESSAGE);
        }
    }

    /** Menghapus baris yang dipilih dari JTable (Delete). */
    private void deleteRow(JTable table, DefaultTableModel model) {
        int selectedRow = table.getSelectedRow();
        if (selectedRow != -1) {
            int confirm = JOptionPane.showConfirmDialog(this, "Anda yakin ingin menghapus baris ini?", "Konfirmasi Hapus", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                model.removeRow(selectedRow);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Pilih baris yang akan dihapus.", "Peringatan", JOptionPane.WARNING_MESSAGE);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ProjectDetailUI().setVisible(true));
    }
}