import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.List;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.UIManager;

import net.miginfocom.swing.MigLayout;

public class Demo5 extends JFrame {

    private JPanel panelUtama;
    private JButton buttonMulaiTask;
    private JButton buttonCancel;
    private JLabel labelStatus;
    private JProgressBar progressBar;
    private JTextArea textAreaHasil;
    private SwingWorker<Integer, Integer> worker;

    public Demo5() {
        initializeUI();
        setupEventHandlers();
    }

    private void initializeUI() {
        setTitle("Demo 5: SwingWorker + Cancelation");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(720, 520));

        panelUtama = new JPanel(
            new MigLayout("fill, wrap 1, insets 20")
        );
        addPanelTombol();
        addLabelStatus();
        addProgressBar();
        addScrollTextArea();

        add(panelUtama);
        pack();
        setLocationRelativeTo(null);
    }

    private void addPanelTombol() {
        JPanel panelTombol = new JPanel(
            new MigLayout("fill", "[grow][grow]")
        );
        buttonMulaiTask = new JButton("Mulai Task (8 detik)");
        buttonCancel = new JButton("Cancel");
        buttonMulaiTask.setFont(new Font("Inter", Font.BOLD, 18));
        buttonCancel.setFont(new Font("Inter", Font.BOLD, 18));
        buttonCancel.setEnabled(false);
        panelTombol.add(buttonMulaiTask, "w 90%, growy");
        panelTombol.add(buttonCancel, "w 10%, growy");
        panelUtama.add(panelTombol, "h 20%, hmin pref, grow");
    }

    private void addLabelStatus() {
        labelStatus = new JLabel("Status: Siap", JLabel.CENTER);
        labelStatus.setFont(new Font("Inter", Font.BOLD, 24));
        labelStatus.setForeground(new Color(0, 100, 0));
        panelUtama.add(labelStatus, "h 10%, hmin pref, growx");
    }

    private void addProgressBar() {
        progressBar = new JProgressBar(0, 100);
        progressBar.setStringPainted(true);
        progressBar.setFont(new Font("Inter", Font.BOLD, 18));
        progressBar.setValue(0);
        panelUtama.add(progressBar, "h 10%, hmin pref, growx");
    }

    private void addScrollTextArea() {
        textAreaHasil = new JTextArea();
        textAreaHasil.setEditable(false);
        textAreaHasil.setFont(new Font("Consolas", Font.PLAIN, 14));
        JScrollPane scroll = new JScrollPane(textAreaHasil);
        panelUtama.add(scroll, "h 60%, growx");
    }

    private void setupEventHandlers() {
        buttonMulaiTask.addActionListener(e -> mulaiProses());
        buttonCancel.addActionListener(e -> {
            if (worker != null && !worker.isDone()) {
                worker.cancel(true);
                addLog("Proses dibatalkan oleh user...");
                buttonCancel.setEnabled(false);
                buttonMulaiTask.setEnabled(true);
            }
        });
    }

    private void mulaiProses() {
        buttonMulaiTask.setEnabled(false);
        buttonCancel.setEnabled(true);
        progressBar.setValue(0);
        textAreaHasil.setText("");
        labelStatus.setText("Status: Mulai bekerja");
        addLog("Mengerjakan Background Task (8 detik)");

        worker = new SwingWorker<Integer, Integer>() {
            @Override
            protected Integer doInBackground() throws Exception {
                int total = 100;
                for (int i = 1; i <= total; i++) {
                    if (isCancelled()) return null;
                    Thread.sleep(80);
                    publish(i);
                }
                return total;
            }

            @Override
            protected void process(List<Integer> chunks) {
                if (isCancelled()) return;
                int progress = chunks.get(chunks.size() - 1);
                progressBar.setValue(progress);
                int remainingSeconds = 8 - (8 * progress / 100);
                labelStatus.setText("Sisa " + remainingSeconds + " detik");
                addLog("Persentase pengerjaan task: " + progress + "%");
            }

            @Override
            protected void done() {
                try {
                    Integer result = get();
                    labelStatus.setText("Status: Selesai");
                    addLog("Background Task Selesai " + result + "%");
                    JOptionPane.showMessageDialog(Demo5.this,
                        "Background Task Selesai",
                        "Informasi", JOptionPane.INFORMATION_MESSAGE);
                } catch (ExecutionException ex) {
                    labelStatus.setText("Status: Error");
                    addLog("Error: " + ex.getCause().getMessage());
                    JOptionPane.showMessageDialog(Demo5.this,
                        "Terjadi kesalahan: " + ex.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
                } catch (CancellationException ee){
                    labelStatus.setText("Status: Dibatalkan");
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                } finally {
                    buttonCancel.setEnabled(false);
                    buttonMulaiTask.setEnabled(true);
                    worker = null;
                }
            }
        };

        worker.execute();
    }

    private void addLog(String text) {
        textAreaHasil.append(text + "\n");
        textAreaHasil.setCaretPosition(textAreaHasil.getDocument().getLength());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ignored) {}
            new Demo5().setVisible(true);
        });
    }
}