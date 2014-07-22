package Controller;

import View.*;
import View.SelectionItem.MenuSelectionItem;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MenuController implements MouseListener
{
    private GameFrameController gameFrameController;
    private MenuPanel menuPanel;

    /*erzeugt MenuPanel(View)
     * zeigt MenuPanel zum Spielstart an*/
    public MenuController(GameFrameController paramGameFrameController)
    {
        this.gameFrameController = paramGameFrameController;
        this.menuPanel = new MenuPanel(this);
        this.gameFrameController.getGameFrame().getContentPane().add(this.menuPanel);
    }

    @Override
    public void mouseClicked(MouseEvent e)
    {
        if(e.getComponent().getName().equals("neuesSpiel"))
            this.gameFrameController.initiateCharakterSelection();
        else if(e.getComponent().getName().equals("spielLaden"))
            this.gameFrameController.loadGame();
        else if(e.getComponent().getName().equals("beenden"))
            this.gameFrameController.getDataManager().closeConnection();
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

    /*Gibt aktuelles MenuPanel(View) zurück*/
    public MenuPanel getMenuPanel()
    {
        return this.menuPanel;
    }

    @Override /*not in user*/
    public void mousePressed(MouseEvent e)
    {
    }

    @Override /*not in user*/
    public void mouseReleased(MouseEvent e)
    {

    }
}
