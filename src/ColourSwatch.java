import javax.swing.JButton;
import java.awt.*;

/**
 * A small round button that shows one background colour.
 * It INHERITS from JButton. A ring is drawn around the selected colour.
 */
public class ColourSwatch extends JButton {

    private final Color colour;
    private boolean selected;
    private Color ringColour = Color.WHITE;

    public ColourSwatch(Color colour, String name) {
        this.colour = colour;
        setToolTipText(name);
        setContentAreaFilled(false);
        setBorderPainted(false);
        setFocusPainted(false);
        setOpaque(false);
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        setPreferredSize(new Dimension(34, 34));
    }

    public Color getColour() {
        return colour;
    }

    public void setSelectedSwatch(boolean selected, Color ringColour) {
        this.selected = selected;
        this.ringColour = ringColour;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        int size = Math.min(getWidth(), getHeight());
        g2.setColor(colour);
        g2.fillOval(5, 5, size - 10, size - 10);
        g2.setColor(new Color(128, 128, 128, 90));
        g2.drawOval(5, 5, size - 11, size - 11);
        if (selected) {
            g2.setColor(ringColour);
            g2.setStroke(new BasicStroke(2f));
            g2.drawOval(1, 1, size - 3, size - 3);
        }
        g2.dispose();
    }
}
