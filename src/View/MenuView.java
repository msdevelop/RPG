package View;

import Controller.MenuController;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Created by Tommy on 09.05.2014.
 */
public class MenuView extends JPanel
{
    MenuController menuController;
    Image ground;
    int x, y;

    public MenuView(MenuController paramController)
    {
        this.menuController = paramController;

        try
        {
            this.ground = ImageIO.read(new File("img//ground.png"));
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }

        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        this.x = ((int) dimension.getWidth()) / 32;
        this.y = ((int) dimension.getHeight()) / 32;
    }
    public void paint(Graphics g)
    {
        super.paint(g);

        for(int i = 0; i < x; i++)
            for(int j = 0; j <= y; j++)
            {
                g.drawImage(this.ground, i*32, j*32, this);
            }
    }

    public void addMenuItem(MenuItem paramItem)
    {
        this.add(paramItem);
    }

}
