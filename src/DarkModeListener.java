import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JPanel;

/**
 * Listener to handle switching between dark and light mode
 * @author Leonardo Ledda (LeddaZ)
 */
public class DarkModeListener extends MouseAdapter {

    private final JTextField f;
    private final JButton[] buttonArray;
    private final JPanel p;
    private final JButton dmb;
    private static boolean isDarkModeEnabled = false;

    /**
     * Constructor.
     * @param f JTextField used as display
     * @param buttonArray array containing every button in the calculator window
     * @param p JPanel containing the number/operation buttons
     * @param dmb JButton used as dark/light mode switch
     */
    public DarkModeListener(JTextField f, JButton[] buttonArray, JPanel p, JButton dmb) {
        this.f = f;
        this.buttonArray = buttonArray;
        this.p = p;
        this.dmb = dmb;
    }

    /**
     * Called when a button is clicked.
     */
    public void mouseClicked(MouseEvent e) {
        Color dark = new Color(44, 44, 44);
        Color light = new Color(238, 238, 238);
        Color defaultText = new Color(51, 51, 51);
        if(!isDarkModeEnabled) {
            f.setBackground(dark);
            f.setForeground(Color.WHITE);
            p.setBackground(dark);
            for (JButton button : buttonArray) {
                button.setBackground(dark);
                button.setForeground(Color.WHITE);
            }
            dmb.setText("Light mode");
            isDarkModeEnabled = true;
        } else {
            f.setBackground(light);
            f.setForeground(defaultText);
            p.setBackground(light);
            for (JButton button : buttonArray) {
                button.setBackground(new JButton().getBackground());
                button.setForeground(defaultText);
            }
            dmb.setText("Dark mode");
            isDarkModeEnabled = false;
        }
    }

}
