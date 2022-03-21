import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

/**
 * Listener to manage events from a CalculatorWindow.
 * @author Leonardo Ledda (LeddaZ)
 */
public class CalculatorListener extends MouseAdapter implements KeyListener {

    private final JFrame f;
    private final JTextField p;
    private final boolean isNumber;
    private static int op = -1;
    private static double n;

    /**
     * Constructor.
     * @param f JFrame associated with the listener (the CalculatorWindow)
     * @param p JTextField used as the calculator's display
     * @param isNumber true if the button contains a number, false otherwise
     */
    public CalculatorListener(JFrame f, JTextField p, boolean isNumber) {
        this.f = f;
        this.p = p;
        this.isNumber = isNumber;
    }

    /**
     * Called when a button is clicked.
     */
    public void mouseClicked(MouseEvent e) {
        JButton b = (JButton) e.getSource();
        if(isNumber)
            p.setText(p.getText() + b.getText());
        else {
            switch (b.getText()) {
                case "+" -> {
                    op = 0;
                    n = Double.parseDouble(p.getText());
                    p.setText("");
                }
                case "-" -> {
                    op = 1;
                    n = Double.parseDouble(p.getText());
                    p.setText("");
                }
                case "X" -> {
                    op = 2;
                    n = Double.parseDouble(p.getText());
                    p.setText("");
                }
                case ":" -> {
                    op = 3;
                    n = Double.parseDouble(p.getText());
                    p.setText("");
                }
                case "x^y" -> {
                    op = 4;
                    n = Double.parseDouble(p.getText());
                    p.setText("");
                }
                case "+/-" -> {
                    try {
                        if(p.getText().charAt(p.getText().length()-1) != '-')
                            p.setText(p.getText() + "-");
                        else
                            p.setText(p.getText().substring(0, p.getText().length()-1));
                    } catch(StringIndexOutOfBoundsException ex) {
                        new ErrorDialog(f, "Error", "Cannot invert sign without typing a"
                            + "\nnumber first.");
                    }
                }
                case "rand" -> {
                    Random r = new Random();
                    p.setText(String.valueOf(r.nextDouble(1)));
                }
                case "sin" -> p.setText(String.valueOf(Math.sin(Double.parseDouble(p.getText()))));
                case "cos" -> p.setText(String.valueOf(Math.cos(Double.parseDouble(p.getText()))));
                case "tan" -> p.setText(String.valueOf(Math.tan(Double.parseDouble(p.getText()))));
                case "log10" -> {
                    if(p.getText().charAt(0) == '-')
                        new ErrorDialog(f, "Error", "Cannot calculate the logarithm of a"
                                + "\nnegative number.");
                    else
                        p.setText(String.valueOf(Math.log10(Double.parseDouble(p.getText()))));
                }
                case "." -> p.setText(p.getText() + ".");
                case "sqrt" -> {
                    if(p.getText().charAt(p.getText().length()-1) == '-')
                        new ErrorDialog(f, "Error", "Cannot calculate the square root of a"
                                + "\nnegative number.");
                    else
                        p.setText(String.valueOf(Math.sqrt(Double.parseDouble(p.getText()))));
                }
                case "=" -> {
                    try {
                        p.setText(String.valueOf(calculate()));
                    } catch(InvalidOperationException ex) {
                        new ErrorDialog(f, "Error", ex.getMessage());
                    }
                }
                case "CE" -> p.setText("");
            }
        }
    }

    // Executes the appropriate operation.
    private double calculate() {
        switch (op) {
            case 0 -> {
                return n + Double.parseDouble(p.getText());
            }
            case 1 -> {
                return n - Double.parseDouble(p.getText());
            }
            case 2 -> {
                return n * Double.parseDouble(p.getText());
            }
            case 3 -> {
                if(p.getText().equals("0"))
                   throw new InvalidOperationException("Cannot divide a number by zero.");
                else
                    return n / Double.parseDouble(p.getText());
            }
            case 4 -> {
                return Math.pow(n, Double.parseDouble(p.getText()));
            }
            default -> {
                return -1;
            }
        }
    }

    /**
     * Invoked when a key has been typed.
     * See the class description for {@link KeyEvent} for a definition of
     * a key typed event.
     *
     * @param e the event to be processed
     */
    @Override
    public void keyTyped(KeyEvent e) {
        char c = e.getKeyChar();
        switch(c) {
            case '+' -> {
                op = 0;
                n = Double.parseDouble(p.getText());
                p.setText("");
            }
            case '-' -> {
                op = 1;
                n = Double.parseDouble(p.getText());
                p.setText("");
            }
            case 'X' -> {
                op = 2;
                n = Double.parseDouble(p.getText());
                p.setText("");
            }
            case '/' -> {
                op = 3;
                n = Double.parseDouble(p.getText());
                p.setText("");
            }
            case '=', '\n' -> {
                try {
                    p.setText(String.valueOf(calculate()));
                } catch(InvalidOperationException ex) {
                    new ErrorDialog(f, "Error", ex.getMessage());
                }
            }
        }
        if (((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE))
            e.consume();
    }

    /**
     * Invoked when a key has been pressed.
     * See the class description for {@link KeyEvent} for a definition of
     * a key pressed event.
     *
     * @param e the event to be processed
     */
    @Override
    public void keyPressed(KeyEvent e) {

    }

    /**
     * Invoked when a key has been released.
     * See the class description for {@link KeyEvent} for a definition of
     * a key released event.
     *
     * @param e the event to be processed
     */
    @Override
    public void keyReleased(KeyEvent e) {

    }
}
