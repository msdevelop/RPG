package Controller;

import Model.CharakterModel;
import View.CharakterSelectionView;

import java.util.LinkedList;

public class CharakterSelectionController
{
    private GameFrameController gameFrameController;
    private CharakterSelectionView charakterSelectionView;
    private LinkedList<CharakterModel> charakterModelList;

    public CharakterSelectionController(GameFrameController paramGameFrameController)
    {
        this.gameFrameController = paramGameFrameController;
        this.charakterModelList = this.gameFrameController.getDataManager().getCharaktersRaw();
        this.charakterSelectionView = new CharakterSelectionView();
    }
}
