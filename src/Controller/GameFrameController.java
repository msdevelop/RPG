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
        this.username = JOptionPane.showInputDialog(null, "Bitte geben Sie einen Benutzername ein.", "Benutzername Eingeben", JOptionPane.QUESTION_MESSAGE);
        this.username.trim();
        if((this.username.length() < 4) || (this.username.length() > 15) || (!(this.username.matches("\\w*"))))
        {
            JOptionPane.showMessageDialog(null, "Ungültiger Benutzername!\n" +
                    "      - Gültige Zeichen: [a-z] [A-Z] [0-9] [_]\n" +
                    "      - mindestens 4 maximal 15 Zeichen", "Fehler beim Erstellen des Benutzers", JOptionPane.WARNING_MESSAGE);
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

    public void initializeMapSelection(LinkedList<CharakterModel> paramGroupList)
    {
        this.currentGroup = paramGroupList;
        this.charakterSelectionController.getCharakterSelectionView().setVisible(false);
        this.gameFrame.remove(this.charakterSelectionController.getCharakterSelectionView());
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
        {
            this.dataManager.closeConnection();
            System.exit(0);
        }
    }

    public DataManager getDataManager()
    {
        return this.dataManager;
    }

    public GameFrame getGameFrame()
    {
        return this.gameFrame;
    }

    public String getCurrentUser()
    {
        return this.currentUser;
    }
}
