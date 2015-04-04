package Controller;

import CustomExceptions.CustomImageException;
import Model.AttributeTooltipModel;
import Model.CharakterModel;
import View.CharakterSelectionView;
import View.Overlay.AttributeTooltipOverlay;
import View.SelectionItem.CharakterSelectionButton;
import View.SelectionItem.CharakterSelectionItem;
import View.SelectionItem.TooltipSelectionItem;
import org.w3c.dom.Attr;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.LinkedList;

public class CharakterSelectionController implements MouseListener, ActionListener
{
    private GameFrameController gameFrameController;
    private CharakterSelectionView charakterSelectionView;
    private LinkedList<CharakterModel> charakterModelList;
    private LinkedList<CharakterModel> groupSelectionList = new LinkedList<>();
    private CharakterModel currentCharakter;
    private CharakterSelectionItem currentCharakterSelectionItem = null;
    private CharakterSelectionItem previousCharakterSelectionItem = null;
    private LinkedList<CharakterSelectionItem> selectedCharakterItems = new LinkedList<>();
    private LinkedList<AttributeTooltipModel> attributeTooltipModelList = new LinkedList<>();
    private AttributeTooltipOverlay attributeTooltipOverlay;

    /**
     * Initialisiert die CharakterSelectionView
     * speichert in 'charakterModelList' alle aus der Datenbanktabelle 'charakterraw' ausgelesenen Charaktere als CharakterModel(Model) -> DataManager.getCharaktersRaw()
     * erzeugt für jeden Charakter in 'charakterModelList' ein CharakterSelectionItem(SelectionItem)
     * erzeugt notwendige CharakterSelectionButton(SelectionItem)
     */
    public CharakterSelectionController(GameFrameController paramGameFrameController)
    {
        this.gameFrameController = paramGameFrameController;
        this.charakterModelList = this.gameFrameController.getDataManager().getCharaktersRaw();

        try
        {
            this.charakterSelectionView = new CharakterSelectionView(this);
            this.gameFrameController.getGameFrame().getContentPane().add(this.charakterSelectionView);

            int j = 0;
            for(int i = 0; i < this.charakterModelList.size(); i++)
            {
                if(i % 2 == 0 && i != 0)
                    j++;

                this.charakterSelectionView.add(new CharakterSelectionItem(this.charakterModelList.get(i).getUrl(), (476 + (i % 2) * 74), (363 + j * 67), this.charakterModelList.get(i).getCharID(), this));
            }
            this.charakterSelectionView.add(new CharakterSelectionButton("hinzufuegen", 1150, 730, this));
            this.charakterSelectionView.add(new CharakterSelectionButton("fertig", 1150, 815, this));
            this.charakterSelectionView.add(new CharakterSelectionButton("remove", 1150, 900, this));

            this.attributeTooltipOverlay = new AttributeTooltipOverlay();
            this.charakterSelectionView.add(this.attributeTooltipOverlay);
        }
        catch(CustomImageException e)
        {
            this.gameFrameController.handleCustomImageException(e);
        }

        this.addTooltipSelectionItems();
    }

    /**Fügt CharakterSelectionView TooltipSelectionItems hinzu*/
    public void addTooltipSelectionItems()
    {
        this.attributeTooltipModelList = this.gameFrameController.getDataManager().getAttributeTooltips();

        //TODO fehlende tooltips hinzufügen
        //this.charakterSelectionView.add(new TooltipSelectionItem(300, 300, 50, 50, "0_lebenspkte", this));
        this.charakterSelectionView.add(new TooltipSelectionItem(300, 300, 50, 50, "1_astralpkte", this));
        this.charakterSelectionView.add(new TooltipSelectionItem(100, 100, 50, 50, "2_mut", this));
        //this.charakterSelectionView.add(new TooltipSelectionItem(300, 300, 50, 50, "3_klugheit", this));
        //this.charakterSelectionView.add(new TooltipSelectionItem(300, 300, 50, 50, "4_intuition", this));
        //this.charakterSelectionView.add(new TooltipSelectionItem(300, 300, 50, 50, "5_charisma", this));
        //this.charakterSelectionView.add(new TooltipSelectionItem(300, 300, 50, 50, "6_fingerfertigkeit", this));
        //this.charakterSelectionView.add(new TooltipSelectionItem(300, 300, 50, 50, "7_gewandheit", this));
        //this.charakterSelectionView.add(new TooltipSelectionItem(300, 300, 50, 50, "8_koerperkraft", this));
        //this.charakterSelectionView.add(new TooltipSelectionItem(300, 300, 50, 50, "9_aberglaube", this));
        //this.charakterSelectionView.add(new TooltipSelectionItem(300, 300, 50, 50, "10_ausdauer", this));
    }

    /**
     * fügt previous einen MouseListener hinzu und setzt Border auf null -> wenn previous != null
     * entfernt MouseListener von current
     */
    private void switchMouseListenerOnCharakterSelectionItem()
    {
        if(this.previousCharakterSelectionItem != null)
        {
            this.previousCharakterSelectionItem.addMouseListener(this);
            this.previousCharakterSelectionItem.setBorder(null);
        }
        this.currentCharakterSelectionItem.removeMouseListener(this);
    }

    /**
     * speichert den derzeit ausgewählten Charakter -> currentCharakter(CharakterModel)
     * übergibt currentCharakter an charakterSelectionView.synchronizeProperties() um Charakterwerte in der CharakterSelectionView anzuzeigen
     */
    private void forwardCharakter(int paramID)
    {
        this.currentCharakter = this.charakterModelList.get(paramID);
        this.charakterSelectionView.synchronizeCharProperties(this.currentCharakter);
    }

    /**
     * Fügt den derzeit ausgewählten Charakter der Gruppe hinzu
     * prüft ob der eingegebene Charaktername nicht länger als 15 Zeichen ist -> Fehler sonst (JOptionPange)
     * setzt die Border des ausgewählten CharakterSelectionItem auf null -> this.currentCharakterSelectionItem.setBorder()
     * fügt currentCharakterSelectionItem einer Liste mit CharakterSelectionItems der sich in der Gruppe befindenden Charaktere hinzu
     * current und previous (CharakterSelectionItem) werden auf null gesetzt
     * der Name des ausgewählten Charakters wird mit Namen aus dem TextField der CharakterSelectionView gesetzt -> currentCharakter.setName()
     * currentCharakter wird der Liste mit ausgewählten Charakteren hinzugefüt (Gruppe) -> this.groupSelectionList.add()
     * fügt das Bild des Charakters der Gruppenauswahl hinzu -> this.charakterSelectionView.addSelectedCharakterImage()
     * setzt isCharSelected(boolean) der CharakterSelectionView auf false damit keine Charakterwerte mehr in der View angezeigt werden
     */
    private void addCharakterToGroupSelection()
    {
        String tmpName = this.charakterSelectionView.getNameFromTextfield();
        if((tmpName.length() >= 2) && (tmpName.length() < 15))
        {
            this.currentCharakterSelectionItem.setBorder(null);
            this.selectedCharakterItems.add(this.currentCharakterSelectionItem);
            this.currentCharakterSelectionItem = null;
            this.previousCharakterSelectionItem = null;
            this.currentCharakter.setName(this.charakterSelectionView.getNameFromTextfield());
            this.groupSelectionList.add(this.currentCharakter);
            this.charakterSelectionView.addSelectedCharakterImage(this.currentCharakter.getUrl());
            this.charakterSelectionView.setIsCharSelected(false);
        }
        else
        {
            JOptionPane.showMessageDialog(null, "Charaktername zu kurz oder zu lang!\nMindestens 2 maximal 15 Zeichen.", "Fehler beim Hinzufügen!", JOptionPane.WARNING_MESSAGE);
        }
    }

    /**
     * entfernt den zuletzt hinzugefügten Charakter aus this.groupSelectionList
     * entfernt das Bild des Charakters aus der Gruppenauswahl -> this.charakterSelectionView.removeSelectedCharakterImage()
     * entfernt das CharakterSelectionItem des aus der Liste entfernten Charakters aus der Liste der ausgewählten Charaktere -> this.selectedCHarakterItems.removeLast()
     * fügt dem CharakterSelectionItem des soeben aus der Gruppe entfernten Charakters einen MouseListener hinzu -> tmpItem.addMouseListener()
     */
    private void removeLastFromGroupSelection()
    {
        this.groupSelectionList.removeLast();
        this.charakterSelectionView.removeSelectedCharakterImage();
        CharakterSelectionItem tmpItem = this.selectedCharakterItems.removeLast();
        tmpItem.addMouseListener(this);
    }

    /**
     * Beendet die erfolgreiche Charakterauswahl
     * speichert die charID's der Charaktere aus der Gruppe -> int[] charIDCollection
     * speichert die Namen der Charaktere aus der Gruppe -> String[] charNameCollection
     * übergibt die beiden Arrays und den Namen des currentUsers an den DataManager -> DataManager.createNewCharTableForUser()
     * übergibt die finale Gruppe und ruft die MapSelection auf -> GameFrameController.initiateMapSelection()
     */
    private void finalizeCharakterSelection()
    {
        int[] charIDCollection = new int[6];
        String[] charNameCollection = new String[6];
        for(int i = 0; i < this.groupSelectionList.size(); i++)
        {
            charIDCollection[i] = groupSelectionList.get(i).getCharID();
            charNameCollection[i] = groupSelectionList.get(i).getName();
        }
        this.gameFrameController.getDataManager().createNewCharTableForUser(this.gameFrameController.getCurrentUser(), charIDCollection, charNameCollection);
        this.charakterSelectionView.setVisible(false);
        this.gameFrameController.getGameFrame().getContentPane().remove(this.charakterSelectionView);
        this.gameFrameController.initiateMapSelection(this.groupSelectionList);
    }

    @Override
    public void mouseClicked(MouseEvent e)
    {
        String tmpComponentName = e.getComponent().getName();

        /**MouseClicked für CharakterSelectionItems*/
        if(tmpComponentName.startsWith("charakter"))
        {
            /**Speichert das aktuell(current) und das zuvor(previous) ausgewählte CharakterSelectionItem
             * deaktiviert den MouseListener von current und aktiviert den MouseListener von previous -> this.switchMouseListenerOnCharakterSelectionItem()
             * übergibt current an CharakterSelectionView -> this.forwardCharakter()*/
            this.previousCharakterSelectionItem = this.currentCharakterSelectionItem;
            this.currentCharakterSelectionItem = (CharakterSelectionItem) e.getComponent();
            this.switchMouseListenerOnCharakterSelectionItem();
            this.forwardCharakter(Integer.parseInt(this.currentCharakterSelectionItem.getName().substring(10)));
        }
        /**MouseClicked für CharakterSelectionButtons*/
        else if(tmpComponentName.startsWith("btn"))
        {
            if(tmpComponentName.endsWith("hinzufuegen"))
            {
                /**Bereitet das Hinzufügen des ausgewählten Charakters zur Gruppe vor
                 * prüft ob ein Charakter ausgewählt ist
                 * prüft ob die Anzahl der bereits ausgewählten Charaktere unter der Maximum (6) liegt -> Fehler sonst (JOptionPane)
                 * übergibt -> this.addCHarakterToGroupSelection()*/
                if(this.charakterSelectionView.getIsCharSelected())
                {
                    if(this.groupSelectionList.size() < 6)
                        this.addCharakterToGroupSelection();
                    else
                        JOptionPane.showMessageDialog(null, "Ihre Gruppe ist voll!", "Gruppe ist voll", JOptionPane.INFORMATION_MESSAGE);
                }
            }
            else if(tmpComponentName.endsWith("remove"))
            {
                /**Bereitet das Entfernen des zuletzt der Gruppe hinzugefügten Charakters vor
                 * Prüft ob Gruppe nicht leer
                 * übergibt -> this.removeLastFromGroupSelection()*/
                if(this.groupSelectionList.size() > 0)
                    this.removeLastFromGroupSelection();
            }
            else if(tmpComponentName.endsWith("fertig"))
            {
                /**Beendet die Gruppenauswahl -> this.finalizeCharakterSelection(), wenn
                 * Gruppe 6 Charaktere enthält -> Fehler sonst (JOptionPane)
                 * Benutzerabfrage YES_OPTION ergbigt*/
                if(this.groupSelectionList.size() == 6)
                {
                    int userInputOption = JOptionPane.showConfirmDialog(null, "Ist ihre Gruppe fertiggestellt?", "Fortfahren?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if(userInputOption == JOptionPane.YES_OPTION)
                        this.finalizeCharakterSelection();
                }
                else
                    JOptionPane.showMessageDialog(null, "Bitte wählen Sie 6 Charaktere aus!", "Gruppe nicht voll", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }

    @Override
    public void mouseEntered(MouseEvent e)
    {
        String tmpComponentName = e.getComponent().getName();

        /**MouseEntered für CharakterSelectionItems
         * fügt BevelBorder hinzu*/
        if(tmpComponentName.startsWith("charakter"))
        {
            CharakterSelectionItem tmpItem = (CharakterSelectionItem) e.getComponent();
            tmpItem.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
        }
        /**MouseEntered für CharakterSelectionButtons
         * setzt den Wert 'isInMouseFocus(boolean)' von e.getComponent(CharakterSelectionButton) auf true
         * OnMouseOverEffect -> neues Bild*/
        else if(tmpComponentName.startsWith("btn"))
        {
            CharakterSelectionButton tmpButton = (CharakterSelectionButton) e.getComponent();
            tmpButton.setIntMouseFocus(true);
        }
        /**MouseEntered für TooltipSelectionItem
         * zeigt Tooltip an*/
        else if(tmpComponentName.startsWith("tooltip"))
            this.attributeTooltipOverlay.setTooltipString(this.attributeTooltipModelList.get(Integer.parseInt(tmpComponentName.substring(8, 9))).getTooltipText());
     }

    @Override
    public void mouseExited(MouseEvent e)
    {
        /**MouseExited für CharakterSelectionItems
         * setzt Border auf null*/
        if(e.getComponent().getName().startsWith("charakter"))
        {
            CharakterSelectionItem tmpItem = (CharakterSelectionItem) e.getComponent();
            tmpItem.setBorder(null);
        }
        /**MouseExited für CharakterSelectionButtons
         * setzt den Wert 'isInMouseFocus(boolean)' von e.getComponent(CharakterSelectionButton) auf false
         * OnMOuseExitedEffect -> neues Bild*/
        else if(e.getComponent().getName().startsWith("btn"))
        {
            CharakterSelectionButton tmpButton = (CharakterSelectionButton) e.getComponent();
            tmpButton.setIntMouseFocus(false);
        }
    }

    @Override
    /**ActionEvent für JButton Zufallsname*/
    public void actionPerformed(ActionEvent e)
    {
        /**Prüft ob ein Charakter ausgewählt ist
         * übergibt -> this.charakterSelectionView.selectRandomName()*/
        if(this.charakterSelectionView.getIsCharSelected())
            this.charakterSelectionView.selectRandomName();
    }

    /**
     * Gibt den aktuellen GameFrameController zurück
     */
    public GameFrameController getGameFrameController()
    {
        return this.gameFrameController;
    }

    @Override
    /**not in use*/
    public void mousePressed(MouseEvent e)
    {

    }

    @Override
    /**not in use*/
    public void mouseReleased(MouseEvent e)
    {

    }
}
