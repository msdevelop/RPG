package View;

import Controller.MenuController;

import javax.swing.*;

public class MenuItem extends JPanel
{
    public MenuItem(int paramX, int paramY, MenuController paramMenuController, String paramName)
    {
        this.setName(paramName);
        this.setOpaque(false);
        this.setBounds(paramX, paramY, 169, 47);
        this.addMouseListener(paramMenuController);
    }
}
