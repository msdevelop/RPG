package Controller;

import View.*;
import View.MenuItem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MenuController implements MouseListener
{
    private GameFrame gameFrame;
    private MenuPanel menuPanel;

    public MenuController(GameFrame paramGameFrame)
    {
        this.gameFrame = paramGameFrame;
        this.menuPanel = new MenuPanel(this);
        this.gameFrame.getContentPane().add(this.menuPanel);
    }

    @Override
    public void mouseClicked(MouseEvent e)
    {
        if(e.getComponent().getName().equals("neuesSpiel"))
            this.gameFrame.getGameFrameController().startGame();
        else if(e.getComponent().getName().equals("spielLaden"))
            this.gameFrame.getGameFrameController().loadGame();
        else if(e.getComponent().getName().equals("beenden"))
            System.exit(0);
    }

    @Override
    public void mousePressed(MouseEvent e)
    {
    }

    @Override
    public void mouseReleased(MouseEvent e)
    {

    }

    @Override
    public void mouseEntered(MouseEvent e)
    {
        MenuItem tmpItem = (MenuItem) e.getComponent();
        tmpItem.setBorder(BorderFactory.createLineBorder(Color.black, 1, true));
    }

    @Override
    public void mouseExited(MouseEvent e)
    {
        MenuItem tmpItem = (MenuItem) e.getComponent();
        tmpItem.setBorder(null);
    }

    public MenuPanel getMenuPanel()
    {
        return this.menuPanel;
    }
}
