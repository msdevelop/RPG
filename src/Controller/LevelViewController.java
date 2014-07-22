package Controller;

import View.DesignElement.LevelBorder;
import View.GameFrame;
import View.LevelView;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class LevelViewController implements KeyListener
{
    private LevelView LevelView;
    private LevelBorder levelBorder;
    private GameFrameController gameFrameController;

    public LevelViewController(GameFrameController paramGameFrameController, String paramLevelName)
    {
        this.gameFrameController = paramGameFrameController;
        this.LevelView = new LevelView(this.gameFrameController.getGameFrame(), this, paramLevelName);
        this.levelBorder = new LevelBorder(paramGameFrameController.getGameFrame());
    }

    @Override
    public void keyPressed(KeyEvent e)
    {
        if(e.getKeyCode() == KeyEvent.VK_RIGHT)
            this.LevelView.addX();
        else if(e.getKeyCode() == KeyEvent.VK_LEFT)
            this.LevelView.subX();
        else if(e.getKeyCode() == KeyEvent.VK_UP)
            this.LevelView.subY();
        else if(e.getKeyCode() == KeyEvent.VK_DOWN)
            this.LevelView.addY();
    }

    public GameFrameController getGameFrameController()
    {
        return this.gameFrameController;
    }

    @Override /*not in use*/
    public void keyReleased(KeyEvent e)
    {

    }

    @Override /*not in use*/
    public void keyTyped(KeyEvent e)
    {

    }
}
