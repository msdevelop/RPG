package View;

import Controller.MenuController;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class MenuPanel extends JPanel
{
    private Image menuBg;

    public MenuPanel(MenuController paramMenuController)
    {
        this.setLayout(null);
        this.add(new MenuItem(875, 502, paramMenuController, "neuesSpiel"));
        this.add(new MenuItem(875, 561, paramMenuController, "spielLaden"));
        this.add(new MenuItem(875, 620, paramMenuController, "beenden"));

        try
        {
            menuBg = ImageIO.read(new File("img//menu//menuBg.png"));
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }

        setVisible(true);
    }

    public void paintComponent(Graphics menuView)
    {
        super.paintComponents(menuView);
        menuView.drawImage(menuBg, 0, 0, this);
    }
}
