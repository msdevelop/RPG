package View.SelectionItem;

import Controller.CharakterSelectionController;
import Interface.ScreenResolution;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class CharakterSelectionItem extends JPanel implements ScreenResolution
{
    private Image charImg;
    private int screenWidth, screenHeight;

    /**SelectionItems für Charaktere
    * Namen nach dem Muster charakter_charID
    * fügt MouseListener hinzu (CharakterSelectionController)
    * lädt Charakterbild*/
    public CharakterSelectionItem(String paramUrl, int paramXPos, int paramYPos, int paramID, CharakterSelectionController paramCharakterSelectionController)
    {
        this.screenWidth = ScreenResolution.screenWidth;
        this.screenHeight = ScreenResolution.screenHeight;
        this.setOpaque(false);
        this.setName("charakter_" + paramID);
        this.setBounds(this.calculateXPos(paramXPos), this.calculateYPos(paramYPos), this.calculateXPos(52), this.calculateXPos(52));
        this.addMouseListener(paramCharakterSelectionController);

        try
        {
            this.charImg = ImageIO.read(new File(paramUrl));
        }
        catch(IOException e)
        {
            System.err.println("IOExceptio\nFehler beim Laden von Charakterbild\nCharakterSelectionItem.constructor()");
        }
    }

    public void paintComponent(Graphics charGraphic)
    {
        super.paintComponent(charGraphic);
        charGraphic.drawImage(this.charImg, 2, 2, this.calculateXPos(48), this.calculateXPos(48), this);
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
