package View;

import Controller.paintTestController;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class paintTest extends JPanel
{
    private Image testMat, player;
    private int x = 0, y = 0, z = 48;

    public paintTest(GameFrame gameFrame, paintTestController parampaintTestController)
    {
        this.setBounds(24, 24, 1872, 1008);
        this.setLayout(null);

        try
        {
            this.testMat = ImageIO.read(new File("data\\img\\tiles\\materials\\tree.png"));
            this.player = ImageIO.read(new File("data\\img\\tiles\\char\\magier.png"));
        }
        catch(IOException e)
        {
            System.err.println("IOException\nFehler beim Laden von Testmaterial\npaintTest.constructor()");
        }
        this.addKeyListener(parampaintTestController);
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
                field.drawImage(this.testMat,48 * j, 48 * i, this);
            }
        }
        field.drawImage(this.player, this.x, this.y, this);
    }

    public void addX()
    {
        if(this.x < 1824)
        {
            this.x += this.z;
            this.repaint();
        }
    }

    public void subX()
    {
        if(this.x >= 48)
        {
            this.x -= this.z;
            this.repaint();
        }
    }

    public void addY()
    {
        if(this.y  < 960)
        {
            this.y += this.z;
            this.repaint();
        }
    }

    public void subY()
    {
        if(this.y >= 48)
        {
            this.y -= this.z;
            this.repaint();
        }
    }
}
