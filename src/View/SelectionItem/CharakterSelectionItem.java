package View.SelectionItem;

import Controller.CharakterSelectionController;
import CustomExceptions.CustomImageException;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class CharakterSelectionItem extends JPanel
{
    private Image charImg;

    /**SelectionItems für Charaktere
     * Namen nach dem Muster charakter_charID
     * fügt MouseListener hinzu (CharakterSelectionController)
     * lädt Charakterbild*/
    public CharakterSelectionItem(String paramUrl, int paramXPos, int paramYPos, int paramID, CharakterSelectionController paramCharakterSelectionController) throws CustomImageException
    {
        this.setOpaque(false);
        this.setName("charakter_" + paramID);
        this.setBounds(paramXPos, paramYPos, 52, 52);
        this.addMouseListener(paramCharakterSelectionController);

        try
        {
            this.charImg = ImageIO.read(new File(paramUrl));
        }
        catch(IOException e)
        {
            throw new CustomImageException("Fehler beim Laden von Charakterbild!\nCharakterSelectionItem.constructor()");
        }
        this.setVisible(true);
    }

    public void paintComponent(Graphics charGraphic)
    {
        super.paintComponent(charGraphic);
        charGraphic.drawImage(this.charImg, 2, 2, this);
    }
}