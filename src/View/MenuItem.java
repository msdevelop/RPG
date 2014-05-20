package View;

import Controller.MenuController;

import javax.swing.*;

public class MenuItem extends JPanel
{
    public MenuItem(int paramXPos, int paramYPos, MenuController paramMenuController, String paramName)
    {
        this.setName(paramName);
        this.setOpaque(false);
        this.setBounds(paramXPos, paramYPos, 169, 47);
        this.addMouseListener(paramMenuController);
    }
}
