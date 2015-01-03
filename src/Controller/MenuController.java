package Controller;

import CustomExceptions.CustomImageException;
import View.*;
import View.SelectionItem.MenuSelectionItem;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MenuController implements MouseListener
{
    private GameFrameController gameFrameController;
    private MenuView menuView;

    /**erzeugt MenuView
     * zeigt MenuView zum Spielstart an*/
    public MenuController(GameFrameController paramGameFrameController)
    {
        this.gameFrameController = paramGameFrameController;
        try
        {
            this.menuView = new MenuView(this);
        }
        catch(CustomImageException ciE)
        {
            this.gameFrameController.handleCustomImageException(ciE);
        }
        this.gameFrameController.getGameFrame().getContentPane().add(this.menuView);
    }

    /**Wird aufgerufen wenn im Menü der Punkt "Neues Spiel" gewählt wird
     * Benutzer wird zur Eingabe eines Benutzernamens aufgefordert -> JOptionPane
     * Länge des Benutzernamens muss zwischen 4 und 15 Zeichen liegen und muss den Pattern [a-z] [A-Z] [0-9] [ _ ] in beliebiger Reihenfolge entsprechen (keine führenden/angehämgten Leer- bzw Sonderzeichen)
     * Es wird geprüft ob der Benutzername bereits vergeben ist ->this.validateUsername()*/
    private void requestUsername()
    {
        try
        {
            String tmpUsername = JOptionPane.showInputDialog(null, "Bitte geben Sie einen Benutzername ein.", "Benutzername Eingeben", JOptionPane.QUESTION_MESSAGE);

            if((tmpUsername.length() < 4) || (tmpUsername.length() > 15) || (! (tmpUsername.matches("\\p{Alpha}\\w*"))))
                JOptionPane.showMessageDialog(null, "Ungültiger Benutzername!\n" +
                        "      - Gültige Zeichen: [a-z] [A-Z] [0-9] [ _ ]\n" +
                        "      - mindestens 4 maximal 15 Zeichen\n" +
                        "      - keine Leerzeichen\n" +
                        "      - keine führenden Zahlen oder Sonderzeichen", "Fehler beim Erstellen des Benutzers", JOptionPane.WARNING_MESSAGE);

            else if(!(this.gameFrameController.getDataManager().isValidUsername(tmpUsername)))
                JOptionPane.showMessageDialog(null, "Benutzername bereits vergeben!", "Fehler beim Erstellen des Benutzers", JOptionPane.WARNING_MESSAGE);
            else
            {
                this.menuView.setVisible(false);
                this.gameFrameController.getGameFrame().getContentPane().remove(this.menuView);
                this.gameFrameController.initiateCharakterSelection(tmpUsername);
            }
        }
        catch(NullPointerException e)
        {}
    }

    /**currently not ín use*/
    private void loadGame()
    {
    }

    @Override
    public void mouseClicked(MouseEvent e)
    {
        if(e.getComponent().getName().equals("neuesSpiel"))
            this.requestUsername();
        else if(e.getComponent().getName().equals("spielLaden"))
            this.loadGame();
        else if(e.getComponent().getName().equals("beenden"))
            this.gameFrameController.getDataManager().closeConnection(0);
    }

    @Override
    public void mouseEntered(MouseEvent e)
    {
        MenuSelectionItem tmpItem = (MenuSelectionItem) e.getComponent();
        tmpItem.setInMouseFocus(true);
    }

    @Override
    public void mouseExited(MouseEvent e)
    {
        MenuSelectionItem tmpItem = (MenuSelectionItem) e.getComponent();
        tmpItem.setInMouseFocus(false);
    }

    @Override /**not in use*/
    public void mousePressed(MouseEvent e)
    {
    }

    @Override /**not in use*/
    public void mouseReleased(MouseEvent e)
    {

    }
}
