import javax.swing.JButton;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * A modern rounded button.
 * It INHERITS from JButton. Filled buttons use a gradient,
 * outline buttons only draw a border. Both react when the mouse hovers.
 */
public class RoundedButton extends JButton {

    private final Color start;
    private final Color end;
    private final boolean outline;
    private boolean hover;

    public RoundedButton(String text, Color start, Color end, boolean outline) {
        super(text);
        this.start = start;
        this.end = end;
        this.outline = outline;

        setFont(new Font(BaseWindow.FONT, Font.BOLD, 14));
        setForeground(Color.WHITE);
        setContentAreaFilled(false);
        setBorderPainted(false);
        setFocusPainted(false);
        setOpaque(false);
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        setPreferredSize(new Dimension(150, 46));

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                hover = true;
                repaint();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                hover = false;
                repaint();
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        int w = getWidth();
        int h = getHeight();
        int arc = 14;

        if (outline) {
            Color fg = getForeground();
            g2.setColor(new Color(fg.getRed(), fg.getGreen(), fg.getBlue(), hover ? 40 : 14));
            g2.fillRoundRect(0, 0, w - 1, h - 1, arc, arc);
            g2.setColor(new Color(fg.getRed(), fg.getGreen(), fg.getBlue(), 90));
            g2.drawRoundRect(0, 0, w - 1, h - 1, arc, arc);
        } else {
            Color a = hover ? start.brighter() : start;
            Color b = hover ? end.brighter() : end;
            g2.setPaint(new GradientPaint(0, 0, a, w, h, b));
            g2.fillRoundRect(0, 0, w, h, arc, arc);
        }

        g2.setFont(getFont());
        g2.setColor(getForeground());
        FontMetrics fm = g2.getFontMetrics();
        String text = getText();
        int x = (w - fm.stringWidth(text)) / 2;
        int y = (h + fm.getAscent() - fm.getDescent()) / 2;
        g2.drawString(text, x, y);
        g2.dispose();
    }
}
