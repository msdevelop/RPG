package Controller;

import Model.CharakterModel;
import View.CharakterSelectionView;
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

            this.charakterSelectionView.add(new CharakterSelectionItem(this.charakterModelList.get(i).getUrl(), (500 + i * 75), (300 + j * 64), this));
        }
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
        CharakterSelectionItem tmpItem = (CharakterSelectionItem) e.getComponent();
        tmpItem.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
    }

    @Override
    public void mouseExited(MouseEvent e)
    {
        CharakterSelectionItem tmpItem = (CharakterSelectionItem) e.getComponent();
        tmpItem.setBorder(null);
    }
}
