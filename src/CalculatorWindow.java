import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

/**
 * Creates a new calculator window.
 * @author Leonardo Ledda (LeddaZ)
 */
public class CalculatorWindow extends JFrame {

    /**
     * Creates the components and places them in the
     * calculator window.
     */
    public CalculatorWindow() {
        super("Calculator");
        setSize(450, 500);

        JPanel displayPanel = new JPanel();
        displayPanel.setLayout(new GridLayout());
        add(displayPanel, "North");

        JTextField displayField = new JTextField("", SwingConstants.RIGHT);
        displayField.setFont(new Font("sans-serif", Font.PLAIN, 20));
        displayField.setBorder(new EmptyBorder(10, 10, 10, 10));
        displayField.setBackground(new Color(238, 238, 238));
        displayField.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        displayPanel.add(displayField);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(5, 4));
        add(buttonPanel, "Center");

        String[] buttonLabels = {"rand", "sqrt", "x^y", "CE", "=", "sin", "9", "8", "7", "X", "cos", "6", "5", "4", ":", "tan", "3",
                "2", "1", "-", "log10", "+/-", "0", ".", "+"};
        JButton[] buttons = new JButton[buttonLabels.length+1];
        for(int i = 0; i < buttonLabels.length; i++) {
            JButton b = new JButton(buttonLabels[i]);
            if (isInt(buttonLabels[i]))
                b.addMouseListener(new CalculatorListener(this, displayField, true));
            else
                b.addMouseListener(new CalculatorListener(this, displayField, false));
            buttons[i] = b;
            buttonPanel.add(b);
        }

        JPanel darkModePanel = new JPanel();
        darkModePanel.setLayout(new GridLayout());
        JButton darkModeButton = new JButton("Dark mode");
        darkModePanel.add(darkModeButton);
        buttons[buttonLabels.length] = darkModeButton;
        darkModeButton.addMouseListener(new DarkModeListener(displayField, buttons, buttonPanel, darkModeButton));
        add(darkModePanel, "South");

        displayField.addKeyListener(new CalculatorListener(this, displayField, false));
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    // Determines if a string contains an integer.
    private boolean isInt(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

}
