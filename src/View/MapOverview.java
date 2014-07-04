package View;

import Controller.MapController;
import View.SelectionItem.OverviewSelectionItem;

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
        this.setBounds(0, 0, 811, 1057);

        try
        {
            this.mapImage = ImageIO.read(new File("data//img//map//overview//overviewMap.png"));
        }
        catch(IOException e)
        {}

        this.enableView();
        this.setVisible(true);
    }

    public void paintComponent(Graphics map)
    {
        super.paintComponent(map);
        map.drawImage(this.mapImage, 0, 0, this);
    }

    public void disableView()
    {
        this.setEnabled(false);
        this.removeAll();
    }

    public void enableView()
    {
        this.add(new OverviewSelectionItem("overview_gareth", 340, 477, this.mapController));
        this.add(new OverviewSelectionItem("overview_gerasim", 420, 258, this.mapController));
    }
}
