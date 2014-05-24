package View;

import Controller.MenuController;
import View.SelectionItem.MenuSelectionItem;

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
        this.setBounds(0, 0, 1920, 1080);
        this.add(new MenuSelectionItem(780, 457, paramMenuController, "neuesSpiel"));
        this.add(new MenuSelectionItem(788, 543, paramMenuController, "spielLaden"));
        this.add(new MenuSelectionItem(788, 630, paramMenuController, "beenden"));

        try
        {
            menuBg = ImageIO.read(new File("data//img//menu//menuBg.png"));
        }
        catch(IOException e)
        {}

        setVisible(true);
    }

    public void paintComponent(Graphics menuView)
    {
        super.paintComponent(menuView);
        menuView.drawImage(menuBg, 0, 0, this);
    }
}
