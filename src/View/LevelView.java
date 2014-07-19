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
    private int playerXPos = 0, playerYPos = 0, playerStepSize = 48;
    private MaterialModel[][] matModelArray;
    private Image playerImg;
    private boolean fieldIsPainted = false;

    public LevelView(GameFrame gameFrame, LevelViewController paramLevelViewController, String paramLevelName)
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
        gameFrame.getContentPane().add(this);
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
                if((this.matModelArray[i][j].getMaterialID() == 302) && !(this.fieldIsPainted))
                {
                    this.playerXPos = 48 * j;
                    this.playerYPos = 48 * i;
                    this.fieldIsPainted = true;
                }
            }
        }
        field.drawImage(this.playerImg, this.playerXPos, this.playerYPos, this);
    }

    public void addX()
    {
        if(this.playerXPos < 1824)
        {
            this.playerXPos += this.playerStepSize;
            this.repaint();
        }
    }

    public void subX()
    {
        if(this.playerXPos >= 48)
        {
            this.playerXPos -= this.playerStepSize;
            this.repaint();
        }
    }

    public void addY()
    {
        if(this.playerYPos < 960)
        {
            this.playerYPos += this.playerStepSize;
            this.repaint();
        }
    }

    public void subY()
    {
        if(this.playerYPos >= 48)
        {
            this.playerYPos -= this.playerStepSize;
            this.repaint();
        }
    }


}
