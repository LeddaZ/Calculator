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

        JPanel pDisplay = new JPanel();
        pDisplay.setLayout(new GridLayout());
        add(pDisplay, "North");

        JTextField display = new JTextField("", SwingConstants.RIGHT);
        display.setFont(new Font("sans-serif", Font.PLAIN, 20));
        display.setBorder(new EmptyBorder(10, 10, 10, 10));
        display.setBackground(new Color(238, 238, 238));
        display.setComponentOrientation(ComponentOrientation.RIGHT_TO_LEFT);
        pDisplay.add(display);

        JPanel pNumeri = new JPanel();
        pNumeri.setLayout(new GridLayout(5, 4));
        add(pNumeri, "Center");

        String[] numeri = {"rand", "sqrt", "x^y", "CE", "=", "sin", "9", "8", "7", "X", "cos", "6", "5", "4", ":", "tan", "3",
                "2", "1", "-", "log10", "+/-", "0", ".", "+"};
        for (String s : numeri) {
            JButton b = new JButton(s);
            if (isInt(s))
                b.addMouseListener(new CalculatorListener(this, display, true));
            else
                b.addMouseListener(new CalculatorListener(this, display, false));
            pNumeri.add(b);
        }

        display.addKeyListener(new CalculatorListener(this, display, false));
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
