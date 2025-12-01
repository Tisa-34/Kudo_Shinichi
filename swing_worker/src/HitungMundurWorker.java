import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.SwingWorker;

public class HitungMundurWorker extends SwingWorker<String, Integer> {
	private final JFrame frame;
	private final JLabel labelStatus;
	private final JTextArea textAreaHasil;
	private final JButton buttonMulaiTask;

	public HitungMundurWorker(JFrame frame, JLabel labelStatus, JTextArea textAreaHasil, JButton buttonMulaiTask) {
		this.frame = frame;
		this.labelStatus = labelStatus;
		this.textAreaHasil = textAreaHasil;
		this.buttonMulaiTask = buttonMulaiTask;
	}

	@Override
	protected String doInBackground() throws Exception {
		buttonMulaiTask.setEnabled(false);
		labelStatus.setText("Status: Mulai bekerja");
		textAreaHasil.setText("Mengerjakan Background Task (8 detik)\n");

		for (int i = 8; i >= 0; i--) {
			Thread.sleep(1000);
			publish(i);
		}
		return "Background Task Selesai";
	}

	@Override
	protected void process(List<Integer> chunks) {
		int detik = chunks.get(chunks.size() - 1);
		textAreaHasil.append("Hitung mundur: " + detik + "\n");
		textAreaHasil.setCaretPosition(textAreaHasil.getDocument().getLength());
		labelStatus.setText("Sisa " + detik + " detik");
	}

	@Override
	protected void done() {
		try {
			String hasil = get();
			textAreaHasil.append(hasil);
			labelStatus.setText("Status: Selesai");
			JOptionPane.showMessageDialog(frame, hasil, "Informasi", JOptionPane.INFORMATION_MESSAGE);
		} catch (Exception ex) {
			labelStatus.setText("Status: Error");
		} finally {
			buttonMulaiTask.setEnabled(true);
		}
	}
	
}