package View;

import Controller.MapController;
import Exceptions.CustomImageException;
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

    /**Lädt Hintergrundbild der MapOverview*/
    public MapOverview(MapController paramMapController) throws CustomImageException
    {
        this.mapController = paramMapController;

        this.setLayout(null);
        this.setBounds(0, 0, 811, 1057);

        try
        {
            this.mapImage = ImageIO.read(new File("data//img//map//overview//overviewMap.png"));
        }
        catch(IOException e)
        {
            throw new CustomImageException("Fehler beim Laden von Hintergrundbild!\nMapOverview.constructor()");
        }

        this.enableView();
        this.setVisible(true);
    }

    public void paintComponent(Graphics map)
    {
        super.paintComponent(map);
        map.drawImage(this.mapImage, 0, 0, this);
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
    private void enableView() throws CustomImageException
    {
        this.add(new OverviewSelectionItem("overview_gareth", 340, 477, this.mapController));
        this.add(new OverviewSelectionItem("overview_gerasim", 420, 258, this.mapController));
    }
}