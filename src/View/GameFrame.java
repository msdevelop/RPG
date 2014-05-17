package View;

import Controller.MenuController;

import javax.swing.*;

public class GameFrame extends JFrame
{
    private MenuController menuController;

    public GameFrame()
    {
        setUndecorated(true);
        setResizable(false);
        setExtendedState(MAXIMIZED_BOTH);
        this.menuController = new MenuController(this);
        setVisible(true);
    }
}
