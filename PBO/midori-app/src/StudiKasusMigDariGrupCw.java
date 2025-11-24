import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableModel;
import net.miginfocom.swing.MigLayout;
import com.formdev.flatlaf.FlatLightLaf;

public class StudiKasusMigDariGrupCw extends JFrame {
    public StudiKasusMigDariGrupCw() {
        initUI();
    }

    private void initUI() {
        setTitle("Studi Kasus Analisis Layout & UI Swing");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(1200, 820);
        setLocationRelativeTo(null);

        // Main panel using MigLayout: 3 columns, 5 rows (rows height controlled by grow)
        JPanel main = new JPanel(new MigLayout("fill, insets 12", 
                // columns: left(30%), center(40%), right(30%)
                "[30%]10[grow 0, 40%]10[30%]",
                // rows: top panels, product list, tasks, comments, controls
                "[][grow 0, 30%][grow 0, 20%][grow 0, 20%][]"));
        add(main);

        // Row 1: three titled panels (Client | Information | Additional Information)
        main.add(createClientPanel(), "growy, top, h 150!, gapbottom 4");
        main.add(createInformationPanel(), "growy, top, h 150!, gapbottom 4");
        main.add(createAdditionalPanel(), "growy, top, h 150!, wrap, gapbottom 8");

        // Row 2: Product List - span 3 cols
        main.add(createProductListPanel(), "spanx, growx, h 180!, wrap, gapbottom 8");

        // Row 3: Tasks - span 3 cols
        main.add(createTasksPanel(), "spanx, growx, h 140!, wrap, gapbottom 8");

        // Row 4: Comments - span 3 cols
        main.add(createCommentsPanel(), "spanx, growx, h 140!, wrap, gapbottom 8");

        // Row 5: Controls: Export left, Save & Cancel right
        JPanel bottomControls = new JPanel(new BorderLayout());
        JButton exportBtn = new JButton("Export");
        bottomControls.add(exportBtn, BorderLayout.WEST);

        JPanel rightBtns = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 0));
        JButton saveBtn = new JButton("Save");
        saveBtn.setOpaque(true);
        saveBtn.setBackground(new Color(153, 51, 204)); // purple
        saveBtn.setForeground(Color.WHITE);
        saveBtn.setPreferredSize(new Dimension(90, 28));
        JButton cancelBtn = new JButton("Cancel");
        rightBtns.add(saveBtn);
        rightBtns.add(cancelBtn);
        bottomControls.add(rightBtns, BorderLayout.EAST);

        main.add(bottomControls, "spanx, growx");

        // Footer (small caption)
        JLabel footer = new JLabel("Studi Kasus Analisis Layout & UI Swing -  Aoi Midori");
        footer.setFont(footer.getFont().deriveFont(Font.ITALIC, 12f));
        footer.setHorizontalAlignment(SwingConstants.CENTER);
        add(footer, BorderLayout.SOUTH);

        // Some global tweaks for table selection color
        UIManager.put("Table.selectionBackground", new Color(153, 51, 204));
        UIManager.put("Table.selectionForeground", Color.white);
    }

    private JPanel createClientPanel() {
        JPanel p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        p.setBorder(titledBorder("Client"));

        p.add(rowLabel("Client ID:", "<html><b>101</b></html>"));
        p.add(rowLabel("Name:", "<html><b>Bapak Alex Gunawan</b></html>"));
        p.add(rowLabel("Phone:", "(+62) 8123456789"));
        p.add(rowLabel("Registration No:", "RNV-JKT-AXG-001"));

        p.add(Box.createVerticalGlue());
        JButton details = new JButton("Details");
        details.setAlignmentX(Component.CENTER_ALIGNMENT);
        details.setBackground(new Color(153, 51, 204));
        details.setForeground(Color.WHITE);
        details.setOpaque(true);
        p.add(Box.createRigidArea(new Dimension(0,6)));
        p.add(details);

        return p;
    }

    private JPanel createInformationPanel() {
        JPanel p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        p.setBorder(titledBorder("Information"));

        p.add(rowLabel("Reserve days:", "0 of 30    [Add Days]"));
        p.add(rowLabel("Buyer:", "Bapak Alex Gunawan"));
        p.add(rowLabel("Seller:", "PT Bangun Jaya Abadi"));
        p.add(rowLabel("Address:", "Jl. Raya Lohbener Baru, 778A"));
        p.add(rowLabel("Credit rating:", "AAA"));
        p.add(rowLabel("Approved:", "Proyek renovasi telah disetujui, siap dimulai"));

        return p;
    }

    private JPanel createAdditionalPanel() {
        JPanel p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        p.setBorder(titledBorder("Additional Information"));

        p.add(rowLabel("Estimated close:", "2025-12-15"));
        p.add(rowLabel("Creation date:", "2025-10-15"));
        p.add(rowLabel("Created by:", "Admin"));
        p.add(rowLabel("Last edit date:", "2025-11-16"));
        p.add(rowLabel("Last edited by:", "Warnoto"));
        p.add(rowLabel("Closed date:", "null"));
        p.add(rowLabel("Closed by:", "null"));

        return p;
    }

    private JPanel createProductListPanel() {
        JPanel p = new JPanel(new BorderLayout(6,6));
        p.setBorder(titledBorder("Product List"));

        String[] cols = {"Renovation","Description","Part #","Quantity","List Price","Discount","Price","Wholesale Discount","Wholesaler Price"};
        Object[][] rows = {
            {"Dapur","Keramik Dinding Putih","KW-PT-DLX-01",50,150000,0,750000,5,7125000},
            {"Dapur","Lem Keramik Instan","LMI-GRY-STD",5,50000,0,250000,0,250000},
            {"Dapur","Pipa PVC 3 inch","PVC-3IN-STD",12,35000,0,420000,10,378000}
        };
        DefaultTableModel model = new DefaultTableModel(rows, cols) {
            @Override public boolean isCellEditable(int row, int col) { return false; }
        };
        JTable table = new JTable(model);
        table.setRowHeight(24);
        table.setSelectionBackground(new Color(153,51,204));
        table.setSelectionForeground(Color.white);
        JScrollPane sp = new JScrollPane(table);
        p.add(sp, BorderLayout.CENTER);

        // summary on bottom-right
        JPanel sum = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 6));
        sum.add(new JLabel("Subtotal list price: Rp 15.570.000"));
        sum.add(Box.createHorizontalStrut(10));
        sum.add(new JLabel("Total retailer price: Rp 15.182.500"));
        sum.add(Box.createHorizontalStrut(10));
        sum.add(new JLabel("Total wholesaler price: Rp 14.249.625"));
        p.add(sum, BorderLayout.SOUTH);

        // Right-side buttons (Add/Edit/Delete) anchored vertically
        JPanel right = new JPanel();
        right.setLayout(new BoxLayout(right, BoxLayout.Y_AXIS));
        JButton add = new JButton("Add");
        JButton edit = new JButton("Edit");
        JButton del = new JButton("Delete");
        add.setMaximumSize(new Dimension(90, 28));
        edit.setMaximumSize(new Dimension(90, 28));
        del.setMaximumSize(new Dimension(90, 28));
        right.add(add);
        right.add(Box.createRigidArea(new Dimension(0,6)));
        right.add(edit);
        right.add(Box.createRigidArea(new Dimension(0,6)));
        right.add(del);
        p.add(right, BorderLayout.EAST);

        return p;
    }

    private JPanel createTasksPanel() {
        JPanel p = new JPanel(new BorderLayout());
        p.setBorder(titledBorder("Tasks"));

        String[] cols = {"State","Task","Assigner","Executer","Creation Date","Estimated Date","Executed Date"};
        Object[][] rows = {
            {"Completed","Pemasangan pipa PVC di area garasi","Warnoto","Goang","2025-10-20","2025-10-21","2025-10-21"},
            {"Completed","Pengecatan ulang ruang tamu","Warnoto","Toni","2025-10-25","2025-10-28","2025-10-27"},
            {"Delayed","Pemasangan keramik dinding dapur","Warnoto","Toni","2025-11-01","2025-11-04",""}
        };
        DefaultTableModel model = new DefaultTableModel(rows, cols) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };
        JTable table = new JTable(model);
        table.setRowHeight(22);
        table.setSelectionBackground(new Color(153,51,204));
        table.setSelectionForeground(Color.white);
        p.add(new JScrollPane(table), BorderLayout.CENTER);

        // right vertical buttons
        JPanel right = new JPanel();
        right.setLayout(new BoxLayout(right, BoxLayout.Y_AXIS));
        right.setBorder(BorderFactory.createEmptyBorder(8,8,8,8));
        JButton add = new JButton("Add"), edit = new JButton("Edit"), del = new JButton("Delete");
        add.setMaximumSize(new Dimension(90, 28));
        edit.setMaximumSize(new Dimension(90, 28));
        del.setMaximumSize(new Dimension(90, 28));
        right.add(add); right.add(Box.createRigidArea(new Dimension(0,6))); right.add(edit); right.add(Box.createRigidArea(new Dimension(0,6))); right.add(del);
        p.add(right, BorderLayout.EAST);

        return p;
    }

    private JPanel createCommentsPanel() {
        JPanel p = new JPanel(new BorderLayout());
        p.setBorder(titledBorder("Comments"));

        String[] cols = {"Date","User","Comment"};
        Object[][] rows = {
            {"2025-10-26 10:15","Toni","Cat tembok sudah diolesi lapisan pertama. Menunggu kering sebelum lapisan kedua."},
            {"2025-11-03 16:45","Toni","Cuaca hujan deras selama 2 hari, area kerja sedikit basah. Pemasangan ditunda besok pagi."},
            {"2025-11-15 14:30","Goang","Barang sudah sampai di lokasi. Mulai proses pembongkaran closet lama sore ini."}
        };
        DefaultTableModel model = new DefaultTableModel(rows, cols) {
            @Override public boolean isCellEditable(int r, int c) { return false; }
        };
        JTable table = new JTable(model);
        table.setRowHeight(22);
        table.setSelectionBackground(new Color(153,51,204));
        table.setSelectionForeground(Color.white);
        p.add(new JScrollPane(table), BorderLayout.CENTER);

        // right-side small buttons
        JPanel right = new JPanel();
        right.setLayout(new BoxLayout(right, BoxLayout.Y_AXIS));
        right.setBorder(BorderFactory.createEmptyBorder(8,8,8,8));
        JButton add = new JButton("Add"), edit = new JButton("Edit"), del = new JButton("Delete");
        add.setMaximumSize(new Dimension(90,28)); edit.setMaximumSize(new Dimension(90,28)); del.setMaximumSize(new Dimension(90,28));
        right.add(add); right.add(Box.createRigidArea(new Dimension(0,6))); right.add(edit); right.add(Box.createRigidArea(new Dimension(0,6))); right.add(del);
        p.add(right, BorderLayout.EAST);

        return p;
    }

    // helpers
    private Border titledBorder(String title) {
        return BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.GRAY), title);
    }

    private JPanel rowLabel(String left, String right) {
        JPanel p = new JPanel(new BorderLayout(6,0));
        p.setOpaque(false);
        JLabel l = new JLabel(left);
        l.setPreferredSize(new Dimension(90, 20));
        JLabel r = new JLabel(right);
        p.add(l, BorderLayout.WEST);
        p.add(r, BorderLayout.CENTER);
        p.setAlignmentX(Component.CENTER_ALIGNMENT);
        return p;
    }

    public static void main(String[] args) {
        // Set FlatLaf look
        try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (Exception ex) {
            System.err.println("Failed to initialize LaF");
        }

        SwingUtilities.invokeLater(() -> {
            StudiKasusMigDariGrupCw ex = new StudiKasusMigDariGrupCw();
            ex.setVisible(true);
        });
    }
}