package gui;

import java.awt.BorderLayout;
import java.awt.Desktop;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.MediaTracker;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import utilities.Calculations;
import utilities.Operand;
import utilities.Operator;

/**
 * GUI for the calculator.
 *
 * @author hamzejr, bresloct, kirkw2cj and rauchas
 */
@SuppressWarnings("serial")
public class CalculatorUI extends JFrame implements ActionListener, KeyListener, ComponentListener
{
  Font myFont = new Font(Font.SANS_SERIF, Font.BOLD, 30);

  Config config;

  private int operandCounter = 0;
  private int equalsCounter = 0;
  private Operand result;
  private ArrayList<String> itemsInDropdown;
  private boolean thousSep;
  private final String fileName = "units_file.unit";
  private final HistoryWindow hw;

  private static final Locale LOCALE = Locale.getDefault();
  static final ResourceBundle STRINGS = ResourceBundle.getBundle("Strings", LOCALE);

  private final JTextField display, input;
  private final JComboBox<String> unit, resultUnits;
  private final JButton[] spaces = new JButton[2];
  private final JButton[] numberButtons = new JButton[10];
  private final JButton[] functionButtons = new JButton[8];
  private final JButton addButton, subButton, mulButton, divButton;
  private final JButton signButton, decButton, equButton, delButton, clrButton, backButton,
      inverseButton, expButton;
  private final JPanel panel, displayPanel, inputPanel;
  private final JMenuBar menuBar;
  private final JMenu fileMenu, helpMenu, prefrencesMenu;
  private final JMenuItem newItem, printItem, exitItem, aboutItem, helpItem, editItem, openItem,
      saveItem, saveAsItem;

  // String values:
  private final String addValue = "+";
  private final String subValue = "-";
  private final String mulValue = "x";
  private final String divValue = "/";
  private final String decValue = ".";
  private final String equValue = "=";
  private final String clrValue = "C";
  private final String delValue = "R";
  private final String signValue = "±";
  private final String backValue = "←";
  private final String inverseValue = "1/x";
  private final String expValue = "x\u02B8";

  private final String exitValue = STRINGS.getString("EXIT");
  private final String helpValue = STRINGS.getString("HELP");
  private final String aboutValue = STRINGS.getString("ABOUT");
  private final String errValue = STRINGS.getString("ERR");
  private final String spaceValue = " ";
  private final ArrayList<Character> typable = new ArrayList<>(
      Arrays.asList('a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p',
          'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '$', '/', '-'));

  public CalculatorUI()
  {
    // frame = new JFrame("Calculator");
    this.setTitle(STRINGS.getString("CALCULATOR"));
    this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    this.addWindowListener(new java.awt.event.WindowAdapter()
    {
      public void windowClosing(java.awt.event.WindowEvent e)
      {
        hw.dispose();
      }
    });
    this.setSize(420, 550);
    this.setResizable(false); // Cade: I think this will make the history window easier.
    this.setLayout(new BorderLayout());

    displayPanel = new JPanel();
    display = new JTextField(10);
    display.setFont(myFont);
    display.setEditable(false);
    displayPanel.add(display);
    String[] resultUnit = {"", "Custom", "in", "ft", "yd", "mi", "mm", "cm", "m", "km", "oz", "lb",
        "ton", "g", "kg", "pt", "qt", "gal", "cc", "l", "c", "$", "sec", "min", "hr", "day", "mo",
        "yr", "w", "kw"};
    resultUnits = new JComboBox<>(resultUnit);
    displayPanel.add(resultUnits);

    this.add(displayPanel, BorderLayout.NORTH);

    inputPanel = new JPanel();
    input = new JTextField(10);
    input.setFont(myFont);
    inputPanel.add(input);
    String[] items = {"", "Custom", "in", "ft", "yd", "mi", "mm", "cm", "m", "km", "oz", "lb",
        "ton", "g", "kg", "pt", "qt", "gal", "cc", "l", "c", "$", "sec", "min", "hr", "day", "mo",
        "yr", "w", "kw"};
    itemsInDropdown = new ArrayList<String>();
    Collections.addAll(itemsInDropdown, items);
    unit = new JComboBox<>(items);
    getUnitItems(); // Adds units to box.
    unit.setSize(100, 50);
    inputPanel.add(input);
    inputPanel.add(unit);
    this.add(inputPanel, BorderLayout.CENTER);
    input.addKeyListener(this);

    addButton = new JButton(addValue);
    subButton = new JButton(subValue);
    mulButton = new JButton(mulValue);
    divButton = new JButton(divValue);
    decButton = new JButton(decValue);
    equButton = new JButton(equValue);
    clrButton = new JButton(clrValue);
    delButton = new JButton(delValue);
    signButton = new JButton(signValue);
    backButton = new JButton(backValue);
    inverseButton = new JButton(inverseValue);
    expButton = new JButton(expValue);
    signButton.addActionListener(this);
    backButton.addActionListener(this);
    inverseButton.addActionListener(this);
    expButton.addActionListener(this);

    functionButtons[0] = addButton;
    functionButtons[1] = subButton;
    functionButtons[2] = mulButton;
    functionButtons[3] = divButton;
    functionButtons[4] = decButton;
    functionButtons[5] = equButton;
    functionButtons[6] = delButton;
    functionButtons[7] = clrButton;

    for (int i = 0; i < 8; i++)
    {
      functionButtons[i].addActionListener(this);
      functionButtons[i].setFont(myFont);
      functionButtons[i].setFocusable(false);
    }

    for (int i = 0; i < 10; i++)
    {
      numberButtons[i] = new JButton(String.valueOf(i));
      numberButtons[i].setFont(myFont);
      numberButtons[i].setFocusable(false);
      numberButtons[i].addActionListener(this);
    }
    for (int i = 0; i < spaces.length; i++)
    {
      spaces[i] = new JButton();
      spaces[i].setVisible(false);
    }

    panel = new JPanel();
    panel.setBounds(50, 125, 300, 300);
    panel.setLayout(new GridLayout(5, 4, 10, 10));

    panel.add(signButton);
    panel.add(clrButton);
    panel.add(delButton);
    panel.add(addButton);
    panel.add(expButton);
    panel.add(numberButtons[7]);
    panel.add(numberButtons[8]);
    panel.add(numberButtons[9]);
    panel.add(subButton);
    panel.add(inverseButton);
    panel.add(numberButtons[4]);
    panel.add(numberButtons[5]);
    panel.add(numberButtons[6]);
    panel.add(mulButton);
    panel.add(spaces[0]);
    panel.add(numberButtons[1]);
    panel.add(numberButtons[2]);
    panel.add(numberButtons[3]);
    panel.add(divButton);
    panel.add(spaces[1]);
    panel.add(numberButtons[0]);
    panel.add(decButton);
    panel.add(backButton);
    panel.add(equButton);

    menuBar = new JMenuBar();
    fileMenu = new JMenu(STRINGS.getString("FILE"));
    newItem = new JMenuItem(STRINGS.getString("NEW"));
    printItem = new JMenuItem(STRINGS.getString("PRINT"));
    exitItem = new JMenuItem(exitValue);
    fileMenu.add(newItem);
    fileMenu.add(printItem);
    fileMenu.add(exitItem);
    exitItem.addActionListener(this);
    newItem.addActionListener(this);
    printItem.addActionListener(this);

    input.setFocusable(true);
    this.setFocusable(true);
    addKeyListener(this);

    helpMenu = new JMenu(helpValue);
    aboutItem = new JMenuItem(aboutValue);
    helpItem = new JMenuItem(helpValue);
    helpMenu.add(aboutItem);
    helpMenu.add(helpItem);
    aboutItem.addActionListener(this);
    helpItem.addActionListener(this);

    prefrencesMenu = new JMenu("Prefrences");
    editItem = new JMenuItem("Edit");
    editItem.addActionListener(this);
    openItem = new JMenuItem("Open");
    saveItem = new JMenuItem("Save");
    saveAsItem = new JMenuItem("Save As");
    prefrencesMenu.add(editItem);
    prefrencesMenu.add(openItem);
    prefrencesMenu.add(saveItem);
    prefrencesMenu.add(saveAsItem);

    menuBar.add(fileMenu);
    menuBar.add(helpMenu);
    menuBar.add(prefrencesMenu);
    menuBar.setVisible(true);
    this.setJMenuBar(menuBar);

    hw = new HistoryWindow(this.getX(), this.getY(), this.getWidth());

    this.setAlwaysOnTop(true); // Cade: So the HistoryWindow will be behind.
    this.addComponentListener(this);
    this.add(panel, BorderLayout.SOUTH);
    this.setVisible(true);

    printItem.addActionListener(new ActionListener()
    {
      @Override
      public void actionPerformed(ActionEvent e)
      {
        handlePrintCommand();
      }
    });
    this.config = new Config();
    configureLogo();
  }

  private void configureLogo()
  {
    String logoPath = config.getSetting("logoPath");
    ImageIcon logoIcon = new ImageIcon(getClass().getResource(logoPath));
    if (logoIcon.getImageLoadStatus() != MediaTracker.COMPLETE)
    {
      System.err.println("Failed to load logo image: " + logoPath);
      return;
    }
    JLabel logoLabel = new JLabel(logoIcon);
    add(logoLabel, BorderLayout.NORTH);
  }

  /**
   * Reads the file unitFile.txt to see what custom units are saved there.
   */
  private void getUnitItems()
  {
    File unitItems;
    Scanner sc;
    try
    {
      unitItems = new File(fileName);
      sc = new Scanner(unitItems);
      while (sc.hasNext())
      {
        String s = sc.nextLine();
        unit.addItem(s);
        itemsInDropdown.add(s);
      }
      sc.close();
    }
    catch (IOException ioe)
    {
      System.out.println("Fail");
    }
  }

  /**
   * Entry point of program. Creates a calculatorUI object.
   *
   * @param args
   *          not used.
   */
  @SuppressWarnings("unused")
  public static void main(final String[] args)
  {
    CalculatorUI calc = new CalculatorUI();
  }

  @Override
  public void actionPerformed(final ActionEvent e)
  {
    String command;
    command = e.getActionCommand();

    calcFunction(command);
  }

  @Override
  public void keyTyped(final KeyEvent e)
  {
    char typedKey = e.getKeyChar();
    if (!typable.contains(typedKey) || !unit.getSelectedItem().toString().equals("Custom")
        || input.getText().isEmpty())
    {
      e.consume();
    }
  }

  @Override
  public void keyPressed(final KeyEvent e)
  {
    if (e.getKeyCode() == KeyEvent.VK_BACK_SPACE)
    {
      input.setText(input.getText());
    }
    if (e.getKeyCode() == KeyEvent.VK_ENTER && equalsCounter == 0)
    {
      calcFunction(equValue);
    }
    char typedKey = e.getKeyChar();
    String command = String.valueOf(typedKey);
    if (typable.contains(typedKey) && unit.getSelectedItem().toString().equals("Custom")
        && !input.getText().isEmpty() && input.isFocusOwner())
    {
      e.consume();
    }
    else
    {
      calcFunction(command);
    }
    System.out.println("Pressed");
  }

  @Override
  public void keyReleased(final KeyEvent e)
  {
    // Auto-generated method stub

  }

  /**
   * Helper method to hold all commands for the user interface.
   *
   * @param command
   *          the command to react to
   */
  private void calcFunction(final String command)
  {
    if (command.equals("Edit"))
    {
      new PrefrencesWindow(this);
    }
    else if (command.equals(exitItem.getText())) // File -> Exit
    {
      hw.dispose();
      this.dispose(); // Closes window.
    }
    else if (command.equals(decValue) && !input.getText().contains(decValue)
        && !input.getText().isEmpty())
    {
      input.setText(input.getText() + decValue); // Adds a decimal point.
    }
    else if (command.equals(backValue))
    {
      String currentText = input.getText();
      if (!currentText.isEmpty())
      {
        if (input.getText().contains("-") && input.getText().length() == 2)
        {
          input.setText("");
        }
        else
        {
          input.setText(currentText.substring(0, currentText.length() - 1));
        }
      }
    }
    else if (command.equals(newItem.getText()))
    {
      CalculatorUI newCalculator = new CalculatorUI();
      newCalculator.setVisible(true);
    }
    else if (command.equals(expValue) || command.equals(addValue) || command.equals(subValue)
        || command.equals(mulValue) || command.equals(divValue))
    {
      if (!unit.getSelectedItem().equals(STRINGS.getString("UNIT")))
      {
        if (operandCounter == 2 && equalsCounter == 1 && input.getText().isEmpty()
            && !display.getText().equals(errValue))
        {
          equalsCounter = 0;
          operandCounter = 1;
          if (!Calculations.getResultUnit().isEmpty()
              && Calculations.getResultUnit().charAt(0) == '<')
          {
            String displayUnit = "";
            char[] chArray = Calculations.getResultUnit().toCharArray();
            for (int i = 6; i < chArray.length; i++)
            {
              if (chArray[i] == '<')
              {
                displayUnit += result.getUnit().substring(6, i);
                break;
              }
            }
            String power = "";
            for (int i = 11 + displayUnit.length(); i < chArray.length; i++)
            {
              if (chArray[i] == '<')
              {
                power += result.getUnit().substring(11, i);
                break;
              }
            }
            displayUnit = addExponent(displayUnit, power);
            if (command.equals(expValue))
            {
              display.setText(Calculations.getResultValue() + displayUnit + spaceValue + "^");
            }
            else
            {
              display.setText(Calculations.getResultValue() + displayUnit + spaceValue + command);
            }
          }
          else
          {
            if (command.equals(expValue))
            {
              display.setText(
                  Calculations.getResultValue() + Calculations.getResultUnit() + spaceValue + "^");
            }
            else
            {
              display.setText(Calculations.getResultValue() + Calculations.getResultUnit()
                  + spaceValue + command);
            }
          }
          Calculations.setFirstOperand(Calculations.getResultValue(), Calculations.getResultUnit());
          middleOpCheck(command); // Sets middle operator based on command value.
        }
        else if (operandCounter == 2 && equalsCounter == 1 && !display.getText().equals(errValue))
        { // Ending
          // running
          // calculations.
          // Adds
          // first
          // operand
          // to
          // Calculations
          // and
          // operator
          if (unit.getSelectedItem().toString().equals("Custom"))
          {
            double valueOfFirst = 0;
            String inputText = input.getText();
            int subStringStart = 0;
            int breakCounter = 0;
            for (int i = 0; i < inputText.length(); i++)
            {
              if (Character.isLetter(inputText.charAt(i)))
              {
                valueOfFirst = Double.parseDouble(inputText.substring(0, i));
                breakCounter++;
                break;
              }
              subStringStart++;
            }
            if (breakCounter == 0)
            {
              valueOfFirst = Double.parseDouble(inputText);
            }
            String unitSubstring;
            if (subStringStart < input.getText().length())
            {
              unitSubstring = input.getText().substring(subStringStart);
            }
            else
            {
              unitSubstring = "";
            }
            addCustomUnit(unitSubstring);
            if (command.equals(expValue))
            {
              display.setText(valueOfFirst + unitSubstring + spaceValue + "^");
            }
            else
            {
              display.setText(valueOfFirst + unitSubstring + spaceValue + command);
            }
            display.setText(valueOfFirst + unitSubstring + spaceValue + command);
            Calculations.setFirstOperand(valueOfFirst, unitSubstring);
          }
          else
          {
            if (command.equals(expValue))
            {
              display.setText(input.getText() + unit.getSelectedItem() + spaceValue + "^");
            }
            else
            {
              display.setText(input.getText() + unit.getSelectedItem() + spaceValue + command);
            }

            Calculations.setFirstOperand(Double.parseDouble(input.getText()),
                unit.getSelectedItem().toString());
          }
          middleOpCheck(command); // Sets middle operator based on command value.
          input.setText("");
          operandCounter = 1;
          equalsCounter = 0;
        }
        else if (!input.getText().isEmpty() && (operandCounter == 2 || operandCounter == 0))
        {
          if (unit.getSelectedItem().toString().equals("Custom"))
          {
            double valueOfFirst = 0;
            String inputText = input.getText();
            int subStringStart = 0;
            int breakCounter = 0;
            for (int i = 0; i < inputText.length(); i++)
            {
              if (Character.isLetter(inputText.charAt(i)))
              {
                valueOfFirst = Double.parseDouble(inputText.substring(0, i));
                breakCounter++;
                break;
              }
              subStringStart++;
            }
            if (breakCounter == 0)
            {
              valueOfFirst = Double.parseDouble(inputText);
            }
            String unitSubstring;
            if (subStringStart < input.getText().length())
            {
              unitSubstring = input.getText().substring(subStringStart);
            }
            else
            {
              unitSubstring = "";
            }
            addCustomUnit(unitSubstring);

            if (command.equals(expValue))
            {
              display.setText(valueOfFirst + unitSubstring + spaceValue + "^");
            }
            else
            {
              display.setText(valueOfFirst + unitSubstring + spaceValue + command);
            }
            Calculations.setFirstOperand(valueOfFirst, unitSubstring);
          }
          else
          {
            if (command.equals(expValue))
            {
              display.setText(input.getText() + unit.getSelectedItem() + spaceValue + "^");
            }
            else
            {
              display.setText(input.getText() + unit.getSelectedItem() + spaceValue + command);
            }
            Calculations.setFirstOperand(Double.parseDouble(input.getText()),
                unit.getSelectedItem().toString());
          }
          middleOpCheck(command); // Sets middle operator based on command value.
          input.setText("");
          operandCounter = 1;
          equalsCounter = 0;
        }
      }
    }
    else if (command.equals(clrValue))
    {
      input.setText("");
    }
    else if (command.equals(delValue))
    {
      input.setText("");
      display.setText("");
      operandCounter = 0;
      equalsCounter = 0;
    }
    else if (command.equals(equValue) && equalsCounter == 0 && operandCounter == 1
        && !input.getText().isEmpty())
    {
      if (unit.getSelectedItem().toString().equals("Custom"))
      {
        double valueOfSecond = 0;
        String inputText = input.getText();
        int subStringStart = 0;
        int breakCounter = 0;
        for (int i = 0; i < inputText.length(); i++)
        {
          if (Character.isLetter(inputText.charAt(i)))
          {
            valueOfSecond = Double.parseDouble(inputText.substring(0, i));
            breakCounter++;
            break;
          }
          subStringStart++;
        }
        if (breakCounter == 0)
        {
          valueOfSecond = Double.parseDouble(inputText);
        }
        String unitSubstring;
        if (subStringStart < input.getText().length())
        {
          unitSubstring = input.getText().substring(subStringStart);
        }
        else
        {
          unitSubstring = "";
        }
        display.setText(display.getText() + spaceValue + valueOfSecond + unitSubstring + " = ");
        Calculations.setSecondOperand(valueOfSecond, unitSubstring);
      }
      else
      {
        display.setText(
            display.getText() + spaceValue + input.getText() + unit.getSelectedItem() + " = ");
        Calculations.setSecondOperand(Double.parseDouble(input.getText()),
            unit.getSelectedItem().toString());
      }
      input.setText("");
      result = Calculations.calculate(400);
      if (result == null)
      {
        display.setText(errValue);
      }
      else
      {
        if (!result.getUnit().isEmpty() && result.getUnit().charAt(0) == '<')
        {
          String displayUnit = "";
          char[] chArray = result.getUnit().toCharArray();
          for (int i = 6; i < chArray.length; i++)
          {
            if (chArray[i] == '<')
            {
              displayUnit += result.getUnit().substring(6, i);
              break;
            }
          }
          String power = "";
          for (int i = 11 + displayUnit.length(); i < chArray.length; i++)
          {
            if (chArray[i] == '<')
            {
              power += result.getUnit().substring(11, i);
              break;
            }
          }
          displayUnit = addExponent(displayUnit, power);
          display.setText(display.getText() + result.getValue() + displayUnit);
        }
        else
        {
          display.setText(display.getText() + result.getValue() + result.getUnit());
        }
      }
      hw.addCalculation(display.getText());
      equalsCounter = 1;
      operandCounter = 2;
    }
    else if (command.equals(aboutItem.getText())) // Help -> About
    {
      try
      {
        AboutDialog aboutDialog = new AboutDialog(LOCALE, this);
        aboutDialog.setVisible(true);
      }
      catch (IOException ex)
      {
        ex.printStackTrace();
      }
    }
    else if (command.equals(helpItem.getText())) // Help -> Help
    {
      String id = "temp";
      String subdir = "/help/en";
      try
      {
        Path tempDirectory = ResourceCopier.copyResourcesToTemp(id, subdir);
        Path htmlFile = tempDirectory.resolve("help.html");
        // System.out.println(htmlFile.toUri());
        Desktop.getDesktop().browse(htmlFile.toUri());
      }
      catch (IOException e1)
      {
        e1.printStackTrace();
      }
      catch (URISyntaxException e1)
      {
        e1.printStackTrace();
      }

    }
    else
    {
      for (int i = 0; i < numberButtons.length; i++)
      {
        if (command.equals(numberButtons[i].getText()))
        {
          input.setText(input.getText() + command);
        }
      }
      char sign = input.getText().charAt(0);
      if (command.equals(signValue) && sign != '-')
      {
        input.setText(subValue + input.getText());
      }
      else if (command.equals(signValue) && sign == '-')
      {
        input.setText(input.getText().substring(1));
      }
    }

    if (command.equals(inverseValue))
    {
      display.setText(
          String.valueOf(1 / Double.parseDouble(input.getText())) + unit.getSelectedItem());
      input.setText("");
    }
  }

  private void middleOpCheck(final String command)
  {
    if (command.equals(addValue))
    {
      Calculations.setMiddleOperator(Operator.ADD);
    }
    else if (command.equals(subValue))
    {
      Calculations.setMiddleOperator(Operator.SUBTRACT);
    }
    else if (command.equals(divValue))
    {
      Calculations.setMiddleOperator(Operator.DIVIDE);
    }
    else if (command.equals(mulValue))
    {
      Calculations.setMiddleOperator(Operator.MULTIPLY);
    }
    else
    {
      Calculations.setMiddleOperator(Operator.POWER);
    }
  }

  /**
   * Helper method for handling formatting and printing.
   */
  private void handlePrintCommand()
  {
    String historyData = hw.getHistoryData();
    if (historyData.isEmpty())
    {
      JOptionPane.showMessageDialog(this, "There is no history to print.", "No History",
          JOptionPane.INFORMATION_MESSAGE);
      return;
    }

    // Prepares the text for printing
    JTextArea printArea = new JTextArea(historyData);
    printArea.setFont(new Font("Monospaced", Font.PLAIN, 12)); // Ensures the printout is readable
    try
    {
      printArea.print();
    }
    catch (java.awt.print.PrinterException ex)
    {
      JOptionPane.showMessageDialog(this, "Failed to print history.", "Print Error",
          JOptionPane.ERROR_MESSAGE);
    }
  }

  @Override
  public void componentResized(ComponentEvent e)
  {
    // Not used.
  }

  @Override
  public void componentMoved(ComponentEvent e)
  {
    hw.setWindowLocation(this.getX(), this.getY(), this.getWidth());
  }

  @Override
  public void componentShown(ComponentEvent e)
  {
    hw.setVisible(true);
  }

  @Override
  public void componentHidden(ComponentEvent e)
  {
    hw.setVisible(false);
  }

  private String addExponent(String s, String pow)
  {
    char[] powArr = pow.toCharArray();
    String toReturn = s;
    for (int i = 0; i < powArr.length; i++)
    {
      switch (powArr[i])
      {
        case '0':
          toReturn += "\u2070";
          break;
        case '1':
          toReturn += "\u00B9";
          break;
        case '2':
          toReturn += "\u00B2";
          break;
        case '3':
          toReturn += "\u00B3";
          break;
        case '4':
          toReturn += "\u2074";
          break;
        case '5':
          toReturn += "\u2075";
          break;
        case '6':
          toReturn += "\u2076";
          break;
        case '7':
          toReturn += "\u2077";
          break;
        case '8':
          toReturn += "\u2078";
          break;
        case '9':
          toReturn += "\u2079";
          break;
      }
    }
    return toReturn;
  }

  public void addCustomUnit(String customUnit)
  {
    try
    {
      File file = new File(fileName);
      file.createNewFile();
      if (!itemsInDropdown.contains(customUnit))
      {
        FileWriter fw = new FileWriter(fileName);
        fw.write(customUnit + "\n");
        fw.close();
        unit.addItem(customUnit);
        itemsInDropdown.add(customUnit);
      }
    }
    catch (IOException ioe)
    {
      System.out.println("Fail - addCustomUnit");
    }
  }
}

// This is new code to replace the old file handling for JAR implementation
// URL helpUrl = getClass().getResource("/gui/help.html");
// if (Desktop.isDesktopSupported() && helpUrl != null)
// {
// try
// {
// File tempHelpFile = File.createTempFile("help", ".html");
// tempHelpFile.deleteOnExit();
// try (InputStream is = helpUrl.openStream();
// FileOutputStream fos = new FileOutputStream(tempHelpFile))
// {
// byte[] buffer = new byte[1024];
// int bytesRead;
// while ((bytesRead = is.read(buffer)) != -1)
// {
// fos.write(buffer, 0, bytesRead);
// }
// }
// Desktop.getDesktop().browse(tempHelpFile.toURI());
// }
// catch (IOException e)
// {
// e.printStackTrace();
// }
