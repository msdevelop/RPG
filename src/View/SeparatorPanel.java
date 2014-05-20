package View;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class SeparatorPanel extends JPanel
{
    private Image separatorImage;

    public SeparatorPanel()
    {
        this.setLayout(null);
        this.setBounds(801, 0, 20, 1057);
        try
        {
            this.separatorImage = ImageIO.read(new File("img//map//separator.png"));
        }
        catch(IOException e)
        {}
        this.setVisible(true);
    }

    public void paintComponent(Graphics separator)
    {
        super.paintComponent(separator);
        separator.drawImage(this.separatorImage, 0, 0, this);
    }
}
