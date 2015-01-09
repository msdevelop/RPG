package View.Overlay;

import javax.swing.*;
import java.awt.*;

public class AttributeTooltipOverlay extends JPanel
{
    public AttributeTooltipOverlay()
    {
        this.setBounds(100, 700, 200, 200);
        this.setBorder(BorderFactory.createLineBorder(Color.MAGENTA, 1, true));
        this.setVisible(true);
    }

    /**
    public void paint(Graphics tooltipOverlay)
    {
        super.paint(tooltipOverlay);

        tooltipOverlay.setColor(new Color(8, 1, 1));
        tooltipOverlay.fillRect(0, 0, 200, 200);
    }
     */
}
