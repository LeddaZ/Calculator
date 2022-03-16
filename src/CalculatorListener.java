import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

/**
 * Listener to manage events from a CalculatorWindow.
 * @author Leonardo Ledda (LeddaZ)
 */
public class CalculatorListener extends MouseAdapter {

    private final JFrame f;
    private final JLabel l;
    private final boolean isNumber;
    private static int op = -1;
    private static double n;

    /**
     * Constructor.
     * @param f JFrame associated with the listener (the CalculatorWindow)
     * @param l JLabel used as the calculator's display
     * @param isNumber true if the button contains a number, false otherwise
     */
    public CalculatorListener(JFrame f, JLabel l, boolean isNumber) {
        this.f = f;
        this.l = l;
        this.isNumber = isNumber;
    }

    /**
     * Called when a button is clicked.
     */
    public void mouseClicked(MouseEvent e) {
        JButton b = (JButton) e.getSource();
        if(isNumber) {
            l.setText(l.getText() + b.getText());
            if(op == -1)
                n = Double.parseDouble(l.getText());
        }
        else {
            switch (b.getText()) {
                case "+" -> {
                    op = 0;
                    l.setText("");
                }
                case "-" -> {
                    op = 1;
                    l.setText("");
                }
                case "X" -> {
                    op = 2;
                    l.setText("");
                }
                case ":" -> {
                    op = 3;
                    l.setText("");
                }
                case "x^y" -> {
                    op = 4;
                    l.setText("");
                }
                case "+/-" -> {
                    try {
                        if(l.getText().charAt(0) != '-')
                            l.setText("-" + l.getText());
                        else
                            l.setText(l.getText().substring(1));
                    } catch(StringIndexOutOfBoundsException ex) {
                        ErrorDialog err = new ErrorDialog(f, "Error", "Cannot invert sign without typing a"
                            + "\nnumber first.");
                    }
                }
                case "rand" -> {
                    Random r = new Random();
                    l.setText(String.valueOf(r.nextDouble(1)));
                }
                case "sin" -> l.setText(String.valueOf(Math.sin(Double.parseDouble(l.getText()))));
                case "cos" -> l.setText(String.valueOf(Math.cos(Double.parseDouble(l.getText()))));
                case "tan" -> l.setText(String.valueOf(Math.tan(Double.parseDouble(l.getText()))));
                case "log10" -> {
                    if(l.getText().charAt(0) == '-')
                        new ErrorDialog(f, "Error", "Cannot calculate the logarithm of a"
                                + "\nnegative number.");
                    else
                        l.setText(String.valueOf(Math.log10(Double.parseDouble(l.getText()))));
                }
                case "." -> l.setText(l.getText() + ".");
                case "sqrt" -> {
                    if(l.getText().charAt(0) == '-')
                        new ErrorDialog(f, "Error", "Cannot calculate the square root of a"
                                + "\nnegative number.");
                    else
                        l.setText(String.valueOf(Math.sqrt(Double.parseDouble(l.getText()))));
                }
                case "=" -> {
                    try {
                        l.setText(String.valueOf(calculate()));
                    } catch(InvalidOperationException ex) {
                        ErrorDialog err = new ErrorDialog(f, "Error", ex.getMessage());
                    }
                }
                case "CE" -> l.setText("");
            }
        }
    }

    // Executes the appropriate operation.
    private double calculate() {
        switch (op) {
            case 0 -> {
                return n + Double.parseDouble(l.getText());
            }
            case 1 -> {
                return n - Double.parseDouble(l.getText());
            }
            case 2 -> {
                return n * Double.parseDouble(l.getText());
            }
            case 3 -> {
                if(l.getText().equals("0"))
                   throw new InvalidOperationException("Cannot divide a number by zero.");
                else
                    return n / Double.parseDouble(l.getText());
            }
            case 4 -> {
                return Math.pow(n, Double.parseDouble(l.getText()));
            }
            default -> {
                return -1;
            }
        }
    }

}
