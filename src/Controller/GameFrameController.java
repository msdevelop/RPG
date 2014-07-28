package Controller;

import Data.DataManager;
import Model.CharakterModel;
import View.GameFrame;

import javax.swing.*;
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
    private String username = "", currentUser;
    private LinkedList<CharakterModel> currentGroup;
    private LevelViewController levelViewController;

    /*Initialisiert den DataManager
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

    /*Wird aufgerufen wenn im Menü der Punkt "Neues Spiel" gewählt wird
     * Benutzer wird zur Eingabe eines Benutzernamens aufgefordert -> JOptionPane
     * Führende und angehängte Leerzeichen werden abgeschnitten -> String.trim()
     * Länge des Benutzernamens muss zwischen 4 und 15 Zeichen liegen und muss den Pattern [a-z] [A-Z] [0-9] [ _ ] in beliebiger Reihenfolge entsprechen (keine führenden/angehämgten Leer- bzw Sonderzeichen)
     * Es wird geprüft ob der Benutzername bereits vergeben ist ->this.validateUsername()
     * Fehlermeldungen via JOptionPane*/
    public void initiateCharakterSelection()
    {
        this.username = JOptionPane.showInputDialog(null, "Bitte geben Sie einen Benutzername ein.", "Benutzername Eingeben", JOptionPane.QUESTION_MESSAGE);
        if((this.username.length() < 4) || (this.username.length() > 15) || (! (this.username.matches("\\p{Alpha}\\w*"))))
        {
            JOptionPane.showMessageDialog(null, "Ungültiger Benutzername!\n" +
                    "      - Gültige Zeichen: [a-z] [A-Z] [0-9] [ _ ]\n" +
                    "      - mindestens 4 maximal 15 Zeichen\n" +
                    "      - keine führenden oder angehängten Leer- oder Sonderzeichen", "Fehler beim Erstellen des Benutzers", JOptionPane.WARNING_MESSAGE);
        }
        else if(! this.validateUsername())
        {
            JOptionPane.showMessageDialog(null, "Benutzername bereits vergeben!", "Fehler beim Erstellen des Benutzers", JOptionPane.WARNING_MESSAGE);
        }
        /*Wenn der Benutzername gültig ist wird das MenuPanel(View) entfernt und die MenuBar(View) hinzugefügt -> gameFrame.addMenuBar()
         * Die Charakterselektion wird initialisiert -> new CharakterSelectionController*/
        else
        {
            this.currentUser = username;
            this.menuController.getMenuPanel().setVisible(false);
            this.gameFrame.remove(this.menuController.getMenuPanel());
            this.gameFrame.addMenuBar();
            this.charakterSelectionController = new CharakterSelectionController(this);
            this.gameFrame.getContentPane().add(this.charakterSelectionController.getCharakterSelectionView());
        }
    }

    /*Wird aufgerufen wenn die Charakterauswahl beendet ist
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

    /*currently not ín use*/
    public void loadGame()
    {
    }

    /*Übergibt den eingegebenen Benutzernamen an den DataManager um zu überprüfen ob er bereits vergeben ist
     * return true -> Beutzername ist gültig und wurde der Datenbanktabelle 'user' hinzugefügt
     * return false -> Benutzername ist bereits vergeben*/
    private boolean validateUsername()
    {
        return this.dataManager.isValidUsername(this.username);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(e.getActionCommand().equals("beenden"))
            this.dataManager.closeConnection();
    }

    public void initiateLevel(String paramLevelName)
    {
        this.mapController.getMapOverview().setVisible(false);
        this.mapController.getMapDetailView().setVisible(false);
        this.gameFrame.remove(this.mapController.getMapOverview());
        this.gameFrame.remove(this.mapController.getMapDetailView());
        this.levelViewController = new LevelViewController(this, paramLevelName);
    }

    /*Gibt den aktuellen DataManager zurück*/
    public DataManager getDataManager()
    {
        return this.dataManager;
    }

    /*Gibt den aktuellen GameFrame zurück*/
    public GameFrame getGameFrame()
    {
        return this.gameFrame;
    }

    /*Gibt den aktuell im Programmfokus stehenden Benutzer zurück*/
    public String getCurrentUser()
    {
        return this.currentUser;
    }

    public LinkedList<CharakterModel> getCurrentGroup()
    {
        return this.currentGroup;
    }
}
