import javax.swing.ImageIcon;
import javax.swing.JFrame;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

/**
 * BaseWindow is the PARENT class.
 * It creates the JFrame and holds settings that any window can reuse.
 */
public class BaseWindow {

    // JFrame variable named "frame" (protected so the child class can use it)
    protected JFrame frame;

    // Font used across the app (first one installed on the computer is chosen)
    protected static final String FONT = pickFont("Segoe UI", "Inter", "Helvetica Neue", "Arial");

    /** Constructor of the parent class: builds and configures the frame. */
    public BaseWindow(String title, int width, int height) {
        frame = new JFrame();
        frame.setTitle(title);
        frame.setSize(width, height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
    }

    /** Loads an image from the src folder as an ImageIcon. */
    protected ImageIcon loadIcon(String fileName) {
        URL url = getClass().getResource("/" + fileName);
        if (url != null) {
            return new ImageIcon(url);
        }
        return new ImageIcon("src/" + fileName);
    }

    /** Returns a resized copy of an ImageIcon. */
    protected ImageIcon scaleIcon(ImageIcon icon, int width, int height) {
        Image scaled = icon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(scaled);
    }

    /** Shows the window on the screen. */
    public void showWindow() {
        frame.setVisible(true);
    }

    private static String pickFont(String... names) {
        List<String> installed = Arrays.asList(
                GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames());
        for (String name : names) {
            if (installed.contains(name)) {
                return name;
            }
        }
        return "SansSerif";
    }
}
