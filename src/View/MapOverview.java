package View;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class MapOverview extends JPanel
{
    private Image mapImage;

    public MapOverview()
    {
        this.setLayout(null);
        this.setBounds(0, 0, 801, 1057);

        try
        {
            this.mapImage = ImageIO.read(new File("img//map//overviewMap.png"));
        }
        catch(IOException e)
        {}
    }

    public void paintComponent(Graphics map)
    {
        super.paintComponent(map);
        map.drawImage(this.mapImage, 0, 0, this);
    }
}
