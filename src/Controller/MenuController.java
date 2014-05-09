package Controller;

import View.MenuItem;
import View.MenuView;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * Created by Tommy on 09.05.2014.
 */
public class MenuController implements MouseListener
{
    public MenuView menuView;

    public MenuController()
    {
        this.menuView = new MenuView(this);
        MenuItem menuItemA = new MenuItem(this, "Neues Spiel", menuView.getWidth()/2-40, menuView.getHeight()/2);
        menuView.addMenuItem(menuItemA);
    }

    @Override
    public void mouseClicked(MouseEvent e)
    {
        System.exit(0);
    }

    @Override
    public void mousePressed(MouseEvent e)
    {
        System.exit(0);
    }

    @Override
    public void mouseReleased(MouseEvent e)
    {
        System.exit(0);
    }

    @Override
    public void mouseEntered(MouseEvent e)
    {
        System.exit(0);
    }

    @Override
    public void mouseExited(MouseEvent e)
    {
        System.exit(0);
    }
}
