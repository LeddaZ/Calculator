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

        String[] numeri = {"rand", "sqrt", "x^y", "CE", "=", "sin", "9", "8", "7", "X", "cos", "6", "5", "4", ":", "tan", "3",
                "2", "1", "-", "log10", "+/-", "0", ".", "+"};
        for (String s : numeri) {
            JButton b = new JButton(s);
            if (isInt(s))
                b.addMouseListener(new CalculatorListener(this, displayField, true));
            else
                b.addMouseListener(new CalculatorListener(this, displayField, false));
            buttonPanel.add(b);
        }

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
