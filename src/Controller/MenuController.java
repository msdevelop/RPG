package Controller;

import View.*;
import View.SelectionItem.MenuSelectionItem;

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
            this.gameFrame.getGameFrameController().startNewGame();
        else if(e.getComponent().getName().equals("spielLaden"))
            this.gameFrame.getGameFrameController().loadGame();
        else if(e.getComponent().getName().equals("beenden"))
            System.exit(0);
    }

    @Override
    public void mouseEntered(MouseEvent e)
    {
        MenuSelectionItem tmpItem = (MenuSelectionItem) e.getComponent();
        tmpItem.setInMouseFocus(true);
    }

    @Override
    public void mouseExited(MouseEvent e)
    {
        MenuSelectionItem tmpItem = (MenuSelectionItem) e.getComponent();
        tmpItem.setInMouseFocus(false);
    }

    @Override
    public void mousePressed(MouseEvent e)
    {
    }

    @Override
    public void mouseReleased(MouseEvent e)
    {

    }

    public MenuPanel getMenuPanel()
    {
        return this.menuPanel;
    }
}
