package View;

import Controller.MenuController;
import CustomExceptions.CustomImageException;
import View.SelectionItem.MenuSelectionItem;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class MenuView extends JPanel
{
    private Image menuBg;

    /**Erzeugt MenuSelectionItems für jeden Menüpunkt
     * lädt Hintergrundbild für Hauptmenü*/
    public MenuView(MenuController paramMenuController) throws CustomImageException
    {
        this.setLayout(null);
        this.setBounds(0, 0, 1920, 1080);
        this.add(new MenuSelectionItem(792, 457, paramMenuController, "neuesSpiel"));
        this.add(new MenuSelectionItem(788, 543, paramMenuController, "spielLaden"));
        this.add(new MenuSelectionItem(788, 630, paramMenuController, "beenden"));
        try
        {
            menuBg = ImageIO.read(new File("data//img//menu//menuBg.png"));
        }
        catch(IOException e)
        {
            throw new CustomImageException("Fehler beim Laden von Hintergrundbild\nMenuView.constructor()");
        }
        setVisible(true);
    }

    public void paintComponent(Graphics menuView)
    {
        super.paintComponent(menuView);
        menuView.drawImage(menuBg, 0, 0, this);
    }
}