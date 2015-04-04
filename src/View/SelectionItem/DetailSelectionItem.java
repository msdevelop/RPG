package View.SelectionItem;

import Controller.MapController;

import javax.swing.*;
import java.awt.*;

public class DetailSelectionItem extends JPanel
{
    /**SelectionItem zur Auswahl des Missionsabschnittes in der MapDetailView
     * Namen nach dem Muster detail_kartenabschnitt
     * fügt MouseListener hinzu (MapController)*/
    public DetailSelectionItem(String paramName, int paramXPos, int paramYPos, MapController paramMapController)
    {
        this.setName(paramName);
        this.setOpaque(false);
        this.setBounds(paramXPos, paramYPos, 106, 106);
        this.setBorder(BorderFactory.createLineBorder(Color.black, 1, true));
        this.addMouseListener(paramMapController);
        this.setVisible(true);
    }
}