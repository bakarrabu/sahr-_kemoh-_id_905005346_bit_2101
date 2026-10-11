import javax.swing.ImageIcon;
import javax.swing.JFrame;
import java.awt.Image;
import java.net.URL;

/**
 * BaseWindow is the PARENT (super) class.
 * It holds the JFrame and the common window settings that any
 * Swing application needs. SwingAssignment INHERITS from this class.
 */
public class BaseWindow {

    // JFrame variable named "frame" (protected so the child class can use it)
    protected JFrame frame;

    /**
     * Constructor of the parent class.
     * It instantiates the frame and applies title, size and close behaviour.
     *
     * @param title  text shown on the title bar
     * @param width  width of the window in pixels
     * @param height height of the window in pixels
     */
    public BaseWindow(String title, int width, int height) {
        frame = new JFrame();                                  // instantiate the frame
        frame.setTitle(title);                                 // "Java Swing Assignment"
        frame.setSize(width, height);                          // 800 x 800
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // close app when X is clicked
        frame.setLocationRelativeTo(null);                     // centre on screen
    }

    /**
     * Loads an image file as an ImageIcon.
     * First looks on the classpath (works in IntelliJ / VS Code),
     * then falls back to the file system.
     */
    protected ImageIcon loadIcon(String fileName) {
        URL url = getClass().getResource("/" + fileName);
        if (url != null) {
            return new ImageIcon(url);
        }
        return new ImageIcon("src/" + fileName);
    }

    /** Resizes an ImageIcon so it fits nicely inside a component. */
    protected ImageIcon scaleIcon(ImageIcon icon, int width, int height) {
        Image scaled = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(scaled);
    }

    /** Makes the window visible on the screen. */
    public void showWindow() {
        frame.setVisible(true);
    }
}
