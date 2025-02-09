package gui;

import java.awt.GridBagLayout;
import java.awt.Image;
import java.io.IOException;
import java.util.Locale;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * GUI for localized "About" page
 */
public class AboutDialog extends JDialog {

	private Locale loc;

	AboutDialog(Locale loc, JFrame parent) throws IOException {
		super(parent);
		this.loc = loc;
		initialize();
	}

	private void initialize() throws IOException {
		setSize(400, 300);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		// Set layout manager for the content pane
		getContentPane().setLayout(new GridBagLayout());
		// Create panel to hold components with BoxLayout
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		// Add vertical space between components
		panel.add(Box.createVerticalStrut(10));
		// Create and center image label
		JLabel imageLabel = new JLabel();
		ImageIcon logo = new ImageIcon(getClass().getResource("/images/unitED_Logo.png"));
		Image scaledImg = logo.getImage().getScaledInstance(150, 50, Image.SCALE_SMOOTH);
		imageLabel.setIcon(new ImageIcon(scaledImg));
		imageLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT); // Center horizontally
		panel.add(imageLabel);

		JLabel textLabel;
		if (loc == Locale.US) {
			setTitle("About");
			textLabel = new JLabel("<html><center><p>unitED v1.0</p><br>"
					+ "<p>UnitED is a modern, easy-to-use measure calculator</p>"
					+ "<p>(i.e., a calculator that performs operations on</p>" + "<p>operands that can have units).</p>"
					+ "<p>It is a product of Sagacious Media that was</p>" + "<p>developed by:</p></html>");
			textLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
			panel.add(textLabel);
		}
		if (loc == Locale.FRANCE) {
			setTitle("À Propos");
			textLabel = new JLabel("<html><center><p>unitED v1.0</p><br>"
					+ "<p>UnitED est un calculateur de mesures moderne et facile à utiliser</p>"
					+ "<p>(c'est-à-dire une calculatrice qui effectue des opérations sur</p>"
					+ "<p>opérandes pouvant avoir des unités).</p>"
					+ "<p>C'est un produit de Sagacious Media qui a été</p>" + "<p>développé par:</p></html>");
			textLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
			panel.add(textLabel);
		}

		if (loc == Locale.GERMANY) {
			setTitle("Über");
			textLabel = new JLabel("<html><center><p>unitED v1.0</p><br>"
					+ "<p>UnitED ist ein moderner, benutzerfreundlicher Maßrechner</p>"
					+ "<p>(ein Rechner, der Operationen ausführt</p>" + "<p>operanden, die Einheiten haben können).</p>"
					+ "<p>Es ist ein Produkt von Sagacious Media</p>" + "<p>entwickelt von:</p></html>");
			textLabel.setAlignmentX(JLabel.CENTER_ALIGNMENT);
			panel.add(textLabel);
		}

		// Add panel to content pane
		getContentPane().add(panel);
	}
}
