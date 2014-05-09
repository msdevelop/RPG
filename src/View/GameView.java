package View;

import Controller.MenuController;

import javax.swing.*;
import java.awt.*;

/**
 * Created by Tommy on 09.05.2014.
 */
public class GameView extends JFrame
{
    MenuController menuController = new MenuController();

    public GameView()
    {
        this.setUndecorated(true);
        this.setResizable(false);
        this.setExtendedState(MAXIMIZED_BOTH);
        this.getContentPane().add(menuController.menuView);
        this.setVisible(true);
    }
}
