package View.SelectionItem;

import Controller.MapController;
import Interface.ScreenResolution;

import javax.swing.*;
import java.awt.*;

public class DetailSelectionItem extends JPanel implements ScreenResolution
{
    private int screenWidth, screenHeight;

    /**SelectionItem zur Auswahl des Missionsabschnittes in der MapDetailView
    * Namen nach dem Muster detail_kartenabschnitt
    * fügt MouseListener hinzu (MapController)*/
    public DetailSelectionItem(String paramName, int paramXPos, int paramYPos, MapController paramMapController)
    {
        this.screenWidth = ScreenResolution.screenWidth;
        this.screenHeight = ScreenResolution.screenHeight;
        this.setName(paramName);
        this.setOpaque(false);
        this.setBounds(this.calculateXPos(paramXPos), this.calculateYPos(paramYPos), this.calculateXPos(106), this.calculateXPos(106));
        this.setBorder(BorderFactory.createLineBorder(Color.black, 1, true));
        this.addMouseListener(paramMapController);
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
