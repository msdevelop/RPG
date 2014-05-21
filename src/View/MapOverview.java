package View;

import Controller.MapController;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class MapOverview extends JPanel
{
    private MapController mapController;
    private Image mapImage;

    public MapOverview(MapController paramMapController)
    {
        this.mapController = paramMapController;

        this.setLayout(null);
        this.setBounds(0, 0, 801, 1057);

        try
        {
            this.mapImage = ImageIO.read(new File("data//img//map//overview//overviewMap.png"));
        }
        catch(IOException e)
        {}

        this.add(new SelectionItem("gareth", 350, 460, this.mapController));
    }

    public void paintComponent(Graphics map)
    {
        super.paintComponent(map);
        map.drawImage(this.mapImage, 0, 0, this);
    }
}
