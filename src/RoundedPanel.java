import javax.swing.JPanel;
import java.awt.*;

/**
 * A JPanel with rounded corners and a soft border.
 * It INHERITS from JPanel and overrides paintComponent().
 */
public class RoundedPanel extends JPanel {

    private final int radius;
    private Color fill;
    private Color border;

    public RoundedPanel(int radius, Color fill, Color border) {
        this.radius = radius;
        this.fill = fill;
        this.border = border;
        setOpaque(false);
    }

    public void setColours(Color fill, Color border) {
        this.fill = fill;
        this.border = border;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(fill);
        g2.fillRoundRect(0, 0, getWidth() - 1, getHeight() - 1, radius, radius);
        g2.setColor(border);
        g2.drawRoundRect(0, 0, getWidth() - 1, getHeight() - 1, radius, radius);
        g2.dispose();
        super.paintComponent(g);
    }
}
