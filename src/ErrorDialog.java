import java.awt.Color;
import java.awt.Font;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

/**
 * Shows an error dialog.
 * @author Leonardo Ledda (LeddaZ)
 */
public class ErrorDialog extends JDialog {

    /**
     * Constructor.
     * @param f parent JFrame
     * @param title dialog title
     * @param message error message
     */
    public ErrorDialog(JFrame f, String title, String message) {
        super(f, title);

        JTextPane errMsg = new JTextPane();
        errMsg.setFont(new Font("sans-serif", Font.PLAIN, 14));
        errMsg.setEditable(false);
        errMsg.setBackground(new Color(238, 238, 238));

        StyledDocument documentStyle = errMsg.getStyledDocument();
        SimpleAttributeSet centerAttribute = new SimpleAttributeSet();
        StyleConstants.setAlignment(centerAttribute, StyleConstants.ALIGN_CENTER);
        documentStyle.setParagraphAttributes(0, documentStyle.getLength(), centerAttribute, false);

        errMsg.setText(message);
        errMsg.setBorder(new EmptyBorder(10, 10, 10, 10));
        add(errMsg);
        pack();
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
    }

}
