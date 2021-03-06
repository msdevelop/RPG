package Controller;

import View.DesignElement.LevelBorder;
import View.GameFrame;
import View.paintTest;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class paintTestController implements KeyListener
{
    private paintTest paintTest;
    private LevelBorder levelBorder;

    public paintTestController(GameFrame gf)
    {
        this.paintTest = new paintTest(gf, this);
        //this.levelBorder = new LevelBorder(gf);
    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        if(e.getKeyCode() == KeyEvent.VK_RIGHT)
           this.paintTest.addX();
        else if(e.getKeyCode() == KeyEvent.VK_LEFT)
            this.paintTest.subX();
        else if(e.getKeyCode() == KeyEvent.VK_UP)
            this.paintTest.subY();
        else if(e.getKeyCode() == KeyEvent.VK_DOWN)
            this.paintTest.addY();
    }

    @Override
    public void keyReleased(KeyEvent e)
    {

    }

    @Override
    public void keyTyped(KeyEvent e)
    {

    }
}
