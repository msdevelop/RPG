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
    private DataManager dataManager;
    private String currentUser;
    private LinkedList<CharakterModel> currentGroup;

    /**Initialisiert den DataManager
     * erzeugt GameFrame(View)
     * erzeugt MenuController*/
    public GameFrameController()
    {
        this.dataManager = DataManager.getInstance();
        this.gameFrame = new GameFrame();
        new MenuController(this);
        this.gameFrame.setVisible(true);
        this.gameFrame.requestFocus();
    }

    /**Wird von MenuController.requestUsername() nach gültiger Eingabe eines Benutzernamen aufgerufen
     * MenuBar(View) wird hinzugefügt -> gameFrame.addMenuBar()
     * Die Charakterselektion wird initialisiert -> new CharakterSelectionController*/
    public void initiateCharakterSelection(String paramUsername)
    {
        this.currentUser = paramUsername;
        this.gameFrame.addMenuBar(this);
        new CharakterSelectionController(this);
    }

    /**Wird aufgerufen wenn die Charakterauswahl beendet ist
    * setzt currentGroup
    * initialisiert den MapController*/
    public void initiateMapSelection(LinkedList<CharakterModel> paramGroupList)
    {
        this.currentGroup = paramGroupList;
        new MapController(this);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getActionCommand().equals("beenden"))
            this.dataManager.closeConnection(0);
    }

    /**initialisiert den LevelViewController*/
    public void initiateLevel(String paramLevelName)
    {
        new LevelViewController(this, paramLevelName);
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
