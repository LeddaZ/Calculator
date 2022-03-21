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
    private final JTextField tf;
    private final boolean isNumber;
    private static int op = -1;
    private static double n;

    /**
     * Constructor.
     * @param f JFrame associated with the listener (the CalculatorWindow)
     * @param tf JTextField used as the calculator's display
     * @param isNumber true if the button contains a number, false otherwise
     */
    public CalculatorListener(JFrame f, JTextField tf, boolean isNumber) {
        this.f = f;
        this.tf = tf;
        this.isNumber = isNumber;
    }

    /**
     * Called when a button is clicked.
     */
    public void mouseClicked(MouseEvent e) {
        try {
            JButton b = (JButton) e.getSource();
            if(isNumber)
                tf.setText(tf.getText() + b.getText());
            else {
                switch (b.getText()) {
                    case "+" -> {
                        op = 0;
                        n = Double.parseDouble(tf.getText());
                        tf.setText("");
                    }
                    case "-" -> {
                        op = 1;
                        n = Double.parseDouble(tf.getText());
                        tf.setText("");
                    }
                    case "X" -> {
                        op = 2;
                        n = Double.parseDouble(tf.getText());
                        tf.setText("");
                    }
                    case ":" -> {
                        op = 3;
                        n = Double.parseDouble(tf.getText());
                        tf.setText("");
                    }
                    case "x^y" -> {
                        op = 4;
                        n = Double.parseDouble(tf.getText());
                        tf.setText("");
                    }
                    case "+/-" -> {
                        try {
                            if(tf.getText().charAt(tf.getText().length()-1) != '-')
                                tf.setText(tf.getText() + "-");
                            else
                                tf.setText(tf.getText().substring(0, tf.getText().length()-1));
                        } catch(StringIndexOutOfBoundsException ex) {
                            new ErrorDialog(f, "Error", "Cannot invert sign without typing a"
                                    + "\nnumber first.");
                        }
                    }
                    case "rand" -> {
                        Random r = new Random();
                        tf.setText(String.valueOf(r.nextDouble(1)));
                    }
                    case "sin" -> tf.setText(String.valueOf(Math.sin(Double.parseDouble(tf.getText()))));
                    case "cos" -> tf.setText(String.valueOf(Math.cos(Double.parseDouble(tf.getText()))));
                    case "tan" -> tf.setText(String.valueOf(Math.tan(Double.parseDouble(tf.getText()))));
                    case "log10" -> {
                        if(tf.getText().charAt(0) == '-')
                            new ErrorDialog(f, "Error", "Cannot calculate the logarithm of a"
                                    + "\nnegative number.");
                        else
                            tf.setText(String.valueOf(Math.log10(Double.parseDouble(tf.getText()))));
                    }
                    case "." -> tf.setText(tf.getText() + ".");
                    case "sqrt" -> {
                        if(tf.getText().charAt(tf.getText().length()-1) == '-')
                            new ErrorDialog(f, "Error", "Cannot calculate the square root of a"
                                    + "\nnegative number.");
                        else
                            tf.setText(String.valueOf(Math.sqrt(Double.parseDouble(tf.getText()))));
                    }
                    case "=" -> {
                        try {
                            tf.setText(String.valueOf(calculate()));
                        } catch(InvalidOperationException ex) {
                            new ErrorDialog(f, "Error", ex.getMessage());
                        }
                    }
                    case "CE" -> tf.setText("");
                }
            }
        } catch(NumberFormatException | StringIndexOutOfBoundsException ignored) {

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
        try {
            char c = e.getKeyChar();
            switch(c) {
                case '+' -> {
                    op = 0;
                    n = Double.parseDouble(tf.getText());
                    tf.setText("");
                }
                case '-' -> {
                    op = 1;
                    n = Double.parseDouble(tf.getText());
                    tf.setText("");
                }
                case 'X' -> {
                    op = 2;
                    n = Double.parseDouble(tf.getText());
                    tf.setText("");
                }
                case '/' -> {
                    op = 3;
                    n = Double.parseDouble(tf.getText());
                    tf.setText("");
                }
                case '=', '\n' -> {
                    try {
                        tf.setText(String.valueOf(calculate()));
                    } catch(InvalidOperationException ex) {
                        new ErrorDialog(f, "Error", ex.getMessage());
                    }
                }
            }
            if (((c < '0') || (c > '9')) && (c != KeyEvent.VK_BACK_SPACE))
                e.consume();
        } catch(NumberFormatException ex) {
            e.consume();
        } catch(StringIndexOutOfBoundsException ignored) {

        }
    }

    // Executes the appropriate operation.
    private double calculate() {
        switch (op) {
            case 0 -> {
                return n + Double.parseDouble(tf.getText());
            }
            case 1 -> {
                return n - Double.parseDouble(tf.getText());
            }
            case 2 -> {
                return n * Double.parseDouble(tf.getText());
            }
            case 3 -> {
                if(tf.getText().equals("0"))
                    throw new InvalidOperationException("Cannot divide a number by zero.");
                else
                    return n / Double.parseDouble(tf.getText());
            }
            case 4 -> {
                return Math.pow(n, Double.parseDouble(tf.getText()));
            }
            default -> {
                return -1;
            }
        }
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
