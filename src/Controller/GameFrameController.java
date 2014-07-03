package Controller;

import Data.DataManager;
import View.GameFrame;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameFrameController implements ActionListener
{
    private GameFrame gameFrame;
    private MenuController menuController;
    private MapController mapController;
    private DataManager dataManager;
    private CharakterSelectionController charakterSelectionController;
    private String username = "", currentUser;

    public GameFrameController()
    {
        this.dataManager = new DataManager();
        this.gameFrame = new GameFrame(this);
        this.menuController = new MenuController(this);
        this.gameFrame.setVisible(true);
        this.gameFrame.requestFocus();
    }

    public void initializeCharakterSelection()
    {
        //TODO prüfen ob benutzername bereits in DB vorhanden ist
        this.username = JOptionPane.showInputDialog(null, "Bitte geben Sie einen Benutzername ein.", "Benutzername Eingeben", JOptionPane.QUESTION_MESSAGE);
        this.username.trim();
        if((this.username.length() < 4) || (this.username.length() > 15) || (!(this.username.matches("\\w*"))))
        {
            JOptionPane.showMessageDialog(null, "Gültiger Benutzername:\n" +
                    "       kann Klein- und Großbuchstaben, sowie Zahlen und Unterstrich enthalten\n" +
                    "       ist kürzer als 4 bzw. länger als 15 Zeichen", "Fehler beim Erstellen des Benutzers", JOptionPane.WARNING_MESSAGE);
        }
        else if(!this.validateUsername())
        {
            JOptionPane.showMessageDialog(null, "Benutzername bereits vergeben!", "Fehler beim Erstellen des Benutzers", JOptionPane.WARNING_MESSAGE);
        }
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

    public void initializeMapSelection()
    {
        this.mapController = new MapController(this);
        this.gameFrame.getContentPane().add(this.mapController.getMapOverview());
        this.gameFrame.getContentPane().add(this.mapController.getMapDetailView());
    }

    public void loadGame()
    {}

    public boolean validateUsername()
    {
        return this.dataManager.isValidUsername(this.username);
    }

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

    public GameFrame getGameFrame()
    {
        return this.gameFrame;
    }
}
