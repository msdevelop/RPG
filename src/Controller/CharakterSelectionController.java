package Controller;

import Model.CharakterModel;
import View.CharakterSelectionView;
import View.SelectionItem.CharakterSelectionButton;
import View.SelectionItem.CharakterSelectionItem;

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
    private LinkedList<CharakterModel> groupSelectionList = new LinkedList<CharakterModel>();
    private CharakterModel currentCharakter;
    private CharakterSelectionItem currentCharakterSelectionItem = null;
    private CharakterSelectionItem previousCharakterSelectionItem = null;
    private LinkedList<CharakterSelectionItem> selectedCharakterItems = new LinkedList<CharakterSelectionItem>();

    public CharakterSelectionController(GameFrameController paramGameFrameController)
    {
        this.gameFrameController = paramGameFrameController;
        this.charakterModelList = this.gameFrameController.getDataManager().getCharaktersRaw();
        this.charakterSelectionView = new CharakterSelectionView(this);

        int j = 0;
        for(int i = 0; i < this.charakterModelList.size(); i++)
        {
            if(i % 2 == 0 && i != 0)
                j++;

            this.charakterSelectionView.add(new CharakterSelectionItem(this.charakterModelList.get(i).getUrl(), (476 + (i % 2) * 74), (363 + j * 67), this.charakterModelList.get(i).getCharID(), this));
        }

        this.charakterSelectionView.add(new CharakterSelectionButton("hinzufuegen", 1150, 730, this));
        this.charakterSelectionView.add(new CharakterSelectionButton("fertig", 1150, 815, this));
        this.charakterSelectionView.add(new CharakterSelectionButton("remove", 1450, 815, this));
    }

    @Override
    public void mouseClicked(MouseEvent e)
    {
        if(e.getComponent().getName().startsWith("charakter"))
        {
            this.previousCharakterSelectionItem = this.currentCharakterSelectionItem;
            this.currentCharakterSelectionItem = (CharakterSelectionItem) e.getComponent();
            this.switchMouseListenerOnCharakterSelectionItem();
            this.forwardCharakter(Integer.parseInt(this.currentCharakterSelectionItem.getName().substring(10)));
        }
        else if(e.getComponent().getName().startsWith("btn"))
        {
            if(e.getComponent().getName().endsWith("hinzufuegen"))
            {
                if(this.charakterSelectionView.getIsCharSelected())
                    this.addCharakterToGroupSelection();
            }
            else if(e.getComponent().getName().endsWith("remove"))
            {
                if(this.groupSelectionList.size() > 0)
                    this.removeLastFromGroupSelection();
            }
            else if(e.getComponent().getName().endsWith("fertig"))
            {
                //TODO size() = 6 ------ testvalue
                if(this.groupSelectionList.size() == 3)
                {
                    int yesNoOption = JOptionPane.showConfirmDialog(null, "Ist ihre Gruppe fertiggestellt?", "Fortfahren?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                    if(yesNoOption == JOptionPane.YES_OPTION)
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
        if(e.getComponent().getName().startsWith("charakter"))
        {
            CharakterSelectionItem tmpItem = (CharakterSelectionItem) e.getComponent();
            tmpItem.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
        }
        else if(e.getComponent().getName().startsWith("btn"))
        {
            CharakterSelectionButton tmpButton = (CharakterSelectionButton) e.getComponent();
            tmpButton.setIntMouseFocus(true);
        }
    }

    @Override
    public void mouseExited(MouseEvent e)
    {
        if(e.getComponent().getName().startsWith("charakter"))
        {
            CharakterSelectionItem tmpItem = (CharakterSelectionItem) e.getComponent();
            tmpItem.setBorder(null);
        }
        else if(e.getComponent().getName().startsWith("btn"))
        {
            CharakterSelectionButton tmpButton = (CharakterSelectionButton) e.getComponent();
            tmpButton.setIntMouseFocus(false);
        }
    }

    @Override
    public void mousePressed(MouseEvent e)
    {

    }

    @Override
    public void mouseReleased(MouseEvent e)
    {

    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(this.charakterSelectionView.getIsCharSelected())
            this.charakterSelectionView.selectRandomName();
    }

    public void forwardCharakter(int paramID)
    {
        this.currentCharakter = this.charakterModelList.get(paramID);
        this.charakterSelectionView.synchronizeCharProperties(this.currentCharakter);
    }

    public void switchMouseListenerOnCharakterSelectionItem()
    {
        if(this.previousCharakterSelectionItem != null)
        {
            this.previousCharakterSelectionItem.addMouseListener(this);
            this.previousCharakterSelectionItem.setBorder(null);
        }
        this.currentCharakterSelectionItem.removeMouseListener(this);
    }

    public void addCharakterToGroupSelection()
    {
        if(this.charakterSelectionView.getNameFromTextfield().length() < 15)
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
            JOptionPane.showMessageDialog (null, "Charaktername darf maximal 15 Zeichen enthalten!", "Fehler beim Hinzufügen!", JOptionPane.WARNING_MESSAGE);
        }
    }

    public void removeLastFromGroupSelection()
    {
        this.groupSelectionList.removeLast();
        this.charakterSelectionView.removeSelectedCharakterImage();
        CharakterSelectionItem tmpItem = this.selectedCharakterItems.removeLast();
        tmpItem.addMouseListener(this);
    }

    public void finalizeCharakterSelection()
    {
        //TODO gruppe in DB abspeichern
        int[] charIDCollection = new int[6];
        for(int i = 0; i < this.groupSelectionList.size(); i++)
        {
            charIDCollection[i] = groupSelectionList.get(i).getCharID();
        }
        this.gameFrameController.getDataManager().createNewCharTableForUser(this.gameFrameController.getCurrentUser(), charIDCollection);
        this.gameFrameController.initializeMapSelection(this.groupSelectionList);
    }

    public CharakterSelectionView getCharakterSelectionView()
    {
        return this.charakterSelectionView;
    }
}
