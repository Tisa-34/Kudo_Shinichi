

import java.awt.Dimension;
import java.util.Collections;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.themes.FlatMacLightLaf;

import net.miginfocom.swing.MigLayout;


public class RenovationApp {
    private static String buttonColor ="background: $Component.accentColor; foreground: #FFFFFF";

    
    public static void main(String[] args) {
        try {
            // FlatLaf.setGlobalExtraDefaults(Collection.singletonMap("@accentColor", "#8A2BE2"));
            FlatLaf.setGlobalExtraDefaults(Collections.singletonMap("@accentColor", "#8A2BE2"));
            UIManager.setLookAndFeel(new FlatMacLightLaf());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> 
    {
      /** Deklarasi Frame */
      JFrame frame = new JFrame("Detail Proyek: Midori 24 November 2025");
      frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      frame.setPreferredSize(new Dimension(1024, 768));
      frame.setMinimumSize(new Dimension(800, 600));


      /** Deklarasi Panel Utama */  
      JPanel panelUtama = new JPanel(new MigLayout("fill, wrap 3", "[][grow][]", "[][grow][grow][]") );


      /** Deklarasi Panel  Client */  
      JPanel panelClient = new JPanel(new MigLayout("wrap 2", "[right]10[left]"));
      panelClient.setBorder(new TitledBorder("Client"));
      panelClient.add(new JLabel("Client ID:"));
      panelClient.add(new JLabel("<html><b>101</b></html>"));
      panelClient.add(new JLabel("Name:"));
      panelClient.add(new JLabel("<html><b>Bapak Alex Gunawan</b></html>"));
      panelClient.add(new JLabel("Phone:"));
      panelClient.add(new JLabel("<html><b>(+62) 8123456789</b></html>"));
      panelClient.add(new JLabel("Registration No:"));
      panelClient.add(new JLabel("<html><b>RNV-JKT-AXG-001</b></html>"));
      JButton buttonDetails = new JButton("Details");
      buttonDetails.putClientProperty("FlatLaf.style", buttonColor);
      panelClient.add(buttonDetails, "span 2, gap top 10, center");
      panelUtama.add(panelClient, "grow");


      /** Deklarasi Panel Information */  
      JPanel panelInformation = new JPanel(new MigLayout("wrap 3", "[right]10[left, grow, fill]5[fill]"));
      panelInformation.setBorder(new TitledBorder("Information"));
      panelInformation.add(new JLabel("Reserve days:"));
      panelInformation.add(new JTextField("0 of 30"));
      panelInformation.add(new JButton("Add Days"));
      panelInformation.add(new JLabel("Buyer:"));
      panelInformation.add(new JTextField("Bapak Alex Gunawan"), "span 2");
      panelInformation.add(new JLabel("Seller:"));
      panelInformation.add(new JTextField("PT Bangun Jaya Abadi"), "span 2");
      panelInformation.add(new JLabel("Address:"));
      panelInformation.add(new JTextField("JL. Raya Lohbener Baru, 778A"), "span 2");
      panelInformation.add(new JLabel("Credit rating:"));
      panelInformation.add(new JTextField("AAA"));
      panelInformation.add(new JButton("S&P Update"));
      panelInformation.add(new JCheckBox("Approved:"));
      panelInformation.add(new JTextField("Proyek renovasi telah disetujui, siap dimulai"), "span 2");
      panelUtama.add(panelInformation, "grow");


      /** Deklarasi Panel Additional Information */ 
       JPanel panelAdditional = new JPanel(new MigLayout("wrap 3", "[right]10[left,grow,fill][]"));
       panelAdditional.setBorder(new TitledBorder("Additional Information"));
       panelAdditional.add(new JLabel("Estimated close"));
       panelAdditional.add(new JTextField("2025-12-15"), "wmin pref");
       panelAdditional.add(new JButton("Edit"), "wmax pref");
       panelAdditional.add(new JLabel("Creation date:"));
       panelAdditional.add(new JLabel("<html><b>2025-10-15</b></html>"), "span 2");
       panelAdditional.add(new JLabel("Created by:"));
       panelAdditional.add(new JLabel("<html><b>Admin</b></html>"), "span 2");
       panelAdditional.add(new JLabel("Last edit date:"));
       panelAdditional.add(new JLabel("<html><b>2025-11-16</b></html>"), "span 2");
       panelAdditional.add(new JLabel("Last edited by:"));
       panelAdditional.add(new JLabel("<html><b>Warnoto</b></html>"), "span 2");
       panelAdditional.add(new JLabel("Closed date:"));
       panelAdditional.add(new JLabel("<html><b>null</b></html>"), "span 2");
       panelAdditional.add(new JLabel("Closed by:"));
       panelAdditional.add(new JLabel("<html><b>null</b></html>"), "span 2");
       panelUtama.add(panelAdditional, "grow");


      /** Deklarasi Panel Product List */  
      JPanel panelProduct = new JPanel(new MigLayout("", "[grow][]"));
      panelProduct.setBorder(new TitledBorder("Product List"));


      /** Deklarasi Panel (Table) Product List */  
      JPanel panelTableProductList = new JPanel(new MigLayout("wrap 3", "[grow][left][right]", "[][][][][][][][][][]"));
      String[] kolomTableProductList = {
          "Renovation", "Description", "Part #", "Quantity", "List Price", "Discount", "Price", "Wholesale Discount", "Wholesaler Price"
      };
      Object[][] dataProductList = {
          {"Dapur", "Keramik Dinding Putih", "KW-PT-DLX-01", 50, 150000, 0, 7500000L, 5, 7125000L},
          {"Dapur", "Lem Keramik Instan", "LMI-GRY-STD", 5, 50000, 0, 250000L, 0, 250000L},
          {"Dapur", "Pipa PVC 3 inch", "PVC-3IN-STD", 12, 35000, 0, 420000L, 10, 378000L},
          {"Kamar Mandi", "Shower Set Minimalis", "SHW-MN-STL", 1, 850000, 15, 722500L, 5, 686375L},
          {"Kamar Mandi", "Closet Duduk Premium", "CLO-DD-PRM", 1, 2500000, 5, 2375000L, 10, 2137500L},
          {"Ruang Tamu", "Lampu Gantung Hias", "LMP-HNG-CRS", 2, 750000, 0, 1500000L, 0, 1500000L},
          {"Ruang Tamu", "Cat Tembok Putih 20L", "CAT-PT-20L", 3, 450000, 10, 1215000L, 15, 1032750L},
          {"Garasi", "Semen PC 50kg", "SMN-PC-50", 20, 60000, 0, 1200000L, 5, 1140000L},
      };
      
      DefaultTableModel tableModelProductList = new DefaultTableModel(dataProductList, kolomTableProductList);
      JTable tableProductList = new JTable(tableModelProductList);
      tableProductList.setAutoCreateRowSorter(true);
      tableProductList.setShowGrid(true);
      JScrollPane scrollPaneTableProductList = new JScrollPane(tableProductList);
      
      panelTableProductList.add(scrollPaneTableProductList, "span 3, growx, h 150");
      panelTableProductList.add(new JSeparator(), "growx");
      panelTableProductList.add(new JLabel("Subtotal list price:"), "gapx 10");
      panelTableProductList.add(new JLabel("Rp 15.570.000"), "gapx 30");
      panelTableProductList.add(new JLabel("Total retailer price:"), "skip 1, gapx 10");
      panelTableProductList.add(new JLabel("Rp 15.182.500"), "gapx 30");
      panelTableProductList.add(new JLabel("Total wholesaler price:"), "skip 1, gapx 10");
      panelTableProductList.add(new JLabel("Rp 14.249.625"), "gapx 30");
      panelUtama.add(panelTableProductList, "grow");


      /** Deklarasi Panel (Button) Product List */ 
      JPanel panelButtonProductList = new JPanel(new MigLayout("wrap 1"));
      panelButtonProductList.add(new JButton("Add"));
      panelButtonProductList.add(new JButton("Edit"));
      panelButtonProductList.add(new JButton("Delete"));
      panelProduct.add(panelButtonProductList, "grow");
      panelUtama.add(panelProduct, "growx, span 3");
      
      

      /** Deklarasi Panel Tasks */  
      JPanel panelTask = new JPanel(new MigLayout("", "[grow][]"));
      panelTask.setBorder(new TitledBorder("Tasks"));
      String[] kolomTableTask = { "State", "Task", "Assigner", "Executer", "Creation Date", "Estimated Date", "Executed Date" };
      Object[][] dataTask = {
          { "Completed", "Pengecatan ulang ruang tamu", "Warnoto", "Toni", "2025-10-25", "2025-10-28", "2025-10-27" },
          { "In Progress", "Instalasi closet duduk premium", "Warnoto", "Goang", "2025-11-15", "2025-11-17", "null" },
          { "Delayed", "Pemasangan keramik dinding dapur", "Warnoto", "Toni", "2025-11-01", "2025-11-04", "null" },
          { "Completed", "Pemasangan pipa PVC di area garasi", "Warnoto", "Goang", "2025-10-20", "2025-10-21", "2025-10-21" },
          { "Not Started", "Pembelian dan instalasi lampu gantung hias", "Warnoto", "Toni", "2025-11-16", "2025-11-18", "null" },
      };
      
      DefaultTableModel tableModelTask = new DefaultTableModel(dataTask, kolomTableTask);
      JTable tableTask = new JTable(tableModelTask);
      tableTask.setAutoCreateRowSorter(true);
      tableTask.setShowGrid(true);
      JScrollPane scrollPaneTableTask = new JScrollPane(tableTask);
      panelTask.add(scrollPaneTableTask, "grow, hmin 150, hmax 190");


      /** Deklarasi Panel (Button) Tasks */  
      JPanel panelButtonTask = new JPanel(new MigLayout("wrap 1"));
      panelButtonTask.add(new JButton("Add"));
      panelButtonTask.add(new JButton("Edit"));
      panelButtonTask.add(new JButton("Delete"));
      panelTask.add(panelButtonTask, "grow");
      panelUtama.add(panelTask, "grow, span 3");



      /** Deklarasi Panel Comments */
      JPanel panelComments = new JPanel(new MigLayout("", "[grow]"));
      panelComments.setBorder(new TitledBorder("Comments"));
      String[] kolomTableComment = { "Date", "User", "Comment" };
      Object[][] dataComment = {
          { "2025-10-26 10:15", "Toni", "Cat tembok sudah diolesi lapisan pertama. Menunggu kering sebelum lapisan kedua." },
          { "2025-11-15 14:30", "Goang", "Barang sudah sampai di lokasi. Mulai proses pembongkaran closet lama sore ini." },
          { "2025-11-16 09:00", "Warnoto", "Pastikan sambungan pipa air bersih dan pembuangan tidak ada yang bocor, cek tekanannya." },
          { "2025-11-03 16:45", "Toni", "Cuaca hujan deras selama 2 hari, area kerja sedikit basah. Pemasangan ditunda besok pagi." },
          { "2025-11-16 20:00", "Warnoto", "Lampu yang diinginkan customer stoknya habis. Mencari alternatif lampu model serupa." },
      };
      
      DefaultTableModel tableModelComment = new DefaultTableModel(dataComment, kolomTableComment);
      JTable tableComment = new JTable(tableModelComment);
      tableComment.setAutoCreateRowSorter(true);
      tableComment.setShowGrid(true);
      JScrollPane scrollPaneTableComment = new JScrollPane(tableComment);
      panelComments.add(scrollPaneTableComment, "grow, hmin 150, hmax 190");



       /** Deklarasi Panel (Button) Comments */
       JPanel panelButtonComment = new JPanel(new MigLayout("wrap 1"));
       panelButtonComment.add(new JButton("Add"));
       panelButtonComment.add(new JButton("Edit"));
       panelButtonComment.add(new JButton("Delete"));
       panelComments.add(panelButtonComment, "grow");
       panelUtama.add(panelComments, "grow, span 3");


      /** Deklarasi Panel Controls */  
      JPanel panelControls = new JPanel(new MigLayout ("insets 0", "[grow,left] [right]") );


      /** Deklarasi Panel Controls (Left) */  
      JPanel panelControlsLeft = new JPanel(new MigLayout());
      panelControlsLeft.add(new JButton("Export"));
      panelControls.add(panelControlsLeft);


      /** Deklarasi Panel Controls (Right) */  
      JPanel panelControlsRight = new JPanel(new MigLayout());
      JButton buttonSave = new JButton("Save");
      buttonSave.putClientProperty("FlatLaf.style", buttonColor);
      panelControlsRight.add(buttonSave);
      panelControlsRight.add(new JButton("Cancel"));
      panelControls.add(panelControlsRight);
      panelUtama.add(panelControls, "grow, span 3");


      /** Deklarasi ScrollPane */  
      JScrollPane scrollPane = new JScrollPane(panelUtama);
      scrollPane.getVerticalScrollBar().setUnitIncrement(18);
      scrollPane.getVerticalScrollBar().putClientProperty("JScrollBar.fastWheelScrolling", true);
      scrollPane.putClientProperty("JScrollPane.fastWheelScrolling", true);
      scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
          
      frame.add(scrollPane);
      frame.pack();
      frame.setLocationRelativeTo(null);
      frame.setVisible(true);
    } );
    }
}
