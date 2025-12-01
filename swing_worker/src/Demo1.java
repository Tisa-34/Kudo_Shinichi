import java.awt.*;
import java.awt.event.ActionEvent;

import javax.swing.*;

import net.miginfocom.swing.MigLayout;

public class Demo1 extends JFrame {

	private JPanel panelUtama;
	private JButton buttonMulaiTask;
	private JLabel labelStatus;
	private JTextArea textAreaHasil;

	public Demo1() {
		initializeUI();
		setupEventHandlers();
	}

	private void initializeUI() {
		setTitle("Demo 1: UI Freeze");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setPreferredSize(new Dimension(560, 420));

		panelUtama = new JPanel(
			new MigLayout(
				"fill, wrap 1, insets 20",
				"[grow]"
			)
		);

		buttonMulaiTask = new JButton("Mulai Task (8 detik)");
		buttonMulaiTask.setFont(new Font("Inter", Font.BOLD, 18));
		panelUtama.add(buttonMulaiTask, "growx, h 20%");

		labelStatus = new JLabel("Status: Siap", JLabel.CENTER);
		labelStatus.setFont(new Font("Inter", Font.PLAIN, 22));
		labelStatus.setForeground(new Color(0, 102, 204));
		panelUtama.add(labelStatus, "growx, h 20%");

		textAreaHasil = new JTextArea(10, 50);
		textAreaHasil.setEditable(false);
		textAreaHasil.setFont(new Font("Consolas", Font.PLAIN, 15));
		JScrollPane scroll = new JScrollPane(textAreaHasil);
		panelUtama.add(scroll, "growx, h 60%");

		add(panelUtama);
		pack();
		setLocationRelativeTo(null);
	}

	private void setupEventHandlers() {
		buttonMulaiTask.addActionListener((ActionEvent e) -> {
			buttonMulaiTask.setEnabled(false);
			labelStatus.setText("Status: Sedang bekerja");
			textAreaHasil.setText("Mengerjakan Background Task (8 detik)\n");

			for (int i = 8; i >= 0; i--) {
				final int seconds = i;
				SwingUtilities.invokeLater(() -> {
					textAreaHasil.append("Hitung mundur: " + seconds + "\n");
					textAreaHasil.setCaretPosition(textAreaHasil.getDocument().getLength());
					labelStatus.setText("Sisa " + seconds + " detik");
				});

				try {
					Thread.sleep(1000);
				} catch (InterruptedException ex) {
					Thread.currentThread().interrupt();
				}
			}
			textAreaHasil.append("Background Task selesai\n");
			labelStatus.setText("Status: Selesai");
			JOptionPane.showMessageDialog(
				this,
				"Background Task Selesai",
				"Informasi",
				JOptionPane.INFORMATION_MESSAGE
			);
            buttonMulaiTask.setEnabled(true);
		});
	}

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) { }

            new Demo1().setVisible(true);

        } );
    }
}