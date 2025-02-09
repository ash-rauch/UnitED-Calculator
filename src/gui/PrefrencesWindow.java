package gui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * GUI for preferences window.
 */
@SuppressWarnings("serial")
public class PrefrencesWindow extends JDialog implements ItemListener, ActionListener {
	private final JCheckBox thousandSeperator;
	private final CalculatorUI calcUser;
	JTextField maximumDid;
	JButton okButton = new JButton("OK");
	JTextField tf;

	public PrefrencesWindow(CalculatorUI parent) {
		super(parent);
		this.calcUser = parent;
		this.thousandSeperator = new JCheckBox("Thousand Seperator");
		initialize();
	}

	private void initialize() {
		this.setTitle("Prefrences");
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);

		this.setLayout(new FlowLayout());

		File file = new File("prefrences.txt");
		try {
			FileReader fr = new FileReader(file);
			int next = fr.read();
			if (next == 49) {
			  calcUser.thousSep = true;
				thousandSeperator.setSelected(true);
			}
			fr.close();
		} catch (FileNotFoundException e) {
			System.out.println("Fail - initialize");
		} catch (IOException e) {
			System.out.println("Fail - initialize");
		}

		thousandSeperator.addItemListener(this);
		JPanel p = new JPanel();

		p.add(thousandSeperator);

		this.add(p);
		
		tf = new JTextField();
		tf.setColumns(10);
		JLabel jl = new JLabel("Max Didgets: ");
		okButton.addActionListener(this);
		
		this.add(jl);
		this.add(tf);
		this.add(okButton);

		this.setSize(250, 150);
		this.setResizable(false);

		this.setVisible(true);
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		if (e.getSource() == thousandSeperator) {
			if (e.getStateChange() == 1) {
				try {
					File file = new File("thousandSeperator.pref");
					file.createNewFile();
					FileWriter fw = new FileWriter("thousandSeperator.pref");
					fw.write('1');
					fw.close();
				} catch (IOException ioe) {
					System.out.println("Fail - itemStateChanged");
				}
				calcUser.thousSep = true;
			} else {
				try {
					File file = new File("thousandSeperator.pref");
					file.createNewFile();
					FileWriter fw = new FileWriter("thousandSeperator.pref");
					fw.write('0');
					fw.close();
				} catch (IOException ioe) {
					System.out.println("Fail - itemStateChanged");
				}
				calcUser.thousSep = false;
			}
		}
	}

  @Override
  public void actionPerformed(ActionEvent e)
  {
    try {
      calcUser.numDidgets = Integer.parseInt(tf.getText());
      tf.setText("");
      File file = new File("maximumDidgets.pref");
      file.createNewFile();
      FileWriter fw = new FileWriter("maximumDidgets.pref");
      fw.write('0');
      fw.close();
    } catch (NumberFormatException nfe) {
      tf.setText("Numbers Only");
    }
    catch (IOException e1)
    {
      System.out.println("Fail - actionPerformed");
    }
  }
}