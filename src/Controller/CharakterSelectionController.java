package Controller;

import Model.CharakterModel;
import View.CharakterSelectionView;
import View.SelectionItem.CharakterSelectionButton;
import View.SelectionItem.CharakterSelectionItem;

import javax.swing.border.BevelBorder;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.LinkedList;

public class CharakterSelectionController implements MouseListener
{
    private GameFrameController gameFrameController;
    private CharakterSelectionView charakterSelectionView;
    private LinkedList<CharakterModel> charakterModelList;

    public CharakterSelectionController(GameFrameController paramGameFrameController)
    {
        this.gameFrameController = paramGameFrameController;
        this.charakterModelList = this.gameFrameController.getDataManager().getCharaktersRaw();
        this.charakterSelectionView = new CharakterSelectionView();

        int j = 0;
        for(int i = 0; i < this.charakterModelList.size(); i++)
        {
            if(i%2 == 0 && i != 0)
                j++;

            this.charakterSelectionView.add(new CharakterSelectionItem(this.charakterModelList.get(i).getUrl(), (476 + (i%2) * 74), (363 + j * 67), this.charakterModelList.get(i).getCharID(), this));
        }

        this.charakterSelectionView.add(new CharakterSelectionButton("hinzufuegen", 1150, 730, this));
        this.charakterSelectionView.add(new CharakterSelectionButton("fertig", 1150, 815, this));
        this.charakterSelectionView.add(new CharakterSelectionButton("reset", 1450, 815, this));
    }

    public CharakterSelectionView getCharakterSelectionView()
    {
        return this.charakterSelectionView;
    }

    @Override
    public void mouseClicked(MouseEvent e)
    {

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
}
