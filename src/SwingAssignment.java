import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * PROG209 Event Driven Programming - Individual Assignment
 * Basic Interactive GUI
 *
 * SwingAssignment is the CHILD class: it INHERITS the frame from BaseWindow.
 */
public class SwingAssignment extends BaseWindow {

    // JPanel variable named "panel"
    private JPanel panel;

    // JLabel variable named "label"
    private JLabel label;

    private JLabel titleLabel;
    private final List<JLabel> captions = new ArrayList<>();
    private final List<ColourSwatch> swatches = new ArrayList<>();
    private RoundedPanel controls;
    private RoundedPanel fieldBox;
    private JTextField textField;
    private RoundedButton changeTextButton;
    private RoundedButton customColourButton;
    private RoundedButton resetButton;

    private Color cardFill;
    private Color cardBorder;
    private Color placeholderColour;

    private static final String DEFAULT_TEXT =
            "Welcome to Java Swing First Individual Assignment by Sahr";

    // Colour palette
    private static final Color MIDNIGHT = new Color(15, 23, 42);
    private static final Color INDIGO = new Color(99, 102, 241);
    private static final Color CYAN = new Color(34, 211, 238);
    private static final Color DEFAULT_BACKGROUND = MIDNIGHT;

    /** Constructor of the child class. */
    public SwingAssignment() {
        super("Java Swing Assignment", 800, 800);   // calls the parent constructor

        // ImageIcon: own application icon
        ImageIcon appIcon = loadIcon("logo.png");
        frame.setIconImage(appIcon.getImage());

        // JPanel (paints a soft glow and a card behind the label)
        panel = new JPanel(new BorderLayout(0, 24)) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                paintBackgroundDecoration((Graphics2D) g);
            }
        };
        panel.setBorder(BorderFactory.createEmptyBorder(28, 36, 30, 36));

        // JLabel with the welcome text and the ImageIcon
        label = new JLabel(DEFAULT_TEXT);
        label.setIcon(scaleIcon(appIcon, 150, 150));
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setHorizontalTextPosition(SwingConstants.CENTER);
        label.setVerticalTextPosition(SwingConstants.BOTTOM);
        label.setIconTextGap(28);
        label.setFont(new Font(FONT, Font.BOLD, 22));
        label.setBorder(BorderFactory.createEmptyBorder(24, 24, 24, 24));

        panel.add(createHeader(appIcon), BorderLayout.NORTH);
        panel.add(label, BorderLayout.CENTER);              // add the label to the panel
        panel.add(createControls(), BorderLayout.SOUTH);

        frame.add(panel);                                    // add the panel to the frame

        registerEvents();
        applyTheme(DEFAULT_BACKGROUND);
    }

    /** Top bar: small logo and title. */
    private JPanel createHeader(ImageIcon appIcon) {
        JPanel header = new JPanel(new BorderLayout(14, 0));
        header.setOpaque(false);

        header.add(new JLabel(scaleIcon(appIcon, 46, 46)), BorderLayout.WEST);

        titleLabel = new JLabel("Java Swing Assignment");
        titleLabel.setFont(new Font(FONT, Font.BOLD, 22));
        header.add(titleLabel, BorderLayout.CENTER);
        return header;
    }

    /** Bottom card: text field, buttons and colour swatches. */
    private JPanel createControls() {
        controls = new RoundedPanel(24, Color.WHITE, Color.WHITE);
        controls.setLayout(new GridBagLayout());
        controls.setBorder(BorderFactory.createEmptyBorder(18, 22, 20, 22));

        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.weightx = 1;
        c.fill = GridBagConstraints.HORIZONTAL;

        // Row 1: caption
        c.gridy = 0;
        c.insets = new Insets(0, 2, 8, 0);
        controls.add(caption("CHANGE THE LABEL TEXT"), c);

        // Row 2: text field + Change Text button
        textField = new JTextField() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (getText().isEmpty()) {
                    Graphics2D g2 = (Graphics2D) g.create();
                    g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING,
                            RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
                    g2.setColor(placeholderColour);
                    g2.setFont(getFont());
                    FontMetrics fm = g2.getFontMetrics();
                    int y = (getHeight() + fm.getAscent() - fm.getDescent()) / 2;
                    g2.drawString("Type something new for the label…", getInsets().left, y);
                    g2.dispose();
                }
            }
        };
        textField.setOpaque(false);
        textField.setFont(new Font(FONT, Font.PLAIN, 15));
        textField.setBorder(BorderFactory.createEmptyBorder(0, 16, 0, 16));

        fieldBox = new RoundedPanel(14, Color.WHITE, Color.WHITE);
        fieldBox.setLayout(new BorderLayout());
        fieldBox.add(textField);

        changeTextButton = new RoundedButton("Change Text", INDIGO, CYAN, false);

        JPanel textRow = new JPanel(new BorderLayout(12, 0));
        textRow.setOpaque(false);
        textRow.add(fieldBox, BorderLayout.CENTER);
        textRow.add(changeTextButton, BorderLayout.EAST);
        textRow.setPreferredSize(new Dimension(10, 46));

        c.gridy = 1;
        c.insets = new Insets(0, 0, 18, 0);
        controls.add(textRow, c);

        // Row 3: caption
        c.gridy = 2;
        c.insets = new Insets(0, 2, 8, 0);
        controls.add(caption("PANEL BACKGROUND"), c);

        // Row 4: swatches + Custom + Reset
        JPanel swatchRow = new JPanel(new FlowLayout(FlowLayout.LEFT, 6, 0));
        swatchRow.setOpaque(false);
        addSwatch(swatchRow, MIDNIGHT, "Midnight");
        addSwatch(swatchRow, new Color(30, 27, 75), "Deep Indigo");
        addSwatch(swatchRow, new Color(6, 78, 59), "Forest");
        addSwatch(swatchRow, new Color(127, 29, 29), "Wine");
        addSwatch(swatchRow, new Color(248, 250, 252), "Cloud");
        addSwatch(swatchRow, new Color(254, 243, 199), "Sand");

        customColourButton = new RoundedButton("Custom…", INDIGO, CYAN, true);
        customColourButton.setPreferredSize(new Dimension(110, 40));
        resetButton = new RoundedButton("Reset", INDIGO, CYAN, true);
        resetButton.setPreferredSize(new Dimension(96, 40));

        JPanel actionRow = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        actionRow.setOpaque(false);
        actionRow.add(customColourButton);
        actionRow.add(resetButton);

        JPanel bottomRow = new JPanel(new BorderLayout());
        bottomRow.setOpaque(false);
        bottomRow.add(swatchRow, BorderLayout.WEST);
        bottomRow.add(actionRow, BorderLayout.EAST);

        c.gridy = 3;
        c.insets = new Insets(0, 0, 0, 0);
        controls.add(bottomRow, c);
        return controls;
    }

    private JLabel caption(String text) {
        JLabel caption = new JLabel(text);
        caption.setFont(new Font(FONT, Font.BOLD, 11));
        captions.add(caption);
        return caption;
    }

    private void addSwatch(JPanel row, Color colour, String name) {
        ColourSwatch swatch = new ColourSwatch(colour, name);
        swatch.addActionListener(e -> applyTheme(swatch.getColour()));
        swatches.add(swatch);
        row.add(swatch);
    }

    /** Event handling: the program reacts to the user's actions. */
    private void registerEvents() {
        changeTextButton.addActionListener(e -> changeLabelText());
        textField.addActionListener(e -> changeLabelText());   // pressing Enter

        customColourButton.addActionListener(e -> {
            Color chosen = JColorChooser.showDialog(frame,
                    "Choose a background colour", panel.getBackground());
            if (chosen != null) {
                applyTheme(chosen);
            }
        });

        resetButton.addActionListener(e -> {
            label.setText(DEFAULT_TEXT);
            textField.setText("");
            applyTheme(DEFAULT_BACKGROUND);
        });
    }

    /** Changes the text of the JLabel. */
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

    /**
     * Changes the background of the JPanel and adjusts every text colour
     * so the writing stays readable on both dark and light backgrounds.
     */
    private void applyTheme(Color background) {
        panel.setBackground(background);

        boolean light = isLight(background);
        Color text = light ? MIDNIGHT : new Color(248, 250, 252);
        Color muted = light ? new Color(71, 85, 105) : new Color(148, 163, 184);
        cardFill = light ? new Color(15, 23, 42, 10) : new Color(255, 255, 255, 14);
        cardBorder = light ? new Color(15, 23, 42, 32) : new Color(255, 255, 255, 30);
        placeholderColour = muted;

        label.setForeground(text);
        titleLabel.setForeground(text);
        for (JLabel caption : captions) {
            caption.setForeground(muted);
        }

        controls.setColours(cardFill, cardBorder);
        fieldBox.setColours(light ? Color.WHITE : new Color(2, 6, 23, 120), cardBorder);
        textField.setForeground(text);
        textField.setCaretColor(text);
        customColourButton.setForeground(text);
        resetButton.setForeground(text);

        for (ColourSwatch swatch : swatches) {
            swatch.setSelectedSwatch(swatch.getColour().equals(background), text);
        }
        panel.repaint();
    }

    private boolean isLight(Color c) {
        double luminance = (0.299 * c.getRed() + 0.587 * c.getGreen() + 0.114 * c.getBlue()) / 255;
        return luminance > 0.6;
    }

    /** Paints two soft colour glows and the rounded card behind the label. */
    private void paintBackgroundDecoration(Graphics2D g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        int w = panel.getWidth();
        int h = panel.getHeight();
        float[] stops = {0f, 1f};

        g2.setPaint(new RadialGradientPaint(new Point(90, 80), 420, stops,
                new Color[]{new Color(99, 102, 241, 70), new Color(99, 102, 241, 0)}));
        g2.fillRect(0, 0, w, h);
        g2.setPaint(new RadialGradientPaint(new Point(w - 70, h - 90), 420, stops,
                new Color[]{new Color(34, 211, 238, 55), new Color(34, 211, 238, 0)}));
        g2.fillRect(0, 0, w, h);

        Rectangle r = label.getBounds();
        g2.setColor(cardFill);
        g2.fillRoundRect(r.x, r.y, r.width - 1, r.height - 1, 28, 28);
        g2.setColor(cardBorder);
        g2.drawRoundRect(r.x, r.y, r.width - 1, r.height - 1, 28, 28);
        g2.dispose();
    }

    public static void main(String[] args) {
        System.setProperty("awt.useSystemAAFontSettings", "on");
        SwingUtilities.invokeLater(() -> {
            SwingAssignment app = new SwingAssignment();
            app.showWindow();
        });
    }
}
