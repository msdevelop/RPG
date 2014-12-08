package View.SelectionItem;

import Controller.MenuController;
import Interface.ScreenResolution;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class MenuSelectionItem extends JPanel implements ScreenResolution
{
    private Image selectionImg;
    private boolean isInMouseFocus = false;
    private int screenWidth, screenHeight;

    /**SelectionItems für Startmenü
    * Namen nach Funktion
    * fügt MouseListener hinzu (MenuController)
    * lädt Bild*/
    public MenuSelectionItem(int paramXPos, int paramYPos, MenuController paramMenuController, String paramName)
    {
        this.screenWidth = ScreenResolution.screenWidth;
        this.screenHeight = ScreenResolution.screenHeight;
        this.setLayout(null);
        this.setName(paramName);
        this.setOpaque(false);
        this.setBounds(((paramXPos * this.screenWidth) / 1920), ((paramYPos * this.screenHeight) / 1080), ((345 * this.screenWidth) / 1920), ((88 * this.screenHeight) / 1080));
        this.addMouseListener(paramMenuController);
        try
        {
            this.selectionImg = ImageIO.read(new File("data//img//menu//menuSelectionItem.png"));
        }
        catch(IOException e)
        {
            System.err.println("IOException\nFehler beim Laden von MenuSelectionItem.png\nMenuSelectionItem.constructor()");
        }
    }

    /**Zeichnet Bild wenn isInMouseFocus = true*/
    public void paintComponent(Graphics selectionItem)
    {
        super.paintComponent(selectionItem);

        if(isInMouseFocus)
            selectionItem.drawImage(this.selectionImg, 0, 0, ((345 * this.screenWidth) / 1920), ((88 * this.screenHeight) / 1080), this);
    }

    /**setzt isInMouseFocus(boolean) auf den übergebenen Wert
    * führt in paintComponent() zur Anzeige eines Bildes (wenn paramBool = true), kein Bild sonst
    * this.repaint()*/
    public void setInMouseFocus(boolean paramBool)
    {
        this.isInMouseFocus = paramBool;
        this.repaint();
    }
}
