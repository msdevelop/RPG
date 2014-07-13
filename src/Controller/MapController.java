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
    private MapDetailView mapDetailView;
    private GameFrameController gameFrameController;

    /*erzeugt MapOverview(View)
    * erzeugt MapDetailView(View)*/
    public MapController(GameFrameController paramGameFrameController)
    {
        this.gameFrameController = paramGameFrameController;
        this.mapOverview = new MapOverview(this);
        this.mapDetailView = new MapDetailView(this);
    }

    @Override
    public void mouseClicked(MouseEvent e)
    {   /*MouseClicked für OverviewSelectionItem(SelectionItem)*/
        if(e.getComponent().getName().startsWith("overview"))
        {
            /*Gibt den Namen der in der View ausgewählten Karte an die MapDetailView weiter um den Kartenausschnitt anzuzeigen
            * entfernt alle OverviewSelectionItems von der MapOverview*/
            this.mapDetailView.selectMission("detail_" + e.getComponent().getName().substring(9));
            this.mapOverview.disableView();
        }
        /*MouseClicked für DetailSelectionItem(SelectionItem)*/
        else if(e.getComponent().getName().startsWith("detail"))
        {
            this.gameFrameController.startTestLevel();
        }
    }

    @Override
    public void mouseEntered(MouseEvent e)
    {
        /*MouseEntered für OverviewSelectionItem*/
        if(e.getComponent().getName().startsWith("overview"))
        {
            OverviewSelectionItem tmpOverviewItem = (OverviewSelectionItem) e.getComponent();
            tmpOverviewItem.setMouseFocus(true);
        }
        /*MouseEntered für DetailSelectionItem*/
        else if(e.getComponent().getName().startsWith("detail"))
        {
            DetailSelectionItem tmpDetailItem = (DetailSelectionItem) e.getComponent();
            tmpDetailItem.setBorder(BorderFactory.createLineBorder(Color.blue, 2, true));
        }
    }

    @Override
    public void mouseExited(MouseEvent e)
    {
        /*MouseExited für OverviewSelectionItem*/
        if(e.getComponent().getName().startsWith("overview"))
        {
            OverviewSelectionItem tmpItem = (OverviewSelectionItem) e.getComponent();
            tmpItem.setMouseFocus(false);
        }
        /*MouseExited für DetailSelectionItem*/
        else if(e.getComponent().getName().startsWith("detail"))
        {
            DetailSelectionItem tmpDetailItem = (DetailSelectionItem) e.getComponent();
            tmpDetailItem.setBorder(BorderFactory.createLineBorder(Color.black, 1, true));
        }
    }


    /*Gibt aktuelle MapOverview(View) zurück*/
    public MapOverview getMapOverview()
    {
        return this.mapOverview;
    }

    /*Gibt aktuelle MapDetailView(View) zurück*/
    public MapDetailView getMapDetailView()
    {
        return this.mapDetailView;
    }

    /*Gibt aktuellen GameFrameController zurück*/
    public GameFrameController getGameFrameController()
    {
        return this.gameFrameController;
    }

    @Override /*not in use*/
    public void mousePressed(MouseEvent e)
    {

    }

    @Override /*not in use*/
    public void mouseReleased(MouseEvent e)
    {

    }
}
