import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
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

        JLabel lDisplay = new JLabel("", SwingConstants.RIGHT);
        lDisplay.setVerticalAlignment(SwingConstants.CENTER);
        lDisplay.setFont(new Font("sans-serif", Font.PLAIN, 20));
        lDisplay.setBorder(new EmptyBorder(10, 10, 10, 10));
        pDisplay.add(lDisplay);

        JPanel pNumeri = new JPanel();
        pNumeri.setLayout(new GridLayout(5, 4));
        add(pNumeri, "Center");

        String[] numeri = {"rand", "sqrt", "x^y", "CE", "=", "sin", "9", "8", "7", "X", "cos", "6", "5", "4", ":", "tan", "3",
                "2", "1", "-", "log10", "+/-", "0", ".", "+"};
        for (String s : numeri) {
            JButton b = new JButton(s);
            if (isInt(s))
                b.addMouseListener(new CalculatorListener(this, lDisplay, true));
            else
                b.addMouseListener(new CalculatorListener(this, lDisplay, false));
            pNumeri.add(b);
        }

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
