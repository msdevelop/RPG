package View.SelectionItem;

import Controller.CharakterSelectionController;
import Interface.ScreenResolution;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class CharakterSelectionButton extends JPanel implements ScreenResolution
{
    private Image btnImage, btnImageActive;
    private boolean isInMouseFocus = false;
    private int screenWidth, screenHeight;

    /**Buttons der CharakterSelectionView
    * Namen nach dem Muster btn_funktion
    * fügt MouseListener hinzu(CharakterSelectionController)
    * lädt ButtonBilder für Anzeige im MouseFocus und Default*/
    public CharakterSelectionButton(String paramName, int paramXPos, int paramYPos, CharakterSelectionController paramCharakterSelectionController)
    {
        this.screenWidth = ScreenResolution.screenWidth;
        this.screenHeight = ScreenResolution.screenHeight;
        this.setName("btn_" + paramName);
        this.setLayout(null);
        this.setOpaque(false);
        this.setVisible(true);
        this.setBounds(this.calculateXPos(paramXPos), this.calculateYPos(paramYPos), this.calculateXPos(280), this.calculateYPos(60));
        this.addMouseListener(paramCharakterSelectionController);
        try
        {
            this.btnImage = ImageIO.read(new File("data//img//charSelection//button//btn_" + paramName + ".png"));
            this.btnImageActive = ImageIO.read(new File("data//img//charSelection//button//btn_" + paramName + "_active.png"));
        }
        catch(IOException e)
        {
            System.err.println("IOException\nFehler beim Laden von Buttons\nCharakterSelectionButton.constructor()");
        }
    }

    /**Zeichnet den Button in den Container
    * wenn sich der Button im MouseFocus befindet (isInMouseFocus = true) wird das _active.png gezeichnet*/
    public void paintComponent(Graphics button)
    {
        super.paintComponent(button);

        button.drawImage(this.btnImage, 0, 0, this.calculateXPos(280), this.calculateYPos(60), this);

        if(this.isInMouseFocus)
            button.drawImage(this.btnImageActive, 0, 0, this.calculateXPos(280), this.calculateYPos(60), this);
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

    /**setzt isInMouseFocus(boolean) auf den übergebenen Wert
    * führt in paintComponent() zur Anzeige eines anderen ButtonBildes
    * this.repaint()*/
    public void setIntMouseFocus(boolean paramBool)
    {
        this.isInMouseFocus = paramBool;
        this.repaint();
    }
}
