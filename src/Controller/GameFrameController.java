package Controller;

import Data.DataManager;
import Model.CharakterModel;
import View.GameFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;

public class GameFrameController implements ActionListener
{
    private GameFrame gameFrame;
    private MenuController menuController;
    private MapController mapController;
    private DataManager dataManager;
    private CharakterSelectionController charakterSelectionController;
    private String currentUser;
    private LinkedList<CharakterModel> currentGroup;
    private LevelViewController levelViewController;

    /**Initialisiert den DataManager
     * erzeugt GameFrame(View)
     * erzeugt MenuController*/
    public GameFrameController()
    {
        this.dataManager = new DataManager();
        this.gameFrame = new GameFrame(this);
        this.menuController = new MenuController(this);
        this.gameFrame.setVisible(true);
        this.gameFrame.requestFocus();
    }

    /**Wird von MenuController.requestUsername() nach gültiger Eingabe eines Benutzernamen aufgerufen
     * MenuView wird entfernt und die MenuBar(View) hinzugefügt -> gameFrame.addMenuBar()
     * Die Charakterselektion wird initialisiert -> new CharakterSelectionController*/
    public void initiateCharakterSelection(String paramUsername)
    {
        this.currentUser = paramUsername;
        this.menuController.getMenuView().setVisible(false);
        this.gameFrame.remove(this.menuController.getMenuView());
        this.gameFrame.addMenuBar();
        this.charakterSelectionController = new CharakterSelectionController(this);
        this.gameFrame.getContentPane().add(this.charakterSelectionController.getCharakterSelectionView());
    }

    /**Wird aufgerufen wenn die Charakterauswahl beendet ist
    * setzt currentGroup
    * entfern die CharakterSelectionView
    * initialisiert den MapController
    * fügt die MapOveriview(View) und MapDetailView(View) dem GameFrame hinzu*/
    public void initiateMapSelection(LinkedList<CharakterModel> paramGroupList)
    {
        this.currentGroup = paramGroupList;
        this.charakterSelectionController.getCharakterSelectionView().setVisible(false);
        this.gameFrame.remove(this.charakterSelectionController.getCharakterSelectionView());
        this.mapController = new MapController(this);
        this.gameFrame.getContentPane().add(this.mapController.getMapOverview());
        this.gameFrame.getContentPane().add(this.mapController.getMapDetailView());
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getActionCommand().equals("beenden"))
            this.dataManager.closeConnection();
    }

    /**Entfernt nach erfolgreicher Levelauswahl die MapDetailView und MapOverview
    * initialisiert den LevelViewController*/
    public void initiateLevel(String paramLevelName)
    {
        this.mapController.getMapOverview().setVisible(false);
        this.mapController.getMapDetailView().setVisible(false);
        this.gameFrame.remove(this.mapController.getMapOverview());
        this.gameFrame.remove(this.mapController.getMapDetailView());
        this.levelViewController = new LevelViewController(this, paramLevelName);
    }

    /**Gibt den aktuellen DataManager zurück*/
    public DataManager getDataManager()
    {
        return this.dataManager;
    }

    /**Gibt den aktuellen GameFrame zurück*/
    public GameFrame getGameFrame()
    {
        return this.gameFrame;
    }

    /**ibt den aktuell im Programmfokus stehenden Benutzer zurück*/
    public String getCurrentUser()
    {
        return this.currentUser;
    }

    /**Gibt die aktuelle Gruppe aus 6 Charakteren zurück*/
    public LinkedList<CharakterModel> getCurrentGroup()
    {
        return this.currentGroup;
    }
}
