package View;

import Controller.LevelViewController;
import Model.MaterialModel;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class LevelView extends JPanel
{
    private int currentPlayerXPos = 0, currentPlayerYPos = 0, playerStepSize = 48;
    private MaterialModel[][] matModelArray;
    private Image playerImg;
    private boolean fieldIsPainted = false;

    /**Lädt matModelArray für angefordertes Level aus der DB
    * setzt currentGroup(0) als playerImg*/
    public LevelView(GameFrame paramGameFrame, LevelViewController paramLevelViewController, String paramLevelName)
    {
        this.matModelArray = paramLevelViewController.getGameFrameController().getDataManager().loadLevel(paramLevelName);
        try
        {
            this.playerImg = ImageIO.read(new File(paramLevelViewController.getGameFrameController().getCurrentGroup().get(0).getUrl()));
        }
        catch(IOException e)
        {
            System.err.println("IOException\nFehler beim Laden von playerImg\nLevelView.constructor()");
        }
        this.setBounds(24, 24, 1872, 1008);
        this.setLayout(null);
        this.addKeyListener(paramLevelViewController);
        this.setVisible(true);
        paramGameFrame.getContentPane().add(this);
        this.requestFocus();
    }

    public void paintComponent(Graphics field)
    {
        super.paintComponent(field);

        for(int i = 0; i < 21; i++)
        {
            for(int j = 0; j < 39; j++)
            {
                field.drawImage(this.matModelArray[i][j].getMatImg(), 48 * j, 48 * i, this);
                if((this.matModelArray[i][j].getMaterialID() == 302) && ! (this.fieldIsPainted))
                {
                    this.currentPlayerXPos = 48 * j;
                    this.currentPlayerYPos = 48 * i;
                    this.fieldIsPainted = true;
                }
            }
        }
        field.drawImage(this.playerImg, this.currentPlayerXPos, this.currentPlayerYPos, this);
    }

    /**Bewegt die Spielfigur um playerStepSize nach rechts
    * wenn dortiges Feld gültig ist*/
    public void addX()
    {
        if((this.currentPlayerXPos < 1824) && (((this.matModelArray[this.currentPlayerYPos / 48][(this.currentPlayerXPos + this.playerStepSize) / 48].getMaterialID()) % 200) >= 100))
        {
            this.currentPlayerXPos += this.playerStepSize;
            this.repaint();
        }
    }

    /*Bewegt die Spielfigur um playerStepSize nach links
    * wenn dortiges Feld gültig ist*/
    public void subX()
    {
        if((this.currentPlayerXPos >= 48) && (((this.matModelArray[this.currentPlayerYPos / 48][(this.currentPlayerXPos - this.playerStepSize) / 48].getMaterialID()) % 200) >= 100))
        {
            this.currentPlayerXPos -= this.playerStepSize;
            this.repaint();
        }
    }

    /**Bewegt die Spielfigur um playerStepSize nach unten
    * wenn dortiges Feld gültig ist*/
    public void addY()
    {
        if((this.currentPlayerYPos < 960) && (((this.matModelArray[(this.currentPlayerYPos + this.playerStepSize) / 48][this.currentPlayerXPos / 48].getMaterialID()) % 200) >= 100))
        {
            this.currentPlayerYPos += this.playerStepSize;
            this.repaint();
        }
    }

    /**Bewegt die Spielfigur um playerStepSize nach oben
    * wenn dortiges Feld gültig ist*/
    public void subY()
    {
        if((this.currentPlayerYPos >= 48) && (((this.matModelArray[(this.currentPlayerYPos - this.playerStepSize) / 48][this.currentPlayerXPos / 48].getMaterialID()) % 200) >= 100))
        {
            this.currentPlayerYPos -= this.playerStepSize;
            this.repaint();
        }
    }
}
