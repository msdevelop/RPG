package View.SelectionItem;

import Controller.MapController;
import CustomExceptions.CustomImageException;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class OverviewSelectionItem extends JPanel
{
    private Image selectionImg_100, selectionImg_50;
    private boolean isInMouseFocus = false;

    /**SelectionItem für Missionen in der MapOverview
    * Namen nach dem Muster overview_kartenabschnitt
    * fügt MouseListener hinzu (MapController)
    * lädt Bilder für Anzeige im MouseFocus und Default*/
    public OverviewSelectionItem(String paramName, int paramXPos, int paramYPos, MapController paramMapController) throws CustomImageException
    {
        this.setLayout(null);
        this.setName(paramName);
        this.setOpaque(false);
        this.setBounds(paramXPos, paramYPos, 200, 200);
        this.addMouseListener(paramMapController);

        try
        {
            this.selectionImg_100 = ImageIO.read(new File("data//img//map//overview//overviewSelectionItem_100.png"));
            this.selectionImg_50 = ImageIO.read(new File("data//img//map//overview//overviewSelectionItem_50.png"));
        }
        catch(IOException e)
        {
            throw new CustomImageException("Fehler beim Laden von overViewSelectionItem image!\nOvervieSelectionItem.constructor()");
        }
        this.setVisible(true);
    }

    /**Zeichnet selectionImg_100 wenn sich die Component im MouseFocus befindet -> isInMouseFocus = true
    * zeichnet selectionImg_50 -> default*/
    public void paintComponent(Graphics selectionItem)
    {
        super.paintComponent(selectionItem);

        if(this.isInMouseFocus)
        {
            selectionItem.drawImage(this.selectionImg_100, 0, 0, 200, 200, this);
        }
        else
            selectionItem.drawImage(this.selectionImg_50, 0, 0, 200, 200, this);
    }

    /**setzt isInMouseFocus(boolean) auf den übergebenen Wert
    * führt in paintComponent() zur Anzeige eines anderen Bildes
    * this.repaint()*/
    public void setMouseFocus(boolean paramBool)
    {
        this.isInMouseFocus = paramBool;
        this.repaint();
    }
}
