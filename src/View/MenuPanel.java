package View;

import Controller.MenuController;
import Interface.ScreenResolution;
import View.SelectionItem.MenuSelectionItem;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class MenuPanel extends JPanel implements ScreenResolution
{
    private Image menuBg;
    private int screenWidth, screenHeight;

    /**Erzeugt MenuSelectionItems für jeden Menüpunkt
    * lädt Hintergrundbild für Hauptmenü*/
    public MenuPanel(MenuController paramMenuController)
    {
        this.screenWidth = ScreenResolution.screenWidth;
        this.screenHeight = ScreenResolution.screenHeight;
        this.setLayout(null);
        this.setBounds(0, 0, this.screenWidth, this.screenHeight);
        this.add(new MenuSelectionItem(792, 457, paramMenuController, "neuesSpiel"));
        this.add(new MenuSelectionItem(788, 543, paramMenuController, "spielLaden"));
        this.add(new MenuSelectionItem(788, 630, paramMenuController, "beenden"));

        try
        {
            menuBg = ImageIO.read(new File("data//img//menu//menuBg.png"));
        }
        catch(IOException e)
        {
            System.err.println("IOException\nFehler beim Laden von Hintergrundbild\nMenuPanel.constructor()");
        }

        setVisible(true);
    }

    public void paintComponent(Graphics menuView)
    {
        super.paintComponent(menuView);
        menuView.drawImage(menuBg, 0, 0, this.screenWidth, this.screenHeight, this);
    }
}
