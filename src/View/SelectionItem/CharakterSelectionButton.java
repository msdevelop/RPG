package View.SelectionItem;

import Controller.CharakterSelectionController;
import CustomExceptions.CustomImageException;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class CharakterSelectionButton extends JPanel
{
    private Image btnImage, btnImageActive;
    private boolean isInMouseFocus = false;

    /**Buttons der CharakterSelectionView
     * Namen nach dem Muster btn_funktion
     * fügt MouseListener hinzu(CharakterSelectionController)
     * lädt ButtonBilder für Anzeige im MouseFocus und Default*/
    public CharakterSelectionButton(String paramName, int paramXPos, int paramYPos, CharakterSelectionController paramCharakterSelectionController) throws CustomImageException
    {
        this.setName("btn_" + paramName);
        this.setLayout(null);
        this.setOpaque(false);
        this.setBounds(paramXPos, paramYPos, 280, 60);
        this.addMouseListener(paramCharakterSelectionController);

        try
        {
            this.btnImage = ImageIO.read(new File("data//img//charSelection//button//btn_" + paramName + ".png"));
            this.btnImageActive = ImageIO.read(new File("data//img//charSelection//button//btn_" + paramName + "_active.png"));
        }
        catch(IOException e)
        {
            throw new CustomImageException("Fehler beim Laden von Button images!\nCharakterSelectionButton.constructor()");
        }
        this.setVisible(true);
    }

    /**Zeichnet den Button in den Container
     * wenn sich der Button im MouseFocus befindet (isInMouseFocus = true) wird das _active.png gezeichnet*/
    public void paintComponent(Graphics button)
    {
        super.paintComponent(button);

        button.drawImage(this.btnImage, 0, 0, this);

        if(this.isInMouseFocus)
            button.drawImage(this.btnImageActive, 0, 0, this);
    }

    /**setzt isInMouseFocus(boolean) auf den übergebenen Wert
     * führt in paintComponent() zur Anzeige eines anderen ButtonBildes
     * this.repaint()*/
    public void setIntMouseFocus(boolean paramBool)
    {
        this.isInMouseFocus = paramBool;
        this.repaint();
    }
}