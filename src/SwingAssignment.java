import javax.swing.*;
import java.awt.*;

/**
 * PROG209 Event Driven Programming - Individual Assignment
 * Title   : Basic Interactive GUI
 * Student : Sahr
 *
 * SwingAssignment is the CHILD class. It INHERITS the JFrame "frame"
 * and the window setup from BaseWindow using the "extends" keyword.
 */
public class SwingAssignment extends BaseWindow {

    // JPanel variable named "panel"
    private JPanel panel;

    // JLabel variable named "label"
    private JLabel label;

    // Extra components for the "Addition" part (change text and background)
    private JTextField textField;
    private JButton changeTextButton;
    private JButton changeColourButton;
    private JButton resetButton;

    private static final String DEFAULT_TEXT =
            "Welcome to Java Swing First Individual Assignment by Sahr";
    private static final Color DEFAULT_BACKGROUND = new Color(232, 240, 254);

    /**
     * Constructor of the child class.
     * super(...) calls the constructor of the parent class (BaseWindow),
     * which instantiates the frame, sets the title, size and close operation.
     */
    public SwingAssignment() {
        super("Java Swing Assignment", 800, 800);

        // ---------- ImageIcon: our own application icon ----------
        ImageIcon appIcon = loadIcon("KEMOH.png");
        frame.setIconImage(appIcon.getImage());          // icon on title bar / taskbar

        // ---------- JPanel ----------
        panel = new JPanel();                             // instantiate the panel
        panel.setLayout(new BorderLayout(10, 10));
        panel.setBackground(DEFAULT_BACKGROUND);
        panel.setBorder(BorderFactory.createEmptyBorder(40, 40, 40, 40));

        // ---------- JLabel ----------
        label = new JLabel(DEFAULT_TEXT);                 // instantiate with required text
        label.setIcon(scaleIcon(appIcon, 160, 160));      // ImageIcon shown on the label
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setHorizontalTextPosition(SwingConstants.CENTER);
        label.setVerticalTextPosition(SwingConstants.BOTTOM);
        label.setIconTextGap(25);
        label.setFont(new Font("Tahoma", Font.BOLD, 20));
        label.setForeground(new Color(30, 30, 60));

        // Add the label to the panel
        panel.add(label, BorderLayout.CENTER);

        // ---------- Addition: controls ----------
        panel.add(createControls(), BorderLayout.SOUTH);

        // Add the panel to the frame
        frame.add(panel);
    }

    /** Builds the controls used to change the label text and panel background. */
    private JPanel createControls() {
        JPanel controls = new JPanel(new GridLayout(2, 1, 10, 10));
        controls.setOpaque(false);

        // Row 1: text field + "Change Text" button
        JPanel textRow = new JPanel(new BorderLayout(10, 0));
        textRow.setOpaque(false);
        textField = new JTextField();
        textField.setFont(new Font("Tahoma", Font.PLAIN, 15));
        textField.setToolTipText("Type new text for the label");
        changeTextButton = new JButton("Change Text");
        textRow.add(textField, BorderLayout.CENTER);
        textRow.add(changeTextButton, BorderLayout.EAST);

        // Row 2: "Change Background" and "Reset" buttons
        JPanel buttonRow = new JPanel(new GridLayout(1, 2, 10, 0));
        buttonRow.setOpaque(false);
        changeColourButton = new JButton("Change Background");
        resetButton = new JButton("Reset");
        buttonRow.add(changeColourButton);
        buttonRow.add(resetButton);

        controls.add(textRow);
        controls.add(buttonRow);

        registerEvents();
        return controls;
    }

    /** Event handling: each button reacts to a click (event-driven programming). */
    private void registerEvents() {
        // Change the text of the JLabel
        changeTextButton.addActionListener(e -> changeLabelText());
        textField.addActionListener(e -> changeLabelText());   // pressing Enter also works

        // Change the background of the JPanel
        changeColourButton.addActionListener(e -> {
            Color chosen = JColorChooser.showDialog(frame,
                    "Choose a background colour", panel.getBackground());
            if (chosen != null) {
                panel.setBackground(chosen);
            }
        });

        // Put everything back to the original state
        resetButton.addActionListener(e -> {
            label.setText(DEFAULT_TEXT);
            panel.setBackground(DEFAULT_BACKGROUND);
            textField.setText("");
        });
    }

    private void changeLabelText() {
        String newText = textField.getText().trim();
        if (newText.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Please type some text first.",
                    "No text entered", JOptionPane.WARNING_MESSAGE);
            return;
        }
        label.setText(newText);
        textField.setText("");
    }

    public static void main(String[] args) {
        // Start the GUI on the Event Dispatch Thread (recommended for Swing)
        SwingUtilities.invokeLater(() -> {
            SwingAssignment app = new SwingAssignment();
            app.showWindow();
        });
    }
}
