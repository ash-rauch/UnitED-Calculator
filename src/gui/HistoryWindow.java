package gui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JWindow;

/**
 * Displays the session history of the user's calculations
 */
@SuppressWarnings("serial")
public class HistoryWindow extends JWindow implements ActionListener {
	private ArrayList<String> history;
	private JTextArea field;
	private boolean open;
	private JButton btn;
	private BorderLayout layout;
	private JScrollPane scrollPane;

	public HistoryWindow(int x, int y, int w) { // Maybe have "Frame owner"
												// super(owner);
		this.history = new ArrayList<String>();
		this.setSize(360, 500);

		open = false;
		btn = new JButton(">");
		btn.addActionListener(this);
		field = new JTextArea();
		field.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 25));
		scrollPane = new JScrollPane(field);
		layout = new BorderLayout();

		this.setLayout(layout);
		this.add(scrollPane, BorderLayout.CENTER);
		this.add(btn, BorderLayout.EAST);
		this.setWindowLocation(x, y, w);
		this.setAlwaysOnTop(false);
		this.setFocusable(false);
		this.setVisible(true);
	}

	public void addCalculation(String calc) {
		history.add(calc);
		field.setText(field.getText() + " " + calc + "\n");
	}

	private void openWindow() {
		open = true;
		btn.setText("<");
		int x = this.getX();
		for (int i = x; i < this.getWidth() + x - 50; i += 4) {
			this.setLocation(i, this.getY());
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	private void closeWindow() {
		open = false;
		btn.setText(">");
		int x = this.getX();
		for (int i = x; i > x - (this.getWidth() - 50); i -= 4) {
			this.setLocation(i, this.getY());
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void setWindowLocation(final int x, final int y, final int w) {
		if (open) {
			this.setLocation(x + w - 10, y + 25);
		} else {
			this.setLocation(x + 100, y + 25);
		}
		this.toFront();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String action = e.getActionCommand();
		if (action.equals(btn.getText())) {
			if (open) {
				closeWindow();
			} else {
				openWindow();
			}
		}
	}

	// Returns the current text in the history window's text area
	public String getHistoryData() {
		return field.getText();
	}
}
