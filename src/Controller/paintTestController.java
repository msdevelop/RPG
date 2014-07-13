package Controller;

import View.GameFrame;
import View.paintTest;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class paintTestController implements KeyListener
{
    private paintTest paintTest;

    public paintTestController(GameFrame gf)
    {
        this.paintTest = new paintTest(gf, this);
    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        System.out.println("Success");
        if(e.getKeyCode() == KeyEvent.VK_RIGHT)
        {
           this.paintTest.addX();
            System.out.println("RIGHT");
        }
        else if(e.getKeyCode() == KeyEvent.VK_LEFT)
        {
            this.paintTest.subX();
            System.out.println("LEFT");
        }
        else if(e.getKeyCode() == KeyEvent.VK_UP)
        {
            this.paintTest.subY();
            System.out.println("UP");
        }
        else if(e.getKeyCode() == KeyEvent.VK_DOWN)
        {
            this.paintTest.addY();
            System.out.println("DOWN");
        }
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
