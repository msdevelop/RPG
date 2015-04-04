package View.Overlay;

import CustomExceptions.CustomImageException;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class AttributeTooltipOverlay extends JPanel
{
    private String tooltipString = "";
    private final int LINE_WIDTH;
    private Image backgroundImage;

    public AttributeTooltipOverlay() throws CustomImageException
    {
        this.setOpaque(false);
        this.setBounds(1555, 604, 365, 455);
        this.LINE_WIDTH = this.getWidth() - 20;
        try
        {
            this.backgroundImage = ImageIO.read(new File("data//img//charSelection//tooltipOverlay.png"));
        }
        catch(IOException e)
        {
            throw new CustomImageException("Fehler beim Laden von tooltipOverlay.png\nAttributeTooltipOverlay.constructor()");
        }
        this.setVisible(true);
    }

    public void paintComponent(Graphics tooltipOverlay)
    {
        super.paintComponent(tooltipOverlay);

        tooltipOverlay.drawImage(this.backgroundImage, 0, 0, this);
        tooltipOverlay.setFont(new Font("TimesNewRoman", Font.PLAIN, 17));
        tooltipOverlay.setColor(new Color(255, 255, 255));
        this.drawMultilineString(tooltipOverlay);
    }

    public void drawMultilineString(Graphics paramTooltipOverlay)
    {
        FontMetrics fm = paramTooltipOverlay.getFontMetrics();
        int xOffset = 10, yOffset = fm.getHeight();

        if(fm.stringWidth(this.tooltipString) < this.LINE_WIDTH)
            paramTooltipOverlay.drawString(this.tooltipString, xOffset, yOffset);
        else
        {
            String[] words = this.tooltipString.split(" ");
            String currentLine = words[0];
            for(int i = 1; i < words.length; i++)
            {
                if(fm.stringWidth(currentLine + words[i]) < this.LINE_WIDTH)
                    currentLine += " " + words[i];
                else
                {
                    paramTooltipOverlay.drawString(currentLine, xOffset, yOffset);
                    yOffset += fm.getHeight();
                    currentLine = words[i];
                }
            }
            if(currentLine.trim().length() > 0)
                paramTooltipOverlay.drawString(currentLine, xOffset, yOffset);
        }
    }

    public void setTooltipString(String paramTooltipString)
    {
        this.tooltipString = paramTooltipString;
        this.repaint();
    }
}
