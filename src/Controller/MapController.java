package Controller;

import View.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MapController implements MouseListener
{
    private MapOverview mapOverview;
    private MapDetail mapDetail;
    private SeparatorPanel separatorPanel;
    private GameFrameController gameFrameController;

    public MapController(GameFrameController paramGameFrameController)
    {
        this.gameFrameController = paramGameFrameController;
        this.mapOverview = new MapOverview(this);
        this.mapDetail = new MapDetail(this);
        this.separatorPanel = new SeparatorPanel();
    }

    public MapOverview getMapOverview()
    {
        return this.mapOverview;
    }

    public MapDetail getMapDetail()
    {
        return this.mapDetail;
    }

    public SeparatorPanel getSeparatorPanel()
    {
        return this.separatorPanel;
    }

    public GameFrameController getGameFrameController()
    {
        return this.gameFrameController;
    }

    @Override
    public void mouseClicked(MouseEvent e)
    {
        if(e.getComponent().getName().equals("overviewGareth"))
            this.mapDetail.selectMission("detailGareth");
        else if(e.getComponent().getName().equals("detailGareth"))
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
            tmpOverviewItem.setBorder(BorderFactory.createLineBorder(Color.blue, 2, true));
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
            tmpItem.setBorder(BorderFactory.createLineBorder(Color.black, 1, true));
        }
        else if(e.getComponent().getName().startsWith("detail"))
        {
            DetailSelectionItem tmpDetailItem = (DetailSelectionItem) e.getComponent();
            tmpDetailItem.setBorder(BorderFactory.createLineBorder(Color.black, 1, true));
        }
    }
}
