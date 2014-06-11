package View;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class CharakterSelectionView extends JPanel
{
    private Image bgImage;

    public CharakterSelectionView()
    {
        try
        {
            this.bgImage = ImageIO.read(new File("data//img//background//charSelection.png"));
        }
        catch(IOException e)
        {
        }

        this.setLayout(null);
        this.setBounds(0, 0, 1920, 1057);
        this.setVisible(true);
    }

    public void paintComponent(Graphics charBg)
    {
        super.paintComponent(charBg);
        charBg.drawImage(this.bgImage, 0, 0, this);
    }
}
