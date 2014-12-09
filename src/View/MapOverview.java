package View;

import Controller.MapController;
import Interface.ScreenResolution;
import View.SelectionItem.OverviewSelectionItem;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class MapOverview extends JPanel implements ScreenResolution
{
    private MapController mapController;
    private Image mapImage;
    private int screenWidth, screenHeight;

    /**Lädt Hintergrundbild der MapOverview*/
    public MapOverview(MapController paramMapController)
    {
        this.screenWidth = ScreenResolution.screenWidth;
        this.screenHeight = ScreenResolution.screenHeight;

        this.mapController = paramMapController;

        this.setLayout(null);
        this.setBounds(0, 0, this.calculateXPos(811), this.calculateYPos(1057));

        try
        {
            this.mapImage = ImageIO.read(new File("data//img//map//overview//overviewMap.png"));
        }
        catch(IOException e)
        {
            System.err.println("IOException\nFehler beim Laden von Hintergrundbild\nMapOverview.constructor()");
        }

        this.enableView();
        this.setVisible(true);
    }

    public void paintComponent(Graphics map)
    {
        super.paintComponent(map);
        map.drawImage(this.mapImage, 0, 0, this.calculateXPos(811), this.calculateYPos(1057), this);
    }

    /**Wird aufgerufen nachdem eine Mission ausgewählt wurde
    * Entfernt alle OverviewSelectionItems aus der View*/
    public void disableView()
    {
        this.setEnabled(false);
        this.removeAll();
    }

    /**Fügt OverviewSelectionItems zur View hinzu
    * Namen nach dem Muster overview_kartenabschnitt*/
    private void enableView()
    {
        this.add(new OverviewSelectionItem("overview_gareth", 340, 477, this.mapController));
        this.add(new OverviewSelectionItem("overview_gerasim", 420, 258, this.mapController));
    }

    /**Berechnet die relative x-Position abhängig von der Bildschirmauflösung*/
    private int calculateXPos(int paramX)
    {
        return ((paramX * this.screenWidth) / 1920);
    }

    /**Berechnet die relative y-Position abhängig von der Bildschirmauflösung*/
    private int calculateYPos(int paramY)
    {
        return ((paramY * this.screenHeight) / 1080);
    }
}
