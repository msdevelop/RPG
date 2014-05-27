package Controller;

import View.*;
import View.SelectionItem.DetailSelectionItem;
import View.SelectionItem.OverviewSelectionItem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MapController implements MouseListener
{
    private MapOverview mapOverview;
    private MapDetail mapDetail;
    private GameFrameController gameFrameController;

    public MapController(GameFrameController paramGameFrameController)
    {
        this.gameFrameController = paramGameFrameController;
        this.mapOverview = new MapOverview(this);
        this.mapDetail = new MapDetail(this);
    }

    public MapOverview getMapOverview()
    {
        return this.mapOverview;
    }

    public MapDetail getMapDetail()
    {
        return this.mapDetail;
    }

    public GameFrameController getGameFrameController()
    {
        return this.gameFrameController;
    }

    @Override
    public void mouseClicked(MouseEvent e)
    {
        if(e.getComponent().getName().startsWith("overview"))
        {
            this.mapDetail.selectMission("detail" + e.getComponent().getName().substring(8));
            this.mapOverview.disableView();
        }
        else if(e.getComponent().getName().startsWith("detail"))
            System.out.println("Success!");
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
        if(e.getComponent().getName().startsWith("overview"))
        {
            OverviewSelectionItem tmpOverviewItem = (OverviewSelectionItem) e.getComponent();
            tmpOverviewItem.setMouseFocus(true);
        }
        else if(e.getComponent().getName().startsWith("detail"))
        {
            DetailSelectionItem tmpDetailItem = (DetailSelectionItem) e.getComponent();
            tmpDetailItem.setBorder(BorderFactory.createLineBorder(Color.blue, 2, true));
        }
    }

    @Override
    public void mouseExited(MouseEvent e)
    {
        if(e.getComponent().getName().startsWith("overview"))
        {
            OverviewSelectionItem tmpItem = (OverviewSelectionItem) e.getComponent();
            tmpItem.setMouseFocus(false);
        }
        else if(e.getComponent().getName().startsWith("detail"))
        {
            DetailSelectionItem tmpDetailItem = (DetailSelectionItem) e.getComponent();
            tmpDetailItem.setBorder(BorderFactory.createLineBorder(Color.black, 1, true));
        }
    }
}
