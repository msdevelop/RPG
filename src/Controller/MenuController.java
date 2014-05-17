package Controller;

import View.GameFrame;
import View.MenuPanel;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MenuController implements MouseListener
{
    private GameFrame gameFrame;
    private MenuPanel menuPanel;

    public MenuController(GameFrame paramFrame)
    {
        this.gameFrame = paramFrame;
        this.menuPanel = new MenuPanel(this);
        this.gameFrame.getContentPane().add(this.menuPanel);
    }

    @Override
    public void mouseClicked(MouseEvent e)
    {
        if(e.getComponent().getName().equals("neuesSpiel"))
            System.out.println("Neues Spiel starten!");
        else if(e.getComponent().getName().equals("spielLaden"))
            System.out.println("Spielstand laden!");
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

    }

    @Override
    public void mouseExited(MouseEvent e)
    {

    }
}
