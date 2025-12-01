import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import net.miginfocom.swing.MigLayout;

public class Demo4 extends JFrame {

	private JPanel panelUtama;
	private JButton buttonMulaiTask;
	private JLabel labelStatus;
	private JTextArea textAreaHasil;

	public Demo4() {
		initializeUI();
		setupEventHandlers();
	}

	private void initializeUI() {
		setTitle("Demo 4: SwingWorker (Separate Top-Level Class)");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setPreferredSize(new Dimension(600, 450));

		panelUtama = new JPanel(new MigLayout("fill, wrap 1, insets 20"));

		buttonMulaiTask = new JButton("Mulai Task (8 detik)");
		buttonMulaiTask.setFont(new Font("Inter", Font.BOLD, 18));
		panelUtama.add(buttonMulaiTask, "growx, h 20%");

		labelStatus = new JLabel("Status: Siap", JLabel.CENTER);
		labelStatus.setFont(new Font("Inter", Font.PLAIN, 22));
		labelStatus.setForeground(new Color(0, 128, 0));
		panelUtama.add(labelStatus, "growx, h 20%");

		textAreaHasil = new JTextArea();
		textAreaHasil.setEditable(false);
		textAreaHasil.setFont(new Font("Consolas", Font.PLAIN, 15));
		JScrollPane scroll = new JScrollPane(textAreaHasil);
		panelUtama.add(scroll, "growx, h 60%");

		add(panelUtama);
		pack();
		setLocationRelativeTo(null);
	}

	private void setupEventHandlers() {
		buttonMulaiTask.addActionListener(e -> {
			buttonMulaiTask.setEnabled(false);
			new HitungMundurWorker(this, labelStatus, textAreaHasil, buttonMulaiTask).execute();
		});
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> {
			try {
				UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
			} catch (Exception e) {
				System.err.println(e.getMessage());
			}
			new Demo4().setVisible(true);
		});
	}
}