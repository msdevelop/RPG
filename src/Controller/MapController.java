package Controller;

import View.MapDetail;
import View.MapOverview;
import View.SelectionItem;
import View.SeparatorPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class MapController implements MouseListener
{
    private MapOverview mapOverview;
    private MapDetail mapDetail;
    private SeparatorPanel separatorPanel;

    public MapController()
    {
        this.mapOverview = new MapOverview(this);
        this.mapDetail = new MapDetail("gareth");
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

    @Override
    public void mouseClicked(MouseEvent e)
    {
        if(e.getComponent().getName().equals("gareth"))
            this.mapDetail.setMissionSelected(true);
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
        SelectionItem tmpItem = (SelectionItem) e.getComponent();
        tmpItem.setBorder(BorderFactory.createLineBorder(Color.blue, 2, true));
    }

    @Override
    public void mouseExited(MouseEvent e)
    {
        SelectionItem tmpItem = (SelectionItem) e.getComponent();
        tmpItem.setBorder(BorderFactory.createLineBorder(Color.black, 1, true));

    }
}
