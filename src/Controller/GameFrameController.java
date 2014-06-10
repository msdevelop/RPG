package Controller;

import Data.DataManager;
import View.GameFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameFrameController implements ActionListener
{
    private GameFrame gameFrame;
    private MenuController menuController;
    private MapController mapController;
    private DataManager dataManager;
    private CharakterSelectionController charakterSelectionController;

    public GameFrameController()
    {
        this.dataManager = new DataManager();
        this.gameFrame = new GameFrame(this);
        this.menuController = new MenuController(this.gameFrame);
        this.gameFrame.setVisible(true);
        this.gameFrame.requestFocus();
    }

    public void initializeCharakterSelection()
    {
        this.menuController.getMenuPanel().setVisible(false);
        this.gameFrame.remove(this.menuController.getMenuPanel());
        this.gameFrame.addMenuBar();
        this.charakterSelectionController = new CharakterSelectionController(this);
        this.gameFrame.getContentPane().add(this.charakterSelectionController.getCharakterSelectionView());
    }

    public void initializeMapSelection()
    {
        this.mapController = new MapController(this);
        this.gameFrame.getContentPane().add(this.mapController.getMapOverview());
        this.gameFrame.getContentPane().add(this.mapController.getMapDetailView());
    }

    public void loadGame()
    {}

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getActionCommand().equals("beenden"))
            System.exit(0);
    }

    public DataManager getDataManager()
    {
        return this.dataManager;
    }
}
