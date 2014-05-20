package Controller;

import View.GameFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameFrameController implements ActionListener
{
    private GameFrame gameFrame;
    private MenuController menuController;
    private MapController mapController;

    public GameFrameController()
    {
        this.gameFrame = new GameFrame(this);
        this.menuController = new MenuController(this.gameFrame);
        this.gameFrame.setVisible(true);
    }

    public void startGame()
    {
        this.menuController.getMenuPanel().setVisible(false);
        this.gameFrame.remove(this.menuController.getMenuPanel());
        this.gameFrame.addMenu();
        this.mapController = new MapController();
        this.gameFrame.getContentPane().add(this.mapController.getSeparatorPanel());
        this.gameFrame.getContentPane().add(this.mapController.getMapOverview());
        this.gameFrame.getContentPane().add(this.mapController.getMapDetail());
    }

    public void loadGame()
    {}

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getActionCommand().equals("beenden"))
            System.exit(0);

/**TEST*/
        else if(e.getActionCommand().equals("switchMap"))
            this.mapController.getMapDetail().setMissionSelected(true);
/***/

    }
}
